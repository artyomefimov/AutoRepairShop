import axios from 'axios'

const AUTOREPAIR_API_URL = 'http://localhost:8080'

class AutorepairService {
    getAllWorkshops() {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops`);
    }

    deleteWorkshop(id) {
        return axios.delete(`${AUTOREPAIR_API_URL}/workshops/workshop/${id}`)
    }

    getWorkshop(id) {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops/workshop/${id}`)
    }

    updateWorkshop(id, workshop) {
        return axios.put(`${AUTOREPAIR_API_URL}/workshops/workshop/${id}`, workshop)
    }

    createWorkshop(workshop) {
        return axios.post(`${AUTOREPAIR_API_URL}/workshops/workshop`, workshop)
    }

    getAllLevels() {
        return axios.get(`${AUTOREPAIR_API_URL}/levels`)
    }

    deleteLevel(id) {
        return axios.delete(`${AUTOREPAIR_API_URL}/levels/level/${id}`)
    }

    getLevel(id) {
        return axios.get(`${AUTOREPAIR_API_URL}/levels/level/${id}`)
    }

    updateLevel(id, level) {
        return axios.put(`${AUTOREPAIR_API_URL}/levels/level/${id}`, level)
    }

    createLevel(level) {
        return axios.post(`${AUTOREPAIR_API_URL}/levels/level`, level)
    }
} 

export default new AutorepairService()