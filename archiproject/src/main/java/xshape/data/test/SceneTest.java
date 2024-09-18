package xshape.data.test;
import static org.junit.Assert.*;
import org.junit.Test;
import xshape.Toolbarjpanel;
import xshape.data.Scene;
import xshape.data.command.Command;
import xshape.data.command.EditCommand;
import xshape.data.command.MoveShapeCommand;
import xshape.data.composite.Rectangle;
import xshape.data.composite.Shape;

import java.awt.*;

public class SceneTest {

    @Test
    public void testExecute() {
        Shape shape = new Rectangle(0, 0, 100, 100, Color.RED);
        Command command = new EditCommand(shape, 50, 50, 150, 150, Color.BLUE);

        command.execute();

        // vérifier si la forme a été modifiée correctement
        assertEquals(50, shape.getPosition().getX(), 0.001);
        assertEquals(50, shape.getPosition().getY(), 0.001);
        assertEquals(150, shape.getWidth(), 0.001);

        assertEquals(150, shape.getHeight(), 0.001);
        assertEquals(Color.BLUE, shape.getColor());
    }

    @Test
    public void testUndo() {
        Scene canvajpanel = new Scene();
        Toolbarjpanel toolbar = new Toolbarjpanel(canvajpanel);

        Shape shape = new Rectangle(0, 0, 100, 100, Color.RED);
        canvajpanel.getCanvas().addShape(shape);

        //enregistrer l'état actuel dans le memento (fait manuellement car c'est une action effectué par un mouvement souris)
        MoveShapeCommand addshapecommande = new MoveShapeCommand(canvajpanel,canvajpanel.toolbarjpanel);
        canvajpanel.executeCommand(addshapecommande);

        Command command = new EditCommand(canvajpanel.getCanvas().getList().get(0), 50, 50, 150, 150, Color.BLUE);
        command.execute();
       
        System.out.println(canvajpanel.getCanvas().getList().get(0).getPosition().getX());

       // canvajpanel.undo();
        System.out.println(canvajpanel.getCanvas().getList().get(2).getPosition().getX());
        assertEquals(0, canvajpanel.getCanvas().getList().get(2).getPosition().getX(), 0.001);
        assertEquals(0, canvajpanel.getCanvas().getList().get(2).getPosition().getY(), 0.001);
        assertEquals(100, canvajpanel.getCanvas().getList().get(2).getWidth(), 0.001);
        assertEquals(100,canvajpanel.getCanvas().getList().get(2).getHeight(), 0.001);
        assertEquals(Color.RED, canvajpanel.getCanvas().getList().get(2).getColor());
    }

    @Test
    public void testRedo() {
        Scene canvajpanel = new Scene();
        Toolbarjpanel toolbar = new Toolbarjpanel(canvajpanel);

        Shape shape = new Rectangle(0, 0, 100, 100, Color.RED);
        canvajpanel.getCanvas().addShape(shape);

        MoveShapeCommand addshapecommande = new MoveShapeCommand(canvajpanel, canvajpanel.toolbarjpanel);
        canvajpanel.executeCommand(addshapecommande);

       //enregistrer l'état actuel dans le memento (fait manuellement car c'est une action effectué par un mouvement souris)
     

       Command command = new EditCommand(canvajpanel.getCanvas().getList().get(0), 50, 50, 150, 150, Color.BLUE);
       command.execute();
      
        canvajpanel.undo();

        canvajpanel.redo();

        System.out.println(canvajpanel.getCanvas().getList().get(0).getPosition().getX());
        assertEquals(50, canvajpanel.getCanvas().getList().get(0).getPosition().getX(), 0.001);
        assertEquals(50, canvajpanel.getCanvas().getList().get(0).getPosition().getY(), 0.001);
        assertEquals(150, canvajpanel.getCanvas().getList().get(0).getWidth(), 0.001);
        assertEquals(150,canvajpanel.getCanvas().getList().get(0).getHeight(), 0.001);
        assertEquals(Color.BLUE,canvajpanel.getCanvas().getList().get(0).getColor());
    }


}