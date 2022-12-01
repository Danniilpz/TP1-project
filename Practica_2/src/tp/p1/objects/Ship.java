
package tp.p1.objects;

import tp.p1.Game;

public class Ship extends GameObject{
    public Ship(int row, int col, int live, Game game) {
        super(row, col, live, game);
    }
    @Override
    public void update() {}
    @Override
    public void computerAction() {}    
    @Override
    public void move() {}    

    @Override
    public GameObject parse(String linea,Game game) {
        return null;
    }
}
