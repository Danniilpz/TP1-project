
package tp.p1.objects;

import tp.p1.Game;

public class Bomb extends Weapon{  
    public Bomb(int row, int col, Game game) {
        super(row, col, game, 1, 1);
        this.icono=".";
        this.letter="B";
    }  

    public Bomb() {
        super(0, 0, null, 0, 0);
        this.letter="B";
    }
    
    public void update() {
    	if(game.isOnBoard(this.row+1, this.col)){
            this.move();
    	}
    	else {
            this.delete();
    	}
    	
    	
    	
   	 
    }
    public void move() {
        GameObject object=game.getBoard().getObjectInPosition(row+1, col);
        if(object==null) {
            this.row+=1;
        }
        else {
        	
        	if(game.getUcmShip().getRow()==(row + 1)&&game.getUcmShip().getCol()==col) {// si ataco a la ucmShip
                game.atacarUcmShip();
                this.delete();
	       	 }
	       	
        }
    }
    
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==3 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            return new Bomb(row2,col2,game);
        }
        else { return null;}
    }
}
