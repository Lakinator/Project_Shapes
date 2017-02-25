package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Rechteck implements Shape {
    private final ShapeType type = ShapeType._2D;
    private final static int varLength = 5;
    private double width = 0, length = 0, umfang = 0, inhalt = 0, diagonale = 0;


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
        return new String[]{"Breite", "Länge", "Umfang", "Inhalt", "Diagonale"};
    }

    @Override
    public boolean handleCalculations() {
        int setVars = 0;

        if(width != 0) setVars++;
        if(length != 0) setVars++;
        if(umfang != 0) setVars++;
        if(inhalt != 0) setVars++;
        if(diagonale != 0) setVars++;

        if(setVars < 2) return false;

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


    public void updateValues(final JTextArea[] textAreas) {
        setWidth( Double.parseDouble(textAreas[0].getText()) );
        setLength( Double.parseDouble(textAreas[1].getText()) );
        setUmfang( Double.parseDouble(textAreas[2].getText()) );
        setInhalt( Double.parseDouble(textAreas[3].getText()) );
        setDiagonale( Double.parseDouble(textAreas[4].getText()) );
    }

    public void resetAll() {
        setWidth(0);
        setLength(0);
        setInhalt(0);
        setUmfang(0);
        setDiagonale(0);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
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

    public double getDiagonale() {
        return diagonale;
    }

    public void setDiagonale(double diagonale) {
        this.diagonale = diagonale;
    }
}
