package Shapes;

import javax.swing.*;

/**
 * 22.02.2017
 * Created by user Schalk (Lukas Schalk).
 */

public class Dreieck implements Shape {
    /** !! Rechtwinkliges Dreieck !! **/

    private final ShapeType type = ShapeType._2D;
    private double a = 0, b = 0, c = 0, alpha = 0, beta = 0, gamma = 0;

    @Override
    public ShapeType getType() {
        return type;
    }

    @Override
    public String[] getVarNames() {
        return new String[]{"Seite a", "Seite b", "Seite c", "Winkel Alpha", "Winkel Beta", "Winkel Gamma"};
    }

    @Override
    public double[] getVariables() {
        return new double[]{a, b, c, alpha, beta, gamma};
    }

    @Override
    public boolean handleCalculations() {

        if(alpha + beta + gamma > 180) return false;


        if(a != 0 && b != 0) {
            if (alpha == 90) {
                c = Math.sqrt((a*a) - (b*b));

                beta = Math.toDegrees(Math.asin(b/a));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (beta == 90) {
                c = Math.sqrt((b*b) - (a*a));

                alpha = Math.toDegrees(Math.asin(a/c));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (gamma == 90) {
                c = Math.sqrt((a*a) + (b*b));

                alpha = Math.toDegrees(Math.asin(a/c));
                beta = 180 - (alpha + gamma);

                return true;
            }
        }

        if(a != 0 && c != 0) {
            if (alpha == 90) {
                b = Math.sqrt((a*a) - (c*c));

                beta = Math.toDegrees(Math.asin(b/a));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (beta == 90) {
                b = Math.sqrt((a*a) + (c*c));

                alpha = Math.toDegrees(Math.asin(a/c));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (gamma == 90) {
                b = Math.sqrt((c*c) - (a*a));

                alpha = Math.toDegrees(Math.asin(a/c));
                beta = 180 - (alpha + gamma);

                return true;
            }
        }

        if(b != 0 && c != 0) {
            if (alpha == 90) {
                a = Math.sqrt((b*b) + (c*c));

                beta = Math.toDegrees(Math.asin(b/a));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (beta == 90) {
                a = Math.sqrt((b*b) - (c*c));

                alpha = Math.toDegrees(Math.asin(a/c));
                gamma = 180 - (alpha + beta);

                return true;
            } else if (gamma == 90) {
                a = Math.sqrt((c*c) - (b*b));

                alpha = Math.toDegrees(Math.asin(a/c));
                beta = 180 - (alpha + gamma);

                return true;
            }
        }


        return false;
    }

    @Override
    public void updateValues(JTextArea[] textAreas) {
        a = Double.parseDouble(textAreas[0].getText());
        b = Double.parseDouble(textAreas[1].getText());
        c = Double.parseDouble(textAreas[2].getText());
        alpha = Double.parseDouble(textAreas[3].getText());
        beta = Double.parseDouble(textAreas[4].getText());
        gamma = Double.parseDouble(textAreas[5].getText());
    }

    @Override
    public String errorMsg() {
        return "Es müssen 2 Seiten und ein Winkel mit 90 Grad gegeben sein. Die Hypotenuse muss dabei am größten sein";
    }

    @Override
    public void resetAll() {
        a = 0;
        b = 0;
        c = 0;
        alpha = 0;
        beta = 0;
        gamma = 0;
    }

    @Override
    public String toString() {
        return "{Seite a: " + a + ", Seite b: " + b + ", Seite c: " + c + ", Alpha: " + alpha + ", Beta: " + beta + ", Gamma: " + gamma + "}";
    }
}
