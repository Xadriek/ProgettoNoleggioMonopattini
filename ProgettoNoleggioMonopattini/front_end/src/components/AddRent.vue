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
          v-model="customerInformation.surname"
          placeholder="Inserisci il Cognome"
          required
          name="surname"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Telefono:" >
        <b-form-input
          id="input-2"
          v-model="customerInformation.telephon"
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
import Contract from "../model/contract";
import rentService from "../services/rent.service.js";

export default {
  name: "AddRent",
  data() {
    return {
      customerInformation: new CustomerInformation(
        "",
        "",
        "",
        "",
        ""
      ),
      address: new Address("", "", "", "", "",""),
      contract: new Contract(""),
      show: true,
      
    };
  },
  methods: {
    createRent() {
      
      return rentService.saveRent(this.customerInformation,this.address,this.contract,this.currentUser.id).then(
        response=> {
          alert('WIN');
          return response.data;

        },
        () => {
          alert('dio cane');
          return alert("errore");
        }
      );
      
   },
    currentUser() {
      return this.$store.state.auth.user;
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