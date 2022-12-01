package tp.p1;

import tp.p1.exception.NumberFormatException;

public class Main {

	public static void main(String[] args) throws NumberFormatException{
           
            Game game;
            if((args.length < 1) || (args.length > 2)){
    			throw new NumberFormatException("Numero de argumentos incorrectos");
    		}
            if(args.length==2) {
            	 Level level=Level.valueOf(args[0]);
                int seed = Integer.parseInt(args[1]);
                game=new Game(level,seed);                                 
            }
            else {
            	 Level level=Level.valueOf(args[0]);
                game=new Game(level);
            }
            Controller controller=new Controller(game);
            controller.run();
	}

}
