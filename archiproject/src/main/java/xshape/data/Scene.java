package xshape.data;

import xshape.CanvasMouseHandler;
import xshape.Toolbarjpanel;
import xshape.data.command.Command;
import xshape.data.composite.CanvasShapes;
import xshape.data.composite.Cercle;
import xshape.data.composite.Group;
import xshape.data.composite.Polygon;
import xshape.data.composite.Rectangle;
import xshape.data.composite.Shape;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class Scene extends Panel {
    
    private CanvasShapes _canvasShapes;
    public Toolbarjpanel toolbarjpanel;
    private xshape.data.composite.Shape _selectedShapedelete;
    private final List<Shape> _selectedShapeList = new ArrayList<>();
    private final List<Command> _commands = new ArrayList<>();
    private final List<Command> _redoCommands = new ArrayList<>();

    public Scene() {
        setLayout(new FlowLayout(FlowLayout.RIGHT, 70, 10));
        _canvasShapes = new CanvasShapes(new Position(0, 0), 100,100, 50, 90);
        CanvasMouseHandler mouseHandler = new CanvasMouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    public void executeCommand(Command command) {
         command.execute();
        _commands.add(command);
    }

    public Shape loadRec(String fileName, String currentLine, BufferedReader reader) {
        List<Shape> shapes = _canvasShapes.getList();
        shapes.clear();
        try {
            String line;
            while (currentLine != null) {
                String[] parts = currentLine.split(":");
                String type = parts[0].trim(); // Extract the type of shape

                if (type.equals("Cercle")) {
                    String lineCur = reader.readLine();
                    String[] partsCur = lineCur.split(" ");
                    double x = Double.parseDouble(partsCur[0].trim());
                    double y = Double.parseDouble(partsCur[1].trim());
                    int diameter = Integer.parseInt(partsCur[2].trim());
                    Color color = Color.decode(partsCur[3].trim());
                    Shape shape = new Cercle(x, y, diameter, color);
                    shapes.add(shape);
                } else if (type.equals("Rectangle")) {
                    String lineCur = reader.readLine();
                    String[] partsCur = lineCur.split(" ");
                    double x = Double.parseDouble(partsCur[0].trim());
                    double y = Double.parseDouble(partsCur[1].trim());
                    int width = Integer.parseInt(partsCur[2].trim());
                    int height = Integer.parseInt(partsCur[3].trim());
                    Color color = new Color(Integer.parseInt(partsCur[4].trim()));
                    Shape shape = new Rectangle(x, y, width, height, color);
                    shapes.add(shape);
                } else if (type.equals("Polygon")) {
                    String lineCur = reader.readLine();
                    String[] partsCur = lineCur.split(" ");
                    List<Point> points = new ArrayList<>();
                    for (int i = 0; i < partsCur.length / 2; i++) {
                        double x = Double.parseDouble(partsCur[i * 2].trim());
                        double y = Double.parseDouble(partsCur[i * 2 + 1].trim());
                        points.add(new Point((int) x, (int) y));
                    }
                    Color color = new Color(Integer.parseInt(partsCur[partsCur.length - 1].trim()));
                    Shape shape = new Polygon(points, color);
                    shapes.add(shape);
                }


                else if (type.equals("Group")) {
                    Group group = new Group();
                    shapes.add(group);
                    if (parts.length > 1 && parts[1].trim().equals("End of group")) {
                        return null;
                    }
                    currentLine = reader.readLine();
                    group.addShape(loadRec(fileName, currentLine, reader));
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier " + fileName);
        }
        return null;
    }

    public void load(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String firstLine = reader.readLine();
            loadRec(fileName, firstLine, reader);
            repaint();
            System.out.println("Les formes ont été chargées depuis le fichier " + fileName);
        } catch (IOException e) {
            System.out.println("Les formes n'ont pas pu être chargées depuis le fichier " + fileName);
        }
    }

    public void save(String fileName) {

        try {
            FileWriter writer = new FileWriter(fileName);
            List<Shape> shapes = this.getCanvas().getList();
            for (Shape shape : shapes) {
                writer.write(shape.getInfo() + "\n");
            }
            writer.close();

            System.out.println("Les formes ont été enregistrées dans le fichier " + fileName);
        } catch (IOException e) {
            System.out.println("Les formes n'ont pas pu être enregistrées dans le fichier " + fileName);
        }
    }

    public void undo() {
        if (!_commands.isEmpty()) {
            Command command = _commands.get(_commands.size() - 1);
            //System.out.println(_commands.size()-1);
            _redoCommands.add(command);
            command.undo();
            _commands.remove(command);
        }
    }
    public void redo() {
        if (!_redoCommands.isEmpty()) {
            Command command = _redoCommands.get(_redoCommands.size() - 1);
            command.redo();
            _commands.add(command);
            _redoCommands.remove(command);
        }
    }

    public xshape.data.composite.Shape getSlectShape(){
         return _selectedShapedelete;
    }

    public List<xshape.data.composite.Shape> getSelectShape(){
        return _selectedShapeList;
    }

    public void setSlectShape(xshape.data.composite.Shape shape){
        _selectedShapedelete = shape;
    }

    public void addSelectShape(xshape.data.composite.Shape shape){
        _selectedShapeList.add(shape);
    }

    public void removeSelectShape(xshape.data.composite.Shape shape){
        _selectedShapeList.remove(shape);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        _canvasShapes.draw(g2d);
    } 

    public CanvasShapes getCanvas(){
         return _canvasShapes;
    }

    public  void setCanvas(CanvasShapes canvasShapes){
        this._canvasShapes = canvasShapes;
    }

    public void setToolbar(Toolbarjpanel toolbar) {
        this.toolbarjpanel = toolbar;
    }

    public void renite_liste(){
        _selectedShapeList.clear();
    }
}
