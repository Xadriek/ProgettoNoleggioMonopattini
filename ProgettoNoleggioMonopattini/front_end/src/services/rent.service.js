
import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class RentService{

    getAllRents() {
        return axios.get(API_URL + 'rents', {headers: authHeader() })}

    saveRent(customerInformation,address,contract) {
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
              user: customerInformation.user,
              
                
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