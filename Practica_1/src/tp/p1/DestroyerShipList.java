package tp.p1;

import java.util.Random;

public class DestroyerShipList {
    private int direccion;
    private int contador;
    private DestroyerShip[] destroyerShips;
    private Game game;
    
	public DestroyerShipList(int destroyerShips,Game game) {
            this.destroyerShips=new DestroyerShip[destroyerShips];
            this.contador=destroyerShips;
            int row=((int)game.getRegularShipList().getRegularShips().length/4)+1,col;
            if(contador>2){
                col=0;
            }
            else{
                col=1;
            }
            for(int i=0;i<contador;i++,col++){    
                this.destroyerShips[i]=new DestroyerShip(row,col);

            }
            this.game=game;
	}
        public DestroyerShip getShip(int row, int col){
            for(int i=0;i<contador;i++){
                if(destroyerShips[i].getRow()==row&&destroyerShips[i].getCol()==col){
                    return destroyerShips[i];
                }
            }
            return null;
        }
        public boolean shipAtPosition(int row, int col){
            for(int i=0;i<contador;i++){
                if(destroyerShips[i].getRow()==row && destroyerShips[i].getCol()==col){
                    return true;
                }
            }
            return false;
        }
        public int getRow(){
            return destroyerShips[0].getRow();
        }

        public int getMaxRow(){
            int row=1;
            for(int i=0;i<contador;i++){
                if(row<destroyerShips[i].getRow()){
                    row=destroyerShips[i].getRow();
                }
            }
            return row;
        }
        public void moveGroup(){            
            direccion=game.getRegularShipList().getDireccion();
            int regularShipRow=game.getRegularShipList().getMaxRow();
            if(regularShipRow==this.getRow()){
                for(int i=0;i<contador;i++){
                    destroyerShips[i].setRow(destroyerShips[i].getRow()+1);
                }
            }
            else{
               for(int i=0;i<contador;i++){
                   destroyerShips[i].setCol(destroyerShips[i].getCol()+direccion);
                } 
            }   
            for(int i=0;i<contador;i++){
                int bomba=game.buscaBombas(destroyerShips[i].getRow(), destroyerShips[i].getCol());
                if(bomba!=-1){
                    this.restarVida(i);
                    game.eliminarDisparo(bomba);
                }
            }
            if(this.getMaxRow()>=7){
                game.gameOver();
            }         
        }

    public int getDireccion() {
        return direccion;
    }

    public void setDireccion(int direccion) {
        this.direccion = direccion;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public DestroyerShip[] getDestroyerShips() {
        return destroyerShips;
    }

    public void setDestroyerShips(DestroyerShip[] destroyerShips) {
        this.destroyerShips = destroyerShips;
    }

    public void deleteDestroyer(int posicion) {
        for (int i = posicion; i < contador - 1; i ++) {
                this.destroyerShips[i] = this.destroyerShips[i + 1];
        }
        this.contador--;
        this.game.setPuntos(this.game.getPuntos()+10);
	}
    public void restarVida(int z) {
        this.destroyerShips[z].setResistencia(this.destroyerShips[z].getResistencia() - 1);
        if(this.destroyerShips[z].getResistencia() == 0){
                deleteDestroyer(z);
        }
    }
    public int buscaDestroyer(int fila, int columna){
        for(int i = 0; i< this.contador; i++){
            if(this.destroyerShips[i].getCol() == columna && this.destroyerShips[i].getRow() == fila){
                return i;
            }
        }
        return -1;
	}
    public void update(Random rand,double fdisparo) {
    	for(int i=0;i<contador;i++){
            if(rand.nextDouble()<fdisparo) {
                game.getBombList().adirBomba(new Disparo("proyectil",destroyerShips[i].getRow(),destroyerShips[i].getCol(), game));
            }
        }
        
    }
}
