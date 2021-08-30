<template>
  <div class="container">
    <header class="jumbotron">
      
    </header>
    <div>
      <div class="mb-3">
        <b-button v-b-toggle.creanoleggio class="m-1">Crea Noleggio</b-button>
        <b-button v-b-toggle.cercaswappoint class="m-1">Cerca SwapPoint</b-button>
        <b-button v-b-toggle.listaswappoint class="m-1">Lista SwapPoint</b-button
        >
      </div>
    </div>
      <b-collapse id="creanoleggio">
        <b-card title="Inserimento Nuovo Noleggio">
          <add-rent />
        </b-card>
      </b-collapse>
      <div>
        
        <b-collapse id="cercaswappoint">
          <b-card title="Cerca Swap Point">
           <swap-point />
          </b-card>
        </b-collapse>
        <b-collapse id="listaswappoint">
          <b-card title="Lista Swap Point">
           <swap-points />
          </b-card>
        </b-collapse>
      </div>
    </div>
  
</template>

<script>
import AddRent from "../components/AddRent.vue";
import SwapPoint from "../components/SwapPoint.vue";
import SwapPoints from "../components/SwapPoints.vue";
import customerInformationService from "../services/customerInformation.service"


export default {
  components: { AddRent, SwapPoint, SwapPoints },
  name: "customer",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      currentCustomer:{},
      coinTransations:[],
      rent:[]
    };
  },
  mounted() {
   customerInformationService.getCustomerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.currentCustomer=response.data
      this.coinTransations=this.currentCustomer.coinTransations;
      this.rent=this.currentCustomer.rent;
      }
    )
  },

  
};
</script>