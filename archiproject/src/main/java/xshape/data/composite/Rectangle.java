package xshape.data.composite;

import xshape.data.Position;

import java.awt.*;

public class Rectangle  implements Shape{
    private int _width, _height;
    private final Position _position;
    private boolean _selected;
    private Color _color;

    public Rectangle(double x, double y, int width, int height, Color color) {
        _position = new Position(x, y);
        _width = width;
        _height = height;
        _color = color;
    }

    public void draw(Graphics g) {
        g.setColor(_color);
        g.fillRect((int)_position.getX(), (int ) _position.getY(), _width, _height);
    }
    @Override
    public  Position  getPosition(){
      return  _position;
  }

    @Override
    public double getWidth() {
        return _width;
    }

    @Override
    public double getHeight() {
        return _height;
    }

    public Shape clone (){
        return  new  Rectangle(this._position.getX(), this._position.getY(), this._width, this._height, this._color);
    }

    @Override
    public void setSize(double newWidth, double newHeight) {
        this._width = (int) newWidth;
        this._height = (int) newHeight;
    }

    @Override
    public void translate(double x, double y) {
        _position.translate(x, y);
    }

    @Override
    public String getInfo() {
        return "Rectangle :\n" + _position.getX() + " " + _position.getY() + " " + _width + " " + _height + " " + _color.getRGB();
    }

    @Override
    public boolean isInside(double x, double y) {
        return x >= getPosition().getX() && x <= getPosition().getX() + _width && y >= getPosition().getY() && y <= getPosition().getY() + _height;
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void select() {
        this._selected = true;

        _color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), 100);
    }

    @Override
    public void unselect() {
        this._selected = false;
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
}
