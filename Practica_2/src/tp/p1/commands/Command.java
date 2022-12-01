package tp.p1.commands;
import tp.p1.*;
import tp.p1.exception.CommandExecuteException;

public abstract class Command {
	private String helpText;
	private String helpInfo;
	protected final String commandName;
	protected boolean exit;
	
	public Command(String commandText, String commandTextMsg, String helpTextMsg){
		commandName = commandText;
		helpText = commandTextMsg;
		helpInfo = helpTextMsg;
	}
	
	public abstract boolean execute(Game game, Controller controller) throws CommandExecuteException;
	
	public String helpText(){return " " + helpText + ": " + helpInfo;}
	
	public String getCommandName() {
		return commandName;
	}
}
