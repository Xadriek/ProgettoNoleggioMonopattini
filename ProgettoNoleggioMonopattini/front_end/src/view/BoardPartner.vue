<template>
  <div class="container">
    

    <div>
      </div>
      
        <b-card title="Inserimento Nuovo Noleggio">
          <add-hub />
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
            <div>{{currentPartner}}</div>
            <div>{{batteries}}</div>
            <div>{{swaps}}</div>
            <div>{{coinTransation}}</div>
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

import AddHub from '../components/AddHub.vue';
import partnerInformationService from "../services/partnerInformation.service"
import geocodeService from "../services/geocode.service"


export default {
  components: { AddHub },
  name: "partner",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      show: true,
      currentPartner:{},
      batteries:[],
      swaps:[],
      coinTransation:[],
      geocode:{
        id:null,
        latitude:null,
        longitude:null
      }
      

    };
  },

  mounted() {
    partnerInformationService.getPartnerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.currentPartner=response.data;
      this.geocode.id=this.currentPartner.hub.geocode.id;
      this.batteries=this.currentPartner.hub.stokedBattery;
      this.swaps=this.currentPartner.hub.swapCompleted;
      this.coinTransation=this.currentPartner.coinTransation;
      this.locateGeoLocation();
      console.log(this.currentPartner.hub.coordinate);
      alert();
      geocodeService.updateGeocode(this.geocode);
      this.currentPartner.hub.coordinate=this.geocode;
      partnerInformationService.updatePartnerInformation(this.currentPartner);
      })
  },
    
  methods:{
    locateGeoLocation: function () {
      navigator.geolocation.getCurrentPosition((res) => {
        this.geocode.cordinate.latitude=res.coords.latitude;
        this.geocode.cordinate.longitude=res.coords.longitude;
        
        
      });
    },
  }
};
</script>