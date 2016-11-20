package domain.metier;

import javax.persistence.Version;

public class Entreprise extends Contact{
	
	private long numSiret;
	private int version;
	
	public Entreprise() { }
	
	public Entreprise(String firstName, String lastName, String email, long numSiret){
		super(firstName, lastName, email);
		this.numSiret = numSiret;
	}
	
	public Entreprise( long numSiret){
		super();
		this.numSiret = numSiret;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

	@Version
	public long getVersion() {
	    return version;
	} 
}
