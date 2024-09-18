package xshape.data.composite;

import xshape.data.Position;

import java.awt.geom.Point2D;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ToolbarShape extends Composite {
    private Position _position;
    private Point2D _size;

    public ToolbarShape(Position position, double x, double y, int height, int width) {
        super();
        super.addShape(new Rectangle(x, y, width, height, Color.BLUE));
        super.addShape(new Cercle(10, 80, 90, Color.BLUE));
        List<Point> points = new ArrayList<Point>();
        points.add(new Point(10, 230));
        points.add(new Point(25, 300));
        points.add(new Point(85, 300));
        points.add(new Point(100, 230));
        points.add(new Point(50, 200));
        super.addShape(new Polygon(points, Color.BLUE));
        super.addShape(new Rectangle(0, 800, 140, 200, Color.RED));
        _position = position;
        _size = new Point2D.Double(width, height);
    }

    public  ToolbarShape () {}

    public void draw(Graphics2D g2d) {
        for (Shape shape : getList()) {
            shape.draw(g2d);
        }
    }

    @Override
    public String getInfo() {
        return "Toolbar : " + _position.getX() + " " + _position.getY() + " Taille " + _size.getX() + " " + _size.getY() ;
    }

    @Override
    public ToolbarShape  clone() {
        ToolbarShape clone = new  ToolbarShape();
        for (Shape shape : getList()) {
             clone.addShape(shape.clone());
        }
        return clone;
    }

    @Override
    public Position getPosition() {
        return _position;
    }

    @Override
    public void setPosition(double newX, double newY) {
        _position.setX(newX);
        _position.setY(newY);
    }

    public boolean InsideToolbar(double x,double y){
        System.out.println(x);
        System.out.println(y);
        return x < 0 && y >= 0 && y <= 700;
    }

    public boolean isInTrash(double x, double y) {
        return x < 0 && y >= 800 && y <= 1000;
    }

}
