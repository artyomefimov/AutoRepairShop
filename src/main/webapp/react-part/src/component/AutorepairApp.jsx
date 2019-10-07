import React, { Component } from "react";
import WorkshopTable from "./workshop/WorkshopTable";
import WorkshopDetailsPage from "./workshop/WorkshopDetailsPage";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import LevelTable from "./level/LevelTable";
import LevelDetailsPage from "./level/LevelDetailsPage";
import CustomerTable from "./customer/CustomerTable";
import CustomerDetailsPage from "./customer/CustomerDetailsPage";
import MasterTable from "./master/MasterTable";
import MasterDetailsPage from "./master/MasterDetailsPage";
import CarTable from "./car/CarTable";
import CarDetailsPage from "./car/CarDetailsPage";

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
              render={props => <WorkshopTable {...props} />}
            />
            <Route
              exact
              path="/workshops"
              render={props => <WorkshopTable {...props} />}
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
            <Route
              exact
              path="/workshop/:workshopId/customers"
              render={props => <CustomerTable {...props} />}
            />
            <Route
              path="/customers/customer/:customerId"
              render={props => <CustomerDetailsPage {...props} />}
            />
            <Route
              exact
              path="/workshop/:parentId/masters"
              render={props => <MasterTable {...props} />}
            />
            <Route
              exact
              path="/level/:parentId/masters"
              render={props => <MasterTable {...props} />}
            />
            <Route
              path="/masters/master/:masterId"
              render={props => <MasterDetailsPage {...props} />}
            />
            <Route
              exact
              path="/master/:parentId/cars"
              render={props => <CarTable {...props} />}
            />
            <Route
              exact
              path="/customer/:parentId/cars"
              render={props => <CarTable {...props} />}
            />
            <Route
              path="/cars/car/:carId"
              render={props => <CarDetailsPage {...props} />}
            />
          </Switch>
        </div>
      </Router>
    );
  }
}

export default AutorepairApp;
