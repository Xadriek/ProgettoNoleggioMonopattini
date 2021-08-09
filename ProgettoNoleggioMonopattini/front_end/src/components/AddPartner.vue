<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-2" label="Nome:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.name"
          placeholder="Inserisci il Nome"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Cognome:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.cognome"
          placeholder="Inserisci il Cognome"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Telefono:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.telefono"
          placeholder="Inserisci il telefono"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Indirizzo:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.indirizzo"
          placeholder="Inserisci Indirizzo"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Citta:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.citta"
          placeholder="Inserisci la Citta"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Naziome:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.nazione"
          placeholder="Inserisci la Nazione"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="DataInizio:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.datainizio"
          placeholder="Inserisci la Data di Inizio Noleggio"
          required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Partita Iva:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.piva"
          placeholder="Inserisci la Partita Iva"
          required
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Conferma</b-button>
      <b-button type="reset" variant="danger">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "AddPartner",
  data() {
    return {
      form: {
        nome: "",
        cognome: "",
        telefono: "",
        indirizzo: "",
        citta: "",
        nazione: "",
        datainizio: "",
        piva: "",
      },
      show: true,
    };
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      alert(JSON.stringify(this.form));
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
      this.piva = "";
     
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>