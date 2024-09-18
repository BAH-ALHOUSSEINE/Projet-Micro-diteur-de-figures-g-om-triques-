package xshape.data.composite;

import xshape.data.Position;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {
    private final List<Shape> _shapes;
    private boolean selected;

    public Group() {
        _shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        _shapes.add(shape);
    }

    @Override
    public void translate(double x, double y) {
        for (Shape shape : _shapes) {
            shape.translate(x, y);
        }
    }



    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder("Group : \n");
        for (Shape shape : _shapes) {
            info.append("  ");
            info.append(shape.getInfo()).append("\n");
        }
        info.append("End of group\n");
        return info.toString();
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
    public Shape clone() {
        Group cloneGroup = new Group();
        for (Shape shape : _shapes) {
            cloneGroup.addShape(shape.clone());
        }
        return cloneGroup;
    }

    @Override
    public void select() {
        selected = true;
        for (Shape shape : _shapes) {
            shape.select();
        }
    }

    @Override
    public void setSize(double newWidth, double newHeight) {
        for (Shape shape : _shapes) {
            shape.setSize(newWidth, newHeight);
        }
    }

    @Override
    public void unselect() {
        selected = false;
        for (Shape shape : _shapes) {
            shape.unselect();
        }
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public Position getPosition() {
        if (_shapes.isEmpty()) {
            return null;
        }

        double totalX = 0;
        double totalY = 0;

        for (Shape shape : _shapes) {
            Position position = shape.getPosition();
            totalX += position.getX();
            totalY += position.getY();
        }

        double avgX = totalX / _shapes.size();
        double avgY = totalY / _shapes.size();

        return new Position(avgX, avgY);
    }

    @Override
    public double getWidth() {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;

        for (Shape shape : _shapes) {
            Position position = shape.getPosition();
            double x = position.getX();
            if (x < minX) {
                minX = x;
            }
            if (x > maxX) {
                maxX = x;
            }
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Shape shape : _shapes) {
            Position position = shape.getPosition();
            double y = position.getY();
            if (y < minY) {
                minY = y;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
        return maxY - minY;
    }

    @Override
    public void setPosition(double newX, double newY) {
        Position groupPosition = getPosition();
        double deltaX = newX - groupPosition.getX();
        double deltaY = newY - groupPosition.getY();

        for (Shape shape : _shapes) {
            Position currentPosition = shape.getPosition();
            double currentX = currentPosition.getX();
            double currentY = currentPosition.getY();

            shape.setPosition(currentX + deltaX, currentY + deltaY);
        }
    }



    @Override
    public Color getColor() {
        System.out.println("getColor() impossible for many shapes" );
        return null;
    }

    @Override
    public void setColor(Color newColor) {
        for (Shape shape : _shapes) {
            shape.setColor(newColor);
        }
    }
    public List <Shape> getList (){
         return  _shapes;
    }
}
