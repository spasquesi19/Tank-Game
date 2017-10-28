/**
 * CanShoot interfaces that includes the fire method and the speed and turn_speed constants
 * 
 * @author Sam Pasquesi
 * @version XOR
 */
public interface CanShoot  
{
    public final int SPEED = 2;
    public final int TURN_SPEED = 2;
    /**
     * method that shoots a bullet
     */
    void fire();
}
