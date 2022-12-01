
package tp.p1.objects;

import tp.p1.Game;

public class Ovni extends EnemyShip{
    public Ovni(int row, int col, Game game) {
        super(row, col, 1, game, 25);
        this.icono="O";
        this.letter="O";
    }    

    public Ovni() {
        super(0, 0, 0, null, 0);
        this.letter="O";
    }
 
    @Override
    public void move() {
        this.col-=1;
    }
    
    @Override
    public void update() {
        if((this.col-1)<0) {
            this.delete();
        }
        else {
            this.move();
        }    	
    }

    @Override
    public void attack(int damage) {
        super.attack(damage); 
        if(!this.game.getBoard().canShockwave()) this.game.enableShockWave();
        this.game.setPuntos(this.game.getPuntos()+this.puntos);
    }

    @Override
    public void delete() {
        this.game.getBoard().remove(this);
    }
    
    @Override
    public String toString() {
        return super.toString()+"["+this.live+"]"; 
    }
    @Override
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==3 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            return new Ovni(row2,col2,game);
        }
        else { return null;}
    }
}
