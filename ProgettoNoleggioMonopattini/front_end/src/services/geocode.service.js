import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class GeocodeService{

    getAllGeocodes() {
        return axios.get(API_URL + 'geocodes', {headers: authHeader() })}
    updatePosition(geocode){
        return axios.put(API_URL + 'geocodes' + '/'+ geocode.id ,{geocode} ,{headers: authHeader() })}
}

export default new GeocodeService();