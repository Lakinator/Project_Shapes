package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Kreis implements Shape {
    private final ShapeType type = ShapeType._2D;
    private double radius = 0, durchmesser = 0, umfang = 0, inhalt = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Radius", "Durchmesser", "Umfang", "Inhalt"};
    }

    @Override
    public double[] getVariables() {
        return new double[]{radius, durchmesser, umfang, inhalt};
    }

    @Override
    public boolean handleCalculations() {

        if(radius != 0) {
            durchmesser = radius*2;
            umfang = 2 * Math.PI * radius;
            inhalt = Math.PI * (radius*radius);
            return true;
        }

        if(durchmesser != 0) {
            radius = durchmesser / 2;
            umfang = 2 * Math.PI * radius;
            inhalt = Math.PI * (radius*radius);
            return true;
        }

        if(umfang != 0) {
            radius = umfang / (2 * Math.PI);
            durchmesser = radius*2;
            inhalt = Math.PI * (radius*radius);
            return true;
        }

        if(inhalt != 0) {
            radius = Math.sqrt(inhalt / Math.PI);
            durchmesser = radius*2;
            umfang = 2 * Math.PI * radius;
            return true;
        }


        return false;
    }

    @Override
    public void updateValues(JTextArea[] textAreas) {
        radius = Double.parseDouble(textAreas[0].getText());
        durchmesser = Double.parseDouble(textAreas[1].getText());
        umfang = Double.parseDouble(textAreas[2].getText());
        inhalt = Double.parseDouble(textAreas[3].getText());
    }

    @Override
    public String errorMsg() {
        return "Mindestens ein Parameter benötigt";
    }

    @Override
    public void resetAll() {
        radius = 0;
        durchmesser = 0;
        umfang = 0;
        inhalt = 0;
    }

    @Override
    public String toString() {
        return "{Radius: " + radius + ", Durchmesser: " + durchmesser + ", Umfang: " + umfang + ", Inhalt: " + inhalt + "}";
    }
}
