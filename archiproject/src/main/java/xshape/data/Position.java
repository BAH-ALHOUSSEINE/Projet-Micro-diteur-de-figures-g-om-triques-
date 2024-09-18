package xshape.data;

public class Position {
    private double _x;
    private double _y;

    public Position(double x, double y) {
        _x = x;
        _y = y;
    }

    public double getX() {
        return _x;
    }

    public double getY() {
        return _y;
    }

    public void setX(double x) {
        _x = x;
    }

    public void setY(double y) {
        _y = y;
    }

    public Position clone() {
        return new Position(_x, _y);
    }

    public void translate(double x, double y) {
        _x += x;
        _y += y;
    }

    public void setPosition(int x, int y) {
        this._x = x;
        this._y = y;
    }

}

