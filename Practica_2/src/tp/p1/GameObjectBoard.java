package tp.p1;

import java.util.ArrayList;
import tp.p1.objects.*;
import tp.p1.printer.FormattedPrinter;

public class GameObjectBoard {
    private ArrayList<GameObject> objects;
    private ArrayList<GameObject> objects_update;
    private FormattedPrinter printer;
    private int width;
    private int height;
    private boolean canShockwave;
    private boolean canShoot;
    private int superMisiles;

    public GameObjectBoard (int width, int height) {
        objects=new ArrayList();
        objects_update=null;
        this.height=height;
        this.width=width;
        this.canShockwave=false;
        this.canShoot=true;
        this.superMisiles=0;
    }
    private int getCurrentObjects () {
        return objects.size();
    }
    public void add (GameObject object) {
        if(objects_update==null){
            objects.add(object);
        }
        else{
            objects_update.add(object);
        }
    }
    public GameObject getObjectInPosition (int row, int col) {
        GameObject objectInPosition=null;
        if(objects_update==null){
            for(GameObject object:objects){
                if(object.getRow()==row&&object.getCol()==col) objectInPosition=object;
            }
        }
        else{
            for(GameObject object:objects_update){
                if(object.getRow()==row&&object.getCol()==col) objectInPosition=object;
            }
        }
        
        return objectInPosition;
    }
    private int getIndex(int row, int col) {
        int index=-1;
        for(int i=0;i<objects.size();i++){
            if(objects.get(i).getRow()==row&&objects.get(i).getCol()==col) index=i;
        }
        return index;
    }

    public void remove (GameObject object) {
        if(objects_update==null){
            objects.remove(object);
        }
        else{
            objects_update.remove(object);
        }
        
    }
    
    public void update() {
        this.computerAction();
    	objects_update=(ArrayList<GameObject>) objects.clone();
        for(GameObject object:objects){
            object.update();
        }
        objects=(ArrayList<GameObject>) objects_update.clone();
        objects_update=null;
    }
    private void checkAttacks(GameObject object) {
    // TODO implement
    }
    public void computerAction() {
        objects_update=(ArrayList<GameObject>) objects.clone();
        for(GameObject object:objects){
            object.computerAction();
        }
        objects=(ArrayList<GameObject>) objects_update.clone();
        objects_update=null;
    }
    private void removeDead() {
    // TODO implement
    }
    public String toString(int row, int col) {
        GameObject objectInPosition=this.getObjectInPosition(row,col);
        if(objectInPosition!=null) return objectInPosition.toString();
        else return "  ";
    }
    public String toString(){
        this.printer=new FormattedPrinter(this,this.height,this.width);
        
        return printer.toString(); //TEXTO
    }
    public int remainingAliens(){
        for(GameObject object:objects){
            if(object.getClass().getName().equals("tp.p1.objects.RegularAlien")||
                    object.getClass().getName().equals("tp.p1.objects.DestroyerAlien")){
                return ((AlienShip)object).group;
            }
        }
        return 0;
    }
    public void shockWave() {
        objects_update=(ArrayList<GameObject>) objects.clone();
        for(GameObject object:objects){
            if(object.getClass().getName().equals("tp.p1.objects.RegularAlien")||
               object.getClass().getName().equals("tp.p1.objects.DestroyerAlien")||
               object.getClass().getName().equals("tp.p1.objects.ExplosiveAlien")||
               object.getClass().getName().equals("tp.p1.objects.Ovni")){
                object.attack(1);
            }
        }
        objects=(ArrayList<GameObject>) objects_update.clone();
        objects_update=null;
        this.disableShockWave();
    }
    public void disableShockWave() {
        objects_update=(ArrayList<GameObject>) objects.clone();
        for(GameObject object:objects){
            if(object.getClass().getName().equals("tp.p1.objects.Shockwave")) this.remove(object);
        }
        objects=(ArrayList<GameObject>) objects_update.clone();
        objects_update=null;
        this.canShockwave=false;
    }
     
    public boolean canShockwave() {
        return canShockwave;
    }

    public boolean canShoot() {
        return canShoot;
    }

    public void canShockwave(boolean canShockwave) {
        this.canShockwave = canShockwave;
    }

    public void canShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }
    
    public boolean existsOvni() {
        for(GameObject object:objects){
            if(object.getClass().getName().equals("tp.p1.objects.Ovni")) return true;
        }
        return false;
    }
    public void list(){
        for(GameObject object:objects){
            if(object.getClass().getName().equals("tp.p1.objects.RegularAlien")){
                System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: "+object.getLive());
            }
            else if(object.getClass().getName().equals("tp.p1.objects.DestroyerAlien")){
                System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
            }
            else if(object.getClass().getName().equals("tp.p1.objects.ExplosiveAlien")){
                System.out.println("[E]xplosive ship: Points: 5 - Harm: 1 - Shield: "+object.getLive());
            }
            else if(object.getClass().getName().equals("tp.p1.objects.Ovni")){
                System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
            }
            else if(object.getClass().getName().equals("tp.p1.objects.UCMShip")){
                System.out.println("^__^: Harm: 1 - Shield: "+object.getLive());
            }            
        }
    }
    
    public boolean canShootSuperMissile(){
        if(superMisiles>0) return true;
        else return false;
    }

    public int getSuperMisiles() {
        return superMisiles;
    }

    public void setSuperMisiles(int superMisiles) {
        this.superMisiles = superMisiles;
    }

   public void attackAdjacent(int row, int col) {
        GameObject o1,o2,o3,o4;
        o1=this.getObjectInPosition(row-1, col);
        if(o1!=null) o1.attack(1);
        o2=this.getObjectInPosition(row+1, col);
        if(o2!=null) o2.attack(1);
        o3=this.getObjectInPosition(row, col-1);
        if(o3!=null) o3.attack(1);
        o4=this.getObjectInPosition(row, col+1);
        if(o4!=null) o4.attack(1);
        
    }

    public ArrayList<GameObject> getObjects() {
        return objects;
    }
    
    
}
