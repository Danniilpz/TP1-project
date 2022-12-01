package tp.p1;

public class DestroyerShip {
	private int row;
	private int col;
	private int resistencia;
	private int contadorMisiles;
	private Disparo misil;
	private int puntos;
	public DestroyerShip(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.resistencia = 1;
		this.contadorMisiles = 0;
		this.puntos = 10;
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
	public int getContadorMisiles() {
		return contadorMisiles;
	}
	public void setContadorMisiles(int contadorMisiles) {
		this.contadorMisiles = contadorMisiles;
	}
	public Disparo getMisil() {
		return misil;
	}
	public void setMisil(Disparo misil) {
		this.misil = misil;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	@Override
	public String toString() {
		return "D["+this.getResistencia()+"]";
	}
}
