
package tp.p1.objects;

import tp.p1.Game;

public class Shockwave extends Weapon{  
    
    public Shockwave(int row, int col, Game game) {
        super(row, col, game, 0, 1);
        this.letter="W";
    }  

    public Shockwave() {
        super(0, 0, null, 0, 0);
        this.letter="W";
    }
}
