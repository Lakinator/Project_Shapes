package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Rechteck implements Shape {
    private final ShapeType type = ShapeType._2D;
    private double width = 0, length = 0, umfang = 0, inhalt = 0, diagonale = 0;


    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Breite", "Länge", "Umfang", "Inhalt", "Diagonale"};
    }

    @Override
    public double[] getVariables() {
        return new double[]{width, length, umfang, inhalt, diagonale};
    }

    @Override
    public boolean handleCalculations() {

        if (width != 0 && length != 0) {
            umfang = (2*width) + (2*length);
            inhalt = width * length;
            diagonale = Math.sqrt( (width*width) + (length*length) );
            return true;
        }

        if (inhalt != 0 && length != 0) {
            width = inhalt / length;
            umfang = (2*width) + (2*length);
            diagonale = Math.sqrt( (width*width) + (length*length) );
            return true;
        }

        if (inhalt != 0 && width != 0) {
            length = inhalt / width;
            umfang = (2*width) + (2*length);
            diagonale = Math.sqrt( (width*width) + (length*length) );
            return true;
        }

        if(umfang != 0 && length != 0) {
            width = (umfang - (2*length)) / 2;
            inhalt = width * length;
            diagonale = Math.sqrt( (width*width) + (length*length) );
            return true;
        }

        if(umfang != 0 && width != 0) {
            length = (umfang - (2*width)) / 2;
            inhalt = width * length;
            diagonale = Math.sqrt( (width*width) + (length*length) );
            return true;
        }

        if(diagonale != 0 && width != 0) {
            length = Math.sqrt( (diagonale*diagonale) - (width*width) );
            inhalt = width * length;
            umfang = (2*width) + (2*length);
            return true;
        }

        if(diagonale != 0 && length != 0) {
            width = Math.sqrt( (diagonale*diagonale) - (length*length) );
            inhalt = width * length;
            umfang = (2*width) + (2*length);
            return true;
        }



        return false;
    }

    @Override
    public void updateValues(JTextArea[] textAreas) {
        width = Double.parseDouble(textAreas[0].getText());
        length = Double.parseDouble(textAreas[1].getText());
        umfang = Double.parseDouble(textAreas[2].getText());
        inhalt = Double.parseDouble(textAreas[3].getText());
        diagonale = Double.parseDouble(textAreas[4].getText());
    }

    @Override
    public String errorMsg() {
        return "Mindestens Länge oder Breite und Umfang, Inhalt oder Diagonale benötigt";
    }

    @Override
    public void resetAll() {
        width = 0;
        length = 0;
        umfang = 0;
        inhalt = 0;
        diagonale = 0;
    }

    @Override
    public String toString() {
        return "{Breite: " + width + ", Länge: " + length + ", Umfang: " + umfang + ", Inhalt: " + inhalt + ", Diagonale: " + diagonale + "}";
    }
}
