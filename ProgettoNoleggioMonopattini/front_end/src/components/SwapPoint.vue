<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <div>
        <b-card
          title="Mappa Swap Point nei dintorni"
          class="mb-2"
          style="max-heght: 200px"
        >
          <AddGoogleMap />
        </b-card>
      </div>
      <b-card title="Lista Swap Points nei dintorni" class="mb-2">
        <b-list-group>
          <b-list-group-item button>Button item</b-list-group-item>
          <b-list-group-item button>I am a button</b-list-group-item>
          <b-list-group-item button> button</b-list-group-item>
          <b-list-group-item button>This is a button too</b-list-group-item>
        </b-list-group>
      </b-card>

      <div>
        <b-form-group
          id="fieldset-horizontal"
          label-cols-sm="4"
          label-cols-lg="3"
          content-cols-sm
          content-cols-lg="7"
          label="Seleziona lo Swap"
          label-for="input-horizontal">
          <b-form-select v-model="selected" :options="options"></b-form-select>
        </b-form-group>
      </div>

      <b-button type="submit" variant="primary" class="m-1">Conferma</b-button>
      <b-button type="reset" variant="danger" class="m-1">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
import AddGoogleMap from "../components/AddGoogleMap";

export default {
  name: "SwapPoint",
  data() {
    return {
      form: {},
      show: true,
      selected: null,
      options: [
        { value: null, text: "Please select an option" },
        { value: "a", text: "This is First option" },
        { value: "b", text: "Selected Option" },
        { value: { C: "3PO" }, text: "This is an option with object value" },
        { value: "d", text: "This one is disabled", disabled: true },
      ],
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
  },
};
</script>
