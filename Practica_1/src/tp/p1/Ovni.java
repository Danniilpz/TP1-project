package tp.p1;

import java.util.Random;

public class Ovni {
    private int col;
    private int row;
    private int resistencia;
    private int puntos;
    private boolean existe;
    private Game game;

    public Ovni(int row, int col, Game game) {
            this.row = row;
            this.col = col;
            this.resistencia = 1;
            this.puntos = 25;
            existe=true;
            this.game = game;
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

    public int getResistencia() {
            return resistencia;
    }

    public void setResistencia(int resistencia) {
            this.resistencia = resistencia;
    }

    public int getPuntos() {
            return puntos;
    }

    public void setPuntos(int puntos) {
            this.puntos = puntos;
    }

    @Override
    public String toString() {
            return "O["+this.getResistencia()+"]";
    }

    public boolean AtPosition(int i, int j) {
            if(this.getRow()==i&&this.getCol()==j){
        return true;
    }
            else {
                    return false;
            }
    }

    public void move() {
        if(existe) {
            if(col-1<0) {
                    this.eliminar();
            }
            else {
                this.col-=1;
                int bomba=game.buscaBombas(row, col);
                if(bomba!=-1){
                    this.eliminar();
                    game.eliminarDisparo(bomba);
                }
            }
        }
    }
    public void update(Random rand,double fovni) {
        if(!existe) {
            if(rand.nextDouble()<fovni) {
                this.existe=true;
                col=8;
            }
        }
    }
    public void atacar() {
        if(this.existe) {
            this.eliminar();
            game.getUcmShip().setShockWave(true);
        }
    }
    public void eliminar() {		
        this.existe=false;
        this.col = -1;		
    }

    public boolean existe() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
        
}
