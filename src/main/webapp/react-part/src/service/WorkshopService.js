import axios from 'axios'
import * as Constants from '../Constants';

const AUTOREPAIR_API_URL = 'http://localhost:8080'

class AutorepairService {
    getAllWorkshops() {
        return axios.get(`${AUTOREPAIR_API_URL}${Constants.WORKSHOP_LIST_URL}`);
    }

    deleteWorkshop(id) {
        return axios.delete(`${Constants.WORKSHOP_DETAIL_URL}/${id}`)
    }

    getWorkshop(id) {
        return axios.get(`${Constants.WORKSHOP_DETAIL_URL}/${id}`)
    }
}

export default new AutorepairService()