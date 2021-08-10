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
    </p>
    {{userProfile.name}}
    <p>
    <strong>Cognome:</strong>
    </p>
    <p>
    <strong>Telefono:</strong>
    </p>
    <p>
    <strong>Stato Attivo/Disattivo</strong>
    </p>        
    <p>
    <strong>Monete nel Wallet</strong>
    </p>
    <p>
    <strong>ID Noleggio</strong>
    </p>    
    <p>
    <strong>Numero Transazioni</strong>
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
  customerInformationService.getCustomerByEmail(this.currentUser.email)
    .then(response=>{
      this.userProfile=response.data;
    }
    )
    
  }
};
</script>