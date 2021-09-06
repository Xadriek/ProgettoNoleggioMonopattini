package it.uniroma3.siw.rentalev.payload.request;

public class CoinTransationRequest {
	
	private String idPartner;
	private String idCustomer;
	private int coin;
	
	
	public String getIdPartner() {
		return idPartner;
	}
	public void setIdPartner(String idPartner) {
		this.idPartner = idPartner;
	}
	public String getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(String idCustomer) {
		this.idCustomer = idCustomer;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	
}
