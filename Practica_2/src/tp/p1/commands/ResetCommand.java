package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ResetCommand extends Command{
	public ResetCommand() {
		super("R", "reset", "reset: Starts a new game.");
	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("reset") || textos[0].equals("r")))
		{
			return new ResetCommand();
		}
		else { return null;}
	}
	
	public boolean execute(Game game, Controller controller) throws CommandExecuteException {
		game.reset(); 
		return false;
		
	}
}
