public class PointCP3 extends PointCP5{

 public PointCP3(char type, double xOrRho, double yOrTheta)
 {
 super(type, xOrRho, yOrTheta);
 }

 public double getX()
 {
 return xOrRho;
 }

 public double getY()
 {
 return yOrTheta;
 }

 public double getRho()
 {
 return (Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2)));
 }

 public double getTheta()
 {
 return Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
 }

 public PointCP2 convertStorageToPolar(){

 Calculate RHO and THETA
 yOrTheta = getTheta();
 xOrRho = getRho();

 char typeCoord = 'P';

 return new PointCP2(typeCoord, xOrRho, yOrTheta);
 }

 public double getDistance(PointCP3 pointB)
 {

 double deltaX = getX() - pointB.getX();
 double deltaY = getY() - pointB.getY();
 return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
 }

 public PointCP3 rotatePoint(double rotation)
 {
 double radRotation = Math.toRadians(rotation);
 double X = getX();
 double Y = getY();
 return new PointCP3('C', (Math.cos(radRotation) * X)-(Math.sin(radRotation) * Y), (Math.sin(radRotation) * X)+(Math.cos(radRotation) * Y));
 }

 public String toString()
 {
 return "Stored as Cartesian (" + getX() + "," + getY() + ")" + "\n";
 }

}
 