package xshape.graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditShapeDialog extends JDialog {
    private final JTextField xTextField;
    private final JTextField yTextField;
    private final JTextField heightTextField;
    private final JTextField widthTextField;
    private final JButton colorButton;

    private double newX, newY, newHeight, newWidth;
    private Color newColor;

    public EditShapeDialog(Frame owner) {
        super(owner, "Edit Shape", true);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("New X:"));
        xTextField = new JTextField();
        panel.add(xTextField);

        panel.add(new JLabel("New Y:"));
        yTextField = new JTextField();
        panel.add(yTextField);

        panel.add(new JLabel("New Height:"));
        heightTextField = new JTextField();
        panel.add(heightTextField);

        panel.add(new JLabel("New Width:"));
        widthTextField = new JTextField();
        panel.add(widthTextField);

        panel.add(new JLabel("New Color:"));
        colorButton = new JButton();
        colorButton.setBackground(Color.BLACK); // Couleur par défaut
        colorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color selectedColor = JColorChooser.showDialog(EditShapeDialog.this, "Choose a Color", colorButton.getBackground());
                if (selectedColor != null) {
                    colorButton.setBackground(selectedColor);
                }else{
                    colorButton.setBackground(Color.BLACK);
                }
            }
        });
        panel.add(colorButton);

        JButton okButton = new JButton("Ok");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    newX = Double.parseDouble(xTextField.getText());
                    newY = Double.parseDouble(yTextField.getText());
                    newHeight = Double.parseDouble(heightTextField.getText());
                    newWidth = Double.parseDouble(widthTextField.getText());
                    newColor = colorButton.getBackground();
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditShapeDialog.this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(okButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(owner);
    }

    public double getNewX() {
        return newX;
    }

    public double getNewY() {
        return newY;
    }

    public double getNewHeight() {
        return newHeight;
    }

    public double getNewWidth() {
        return newWidth;
    }

    public Color getNewColor() {
        return newColor;
    }
}
