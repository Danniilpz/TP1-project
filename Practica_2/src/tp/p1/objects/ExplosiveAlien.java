
package tp.p1.objects;

import tp.p1.Game;

public class ExplosiveAlien extends RegularAlien{
    
    public ExplosiveAlien(int row, int col, Game game) {
        super(row, col, game);
        this.icono="E";
        this.letter="E";
    }

    public ExplosiveAlien() {
        super(0, 0, null);
        group--; //Para que no sume el constructor del padre
        this.letter="E";
    }
    
    @Override
    public void computerAction() {}
    
    @Override
    public void delete() {
        super.delete(); 
        this.game.attackAdjacent(this.row,this.col);
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
            ExplosiveAlien o=new ExplosiveAlien(row2,col2,game);
            o.setLive(live2);
            return o;
        }
        else { return null;}
    }
    
}
