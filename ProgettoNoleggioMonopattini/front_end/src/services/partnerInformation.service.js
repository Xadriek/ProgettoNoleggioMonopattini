import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class PartnerInformationService{

    getAllPartnerInformations() {
        return axios.get(API_URL + 'partnerInformations', {headers: authHeader() })}

    getPartnerByEmail(email){
        return axios.get(API_URL +'partnerInformation'+ '/'+ email, {headers: authHeader() })
             
          }
    updatePartnerInformation(partnerInformation){
        return axios.put(API_URL + 'partnerInformations' + '/'+ partnerInformation.id ,{partnerInformation} ,{headers: authHeader() })}
      
    savePartner(partnerInformation,address,currentUserEmail,currentUsername,geocode) {
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
              username:currentUsername,
              latitude:geocode.lat,
              longitude:geocode.lng
                
            }, {headers: authHeader() })
            ;
        }
    getPartnerByPosition(position){
        return axios.get('http://localhost:8080/api/test/' + 'partnersPosition',{
            params:{
                lat:position.lat,
                lng:position.lng
            }
        ,header: authHeader()});
    }
}

export default new PartnerInformationService();