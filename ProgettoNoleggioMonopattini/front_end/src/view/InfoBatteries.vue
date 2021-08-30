<template>
  <div class="container">
    <div>
      <b-container fluid class="text-center">
        <h3 class="display-2">Le nostre Batterie</h3>

        Per tutte le tue esigenze 
        <br />
        <br />
      </b-container>
    </div>
    <div>
      <b-card no-body class="overflow-hidden">
        <b-row no-gutters>
          <b-col md="6">
            <b-card-img
              src="images\22-400x400.jpg"
              alt="Image"
              class="rounded-0"
            ></b-card-img>
          </b-col>
          <b-col md="6">
            <b-card-body title="Vuoi diventare Partner?">
              <b-card-text>
                Una batteria standard solitamente ha una tensione di 12 V ed un'intensità di 14 Ah, per crescere progressivamente in base alle caratteristiche tecniche del motore e del monopattino. Le entry-level del momento sono equipaggiate frequentemente con batteria da 36 e 48 V.
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
  name: "InfoBatteries",
 
  data() {
    return {
      content: ""

    };
  },
  //components:{AddGoogleMap},
  
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
