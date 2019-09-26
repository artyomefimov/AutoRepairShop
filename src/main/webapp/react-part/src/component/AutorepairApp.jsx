import React, { Component } from "react";
import AppName from "./AppName";
import ObjectsTable from "./ObjectsTable";
import ObjectDetailsPage from "./ObjectDetailsPage";
import * as Constants from "../Constants";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

class AutorepairApp extends Component {
  render() {
    return (
      <Router>
        <>
          <AppName />
          <Switch>
            <Route
              exact path="/"
              render={props => (
                <ObjectsTable
                  tableHeaders={Constants.workshopListTableHeaders}
                  objectType={Constants.workshopObjectType}
                  {...props}
                />
              )}
            />
            <Route
              path={Constants.WORKSHOP_LIST_URL}
              render={props => (
                <ObjectsTable
                  tableHeaders={Constants.workshopListTableHeaders}
                  objectType={Constants.workshopObjectType}
                  {...props}
                />
              )}
            />
            <Route
              path={`${Constants.WORKSHOP_DETAIL_URL}/:id`}
              //path={'/workshops/workshop/:id'}
              render={props => <ObjectDetailsPage {...props} />}
            />
          </Switch>
        </>
      </Router>
    );
  }
}

export default AutorepairApp;
