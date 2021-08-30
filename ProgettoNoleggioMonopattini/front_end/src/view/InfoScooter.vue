<template>
  <div class="container">
    <div>
      <b-container fluid class="text-center">
        <h3 class="display-2">I nostri Modelli</h3>

        I Nostri Partner soddisfano tutte le esigenze cittadine 
        <br />
        <br />
      </b-container>
    </div>
    <div>
      <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img
              src="images\21-400x400.jpg"
              alt="Image"
              class="rounded-0"
            ></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="Vuoi diventare Partner?">
              <b-card-text>
                 I monopattini elettrici hanno generalmente due piccole ruote dure, con un telaio pieghevole, solitamente in alluminio. ... Alcuni monopattini hanno tre o quattro ruote, o sono di plastica, o non sono pieghevoli. L'autonomia varia in genere da 5 a 50 km, e la velocità massima è di circa 30 km/h.
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  name: "InfoScooter",

  data() {
    return {
      content: "",
    };
  },
  

  mounted() {
    UserService.getPublicContent().then(
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
