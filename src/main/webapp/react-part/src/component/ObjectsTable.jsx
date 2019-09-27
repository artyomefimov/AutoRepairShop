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
  }

  componentDidMount() {
    this.requestObjects();
  }

  requestObjects() {
    WorkshopService.getAllWorkshops().then(response => {
      this.setState({ objects: response.data });
    });
  }

  deleteObject(id) {
    // WorkshopService.deleteWorkshop(id).then(response => {
    //   this.setState({message: 'Автомастерская успешно удалена!'})
    //   this.requestObjects()
    // })
   // alert(`delete object ${id}`);
    this.props.history.push(`-1`);
  }

  openObjectDetails(id) {
    alert(`openObjectDetails ${id}`)
    this.props.history.push(`/workshops/workshop/${id}`);
  }

  openObject(id) {
    alert(`openObject ${id}`);
  }

  render() {
    let key = 0;
    return (
      <>
        <PageName pageName={Constants.workshopListPageName} />
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
          </div>
        </div>
      </>
    );
  }
}

export default ObjectsTable;
