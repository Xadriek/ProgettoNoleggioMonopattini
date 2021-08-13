<template>
  <div class="container">
   

    <div>
      <div>
        <div>
          <b-card-group deck>
            <b-card
              bg-variant="primary"
              text-variant="white"
              header="Numero Noleggi"
              class="text-center"
            >
              <b-button variant="primary">
                I Noleggi Attivi sono: <b-badge variant="light">{{countRentOngoing}}</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="secondary"
              text-variant="white"
              header="Numero Hub"
              class="text-center"
            >
              <b-button variant="secondary">
                Il Numero di Hub Attivi: <b-badge variant="light">{{numHubActive}}</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="success"
              text-variant="white"
              header="Numero Transazioni"
              class="text-center"
            >
              <b-button variant="info">
                Il Numero Transazioni: <b-badge variant="light">{{countTransation}}</b-badge>
              </b-button>
            </b-card>
          </b-card-group>
        </div>

       <div>
          <b-card no-body>
            <b-tabs card>
              <b-tab no-body title="Clienti">
                <b-table
                  striped
                  hover
                  :items="customers"
                  
                ></b-table>
              </b-tab>

            <b-tab no-body title="Partner">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  
                ></b-table>
              </b-tab>

              
              <b-tab no-body title="Swap">
                <b-table
                  striped
                  hover
                  :items="listaswappoint"
                  
                ></b-table>
              </b-tab>
              

              <b-tab title="Aiuto">
                <b-card-text>
                  Qui scriviamo un minimo di spiegazione che può essere utile se
                  vuoi
                </b-card-text>
              </b-tab>
            </b-tabs>
          </b-card>
        </div>

        
      </div>
    </div>
  </div>
</template>

<script>
import CustomerInformationService from '../services/customerInformation.service';
import PartnerInformationService from '../services/partnerInformation.service';

import CoinTransationService from '../services/coinTransation.service';



export default {
  name: "admin",
  data() {
    return {

      text: "This is some text.\nIt is read only and doesn't look like an input.",
      listaswappoint: [
        { distanza: 40, name: "Dickerson", indirizzo: "Via Roma" },
        { distanza: 21, name: "Larsen", indirizzo: "Viale Marconi" },
        { distanza: 89, name: "Geneva", indirizzo: "Piazza Verdi" },
        { distanza: 38, name: "Jami", indirizzo: "Colosseo" },
      ],
      customers:[],
      partners:[],
      coinTransations:[],
      numHubActive:0,
      countTransation:0,
      countRentOngoing:0,
      count:0
    };
  },
  mounted() {
    
    
    this.allCustomers();
    this.allPartners();
    this.allCounters();
    this.allCoinTransation();
    
  },
  methods:{
    rentOutgoing(){
      var rent;
      this.count=0;
      for(rent in this.rents){
        if(rent.outgoing==true){
          this.count++;
        }
      }
      return this.count;
    },
    activeHub(){
      this.count=0;
       var partner;
      for(partner in this.partners){
        
        if(partner.isActive==true){
          this.count++;
        }
    }
    return this.count;
  },
  
  allCustomers(){
    console.log('customers');
    CustomerInformationService.getAllCustomerInformations().then(
      (response)=>{
        console.log(response.data);
        this.customers=response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  
  allCoinTransation(){
    console.log('coinTransation');
    CoinTransationService.getAllCoinTransations().then(
      (response)=>{
        console.log(response.data);
        this.coinTransations=response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
    },
  
  allPartners(){
    console.log('partners');
    PartnerInformationService.getAllPartnerInformations().then(
      (response)=>{
        console.log(response.data);
        this.partners=response.data;
      },
      (error) => {
        this.content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();
      }
    );
  },
  allCounters(){
    this.countTransation=this.coinTransations.length;
    this.countRentOngoing=this.rentOutgoing();
    this.numHubActive=this.activeHub();
  }
  }
};
</script>