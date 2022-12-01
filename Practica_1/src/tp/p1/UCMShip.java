package tp.p1;

public class UCMShip {
	private int row;
	private int col;
	private int resistencia;
	private boolean misil;
	private boolean shockWave;
        private String icono;
	public UCMShip(int row, int col) {
            this.row = row;
            this.col = col;
            this.resistencia = 3;
            this.misil = false;
            this.shockWave=false;
            this.icono="^__^";
	}
        public void move(String direccion,int numero){
            switch(direccion){
                case "left":
                    if(this.col-1>=0){
                        this.col=this.col-numero;
                    }
                    break;
                case "right":
                    if(this.col+1<=8){
                        this.col=this.col+numero;
                    }
                    break;
            }
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

        public boolean isShockWave() {
            return shockWave;
        }

        public void setShockWave(boolean shockWave) {
            this.shockWave = shockWave;
        }
        
        public void setMisil(boolean misil) {
            this.misil = misil;
        }

        public boolean comprobarMisil() {
            return misil;
        }

        public String getIcono() {
            return icono;
        }

        public void setIcono(String icono) {
            this.icono = icono;
        }
        
	@Override
	public String toString() {
		return icono;
	}
	
}
