import React, { Component } from "react";

class LevelRow extends Component {
  constructor(props) {
    super(props);

    this.deleteLevel = this.deleteLevel.bind(this);
    this.openMastersByLevelId = this.openMastersByLevelId.bind(this);
    this.openLevelDetails = this.openLevelDetails.bind(this);
  }

  deleteLevel(id) {
    this.props.onDeleteClicked(id);
  }

  openMastersByLevelId(id) {
    this.props.onOpenMastersClicked(id);
  }

  openLevelDetails(id) {
    this.props.onOpenLevelDetailsClicked(id);
  }

  render() {
    const object = this.props.object;
    let key = 0;

    return (
      <tr className="top-margin" key={object.levelId}>
        <td key={key++} className="main-td">
          {object.name}
        </td>
        <td>
          <button
            className="btn btn-info"
            onClick={() => this.openMastersByLevelId(object.levelId)}
          >
            Мастера
          </button>
        </td>
        <td>
          <button
            className="btn btn-success"
            onClick={() => this.openLevelDetails(object.levelId)}
          >
            Изменить
          </button>
        </td>
        <td>
          <button
            className="btn btn-warning"
            onClick={() => this.deleteLevel(object.levelId)}
          >
            Удалить
          </button>
        </td>
      </tr>
    );
  }
}

export default LevelRow;
