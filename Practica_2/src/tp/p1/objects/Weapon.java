
package tp.p1.objects;

import tp.p1.Game;

public class Weapon extends GameObject{
    protected int direccion;
    protected int dano;
    public Weapon(int row, int col, Game game, int direccion, int dano) {
        super(row, col, 1, game);
        this.direccion = direccion;
        this.dano=dano;
    }
    
    public  void update(){}    
    public void computerAction(){}   
    public void move(){}
    public GameObject parse(String linea,Game game) {
        return null;
    }
    
}
