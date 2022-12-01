package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class MoveCommand extends Command{
	private String direccion;
	private int numero;
	public MoveCommand(String direccion,String numero) {
		super("M", "move", "move <left|right><1|2>: Moves UCM-Ship to the indicated direction.");
		if(direccion.charAt(0)=='l') {
			this.direccion="left";
		}
		else if(direccion.charAt(0)=='r') {
			this.direccion="right";
		}
		this.numero=Integer.parseInt(numero);
	}
	public static Command parse(String s) throws CommandParseException  {
		String[] textos = s.toLowerCase().split(" ");
		if(textos.length == 3 && (textos[0].equals("move") || textos[0].equals("m")&&
                (textos[1].equals("left")||textos[1].equals("l")||textos[1].equals("right")||textos[1].equals("r"))&&
                (textos[2].equals("1")||textos[2].equals("2"))))
		{
		if(!textos[2].equals("1") && !textos[2].equals("2")) {
			throw new CommandParseException("Número del movimiento no válido");
		}
			
			return new MoveCommand(textos[1],textos[2]);
		}
		else { return null;}
	}
	public boolean execute(Game game, Controller controller)  throws CommandExecuteException {
                game.move(direccion, numero);
		return true;
		
	}
}
