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
          <b-list-group-item >Nome {{selectedPartner.name}}</b-list-group-item>
          <b-list-group-item >Via</b-list-group-item>
          <b-list-group-item > Numero batterie disponibili</b-list-group-item>
          <b-list-group-item >telefono</b-list-group-item>
        </b-list-group>
      </b-card>

      

      <b-button type="submit" variant="primary" class="m-1">Conferma</b-button>
      <b-button type="reset" variant="danger" class="m-1">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
import AddGoogleMap from "../components/AddGoogleMap";
import partnerInformationService from "../services/partnerInformation.service"
export default {
  name: "SwapPoint",
  data() {
    return {
      form: {},
      show: true,
      selectedPartner:{},
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
      alert(JSON.stringify(this.form));
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
    hubPosition(position){
      this.position=position;
      console.log(this.position);
      partnerInformationService.getPartnerByPosition(this.position).then(
        response=>{
          alert(response.data);
          this.selectedPartner=response.data;
          console.log(this.selectedPartner);
        }
      )
    }
    
  },
};
</script>
