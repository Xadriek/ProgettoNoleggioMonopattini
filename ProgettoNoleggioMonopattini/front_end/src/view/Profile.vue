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
<div v-if="this.currentUser.roles.includes('ROLE_CUSTOMER')">
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
    <strong>Monete nel Wallet</strong>
    {{userProfile.customerWallet.coin}}
    </p>
    <p>
    <strong>ID Noleggio</strong>
    {{userProfile.rent.id}}
    </p>
        <p>
    <strong>Username</strong>
    {{userProfile.username}}
    </p>
</div> 

<div v-if="this.currentUser.roles.includes('ROLE_PARTNER')">
    <p>
    <strong>Nome:</strong>
    {{userProfile.name}}
    </p>
    <p>
    <strong>P.Iva:</strong>
    {{userProfile.pIva}}
    </p>
    <p>
    <strong>Telefono:</strong>
    {{userProfile.telephon}}
    </p>        
    <p>
    <strong>Monete nel Wallet</strong>
    {{userProfile.partnerWallet.coin}}
    </p>
    <p>
    <strong>Username</strong>
    {{userProfile.username}}
    </p>
</div> 
    <p>
    <strong>Numero Transazioni</strong>
    {{numCoinTransation}}
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
import coinTransationService from '../services/coinTransation.service';
import customerInformationService from "../services/customerInformation.service"
import partnerInformationService from "../services/partnerInformation.service"

export default {
  name: 'Profile',
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data(){
    return{
      userProfile:{},
      numCoinTransation:0,
      coinTransations:[]
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
      this.userProfile=response.data;
      this.getCoinTransationNumberCustomer();
      }
    )
    
  }
  if(this.currentUser.roles.includes('ROLE_PARTNER')){
  partnerInformationService.getPartnerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.userProfile=response.data;
      this.getCoinTransationNumberPartner();}
    )
    
  }else{
    this.getCoinTransationNumber();
  }
  
},
methods:{
  getCoinTransationNumberCustomer(){
    coinTransationService.getCoinTransationByCustomer(this.userProfile.id).then(
      response=>{
        console.log(response.data);
        this.coinTransations=response.data;
        this.numCoinTransation=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true).length;
      }
    )
  },
  getCoinTransationNumberPartner(){
    coinTransationService.getCoinTransationByPartner(this.userProfile.id).then(
      response=>{
        console.log(response.data);
        this.coinTransations=response.data;
        this.numCoinTransation=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true).length;
      }
    )
  },
  getCoinTransationNumber(){
    coinTransationService.getAllCoinTransations().then(
      response=>{
        console.log(response.data);
        this.coinTransations=response.data;
        this.numCoinTransation=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true).length;
      }
    )
  }
  
}

}
</script>