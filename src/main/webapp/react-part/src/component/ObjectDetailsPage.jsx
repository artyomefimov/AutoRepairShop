import React, { Component } from "react";
import PageName from "./PageName";
import * as Constants from "../Constants";
import WorkshopService from "../service/WorkshopService";

class ObjectDetailsPage extends Component {
  constructor(props) {
    super(props)
    
    this.state = {
      message: null,
      id: this.props.match.params.id,
      details: []
    }
    this.requestObjectsDetails = this.requestObjectsDetails.bind(this)
  }

  componentDidMount() {
    this.requestObjectsDetails(this.state.id)
  }

  requestObjectsDetails(id) {
    WorkshopService.getWorkshop(id).then(response => {
      this.setState({details: response.data})
    })
  }

  render() {
    return (
      <>
        <PageName pageName={Constants.workshopDetailPageName} />
        <div>
          <p>Object details page for object: {this.state.id}</p>
        </div>
      </>
    );
  }
}

export default ObjectDetailsPage;
