package tp.p1;

import java.util.Scanner;

public class Controller {
	private Game game;
	private Scanner in;
        boolean onPlay;
        boolean exit;
	public Controller(Game game) {
            this.game=game;
            in=new Scanner(System.in);
	}
	public void run() {
            onPlay=true;
            exit = true;
            while(onPlay && exit) {
                game.draw();
                exit = userCommand();
                game.computerAction();
                onPlay=game.update();
            }
	}
	
	public boolean userCommand() {
            boolean valido = false;
            boolean onPlay = true;
            char comando;
            do{
                System.out.print("Command > ");
                String[] comandos=in.nextLine().split(" ");
                if(!comandos[0].equals("")) comando=comandos[0].toLowerCase().charAt(0);
                else comando=' ';
                switch(comando){
                    case 'm':
                        if(comandos.length==3&&
                           (comandos[1].equals("left")||comandos[1].equals("right"))&&
                           (comandos[2].equals("1")||comandos[2].equals("2"))){
                            String direccion=comandos[1];
                            int numero=Integer.parseInt(comandos[2]);
                            game.mover(direccion, numero);
                            valido=true;
                        }
                        else{
                            System.err.println("Comando no válido");
                            valido=false;
                        }
                        break;
                    case 's':
                          game.shoot();
                          valido=true;
                        break;
                    case 'w':          	
                        game.shockWave();           	
                        valido=true;
                        break;
                    case 'r':
                        game.reset();          	            		     	       		
                        valido=true;
                        break;
                    case 'l':
                        game.list();
                        valido=false;
                        break;
                    case 'n':                    
                        valido=true;
                        break;
                    case ' ':
                        valido=true;
                    break;
                    case 'e':
                        onPlay=false;
                        System.err.println("Has salido del juego");
                        valido=true;
                        break;
                    case 'h':
                        game.help();
                        valido=false;
                        break;
                    default:
                        System.err.println("Comando no válido");
                        valido=false;
                        }
            }while(valido==false);
            return onPlay;
    }
}
