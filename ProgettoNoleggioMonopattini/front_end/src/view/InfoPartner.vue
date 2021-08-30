<template>
  <div class="container">
    <div>
      <b-container fluid class="text-center">
        <h3 class="display-2">I Partner</h3>

        I Nostri Partner sono presenti sulla mappa della tua città. Sarà
        sufficente scegliere sulla Mappa il punto più vicino e potrai sostituire
        la tua batteria
        <br />
        <br />
      </b-container>
    </div>
    <div>
      <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img
              src="images\20-400x400.jpg"
              alt="Image"
              class="rounded-0"
            ></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="Vuoi diventare Partner?">
              <b-card-text>
                E' molto semplice, basta uan semplice registrazione e potrai accedere ai benefici e cominciare a guadagnare in modo immediato e semplice.
              </b-card-text>
            </b-card-body>
          </b-col>
        </b-row>
      </b-card>
    </div>

    <div>
      <AddGoogleMap />
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AddGoogleMap from "../components/AddGoogleMap";

export default {
  name: "InfoPartner",

  data() {
    return {
      content: "",
    };
  },
  components: { AddGoogleMap },

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
