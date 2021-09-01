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
              
             
              <b-table striped hover :items="swaps" :ArrivalSwap="fields"></b-table>



            </b-tab>

            <b-tab no-body title="Swap Terminati">

              <b-table striped hover :items="itemsB" :TerminalSwap="fields"></b-table>

            </b-tab>

            <b-tab no-body title="Batterie">
             

              <b-table striped hover :items="itemsC" :Batterie="fields"></b-table>

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

import AddHub from '../components/AddHub.vue';
import coinTransationService from '../services/coinTransation.service';
import partnerInformationService from "../services/partnerInformation.service.js"
import batteryService from '../services/battery.service';


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
      ArrivalSwap: ['ID', 'Event', 'Scooter','UserName'],
      itemsA: [
        {
          
        }
      ],
      
      TerminalSwap: ['ID', 'Customer', 'Partner','Log','Coin','IsComplete'],
      itemsB: [
        {
          
        }
      ],
      Batterie: ['ID', 'Voltaggio', 'Capacita','DataImmissione','Stato'],
      itemsC: [
        {
          
        }
      ],
      geocode:{
        latitude:null,
        longitude:null
      },
      
      batteries:[],
      coinTransation:[],
      currentPartner:{}
      

    };
  },

  
     
  mounted(){
    partnerInformationService.getPartnerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.currentPartner=response.data;
      this.infoPartner(this.currentPartner);
      })}
  ,
  methods:{
    infoPartner(currentPartner){
      console.log(currentPartner);
      coinTransationService.getCoinTransationByPartner(currentPartner.id).then(
        response=>{
          console.log(response.data);
          this.coinTransation=response.data;
        }
      ),
      console.log(currentPartner.hub.id);
      batteryService.getBatteryByHub(currentPartner.hub.id).then(
        response=>{
          console.log(response.data);
          this.batteries=response.data;
        }
      )
        
    },
    
  
  }

};
</script>