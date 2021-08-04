import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CoinTransationService{

    getAllCoinTransations() {
        return axios.get(API_URL + 'coinTransations', {headers: authHeader() })}
}

export default new CoinTransationService();