public class PointCP2 extends PointCP5{

private double rho;

private double theta;

public PointCP2 (char type, double xOrRho, double yOrTheta) {
if(type != 'C' && type != 'P') 
{
throw new IllegalArgumentException();
} 
else if (type=='C') 
{
this.rho = Math.sqrt(Math.pow(xOrRho, 2) + Math.pow(yOrTheta, 2));
this.theta = Math.toDegrees(Math.atan2(yOrTheta, xOrRho));
}
else
{
this.rho = xOrRho;
this.theta = yOrTheta;
}
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
 double yOrTheta = getY();
 double xOrRho = getX();

 char typeCoord = 'C';

 return new PointCP3(typeCoord, xOrRho, yOrTheta);
 }

 public double getDistance(PointCP5 other)
 {
 double deltaX = getX() - other.getX();
 double deltaY = getY() - other.getY();

 return Math.sqrt((Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
 }

 public PointCP2 rotatePoint(double rotation)
 {

double rho1 = this.getRho() + rotation;
double theta1 = this.getTheta();
return new PointCP2 ('P', rho1, theta1);
 }

 public String toString()
{
return ( "Stored as Polar "+"[" + getRho() + "," + getTheta() + "]" + "\n");
 }

public PointCP2 convertStorageToPolar() 
{
return new PointCP2('P', this.rho, this.theta);
}

public char getType() 
{
    return 'P';
}