package GUI;

import RenderGUI.ShapeRenderer;
import Shapes.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Window extends JFrame{
    /**
     * Boolean to either print the whole Error StackTrace or just the toString() Message
     */
    private final boolean printAllErrors = false;

    /**
     * The startBtn starts the calculation of the given Parameters of the current selected Shape
     * The resetBtn resets the current selected Shape and sets it to 0
     */
    private JButton startBtn, resetBtn;

    /**
     * These two Labels are above their two ComboBox counterparts with a short Text to indicate what each ComboBox contains
     * shapeList: Contains the available Shapes for the selected Type
     * shapeTypeList: Contains the available Shape Types (that are in the shapeTypeStrings Array)
     */
    private JLabel shapeListLabel;
    private JLabel shapeTypeListLabel;
    private JComboBox<String> shapeList;
    private JComboBox<String> shapeTypeList;

    /**
     * This String Array contains the available Types for the shapeTypeList ComboBox
     */
    private String[] shapeTypeStrings = {"Shape2D", "Shape3D"};

    /**
     * These Labels represent what Parameter in the attached Input Area is shown
     */
    private JLabel[] inputAreaLabels = new JLabel[6];
    private JTextArea[] inputAreas = new JTextArea[6];

    /**
     * This Shape always cntains the active selected Shape. It is accessed through casting it to a specific Shape (Will be changed in the future)
     */
    private Shape currentShape;

    /**
     * The MenuBar with its components
     */
    private JMenuBar bar;
    private JMenu menu1;
    private JMenuItem item1;



    public Window() {
        setTitle("Project X");
        setSize(400, 520);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        if(!init()) {
            System.err.println("Initialisierung fehlgeschlagen");
            System.exit(0);
        }
        else System.out.println("Initialisierung erfolgreich beendet\nRunning...");
    }


    public boolean init() {
        try{

            /**
             * Here does the whole initialization find its place.
             * If something goes wrong and an Exception is thrown, the init() Method returns false and exits the Program
             */

            startBtn = new JButton("Go");
            startBtn.setBounds(90, 400, 100, 50);
            startBtn.addActionListener(new ButtonHandler());
            startBtn.setVisible(true);
            add(startBtn);

            resetBtn = new JButton("Reset");
            resetBtn.setBounds(200, 400, 100, 50);
            resetBtn.addActionListener(new ButtonHandler());
            resetBtn.setVisible(true);
            add(resetBtn);

            shapeTypeList = new JComboBox<>(shapeTypeStrings);
            shapeTypeList.setBounds(20, 40, 150, 20);
            shapeTypeList.addActionListener(new ComboBoxHandler());
            shapeTypeList.setVisible(true);

            shapeList = new JComboBox<>();
            shapeList.setBounds(190, 40, 150, 20);
            shapeList.addActionListener(new ComboBoxHandler());
            shapeList.setVisible(true);

            shapeTypeListLabel = new JLabel("Shape Type");
            shapeTypeListLabel.setBounds(60, 10, 100, 20);
            shapeTypeListLabel.setVisible(true);

            shapeListLabel = new JLabel("Shape");
            shapeListLabel.setBounds(230, 10, 100, 20);
            shapeListLabel.setVisible(true);

            //Menu
            bar = new JMenuBar();
            menu1 = new JMenu("Shape");
            item1 = new JMenuItem("Render this Shape");
            item1.addActionListener(new MenuItemHandler());
            menu1.add(item1);
            bar.add(menu1);
            setJMenuBar(bar);

            add(shapeTypeList);
            add(shapeList);
            add(shapeListLabel);
            add(shapeTypeListLabel);


            /**
             * All Input Areas and their Labels are created in a loop that uses a start offset on the y-axis and is increased by 50 every step
             */

            int tmp_offsetY = 110;
            for (int i = 0; i < inputAreas.length; i++) {
                inputAreas[i] = new JTextArea();
                inputAreaLabels[i] = new JLabel();

                inputAreas[i].setBounds(50, tmp_offsetY, 250, 20);
                inputAreaLabels[i].setBounds(70, tmp_offsetY-20, 200, 20);

                tmp_offsetY += 50;

                inputAreas[i].setVisible(true);
                inputAreaLabels[i].setVisible(true);

                add(inputAreas[i]);
                add(inputAreaLabels[i]);
            }

            shapeTypeList.setSelectedIndex(0);

            setVisible(true);
        } catch(Exception ex) {
            if (printAllErrors)ex.printStackTrace();
            System.err.println("Initialisierungs Fehler (" + ex.toString() + ")");
            return false;
        }

        return true;
    }

    /**
     * This Method sets the currentShape to the selected shapeName to use it in further Methods
     * It also updates the Input Areas with new Shape Params with the updateInputAreas() Method
     *
     * @param shapeName
     *        The name of the new selected Shape
     */

    public void handleNewObject(String shapeName) {
        switch (shapeName) {
            default:
                System.out.println("Undefined Shape");
                break;
            case "Rechteck":
                currentShape = new Rechteck();
                break;
            case "Dreieck (Rechtwinklig)":
                currentShape = new Dreieck();
                break;
            case "Kreis":
                currentShape = new Kreis();
                break;
            case "Kugel":
                currentShape = new Kugel();
                break;

        }

        updateInputAreas(currentShape);
    }

    /**
     * Helper Method to change the shapeList and update it with other Shapes
     *
     * @param objects
     *        Strings that contain the Names of the available Shapes
     */

    public void changeShapeList(String... objects) {
        shapeList.removeAllItems();

        for (String object : objects) {
            shapeList.addItem(object);
        }
    }

    /**
     * Sets the Input Area Labels with the Names of the Parameters from the given Shape.
     * It also hides the Input Areas that aren't used and resets all to 0.0
     *
     * @param s
     *        The Shape to which the Input Areas should be changed
     */

    public void updateInputAreas(Shape s) {
        for (int i = 0; i < inputAreas.length; i++) {
            if(i < s.getVarLength()) {
                inputAreas[i].setVisible(true);
                inputAreas[i].setText("0.0");
                inputAreaLabels[i].setText(s.getVarNames()[i]);
            }
            else {
                inputAreas[i].setVisible(false);
                inputAreas[i].setText(null);
                inputAreaLabels[i].setText(null);
            }
        }
    }

    /**
     * !! Experimental Method !!
     * This Method sets the Input Areas to given Parameters.
     * Prints an Error if there are more values given than Input Areas exist
     *
     * @param values
     *        The Values to set the Input Areas
     */

    public void setInputAreas(double... values) {
        if (values.length > inputAreas.length) System.err.println("Zu viele Parameter für die Felder");

        for (int i = 0; i < values.length; i++) {
            inputAreas[i].setText("" + values[i]);
        }
    }

    /**
     * Helper Method to print out an Error Message that occurred during the Calculation of a Shape
     *
     * @param s
     *        The Shape where the Calculation Error occurred
     * @param Error
     *        A specific Message that will be shown
     */

    public void calculationErrorMsg(Shape s, String Error) {
        System.err.println("[" + s.getClass().getName() + "] Fehler während der Berechnung: \"" + Error + "\"");
    }


    //TODO: Move all Handlers into own files

    /**
     * This ComboBox Handler handles the User input given to the ComboBoxes.
     * If a new Shape Type is selected, it changes the ShapeList to specific Shapes.
     * If a new Shape is selected, it calls the handleNewObject() Method to process the new selected Shape.
     * It prints an Error if an Exception is thrown
     */

    private class ComboBoxHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox comboBox = (JComboBox) e.getSource();

            if(comboBox.getSelectedItem() == "Shape2D") {
                changeShapeList("Rechteck", "Dreieck (Rechtwinklig)", "Kreis");
            } else if(comboBox.getSelectedItem() == "Shape3D") {
                changeShapeList("Kugel");
            } else {
                try{
                    handleNewObject( (String) comboBox.getSelectedItem());
                } catch(Exception ex) {
                    if (printAllErrors)ex.printStackTrace();
                    System.out.println("Dropdown Error (" + ex.toString() + ")");
                }
            }

        }
    }

    /**
     * This Buttonhandler handles the startBtn and resetBtn Events.
     * It first looks which Shape is selected.
     *  -> startBtn: It first updates the Values that were put into the Input Areas.
     *               Then it calls the handleCalculation() Method from the Shape.
     *               If it was successful, it sets the Input Areas to new calculated Values.
     *               If it wasn't successful, it prints an Error Message
     *  -> resetBtn: It resets the the values inside the Shape and then sets the Input Areas to 0.0
     */

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            switch ( (String) shapeList.getSelectedItem()) {

                case "Rechteck":
                    if (e.getSource() == startBtn) {
                        try{
                            ((Rechteck)currentShape).updateValues(inputAreas);
                            if (currentShape.handleCalculations()) setInputAreas(((Rechteck)currentShape).getWidth(), ((Rechteck)currentShape).getLength(), ((Rechteck)currentShape).getUmfang(), ((Rechteck)currentShape).getInhalt(), ((Rechteck)currentShape).getDiagonale());
                            else calculationErrorMsg(currentShape, "Mindestens Länge oder Breite und Umfang, Inhalt oder Diagonale benötigt");
                        } catch (Exception ex) {
                            if (printAllErrors)ex.printStackTrace();
                            System.err.println("Nur Zahlen erlaubt! (" + ex.toString() + ")");
                        }
                    } else if (e.getSource() == resetBtn) {
                        ((Rechteck)currentShape).resetAll();
                        setInputAreas(0.0, 0.0, 0.0, 0.0, 0.0);
                    }
                    break;
                case "Kreis":
                    if (e.getSource() == startBtn) {
                        try{
                            ((Kreis)currentShape).updateValues(inputAreas);
                            if ((currentShape).handleCalculations()) setInputAreas(((Kreis)currentShape).getRadius(), ((Kreis)currentShape).getDurchmesser(), ((Kreis)currentShape).getUmfang(), ((Kreis)currentShape).getInhalt());
                            else calculationErrorMsg(currentShape, "Mindestens ein Parameter benötigt");
                        } catch (Exception ex) {
                            if (printAllErrors)ex.printStackTrace();
                            System.err.println("Nur Zahlen erlaubt! (" + ex.toString() + ")");
                        }
                    } else if (e.getSource() == resetBtn) {
                        ((Kreis)currentShape).resetAll();
                        setInputAreas(0.0, 0.0, 0.0, 0.0, 0.0);
                    }
                    break;
                case "Dreieck (Rechtwinklig)":
                    if (e.getSource() == startBtn) {
                        try{
                            ((Dreieck)currentShape).updateValues(inputAreas);
                            if ((currentShape).handleCalculations()) setInputAreas(((Dreieck)currentShape).getA(), ((Dreieck)currentShape).getB(), ((Dreieck)currentShape).getC(), ((Dreieck)currentShape).getAlpha(), ((Dreieck)currentShape).getBeta(), ((Dreieck)currentShape).getGamma());
                            else calculationErrorMsg(currentShape, "Ein Winkel muss 90 Grad sein, alle zusammen dürfen nicht größer als 180 Grad sein");
                        } catch (Exception ex) {
                            if (printAllErrors)ex.printStackTrace();
                            System.err.println("Nur Zahlen erlaubt! (" + ex.toString() + ")");
                        }
                    } else if (e.getSource() == resetBtn) {
                        ((Dreieck)currentShape).resetAll();
                        setInputAreas(0.0, 0.0, 0.0, 0.0, 0.0);
                    }
                    break;
                case "Kugel":
                    if (e.getSource() == startBtn) {
                        try{
                            ((Kugel)currentShape).updateValues(inputAreas);
                            if (currentShape.handleCalculations()) setInputAreas(((Kugel)currentShape).getRadius(), ((Kugel)currentShape).getDurchmesser(), ((Kugel)currentShape).getVolumen(), ((Kugel)currentShape).getOberflaeche());
                            else calculationErrorMsg(currentShape, "Mindestens ein Parameter benötigt");
                        } catch (Exception ex) {
                            if (printAllErrors)ex.printStackTrace();
                            System.err.println("Nur Zahlen erlaubt! (" + ex.toString() + ")");
                        }
                    } else if (e.getSource() == resetBtn) {
                        ((Kugel)currentShape).resetAll();
                        setInputAreas(0.0, 0.0, 0.0, 0.0, 0.0);
                    }
                    break;


            }

        }
    }

    /**
     * This MenuItemHandler handles the Input given to the MenuBar.
     * Item1: Starts a new RenderWindow with the currentShape to render it
     */

    private class MenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == item1) {
                System.out.println("Starting Render Window...");
                RenderGUI.ShapeRenderer shapeRenderer = new ShapeRenderer(currentShape);
            }
        }
    }

}
