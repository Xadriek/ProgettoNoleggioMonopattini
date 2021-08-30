import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class AddressService{

    getAllAddresses() {
        return axios.get(API_URL + 'Adresses', {headers: authHeader() })}

    saveAddress(address) {
            return axios.post(API_URL + 'addresses',{
                street: address.street,
                cap: address.cap,
                numberStreet: address.numberStreet,
                municipality: address.municipality,
                city: address.city,
                country: address.country
            }, {headers: authHeader() })
            .then((response) => {
              return response.data;
            })
            .catch((e) => {
              console.log(e);
            });
        }
}

export default new AddressService();