package mx.edu.uacm.is.slt.ms.m4unix;

import java.util.Scanner;

/**
 *
 * @author David R
 */
public class MeticaLOC {
    
    private int lineasDeCodigo;
    
    public MeticaLOC(int lineasDeCodigo){
        if(lineasDeCodigo < 0){
            System.out.println("El numero de lineas de codigo no debe ser 0");
        }
        this.lineasDeCodigo = lineasDeCodigo;
    }
    
    public int getLineasCodigo(){
        return lineasDeCodigo;
    }
    
    public String nivelTamanio( ){
        if (lineasDeCodigo < 500){
            return "Nivel bajo";
        }else{
            if(lineasDeCodigo <= 1500){
                return "Nivel medio";
            }else {
                return "Nivel alto";
            }
        }
    }
    
    public String Informacion(){
        switch(nivelTamanio() ){
            case "Nivel bajo":
                return "Sistema pequenio";
            case "Nivel medio":
                return "Sistema de tamanio moderado";
            case "Nivel alto":
                return "Sistema grande lo que implica mayor complejidad y esfuerzo de mantenimiento";
            default:
                return "No definido";
        }
    }
    
    public String Imprimir(){
        return "Lineas de codigo:  " + lineasDeCodigo + "\nIndicador tamanio del sistema " + 
                "\nInterpretacion:  " + nivelTamanio() + "\nDescipcion:  " + Informacion();
    }
    
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("\t\tMetrica Lineas de Codigo (LOC)\n");
        System.out.println("\nIngrese el numero total de lineas de codigo:    ");
        int lineasCodigo = scan.nextInt();
        if(lineasCodigo > 0){
            MeticaLOC metricaLOC = new MeticaLOC(lineasCodigo);
            
            System.out.println("\n\t\tResultados");
            System.out.println(metricaLOC.Imprimir());
        }else{ 
            System.out.println("Valor no valido, intente nuevamente");
        }
    }
    
    
}
