import axios from 'axios'
import AUTOREPAIR_API_URL from '../UrlConstants'

const ALL_WORKSHOPS_URL = '/workshops'
const WORKSHOP_URL = '/workshop'

class WorkshopService {
    getAllWorkshops() {
        return axios.get(`http://localhost:8080${ALL_WORKSHOPS_URL}`);
    }
}

export default new WorkshopService()