package domain.metier;

import javax.persistence.Version;

public class Entreprise extends Contact{
	
	private long numSiret;
	private int version;
	
	public Entreprise() { }
	
	public Entreprise( Integer numSiret){
		super();
		this.numSiret = numSiret;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}

	@Version
	public long getVersion() {
	    return version;
	} 
}
