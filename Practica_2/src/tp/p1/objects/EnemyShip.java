
package tp.p1.objects;

import tp.p1.Game;

public class EnemyShip extends Ship{
    protected int puntos;
    public EnemyShip(int row, int col, int live, Game game, int puntos) {
        super(row, col, live, game);
        this.puntos=puntos;
    }

}
