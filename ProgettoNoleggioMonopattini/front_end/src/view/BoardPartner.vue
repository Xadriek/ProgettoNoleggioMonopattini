<template>
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
              
             
              <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item>id</b-list-group-item>
                      <b-list-group-item>Event Log</b-list-group-item>
                      <b-list-group-item>Scooter  </b-list-group-item>
                      <b-list-group-item>Customer Username   </b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="coinTransation in coinTransationsNotComplete"
                    v-bind:key="coinTransation.id"
                    >
                      <b-list-group-item>{{coinTransation.id}}</b-list-group-item>
                      <b-list-group-item>{{coinTransation.logTransition}}   </b-list-group-item>
                      <b-list-group-item>{{coinTransation.entrySwap.scooter.id}}     </b-list-group-item>
                      <b-list-group-item>{{coinTransation.fromCustomer.username}}</b-list-group-item>
                      <b-list-group-item>
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
                      </b-list-group-item>
                      
                    </b-list-group>
                </b-list-group>



            </b-tab>

            <b-tab no-body title="Swap Terminati">

               <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item>id</b-list-group-item>
                      <b-list-group-item>From Customer</b-list-group-item>
                      <b-list-group-item>To Partner  </b-list-group-item>
                      <b-list-group-item>Log  </b-list-group-item>
                      <b-list-group-item>Coin  </b-list-group-item>
                      <b-list-group-item>Complete</b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="coinTransation in coinTransationsComplete"
                    v-bind:key="coinTransation.id"
                    >
                      <b-list-group-item>{{coinTransation.id}}</b-list-group-item>
                      <b-list-group-item>{{coinTransation.fromCustomer.username}}   </b-list-group-item>
                      <b-list-group-item>{{coinTransation.toPartner.username}}     </b-list-group-item>
                      <b-list-group-item>{{coinTransation.logTransition}}</b-list-group-item>
                      <b-list-group-item>{{coinTransation.coin}}       </b-list-group-item>
                      <b-list-group-item >
                           <b-iconstack v-if="coinTransation.isComplete==true" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="check"></b-icon>
                                </b-iconstack>
                      </b-list-group-item>
                      
                    </b-list-group>
                </b-list-group>

            </b-tab>

            <b-tab no-body title="Batterie">
             

             <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item>id</b-list-group-item>
                      <b-list-group-item>Voltage</b-list-group-item>
                      <b-list-group-item>Capacity</b-list-group-item>
                      <b-list-group-item>Date Immission</b-list-group-item>
                      <b-list-group-item>State</b-list-group-item>
                      <b-list-group-item>
                        <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxBattery()">
                          <b-icon icon="x-circle"></b-icon> Add Battery
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
                      </b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="battery in batteries"
                    v-bind:key="battery.id"
                    >
                      <b-list-group-item>{{battery.id}}</b-list-group-item>
                      <b-list-group-item>{{battery.voltage}}   </b-list-group-item>
                      <b-list-group-item>{{battery.capacity}}     </b-list-group-item>
                      <b-list-group-item>{{battery.dateOfBirth}}</b-list-group-item>
                      <b-list-group-item>{{battery.state}}       </b-list-group-item>
                      <b-list-group-item>
                        <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxBatteryState(battery)">
                          <b-icon icon="x-circle"></b-icon> Change State Battery
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
                      </b-list-group-item>
                      
                    </b-list-group>
                </b-list-group>

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