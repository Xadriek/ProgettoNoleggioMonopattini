import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class ScooterService{

    getAllScooters() {
        return axios.get(API_URL + 'scooters', {headers: authHeader() })}
}

export default new ScooterService();