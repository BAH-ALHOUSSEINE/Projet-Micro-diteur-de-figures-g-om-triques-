package xshape.data.composite;

import xshape.data.Position;

import java.awt.*;

public interface Shape {
    public void translate(double x, double y);
    public String getInfo();

    public void draw(Graphics g);

    public boolean isInside(double x, double y);
    Shape clone();

    public Color getColor();

    public void select();
    public void setSize(double newWidth, double newHeight);

    public void unselect();

    public boolean isSelected();

    public Position getPosition();
    public double getWidth();
    public double getHeight();

    public void setPosition(double newX, double newY);
    public void setColor(Color newColor);
}

