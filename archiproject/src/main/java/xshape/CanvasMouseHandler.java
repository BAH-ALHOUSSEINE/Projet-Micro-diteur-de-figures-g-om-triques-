package xshape;

import xshape.data.Scene;
import xshape.data.command.*;
import xshape.data.composite.Group;
import xshape.data.composite.Shape;
import xshape.graphic.EditShapeDialog;

import java.awt.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CanvasMouseHandler extends MouseAdapter {
    private final Scene _canvajpanel;
    private Point _startDragPoint = new Point();
    Group subgroup;
    public CanvasMouseHandler(Scene _canvajpanel) {
        this._canvajpanel = _canvajpanel;
    }


    @Override
    public void mouseDragged(MouseEvent e) {
        int startX = _startDragPoint.x;
        int startY = _startDragPoint.y;

        int currentX = e.getX();
        int currentY = e.getY();

        int minX = Math.min(startX, currentX);
        int minY = Math.min(startY, currentY);
        int maxX = Math.max(startX, currentX);
        int maxY = Math.max(startY, currentY);

        Shape selectedShape = _canvajpanel.getSlectShape();
        if (selectedShape != null && _canvajpanel.getCanvas().isInsidecanva(currentX, currentY)) {
            selectedShape.setPosition((double) e.getX(), (double) e.getY());

            _canvajpanel.repaint();
        }else{
            int width = Math.abs(startX - currentX);
            int height = Math.abs(startY - currentY);
            Graphics g = _canvajpanel.getGraphics();
            g.setColor(Color.BLUE);
            g.drawRect(minX, minY, width, height);
            g.dispose();

            for (Shape shape : _canvajpanel.getCanvas().getList()) {
                if (shape.getPosition().getX() >= minX && shape.getPosition().getX() <= maxX &&
                        shape.getPosition().getY() >= minY && shape.getPosition().getY() <= maxY) {
                    shape.select();
                    _canvajpanel.addSelectShape(shape);
                } else {
                    shape.unselect();
                    _canvajpanel.removeSelectShape(shape);
                }
            }
            _canvajpanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        _startDragPoint = e.getPoint();
        System.out.println("Mouse pressed");
        //print toute les formes selectionner
        for (Shape shape : _canvajpanel.getSelectShape()) {
            System.out.println(shape);
        }

        boolean ctrlPressed = e.isControlDown();
        MoveShapeCommand addshapecommande = new MoveShapeCommand(_canvajpanel,_canvajpanel.toolbarjpanel);
        _canvajpanel.executeCommand(addshapecommande);

        Shape selectedShape = null;

        if (!ctrlPressed && !(e.getButton() == MouseEvent.BUTTON3)) {
            for (Shape shape : _canvajpanel.getCanvas().getList()) {
                shape.unselect();
            }
        }
        
        for (Shape shape : _canvajpanel.getCanvas().getList()) {
            if (shape.isInside(e.getX(), e.getY())) {
                selectedShape = shape;
                if (ctrlPressed) {
                    if (shape.isSelected()) {
                        shape.unselect();
                        _canvajpanel.removeSelectShape(shape);
                    } else {
                        shape.select();
                        _canvajpanel.addSelectShape(shape);
                    }
                }
                _canvajpanel.repaint();
                break;
            }
        }
        _canvajpanel.setSlectShape(selectedShape);

        if (e.getButton() == MouseEvent.BUTTON3 && selectedShape != null) {
            JPopupMenu popupMenu = createPopupMenu(selectedShape);
            popupMenu.show(_canvajpanel, e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Shape selectedShape = _canvajpanel.getSlectShape();
        if (selectedShape != null && _canvajpanel.toolbarjpanel.getToolbar().isInTrash(e.getX(), e.getY())) {
            System.out.println("Laché dans la poubelle");
            _canvajpanel.getCanvas().removeShape(selectedShape);
            _canvajpanel.toolbarjpanel.getToolbar().removeShape(selectedShape);
            _canvajpanel.repaint();
            _canvajpanel.toolbarjpanel.repaint();
            return ;
        }
        if (selectedShape != null && _canvajpanel.getCanvas().isInsidecanva(e.getX(), e.getY()) && !selectedShape.isInside(e.getX(), e.getY())) {
            System.out.println("Laché sur le canvas");
            _canvajpanel.repaint();
        }

        if (selectedShape != null && _canvajpanel.toolbarjpanel.getToolbar().InsideToolbar(e.getX(), e.getY())&& !selectedShape.isInside(e.getX(), e.getY())) {
            System.out.println("Laché sur la toolbar");
            Shape clonedShape = selectedShape.clone();
            
            _canvajpanel.getCanvas().removeShape(selectedShape);
            clonedShape.setPosition((double) e.getX()+95, (double) e.getY());
            
            _canvajpanel.repaint();
            _canvajpanel.toolbarjpanel.getToolbar().addShape(clonedShape);
            _canvajpanel.toolbarjpanel.repaint();
          
        }
    }

    private JPopupMenu createPopupMenu(Shape selectedShape) {
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem editMenuItem = new JMenuItem("Edit");
        editMenuItem.addActionListener(e -> {
            EditShapeDialog editShapeDialog = new EditShapeDialog((Frame) SwingUtilities.getWindowAncestor(_canvajpanel));
            editShapeDialog.setVisible(true);

            double newX = editShapeDialog.getNewX();
            double newY = editShapeDialog.getNewY();
            double newHeight = editShapeDialog.getNewHeight();
            double newWidth = editShapeDialog.getNewWidth();
            Color newColor = editShapeDialog.getNewColor();

            Command editCommand = new EditCommand(selectedShape, newX, newY, newWidth, newHeight ,newColor);
            editCommand.execute();
            _canvajpanel.repaint();
            _canvajpanel.toolbarjpanel.repaint();
        });
        popupMenu.add(editMenuItem);

        JMenuItem createSubgroupMenuItem = new JMenuItem("Create Subgroup");
        createSubgroupMenuItem.addActionListener(e -> {
            List<Shape> selectedShapes = _canvajpanel.getSelectShape();


            subgroup = new Group();

            for (Shape shape : selectedShapes) {
                if (shape.isSelected()){
                    subgroup.addShape(shape);
                    _canvajpanel.getCanvas().removeShape(shape);
                    System.out.println("subgroup created");
                }
            }

            _canvajpanel.getCanvas().addShape(subgroup);
            _canvajpanel.renite_liste();
            _canvajpanel.repaint();
        });
        popupMenu.add(createSubgroupMenuItem);

        JMenuItem createdegroupe = new JMenuItem("Degroup");
        createdegroupe.addActionListener(e -> {
           
             Group   grList = subgroup;

             for( Shape  shape : grList.getList()){

                _canvajpanel.getCanvas().addShape(shape); 
             }

             _canvajpanel.getCanvas().removeShape(subgroup);
             _canvajpanel.repaint();

        });
        popupMenu.add(createdegroupe );

        JMenuItem deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(e -> {
            _canvajpanel.removeSelectShape(selectedShape);
            _canvajpanel.getCanvas().removeShape(selectedShape);
            _canvajpanel.toolbarjpanel.getToolbar().removeShape(selectedShape);
            _canvajpanel.repaint();
            _canvajpanel.toolbarjpanel.repaint();
        });
        popupMenu.add(deleteMenuItem);

        return popupMenu;
    }



}
