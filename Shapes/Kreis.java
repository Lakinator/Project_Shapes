package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Kreis implements Shape {
    private final ShapeType type = ShapeType._2D;
    private final static int varLength = 4;
    private double radius = 0, durchmesser = 0, umfang = 0, inhalt = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public int getVarLength() {
        return varLength;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Radius", "Durchmesser", "Umfang", "Inhalt"};
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

    public void updateValues(final JTextArea[] textAreas) {
        setRadius( Double.parseDouble(textAreas[0].getText()) );
        setDurchmesser( Double.parseDouble(textAreas[1].getText()) );
        setUmfang( Double.parseDouble(textAreas[2].getText()) );
        setInhalt( Double.parseDouble(textAreas[3].getText()) );
    }

    public void resetAll() {
        setRadius(0);
        setDurchmesser(0);
        setUmfang(0);
        setInhalt(0);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(double durchmesser) {
        this.durchmesser = durchmesser;
    }

    public double getUmfang() {
        return umfang;
    }

    public void setUmfang(double umfang) {
        this.umfang = umfang;
    }

    public double getInhalt() {
        return inhalt;
    }

    public void setInhalt(double inhalt) {
        this.inhalt = inhalt;
    }
}
