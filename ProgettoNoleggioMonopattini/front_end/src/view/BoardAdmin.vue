<template>
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
                <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item  >id</b-list-group-item>
                      <b-list-group-item>Username   </b-list-group-item>
                      <b-list-group-item>Email</b-list-group-item>
                      <b-list-group-item>Name</b-list-group-item>
                      <b-list-group-item>Surname     </b-list-group-item>
                      <b-list-group-item>Telephon</b-list-group-item>
                      <b-list-group-item>Address    </b-list-group-item>
                      <b-list-group-item>Coin       </b-list-group-item>
                      <b-list-group-item>Rent</b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="customer in customers"
                    v-bind:key="customer.id"
                    >
                      <b-list-group-item>{{customer.id}}</b-list-group-item>
                      <b-list-group-item>{{customer.username}}   </b-list-group-item>
                      <b-list-group-item>{{customer.email}}     </b-list-group-item>
                      <b-list-group-item>{{customer.name}}     </b-list-group-item>
                      <b-list-group-item>{{customer.surname}}     </b-list-group-item>
                      <b-list-group-item>{{customer.telephon}}     </b-list-group-item>
                      <b-list-group-item>{{customer.address.street}}{{customer.address.numberStreet}}{{customer.address.city}}</b-list-group-item>
                      <b-list-group-item>{{customer.customerWallet.coin}}       </b-list-group-item>
                      <b-list-group-item>{{customer.rent.id}}{{customer.rent.contract.plan}}</b-list-group-item>
                      <b-list-group-item >
                           <b-iconstack v-if="customer.rent.ongoing==true" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="check"></b-icon>
                                </b-iconstack>
                      
                           <b-iconstack v-else-if="customer.rent.ongoing==false" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="x-circle"></b-icon>
                                </b-iconstack>
                      </b-list-group-item>
                      <b-list-group-item>
                        <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxOne(customer.rent,'dismiss')">
                          <b-icon icon="x-circle"></b-icon> Dismiss Rent
                             </b-button>
                             </b-button-group>
                      </b-list-group-item>
                    </b-list-group>
                </b-list-group>
              </b-tab>

              <b-tab no-body title="Partner">
                <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item  >id</b-list-group-item>
                      <b-list-group-item>Username   </b-list-group-item>
                      <b-list-group-item>P.Iva      </b-list-group-item>
                      <b-list-group-item>Address    </b-list-group-item>
                      <b-list-group-item>Coin       </b-list-group-item>
                      <b-list-group-item>StartPartnership</b-list-group-item>
                      <b-list-group-item>ClosurePartnership</b-list-group-item>
                      <b-list-group-item>Hub</b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="partner in partners"
                    v-bind:key="partner.id"
                    >
                      <b-list-group-item>{{partner.id}}</b-list-group-item>
                      <b-list-group-item>{{partner.username}}   </b-list-group-item>
                      <b-list-group-item>{{partner.pIva}}     </b-list-group-item>
                      <b-list-group-item>{{partner.address.street}}{{partner.address.numberStreet}}{{partner.address.city}}</b-list-group-item>
                      <b-list-group-item>{{partner.partnerWallet.coin}}       </b-list-group-item>
                      <b-list-group-item>{{partner.startPartnership}}</b-list-group-item>
                      <b-list-group-item v-if="partner.closurePartnership!=null">{{partner.closurePartnership}} </b-list-group-item>
                      <b-list-group-item v-else>Partnerhip in course</b-list-group-item>
                      <b-list-group-item>{{partner.hub.id}}</b-list-group-item>
                      <b-list-group-item>
                        <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxTwo(partner,'dismiss')">
                          <b-icon icon="x-circle"></b-icon> Dismiss PartnerShip
                             </b-button>
                             </b-button-group>
                      </b-list-group-item>
                      
                    </b-list-group>
                </b-list-group>
              </b-tab>

              <b-tab no-body title="Rents">
                 <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item  >id</b-list-group-item>
                      <b-list-group-item>Start    </b-list-group-item>
                      <b-list-group-item>Finish     </b-list-group-item>
                      <b-list-group-item>Number Policy    </b-list-group-item>
                      <b-list-group-item>Scooter       </b-list-group-item>
                      <b-list-group-item>Plan</b-list-group-item>
                      <b-list-group-item>Ongoing</b-list-group-item>
                      
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="rent in rents"
                    v-bind:key="rent.id"
                    >
                      <b-list-group-item>{{rent.id}}</b-list-group-item>
                      <b-list-group-item>{{rent.startRent}}   </b-list-group-item>
                      <b-list-group-item v-if="rent.finishRent!=null">{{rent.finishRent}} </b-list-group-item>
                      <b-list-group-item v-else>rent in course</b-list-group-item>
                      <b-list-group-item>{{rent.numberPolicy}}</b-list-group-item>
                      <b-list-group-item>{{rent.scooter.id}}       </b-list-group-item>
                      <b-list-group-item>{{rent.contract.plan}}</b-list-group-item>
                      <b-list-group-item >
                           <b-iconstack v-if="rent.ongoing==true" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="check"></b-icon>
                                </b-iconstack>
                      
                           <b-iconstack v-else-if="rent.ongoing==false" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="x-circle"></b-icon>
                                </b-iconstack>
                      </b-list-group-item>
                      <b-list-group-item>
                        <b-button-group>
                             <b-button variant="outline-primary" @click="showMsgBoxOne(rent,'dismiss')">
                          <b-icon icon="x-circle"></b-icon> Dismiss Rent
                             </b-button>
                             </b-button-group>
                      </b-list-group-item>
                    </b-list-group>
                </b-list-group>
                  
                
              </b-tab>

              <b-tab no-body title="CoinTransations">
                <b-list-group >
                    <b-list-group horizontal="md"
                     fluid="md"
                    active 
                  >
                      <b-list-group-item>id</b-list-group-item>
                      <b-list-group-item>From Customer   </b-list-group-item>
                      <b-list-group-item>To Partner      </b-list-group-item>
                      <b-list-group-item>Log create    </b-list-group-item>
                      <b-list-group-item>Coin       </b-list-group-item>
                      <b-list-group-item>EntrySwap</b-list-group-item>
                      <b-list-group-item>ExitSwap</b-list-group-item>
                      <b-list-group-item>Complete</b-list-group-item>
                    </b-list-group>
                    <b-list-group horizontal="md"
                     fluid="md"
                    active
                    
                    v-for="coinTransation in coinTransations"
                    v-bind:key="coinTransation.id"
                    >
                      <b-list-group-item>{{coinTransation.id}}</b-list-group-item>
                      <b-list-group-item>{{coinTransation.fromCustomer.username}}{{coinTransation.fromCustomer.email}} </b-list-group-item>
                      <b-list-group-item>{{coinTransation.toPartner.username}}{{coinTransation.toPartner.email}}     </b-list-group-item>
                      <b-list-group-item>{{coinTransation.logTransition}}</b-list-group-item>
                      <b-list-group-item>{{coinTransation.coin}}       </b-list-group-item>
                      <b-list-group-item>{{coinTransation.entrySwap.id}}</b-list-group-item>
                       <b-list-group-item v-if="coinTransation.exitSwap!=null">{{coinTransation.exitSwap.id}} </b-list-group-item>
                      <b-list-group-item v-else>CoinTransation in course</b-list-group-item>
                      <b-list-group-item >
                           <b-iconstack v-if="coinTransation.isComplete==true" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="check"></b-icon>
                                </b-iconstack>
                      
                           <b-iconstack v-else-if="coinTransation.isComplete==false" >
                                <b-icon stacked icon="square"></b-icon>
                                <b-icon stacked icon="x-circle"></b-icon>
                                </b-iconstack>
                      </b-list-group-item>
                    </b-list-group>
                </b-list-group>
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
import rentService from '../services/rent.service';

export default {
  name: "admin",
  data() {
    return {
      
      customers: [],
      partners: [],
      rents: [],
      coinTransations: [],
      countHub: 0,
      countTransation: 0,
      countRent: 0,
    };
  },
  mounted() {
    this.allCustomers();
    this.allPartners();
    this.allCoinTransation();
    this.allRents();
  },
  updated() {
    
  },
  methods: {
    update(){
      this.$forceUpdate();
    },
    showMsgBoxOne(rent,dismiss) {
        this.conferm = ''
        this.$bvModal.msgBoxConfirm('Confermi la terminazione del noleggio?')
          .then(value => { 
            if(value){
              console.log(value);
              this.dismissRent(rent,dismiss);
            }
          })
         
    },
    showMsgBoxTwo(partner,dismiss) {
        this.conferm = ''
        this.$bvModal.msgBoxConfirm('Confermi la fine della collaborazione?')
          .then(value => { 
            if(value){
              console.log(value);
              this.dismissPartner(partner,dismiss);
            }
          })
         
    },
    dismissRent(rent,dismiss){
      rentService.updateRent(rent,dismiss).then(
        response=>{
          console.log(response.data);
          rent=response.data;
          this.allRents;
        }
      )
    },
    dismissPartner(partner,dismiss){
      alert();
      PartnerInformationService.updatePartnerInformation(partner,dismiss).then(
        response=>{
          console.log(response.data);
          partner=response.data;
          this.allPartners;

        }
      )
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
          this.countTransation=this.coinTransations.filter(coinTransation=>coinTransation.isComplete==true).length;
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
          this.countRent=this.rents.filter(rent=>rent.ongoing==true).length;
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
          this.countHub=this.partners.filter(partner=>partner.hub.dateOfDismiss==null).length;
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
  },
};
</script>