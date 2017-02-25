package RenderGUI;

import javax.swing.*;
import java.awt.*;

/**
 * 25.02.2017
 * Created by user Schal (Lukas Schalk).
 */

public class ShapeSettingsWindow {
    private JFrame jf;
    private JButton colorBtn;
    static Color color = Color.RED;

    public ShapeSettingsWindow() {
        jf = new JFrame();

        jf.setTitle("Render Settings Window");
        jf.setSize(350, 300);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        //JC
        colorBtn = new JButton("Farbe wählen");
        colorBtn.setBounds(10, 20, 150, 80);
        colorBtn.setBackground(color);
        colorBtn.setFocusPainted(false);
        colorBtn.addActionListener(e -> {
            color = JColorChooser.showDialog(null, "Wähle eine custom Farbe", color);
            colorBtn.setBackground(color);
        });

        jf.add(colorBtn);

        jf.setVisible(true);
    }
}
