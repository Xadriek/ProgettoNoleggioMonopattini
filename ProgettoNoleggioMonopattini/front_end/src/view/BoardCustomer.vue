<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{ content }}</h3>
    </header>
    <div>
      <div class="mb-3">
        <b-button v-b-toggle.creanoleggio>Crea Noleggio</b-button>
        <b-button v-b-toggle.cercaswappoint>Cerca SwapPoint</b-button>
        <b-button v-b-toggle.listaswappoint>Lista SwapPoint</b-button
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
          <b-card>I am collapsible content!</b-card>
        </b-collapse>
      </div>
    </div>
  
</template>

<script>
import AddRent from "../components/AddRent.vue";
import SwapPoint from "../components/SwapPoint.vue";
import UserService from "../services/user.service";

export default {
  components: { AddRent , SwapPoint },
  name: "customer",
  data() {
    return {
      content: "",
    };
  },
  mounted() {
    UserService.getCustomerBoard().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },

  
};
</script>