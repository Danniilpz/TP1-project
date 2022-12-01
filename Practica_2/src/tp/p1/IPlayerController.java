
package tp.p1;

import tp.p1.exception.CommandExecuteException;

public interface IPlayerController {

    public void move (String direccion, int numero) throws CommandExecuteException;
    public void shoot();
    public void shockWave() throws CommandExecuteException;

    public void recibirPuntos(int points);
    public void enableShockWave();
    public void enableMissile();
}
