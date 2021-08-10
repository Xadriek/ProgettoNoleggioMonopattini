<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{ content }}</h3>
    </header>

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

      <div class="mt-3">
        <b-card-group deck>
          <b-card
            bg-variant="info"
            text-variant="white"
            header="Lista Swap Arrivo"
            class="text-center"
          >
            <b-card-text
              >Qui puoi visualizzare la lista dei Swap in Arrivo</b-card-text
            >
            <b-button v-b-modal.modalListaSwapArrivo>Lista Swap</b-button>

            <b-modal id="modalListaSwapArrivo" title="Lista Swap" ok-only>
              <b-table
                striped
                hover
                :items="listaswappoint"
                :fields="campibusy"
              ></b-table>
            </b-modal>
          </b-card>

          <b-card
            bg-variant="warning"
            text-variant="white"
            header="Lista Swap Terminati"
            class="text-center"
          >
            <b-card-text
              >Qui puoi visualizzare la lista dei Swap Terminati</b-card-text
            >
            <b-button v-b-modal.modalListSwapTerm>Lista Swap</b-button>

            <b-modal id="modalListSwapTerm" title="Lista Swap" ok-only>
              <b-table
                striped
                hover
                :items="listaswappoint"
                :fields="campibusy"
              ></b-table>
            </b-modal>
          </b-card>

          <b-card
            bg-variant="danger"
            text-variant="white"
            header="Lista Batterie"
            class="text-center"
          >
            <b-card-text
              >Qui vengono visualizzati la lista Batterie</b-card-text
            >
            <b-button v-b-modal.modalListaNoleggi>Lista Batterie</b-button>

            <b-modal id="modalListaNoleggi" title="Lista Batterie" ok-only>
              <b-table
                striped
                hover
                :items="listaswappoint"
                :fields="campibusy"
              ></b-table>
            </b-modal>
          </b-card>
        </b-card-group>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
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
