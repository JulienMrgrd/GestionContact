package domain.metier;

public class Entreprise extends Contact{
	
	private Integer numSiret;
	
	public Entreprise() { }
	
	public Entreprise(Integer numSiret){
		super();
		this.setNumSiret(numSiret);
	}

	public Integer getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}

}
