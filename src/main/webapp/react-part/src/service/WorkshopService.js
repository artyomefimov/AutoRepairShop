import axios from 'axios'
import * as Constants from '../Constants';

const AUTOREPAIR_API_URL = 'http://localhost:8080'

class AutorepairService {
    getAllWorkshops() {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops`);
    }

    deleteWorkshop(id) {
        return axios.delete(`/workshops/workshop/${id}`)
    }

    getWorkshop(id) {
        return axios.get(`${AUTOREPAIR_API_URL}/workshops/workshop/${id}`)
    }
}

export default new AutorepairService()