import React, { Component } from "react";
import PageName from "../PageName";
import * as Constants from "../../Constants";
import AutorepairService from "../../service/AutorepairService";
import { Formik, Field, ErrorMessage } from "formik";
import ErrorMessageBlock from "../message/ErrorMessageBlock";
import BackButton from "../BackButton";
import * as Validation from "../../Validation";
import * as Utils from "../../utils/Utils";
import { Select } from "antd";

class CustomerDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      customerId: this.props.match.params.customerId,
      details: null,
      workshops: null,
      isNew: false
    };
    this.requestCustomerDetails = this.requestCustomerDetails.bind(this);
    this.requestAllWorkshops = this.requestAllWorkshops.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.customerId !== "-1") {
      this.setState({ isNew: false });
      this.requestCustomerDetails(this.state.customerId);
    }
    this.requestAllWorkshops();
  }

  requestAllWorkshops() {
    AutorepairService.getAllWorkshops()
      .then(response => {
        this.setState({ workshops: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  requestCustomerDetails(id) {
    AutorepairService.getCustomer(id)
      .then(response => {
        this.setState({ details: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  goBack() {
    this.props.history.goBack();
  }

  onSubmit(values) {
    let customer = {
      customerId: this.state.customerId,
      customerPassportNum: parseInt(values.series + "" + values.num),
      name: values.name,
      phone: values.phone,
      address: values.address,
      birthDate: values.birthDate,
      workshopId: values.workshopId
        ? values.workshopId
        : this.state.details
        ? this.state.details.workshop.workshopId
        : this.state.workshops[0].workshopId
    };

    if (customer.customerId === "-1") {
      AutorepairService.createCustomer(customer)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    } else {
      AutorepairService.updateCustomer(customer.customerId, customer)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    }
  }

  render() {
    let { series, num } = this.state.details
      ? Utils.splitPassportNumOnSeriesAndNum(
          this.state.details.customerPassportNum
        )
      : { series: "", num: "" };
    let name = this.state.details ? this.state.details.name : "";
    let phone = this.state.details ? this.state.details.phone : "";
    let address = this.state.details ? this.state.details.address : "";
    let birthDate = this.state.details
      ? Utils.getDayMonthAndYearFromDate(new Date(this.state.details.birthDate))
      : "";
    let workshops = this.state.workshops ? this.state.workshops : [];
    let errorMessage = this.state.errorMessage;
    let isNew = this.state.isNew;

    return (
      <div className="container">
        <PageName pageName={Constants.customerDetailsPageName} />
        <Formik
          initialValues={{
            series,
            num,
            name,
            phone,
            address,
            birthDate,
            workshops,
            errorMessage,
            isNew
          }}
          validationSchema={Validation.customerSchema}
          onSubmit={this.onSubmit}
          enableReinitialize={true}
          render={({
            setFieldTouched,
            setFieldValue,
            handleSubmit,
            values
          }) => (
            <form onSubmit={handleSubmit}>
              {values.message && <ErrorMessageBlock message={values.message} />}
              <fieldset className="form-group">
                <label>Серия и номер паспорта</label>
                <Field
                  className="form-control"
                  type="number"
                  id="series"
                  max="9999"
                  value={values.isNew ? "" : values.series}
                />
                <ErrorMessage
                  name="series"
                  component="div"
                  className="alert alert-warning"
                />
                <Field
                  className="form-control"
                  type="number"
                  id="num"
                  max="999999"
                  value={values.isNew ? "" : values.num}
                />
                <ErrorMessage
                  name="num"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Имя</label>
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
                <label>Номер телефона</label>
                <Field
                  className="form-control"
                  type="number"
                  id="phone"
                  value={values.isNew ? "" : values.phone}
                />
                <ErrorMessage
                  name="phone"
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
                <label>Дата рождения</label>
                <Field
                  className="form-control"
                  type="date"
                  id="birthDate"
                  value={values.isNew ? "" : values.birthDate}
                />
                <ErrorMessage
                  name="birthDate"
                  component="div"
                  className="alert alert-warning"
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Автомастерские</label>
                <Field
                  name="workshopId"
                  render={({ field }) => (
                    <Select
                      {...field}
                      onChange={value => setFieldValue("workshopId", value)}
                      onBlur={() => setFieldTouched("workshopId", true)}
                      value={values.workshopId}
                    >
                      {workshops.map(workshop => (
                        <Select.Option value={workshop.workshopId}>
                          {workshop.name}
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

export default CustomerDetailsPage;
