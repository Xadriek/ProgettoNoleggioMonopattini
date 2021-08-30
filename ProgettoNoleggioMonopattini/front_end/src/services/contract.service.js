import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class ContractService{

    getAllContracts() {
        return axios.get(API_URL + 'contracts', {headers: authHeader() })}

    saveContract(contract) {
            return axios.post(API_URL + 'contracts',{
            plan:contract.plan    
            }, {headers: authHeader() })
            .then((response) => {
              return response.data;
            })
            .catch((e) => {
              console.log(e);
            });
        }
}

export default new ContractService();