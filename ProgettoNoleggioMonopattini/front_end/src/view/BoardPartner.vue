<template>
  <div class="container">
    

    <div>
      </div>
      
        <b-card title="Inserimento Nuovo Noleggio">
          <add-rent />
        </b-card>
      
      <div>
      <div>
        <b-card-group deck>
          <b-card
            bg-variant="primary"
            text-variant="white"
            header="Numero Swap"
            class="text-center"
          >
            <b-button variant="primary">
              I Swap effettuati sono : <b-badge variant="light">4</b-badge>
            </b-button>
          </b-card>

          <b-card
            bg-variant="success"
            text-variant="white"
            header="Numero Batterie"
            class="text-center"
          >
            <b-button variant="info">
              Numero Batterie Cariche: <b-badge variant="light">9</b-badge>
            </b-button>
          </b-card>
        </b-card-group>
      </div>

      <div>
        <b-card no-body>
          <b-tabs card>
            <b-tab no-body title="Swap in Arrivo">
              Qui va la lista degli swap in Arrivo
              <b-card-footer>Swap in Arrivo</b-card-footer>
            </b-tab>

            <b-tab no-body title="Swap Terminati">
              Qui va la lista degli swap Terminati
              <b-card-footer>Swap Terminati</b-card-footer>
            </b-tab>

            <b-tab no-body title="Batterie">
              Qui va la lista delle Batterie
              <b-card-footer>Lista Batterie</b-card-footer>
            </b-tab>
            <b-tab title="Aiuto">
              
              <b-card-text>
                Qui scriviamo un minimo di spiegazione che può essere utile se vuoi
              </b-card-text>
            </b-tab>
          </b-tabs>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>

import AddRent from '../components/AddRent.vue';
import UserService from "../services/user.service";

export default {
  components: { AddRent },
  name: "partner",
  data() {
    return {
      show: true,
    };
  },

  mounted() {
    UserService.getPartnerBoard().then(
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