<template>
  <div class="container">
    <div>
      <b-carousel
        id="carousel-1"
        :interval="4000"
        controls
        indicators
        background="#ababab"
        img-width="1024"
        img-height="350"
        style="text-shadow: 1px 1px 2px #333"
        
      >
        <!-- Text slides with image -->
        <b-carousel-slide
          caption="Noleggio a Roma"
          text="A Roma i monopattini in sharing possono circolare nelle Zone 30, con limite a 30 chilometri orari, e nelle aree pedonali, tranne dove espressamente vietato."
          img-src="images\52-1024x350.png"
        ></b-carousel-slide>

        <!-- Slides with custom text -->
        <b-carousel-slide 
        caption="Sostituzione Batterie"
        text="Nei punti forniti da questa applicazione potete trovare tutte le informazioni sui nostri Partner"
        img-src="images\51-1024x350.png">
          
        </b-carousel-slide>

        <!-- Slides with image only -->
        <b-carousel-slide
          caption="I nostri Modelli"
        text="Pronti e veloci per affrontare le strade cittadine"
        img-src="images\53-1024x350.png">
        </b-carousel-slide>

        
        
      </b-carousel>
    </div>
    <div>
      <b-container fluid class="text-center">
        <h3 class="display-2">Noleggia un Monopattino</h3>
        
         IL MIGLIOR MODO DI MUOVERSI IN CITTÀ<br>
       
            Con i nostri modelli muoverti in città sarà molto semplice, 
            in assoluta libertà e quando ne hai di bisogno. 
            I nostri Partner ti consentiranno di avere sempre le batterie cariche,
            senza preoccuparti di ricariche.
          
         </b-container>
    </div>
    <div>
      <b-container fluid class="text-center">
        <b-row class="mb-3">
          <b-col md="4" class="p-3 bg-default">
            <div>
              <b-card
                title="Partner"
                img-src="images\25-600x300.png"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem"
                class="mb-2"
              >
                <b-card-text>
                  Qui potrai avere tutte le informazioni sui nostri partner ed accedere alla nostra rete.
                </b-card-text>

                <b-button href="/infopartner" variant="dark">Approfondisci</b-button>
              </b-card>
            </div>
          </b-col>
          <b-col md="4" class="p-3 bg-default" >
            <div>
              <b-card
                title="Scooter"
                img-src="images\26-600x300.png"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem"
                class="mb-2"
              >
                <b-card-text>
                  Qui puoi vedere i nostri modelli e scegliere quelli che soddisfano le tue esigenze di mobilità.
                </b-card-text>

                <b-button href="/infoscooter" variant="dark">Approfondisci</b-button>
              </b-card>
            </div>
          </b-col>
          <b-col md="4" class="p-3 bg-default" >
            <div>
              <b-card
                title="Batterie"
                img-src="images\27-600x300.png"
                img-alt="Image"
                img-top
                tag="article"
                style="max-width: 20rem"
                class="mb-2"
              >
                <b-card-text>
                  Qui le batterie di ricambio che puoi agevolmente sostituire tramnite i nostri partner.
                </b-card-text>

                <b-button href="/infobatteries" variant="dark">Approfondisci</b-button>
              </b-card>
            </div>
          </b-col>
        </b-row>
      </b-container>
       <div>
      <b-container fluid class="text-center">
        <h3 class="display-2">I NOSTRI PARTNER</h3>
        <p class="h3">
           La mappa con l'elenco dei partner dove potrai cambiare le batterie del tuo Monopattino
          </p>
         </b-container>
    </div>
      <div >
      <AddGoogleMap />
   </div>
    </div>
  </div>
</template>

<script>
import UserService from "../services/user.service";
import AddGoogleMap from "../components/AddGoogleMap";
export default {
  name: "Home",
 
  data() {
    return {
      content: ""

    };
  },
  components:{AddGoogleMap},
  


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
<style>
#home {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #000000;
  margin-top: 50px;
}
</style>