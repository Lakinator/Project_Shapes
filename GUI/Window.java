package GUI;

import RenderGUI.ShapeRenderWindow;
import RenderGUI.ShapeSettingsWindow;
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
     * This Shape always contains the active selected Shape.
     */
    private Shape currentShape;

    /**
     * The MenuBar with its components
     */
    private JMenuBar bar;
    private JMenu[] menus = new JMenu[2];
    private JMenuItem[] items = new JMenuItem[3];



    public Window() {
        setTitle("Project Shapes");
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
            menus[0] = new JMenu("Shape");
            menus[1] = new JMenu("Settings");
            items[0] = new JMenuItem("Render this Shape");
            items[0].setEnabled(false);
            items[1] = new JMenuItem("Render Settings");
            items[2] = new JMenuItem("Other Settings");
            items[0].addActionListener(new MenuItemHandler());
            items[1].addActionListener(new MenuItemHandler());
            items[2].addActionListener(new MenuItemHandler());
            menus[0].add(items[0]);
            menus[1].add(items[1]);
            menus[1].add(items[2]);
            bar.add(menus[0]);
            bar.add(menus[1]);
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
        items[0].setEnabled(false);
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
            if(i < s.getVariables().length) {
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
     */

    public void calculationErrorMsg(Shape s) {
        System.err.println("[" + s.getClass().getName() + "] Fehler während der Berechnung: \"" + s.errorMsg() + "\"");
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
     *  -> startBtn: It calls the handleCalculation() Method from the Shape.
     *               If it was successful, it sets the Input Areas to new calculated Values.
     *               If it wasn't successful, it prints an Error Message
     *  -> resetBtn: It resets the the values inside the Shape and then sets all Input Areas to 0.0
     */

    private class ButtonHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startBtn) {
                try{
                    currentShape.updateValues(inputAreas);
                    if (currentShape.handleCalculations()) {
                        setInputAreas(currentShape.getVariables());
                        items[0].setEnabled(true);
                    }
                    else {
                        calculationErrorMsg(currentShape);
                        items[0].setEnabled(false);
                    }
                } catch (Exception ex) {
                    if (printAllErrors)ex.printStackTrace();
                    System.err.println("Nur Zahlen erlaubt! (" + ex.toString() + ")");
                }
            } else if (e.getSource() == resetBtn) {
                currentShape.resetAll();
                setInputAreas(currentShape.getVariables());
            }

        }
    }

    /**
     * This MenuItemHandler handles the Input given to the MenuBar.
     * Item0: Starts a new ShapeRenderWindow with the currentShape to render it
     * Item1: Starts a new ShapeSettingsWindow to manage the Render Settings
     * Item2: Starts a new OtherSettingsWindow (Coming Soon)
     */

    private class MenuItemHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == items[0]) {
                System.out.println("Starting Render Window...");
                ShapeRenderWindow shapeRenderWindow = new ShapeRenderWindow(currentShape);
            } else if (e.getSource() == items[1]) {
                System.out.println("Starting Render Settings Window...");
                ShapeSettingsWindow shapeSettingsWindow = new ShapeSettingsWindow();
            } else if (e.getSource() == items[2]) {
                //Coming Soon
            }
        }
    }

}
