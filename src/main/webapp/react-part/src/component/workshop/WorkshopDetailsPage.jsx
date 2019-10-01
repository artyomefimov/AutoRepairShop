import React, { Component } from "react";
import PageName from "../PageName";
import * as Constants from "../../Constants";
import AutorepairService from "../../service/AutorepairService";
import { Formik, Field, ErrorMessage } from "formik";
import ErrorMessageBlock from "../message/ErrorMessageBlock";
import BackButton from "../BackButton";
import * as Validation from "../../Validation"

class WorkshopDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      id: this.props.match.params.id,
      details: null,
      isNew: false
    };
    this.requestWorkshopDetails = this.requestWorkshopDetails.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.id === "-1") {
      return;
    }
    this.setState({ isNew: false });
    this.requestWorkshopDetails(this.state.id);
  }

  requestWorkshopDetails(id) {
    AutorepairService.getWorkshop(id)
      .then(response => {
        this.setState({ details: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  goBack() {
    this.props.history.push("/workshops");
  }

  onSubmit(values) {
    let workshop = {
      workshopId: this.state.id,
      inn: values.inn,
      name: values.name,
      address: values.address,
      openHours: values.openHours,
      closeHours: values.closeHours,
      ownerName: values.ownerName
    };

    if (workshop.workshopId === "-1") {
      AutorepairService.createWorkshop(workshop)
        .then(() => {
          this.props.history.push("/workshops");
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    } else {
      AutorepairService.updateWorkshop(workshop.workshopId, workshop)
        .then(() => {
          this.props.history.push("/workshops");
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    }
  }

  render() {
    let inn = this.state.details ? this.state.details.inn : "";
    let name = this.state.details ? this.state.details.name : "";
    let address = this.state.details ? this.state.details.address : "";
    let openHours = this.state.details ? this.state.details.openHours : "";
    let closeHours = this.state.details ? this.state.details.closeHours : "";
    let ownerName = this.state.details ? this.state.details.ownerName : "";
    let errorMessage = this.state.errorMessage;
    let isNew = this.state.isNew;

    return (
      <div className="container">
        <PageName pageName={Constants.workshopDetailPageName} />
        <Formik
          initialValues={{
            inn,
            name,
            address,
            openHours,
            closeHours,
            ownerName,
            errorMessage,
            isNew
          }}
          onSubmit={this.onSubmit}
          validationSchema={Validation.workshopSchema}
          enableReinitialize={true}
          render={({ handleSubmit, values }) => (
            <form onSubmit={handleSubmit}>
              {values.message && <ErrorMessageBlock message={values.message} />}
              <fieldset className="form-group">
                <label>ИНН</label>
                <Field
                  className="form-control"
                  type="number"
                  id="inn"
                  value={values.isNew ? "" : values.inn}
                />
                <ErrorMessage
                  name="inn"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
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
              <fieldset className="form-group">
                <label>Адрес</label>
                <Field
                  className="form-control"
                  type="text"
                  id="address"
                  value={values.isNew ? "" : values.address}
                />
                <ErrorMessage
                  name="address"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Открывается в</label>
                <Field
                  className="form-control"
                  type="time"
                  id="openHours"
                  value={values.isNew ? "00:00:00" : values.openHours}
                />
                <ErrorMessage
                  name="openHours"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Закрывается в</label>
                <Field
                  className="form-control"
                  type="time"
                  id="closeHours"
                  value={values.isNew ? "00:00:00" : values.closeHours}
                />
                <ErrorMessage
                  name="closeHours"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Имя владельца</label>
                <Field
                  className="form-control"
                  type="text"
                  id="ownerName"
                  value={values.isNew ? "" : values.ownerName}
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

export default WorkshopDetailsPage;
