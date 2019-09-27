import React, { Component } from "react";
import ObjectRow from "./ObjectRow";
import WorkshopService from "../service/WorkshopService";
import PageName from "./PageName";
import * as Constants from "../Constants";

class ObjectsTable extends Component {
  constructor(props) {
    super(props);

    this.state = {
      objects: [],
      message: null
    };
    this.requestObjects = this.requestObjects.bind(this);
    this.deleteObject = this.deleteObject.bind(this);
    this.openObjectDetails = this.openObjectDetails.bind(this);
    this.openObject = this.openObject.bind(this);
    this.createObject = this.createObject.bind(this);
  }

  componentDidMount() {
    this.requestObjects();
  }

  requestObjects() {
    WorkshopService.getAllWorkshops().then(response => {
      this.setState({ objects: response.data });
    }).catch(e => console.log(e));;
  }

  deleteObject(id) {
    WorkshopService.deleteWorkshop(id).then(() => {
      this.setState({message: 'Автомастерская успешно удалена!'})
      this.requestObjects()
    }).catch(e => console.log(e));
  }

  openObjectDetails(id) {
    this.props.history.push(`/workshops/workshop/${id}`);
  }

  openObject(id) {
    alert(`openObject ${id}`);
  }

  createObject() {
    this.props.history.push(`/workshops/workshop/-1`);
  }

  render() {
    let key = 0;
    return (
      <>
        <PageName pageName={this.props.pageName} />
        <div className="container">
          {this.state.message && (
            <div className="alert alert-success">{this.state.message}</div>
          )}
          <div className="container">
            <table className="table">
              <thead>
                <tr>
                  {this.props.tableHeaders.map(tableHeader => (
                    <th className="main-th" key={key++}>
                      {tableHeader}
                    </th>
                  ))}
                </tr>
              </thead>
              <tbody>
                {this.state.objects.map(object => (
                  <ObjectRow
                    key={key++}
                    object={object}
                    objectType={this.props.objectType}
                    onDeleteClicked={this.deleteObject}
                    onOpenObjectDetailsClicked={this.openObjectDetails}
                    onOpenObjectClicked={this.openObject}
                  />
                ))}
              </tbody>
            </table>
            <button
                className="btn btn-success"
                onClick={() => this.createObject()}
              >
                Добавить
              </button>
          </div>
        </div>
      </>
    );
  }
}

export default ObjectsTable;
