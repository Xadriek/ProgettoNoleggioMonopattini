<template>
  <div class="container">
    <header class="jumbotron">
      <h3>
        <strong>Profilo di Autenticazione: {{currentUser.username}}</strong> 
      </h3>
    </header>



<div>
  <b-card-group deck>
    
     <b-card >
      
      
    <div v-if="this.currentUser.roles.includes('ROLE_CUSTOMER') && userProfile.username!=null">

    <b-card-text><strong>Nome:</strong>
    {{userProfile.name}}</b-card-text>
    <b-card-text><strong>Cognome:</strong>
    {{userProfile.surname}}</b-card-text>
    <b-card-text><strong>Telefono:</strong>
    {{userProfile.telephon}}</b-card-text>
    <b-card-text><strong>Monete nel Wallet:</strong>
    {{userProfile.customerWallet.coin}}</b-card-text>
    <b-card-text><strong>ID Noleggio:</strong>
    {{userProfile.rent.id}}</b-card-text>
    <b-card-text><strong>Username:</strong>
    {{userProfile.username}}</b-card-text>
    
</div> 

<div v-if="this.currentUser.roles.includes('ROLE_PARTNER')">
    <b-card-text><strong>Nome:</strong>
    {{userProfile.name}}</b-card-text>
   <b-card-text><strong>P.Iva:</strong>
   {{userProfile.pIva}}</b-card-text>
   <b-card-text><strong>Telefono:</strong>
   {{userProfile.telephon}}</b-card-text>
   <b-card-text><strong>Monete nel Wallet:</strong>
   {{userProfile.partnerWallet.coin}}</b-card-text>
   <b-card-text><strong>Username:</strong>
   {{userProfile.username}}</b-card-text>
 
</div> 
<p></p>
   <b-card-text><strong>Numero Transazioni: </strong>
   {{numCoinTransation}}</b-card-text>
  <b-card-text><strong>Email: </strong>
  {{currentUser.email}}</b-card-text>
  <b-card-text><strong>Ruolo Operativo: </strong>
    <ul>
      <li v-for="(role,index) in currentUser.roles" :key="index">{{role}}</li>
    </ul>
    
</b-card-text>

<div>
      <b-button v-b-toggle.collapse-2 class="m-1">Modifica Password</b-button>
      <b-collapse id="collapse-2">
    <b-card>
      <form name="form" @submit.prevent="handleUpdate">
        <div v-if="!successful">
      <div class="form-group">
            <label for="password">Vecchia Password:</label>
            <input
              v-model="user.oldPassword"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div>
          </div>
       <div class="form-group">
            <label for="password">Nuova Password:</label>
            <input
              v-model="user.password"
              v-validate="'required|min:6|max:40'"
              type="password"
              class="form-control"
              name="password"
            />
            <div
              v-if="submitted && errors.has('password')"
              class="alert-danger"
            >{{errors.first('password')}}</div>
          </div>
      
          <div class="form-group">
            <button class="btn btn-primary btn-block">Conferma</button>
          </div>
           </div>
      </form>
    </b-card>
      <div
        v-if="message"
        class="alert"
        :class="successful ? 'alert-success' : 'alert-danger'"
      >{{message}}</div>
  </b-collapse>
    </div>

     
      <template #footer>
        
      </template>
    </b-card>
  </b-card-group>
</div>

    



    </div>
</template>

<script>
import coinTransationService from "../services/coinTransation.service";
import customerInformationService from "../services/customerInformation.service";
import partnerInformationService from "../services/partnerInformation.service";
import User from "../model/user";
import userService from '../services/user.service';

export default {
  name: "Profile",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  data() {
    return {
      userProfile: {},
      user: new User("", "", "",""),
      numCoinTransation: 0,
      oldPassword:'',
      coinTransations: [],
      submitted: false,
      successful: false,
      message: "",
    };
  },
  mounted() {
    if (this.currentUser) {
      this.user.id=this.currentUser.id;
      this.user.username = this.currentUser.username;
      this.user.email = this.currentUser.email;
      console.log(this.user);
    }
    if (!this.currentUser) {
      this.$router.push("/login");
    }
    if (this.currentUser.roles.includes("ROLE_CUSTOMER")) {
      customerInformationService
        .getCustomerByEmail(this.currentUser.email)
        .then((response) => {
          console.log(response.data);
          this.userProfile = response.data;
          this.getCoinTransationNumberCustomer();
        });
    }
    if (this.currentUser.roles.includes("ROLE_PARTNER")) {
      partnerInformationService
        .getPartnerByEmail(this.currentUser.email)
        .then((response) => {
          console.log(response.data);
          this.userProfile = response.data;
          this.getCoinTransationNumberPartner();
        });
    } 
    if (this.currentUser.roles.includes("ROLE_ADMIN"))  {
      this.getCoinTransationNumber();
    }
  },
  methods: {
    getCoinTransationNumberCustomer() {
      coinTransationService
        .getCoinTransationByCustomer(this.userProfile.id)
        .then((response) => {
          console.log(response.data);
          this.coinTransations = response.data;
          this.numCoinTransation = this.coinTransations.filter(
            (coinTransation) => coinTransation.isComplete == true
          ).length;
        });
    },
    getCoinTransationNumberPartner() {
      coinTransationService
        .getCoinTransationByPartner(this.userProfile.id)
        .then((response) => {
          console.log(response.data);
          this.coinTransations = response.data;
          this.numCoinTransation = this.coinTransations.filter(
            (coinTransation) => coinTransation.isComplete == true
          ).length;
        });
    },
    getCoinTransationNumber() {
      coinTransationService.getAllCoinTransations().then((response) => {
        console.log(response.data);
        this.coinTransations = response.data;
        this.numCoinTransation = this.coinTransations.filter(
          (coinTransation) => coinTransation.isComplete == true
        ).length;
      });
    },
    handleUpdate() {
      this.message = "";
      this.submitted = true;
      this.$validator.validate().then((isValid) => {
        if (isValid) {
          console.log(this.user);
          userService.updatePassword(this.user).then(
            response=>{
              console.log(response.statusText);
            }
          )
        }
      });
    },
  },
};
</script>