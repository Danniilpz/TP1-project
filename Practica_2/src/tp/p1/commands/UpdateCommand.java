package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class UpdateCommand extends Command{

	public UpdateCommand() {
		super("", "", "");
	}

	public static Command parse(String s) throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length ==1&&textos[0].trim().equals(""))
		{
			return new UpdateCommand();
		}
		else {
			throw new CommandParseException("tipo de argumento invalido");
		}
	}
	
	@Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException {		
		return true;
	}

}
