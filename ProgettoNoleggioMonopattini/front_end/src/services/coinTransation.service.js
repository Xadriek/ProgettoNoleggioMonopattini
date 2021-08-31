import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CoinTransationService{

    getAllCoinTransations() {
        return axios.get(API_URL + 'coinTransations', {headers: authHeader() })}

    updateCoinTransations(coinTransation){
        return axios.put(API_URL + 'coinTransations' + '/'+ coinTransation.id ,{coinTransation:coinTransation} ,{headers: authHeader() })}
    
    saveCoinTransaction(requestBody){
        return axios.post(API_URL + 'coinTransations',{
            idPartner:requestBody.partnerId,
            idCustomer:requestBody.customerId,
            coin:requestBody.coin
            
        },{headers: authHeader() })
        } 
    getCoinTransationByPartner(partner){
        return axios.get(API_URL + 'coinTransations'+ '/'+ 'toPartner',{
        params:{
            partnerID:partner.id
     }, header: authHeader()});
    }    
}


export default new CoinTransationService();