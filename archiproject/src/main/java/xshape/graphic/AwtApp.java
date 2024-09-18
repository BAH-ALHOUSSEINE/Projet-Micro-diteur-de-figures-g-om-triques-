package xshape.graphic;

import xshape.data.Scene;
import xshape.Toolbarjpanel;
import xshape.Xshape;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GUIHelper extends Frame {
    public static void showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };


        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends Xshape {
   

    @Override public void run() {
        Scene canvajpanel = new Scene();
        Toolbarjpanel toolbar = new Toolbarjpanel(canvajpanel);
        JPanel component = new JPanel();
        component.setLayout(new BorderLayout());
        component.add(new CommandBar(canvajpanel,toolbar), BorderLayout.NORTH);
        component.add(toolbar, BorderLayout.WEST);
        component.add(canvajpanel, BorderLayout.CENTER);
        component.setPreferredSize(new Dimension(1920, 1080));
        GUIHelper.showOnFrame(component, "XShape Swing/AWT Rendering");
    }
}

