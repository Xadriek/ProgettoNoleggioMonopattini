<template>
  <div class="container">
    <header class="jumbotron">
        <h3>{{content}}</h3>
    </header>

    <div class="container-fluid text-center"> 

      <center>
        <img src="/images/homelogotitolo.png" with=50%>
      </center>

    <h1 class="text-center">Benvenuti al FoxRental </h1>
    <hr>
    <div class="row content">
    <div class="col-sm-2 sidenav">
      <p><a href="#lanostraazienda">La Nostra Azienda</a></p>
      <p><a href="#iservizi">I Servizi</a></p>
      <p><a href="#ipianitariffari">I Piani Tariffati</a></p>
      <p><a href="#icontatti">I contatti</a></p>
    </div>

    <div class="col-sm-8 text-left"> 
      <p>
      <p>   
      <a name="lanostraazienda"><h2>La nosta azienda </h2></a>
      
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <hr>
      <h3>Test</h3>
      <p>Lorem ipsum...</p>
    
      <p>
      <p>   
      <a name="iservizi"><h2>I Nostri Servizi </h2></a>
      <p>
      <h3>Le Nostre Batterie</h3>  
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <h3>I Nostri Mezzi</h3>  
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <center>
        <img src="/images/inostrimezzi.png" with=50%>
      </center>
      <p>
      <hr>
      <a name="ipianitariffari"><h2>I Piani Tariffati </h2></a>
      
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
      <hr>
       <center>
        <img src="/images/tariffe.png" with=50%>
      </center>
      <p>Lorem ipsum...</p>
    

      <p>
      <p>   
      <a name="icontatti"><h2>Riferimenti</h2></a>
       <br>
      <center>
      <button @click="showModal = true" class="button" >Dove Siamo</button> 
      <button @click="showModal = true" class="button" >Contattaci</button>
      <button @click="showModal = true" class="button" >Parner Swap-Point</button>
      </center>
    <hr>
          <h3>Test</h3>
      <p>Lorem ipsum...</p>



    </div>

    <hr>
    <hr>
    <hr>
  </div>
</div>
  </div>
</template>

<script>
import UserService from '../services/user.service';

export default {
  name: 'Home',
  data() {
    return {
      content: ''
    };
  },
  mounted() {
    UserService.getPublicContent().then(
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