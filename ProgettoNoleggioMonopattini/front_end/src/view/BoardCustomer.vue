<template>
  <div class="container" >
    <header >
      
    </header>
    <div>
      <div class="mb-3" v-if="this.currentUser.roles.includes('ROLE_CUSTOMER')">
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
            <table>
                    <b-tr>
                      <b-th>id</b-th>
                      <b-th>Event Log</b-th>
                      <b-th>Scooter</b-th>
                      <b-th>Customer Username</b-th>
                    </b-tr>
                    <b-tr
                      v-for="coinTransation in coinTransationsNotComplete"
                      v-bind:key="coinTransation.id">
                      <b-th>{{coinTransation.id}}</b-th>
                      <b-th>{{coinTransation.logTransition}}</b-th>
                      <b-th>{{coinTransation.entrySwap.scooter.id}} </b-th>
                      <b-th>{{coinTransation.fromCustomer.username}}</b-th>
                      <b-th>
                        <div>
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
                        </div>
                      </b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                      <b-th></b-th>
                    </b-tr>
                  </table>

          </b-card>
          <b-card  title="Swap Terminati">


            <table>
                    <b-tr>
                      <b-th>id</b-th>
                      <b-th>From Customer</b-th>
                      <b-th>To Partner</b-th>
                      <b-th>Log</b-th>
                      <b-th>Coin</b-th>
                      <b-th>Complete</b-th>
                    </b-tr>
                     <b-tr
                      v-for="coinTransation in coinTransations"
                      v-bind:key="coinTransation.id">
                      <b-th>{{coinTransation.id}}</b-th>
                      <b-th>{{coinTransation.fromCustomer.username}}</b-th>
                      <b-th>{{coinTransation.toPartner.username}} </b-th>
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
                    </b-tr>
                  </table>
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