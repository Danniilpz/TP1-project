package tp.p1.printer;

import tp.p1.Game;
import tp.p1.GameObjectBoard;
import tp.p1.objects.GameObject;

public class StringifierPrinter extends GamePrinter{	
    private Game game;
    private GameObjectBoard board;
    public StringifierPrinter(Game game) {
        this.game = game;
        this.board=game.getBoard();
    } 
    @Override
    public String toString() {
        String texto="";
        texto+=("— Space Invaders v2.0 —\n\n");
        texto+="G;" + game.getCiclos()+"\n";//poner numero de ciclos
        texto+="L;" + game.getLevel().name()+"\n";//poner el nivel
        for(GameObject object:board.getObjects()){
            if(!object.getClass().getName().equals("tp.p1.objects.Shockwave")) texto+=object.toStringStringify()+"\n";
        }
        return texto;
    }
}
