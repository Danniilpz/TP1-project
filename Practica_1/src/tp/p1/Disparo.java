package tp.p1;

public class Disparo {
        private String tipo;
	private int direccion;
        private int row;
        private int col;
        private Game game;
        
	private final static int DANO=1;
	public Disparo(String tipo,int row, int col, Game game) {
            this.tipo=tipo;
            if(tipo=="misil"){
                direccion=-1;
            }
            else if(tipo=="proyectil"){
                direccion=1;
            }
            this.row=row;
            this.col=col;
            this.setGame(game);
	}

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
    
    public void update() {
        boolean eliminada = false;
        int actual = game.buscaBombas(row,col);
        if(this.tipo.equals("misil")){
            int z;   	
            int y;
            int x;
            
            boolean o;

            z = game.buscaDestroyer(row +direccion, col);	
            y = game.buscaRegular(row +direccion, col);
            x = game.buscaBombas(row +direccion,col);
            o = game.buscaOvni(row +direccion, col);

            if(row+direccion<0||row+direccion>7) {
                game.eliminarDisparo(actual);
                eliminada = true;
            }
            else {
                if(z != -1) 
                {
                        game.atacarDestroyer(z);
                        game.eliminarDisparo(actual);
                        eliminada = true;
                }
                else if(y != -1) 
                {
                        game.atacarRegular(y);
                        game.eliminarDisparo(actual);
                        eliminada = true;
                }
                else if(x != -1) 
                {				
                        game.eliminarDisparo(actual);
                        x = game.buscaBombas(row +direccion,col); //volvemos a buscarlo para actualizar su posición después de borrar el actual
                        game.eliminarDisparo(x);
                        eliminada = true;
                }
                else if(o) {
                        game.atacarOvni();
                }
            }
        }
        else if(this.tipo.equals("proyectil")){
            if(game.getUcmShip().getRow()==(row+direccion)&&game.getUcmShip().getCol()==col) {
                    game.atacarUcmShip();
                    game.eliminarDisparo(actual);
                    eliminada = true;
            }
        }
    	

        if(!eliminada) {
                this.row+=direccion;			
        }
    }


    @Override
    public String toString() {
        if(tipo.equals("misil")){
            return("oo");
        }
        else if(tipo.equals("proyectil")){
            return(".");
        }
        return " ";
    }
}
