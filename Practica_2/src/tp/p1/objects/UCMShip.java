
package tp.p1.objects;

import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;

public class UCMShip extends Ship{  
    public UCMShip(int row, int col, Game game) {
        super(row, col, 3, game);
        this.icono="^__^";
        this.letter="P";
    }     
    public UCMShip() {
        super(0, 0, 0, null);
        this.letter="P";
    }    
    public void move(String direccion, int numero)  throws CommandExecuteException{
        switch(direccion){
            case "left":
                if(this.col-1>=0){
                    this.col=this.col-numero;
                }
                else {
                	throw new CommandExecuteException("fuera de posicion");
                }
                break;
            case "right":
                if(this.col+1<=8){
                    this.col=this.col+numero;
                }
                else {
                	throw new CommandExecuteException("fuera de posicion");
                }
                break;
        }
    }
    public void update() {}
    
    public void attack(int damage) {
    	this.live-=damage;
    	if(this.live==0) {
    		icono="!xx!";
    		this.game.draw();
    	}
    }
     
    public String toStringStringify() {
    	return super.toStringStringify()+";"+this.live+";"+game.getPuntos()+";"+game.getBoard().canShockwave()+";"+game.getBoard().getSuperMisiles();
    }

    
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==7 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            int live2=Integer.parseInt(textos[3]);
            game.setPuntos(Integer.parseInt(textos[4]));
            if(Boolean.parseBoolean(textos[5])==true) game.enableShockWave();
            game.getBoard().setSuperMisiles(Integer.parseInt(textos[6]));
            UCMShip o=new UCMShip(row2,col2,game);
            o.setLive(live2);
            return o;
        }
        else { return null;}
    }
    
}
