package GUI;

import Main.Main;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;

/**
 * 25.02.2017
 * Created by user Schal (Lukas Schalk).
 */

class AboutWindow {
    private JFrame jf;
    private JEditorPane jEdit;
    private JScrollPane scrollPane;

    AboutWindow() {
        jf = new JFrame();

        jf.setTitle("About Window");
        jf.setSize(350, 300);
        jf.setLayout(null);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        jEdit = new JEditorPane();
        jEdit.setEditable(false);
        jEdit.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {

                if(Desktop.isDesktopSupported() && e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    try {
                        Desktop.getDesktop().browse(e.getURL().toURI());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        scrollPane = new JScrollPane(jEdit);
        if (!loadText()) System.out.println("Error");
        scrollPane.setBounds(10, 10, 320, 250);

        jf.add(scrollPane);

        jf.setVisible(true);
    }

    boolean loadText() {
        jEdit.setContentType("text/html");

        jEdit.setText("<html> <h2>About this Program:</h2> <p>With this Program you can create different 2D and 3D Shapes and let them render in a seperate Window.</p> " +
                "<p>This Project is open source and you can look at it <a href=\"https://github.com/Lakinator/Project_Shapes\">here</a>.</p>" +
                "<center><h3><u>Erklärung der verschiedenen Shapes:</u></h3></center>" +
                "<p><strong>Rechteck:</strong> Du musst mindestens die Länge oder die Breite mit entweder dem Umfang, Inhalt oder der Diagonale angeben (oder alle) um die Form renderbar zu machen.</p>" +
                "<p><strong>Dreieck (Rechtwinklig):</strong> Du musst mindestens 2 Seiten und einen Winkel mit 90 Grad angeben (oder alle). Die Hypotenuse muss dabei am größten sein um die Form renderbar zu machen.</p>" +
                "<p><strong>Kreis:</strong> Du musst mindestens einen Parameter angeben (oder alle) um die Form renderbar zu machen.</p>" +
                "<p><strong>Kugel:</strong> Du musst mindestens einen Parameter angeben (oder alle). Diese Form ist noch nicht renderbar.</p>" +
                "<p><strong>Quader:</strong> Du musst mindestens 2 Seiten und das Volumen angeben (oder alle) um die Form renderbar zu machen.</p>" +
                "<p></p>" +
                "<p><center><strong>Version: " + Main.VERSION + " </strong></center></p>" +
                "<p><center>This Project was written and published by Lukas Schalk. More Information on my <a href=\"http://lakinator.bplaced.net/\">Website</a>.</center></p>" +
                " </html>");

        return true;
    }

}
