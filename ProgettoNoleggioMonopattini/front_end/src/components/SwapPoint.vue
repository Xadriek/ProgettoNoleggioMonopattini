<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <div>
        <b-card
          title="Mappa Swap Point nei dintorni"
          class="mb-2"
          style="max-heght: 200px"
        >
          <AddGoogleMap @hubPosition="hubPosition" />
        </b-card>
      </div>
      <b-card title="Swap Point scelto" class="mb-2">
        <b-list-group>
          <b-list-group-item ><p>Nome:</p>{{selectedPartner.name}}</b-list-group-item>
          <b-list-group-item ><p>Indirizzo:</p>{{address.street}} {{address.numberStreet}}, 00{{address.cap}}, {{address.municipality}}, {{address.city}}</b-list-group-item>
          <b-list-group-item > <p>Batterie disponibili:</p>{{numBatteries}}</b-list-group-item>
          <b-list-group-item ><p>Telefono:</p>{{selectedPartner.telephon}}</b-list-group-item>
        </b-list-group>
      </b-card>
      <div v-if="show2">
       <b-button type="submit" variant="primary" class="m-1" @click="showMsgBoxOne">Conferma</b-button>
      <b-alert
                          v-model="pippo"
                          class="position-fixed fixed-top m-0 rounded-0"
                          style="z-index: 2000"
                          variant="success"
                          dismissible
                        >
                          Swap is initializated, please refresh
                        </b-alert>
      
      <b-button type="reset" variant="danger" class="m-1" >Annulla</b-button>
      </div>
      <div v-else-if="this.selectedPartner.id!=null ">Non hai abbastanza monete</div>
      <div v-else-if=" this.numBatteries==0">Non ha batterie disponibili</div>
    </b-form>
  </div>
</template>

<script>
import AddGoogleMap from "../components/AddGoogleMap";
import partnerInformationService from "../services/partnerInformation.service"
import coinTransationService from '../services/coinTransation.service';
import customerInformationService from "../services/customerInformation.service"
import batteryService from '../services/battery.service';
export default {
  name: "SwapPoint",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      form: {},
      conferm:'',
      requestBody:{
        partnerId:0,
        customerId:0,
        coin:0,
        
      },
      customerCoin:Number,
      show: true,
      show2:false,
      pippo: false,
      selectedPartner:{},
      batteries:[],
      address:{},
      numBatteries:0,
      hub:{},
      position:{
        lat:0,
        lng:0
      }
    };
  },
  components: { AddGoogleMap },
  methods: {
    
    onSubmit(event) {
      event.preventDefault();
      
    },
    onReset(event) {
      event.preventDefault();
      this.selectedPartner={};
      this.address={};
      this.numBatteries=0;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
    initSwap(){
      console.log(this.requestBody);
      coinTransationService.saveCoinTransaction(this.requestBody).then(
        response=>{
          console.log(response.data);
          
        }
      );
    },
    showMsgBoxOne() {
        this.conferm = ''
        this.$bvModal.msgBoxConfirm('Confermi lo Swap?')
          .then(value => { 
            if(value){
              console.log(value);
              this.initSwap();
              this.pippo=true;
            }
          })
         
    },
    hubPosition(position){
      this.position=position;
      console.log(this.position);
      partnerInformationService.getPartnerByPosition(this.position).then(
        response=>{
          console.log(response.data);
          this.selectedPartner=response.data;
          this.address=this.selectedPartner.address;
          this.hub=this.selectedPartner.hub;
          this.getBatteries();
          console.log(this.hub);
          this.requestBody.partnerId=this.selectedPartner.id;
          this.getCustomer();
          this.requestBody.coin=2;
          console.log(this.requestBody);
          
        }
      )
    },
    getBatteries(){
      batteryService.getBatteryByHub(this.hub.id).then(
        response=>{
          console.log(response.data);
          this.batteries=response.data;
          this.numBatteries=this.batteries.filter(battery=>battery.state=="CARICA").length;
        }
      )
    },

    getCustomer(){
      customerInformationService.getCustomerByEmail(this.currentUser.email).then(
        response=>{
          console.log(response.data.id);
          this.customerCoin=response.data.customerWallet.coin;
          if(this.customerCoin>1){this.show2=true;}
          console.log(this.customerCoin);
          this.requestBody.customerId=response.data.id;
        }
      )
    }
    
  },
};
</script>
<style scoped>
p{
  font-family: Arial, Helvetica, sans-serif;
  font-style: italic;
  font-weight: bold;
}
</style>