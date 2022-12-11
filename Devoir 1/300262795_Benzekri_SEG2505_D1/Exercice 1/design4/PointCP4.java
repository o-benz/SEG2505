package pointcp.design4;

public class PointCP4
{

 private char typeCoord;

 private double x;

 private double y;

 private double rho;

 private double theta;

 public PointCP4(char type, double xOrRho, double yOrTheta)
 {
 if(type != 'C' && type != 'P')
 throw new IllegalArgumentException();
 if(type == 'C'){
 this.x = xOrRho;
 this.y = yOrTheta;
 this.rho = (Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2)));
 this.theta = Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
 }
 else{
 this.rho = xOrRho;
 this.theta = yOrTheta;
 this.x = Math.cos(Math.toRadians(yOrTheta)) * xOrRho;
 this.y= Math.sin(Math.toRadians(yOrTheta)) * xOrRho;
 }
 typeCoord = type;
 }
 
 public double getX()
 {
 return this.x;
 }
 public double getY()
 {
 return this.y;
 }
 public double getRho()
 {
 return this.rho;
 }
 public double getTheta()
 {
 return this.theta;
 }

 public void convertStorageToPolar()
 {
 typeCoord = 'P';
 return;
 }

 public void convertStorageToCartesian()
 {
 typeCoord = 'C';
 return;
 }

 public double getDistance(PointCP4 pointB)
 {
 double deltaX = getX() - pointB.getX();
 double deltaY = getY() - pointB.getY();
 return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
 }

 public PointCP4 rotatePoint(double rotation)
 {
 double radRotation = Math.toRadians(rotation);
 double X = getX();
 double Y = getY();
 return new PointCP4('C',
 (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y),
 (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
 }

 public String toString()
 {
 return "Stored as " + (typeCoord == 'C'
 ? "Cartesian (" + getX() + "," + getY() + ")"
 : "Polar [" + getRho() + "," + getTheta() + "]") + "\n";
 }
}