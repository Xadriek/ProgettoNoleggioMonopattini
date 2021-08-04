import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class RentService{

    getAllRents() {
        return axios.get(API_URL + 'rents', {headers: authHeader() })}
}

export default new RentService();