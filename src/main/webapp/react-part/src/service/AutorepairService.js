import axios from 'axios'

const AUTOREPAIR_API_URL = 'http://localhost:8080'

class AutorepairService {
    getAllWorkshops() {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops`);
    }

    deleteWorkshop(workshopId) {
        return axios.delete(`${AUTOREPAIR_API_URL}/workshops/workshop/${workshopId}`)
    }

    getWorkshop(workshopId) {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops/workshop/${workshopId}`)
    }

    updateWorkshop(workshopId, workshop) {
        return axios.put(`${AUTOREPAIR_API_URL}/workshops/workshop/${workshopId}`, workshop)
    }

    createWorkshop(workshop) {
        return axios.post(`${AUTOREPAIR_API_URL}/workshops/workshop`, workshop)
    }

    getAllLevels() {
        return axios.get(`${AUTOREPAIR_API_URL}/levels`)
    }

    deleteLevel(levelId) {
        return axios.delete(`${AUTOREPAIR_API_URL}/levels/level/${levelId}`)
    }

    getLevel(levelId) {
        return axios.get(`${AUTOREPAIR_API_URL}/levels/level/${levelId}`)
    }

    updateLevel(levelId, level) {
        return axios.put(`${AUTOREPAIR_API_URL}/levels/level/${levelId}`, level)
    }

    createLevel(level) {
        return axios.post(`${AUTOREPAIR_API_URL}/levels/level`, level)
    }

    getCustomersByWorkshopId(workshopId) {
        return axios.get(`${AUTOREPAIR_API_URL}/workshop/${workshopId}/customers`)
    }

    deleteCustomer(customerId) {
        return axios.delete(`${AUTOREPAIR_API_URL}/customers/customer/${customerId}`)
    }

    getCustomer(customerId) {
        return axios.get(`${AUTOREPAIR_API_URL}/customers/customer/${customerId}`)
    }

    updateCustomer(customerId, customer) {
        return axios.put(`${AUTOREPAIR_API_URL}/customers/customer/${customerId}`, customer)
    }

    createCustomer(customer) {
        return axios.post(`${AUTOREPAIR_API_URL}/customers/customer`, customer)
    }
} 

export default new AutorepairService()