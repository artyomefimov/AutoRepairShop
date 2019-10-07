import React, { Component } from "react";
import AutorepairService from "../../service/AutorepairService";
import PageName from "../PageName";
import SuccessMessage from "../message/SuccessMessage";
import * as Constants from "../../Constants";
import LevelRow from "./LevelRow";
import BackButton from "../BackButton";

class LevelTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      objects: [],
      message: null
    };
    this.requestLevels = this.requestLevels.bind(this);
    this.deleteLevel = this.deleteLevel.bind(this);
    this.openLevelDetails = this.openLevelDetails.bind(this);
    this.openMastersByLevelId = this.openMastersByLevelId.bind(this);
    this.createLevel = this.createLevel.bind(this);
    this.goBack = this.goBack.bind(this);
  }

  componentDidMount() {
    this.requestLevels();
  }

  requestLevels() {
    AutorepairService.getAllLevels()
      .then(response => {
        this.setState({ objects: response.data });
      })
      .catch(e => this.setState({ message: e.message }));
  }

  deleteLevel(id) {
    if (window.confirm("Вы действительно хотите удалить эту квалификацию?"))
      AutorepairService.deleteLevel(id)
        .then(() => {
          this.setState({ message: "Квалификация успешно удалена!" });
          this.requestLevels();
        })
        .catch(e => this.setState({ message: e.message }));
  }

  openLevelDetails(id) {
    this.props.history.push(`/levels/level/${id}`);
  }

  openMastersByLevelId(id, name) {
    this.props.history.push({
      pathname: `/level/${id}/masters`,
      search: `?level=${name}`
    });
  }

  createLevel() {
    this.props.history.push(`/levels/level/-1`);
  }

  goBack() {
    this.props.history.push(`/workshops`);
  }

  render() {
    let key = 0;

    return (
      <>
        <PageName pageName={Constants.levelListPageName} />
        <div className="container">
          {this.state.message && (
            <SuccessMessage message={this.state.message} />
          )}
          <div className="container">
            <table className="table">
              <thead>
                <tr>
                  {Constants.levelListTableHeaders.map(tableHeader => (
                    <th className="main-th" key={key++}>
                      {tableHeader}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {this.state.objects.map(object => (
                  <LevelRow
                    key={key++}
                    object={object}
                    onDeleteClicked={this.deleteLevel}
                    onOpenLevelDetailsClicked={this.openLevelDetails}
                    onOpenMastersClicked={this.openMastersByLevelId}
                  />
                ))}
              </tbody>
            </table>
            <button
              className="btn btn-success"
              onClick={() => this.createLevel()}
            >
              Добавить
            </button>
          </div>
          <BackButton goBackAction={this.goBack} />
        </div>
      </>
    );
  }
}

export default LevelTable;
