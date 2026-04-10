/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.edu.uacm.is.slt.ms.m4unix;

import java.util.Scanner;

/**
 *
 * @author DELL VPRO
 */
public class Productividad {
    public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);

        System.out.println("CALCULO DE PRODUCTIVIDAD");

        // Pedir datos al usuario
        System.out.print("Ingresa la tarea total: ");
        double tareas = sc.nextDouble();

        System.out.print("Ingresa los dias: ");
        double dias = sc.nextDouble();

        // Calculara la productividad
        double resultado = calcularProductividad(tareas, dias);

        // Mostrara el resultado
        System.out.println("Productividad: " + resultado + " Tareas por dia");

        sc.close();
    
            }
    
    public static double calcularProductividad(double tareas, double dias) {
        if (dias == 0) {
            System.out.println("Error: no se puede dividir entre 0");
            return 0;
        }
        return tareas / dias;

    }
}
