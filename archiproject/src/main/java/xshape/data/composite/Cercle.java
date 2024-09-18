package xshape.data.composite;

import xshape.data.Position;

import java.awt.*;

public class Cercle implements Shape {
    private  int  _diameter;
    private final Position _position;
    private boolean _selected;
    private Color _color;

    public Cercle(double x, double y, int diameter, Color color) {
        _position = new Position(x, y);
        _diameter = diameter;
        _color = color;
    }
    @Override
    public void draw(Graphics g) {
        g.setColor(_color);
        g.fillOval((int)_position.getX(), (int)_position.getY(), _diameter, _diameter);
    }

    public  Shape clone (){
        return  new  Cercle(_position.getX(), _position.getY(),_diameter , _color);
    }

    @Override
    public void setSize(double newWidth, double newHeight) {
        _diameter = (int) newWidth;
    }
    @Override
    public void translate(double x, double y) {
        _position.translate(x, y);
    }

    @Override
    public String getInfo() {
        return "Cercle : \n" + _position.getX() + " " + _position.getY() + " " + _diameter + " " + _color.getRGB();
    }


    @Override
    public boolean isInside(double x, double y) {
        return Math.sqrt(Math.pow(x - getPosition().getX() - (double) _diameter / 2, 2) + Math.pow(y - getPosition().getY() - (double) _diameter / 2, 2)) <= (double) _diameter / 2;
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void select() {
        this._selected = true;
        //System.out.println("shape selected");
        _color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), 100);
    }
    
    @Override
    public void unselect() {
        this._selected = false;
        //System.out.println("shape unselected");
        _color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), 255);
    }

    @Override
    public boolean isSelected() {
       return _selected;
    }

    public void setPosition(double newX, double newY) {
        _position.setX(newX);
        _position.setY(newY);
    }

    @Override
    public void setColor(Color newColor) {
        _color = newColor;
    }

    @Override
    public  Position  getPosition(){
        return  _position;
    }

    @Override
    public double getWidth() {
        return _diameter;
    }

    @Override
    public double getHeight() {
        return _diameter;
    }
}
