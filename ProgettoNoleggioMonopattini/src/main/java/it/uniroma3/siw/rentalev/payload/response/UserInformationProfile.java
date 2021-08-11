package it.uniroma3.siw.rentalev.payload.response;

import java.util.Date;

public class UserInformationProfile {
	
	private String name;
	
	private String surname;
	
	private Long telephon;
	
	private Boolean isActive;
	
	private int walletCoin;
	
	private Long rentId;
	
	private int transationNumber;
	
	private String pIva;
	
	private Date StartPartnership;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Long getTelephon() {
		return telephon;
	}

	public void setTelephon(Long telephon) {
		this.telephon = telephon;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public int getWalletCoin() {
		return walletCoin;
	}

	public void setWalletCoin(int walletCoin) {
		this.walletCoin = walletCoin;
	}

	public Long getRentId() {
		return rentId;
	}

	public void setRentId(Long rentId) {
		this.rentId = rentId;
	}

	public int getTransationNumber() {
		return transationNumber;
	}

	public void setTransationNumber(int transationNumber) {
		this.transationNumber = transationNumber;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public Date getStartPartnership() {
		return StartPartnership;
	}

	public void setStartPartnership(Date startPartnership) {
		StartPartnership = startPartnership;
	}
	
	
}
