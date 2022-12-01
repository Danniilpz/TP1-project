
package tp.p1.objects;

import java.util.*;
import tp.p1.Game;
import tp.p1.Level;

public class AlienShip extends EnemyShip{
    public static int group=0;
    public static boolean haveLanded=false;
    protected static int group_update=0;
    protected static Map<String,Integer> coordenadas;
    protected static int direccionCol=1;
    protected static int direccionRow=0;
    protected static boolean movimiento;
    public AlienShip(int row, int col, int live, Game game, int puntos) {
        super(row, col, live, game, puntos);
        this.initializeStaticValues();
        
    }
    public void initializeStaticValues(){
        if(coordenadas==null){
            coordenadas=new HashMap(); 
            if(game.getLevel()==Level.EASY){            
                coordenadas.put("maxRow", 3);
            }
            else{  
                coordenadas.put("maxRow", 4);
            }
            coordenadas.put("maxCol", 3);
            coordenadas.put("minRow", 1);
            coordenadas.put("minCol", 0);
        }
    }
    public int getMaxRow(){
        return coordenadas.get("maxRow");
    }
    public int getMaxCol(){
        return coordenadas.get("maxCol");
    }
    public int getMinRow(){
        return coordenadas.get("minRow");
    }
    public int getMinCol(){
        return coordenadas.get("minCol");
    }
    public void setMaxRow(int maxRow){
        coordenadas.put("maxRow",maxRow);
    }
    public void setMaxCol(int maxCol){
        coordenadas.put("maxCol",maxCol);
    }
    public void setMinRow(int minRow){
        coordenadas.put("minRow",minRow);
    }
    public void setMinCol(int minCol){
        coordenadas.put("minCol",minCol);
    }
    public boolean groupIsOnBoard(){
        if(direccionCol==1){
            return game.isOnBoard(this.getMaxRow(), this.getMaxCol()+1);
        }
        else if(direccionCol==-1){
            return game.isOnBoard(this.getMaxRow(), this.getMinCol()-1);
        }
        return true;
    }
    protected void updateCoordenadas(){
        if(this.col>this.getMaxCol()) this.setMaxCol(this.col);
        if(this.col<this.getMinCol()) this.setMinCol(this.col);
        if(this.row>this.getMaxRow()) this.setMaxRow(this.row);
    }

    public void actualizarMovimiento() {
    	if(game.getCiclos()%game.getLevel().getVelocidad()==0){
            movimiento=true;
        }
    	else {
    		movimiento=false;
    	}
    	
    }

    @Override
    public void attack(int damage) {
        super.attack(damage); 
        if(this.live<=0) this.game.setPuntos(this.game.getPuntos()+this.puntos);
    }
    
    @Override
    public void delete() {
        super.delete();
        group--;
    }

    @Override
    public void move() {
        if(direccionRow==0){
            this.col+=direccionCol;
        }
        else{
            this.row+=direccionRow;
        }

        group_update++;

        this.updateCoordenadas();
    }
    
    
    
    @Override
    public void update() {
        if(group_update==0){
            this.actualizarMovimiento();
            if(movimiento) {
                if(!this.groupIsOnBoard()){ 
                    if(game.isOnBoard(this.getMaxRow()+2, this.getMaxCol())){//+2 para que no pueda entrar en la fila de UCMShip
                        if(direccionCol==1){
                            direccionCol=-1;
                        } 
                        else{
                            direccionCol=1;
                        }
                        direccionRow=1;
                    }
                    else{
                        haveLanded=true;
                    }
                }                
                this.setMaxCol(this.col);
                this.setMaxRow(this.row);
                this.setMinCol(this.col);
                this.setMinRow(this.row);
            }            
        }
        if(movimiento&&!haveLanded) {
            this.move();
            if(group_update==group){
                if(direccionCol==1) this.setMinCol(this.getMinCol()+direccionCol);
                else this.setMaxCol(this.getMaxCol()+direccionCol);
                direccionRow=0;
                group_update=0;
            }
        }   
        this.computerAction();
    }
    @Override
    public String toStringStringify() {
    	return super.toStringStringify()+";"+this.live+";"+coordenadas.get("maxRow")+coordenadas.get("maxCol")+coordenadas.get("minRow")+coordenadas.get("minCol")+";"+direccionCol+";"+direccionRow;
    }
}