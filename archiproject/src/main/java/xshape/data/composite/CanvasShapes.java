package xshape.data.composite;

import xshape.data.Position;

import java.awt.*;
import java.awt.geom.Point2D;
public class CanvasShapes extends Composite {
    private Position _position;
    private Point2D _size;

    public CanvasShapes(Position position, double x, double y, int height, int width) {
        super();
        super.addShape(new Rectangle(x, y, width, height, Color.BLUE));
        super.addShape(new Cercle(500, 80, 90, Color.BLUE));
        this._position = position;
        this._size = new Point2D.Double(width, height);
    }
  public CanvasShapes(){}
    public void draw(Graphics2D g2d) {
        for (Shape shape : getList()) {
            shape.draw(g2d);
        }
    }

    public boolean isInsidecanva(double x, double y) {
        return x >= 0 && x <= 1500 && y >= 0 && y <= 1500;
    }

    @Override
    public String getInfo() {
        return "Canvas : " + _position.getX() + " " + _position.getY() + " Taille " + _size.getX() + " " + _size.getY();
    }

    @Override
    public CanvasShapes clone() {
        CanvasShapes clone = new CanvasShapes();
        for (Shape shape : getList()) {
             clone.addShape(shape.clone());
        }
        return clone;
    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(double newX, double newY) {

    }
}

