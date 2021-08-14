import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CoinTransationService{

    getAllCoinTransations() {
        return axios.get(API_URL + 'coinTransations', {headers: authHeader() })}

    updateCoinTransations(coinTransation){
        return axios.put(API_URL + 'coinTransations' + '/'+ coinTransation.id ,{coinTransation:coinTransation} ,{headers: authHeader() })}
    
    saveCoinTransaction(/*mettere parametri*/){
        return axios.post(API_URL + 'coinTransactions',{/*mettere parametri*/},{headers: authHeader() })
        }     
}


export default new CoinTransationService();