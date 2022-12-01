
package tp.p1.objects;

import tp.p1.Game;


public class UCMSuperMissile extends UCMMissile{
    
    public UCMSuperMissile(int row, int col, Game game) {
        super(row, col, game);
        this.dano=2;
        this.letter="X";
    }

    public UCMSuperMissile() {
        this.letter="X";
    }
    
    public GameObject parse(String linea,Game game) {
        String[] textos = linea.split(";");
        if(textos.length ==3 && (textos[0].equals(this.letter)))
        {
            int row2=Integer.parseInt(textos[1]);
            int col2=Integer.parseInt(textos[2]);
            return new UCMSuperMissile(row2,col2,game);
        }
        else { return null;}
    }
}
