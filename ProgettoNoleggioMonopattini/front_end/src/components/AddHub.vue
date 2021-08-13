<template>
  <div>
    <b-form v-if="show">
      <b-form-group label="Nome:" >
        <b-form-input
          id="input-2"
          v-model="partnerInformation.name"
          placeholder="Inserisci il Nome"
          required
          name="name"
        ></b-form-input>
      </b-form-group>
      

      <b-form-group label="P.Iva:" >
        <b-form-input
          id="input-2"
          v-model="partnerInformation.pIva"
          placeholder="Inserisci la P.Iva"
          required
          name="pIva"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Telefono:" >
        <b-form-input
          id="input-2"
          v-model="partnerInformation.telephon"
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


      <b-button @click="createHub" type="submit" variant="primary">Conferma</b-button>
      <b-button type="reset" variant="danger">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
import PartnerInformation from "../model/partnerInformation";
import Address from "../model/address";

import hubService from "../services/hub.service.js";

export default {
  name: "AddHub",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  data() {
    return {
      partnerInformation: new PartnerInformation(
        "",
        "",
        "",
        "",
        ""
      ),
      address: new Address("", "", "", "", "",""),
      show: true,
      
    };
  },
  methods: {
    createHub() {
      
      return hubService.saveHub(this.partnerInformation,this.address,this.currentUser.email,this.currentUser.username).then(
        response=> {
         console.log(response.data);
          

        },
        () => {
          
          return alert("errore");
        }
      );
      
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