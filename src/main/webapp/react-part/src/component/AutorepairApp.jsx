import React, { Component } from "react";
import AppName from "./AppName";
import PageName from "./PageName";
import ObjectsTable from "./ObjectsTable";

const tableHeaders = ['INN', 'Name', 'Address', 'Open hours', 'Close hours', 'Owner name']
class AutorepairApp extends Component {
  render() {
    return (
      <>
        <AppName />
        <PageName pageName = 'Workshops'/>
        <ObjectsTable tableHeaders = {tableHeaders} objectType = 'workshop'/>
      </>
    );
  }
}

export default AutorepairApp;
