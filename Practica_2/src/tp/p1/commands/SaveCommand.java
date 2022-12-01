package tp.p1.commands;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.printer.StringifierPrinter;


public class SaveCommand extends Command{
		private static String fileName = "";
	   public SaveCommand() {
	        super("Save", "Save", "guardar: Permite guardar una partida de un fichero.");

	    }

	    public static SaveCommand parse(String s) throws CommandParseException{
	        String[] textos = s.toLowerCase().split(" ");
	        if(textos.length ==2 && (textos[0].equals("save")))
	        {
	        		fileName = textos[1];
	                return new SaveCommand();
	        }
	        else { return null;}
	    }
	    
            public boolean execute(Game game, Controller controller) throws CommandExecuteException {
                BufferedWriter out = null;
                StringifierPrinter printer=new StringifierPrinter(game);
                try {
                    out = new BufferedWriter(new FileWriter(fileName+".dat"));
                    out.write(printer.toString());
                    out.close();
                    System.out.println("Game successfully saved in file "+fileName+".dat. Use the load command to reload it.");
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                return false;
            }
}
