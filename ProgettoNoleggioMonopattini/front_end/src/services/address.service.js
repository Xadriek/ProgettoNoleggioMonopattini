import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class AddressService{

    getAllAddresses() {
        return axios.get(API_URL + 'Adresses', {headers: authHeader() })}
}

export default new AddressService();