
package tp.p1;

import tp.p1.objects.*;

public class BoardInitializer {
    private Level level;
    private GameObjectBoard board;
    private Game game;
    public GameObjectBoard initialize(Game game, Level level) {
        this.level = level;
        this. game = game;
        board = new GameObjectBoard(game.DIM_X, game.DIM_Y);
        initializeOvni();
        initializeRegularAliens();
        initializeDestroyerAliens();
        return board;
    }
    private void initializeOvni () {
        board.add(new Ovni(0,8,game));
    }
    private void initializeRegularAliens () {      
        for(int i=0,col=0,row=1;i<level.getRegularShips();i++,col++){    
            if(col>3) {
                col=0;
                row++;
            }
            board.add(new RegularAlien(row,col,game));
        }
    }
    private void initializeDestroyerAliens () {
        int row=((int)level.getRegularShips()/4)+1,col;
        if(level.getDestroyerShips()>2){
            col=0;            
        }
        else{
            col=1;  
        }
        for(int i=0;i<level.getDestroyerShips();i++,col++){    
            board.add(new DestroyerAlien(row,col,game));
        }
    }
}
