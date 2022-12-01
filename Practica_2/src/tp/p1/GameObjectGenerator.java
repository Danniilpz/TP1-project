
package tp.p1;

import tp.p1.objects.*;

class GameObjectGenerator {
    private static GameObject[] availableGameObjects = {
        new UCMShip(),
        new Ovni(),
        new RegularAlien(),
        new DestroyerAlien(),
        new ExplosiveAlien(),
        new Bomb(),
        new UCMMissile(),
        new UCMSuperMissile()
    };
    public static GameObject parse(String stringFromFile, Game game){
        GameObject gameObject = null;
        String[] textos = stringFromFile.toLowerCase().split(";");
        for (GameObject go: availableGameObjects) {
            gameObject = go.parse(stringFromFile,game);
            if (gameObject != null) break;
        }
        return gameObject;
    }

}
