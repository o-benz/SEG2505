public class PointCP3 extends PointCP5{

 private double x;

 private double y;

 public PointCP3(char type, double xOrRho, double yOrTheta)
 {
 if(type != 'C' && type != 'P')
 throw new IllegalArgumentException();
 else if(type =='P'){
 this.x = Math.cos(Math.toRadians(yOrTheta)) * xOrRho;
 this.y = Math.sin(Math.toRadians(yOrTheta)) * xOrRho;
 }
 else{
 this.x = xOrRho;
 this.y = yOrTheta;
 }
 }


 public double getX()
 {
 return x;
 }

 public double getY()
 {
 return y;
 }

 public double getRho()
 {
 return (Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
 }

 public double getTheta()
 {
 return Math.toDegrees(Math.atan2(y, x));
 }

 public PointCP2 convertStorageToPolar(){
 double y = getTheta();
 double x = getRho();
 char typeCoord = 'P';
 return new PointCP2(typeCoord, x, y);
 }

 public double getDistance(PointCP5 pointB)
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

 return new PointCP3( 'C',
 (Math.cos(radRotation) * X) - (Math.sin(radRotation) * Y), (Math.sin(radRotation) * X) + (Math.cos(radRotation) * Y));
 }

 public String toString()
 {
 return "Stored as Cartesian (" + getX()+","+getY()+")"+"\n";
 }
public PointCP3 convertStorageToCartesian() {
return new PointCP3('C', this.x, this.y);
}
public char getType() {
return 'C';
}
}
