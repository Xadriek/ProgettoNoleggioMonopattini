import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class HubService{

    getAllHubs() {
        return axios.get(API_URL + 'hubs', {headers: authHeader() })}
    
    saveHub(partnerInformation,address,currentUserEmail,currentUsername) {
            return axios.post(API_URL + 'partnerInformations',{
              name: partnerInformation.name,
              pIva: partnerInformation.pIva,
              telephon: partnerInformation.telephon,
              street:address.street,
              cap:address.cap,
              numberStreet:address.numberStreet,
              municipality: address.municipality,
              city:address.city,
              country:address.country,
              userEmail: currentUserEmail,
              username:currentUsername
              
                
            }, {headers: authHeader() })
            ;
        }
}

export default new HubService();