package xshape.graphic;

import xshape.data.Scene;
import xshape.Toolbarjpanel;
import xshape.data.composite.Shape;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class CommandBar extends Panel {
    private final Scene canvasPanel;

    public CommandBar(Scene canvajpanel, Toolbarjpanel toolbar) {
        this.canvasPanel = canvajpanel;
        setBackground(Color.GRAY);
        setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));

        Button bouton0 = new Button("LOAD");
        Button bouton1 = new Button("SAVE");
        Button bouton2 = new Button("UNDO");
        Button bouton3 = new Button("REDO");


        add(bouton0);
        add(bouton1);
        add(bouton2);
        add(bouton3);

        bouton0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(new Frame(), "Open", FileDialog.LOAD);
                fileDialog.setVisible(true);
                String file = fileDialog.getDirectory() + fileDialog.getFile();
                canvajpanel.load(file);
            }
        });

        bouton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog fileDialog = new FileDialog(new Frame(), "Save", FileDialog.SAVE);
                fileDialog.setVisible(true);
                String file = fileDialog.getDirectory() + fileDialog.getFile();
                canvajpanel.save(file);
            }
        });


        bouton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvajpanel.undo();
                canvajpanel.repaint();
                
            }
        });
        bouton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                canvajpanel.redo();
                canvajpanel.repaint();
                
            }
        });
    }

}


