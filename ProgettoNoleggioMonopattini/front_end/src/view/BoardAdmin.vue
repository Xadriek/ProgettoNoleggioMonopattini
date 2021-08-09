<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{ content }}</h3>
    </header>

    <div>
      <div>
        <div>
          <b-card-group deck>
            <b-card
              bg-variant="primary"
              text-variant="white"
              header="Numero Noleggi"
              class="text-center"
            >
              <b-button variant="primary">
                I Noleggi Attivi sono: <b-badge variant="light">4</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="secondary"
              text-variant="white"
              header="Numero Hub"
              class="text-center"
            >
              <b-button variant="secondary">
                Il Numero di Hub Attivi: <b-badge variant="light">18</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="success"
              text-variant="white"
              header="Numero Transazioni"
              class="text-center"
            >
              <b-button variant="info">
                Il Numero Transazioni: <b-badge variant="light">9</b-badge>
              </b-button>
            </b-card>
          </b-card-group>
        </div>

        <div class="mt-3">
          <b-card-group deck>
            <b-card
              bg-variant="info"
              text-variant="white"
              header="Lista Clienti"
              class="text-center"
            >
              <b-card-text
                >Qui puoi visualizzare la lista dei clienti</b-card-text
              >
              <b-button v-b-modal.modalListaClienti>Lista Clienti</b-button>

              <b-modal id="modalListaClienti" title="Lista Clienti" ok-only>
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
              header="Lista Partner"
              class="text-center"
            >
              <b-card-text>Qui visualizzi la lista dei partner</b-card-text>
              <b-button v-b-modal.modalListaPartner>Lista Partner</b-button>

              <b-modal id="modalListaPartner" title="Lista Partner" ok-only>
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
              header="Lista Noleggi"
              class="text-center"
            >
              <b-card-text
                >Qui vengono visualizzati i noleggi effttuati.</b-card-text
              >
              <b-button v-b-modal.modalListaNoleggi>Lista Noleggi</b-button>

              <b-modal id="modalListaNoleggi" title="Lista Noleggi" ok-only>
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
        <div class="mt-3">
          <b-card-group deck class="mb-3">
            <b-card
              border-variant="dark"
              header="Lista Swap"
              class="text-center"
            >
              <b-card-text>Qui vengono visualizzati gli Swap</b-card-text>
              <b-button v-b-modal.modalListaSwap>Lista Swap</b-button>

              <b-modal id="modalListaSwap" title="Lista Swap" ok-only>
                    <b-table
                      striped
                      hover
                      :items="listaswappoint"
                      :fields="campibusy"
                    ></b-table>
              </b-modal>
            </b-card>

            <b-card border-variant="dark" header="Lista Scooter" align="center">
              <b-card-text>Qui vengono visualizzati gli Scooter</b-card-text>
              <b-button v-b-modal.modalListaScooter>Lista Scooter</b-button>

              <b-modal id="modalListaScooter" title="Lista Scooter" ok-only>
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
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  name: "admin",
  data() {
    return {
      content: "",
      text: "This is some text.\nIt is read only and doesn't look like an input.",
      campibusy: ["name", "indirizzo", "distanza"],
      listaswappoint: [
        { distanza: 40, name: "Dickerson", indirizzo: "Via Roma" },
        { distanza: 21, name: "Larsen", indirizzo: "Viale Marconi" },
        { distanza: 89, name: "Geneva", indirizzo: "Piazza Verdi" },
        { distanza: 38, name: "Jami", indirizzo: "Colosseo" },
      ],
    };
  },
  mounted() {
    UserService.getAdminBoard().then(
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