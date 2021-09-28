<template>
<div class="sfondo">
  <div>
    <div>
      </div>
      
        <b-card v-if="this.currentPartner.id==null" title="Inserimento Nuovo Noleggio">
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
              I Swap effettuati sono : <b-badge variant="light">{{countCoinTransationsComplete}}</b-badge>
            </b-button>
          </b-card>

          <b-card
            bg-variant="success"
            text-variant="white"
            header="Numero Batterie"
            class="text-center"
          >
            <b-button variant="info">
              Numero Batterie Cariche: <b-badge variant="light">{{countBatteryCharged}}</b-badge>
            </b-button>
          </b-card>
        </b-card-group>
      </div>

      <div>
        <b-card no-body>
          <b-tabs card>
            <b-tab no-body title="Swap in Arrivo">
                <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">EVENT LOG</b-th>
                      <b-th class="field">SCOOTER</b-th>
                      <b-th class="field">CUSTOMER USERNAME</b-th>
                    </b-tr>
                    <b-tr
                      v-for="coinTransation in coinTransationsNotComplete"
                      v-bind:key="coinTransation.id"
                    >
                      <b-th>{{coinTransation.id}}</b-th>
                      <b-th>{{coinTransation.logTransition}}</b-th>
                      <b-th>{{coinTransation.entrySwap.scooter.id}}</b-th>
                      <b-th>{{coinTransation.fromCustomer.username}}</b-th>
                      <b-th>
                        <div>
                          <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxIncompleteSwap(coinTransation)">
                         <b-iconstack >
                                <b-icon stacked icon="square"></b-icon>
                                
                                <b-icon stacked icon="check"></b-icon>
                                
                                </b-iconstack>
                                Complete Swap
                             </b-button>
                              <b-alert
                          v-model="showTop1"
                          class="position-fixed fixed-top m-0 rounded-0"
                          style="z-index: 2000"
                          variant="success"
                          dismissible
                        >
                          Swap is complete, please refresh
                        </b-alert>
                             </b-button-group>
                        </div>
                      </b-th>                      
                    </b-tr>
                  </table>
            </b-tab>

            <b-tab no-body title="Swap Terminati">
                <table>
                    <b-tr>
                      <b-th  class="field">ID</b-th>
                      <b-th class="field">FROM CUSTOMER</b-th>
                      <b-th class="field">TO PARTNER</b-th>
                      <b-th class="field">LOG</b-th>
                      <b-th class="field">COIN</b-th>
                      <b-th class="field">COMPLETE</b-th>
                    </b-tr>
                    <b-tr
                      v-for="coinTransation in coinTransations"
                      v-bind:key="coinTransation.id">
                      <b-th>{{coinTransation.id}}</b-th>
                      <b-th>{{coinTransation.fromCustomer.username}}</b-th>
                      <b-th>{{coinTransation.toPartner.username}}</b-th>
                      <b-th>{{coinTransation.logTransition}}</b-th>
                      <b-th>{{coinTransation.coin}}</b-th>
                      <b-th>
                        <div>
                          <b-iconstack v-if="coinTransation.isComplete==true" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="check"></b-icon>
                                </b-iconstack>
                        </div>
                      </b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                    </b-tr>
                  </table>
            </b-tab>

            <b-tab no-body title="Batterie">
                <table>
                    <b-tr >
                      <b-th class="field">ID</b-th>
                      <b-th class="field">VOLTAGE</b-th>
                      <b-th class="field">CAPACITY</b-th>
                      <b-th class="field">DATE IMMISSION</b-th>
                      <b-th class="field">STATE</b-th>
                      <b-th class="field">
                        <div>
                          <b-button-group>
                             <b-button variant="outline-success" @click="showMsgBoxBattery()">
                          <b-icon icon="battery-full"></b-icon> Add Battery
                             </b-button>
                              <b-alert
                          v-model="showTop2"
                          class="position-fixed fixed-top m-0 rounded-0"
                          style="z-index: 2000"
                          variant="success"
                          dismissible
                        >
                          Battery has been added, please refresh
                        </b-alert>
                             </b-button-group>
                        </div>
                      </b-th>
                    </b-tr>
                    <b-tr
                      v-for="battery in batteries"
                      v-bind:key="battery.id"
                    >
                      <b-th>{{battery.id}}</b-th>
                      <b-th>{{battery.voltage}}</b-th>
                      <b-th>{{battery.capacity}}</b-th>
                      <b-th>{{battery.dateOfBirth}}</b-th>
                      <b-th>{{battery.state}}</b-th>
                      <b-th>
                        <div>
                          <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxBatteryState(battery)">
                          <b-icon icon="battery-charging"></b-icon> Change State Battery
                             </b-button>
                              <b-alert
                          v-model="showTop3"
                          class="position-fixed fixed-top m-0 rounded-0"
                          style="z-index: 2000"
                          variant="success"
                          dismissible
                        >
                          State of battery has been changed, please refresh
                        </b-alert>
                             </b-button-group>
                        </div>
                      </b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                    </b-tr>
                  </table>
            </b-tab>
          </b-tabs>
        </b-card>
      </div>
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
      showTop1: false,
      showTop2: false,
      showTop3: false,
      countCoinTransationComplete:0,
      countBatteryCharged:0,
      geocode:{
        latitude:null,
        longitude:null
      },
      
      batteries:[],
      coinTransations:[],
      currentPartner:{},
      coinTransationsComplete:[],
      coinTransationsNotComplete:[],

      

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
    update(){
      this.$forceUpdate();
    },
    showMsgBoxIncompleteSwap(coinTransation){
       this.conferm = ''
        this.$bvModal.msgBoxConfirm('Confermi il completamento dello swap?')
          .then(value => { 
            if(value){
              console.log(value);
              this.completeSwap(coinTransation);
              this.update;
              
            }
          })
    },
    completeSwap(coinTransation){
      coinTransationService.updateCoinTransations(coinTransation).then(
        response=>{
          console.log(response.data);
          coinTransation=response.data;
          this.showTop1=true;
          
        }
      )
    },
    showMsgBoxBattery(){
      this.conferm = ''
        this.$bvModal.msgBoxConfirm('Aggiungi una batteria?')
          .then(value => { 
            if(value){
              console.log(value);
              this.addBattery();
              this.update;
            }
          })
    },
    addBattery(){
      batteryService.createBattery(this.currentPartner.hub.id).then(
        response=>{
          console.log(response.data);
          this.showTop2=true;
          

        }
      )
    },
    showMsgBoxBatteryState(battery){
       this.conferm = ''
        this.$bvModal.msgBoxConfirm('Vuoi cambiare lo stato della batteria?')
          .then(value => { 
            if(value){
              console.log(value);
              this.changeState(battery);
              this.update;
            }
          })
    },
    changeState(battery){
      batteryService.updatebattery(battery).then(
        response=>{
          console.log(response.data);
          battery=response.data;
          this.update();
          this.showTop3=true;
        }
      )
    },
    infoPartner(currentPartner){
      console.log(currentPartner);
      coinTransationService.getCoinTransationByPartner(currentPartner.id).then(
        response=>{
          console.log(response.data);
          this.coinTransations=response.data;
          this.coinTransationsComplete=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true);
          this.coinTransationsNotComplete=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==false);
          this.countCoinTransationsComplete=this.coinTransationsComplete.length;
        }
      ),
      console.log(currentPartner.hub.id);
      batteryService.getBatteryByHub(currentPartner.hub.id).then(
        response=>{
          console.log(response.data);
          this.batteries=response.data;
          this.countBatteryCharged=this.batteries.filter(battery=>battery.state=="CARICA").length;
          
        }
      )
        
    },
    
  
  }

};
</script>
<style>
 th{
  
  width: 250px;
  height: 50px;
  font-weight: normal;
  border-style: outset;
  text-align: center;
}
.field{
  font-weight: bold;
}
.sfondo{
  width: 100%;
  height: 100%;
  padding-left: 20%;
  padding-right: 20%;
}

</style>