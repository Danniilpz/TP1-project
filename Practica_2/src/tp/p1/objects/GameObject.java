
package tp.p1.objects;

import tp.p1.Game;

public abstract class GameObject {
    protected int row;
    protected int col;
    protected int live;
    protected Game game;
    protected String icono;
    protected String letter;
    
    public GameObject(int row, int col, int live, Game game) {
        this.row = row;
        this.col = col;
        this.live = live;
        this.game = game;
        this.icono="  ";
        this.letter="";
    }
    public int getLive() {
        return live;
    }
    public void setLive(int live) {
        this.live = live;
    }
    public boolean isAlive(){
        return this.live > 0;
    }
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public boolean isOut() {
        return !game.isOnBoard(row,col);
    }
    public void attack(int damage) {
        this.live-=damage;
        if(this.live<=0) {
            this.delete();
        }
    }
    public abstract void update();
    public abstract void computerAction();
    public void delete(){
        this.game.getBoard().remove(this);
    };
    public abstract GameObject parse(String linea,Game game);
    public abstract void move();
    public String toString(){
        return icono;
    };
    public String toStringStringify() {
        return this.letter+";"+this.row+";"+this.col; 
    }
}
