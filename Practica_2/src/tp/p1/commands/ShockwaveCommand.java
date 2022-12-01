package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ShockwaveCommand extends Command{
	public ShockwaveCommand() {
		super("W", "Shockwave", "shockWave: UCM-Ship releases a shock wave.");
	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("shockwave") || textos[0].equals("w")))
		{
			return new ShockwaveCommand();
		}
		else { return null;}
	}
	
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
		game.shockWave();  
		return true;
		
	}
}
