package it.uniroma3.siw.rentalev.payload.request;

public class CoinTransationRequest {
	
	private Long idPartner;
	private Long idCustomer;
	private int coin;
	
	
	public Long getIdPartner() {
		return idPartner;
	}
	public void setIdPartner(Long idPartner) {
		this.idPartner = idPartner;
	}
	public Long getIdCustomer() {
		return idCustomer;
	}
	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	
	
}
