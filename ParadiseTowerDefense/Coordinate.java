/**
 * A simple way to manage x,y pairs with precision.
 * 
 * Can be get or set as both double or int.
 * 
 * (Graphics2D's Point2D class is similar, but not supported
 * on the Greenfoot Gallery)
 * 
 * @author Jordan Cohen
 */

public class Coordinate {
    private double x;
    private double y;

    public Coordinate (int x, int y){
        this.x = (double)x;
        this.y = (double)y;
    }

    public Coordinate (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setPreciseLocation (double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setLocation (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setX(int x){
        this.x = (double)x;
    }

    public void setY (int y ){
        this.y = (double)y;
    }

    public int getX(){
        //return (int)Math.round(x);
        return (int)x;
    }

    public int getY(){
        //return (int)Math.round(y);
        return (int)y;
    }

    public double getPreciseX(){
        return x;
    }

    public double getPreciseY(){
        return y;
    }

    public void dX (double value){
        this.x += value;
    }

    public void dY (double value){
        this.y += value;
    }

    public String toString () {
        return "x: " + x + ", y: " + y;
    }
}
