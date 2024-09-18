package xshape.data.command;

import xshape.data.Memento;
import xshape.data.Scene;
import xshape.Toolbarjpanel;
import xshape.data.composite.Shape;

public class MoveShapeCommand implements Command {
    private final Scene _canvajpanel;
    private final Toolbarjpanel _toolbarscene;
    private Shape _shape;
    private Memento _memento1;
    private Memento _memento2;
    private double x, y;

    public MoveShapeCommand(Scene canvajpanel,Toolbarjpanel toolbarScene) {
        _canvajpanel = canvajpanel;
        this._toolbarscene = toolbarScene;
    }

    @Override
    public void execute() {
        _memento1 = new Memento(_canvajpanel.getCanvas().clone(),_toolbarscene.getToolbar().clone());
    }

    public Memento getMemento1() {
        return _memento1;
    }
    public Memento getMemento2() {return _memento2;}

    @Override
    public void undo() {
        _memento2 = new Memento(_canvajpanel.getCanvas().clone(),_toolbarscene.getToolbar().clone());
        _canvajpanel.setCanvas(getMemento1().getCanvas());
        _toolbarscene.setToolbar(getMemento1().getToolbarShape());
        _canvajpanel.repaint();;
        _toolbarscene.repaint();
    }

    @Override
    public void redo() {
        _canvajpanel.setCanvas(getMemento2().getCanvas());
        _toolbarscene.setToolbar(getMemento2().getToolbarShape());
        _canvajpanel.repaint();;
        _toolbarscene.repaint();
    }
}
