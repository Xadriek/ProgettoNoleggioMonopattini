
import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class RentService{

    getAllRents() {
        return axios.get(API_URL + 'rents', {headers: authHeader() })}

    saveRent(customerInformation,address,contract,currentUserEmail,currentUsername) {
            return axios.post(API_URL + 'rents',{
              name: customerInformation.name,
              surname: customerInformation.surname,
              telephon: customerInformation.telephon,
              street:address.street,
              cap:address.cap,
              numberStreet:address.numberStreet,
              municipality: address.municipality,
              city:address.city,
              country:address.country,
              plan:contract.plan,
              userEmail: currentUserEmail,
              username:currentUsername,
              
                
            }, {headers: authHeader() })
            ;
        }
    }

export default new RentService();