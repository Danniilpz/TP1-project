package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ExitCommand extends Command{
	public ExitCommand() {
		super("E", "exit", "exit: Terminates the program.");

	}

	public static Command parse(String s)  throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1 && (textos[0].equals("exit") || textos[0].equals("e")))
		{
			return new ExitCommand();
		}
		else { return null;}
	}

	@Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
                game.exit();
		return true;
		
	}

	
}
