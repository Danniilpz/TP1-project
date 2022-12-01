package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ListPrintersCommand extends Command{
	public ListPrintersCommand() {
		super("listPrinters", "listPrinters", "listPrinters: Prints the printer options.");

	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.split(" ");
		if(textos.length ==1 && textos[0].equals("listPrinters"))
		{
			return new ListPrintersCommand();
		}
		else { return null;}
	}

	@Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
		System.out.println("boardPrinter : prints the game formatted as a board of dimension: 9x8\r\n" + 
				"stringifier : prints the game as plain text\r\n" + 
				"");
		return false;
		
	}
}
