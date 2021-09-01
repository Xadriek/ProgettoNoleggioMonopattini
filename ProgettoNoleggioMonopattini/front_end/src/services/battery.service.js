import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class BatteryService{

    getAllBatteries() {
        return axios.get(API_URL + 'batteries', {headers: authHeader() })}

    updatebattery(battery){
        return axios.put(API_URL + 'batteries' + '/'+ battery.id ,{battery:battery} ,{headers: authHeader() })}
    
    getBatteryByHub(batteryId){
        return axios.get(API_URL+ 'batteries'+ '/'+ 'hub'+ '/'+ batteryId, {headers: authHeader() })
    }
}

export default new BatteryService();