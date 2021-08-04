export default class CoinTransation{
    constructor(fromCustomer,toPartner,coin,swap){
        this.fromCustomer=fromCustomer;
        this.toPartner=toPartner;
        this.coin=coin;
        this.swap=swap;
    }
}