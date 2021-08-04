import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CustomerInformationService{

    getAllCustomerInformations() {
        return axios.get(API_URL + 'customerInformations', {headers: authHeader() })}
}

export default new CustomerInformationService();