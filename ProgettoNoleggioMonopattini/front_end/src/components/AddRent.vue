<template>
  <div>
    <b-form v-if="show">
      <b-form-group label="Nome:" >
        <b-form-input
          id="input-2"
          v-model="customerInformation.name"
          placeholder="Inserisci il Nome"
          required
          name="name"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Cognome:" >
        <b-form-input
          id="input-2"
          v-model="customerInformation.cognome"
          placeholder="Inserisci il Cognome"
          required
          name="surname"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Telefono:" >
        <b-form-input
          id="input-2"
          v-model="customerInformation.telefono"
          placeholder="Inserisci il telefono"
          required
          name="telephon"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Via:" >
        <b-form-input
          id="input-2"
          v-model="address.street"
          placeholder="Inserisci via/viale/piazza"
          required
          name="street"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Civico:" >
        <b-form-input
          id="input-2"
          v-model="address.numberStreet"
          placeholder="Inserisci il civico"
          required
          name="numberStreet"
        ></b-form-input>
      </b-form-group>

       <b-form-group label="Cap:" >
        <b-form-input
          id="input-2"
          v-model="address.cap"
          placeholder="Inserisci il cap"
          required
          name="cap"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Comune:" >
        <b-form-input
          id="input-2"
          v-model="address.municipality"
          placeholder="Inserisci il comune"
          required
          name="municipality"
        ></b-form-input>
      </b-form-group>

      <b-form-group  label="Citta:" >
        <b-form-input
          id="input-2"
          v-model="address.city"
          placeholder="Inserisci la Citta"
          required
          name="city"
        ></b-form-input>
      </b-form-group>

      <b-form-group  label="Nazione:" >
        <b-form-input
          id="input-2"
          v-model="address.country"
          placeholder="Inserisci la Nazione"
          required
          name="country"
        ></b-form-input>
      </b-form-group>

      <b-form-group
        id="new-contract"
        label="Piano tariffario:"
        
      >
        <b-form-radio
          v-model="contract.plan"
          
          name="plan"
          value="PIANO_TARIFFARIO1"
          >Trimestrale</b-form-radio
        >
        <b-form-radio
          v-model="contract.plan"
          
          name="plan"
          value="PIANO_TARIFFARIO2"
          >Semetrale</b-form-radio
        >
        <b-form-radio
          v-model="contract.plan"
          
          name="plan"
          value="PIANO_TARIFFARIO3"
          >Annuale</b-form-radio
        >
      </b-form-group>

      <b-button @click="createRent" type="submit" variant="primary">Conferma</b-button>
      <b-button type="reset" variant="danger">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
import CustomerInformation from "../model/customerInformation";
import Address from "../model/address";
import Rent from "../model/rent";
import Contract from "../model/contract";
import customerInformationService from "../services/customerInformation.service.js";
import addressService from "../services/address.service.js";
import contractService from "../services/contract.service.js";
import rentService from "../services/rent.service.js";

export default {
  name: "AddRent",
  data() {
    return {
      customerInformation: new CustomerInformation(
        null,
        "",
        "",
        "",
        "",
        "",
        ""
      ),
      address: new Address(null, "", "", "", "", "",""),
      rent: new Rent(null,"", ""),
      contract: new Contract(null,"", ""),
      show: true,
      
    };
  },
  methods: {
    createCustomerInformation() {
      this.customerInformation.user = this.currentUser;
      var responseData=customerInformationService.saveCustomerInformation(this.customerInformation);
      return responseData;
   },
    currentUser() {
      return this.$store.state.auth.user;
    },
    createAddress(){
     var responseData=addressService.saveAddress(this.address);
      this.customerInformation.address=responseData;
    },
    createContract(){
    contractService.saveContract(this.contract);
    },
    createRent(){
     this.rent.customer=this.createCustomerInformation;
     this.rent.contract=this.createContract;
     rentService.saveRent(this.rent);
     
     
    },
    
    onReset(event) {
      event.preventDefault();
      // Reset our form values

      this.form.name = "";
      this.cognome = "";
      this.telefono = "";
      this.indirizzo = "";
      this.citta = "";
      this.nazione = "";
      this.datainizio = "";
      this.durata = "";
      this.pianotariffa = "";

      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>