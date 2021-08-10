import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CustomerInformationService{

    getAllCustomerInformations() {
        return axios.get(API_URL + 'customerInformations', {headers: authHeader() });}

    
    saveCustomerInformation(customerInformation) {
        return axios.post(API_URL + 'customerInformations',{
            name: customerInformation.name,
            surname: customerInformation.surname,
            telephon: customerInformation.telephon,
            address: customerInformation.address,
            user: customerInformation.user

        }, {headers: authHeader() })
        .then((response) => {
          return response.data;
        })
        .catch((e) => {
          console.log(e);
        });
    }

    getCustomerByEmail(email){
      return axios.get(API_URL +'customerInformations'+ {email}, {headers: authHeader() })
       
    }
}
    

export default new CustomerInformationService();