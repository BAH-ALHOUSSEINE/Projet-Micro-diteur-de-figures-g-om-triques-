package xshape.data.composite;

import xshape.data.Position;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Polygon implements Shape {
    private final List<Point> _points;
    private Color _color;
    private boolean _selected;

    public Polygon(List<Point> points, Color color) {
        _points = points;
        _color = color;
    }

    public void draw(Graphics g) {
        int[] xPoints = new int[_points.size()];
        int[] yPoints = new int[_points.size()];
        for (int i = 0; i < _points.size(); i++) {
            xPoints[i] = (int) _points.get(i).getX();
            yPoints[i] = (int) _points.get(i).getY();
        }
        g.setColor(_color);
        g.fillPolygon(xPoints, yPoints, _points.size());
    }

    @Override
    public Position getPosition() {
        double sumX = 0, sumY = 0;
        for (Point point : _points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        double centerX = sumX / _points.size();
        double centerY = sumY / _points.size();
        return new Position(centerX, centerY);
    }

    @Override
    public double getWidth() {
        double minX = _points.get(0).getX();
        double maxX = _points.get(0).getX();
        for (Point point : _points) {
            if (point.getX() < minX) {
                minX = point.getX();
            }
            if (point.getX() > maxX) {
                maxX = point.getX();
            }
        }
        return maxX - minX;
    }

    @Override
    public double getHeight() {
        double minY = _points.get(0).getY();
        double maxY = _points.get(0).getY();
        for (Point point : _points) {
            if (point.getY() < minY) {
                minY = point.getY();
            }
            if (point.getY() > maxY) {
                maxY = point.getY();
            }
        }
        return maxY - minY;
    }

    @Override
    public void setPosition(double newX, double newY) {
        double deltaX = newX - getPosition().getX();
        double deltaY = newY - getPosition().getY();
        for (Point point : _points) {
            point.translate((int) deltaX, (int) deltaY);
        }
    }

    @Override
    public Shape clone() {
        List<Point> clonedPoints = new ArrayList<>();
        for (Point point : _points) {
            clonedPoints.add(new Point((int) point.getX(),(int) point.getY()));
        }
        return new Polygon(clonedPoints, _color);
    }

    @Override
    public void setSize(double newWidth, double newHeight) {
        // Non applicable pour un polygone car sa taille est déterminée par ses points
    }

    @Override
    public void translate(double x, double y) {
        for (Point point : _points) {
            point.translate((int)x,(int) y);
        }
    }

    @Override
    public String getInfo() {
        StringBuilder info = new StringBuilder("Polygon :\n");
        for (Point point : _points) {
            info.append(point.getX()).append(" ").append(point.getY()).append(" ");
        }
        info.append(_color.getRGB());
        return info.toString();
    }


    @Override
    public boolean isInside(double x, double y) {
        int i, j;
        boolean result = false;
        for (i = 0, j = _points.size() - 1; i < _points.size(); j = i++) {
            if ((_points.get(i).getY() > y) != (_points.get(j).getY() > y) &&
                    (x < (_points.get(j).getX() - _points.get(i).getX()) * (y - _points.get(i).getY()) / (_points.get(j).getY() - _points.get(i).getY()) + _points.get(i).getX())) {
                result = !result;
            }
        }
        return result;
    }

    @Override
    public Color getColor() {
        return _color;
    }

    @Override
    public void setColor(Color newColor) {
        _color = newColor;
    }

    @Override
    public void select() {
        _selected = true;
        _color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), 100);
    }

    @Override
    public void unselect() {
        _selected = false;
        _color = new Color(_color.getRed(), _color.getGreen(), _color.getBlue(), 255);
    }

    @Override
    public boolean isSelected() {
        return _selected;
    }
}
