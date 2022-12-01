
package tp.p1;

public class Main {

	public static void main(String[] args) {
            Level level=Level.valueOf(args[0]);
            Game game;
            if(args.length==2) {
                    int seed=Integer.parseInt(args[1]);
                    game=new Game(level,seed);
            }
            else {
                    game=new Game(level);
            }
            Controller controller=new Controller(game);
            controller.run();
	}

}
