package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Dreieck implements Shape {
    /** !! Rechtwinkliges Dreieck !! **/

    private final ShapeType type = ShapeType._2D;
    private final static int varLength = 6;
    private double a = 0, b = 0, c = 0, alpha = 0, beta = 0, gamma = 0;

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
        return new String[]{"Seite a", "Seite b", "Seite c", "Winkel Alpha", "Winkel Beta", "Winkel Gamma"};
    }

    @Override
    public boolean handleCalculations() {

        if(alpha + beta + gamma > 180) return false;

        //TODO: Alles berechnen

        if(a != 0 && b != 0) {
            if (alpha == 90) {
                c = Math.sqrt((a*a) - (b*b));

                return true;
            } else if (beta == 90) {
                c = Math.sqrt((b*b) - (a*a));

                alpha = Math.asin(a/c);
                gamma = 180 - (alpha + beta);

                return true;
            } else if (gamma == 90) {
                c = Math.sqrt((a*a) + (b*b));

                return true;
            }
        }

        if(a != 0 && c != 0) {
            if (alpha == 90) {
                b = Math.sqrt((a*a) - (c*c));

                return true;
            } else if (beta == 90) {
                b = Math.sqrt((a*a) + (c*c));

                return true;
            } else if (gamma == 90) {
                b = Math.sqrt((c*c) - (a*a));

                return true;
            }
        }

        if(b != 0 && c != 0) {
            if (alpha == 90) {
                a = Math.sqrt((b*b) + (c*c));

                return true;
            } else if (beta == 90) {
                a = Math.sqrt((b*b) - (c*c));

                return true;
            } else if (gamma == 90) {
                a = Math.sqrt((c*c) - (b*b));

                return true;
            }
        }


        return false;
    }


    public void updateValues(final JTextArea[] textAreas) {
        setA( Double.parseDouble(textAreas[0].getText()) );
        setB( Double.parseDouble(textAreas[1].getText()) );
        setC( Double.parseDouble(textAreas[2].getText()) );
        setAlpha( Double.parseDouble(textAreas[3].getText()) );
        setBeta( Double.parseDouble(textAreas[4].getText()) );
        setGamma( Double.parseDouble(textAreas[5].getText()) );
    }

    public void resetAll() {
        setA(0);
        setB(0);
        setC(0);
        setAlpha(0);
        setBeta(0);
        setGamma(0);
    }


    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }
}
