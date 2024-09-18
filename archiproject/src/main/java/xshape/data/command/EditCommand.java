package xshape.data.command;

import xshape.data.Memento;
import xshape.data.composite.Shape;
import java.awt.*;

public class EditCommand implements Command {
    private final Shape _shape;
    private final double _newX;
    private final double _newY;
    private final double _newWidth;
    private final double _newHeight;
    private final Color _newColor;

    public EditCommand(Shape shape, double newX, double newY, double newWidth,double newHeight ,Color newColor) {
        this._shape = shape;
        this._newX = newX;
        this._newY = newY;
        this._newWidth = newWidth;
        this._newHeight = newHeight;
        this._newColor = newColor;

    }

    @Override
    public void execute() {
        _shape.setPosition(_newX, _newY);
        _shape.setSize(_newWidth, _newHeight);
        _shape.setColor(_newColor);
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
