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

        <div>
          <b-card no-body>
            <b-tabs card>
              <b-tab no-body title="Clienti">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  :fields="campibusy"
                ></b-table>
              </b-tab>

              <b-tab no-body title="Partner">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  :fields="campibusy"
                ></b-table>
              </b-tab>

              <b-tab no-body title="Noleggi">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  :fields="campibusy"
                ></b-table>
              </b-tab>
              <b-tab no-body title="Swap">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  :fields="campibusy"
                ></b-table>
              </b-tab>
              <b-tab no-body title="scooter">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  :fields="campibusy"
                ></b-table>
              </b-tab>

              <b-tab title="Aiuto">
                <b-card-text>
                  Qui scriviamo un minimo di spiegazione che può essere utile se
                  vuoi
                </b-card-text>
              </b-tab>
            </b-tabs>
          </b-card>
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