package tp.p1;

import java.util.Scanner;
import tp.p1.printer.*;

import tp.p1.commands.*;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.exception.NumberFormatException;

public class Controller {
    private Game game;
    private Scanner in;
    boolean onPlay;
    boolean nextCycle;
    ParseCommandFunction[] listaF = {
			(String s) -> {return ComprarCommand.parse(s);},
			(String s) -> {return MoveCommand.parse(s);},
			(String s) -> {return ResetCommand.parse(s);},
			(String s) -> {return ShootCommand.parse(s);},
			(String s) -> {return HelpCommand.parse(s);},
			(String s) -> {return ListCommand.parse(s);},
			(String s) -> {return ShockwaveCommand.parse(s);},
			(String s) -> {return StringifyCommand.parse(s);},
			(String s) -> {return ExitCommand.parse(s);},
			(String s) -> {return ListPrintersCommand.parse(s);},
			(String s) -> {return SaveCommand.parse(s);},
			(String s) -> {return LoadCommand.parse(s);},
			(String s) -> {return UpdateCommand.parse(s);},
			};
	public Controller(Game game) {
            this.game=game;            
            in=new Scanner(System.in);
	}
	public void run() {
            onPlay=true;
            nextCycle = true;
            while(onPlay && nextCycle) {   
            	try {
	                do{
	                game.draw();
	                nextCycle = userCommand();
	                } while(!nextCycle);
	                game.computerAction();
	                onPlay=game.update();
            	} catch (CommandParseException e1) {    				
    				System.out.println(e1.getMessage());
    			} catch (CommandExecuteException e) {    				
    				System.out.println(e.getMessage());			
    			}
            	catch (NumberFormatException e2) {    				
    				System.out.println(e2.getMessage());			
    			}
            }
	}
	
	public boolean userCommand()  throws CommandParseException, CommandExecuteException, NumberFormatException {
            boolean exit=false;
            Command c = null;
            while(c==null) {
                System.out.print("Command > ");
                String comandos=in.nextLine();

                            for(int i=0;i<listaF.length&&c==null;i++) {
                                    ParseCommandFunction f=listaF[i];
                                    c = f.parse(comandos);
                            }
            } 
            exit = c.execute(game, this);
            return exit;
       
    }
}
