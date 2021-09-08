import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/rest/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'all');
  }

  getCustomerBoard() {
    return axios.get(API_URL + 'customer', { headers: authHeader() });
  }

  getPartnerBoard() {
    return axios.get(API_URL + 'partner', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
  getAllUsers(){
    return axios.get(API_URL + 'users',{headers: authHeader()});
  }
  updateRole(user){
    return axios.put(API_URL + 'users'+ '/'+ user.id,{headers: authHeader()});
  }
  updatePassword(user){
    return axios.post(API_URL + 'users'+ '/'+'update',{
      id:user.id,
      oldPassword:user.oldPassword,
      newPassword:user.password
    },{headers: authHeader()});
  }
}

export default new UserService();