package tp.p1;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private UCMShip ucmShip;
	private BombList bombList;
	private DestroyerShipList destroyerShipList;
	private RegularShipList regularShipList;
	private Ovni ovni;
	private Random rand;
	private Level level;
	private int ciclos;
        private int puntos;
        private GamePrinter printer;
        private Scanner scanner;
        private boolean onPlay;
	
	public Game(Level level) {
            this.level=level;
            this.ucmShip=new UCMShip(7,4);
            this.ovni=new Ovni(0,8, this);
            this.regularShipList=new RegularShipList(level.getRegularShips(),this);
            this.destroyerShipList=new DestroyerShipList(level.getDestroyerShips(),this);
            this.scanner=new Scanner(System.in);
            this.bombList=new BombList(this);
            ciclos=0;
            puntos=0;
            this.rand=new Random();
            onPlay=true;
	}
	
	public UCMShip getUcmShip() {
		return ucmShip;
	}

	public void setUcmShip(UCMShip ucmShip) {
		this.ucmShip = ucmShip;
	}

	public BombList getBombList() {
		return bombList;
	}

	public void setBombList(BombList bombList) {
		this.bombList = bombList;
	}

	public DestroyerShipList getDestroyerShipList() {
		return destroyerShipList;
	}

	public void setDestroyerShipList(DestroyerShipList destroyerShipList) {
		this.destroyerShipList = destroyerShipList;
	}

	public RegularShipList getRegularShipList() {
		return regularShipList;
	}

	public void setRegularShipList(RegularShipList regularShipList) {
		this.regularShipList = regularShipList;
	}

	public Ovni getOvni() {
		return ovni;
	}

	public void setOvni(Ovni ovni) {
		this.ovni = ovni;
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

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public GamePrinter getPrinter() {
		return printer;
	}

	public void setPrinter(GamePrinter printer) {
		this.printer = printer;
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public Game(Level level, int seed) {
		this(level);
		this.rand=new Random(seed);
	}
	
        public void draw(){
            System.out.println("Life: "+ucmShip.getResistencia()+"\nNumber of cycles: "+ciclos+"\nPoints: "+puntos
                    +"\nRemaining aliens: "+(destroyerShipList.getContador()+regularShipList.getContador())+"\nShockWave: "+ucmShip.isShockWave());
            this.printer=new GamePrinter(this,8,9);
            System.out.print(this.printer);
        }
        
	
	public void shockWave() {
		if(this.ucmShip.isShockWave()) {
			this.ucmShip.setShockWave(false);
			this.ovni.atacar();
			for(int i=destroyerShipList.getDestroyerShips().length - 1;i>= 0;i--) {
				this.atacarDestroyer(i);
			}
			for(int i=regularShipList.getRegularShips().length - 1;i>=0;i--) {
				this.atacarRegular(i);
			}
		}
		
	}
	public void computerAction() {
            destroyerShipList.update(rand,level.getFdisparo());
            ovni.update(rand,level.getFovni());
	}
	public boolean update() {
            ciclos++;        
            bombList.update();            
            if(ciclos%level.getVelocidad()==0){
                regularShipList.moveGroup();
                destroyerShipList.moveGroup();
            }
            ovni.move();
            if(regularShipList.getContador()==0&&destroyerShipList.getContador()==0){
                this.win();
            }
            return onPlay;
	}
	public int buscaDestroyer(int row, int col) {
		return destroyerShipList.buscaDestroyer(row, col);
	}
	public int buscaRegular(int row, int col) {
		return regularShipList.buscaRegular(row, col);
	}
	public int buscaBombas(int row, int col) {
		return bombList.buscaBombas(row, col);
	}
        public boolean buscaOvni(int row, int col) {
                return ovni.AtPosition(row, col);
	}
	public String toStringObjectAt(int i, int j) {
            if(i==ucmShip.getRow()&&j==ucmShip.getCol()) {
                    return ucmShip.toString();
            }
            else if(regularShipList.shipAtPosition(i, j)){
                    return regularShipList.getShip(i, j).toString();
            }
            else if(destroyerShipList.shipAtPosition(i, j)){
                    return destroyerShipList.getShip(i, j).toString();
            }
            else if(destroyerShipList.shipAtPosition(i, j)){
                    return destroyerShipList.getShip(i, j).toString();
            }
            else if(bombList.bombAtPosition(i, j)){
                    return bombList.getBomba(i, j).toString();
            }
            else if(ovni.AtPosition(i, j)){
                return ovni.toString();
            }
            return "   ";
	}

	public void atacarDestroyer(int z) {
		destroyerShipList.restarVida(z);		
	}
	public void atacarRegular(int y) {
		regularShipList.restarVida(y);		
	}
	public void atacarUcmShip() {
		this.ucmShip.setResistencia(this.ucmShip.getResistencia()-1);
		if(this.ucmShip.getResistencia()==0) {
                    this.gameOver();
                    this.ucmShip.setIcono("!xx!");
		}
	}
	
	public void eliminarDisparo(int x){
		bombList.eliminarDisparo(x);	
	}

	public void atacarOvni() {
		ovni.atacar();
		
	}
        public void win() {
            System.out.println("PLAYER WINS");
            onPlay=false;
        }
        public void gameOver() {
            System.err.println("ALIENS WINS");
            onPlay=false;
        }
        public void reset() { //Reinicia los valores del game    		
            for(int i = 0; i < this.regularShipList.getContador(); i++) {
                    this.regularShipList.deleteRegular(i);
            }
            for(int i = 0; i < this.destroyerShipList.getContador(); i++) {
                    this.destroyerShipList.deleteDestroyer(i);
            }
             for(int i=0;i<bombList.getBombas().length;i++){
                    this.bombList.eliminarDisparo(i);
            }

            this.ucmShip=new UCMShip(7,4);
            this.ovni=new Ovni(0,8, this);
            this.regularShipList=new RegularShipList(level.getRegularShips(),this);
            this.destroyerShipList=new DestroyerShipList(level.getDestroyerShips(),this);
            this.scanner=new Scanner(System.in);
            this.bombList=new BombList(this);
            ciclos=0;
            puntos=0;
            this.rand=new Random();
            onPlay=true;
    	}

        public void mover(String direccion, int numero) {       	
            ucmShip.move(direccion,numero);
        }

        public void shoot() {
            if(!ucmShip.comprobarMisil()) {
                bombList.adirBomba(new Disparo("misil",7,this.ucmShip.getCol(), this));
                ucmShip.setMisil(true);     		                
            }
	}
        public void list() {
            for(int i=0;i<regularShipList.getRegularShips().length;i++){
                System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: "+regularShipList.getRegularShips()[i].getResistencia());
            }
            for(int i=0;i<destroyerShipList.getDestroyerShips().length;i++){
                System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
            }
            if(ovni.existe()){
                System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
            }
            System.out.println("^__^: Harm: 1 - Shield: "+this.ucmShip.getResistencia());	
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
	
}
