import axios from "axios";
import * as Constants from "../Constants";

class AutorepairService {
  getAllWorkshops() {
    return axios.get(`${Constants.AUTOREPAIR_API_URL}/api/workshops`);
  }

  deleteWorkshop(workshopId) {
    return axios.delete(
      `${Constants.AUTOREPAIR_API_URL}/api/workshops/workshop/${workshopId}`
    );
  }

  getWorkshop(workshopId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/workshops/workshop/${workshopId}`
    );
  }

  updateWorkshop(workshopId, workshop) {
    return axios.put(
      `${Constants.AUTOREPAIR_API_URL}/api/workshops/workshop/${workshopId}`,
      workshop
    );
  }

  createWorkshop(workshop) {
    return axios.post(
      `${Constants.AUTOREPAIR_API_URL}/api/workshops/workshop`,
      workshop
    );
  }

  getAllLevels() {
    return axios.get(`${Constants.AUTOREPAIR_API_URL}/api/levels`);
  }

  deleteLevel(levelId) {
    return axios.delete(
      `${Constants.AUTOREPAIR_API_URL}/api/levels/level/${levelId}`
    );
  }

  getLevel(levelId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/levels/level/${levelId}`
    );
  }

  updateLevel(levelId, level) {
    return axios.put(
      `${Constants.AUTOREPAIR_API_URL}/api/levels/level/${levelId}`,
      level
    );
  }

  createLevel(level) {
    return axios.post(
      `${Constants.AUTOREPAIR_API_URL}/api/levels/level`,
      level
    );
  }

  getCustomersByWorkshopId(workshopId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/workshop/${workshopId}/customers`
    );
  }

  deleteCustomer(customerId) {
    return axios.delete(
      `${Constants.AUTOREPAIR_API_URL}/api/customers/customer/${customerId}`
    );
  }

  getCustomer(customerId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/customers/customer/${customerId}`
    );
  }

  updateCustomer(customerId, customer) {
    return axios.put(
      `${Constants.AUTOREPAIR_API_URL}/api/customers/customer/${customerId}`,
      customer
    );
  }

  createCustomer(customer) {
    return axios.post(
      `${Constants.AUTOREPAIR_API_URL}/api/customers/customer`,
      customer
    );
  }

  getMastersByWorkshopId(workshopId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/workshop/${workshopId}/masters`
    );
  }

  getMastersByLevelId(levelId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/level/${levelId}/masters`
    );
  }

  deleteMaster(masterId) {
    return axios.delete(
      `${Constants.AUTOREPAIR_API_URL}/api/masters/master/${masterId}`
    );
  }

  getMaster(masterId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/masters/master/${masterId}`
    );
  }

  updateMaster(masterId, master) {
    return axios.put(
      `${Constants.AUTOREPAIR_API_URL}/api/masters/master/${masterId}`,
      master
    );
  }

  createMaster(master) {
    return axios.post(
      `${Constants.AUTOREPAIR_API_URL}/api/masters/master`,
      master
    );
  }

  getCarsByMasterId(masterId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/master/${masterId}/cars`
    );
  }

  getCarsByCustomerId(customerId) {
    return axios.get(
      `${Constants.AUTOREPAIR_API_URL}/api/customer/${customerId}/cars`
    );
  }

  deleteCar(carId) {
    return axios.delete(
      `${Constants.AUTOREPAIR_API_URL}/api/cars/car/${carId}`
    );
  }

  getCar(carId) {
    return axios.get(`${Constants.AUTOREPAIR_API_URL}/api/cars/car/${carId}`);
  }

  updateCar(carId, car) {
    return axios.put(
      `${Constants.AUTOREPAIR_API_URL}/api/cars/car/${carId}`,
      car
    );
  }

  createCar(car) {
    return axios.post(`${Constants.AUTOREPAIR_API_URL}/api/cars/car`, car);
  }
}

export default new AutorepairService();
