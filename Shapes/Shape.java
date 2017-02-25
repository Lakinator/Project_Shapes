package Shapes;

import javax.swing.JTextArea;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public interface Shape {

    ShapeType getType();
    String[] getVarNames();
    double[] getVariables();
    boolean handleCalculations();
    void resetAll();
    void updateValues(final JTextArea[] textAreas);
    String errorMsg();
}
