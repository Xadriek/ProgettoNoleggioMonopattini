<template>
  <div class="container">
   Info Batterie
  </div>
</template>

<script>
import UserService from "../services/user.service";

export default {
  name: "InfoBatteries",
 
  data() {
    return {
      content: ""

    };
  },
  //components:{AddGoogleMap},
  
  mounted() {
    UserService.getPublicContent().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
};
</script>
