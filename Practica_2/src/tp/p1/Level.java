package tp.p1;

public enum Level {
	EASY(4,2,0.1,3,0.5),
	HARD(8,2,0.3,2,0.2), 
	INSANE(8,4,0.5,1,0.1);
	
	private int regularShips,destroyerShips,velocidad;
	private double fdisparo,fovni;

	private Level(int regularShips, int destroyerShips, double fdisparo, int velocidad, double fovni) {
		this.regularShips = regularShips;
		this.destroyerShips = destroyerShips;
		this.velocidad = velocidad;
		this.fdisparo = fdisparo;
		this.fovni = fovni;
	}

	public int getRegularShips() {
		return regularShips;
	}

	public void setRegularShips(int regularShips) {
		this.regularShips = regularShips;
	}

	public int getDestroyerShips() {
		return destroyerShips;
	}

	public void setDestroyerShips(int destroyerShips) {
		this.destroyerShips = destroyerShips;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public double getFdisparo() {
		return fdisparo;
	}

	public void setFdisparo(double fdisparo) {
		this.fdisparo = fdisparo;
	}

	public double getFovni() {
		return fovni;
	}

	public void setFovni(double fovni) {
		this.fovni = fovni;
	}
	
	
}
