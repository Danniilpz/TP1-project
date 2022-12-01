package tp.p1;

import java.util.Arrays;

public class RegularShipList {
    private int direccion;
    private int contador;
    private RegularShip[] regularShips;
    private Game game;
    public RegularShipList(int regularShips,Game game) {
        this.regularShips=new RegularShip[regularShips];
        this.contador=regularShips;
        for(int i=0,col=0,row=1;i<contador;i++,col++){    
            if(col>3) {
                col=0;
                row++;
            }
            this.regularShips[i]=new RegularShip(row,col);
        }
        direccion=1; //derecha
        this.game=game;
    }
    public RegularShip getShip(int row, int col){
        for(int i=0;i<contador;i++){
            if(regularShips[i].getRow()==row&&regularShips[i].getCol()==col){
                return regularShips[i];
            }
        }
        return null;
    }
    public boolean shipAtPosition(int row, int col){
        for(int i=0;i<contador;i++){
            if(regularShips[i].getRow()==row&&regularShips[i].getCol()==col){
                return true;
            }
        }
        return false;
    }
    public void moveGroup(){        
        if((this.getMaxCol()+direccion)>8||(this.getMinCol()+direccion)<0){
            if(direccion==1) direccion=-1;
            else direccion=1;
            for(int j=0;j<contador;j++){
                regularShips[j].setRow(regularShips[j].getRow()+1);
            }
        } 
        else{
            for(int i=0;i<contador;i++){
                regularShips[i].setCol(regularShips[i].getCol()+direccion);
            }
        }
        for(int i=0;i<contador;i++){
            int bomba=game.buscaBombas(regularShips[i].getRow(), regularShips[i].getCol());
            if(bomba!=-1){
                this.restarVida(i);
                game.eliminarDisparo(bomba);
            }
        }
        
                                
        if(this.getMaxRow()>7){
            game.gameOver();
        }
        
    }
    public int getMaxRow(){
        int row=1;
        for(int i=0;i<contador;i++){
            if(row<regularShips[i].getRow()){
                row=regularShips[i].getRow();
            }
        }
        return row;
    }
    public int getMinCol(){
        int col=8;
        for(int i=0;i<contador;i++){
            if(col>regularShips[i].getCol()){
                col=regularShips[i].getCol();
            }
        }
        return col;
    }

    public int getMaxCol(){
        int col=1;
        for(int i=0;i<contador;i++){
            if(col<regularShips[i].getCol()){
                col=regularShips[i].getCol();
            }
        }
        return col;
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

    public RegularShip[] getRegularShips() {
        return regularShips;
    }

    public void setRegularShips(RegularShip[] regularShips) {
        this.regularShips = regularShips;
    }
    public int getCol(){
        return regularShips[3].getCol();
    }
    public void deleteRegular(int posicion) {
        for (int i = posicion; i < contador - 1; i++) {
                this.regularShips[i] = this.regularShips[i + 1];
        }
        this.contador--;
    	this.game.setPuntos(this.game.getPuntos()+5);
	}
    public void restarVida(int y) {
        this.regularShips[y].setResistencia(this.regularShips[y].getResistencia() - 1);
        if(this.regularShips[y].getResistencia() == 0){
            deleteRegular(y);
        }
    }
    public int buscaRegular(int fila, int columna){
        for(int i = 0; i< this.contador; i++){
                if(this.regularShips[i].getCol() == columna && this.regularShips[i].getRow() == fila){
                    return i;
                }
        }
        return -1;
    }
        
}
