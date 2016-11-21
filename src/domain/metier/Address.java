package domain.metier;

import javax.persistence.Version;

public class Address {
	
	private long id_add;
	private String street;
	private String city;
	private String zip;
	private String country;
	private int version;
	
	public Address() { }
	
	public Address(long id_add, String street, String city, String zip, String country) {
		super();
		this.id_add = id_add;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}
	
	public Address(String street, String city, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.country = country;
	}

	public long getId() {
		return id_add;
	}

	public void setId(long id_add) {
		this.id_add = id_add;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Version
	public long getVersion() {
	    return version;
	} 
}
