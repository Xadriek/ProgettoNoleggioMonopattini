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
<div v-if="this.currentUser.roles.includes('ROLE_CUSTOMER') && userProfile.username!=null">
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
    <div>
      <b-button v-b-toggle.collapse-2 class="m-1">Modifica Password</b-button>
      <b-collapse id="collapse-2">
    <b-card>
      <form name="form" @submit.prevent="handleUpdate">
        <div v-if="!successful">
      <div class="form-group">
            <label for="password">Vecchia Password</label>
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
            <label for="password">Nuova Password</label>
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
    </div>
</template>

<script>
import coinTransationService from "../services/coinTransation.service";
import customerInformationService from "../services/customerInformation.service";
import partnerInformationService from "../services/partnerInformation.service";
import User from "../model/user";

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
          this.$store.dispatch("auth/update", this.user).then(
            (data) => {
              this.message = data.message;
              this.successful = true;
            },
            (error) => {
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              this.successful = false;
            }
          );
        }
      });
    },
  },
};
</script>