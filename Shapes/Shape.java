package Shapes;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public interface Shape {

    public abstract ShapeType getType();
    public abstract int getVarLength();
    public abstract String[] getVarNames();
    public abstract boolean handleCalculations();
}
