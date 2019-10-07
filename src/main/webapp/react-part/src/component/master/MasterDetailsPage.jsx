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

class MasterDetailsPage extends Component {
  constructor(props) {
    super(props);

    this.state = {
      errorMessage: "",
      masterId: this.props.match.params.masterId,
      details: null,
      workshops: null,
      levels: null,
      isNew: false
    };
    this.requestMasterDetails = this.requestMasterDetails.bind(this);
    this.requestAllWorkshops = this.requestAllWorkshops.bind(this);
    this.requestAllLevels = this.requestAllLevels.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    if (this.state.masterId !== "-1") {
      this.setState({ isNew: false });
      this.requestMasterDetails(this.state.masterId);
    }
    this.requestAllWorkshops();
    this.requestAllLevels();
  }

  requestAllWorkshops() {
    AutorepairService.getAllWorkshops()
      .then(response => {
        this.setState({ workshops: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  requestAllLevels() {
    AutorepairService.getAllLevels()
      .then(response => {
        this.setState({ levels: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  requestMasterDetails(id) {
    AutorepairService.getMaster(id)
      .then(response => {
        this.setState({ details: response.data });
      })
      .catch(e => this.setState({ errorMessage: e.message }));
  }

  goBack() {
    this.props.history.goBack();
  }

  onSubmit(values) {
    let master = {
      masterId: this.state.masterId,
      masterPassportNum: parseInt(values.series + "" + values.num),
      name: values.name,
      phone: values.phone,
      workshopId: values.workshopId
        ? values.workshopId
        : this.state.details
        ? this.state.details.workshop.workshopId
        : this.state.workshops[0].workshopId,
      levelId: values.levelId
        ? values.levelId
        : this.state.details
        ? this.state.details.level.levelId
        : this.state.levels[0].levelId
    };

    if (master.masterId === "-1") {
      AutorepairService.createMaster(master)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    } else {
      AutorepairService.updateMaster(master.masterId, master)
        .then(() => {
          this.goBack();
        })
        .catch(e => this.setState({ errorMessage: e.message }));
    }
  }

  render() {
    let { series, num } = this.state.details
      ? Utils.splitPassportNumOnSeriesAndNum(
          this.state.details.masterPassportNum
        )
      : { series: "", num: "" };
    let name = this.state.details ? this.state.details.name : "";
    let phone = this.state.details ? this.state.details.phone : "";
    let workshops = this.state.workshops ? this.state.workshops : [];
    let levels = this.state.levels ? this.state.levels : [];
    let workshopId = this.state.details
      ? this.state.details.workshop.workshopId
      : "";
    let levelId = this.state.details ? this.state.details.level.levelId : "";
    let errorMessage = this.state.errorMessage;
    let isNew = this.state.isNew;

    return (
      <div className="container">
        <PageName pageName={Constants.masterDetailsPageName} />
        <Formik
          initialValues={{
            series,
            num,
            name,
            phone,
            workshops,
            levels,
            workshopId,
            levelId,
            errorMessage,
            isNew
          }}
          validationSchema={Validation.masterSchema}
          onSubmit={this.onSubmit}
          enableReinitialize={true}
          render={({ setFieldValue, handleSubmit, values }) => (
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
                  style={{ marginTop: "10px" }}
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
                <label>Автомастерская</label>
                <Field
                  name="workshopId"
                  render={({ field }) => (
                    <Select
                      {...field}
                      onChange={value => setFieldValue("workshopId", value)}
                      value={values.workshopId}
                    >
                      {workshops.map(workshop => (
                        <Select.Option
                          value={workshop.workshopId}
                          selected={values.workshopId === workshop.workshopId}
                        >
                          {workshop.name}
                        </Select.Option>
                      ))}
                    </Select>
                  )}
                />
              </fieldset>
              <fieldset className="form-group">
                <label>Квалификация</label>
                <Field
                  name="levelId"
                  render={({ field }) => (
                    <Select
                      {...field}
                      onChange={value => setFieldValue("levelId", value)}
                      value={values.levelId}
                    >
                      {levels.map(level => (
                        <Select.Option
                          value={level.levelId}
                          selected={values.levelId === level.levelId}
                        >
                          {level.name}
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

export default MasterDetailsPage;
