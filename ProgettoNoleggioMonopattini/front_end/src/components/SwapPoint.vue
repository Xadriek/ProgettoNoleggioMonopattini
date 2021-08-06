<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-list-group>
        <b-list-group-item>Cras justo odio</b-list-group-item>
        <b-list-group-item active>Dapibus ac facilisis in</b-list-group-item>
        <b-list-group-item>Morbi leo risus</b-list-group-item>
        <b-list-group-item>Porta ac consectetur ac</b-list-group-item>
        <b-list-group-item>Vestibulum at eros</b-list-group-item>
      </b-list-group>

      <b-button type="submit" variant="primary">Conferma</b-button>
      <b-button type="reset" variant="danger">Annulla</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  name: "SwapPoint",
  data() {
    return {
      form: {
        
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
    // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    },
  },
};
</script>
