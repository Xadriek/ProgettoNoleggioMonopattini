import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class RentService{

    getAllRents() {
        return axios.get(API_URL + 'rents', {headers: authHeader() })}

    saveRent(rent) {
            return axios.post(API_URL + 'rents',{
            customer: rent.customer,
            contract: rent.contract    
            }, {headers: authHeader() })
            .then((response) => {
              return response.data;
            })
            .catch((e) => {
              console.log(e);
            });
        }
    }

export default new RentService();