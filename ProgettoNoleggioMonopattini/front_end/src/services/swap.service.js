import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class SwapService{

    getAllSwaps() {
        return axios.get(API_URL + 'swaps', {headers: authHeader() })}
}

export default new SwapService();