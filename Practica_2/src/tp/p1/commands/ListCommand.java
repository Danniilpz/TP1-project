package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ListCommand extends Command{
	public ListCommand() {
		super("L", "list", "list: Prints the list of available ships.");

	}
	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("list") || textos[0].equals("l")))
		{
			return new ListCommand();
		}
		else { return null;}
	}
	@Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
		game.list();
		return false;
		
	}
}
