import React, { Component } from "react";
import PageName from "../PageName";
import * as Constants from "../../Constants";
import AutorepairService from "../../service/AutorepairService";
import { Formik, Field, ErrorMessage } from "formik";
import * as Yup from "yup";
import ErrorMessageBlock from "../message/ErrorMessageBlock";
import BackButton from "../BackButton";

class LevelDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      id: this.props.match.params.id,
      details: null,
      isNew: false
    };
    this.requestLevelDetails = this.requestLevelDetails.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.id === "-1") {
      return;
    }
    this.setState({ isNew: false });
    this.requestLevelDetails(this.state.id);
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
      levelId: this.state.id,
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
          validationSchema={Yup.object().shape({
            name: Yup.string()
              .trim()
              .min(3, "Название квалификации должно иметь минимум 3 символа!")
              .max(
                60,
                "Название квалификации должно иметь максимум 60 символов!"
              )
              .required("Введите название квалификации!")
          })}
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
