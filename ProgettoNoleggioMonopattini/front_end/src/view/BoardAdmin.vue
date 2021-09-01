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
                I Noleggi Attivi sono:
                <b-badge variant="light">{{ countRentOngoing }}</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="secondary"
              text-variant="white"
              header="Numero Hub"
              class="text-center"
            >
              <b-button variant="secondary">
                Il Numero di Hub Attivi:
                <b-badge variant="light">{{ numHubActive }}</b-badge>
              </b-button>
            </b-card>

            <b-card
              bg-variant="success"
              text-variant="white"
              header="Numero Transazioni"
              class="text-center"
            >
              <b-button variant="info">
                Il Numero Transazioni:
                <b-badge variant="light">{{ countTransation }}</b-badge>
              </b-button>
            </b-card>
          </b-card-group>
        </div>

        <div>
          <b-card no-body>
            <b-tabs card>
              <b-tab no-body title="Clienti">
                <b-container class="bv-row">
                  <b-row class="my-1">
                    <b-col v-for="type in types" :key="type">
                      <label :for="`type-${type}`">
                        <code>{{ type }}</code
                        >:</label
                      >
                      <b-form-input :id="`type-${type}`"></b-form-input>
                      </b-col>
                      <b-col>
                      <label >  Active</label                      >
                      <b-form-checkbox
                        v-model="checked"
                        name="check-button"
                        switch
                      >
                        Abilitazione 
                      </b-form-checkbox>
                    </b-col>
                  </b-row>
                </b-container>

                <b-table :items="customers" striped hover> </b-table>
              </b-tab>

              <b-tab no-body title="Partner">
                <b-table striped hover :items="partners"></b-table>
              </b-tab>

              <b-tab no-body title="Rents">
                <b-table striped hover :items="rents"></b-table>
              </b-tab>

              <b-tab no-body title="Swaps">
                <b-table striped hover :items="coinTransations"></b-table>
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
import CustomerInformationService from "../services/customerInformation.service";
import PartnerInformationService from "../services/partnerInformation.service";
import RentService from "../services/rent.service";
import CoinTransationService from "../services/coinTransation.service";

export default {
  name: "admin",
  data() {
    return {
      checked: false,
      types: [
        "id",
        "Name",
        "Surname",
        "Telephon",
        "Address",
        "Customer Wallet",
        "Rent",
        "Email",
        "Username",
      ],
      customers: [],
      partners: [],
      rents: [],
      coinTransations: [],
      numHubActive: 0,
      countTransation: 0,
      countRentOngoing: 0,
      count: 0,
    };
  },
  mounted() {
    this.allCustomers();
    this.allPartners();
    this.allCounters();
    this.allCoinTransation();
    this.allRents();
  },
  methods: {
    rentOutgoing() {
      var rent;
      this.count = 0;
      for (rent in this.rents) {
        if (rent.outgoing == true) {
          this.count++;
        }
      }
      return this.count;
    },
    activeHub() {
      this.count = 0;
      var partner;
      for (partner in this.partners) {
        if (partner.isActive == true) {
          this.count++;
        }
      }
      return this.count;
    },

    allCustomers() {
      console.log("customers");
      CustomerInformationService.getAllCustomerInformations().then(
        (response) => {
          console.log(response.data);
          this.customers = response.data;
        },
        (error) => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },

    allCoinTransation() {
      console.log("coinTransation");
      CoinTransationService.getAllCoinTransations().then(
        (response) => {
          console.log(response.data);
          this.coinTransations = response.data;
        },
        (error) => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    allRents() {
      console.log("rents");
      RentService.getAllRents().then(
        (response) => {
          console.log(response.data);
          this.rents = response.data;
        },
        (error) => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    allPartners() {
      console.log("partners");
      PartnerInformationService.getAllPartnerInformations().then(
        (response) => {
          console.log(response.data);
          this.partners = response.data;
        },
        (error) => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    allCounters() {
      this.countTransation = this.coinTransations.length;
      this.countRentOngoing = this.rentOutgoing();
      this.numHubActive = this.activeHub();
    },
  },
};
</script>