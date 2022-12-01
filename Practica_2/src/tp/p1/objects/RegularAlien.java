
package tp.p1.objects;

import tp.p1.Game;

public class RegularAlien extends AlienShip{    
    public RegularAlien(int row, int col, Game game) {
        super(row, col, 2, game, 5);
        this.icono="R";
        group++;
        this.letter="R";
    }  

    public RegularAlien() {
        super(0, 0, 0, null, 0);
        this.letter="R";
    }

    @Override
    public void computerAction() {
        super.computerAction(); 
        if(game.getRand().nextInt(20)==0) { //  1/20(5%) de probabilidad
            ExplosiveAlien ea=new ExplosiveAlien(this.row,this.col,this.game);
            ea.setLive(live);
            this.game.addObject(ea);
            this.delete();
        }
    }

    
    @Override
    public String toString() {
        return super.toString()+"["+this.live+"]"; 
    }

    @Override
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
            RegularAlien o=new RegularAlien(row2,col2,game);
            o.setLive(live2);
            return o;
        }
        else { return null;}
    }
}
