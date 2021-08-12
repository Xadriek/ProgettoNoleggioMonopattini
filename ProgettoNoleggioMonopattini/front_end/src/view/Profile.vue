<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}</strong> Profile
      </h3>
    </header>
    <p>
      <strong>Token:</strong>
      {{currentUser.accessToken.substring(0, 20)}} ... {{currentUser.accessToken.substr(currentUser.accessToken.length - 20)}}
    </p>
    <p>
    <strong>Id:</strong>
    {{currentUser.id}}
    </p>
    <p>
    <strong>Nome:</strong>
    {{userProfile.name}}
    </p>
    <p>
    <strong>Cognome:</strong>
    {{userProfile.surname}}
    </p>
    <p>
    <strong>Telefono:</strong>
    {{userProfile.telephon}}
    </p>
    <p>
    <strong>Stato Attivo/Disattivo</strong>
    {{userProfile.isActive}}
    </p>        
    <p>
    <strong>Monete nel Wallet</strong>
    {{userProfile.walletCoin}}
    </p>
    <p>
    <strong>ID Noleggio</strong>
    {{userProfile.rentId}}
    </p>    
    <p>
    <strong>Numero Transazioni</strong>
    {{userProfile.transationNumber}}
    </p>   


    <p>
      <strong>Email:</strong>
      {{currentUser.email}}
    </p>
    <strong>Authorities:</strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
  </div>
</template>

<script>
import customerInformationService from "../services/customerInformation.service"
import partnerInformationService from "../services/partnerInformation.service"
import UserProfile from '../model/userProfile';
export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data(){return{
      userProfile: new UserProfile()
  }
  },
  mounted() {
    if (!this.currentUser) {
      
      this.$router.push('/login');
    }
    if(this.currentUser.roles.includes('ROLE_CUSTOMER')){
  customerInformationService.getCustomerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.userProfile=response.data;}
    )
    
  }
  if(this.currentUser.roles.includes('ROLE_PARTNER')){
  partnerInformationService.getPartnerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.userProfile=response.data;}
    )
    
  }
  
}
}
</script>