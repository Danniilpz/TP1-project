package tp.p1.commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tp.p1.Controller;
import tp.p1.Game;
import tp.p1.exception.CommandExecuteException;
import tp.p1.exception.CommandParseException;
import tp.p1.exception.FileContentsException;

public class LoadCommand extends Command{
            private static String fileName = "";
	   public LoadCommand() {
	        super("L", "Load", "cargar: Permite cargar una partida de un fichero.");

	    }

	    public static LoadCommand parse(String s) throws CommandParseException{
	        String[] textos = s.toLowerCase().split(" ");
	        if(textos.length ==2 && (textos[0].equals("load")))
	        {
                    fileName=textos[1];
	                return new LoadCommand();
	        }
	        else { return null;}
	    }
	    
		public boolean execute(Game game, Controller controller) throws CommandExecuteException {
                        BufferedReader in=null;
			try{
                            if(verifyFile(game)){
                                in=new BufferedReader(new FileReader(fileName+".dat"));
                                game.load(in);
                                System.out.println("Game successfully loaded from file "+fileName+".dat");
                            }
                            else{
                                throw new FileContentsException("El fichero no es válido.");
                            }
                        }
                        catch (FileContentsException ex) {
                            System.err.println(ex.getMessage());
                        }
                        catch(FileNotFoundException e){
                            System.err.println("Nombre de fichero no existente");
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        } 
			return false;
		}
                
                public boolean verifyFile(Game game) throws FileNotFoundException,IOException{
                    boolean verify=true;
                    BufferedReader in=null;
                    String linea;
                    String[] partes;
                    boolean player=false,ovni=false,cycles=false,level=false,misil=false,supermisil=false;
                    in=new BufferedReader(new FileReader(fileName+".dat"));
                    if(!in.readLine().equals("— Space Invaders v2.0 —")||!in.readLine().equals("")){
                        verify=false;
                    }
                    else{
                        linea = in.readLine();
                        while(linea != null&&!linea.isEmpty()&&verify==true) {
                            partes=linea.split(";");
                            if(partes.length<2){
                                verify=false;
                            }
                            else{
                                switch(partes[0]){
                                    case "G":
                                        if(cycles){
                                            verify=false; //solo puede salir una vez
                                        }
                                        else{
                                           cycles=true;
                                            if(partes.length!=2){
                                                verify=false;
                                            }
                                            else{
                                                try {
                                                    Integer.parseInt(partes[1]);
                                                } catch (NumberFormatException nfe){
                                                        verify=false;
                                                }
                                            } 
                                        }
                                        break;
                                    case "L":
                                        if(level){
                                            verify=false;
                                        }
                                        else{
                                            level=true;
                                            if(partes.length!=2){
                                                verify=false;
                                            }
                                            else{
                                                try{
                                                    game.getLevel().valueOf(partes[1]);
                                                }
                                                catch(IllegalArgumentException e){
                                                    verify=false;
                                                }
                                            }
                                        }                                                
                                        break;
                                    case "D":
                                        if(partes.length!=7){
                                            verify=false;
                                        }
                                        else{
                                            try {
                                                int row=Integer.parseInt(partes[1]);
                                                int col=Integer.parseInt(partes[2]);
                                                int live=Integer.parseInt(partes[3]);
                                                String coords=partes[4];
                                                int coords_int=Integer.parseInt(partes[4]);
                                                int dirCol=Integer.parseInt(partes[5]);
                                                int dirRow=Integer.parseInt(partes[6]);
                                                if(!game.isOnBoard(row, col)){
                                                    verify=false;
                                                }
                                                if(live>1||live<0){
                                                    verify=false;
                                                }
                                                if(coords.length()!=4){
                                                    verify=false;
                                                }
                                                else{
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(0,1)), Integer.parseInt(coords.substring(0,1)))) verify=false;
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(2,3)), Integer.parseInt(coords.substring(3)))) verify=false;
                                                }
                                                if(dirCol!=1&&dirCol!=-1){
                                                    verify=false;
                                                }
                                                if(dirRow!=1&&dirRow!=0){
                                                    verify=false;
                                                }
                                            } catch (NumberFormatException nfe){
                                                    verify=false;
                                            }                                                    
                                        }
                                        break;
                                    case "R":
                                        if(partes.length!=7){
                                            verify=false;
                                        }
                                        else{
                                            try {
                                                int row=Integer.parseInt(partes[1]);
                                                int col=Integer.parseInt(partes[2]);
                                                int live=Integer.parseInt(partes[3]);
                                                String coords=partes[4];
                                                int coords_int=Integer.parseInt(partes[4]);
                                                int dirCol=Integer.parseInt(partes[5]);
                                                int dirRow=Integer.parseInt(partes[6]);
                                                if(!game.isOnBoard(row, col)){
                                                    verify=false;
                                                }
                                                if(live>2||live<0){
                                                    verify=false;
                                                }
                                                if(coords.length()!=4){
                                                    verify=false;
                                                }
                                                else{
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(0,1)), Integer.parseInt(coords.substring(0,1)))) verify=false;
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(2,3)), Integer.parseInt(coords.substring(3)))) verify=false;
                                                }
                                                if(dirCol!=1&&dirCol!=-1){
                                                    verify=false;
                                                }
                                                if(dirRow!=1&&dirRow!=0){
                                                    verify=false;
                                                }
                                            } catch (NumberFormatException nfe){
                                                    verify=false;
                                            }                                                    
                                        }
                                        break;
                                    case "E":
                                        if(partes.length!=7){
                                            verify=false;
                                        }
                                        else{
                                            try {
                                                int row=Integer.parseInt(partes[1]);
                                                int col=Integer.parseInt(partes[2]);
                                                int live=Integer.parseInt(partes[3]);
                                                String coords=partes[4];
                                                int coords_int=Integer.parseInt(partes[4]);
                                                int dirCol=Integer.parseInt(partes[5]);
                                                int dirRow=Integer.parseInt(partes[6]);
                                                if(!game.isOnBoard(row, col)){
                                                    verify=false;
                                                }
                                                if(live>2||live<0){
                                                    verify=false;
                                                }
                                                if(coords.length()!=4){
                                                    verify=false;
                                                }
                                                else{
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(0,1)), Integer.parseInt(coords.substring(0,1)))) verify=false;
                                                    if(!game.isOnBoard(Integer.parseInt(coords.substring(2,3)), Integer.parseInt(coords.substring(3)))) verify=false;
                                                }
                                                if(dirCol!=1&&dirCol!=-1){
                                                    verify=false;
                                                }
                                                if(dirRow!=1&&dirRow!=0){
                                                    verify=false;
                                                }
                                            } catch (NumberFormatException nfe){
                                                    verify=false;
                                            }                                                    
                                        }
                                        break;
                                    case "O":
                                        if(ovni){
                                            verify=false;
                                        }
                                        else{
                                            ovni=true;
                                            if(partes.length!=3){
                                                verify=false;
                                            }
                                            else{
                                                try {
                                                    int row=Integer.parseInt(partes[1]);
                                                    int col=Integer.parseInt(partes[2]);
                                                    if(!game.isOnBoard(row, col)){
                                                        verify=false;
                                                    }
                                                } catch (NumberFormatException nfe){
                                                        verify=false;
                                                }                                                    
                                            }
                                        }                                                
                                        break;
                                    case "P":
                                        if(player){
                                            verify=false;
                                        }
                                        else{
                                            player=true;
                                            if(partes.length!=7){
                                                verify=false;
                                            }
                                            else{
                                                try {
                                                    int row=Integer.parseInt(partes[1]);
                                                    int col=Integer.parseInt(partes[2]);
                                                    int live=Integer.parseInt(partes[3]);
                                                    int puntos=Integer.parseInt(partes[4]);
                                                    String shockwave=partes[5];
                                                    int supermisiles=Integer.parseInt(partes[6]);
                                                    if(!game.isOnBoard(row, col)){
                                                        verify=false;
                                                    }
                                                    if(live>3||live<0){
                                                        verify=false;
                                                    }
                                                    if(puntos<0){
                                                        verify=false;                                                            
                                                    }
                                                    if(!shockwave.equals("true")&&!shockwave.equals("false")){
                                                        verify=false;
                                                    }
                                                    if(supermisiles<0){
                                                        verify=false;                                                            
                                                    }
                                                } catch (NumberFormatException nfe){
                                                        verify=false;
                                                }                                                    
                                            }
                                        }                                                
                                        break;
                                    case "B":
                                        if(partes.length!=3){
                                            verify=false;
                                        }
                                        else{
                                            try {
                                                int row=Integer.parseInt(partes[1]);
                                                int col=Integer.parseInt(partes[2]);
                                                if(!game.isOnBoard(row, col)){
                                                    verify=false;
                                                }
                                            } catch (NumberFormatException nfe){
                                                    verify=false;
                                            }                                                    
                                        }
                                        break;
                                    case "M":
                                        if(misil){
                                            verify=false;
                                        }
                                        else{
                                            misil=true;
                                            if(partes.length!=3){
                                                verify=false;
                                            }
                                            else{
                                                try {
                                                    int row=Integer.parseInt(partes[1]);
                                                    int col=Integer.parseInt(partes[2]);
                                                    if(!game.isOnBoard(row, col)){
                                                        verify=false;
                                                    }
                                                } catch (NumberFormatException nfe){
                                                        verify=false;
                                                }                                                    
                                            }
                                        }                                                
                                        break;
                                    case "X":
                                        if(supermisil){
                                            verify=false;
                                        }
                                        else{
                                            supermisil=true;
                                            if(partes.length!=3){
                                                verify=false;
                                            }
                                            else{
                                                try {
                                                    int row=Integer.parseInt(partes[1]);
                                                    int col=Integer.parseInt(partes[2]);
                                                    if(!game.isOnBoard(row, col)){
                                                        verify=false;
                                                    }
                                                } catch (NumberFormatException nfe){
                                                        verify=false;
                                                }                                                    
                                            }
                                        }                                                
                                        break;
                                    default:
                                        verify=false;
                                }
                            }
                            linea=in.readLine();                                   
                        }

                    }
                    in.close();                        
                    return verify;
                }
}
