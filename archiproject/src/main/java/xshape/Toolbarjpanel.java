package xshape;

import xshape.data.Position;
import xshape.data.Scene;
import xshape.data.command.MoveShapeCommand;
import xshape.data.composite.Shape;
import xshape.data.composite.ToolbarShape;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.*;
public class Toolbarjpanel extends Panel {
    private ToolbarShape toolbarShape;
    private xshape.data.composite.Shape selectedShape;

    public Toolbarjpanel(Scene panel) {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 70, 10));
        setBackground(Color.gray);

        toolbarShape = new ToolbarShape(new Position(10, 10), 10, 10, 50, 90);
        panel.setToolbar(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (xshape.data.composite.Shape shape : toolbarShape.getList()) {
                    if (shape.isInside(e.getX(), e.getY())) {
                        selectedShape = shape;
                        System.out.println( e.getPoint());
                        MoveShapeCommand addshapecommande = new MoveShapeCommand(panel,panel.toolbarjpanel);
                        panel.executeCommand(addshapecommande);
                        //System.out.println("shape selected2");
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
              
              System.out.println("canvas"+e.getPoint());
                if (selectedShape != null && !toolbarShape.isInside(e.getX(), e.getY()) && !selectedShape.getColor().equals(Color.RED) && !(selectedShape.getPosition().getX()== 0) ) {
                    Shape clonedShape = selectedShape.clone();
                    clonedShape.setPosition((double)e.getX()-170, (double) e.getY()-50);
                    panel.getCanvas().addShape(clonedShape);
                   
                }
                selectedShape = null;
                panel.repaint();
            }
            
        });
        
        
    }


    public void paint(Graphics g) {
      super.paint(g);
      Graphics2D g2d = (Graphics2D) g;
      toolbarShape.draw(g2d);
  } 
    

  public ToolbarShape getToolbar(){
     return toolbarShape;
  }

  public void setToolbar(ToolbarShape toolbarShape){
    this.toolbarShape = toolbarShape;
     
  }
}
