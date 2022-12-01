package tp.p1;

import java.io.FileOutputStream;
import java.io.*;
import java.util.Random;

import tp.p1.exception.CommandExecuteException;
import tp.p1.objects.*;
import tp.p1.printer.StringifierPrinter;

public class Game implements IPlayerController{
    private UCMShip ucmShip;
    private Random rand;
    private Level level;
    private int ciclos;
    private int puntos;
    private boolean onPlay;
    private boolean doExit;
    private BoardInitializer initializer ;
    private GameObjectBoard board;
    public final static int DIM_X = 9;
    public final static int DIM_Y = 8;
	
    public Game(Level level) {
        this.level=level;
        this.rand=new Random();        
        initializer=new BoardInitializer();
        initGame();
    }
    public Game(Level level, int seed) {
        this.level=level;
        this.rand=new Random(seed);
        initializer=new BoardInitializer();
        initGame();
    }
    public void initGame () {
        ciclos=0;
        puntos=0;
        onPlay=true;
        doExit=false;
        board = initializer.initialize(this, level );
        this.ucmShip=new UCMShip(DIM_Y-1,DIM_X/2,this);
        board.add(ucmShip);
    }	
    public UCMShip getUcmShip() {
            return ucmShip;
    }

    public void setUcmShip(UCMShip ucmShip) {
            this.ucmShip = ucmShip;
    }
    public Random getRand() {
            return rand;
    }

    public void setRand(Random rand) {
            this.rand = rand;
    }

    public Level getLevel() {
            return level;
    }

    public void setLevel(Level level) {
            this.level = level;
    }

    public int getCiclos() {
            return ciclos;
    }

    public void setCiclos(int ciclos) {
            this.ciclos = ciclos;
    }

    public int getPuntos() {
            return puntos;
    }

    public void recibirPuntos(int puntos) {
            this.puntos+=puntos;
    } 
    
    public GameObjectBoard getBoard() {
        return board;
    }
    public void setBoard(GameObjectBoard board) {
        this.board = board;
    }
    public void reset() { //Reinicia los valores del game    		
        this.initGame();
    }
    public void addObject(GameObject object) {
        board.add(object);
    }
    
    public String positionToString(int row, int col) {
        return board.toString(row,col);
    }
 
    public void atacarUcmShip() {
		this.ucmShip.attack(1);
		
	}
    public void atacarmisilUCMShip(int row, int col) {
		this.board.getObjectInPosition(row, col);
		
	}
    public boolean update() {
    	if(!this.aliensWin()&&!this.playerWin()&&!doExit){
            board.update();
            ciclos++;
            if(this.aliensWin()||this.playerWin()){
            	if(this.aliensWin()) this.gameOver();
                else this.win();
                return false;
            }
            return true;
        }
        else{
            if(this.aliensWin()) this.gameOver();
            else this.win();
            return false;
        }
        
    }
    
    public boolean isOnBoard(int row, int col) {
        if(row>=0&&row<DIM_Y&&col>=0&&col<DIM_X) return true;
        else return false;        
    }
    public void exit() {
        doExit = true;
    }
    public String getWinnerMessage () {
        if (playerWin()) return "Player win!";
        else if (aliensWin()) return "Aliens win!";
        else return "This should not happen";
    }
    public boolean aliensWin() {
        return (!ucmShip.isAlive () || AlienShip.haveLanded);
    }
    private boolean playerWin () {
        return AlienShip.group==0;
    }

    public void shockWave() throws CommandExecuteException{
        if(this.board.canShockwave()) {
            this.board.shockWave();
        }
        else {
        	throw new CommandExecuteException("No tienes disponible el shockWave");
        }
    }

    
    public void draw(){
            System.out.println("\nLife: "+ucmShip.getLive()+"\nNumber of cycles: "+ciclos+"\nPoints: "+puntos
                    +"\nRemaining aliens: "+board.remainingAliens()+"\nShockWave: "+board.canShockwave()+"\nSuperMisiles: "+board.getSuperMisiles());
            
            System.out.print(board.toString());
        }
		
	

        public void win() {
            System.out.println("PLAYER WINS");
        }
        public void gameOver() {
            System.err.println("ALIENS WINS");
        }

      
        public void list() {
            board.list();           
        }

        public void help() {
                System.out.println("move <left|right><1|2>: Moves UCM-Ship to the indicated direction.\n" +
            "shoot: UCM-Ship launches a missile.\n" +
            "shockWave: UCM-Ship releases a shock wave.\n" +
            "list: Prints the list of available ships.\n" +
            "reset: Starts a new game.\n" +
            "help: Prints this help message.\n" +
            "exit: Terminates the program.\n" +
            "[none]: Skips one cycle.");		
        }
	
    public void move(String direccion, int numero)  throws CommandExecuteException{
        ucmShip.move(direccion,numero);
    }

    public void shoot() {
        if(board.canShoot()) {
            board.add(new UCMMissile(7,ucmShip.getCol(),this));
            this.board.canShoot(false);
        }
    }
    public void enableShockWave() {
        this.board.add(new Shockwave(-1,-1,this));
        this.board.canShockwave(true);
    }

    public void enableMissile() {
        this.board.canShoot(true);
    }
   
    public void computerAction(){
        if(!board.existsOvni()) {
            if(rand.nextDouble()<level.getFovni()) {
                board.add(new Ovni(0,8,this));
            }
        }
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void comprarSuperMisil() throws CommandExecuteException {
        if(this.puntos>=20){
            this.board.setSuperMisiles(this.board.getSuperMisiles()+1);
            this.puntos-=20;
        }
        else{
        	throw new CommandExecuteException("No tienes suficientes puntos");
        }
    }

    public void superMisil() {
        if(board.canShootSuperMissile()){
            if(board.canShoot()) {
                this.board.add(new UCMSuperMissile(7,this.ucmShip.getCol(),this));
                this.board.canShoot(false);
                this.board.setSuperMisiles(this.board.getSuperMisiles()-1);
            }
        }else{
            System.err.println("No tienes supermisiles");
        }
    }

    public void attackAdjacent(int row, int col) {
        this.board.attackAdjacent(row,col);
    }

    public void load(BufferedReader in) {
        String linea;
        String[] partes;
        try{           
            board.getObjects().clear();
            AlienShip.group=0;
            linea=in.readLine().trim();
            partes=linea.split(";");
            if(partes.length==2&&partes[0].equals("G")){
                this.ciclos=Integer.parseInt(partes[1]);
            }
            linea=in.readLine().trim();
            partes=linea.split(";");
            if(partes.length==2&&partes[0].equals("L")){
                this.level=Level.valueOf(partes[1]);
            }
            linea = in.readLine().trim();
            while(linea != null&&!linea.isEmpty() ) {
                GameObject gameObject = GameObjectGenerator.parse(linea, this);
                if (gameObject!= null) {
                    if(gameObject.getClass().getName().equals("tp.p1.objects.UCMShip")) this.ucmShip=(UCMShip)gameObject;
                    board.add(gameObject);
                }                
                linea = in.readLine();
                if(linea!=null) linea=linea.trim();
            }
            in.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(NumberFormatException e){
            e.printStackTrace();
        }
    }
    
}
