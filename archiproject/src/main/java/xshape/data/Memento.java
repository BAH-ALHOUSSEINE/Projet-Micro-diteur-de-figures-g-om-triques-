package xshape.data;

import xshape.data.composite.CanvasShapes;
import xshape.data.composite.ToolbarShape;

public class Memento {

    private final CanvasShapes _canvasShapes;
    private final ToolbarShape  _toolbarShape;
    public Memento(CanvasShapes canvasShapes,ToolbarShape toolbarShape) {
        _canvasShapes = canvasShapes;
        this._toolbarShape= toolbarShape;
    }
    public CanvasShapes getCanvas() {
        return _canvasShapes;
    }

    public ToolbarShape  getToolbarShape(){
         return  _toolbarShape;
    }
    
}
