import React, { Component } from "react";
import PageName from "../PageName";
import * as Constants from "../../Constants";
import AutorepairService from "../../service/AutorepairService";
import { Formik, Field, ErrorMessage } from "formik";
import ErrorMessageBlock from "../message/ErrorMessageBlock";
import BackButton from "../BackButton";
import * as Validation from "../../Validation";
import { Select } from "antd";
import AuthenticationService from "../../service/AuthenticationService";

class CarDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      carId: this.props.match.params.carId,
      workshopId: this.props.match.params.workshopId,
      details: null,
      customers: null,
      masters: null,
      isNew: false
    };
    this.requestCarDetails = this.requestCarDetails.bind(this);
    this.requestAllCustomers = this.requestAllCustomers.bind(this);
    this.requestAllMasters = this.requestAllMasters.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.carId !== "-1") {
      this.setState({ isNew: false });
      this.requestCarDetails(this.state.carId);
    }
    this.requestAllCustomers();
    this.requestAllMasters();
  }

  requestAllMasters() {
    AutorepairService.getMastersByWorkshopId(this.state.workshopId)
      .then(response => {
        this.setState({ masters: response.data });
      })
      .catch(e => {
        this.setState({ message: e.message });
        AuthenticationService.redirectToLoginIfUnauthorized(
          e.response.status,
          this.props.history
        );
      });
  }

  requestAllCustomers() {
    AutorepairService.getCustomersByWorkshopId(this.state.workshopId)
      .then(response => {
        this.setState({ customers: response.data });
      })
      .catch(e => {
        this.setState({ message: e.message });
        AuthenticationService.redirectToLoginIfUnauthorized(
          e.response.status,
          this.props.history
        );
      });
  }

  requestCarDetails(id) {
    AutorepairService.getCar(id)
      .then(response => {
        this.setState({ details: response.data });
      })
      .catch(e => {
        this.setState({ message: e.message });
        AuthenticationService.redirectToLoginIfUnauthorized(
          e.response.status,
          this.props.history
        );
      });
  }

  goBack() {
    this.props.history.goBack();
  }

  onSubmit(values) {
    let car = {
      carId: this.state.carId,
      carNumber: values.carNumber,
      mark: values.mark,
      model: values.model,
      crashType: values.crashType,
      mileage: values.mileage,
      masterId: values.masterId
        ? values.masterId
        : this.state.details
        ? this.state.details.master.masterId
        : this.state.masters[0].masterId,
      customerId: values.customerId
        ? values.customerId
        : this.state.details
        ? this.state.details.customer.customerId
        : this.state.customers[0].customerId
    };

    if (car.carId === "-1") {
      AutorepairService.createCar(car)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    } else {
      AutorepairService.updateCar(car.carId, car)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    }
  }

  render() {
    let carNumber = this.state.details ? this.state.details.carNumber : "";
    let mark = this.state.details ? this.state.details.mark : "";
    let model = this.state.details ? this.state.details.model : "";
    let crashType = this.state.details ? this.state.details.crashType : "";
    let mileage = this.state.details ? this.state.details.mileage : "";
    let masters = this.state.masters ? this.state.masters : [];
    let customers = this.state.customers ? this.state.customers : [];
    let masterId = this.state.details
      ? this.state.details.master
        ? this.state.details.master.masterId
        : ""
      : "";
    let customerId = this.state.details
      ? this.state.details.customer.customerId
      : "";
    let errorMessage = this.state.errorMessage;
    let isNew = this.state.isNew;

    return (
      <div className="container">
        <PageName pageName={Constants.carDetailsPageName} />
        <Formik
          initialValues={{
            carNumber,
            mark,
            model,
            crashType,
            mileage,
            masters,
            customers,
            masterId,
            customerId,
            errorMessage,
            isNew
          }}
          validationSchema={Validation.carSchema}
          onSubmit={this.onSubmit}
          enableReinitialize={true}
          render={({ setFieldValue, handleSubmit, values }) => (
            <form onSubmit={handleSubmit}>
              {values.message && <ErrorMessageBlock message={values.message} />}
              <fieldset className="form-group">
                <label>Номер автомобиля</label>
                <Field
                  className="form-control"
                  type="text"
                  id="carNumber"
                  value={values.isNew ? "" : values.carNumber}
                />
                <ErrorMessage
                  name="carNumber"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Марка</label>
                <Field
                  className="form-control"
                  type="text"
                  id="mark"
                  value={values.isNew ? "" : values.mark}
                />
                <ErrorMessage
                  name="mark"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Модель</label>
                <Field
                  className="form-control"
                  type="text"
                  id="model"
                  value={values.isNew ? "" : values.model}
                />
                <ErrorMessage
                  name="model"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Что сломалось</label>
                <Field
                  className="form-control"
                  type="text"
                  id="crashType"
                  value={values.isNew ? "" : values.crashType}
                />
                <ErrorMessage
                  name="crashType"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Пробег</label>
                <Field
                  className="form-control"
                  type="number"
                  id="mileage"
                  value={values.isNew ? "" : values.mileage}
                />
                <ErrorMessage
                  name="mileage"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Мастер</label>
                <Field
                  name="masterId"
                  render={({ field }) => (
                    <Select
                      {...field}
                      onChange={value => setFieldValue("masterId", value)}
                      value={values.masterId}
                    >
                      {masters.map(master => (
                        <Select.Option
                          value={master.masterId}
                          selected={values.masterId === master.masterId}
                        >
                          {master.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Клиент</label>
                <Field
                  name="customerId"
                  render={({ field }) => (
                    <Select
                      {...field}
                      onChange={value => setFieldValue("customerId", value)}
                      value={values.customerId}
                    >
                      {customers.map(customer => (
                        <Select.Option
                          value={customer.customerId}
                          selected={values.customerId === customer.customerId}
                        >
                          {customer.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                />
              </fieldset>
              <button className="btn btn-success" type="submit">
                Сохранить
              </button>
            </form>
          )}
        />
        <BackButton goBackAction={this.goBack} />
      </div>
    );
  }
}

export default CarDetailsPage;
