<template>
  <div class="container">
    <header class="jumbotron">
      <h3>{{content}}</h3>
    </header>
    <div>
      <div class="mb-3">
      <b-button v-b-toggle.my-collapse>Toggle Collapse</b-button>
    </div>
     <b-collapse id="my-collapse">
      <b-card title="Collapsible card">
        <add-rent/>
      </b-card>
    </b-collapse>
    </div>
  </div>
</template>

<script>
import AddRent from '../components/AddRent.vue';
import UserService from '../services/user.service';

export default {
  components: { AddRent },
  name: 'customer',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getCustomerBoard().then(
      response => {
        this.content = response.data;
      },
      error => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  }
};
</script>