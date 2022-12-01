package tp.p1;

public class RegularShip {
	private int row;
	private int col;
	private int resistencia;
	private int puntos;
	
	
	public RegularShip(int row, int col) {
		this.row = row;
		this.col = col;
		this.resistencia = 2;
		this.puntos = 5;
	}


	public int getPuntos() {
		return puntos;
	}


	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}


	public int getCol() {
		return col;
	}


	public void setCol(int col) {
		this.col = col;
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getResistencia() {
		return resistencia;
	}


	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}
	
	@Override
	public String toString() {
		return "R["+this.getResistencia()+"]";
	}
}
