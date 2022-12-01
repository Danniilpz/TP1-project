package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.printer.StringifierPrinter;

public class StringifyCommand extends Command{
	
	
	public StringifyCommand() {
		super("B", "serialize", "serialize: serialize the game.");
	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("b") || textos[0].equals("serialize")))
		{
			return new StringifyCommand();
		}
		else {return null;}
	}
	
	public boolean execute(Game game, Controller controller)throws CommandExecuteException {
		StringifierPrinter printer=new StringifierPrinter(game);
		System.out.println(printer);
		return false;
		
	}
}
