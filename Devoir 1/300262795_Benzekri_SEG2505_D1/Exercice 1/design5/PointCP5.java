// Yananan Wang (7598753)
// Wilson Ly (7302019)
/**
 * This class implements the design 5 of the PointCP. It stores only the polar
coordinates.
 * @author Yananan Wang
 * @author Wilson Ly
 */
public abstract class PointCP5 {
    /**
     * Contains C(artesian) or P(olar) to identify the type of
     * coordinates that are being dealt with.
     */
     private char typeCoord;
    
     /**
     * Contains the current value of X or RHO depending on the type
     * of coordinates.
     */
     private double xOrRho;
    
     /**
     * Contains the current value of Y or THETA value depending on the
     * type of coordinates.
     */
     private double yOrTheta;
    
    
    public abstract double getDistance(PointCP5 other);
    
    public abstract PointCP5 rotatePoint(double rotation);
    
    public abstract char getType();
    public abstract double getX();
    public abstract double getY();
    public abstract double getRho();
    public abstract double getTheta();
    public abstract PointCP3 convertStorageToCartesian();
    public abstract PointCP2 convertStorageToPolar();
    
    public PointCP5(char type, double xOrRho, double yOrTheta)
    {
     if(type != 'C' && type != 'P')
     {
     throw new IllegalArgumentException();
     }
     else if(type =='P')
     {
     this.xOrRho = Math.cos(Math.toRadians(yOrTheta)) * xOrRho;
     this.yOrTheta = Math.sin(Math.toRadians(yOrTheta)) * xOrRho;
     }
     else{
     this.xOrRho = xOrRho;
     this.yOrTheta = yOrTheta;
     }
    }