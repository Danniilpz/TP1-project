
package tp.p1.objects;

import tp.p1.Game;

public class UCMMissile extends Weapon{    
    public UCMMissile(int row, int col, Game game) {
        super(row, col, game, -1, 1);
        this.icono="oo";
        this.letter="M";
    }    

    public UCMMissile() {
        super(0, 0, null, 0, 0);
        this.letter="M";
    }

    @Override
    public void move() {
        GameObject object=game.getBoard().getObjectInPosition(row-1, col);
        if(object==null) {
            this.row-=1;
        }
        else {
            if(!object.getClass().getName().equals("UCMShip")) {
                this.delete();
                object.attack(this.dano);                
            }
        }
    }

    @Override
    public void delete() {
        super.delete(); 
        this.game.enableMissile();
    }
    
    @Override
    public void update() {    	
    	if(game.isOnBoard(this.row-1, this.col)){
            this.move();
    	}
    	else {
            this.delete();
    	}
    	
    }
    @Override
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==3 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            return new UCMMissile(row2,col2,game);
        }
        else { return null;}
    }
}
