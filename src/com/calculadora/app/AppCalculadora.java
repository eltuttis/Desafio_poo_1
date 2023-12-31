package com.calculadora.app;

import com.calculadora.dominio.Calculadora;

import javax.swing.*;

import java.util.Arrays;

public class AppCalculadora {
    private static ImageIcon icono = new ImageIcon(AppCalculadora.class.getResource("calculadora.png"));
    static JCheckBox redondear = new JCheckBox("redondear");
    private static final  Object[] OPCIONES= Arrays.asList("Sumar","Restar","Multiplicar","Dividir",redondear).toArray();
    private static final int SUMAR = 0;
    private static final int RESTAR = 1;
    private static final int MULTIPLICAR = 2;
    private static final int DIVIDIR = 3;
    public static void main(String[] args) {
        Calculadora calculadora = new Calculadora();
        while (true){
            int opcionElegida = mostarMenu();
            double n1 = recibirNumero("Ingrese el primer número");
            double n2 = recibirNumero("Ingrese el segundo número");
            switch (opcionElegida){
                case SUMAR -> mostrarMensaje("La suma es ", Calculadora.sumar(n1,n2));
                case RESTAR -> mostrarMensaje("La resta es ", Calculadora.restar(n1,n2));
                case MULTIPLICAR -> mostrarMensaje("La multiplicación es ", Calculadora.multiplicacion(n1,n2));
                case DIVIDIR -> {
                    if (n2!=0){
                        mostrarMensaje("La división es ", Calculadora.division(n1,n2));
                    }else {
                        mostrarError("no se pude dividir entre cero");
                    }
                }
            }
        }
    }
    public static void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Calculadora", JOptionPane.ERROR_MESSAGE);
    }


    public static void mostrarMensaje(String mensaje,double numero) {
        String numeroRedondeadoyString = String.valueOf(numero);
        if (redondear.isSelected()) {
            numeroRedondeadoyString = Calculadora.redondear(numero);
        }
        JOptionPane.showMessageDialog(null, mensaje+ numeroRedondeadoyString, "Calculadora", JOptionPane.INFORMATION_MESSAGE);
    }
    public static int mostarMenu(){
        int opcion = JOptionPane.showOptionDialog(null,"¿Qué operación desea realizar?","Calculadora",0,JOptionPane.QUESTION_MESSAGE,icono,OPCIONES,null);

        if (opcion == JOptionPane.CLOSED_OPTION) {
            System.exit(opcion);
        }
        return opcion;
    }
    public static double recibirNumero(String mensaje) {
        double numeroARetornar = 0;
        boolean numeroValido = false;

        do {
            try {
                numeroARetornar = Double.parseDouble(JOptionPane.showInputDialog(mensaje));
                numeroValido = true;
            } catch (NumberFormatException e) {
                mostrarError("El número no es válido. Ingreselo nuevamente.");
                e.printStackTrace();
            }
        } while (!numeroValido);

        return numeroARetornar;
    }

}

