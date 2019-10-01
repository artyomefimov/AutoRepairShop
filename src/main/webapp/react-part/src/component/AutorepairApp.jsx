import React, { Component } from "react";
import ObjectsTable from "./workshop/WorkshopTable";
import WorkshopDetailsPage from "./workshop/WorkshopDetailsPage";
import * as Constants from "../Constants";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LevelTable from "./level/LevelTable";
import LevelDetailsPage from "./level/LevelDetailsPage";

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
              render={props => <ObjectsTable {...props} />}
            />
            <Route
              exact
              path="/workshops"
              render={props => <ObjectsTable {...props} />}
            />
            <Route
              path="/workshops/workshop/:id"
              render={props => <WorkshopDetailsPage {...props} />}
            />
            <Route
              exact
              path="/levels"
              render={props => <LevelTable {...props} />}
            />
            <Route
              path="/levels/level/:id"
              render={props => <LevelDetailsPage {...props} />}
            />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default AutorepairApp;
