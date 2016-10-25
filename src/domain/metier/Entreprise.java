package domain.metier;

public class Entreprise extends Contact{
	
	private long id_entr;
	private long numSiret;
	private int version;
	
	public Entreprise() { }
	
	public Entreprise(long id_entr, Integer numSiret){
		super();
		this.id_entr = id_entr;
		this.numSiret = numSiret;
	}

	public long getId() {
		return id_entr;
	}

	public void setId(long id_entr) {
		this.id_entr = id_entr;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(Integer numSiret) {
		this.numSiret = numSiret;
	}

}
