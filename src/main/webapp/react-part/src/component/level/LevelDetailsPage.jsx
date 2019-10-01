import React, { Component } from "react";
import PageName from "../PageName";
import * as Constants from "../../Constants";
import AutorepairService from "../../service/AutorepairService";
import { Formik, Field, ErrorMessage } from "formik";
import ErrorMessageBlock from "../message/ErrorMessageBlock";
import BackButton from "../BackButton";
import * as Validation from "../../Validation"

class LevelDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      levelId: this.props.match.params.id,
      details: null,
      isNew: false
    };
    this.requestLevelDetails = this.requestLevelDetails.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.levelId === "-1") {
      return;
    }
    this.setState({ isNew: false });
    this.requestLevelDetails(this.state.levelId);
  }

  requestLevelDetails(id) {
    AutorepairService.getLevel(id)
      .then(response => {
        this.setState({ details: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  goBack() {
    this.props.history.push("/levels");
  }

  onSubmit(values) {
    let level = {
      levelId: this.state.levelId,
      name: values.name
    };

    if (level.levelId === "-1") {
      AutorepairService.createLevel(level)
        .then(() => {
          this.props.history.push("/levels");
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    } else {
      AutorepairService.updateLevel(level.levelId, level)
        .then(() => {
          this.props.history.push("/levels");
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    }
  }

  render() {
    let name = this.state.details ? this.state.details.name : "";
    let errorMessage = this.state.errorMessage;
    let isNew = this.state.isNew;

    return (
      <div className="container">
        <PageName pageName={Constants.levelDetailPageName} />
        <Formik
          initialValues={{
            name,
            errorMessage,
            isNew
          }}
          validationSchema={Validation.levelSchema}
          onSubmit={this.onSubmit}
          enableReinitialize={true}
          render={({ handleSubmit, values }) => (
            <form onSubmit={handleSubmit}>
              {values.message && <ErrorMessageBlock message={values.message} />}
              <fieldset className="form-group">
                <label>Название</label>
                <Field
                  className="form-control"
                  type="text"
                  id="name"
                  value={values.isNew ? "" : values.name}
                />
                <ErrorMessage
                  name="name"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <button className="btn btn-success" type="submit">
                Сохранить
              </button>
            </form>
          )}
        />
        <BackButton goBackAction={this.goBack}/>
      </div>
    );
  }
}

export default LevelDetailsPage;
