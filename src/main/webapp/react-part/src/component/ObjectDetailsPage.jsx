import React, { Component } from "react";
import PageName from "./PageName";
import * as Constants from "../Constants";

class ObjectDetailsPage extends Component {
  render() {
    return (
      <>
        <PageName pageName={Constants.workshopDetailPageName} />
        <div>
          <p>Object details page</p>
        </div>
      </>
    );
  }
}

export default ObjectDetailsPage;
