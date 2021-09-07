<template>
  <div class="container" >
    <header >
      
    </header>
    <div>
      <div class="mb-3">
        <b-button block variant="primary" size="lg" v-if="show" v-b-toggle.creanoleggio class="m-1">Crea Noleggio</b-button>
        <b-button block variant="primary" size="lg" v-b-toggle.cercaswappoint class="m-1">Cerca SwapPoint</b-button>
        <b-button block variant="primary" size="lg" v-b-toggle.listaswappoint class="m-1">Lista Swap</b-button
        >
      </div>
    </div>
      <b-collapse v-if="show" id="creanoleggio">
        <b-card title="Inserimento Nuovo Noleggio">
          <add-rent />
        </b-card>
      </b-collapse>
      <div>
        
        <b-collapse id="cercaswappoint">
          <b-card title="Cerca Swap Point">
           <swap-point />
          </b-card>
        </b-collapse>

        <b-collapse id="listaswappoint">

          <b-card  title="Swap in corso">

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
                             <b-button variant="outline-danger" @click="showMsgBoxDelete(coinTransation)">
                          <b-icon icon="x-circle"></b-icon> Delete Swap
                             </b-button>
                              <b-alert
                          v-model="showTop"
                          class="position-fixed fixed-top m-0 rounded-0"
                          style="z-index: 2000"
                          variant="success"
                          dismissible
                        >
                          Swap is delete, please refresh
                        </b-alert>
                             </b-button-group>
                      </b-list-group-item>
                      
                    </b-list-group>
                </b-list-group>
          </b-card>
          <b-card  title="Swap Terminati">

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

            </b-card>




        </b-collapse>
      </div>
    </div>
  
</template>

<script>
import AddRent from "../components/AddRent.vue";
import SwapPoint from "../components/SwapPoint.vue";
import coinTransationService from '../services/coinTransation.service';
import customerInformationService from "../services/customerInformation.service"


export default {
  components: { AddRent, SwapPoint},
  name: "customer",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      currentCustomer:{},
      coinTransations:[],
      coinTransationsNotComplete:[],
      coinTransationsComplete:[],
      rent:[],
      show:true,
      showTop: false
    };
  },
  mounted() {
   customerInformationService.getCustomerByEmail(this.currentUser.email)
    .then(response=>{
      console.log(response.data);
      this.currentCustomer=response.data
      this.getCoinTransation(response.data);
      this.rent=this.currentCustomer.rent;
      this.show=false;
      console.log(this.rent);
      }
    )
  },
  methods:{
    update(){
      this.$forceUpdate();
    },
    showMsgBoxDelete(coinTransation){
      this.conferm = ''
        this.$bvModal.msgBoxConfirm('Confermi la cancellazione dello swap?')
          .then(value => { 
            if(value){
              console.log(value);
              this.deleteSwap(coinTransation);
              this.update;
              
            }
          })
    },
    deleteSwap(coinTransation){
      coinTransationService.deleteCoinTransation(coinTransation).then(
        response=>{
          console.log(response.state);
          this.showTop=true;
          
        }
      )
    },
    getCoinTransation(customer){
      coinTransationService.getCoinTransationByCustomer(customer.id).then(
        response=>{
          console.log(response.data);
          this.coinTransations=response.data;
          this.coinTransationsComplete=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true);
          this.coinTransationsNotComplete=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==false);
        }
      )
    }
  }

  
};
</script>