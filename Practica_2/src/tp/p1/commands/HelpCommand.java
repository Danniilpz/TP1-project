package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class HelpCommand extends Command{	
	public HelpCommand() {
		super("H", "help", "help: Prints this help message.");

	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("help") || textos[0].equals("h")))
		{
			return new HelpCommand();
		}
		else { return null;}
	}

	@Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
		game.help();
		return false;
		
	}

}
