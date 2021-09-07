import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class CoinTransationService{

    getAllCoinTransations() {
        return axios.get(API_URL + 'coinTransations', {headers: authHeader() })}

    updateCoinTransations(coinTransation){
        return axios.put(API_URL + 'coinTransations' + '/'+ coinTransation.id, {headers: authHeader() })}
    
    saveCoinTransaction(requestBody){
        return axios.post(API_URL + 'coinTransations',{
            idPartner:requestBody.partnerId,
            idCustomer:requestBody.customerId,
            coin:requestBody.coin
            
        },{headers: authHeader() })
        } 
    getCoinTransationByPartner(partnerId){
        return axios.get(API_URL + 'coinTransations'+'/'+ 'partner'+ '/'+ partnerId,{
         header: authHeader()});
    }
    getCoinTransationByCustomer(customerId){
        return axios.get(API_URL + 'coinTransations'+'/'+ 'customer'+ '/'+ customerId,{
         header: authHeader()});
    }
    completeSwap(coinTransation){
        return axios.put(API_URL + 'coinTransations' + '/'+ coinTransation.id ,{coinTransation:coinTransation} ,{headers: authHeader() })} 
    
        deleteCoinTransation(coinTransation){
            return axios.delete(API_URL + 'coinTransations' + '/'+ coinTransation.id, {headers: authHeader() })
        }
        
}


export default new CoinTransationService();