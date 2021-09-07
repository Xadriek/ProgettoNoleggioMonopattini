import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class WalletService{

    getAllWallets() {
        return axios.get(API_URL + 'wallets', {headers: authHeader() })}

    updateWallet(wallet){
        return axios.put(API_URL + 'wallets' + '/'+ wallet.id ,{
            id:wallet.id,
            coin:wallet.coin
        } ,{headers: authHeader() })}
}

export default new WalletService();