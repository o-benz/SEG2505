public class PointCP2 extends PointCP5{

 private double rho;
 private double theta;

 public PointCP2 (char type, double xOrRho, double yOrTheta) {
 super(type, xOrRho, yOrTheta);
 }

 public double getX()
 {
 return (Math.cos(Math.toRadians(theta)) * rho);
 }

 public double getY()
 {
 return (Math.sin(Math.toRadians(theta)) * rho);
 }

 public double getRho () {
 return rho;
 }

 public double getTheta () {
 return theta;
 }

 public PointCP3 convertStorageToCartesian(){

 Calculate X and Y
 double yOrTheta = getY();
 double xOrRho = getX();

 char typeCoord = 'C'; 
 return new PointCP3(typeCoord, xOrRho, yOrTheta);
 }

 public double getDistance(PointCP2 pointB)
 {
 double deltaX = getX() - pointB.getX();
 double deltaY = getY() - pointB.getY();

 return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
 }

 public PointCP rotatePoint(double rotation)
 {
 double radRotation = Math.toRadians(rotation);
 double x1 = getX();
 double y1 = getY();
 double x2 = (Math.cos(radRotation) * x1) - (Math.sin(radRotation) * y1);
 double y2 = (Math.sin(radRotation) * x1) + (Math.cos(radRotation) * y1);
 return new PointCP ('C', x2, y2);
 }

 public String toString()
 {
 return ( "Stored as Polar "+"[" + getRho() + "," +getTheta() + "]" + "\n");
 }
 }