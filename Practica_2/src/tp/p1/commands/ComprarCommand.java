
package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ComprarCommand extends Command{
    public ComprarCommand() {
        super("C", "comprar", "comprar: Permite comprar SuperMissile por 20 puntos.");

    }

    public static Command parse(String s) throws CommandParseException{
        String[] textos = s.toLowerCase().split(" ");
        if(textos.length ==2 && (textos[0].equals("comprar") || textos[0].equals("c"))&&textos[1].equals("supermisil"))
        {
                return new ComprarCommand();
        }
        else { return null;}
    }

    @Override
    public boolean execute(Game game, Controller controller) throws CommandExecuteException{
        game.comprarSuperMisil();
        return false;

    }
}
