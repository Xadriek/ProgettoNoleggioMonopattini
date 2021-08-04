import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class HubService{

    getAllHubs() {
        return axios.get(API_URL + 'hubs', {headers: authHeader() })}
}

export default new HubService();