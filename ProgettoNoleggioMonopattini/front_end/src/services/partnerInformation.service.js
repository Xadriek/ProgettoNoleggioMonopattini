import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class PartnerInformationService{

    getAllPartnerInformations() {
        return axios.get(API_URL + 'partnerInformations', {headers: authHeader() })}
}

export default new PartnerInformationService();