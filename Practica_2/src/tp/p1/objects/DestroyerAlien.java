
package tp.p1.objects;

import tp.p1.Game;

public class DestroyerAlien extends AlienShip{    
    public DestroyerAlien(int row, int col, Game game) {
        super(row, col, 1, game, 10);
        this.icono="D";
        group++;
        this.letter="D";
    }   

    public DestroyerAlien() {
        super(0, 0, 0, null, 0);
        this.letter="D";
    }

    public void computerAction() {     	
    	if(game.getRand().nextDouble()<game.getLevel().getFdisparo()) {// metodo que comprueba si tiene que disparar aleatoriamente
            game.addObject(new Bomb(this.row + 1, this.col, game));
        }
    }
    
    
    public String toString() {
        return super.toString()+"["+this.live+"]"; 
    }
    
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==7 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            int live2=Integer.parseInt(textos[3]);
            String coordenadas2=textos[4];
            coordenadas.put("maxRow", Integer.parseInt(coordenadas2.substring(0,1)));
            coordenadas.put("maxCol", Integer.parseInt(coordenadas2.substring(1,2)));
            coordenadas.put("minRow", Integer.parseInt(coordenadas2.substring(2,3)));
            coordenadas.put("minCol", Integer.parseInt(coordenadas2.substring(3)));
            direccionCol=Integer.parseInt(textos[5]);
            direccionRow=Integer.parseInt(textos[6]);
            DestroyerAlien o=new DestroyerAlien(row2,col2,game);
            o.setLive(live2);
            return o;
        }
        else { return null;}
    }
}
