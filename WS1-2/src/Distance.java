
public class Distance {
	
	private double kilometres;
	
	public Distance (double km) {
		kilometres = km;
	}

	public double getKilometres() {
		return kilometres;
	}
	
	public double getMiles() {
		return kilometres / 1.60934;
	}
	
	public double getMetres() {
		return kilometres / 0.001;
	}
	
	public double getYards() {
		return (kilometres / 1.60934) * 1760;
	}

}
