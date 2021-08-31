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
          <b-list-group-item >{{selectedPartner.name}}</b-list-group-item>
          <b-list-group-item >{{address.street}} {{address.numberStreet}} {{address.cap}} {{address.municipality}} {{address.city}}</b-list-group-item>
          <b-list-group-item > Numero batterie disponibili{{numBatteries}}</b-list-group-item>
          <b-list-group-item >{{selectedPartner.telephon}}</b-list-group-item>
        </b-list-group>
      </b-card>
       <b-button type="submit" variant="primary" class="m-1" @click="showMsgBoxOne">Conferma</b-button>
      <b-button type="reset" variant="danger" class="m-1">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
import AddGoogleMap from "../components/AddGoogleMap";
import partnerInformationService from "../services/partnerInformation.service"
import coinTransationService from '../services/coinTransation.service';
import customerInformationService from "../services/customerInformation.service"
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
        coin:0
      },
      show: true,
      selectedPartner:{},
      address:{},
      numBatteries:5,
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
      // Reset our form values
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
          console.log(this.hub);
          this.requestBody.partnerId=this.selectedPartner.id;
          this.getCustomer();
          this.requestBody.coin=2;
          console.log(this.requestBody);
          
        }
      )
    },
    getCustomer(){
      customerInformationService.getCustomerByEmail(this.currentUser.email).then(
        response=>{
          console.log(response.data.id);
          this.requestBody.customerId=response.data.id;
        }
      )
    }
    
  },
};
</script>
