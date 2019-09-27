import React, { Component } from "react";
import ObjectsTable from "./ObjectsTable";
import WorkshopDetailsPage from "./details/WorkshopDetailsPage";
import * as Constants from "../Constants";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";

class AutorepairApp extends Component {
  render() {
    return (
      <Router>
          <div className="text-center">
            <h1>Автомастерская</h1>
            <Switch>
              <Route
                exact
                path="/"
                render={props => (
                  <ObjectsTable
                    pageName={Constants.workshopListPageName}
                    tableHeaders={Constants.workshopListTableHeaders}
                    objectType={Constants.workshopObjectType}
                    {...props}
                  />
                )}
              />
              <Route
                exact
                path="/workshops"
                render={props => (
                  <ObjectsTable
                    tableHeaders={Constants.workshopListTableHeaders}
                    objectType={Constants.workshopObjectType}
                    {...props}
                  />
                )}
              />
              <Route
                path="/workshops/workshop/:id"
                render={props => <WorkshopDetailsPage {...props} />}
              />
            </Switch>
          </div>
      </Router>
    );
  }
}

export default AutorepairApp;
