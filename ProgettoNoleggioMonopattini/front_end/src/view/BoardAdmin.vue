<template>
  <div class="sfondo">
    <div>
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
                  <b-badge variant="light">{{ countRent }}</b-badge>
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
                  <b-badge variant="light">{{ countHub }}</b-badge>
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
                <b-tab no-body title="Customer">
                  <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">USERNAME </b-th>
                      <b-th class="field">EMAIL</b-th>
                      <b-th class="field">NAME</b-th>
                      <b-th class="field" >SURNAME </b-th>
                      <b-th class="field">TELEPHON</b-th>
                      <b-th class="field">ADDRESS </b-th>
                      <b-th class="field">COIN </b-th>
                      <b-th class="field">RENT</b-th>
                      <b-th class="field">IS ACTIVE</b-th>
                      
                    </b-tr>
                    <b-tr
                      v-for="customer in customers"
                      v-bind:key="customer.id"
                    >
                      <b-th >{{ customer.id }}</b-th>
                      <b-th
                        >{{ customer.username }}
                      </b-th>
                      <b-th
                        >{{ customer.email }}
                      </b-th>
                      <b-th
                        >{{ customer.name }}
                      </b-th>
                      <b-th
                        >{{ customer.surname }}
                      </b-th>
                      <b-th
                        >{{ customer.telephon }}
                      </b-th>
                      <b-th
                        >{{ customer.address.street
                        }},
                        {{ customer.address.numberStreet
                        }},
                        {{ customer.address.city }}</b-th
                      >
                      <b-th>
                        <div>
                          <label for="sb-inline"></label>
                          <b-form-spinbutton
                            size="sm"
                            id="sb-inline"
                            v-model="customer.customerWallet.coin"
                            inline
                          ></b-form-spinbutton>
                          <b-button
                            size="sm"
                            squared
                            variant="outline-success"
                            @click="updateWallet(customer.customerWallet)"
                            >Aggiorna</b-button
                          >
                          <b-alert
                            v-model="showTop1"
                            class="position-fixed fixed-top m-0 rounded-0"
                            style="z-index: 2000"
                            variant="success"
                            dismissible
                          >
                            Wallet updated, please refresh
                          </b-alert>
                        </div>
                      </b-th>
                      <b-th
                        >{{ customer.rent.id
                        }}
                        {{ customer.rent.contract.plan }}</b-th
                      >
                      <b-th>
                        <b-iconstack v-if="customer.rent.ongoing == true">
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="check"></b-icon>
                        </b-iconstack>

                        <b-iconstack v-else-if="customer.rent.ongoing == false">
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="x-circle"></b-icon>
                        </b-iconstack>
                      </b-th>
                      <b-th>
                        <b-button-group>
                          <b-button
                            variant="outline-primary"
                            @click="showMsgBoxOne(customer.rent, 'dismiss')"
                          >
                            <b-icon icon="x-circle"></b-icon> Dismiss Rent
                          </b-button>
                          <b-alert
                            v-model="showTop2"
                            class="position-fixed fixed-top m-0 rounded-0"
                            style="z-index: 2000"
                            variant="success"
                            dismissible
                          >
                            Rent dismissed, please refresh
                          </b-alert>
                        </b-button-group>
                      </b-th>
                    </b-tr>
                  </table>
                </b-tab>

                <b-tab no-body title="Partner">
                  <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">USERNAME </b-th>
                      <b-th class="field">P.IVA </b-th>
                      <b-th class="field">ADDRESS</b-th>
                      <b-th class="field">COIN </b-th>
                      <b-th class="field">START PARTNERSHIP</b-th>
                      <b-th class="field">CLOSURE PARTNERSHIP</b-th>
                      <b-th class="field">HUB</b-th>                      
                    </b-tr> 
                    <b-tr
                      v-for="partner in partners"
                      v-bind:key="partner.id"
                    >
                      <b-th>{{ partner.id }}</b-th>
                      <b-th>{{ partner.username }}</b-th>
                      <b-th>{{ partner.pIva }}</b-th>
                      <b-th>{{ partner.pIva }}</b-th>
                      <b-th>{{ partner.address.street
                        }},
                        {{ partner.address.numberStreet
                        }},
                        {{ partner.address.city }}</b-th>
                      <b-th>{{ partner.partnerWallet.coin }}</b-th>
                      <b-th>{{partner.startPartnership}}</b-th>
                      <b-th v-if="partner.closurePartnership != null"> {{ partner.closurePartnership }} </b-th>
                      <b-th v-else>Partnerhip in course</b-th>
                      <b-th>{{partner.hub.id }}</b-th>
                      <b-th>
                        <div>
                           <b-button-group>
                          <b-button
                            variant="outline-primary"
                            @click="showMsgBoxTwo(partner, 'dismiss')"
                          >
                            <b-icon icon="x-circle"></b-icon> Dismiss
                            PartnerShip
                          </b-button>
                          <b-alert
                            v-model="showTop3"
                            class="position-fixed fixed-top m-0 rounded-0"
                            style="z-index: 2000"
                            variant="success"
                            dismissible
                          >
                            Partnership has been dismissed, please refresh
                          </b-alert>
                        </b-button-group> 
                        </div>   
                      </b-th>
                      
                    </b-tr>
                  </table>

                </b-tab>

                <b-tab no-body title="Rents">
                <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">START</b-th>
                      <b-th class="field">FINISH</b-th>
                      <b-th class="field">NUMBER POLICY</b-th>
                      <b-th class="field">SCOOTER</b-th>
                      <b-th class="field">PLAN</b-th>
                      <b-th class="field">ONGOING</b-th>
                    </b-tr>
                    <b-tr
                      v-for="rent in rents"
                      v-bind:key="rent.id">
                      <b-th>{{ rent.id }}</b-th>
                      <b-th>{{ rent.startRent }}</b-th>
                      <b-th v-if="rent.finishRent != null">{{ rent.finishRent }}</b-th>
                      <b-th v-else>rent in course</b-th>
                      <b-th>{{rent.numberPolicy}}</b-th>
                      <b-th>{{rent.scooter.id }}</b-th>
                      <b-th>{{rent.contract.plan}}</b-th>
                      <b-th>
                        <div>
                          <b-iconstack v-if="rent.ongoing == true">
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="check"></b-icon>
                        </b-iconstack>

                        <b-iconstack v-else-if="rent.ongoing == false">
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="x-circle"></b-icon>
                        </b-iconstack>
                        </div>
                      </b-th>
                      <b-th>
                        <div>
                          <b-button-group>
                          <b-button
                            variant="outline-primary"
                            @click="showMsgBoxOne(rent, 'dismiss')"
                          >
                            <b-icon icon="x-circle"></b-icon> Dismiss Rent
                          </b-button>
                          <b-alert
                            v-model="showTop2"
                            class="position-fixed fixed-top m-0 rounded-0"
                            style="z-index: 2000"
                            variant="success"
                            dismissible
                          >
                            Rent has been dismissed, please refresh
                          </b-alert>
                        </b-button-group>  
                        </div>
                      </b-th>
                    </b-tr>
                  </table>
                </b-tab>

                <b-tab no-body title="CoinTransations">
                    <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">FROM CUSTOMER</b-th>
                      <b-th class="field">TO PARTNER</b-th>
                      <b-th class="field">LOG CREATE</b-th>
                      <b-th class="field">COIN</b-th>
                      <b-th class="field">ENTRY SWAP</b-th>
                      <b-th class="field">EXIT SWAP</b-th>
                      <b-th class="field">COMPLETE</b-th>
                    </b-tr>
                    <b-tr
                      v-for="coinTransation in coinTransations"
                      v-bind:key="coinTransation.id"
                    >
                      <b-th>{{coinTransation.id}}</b-th>
                      <b-th>{{coinTransation.fromCustomer.username}}
                        {{ coinTransation.fromCustomer.email }}</b-th>
                      <b-th>{{coinTransation.toPartner.username}}
                        {{ coinTransation.toPartner.email }}</b-th>
                      <b-th>{{coinTransation.logTransition}}</b-th>
                      <b-th>{{coinTransation.coin }}</b-th>
                      <b-th>{{coinTransation.entrySwap.id}}</b-th>
                      <b-th v-if="coinTransation.exitSwap != null">{{ coinTransation.exitSwap.id }}</b-th>
                      <b-th v-else>CoinTransation in course</b-th>
                      <b-th>
                        <div>
                          <b-iconstack v-if="coinTransation.isComplete == true">
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="check"></b-icon>
                        </b-iconstack>

                        <b-iconstack
                          v-else-if="coinTransation.isComplete == false"
                        >
                          <b-icon stacked icon="square"></b-icon>
                          <b-icon stacked icon="x-circle"></b-icon>
                        </b-iconstack>
                        </div>
                      </b-th>
                      
                    </b-tr>
                  </table>
              </b-tab>

                <b-tab title="Utenti">
                  <table>
                    <b-tr>
                      <b-th class="field">ID</b-th>
                      <b-th class="field">USERNAME</b-th>
                      <b-th class="field">EMAIL</b-th>
                      <b-th class="field">ROLE</b-th>
                    </b-tr>
                    <b-tr
                      v-for="user in users"
                      v-bind:key="user.id"
                    >
                      <b-th>{{ user.id }}</b-th>
                      <b-th>{{ user.username }}</b-th>
                      <b-th>{{ user.email }}</b-th>
                      <b-th>
                        <div>
                          <ul>
                          <li v-for="(role, index) in user.roles" :key="index">
                            {{ role.name }}
                          </li>
                          </ul>
                        </div>
                      </b-th>
                      <b-th>
                        <div>
                          <b-button-group>
                          <b-button
                            variant="outline-primary"
                            @click="changeRole(user)"
                          >
                            <b-icon icon="x-circle"></b-icon>Change Role in
                            Partner
                          </b-button>
                          <b-alert
                            v-model="showTop4"
                            class="position-fixed fixed-top m-0 rounded-0"
                            style="z-index: 2000"
                            variant="success"
                            dismissible
                          >
                            Role has been changed, please refresh
                          </b-alert>
                        </b-button-group>
                        </div>  
                      </b-th>
                     
                    </b-tr>
                  </table>


                </b-tab>
              </b-tabs>
            </b-card>
          </div>
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
import rentService from "../services/rent.service";
import walletService from "../services/wallet.service";
import userService from "../services/user.service";

export default {
  name: "admin",
  data() {
    return {
      customers: [],
      partners: [],
      rents: [],
      users: [],
      coinTransations: [],
      countHub: 0,
      countTransation: 0,
      countRent: 0,
      showTop1: false,
      showTop2: false,
      showTop3: false,
      showTop4: false,
    };
  },
  mounted() {
    this.allCustomers();
    this.allPartners();
    this.allCoinTransation();
    this.allRents();
    this.allUsers();
  },
  updated() {},
  methods: {
    update() {
      this.$forceUpdate();
    },
    updateWallet(customerWallet) {
      console.log(customerWallet.coin);
      walletService.updateWallet(customerWallet).then((response) => {
        console.log(response.data);
        customerWallet = response.data;
        this.showTop1 = true;
      });
    },
    showMsgBoxOne(rent, dismiss) {
      this.conferm = "";
      this.$bvModal
        .msgBoxConfirm("Confermi la terminazione del noleggio?")
        .then((value) => {
          if (value) {
            console.log(value);
            this.dismissRent(rent, dismiss);
          }
        });
    },
    showMsgBoxTwo(partner, dismiss) {
      this.conferm = "";
      this.$bvModal
        .msgBoxConfirm("Confermi la fine della collaborazione?")
        .then((value) => {
          if (value) {
            console.log(value);
            this.dismissPartner(partner, dismiss);
          }
        });
    },
    dismissRent(rent, dismiss) {
      rentService.updateRent(rent, dismiss).then((response) => {
        console.log(response.data);
        rent = response.data;
        this.allRents;
        this.showTop2 = true;
      });
    },
    dismissPartner(partner, dismiss) {
      alert();
      PartnerInformationService.updatePartnerInformation(partner, dismiss).then(
        (response) => {
          console.log(response.data);
          partner = response.data;
          this.allPartners;
          this.showTop3 = true;
        }
      );
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
          this.countTransation = this.coinTransations.filter(
            (coinTransation) => coinTransation.isComplete == true
          ).length;
          console.log(this.countTransation);
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
          this.countRent = this.rents.filter(
            (rent) => rent.ongoing == true
          ).length;
          console.log(this.countRent);
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
          this.countHub = this.partners.filter(
            (partner) => partner.hub.dateOfDismiss == null
          ).length;
          console.log(this.countHub);
        },
        (error) => {
          this.content =
            (error.response && error.response.data) ||
            error.message ||
            error.toString();
        }
      );
    },
    allUsers() {
      console.log("users");
      userService.getAllUsers().then((response) => {
        console.log(response.data);
        this.users = response.data;
      });
    },
    changeRole(user) {
      userService.updateRole(user).then((response) => {
        console.log(response.data);
        this.showTop4 = true;
      });
    },
  },
};
</script>
<style scoped>
th{
  
  width: 200px;
  height: 50px;
  font-weight: normal;
  border-style: outset;
}
.field{
  font-weight: bold;
   width: 300px;
  height: 50px;
}
.sfondo{
  width: 100%;
  height: 100%;
  padding-left: 10%;
  padding-right: 10%;
}
</style>