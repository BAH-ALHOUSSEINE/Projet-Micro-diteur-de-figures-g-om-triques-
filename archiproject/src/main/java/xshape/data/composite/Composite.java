package xshape.data.composite;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Composite implements Shape {
    private final List<Shape> _shapes;

    public Composite() {
        _shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        _shapes.add(shape);
    }

    public void removeShape(Shape shape) {
        _shapes.remove(shape);
    }

    public void setSize(double newWidth, double newHeight) {
        System.out.println("setSize() impossible for Toolbar or CommandBar");
    }

    @Override
    public double getWidth() {
        System.out.println("getWidth() impossible for Toolbar or CommandBar");
        return 0;
    }
    @Override
    public abstract Shape clone();

    @Override
    public double getHeight() {
        System.out.println("getHeight() impossible for Toolbar or CommandBar");
        return 0;
    }

    @Override
    public void translate(double x, double y) {
        for (Shape shape : _shapes) {
            shape.translate(x, y);
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : _shapes) {
            shape.draw(g);
        }
    }

    @Override
    public boolean isInside(double x, double y) {
        for (Shape shape : _shapes) {
            if (shape.isInside(x, y)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Color getColor() {
        System.out.println("getColor() impossible for many shapes");
        return null;
    }

    @Override
    public void select() {
        for (Shape shape : _shapes) {
            shape.select();
        }
    }

    @Override
    public void unselect() {
        for (Shape shape : _shapes) {
            shape.unselect();
        }
    }

    @Override
    public boolean isSelected() {
        for (Shape shape : _shapes) {
            if (shape.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public List<Shape>  getList(){
        return _shapes;
    }


    @Override
    public void setColor(Color newColor) {
        //envoyer la couleur à chaque shape
        for (Shape shape : _shapes) {
            shape.setColor(newColor);
        }
    }
}
