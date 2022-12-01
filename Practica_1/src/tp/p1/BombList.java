package tp.p1;

import java.util.Arrays;

public class BombList {
    private Disparo[] bombas;
    private Game game;
    
    public Disparo[] getBombas() {
        return bombas;
    }
    public void setBombas(Disparo[] bombas) {
            this.bombas = bombas;
    }
    public BombList(Game game) {
        this.bombas = new Disparo[0];
        this.game=game;
    }
    public void adirBomba(Disparo bomba){
        this.bombas=Arrays.copyOf(this.bombas, this.bombas.length+1);
        this.bombas[this.bombas.length-1]=bomba;        
    }
    public boolean bombAtPosition(int row, int col){
        for(int i=0;i<bombas.length;i++){
            if(bombas[i].getRow()==row&&bombas[i].getCol()==col){
                return true;
            }
        }
        return false;
    }
    public Disparo getBomba(int row, int col){
        for(int i=0;i<bombas.length;i++){
            if(bombas[i].getRow()==row&&bombas[i].getCol()==col){
                return bombas[i];
            }
        }
        return null;
    }
    public void eliminarDisparo(int pos) {
    	if(bombas[pos].getTipo().equals("misil")){
            this.game.getUcmShip().setMisil(false);
        }
    	for(int i=pos;i<bombas.length-1;i++){             
    		this.bombas[i] = bombas[i + 1];
    	}
    	this.bombas=Arrays.copyOf(this.bombas, this.bombas.length-1);
    	
    }
    public int getCol() {
    	return bombas[0].getCol();
    }
    public int buscaBombas(int fila, int columna){
        for(int i = 0; i< bombas.length; i++){
            if(this.bombas[i].getCol() == columna && this.bombas[i].getRow() == fila){
                    return i;
            }
        }
        return -1;
    }
    public void update() {
    	for(int i = 0; i< bombas.length; i++){
            bombas[i].update();
        }
    }
    
}
