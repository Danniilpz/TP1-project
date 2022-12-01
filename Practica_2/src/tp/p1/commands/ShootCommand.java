package tp.p1.commands;

import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;

public class ShootCommand extends Command{
        private int tipo;
	public ShootCommand(int tipo) {
		super("S", "Shoot", "shoot: UCM-Ship launches a missile.");
                this.tipo=tipo;
	}

	public static Command parse(String s)  throws CommandParseException{
		String[] textos = s.toLowerCase().split(" ");
		if((textos[0].equals("shoot") || textos[0].equals("s")))
		{
                    if(textos.length ==1){
                        return new ShootCommand(1);
                    }
                    else if(textos.length ==2 && (textos[1].equals("supermisil"))){
                        return new ShootCommand(2);
                    }
                        
		}
		return null;
	}
	
        @Override
	public boolean execute(Game game, Controller controller) throws CommandExecuteException{
                if(tipo==1) game.shoot(); 
                if(tipo==2) game.superMisil();
            
		return true;
		
	}
}
