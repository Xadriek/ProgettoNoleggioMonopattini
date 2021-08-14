import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class SwapService{

    getAllSwaps() {
        return axios.get(API_URL + 'swaps', {headers: authHeader() })}

    updateSwap(swap){
          return axios.put(API_URL + 'swaps' + '/'+ swap.id ,{swap:swap} ,{headers: authHeader() })}

    saveSwap(/*mettere parametri*/){
        return axios.post(API_URL + 'swaps',{/*mettere parametri*/},{headers: authHeader() })
    }
          
}

export default new SwapService();