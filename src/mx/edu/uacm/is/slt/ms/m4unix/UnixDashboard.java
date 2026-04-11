/*control de metricas de software 
  punto 4*/
package mx.edu.uacm.is.slt.ms.m4unix;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cruz monje gabriel
 */
public class UnixDashboard {
    
    public static void main(String[] args) {
        
        mostrarInformacionEquipo();
        DashboardController dashboard = new DashboardController();
        
        dashboard.registrarMetrica(new Metrica("productividad","tarea terminada",8));
        dashboard.registrarMetrica(new Metrica("calidad","problemas encontardos",2));
        dashboard.registrarMetrica(new Metrica("esfuerza","horas invertidas",50));
        
        dashboard.mostrarPanel();
    }
    
    private static void mostrarInformacionEquipo(){
        System.out.println("Sistema. Unix Dashboard v1.0");
        System.out.println("Equipo: Metricas4");
        System.out.println("----------------------------");
        System.out.println("Integrantes y Roles");
        System.out.println("(Lider) Aguilar Segura Michel Galilea"+
                           "  Tema: Analisis de indicadores de promedio de tiempo");
        System.out.println("(Lider) Hernandez Montoya Maria Elvira"+
                           "  Tema: Investigacion de metricas de productividad");
        System.out.println("Cruz Monje Gabriel"+
                           "  Tema: Desarrollo punto 4, diseño de dasboard");
        System.out.println("Rocha Basilio David"+
                           "  Tema: Integrante del equipo de desarrollo");
    }
}

class Metrica{
    
    private String nombre;
    private String descripcion;
    private double valor;

    public Metrica() { //constructor vacio, donde se pasaran parametros
    }

    public Metrica(String nombre, String descripcion, double valor) {//constructor
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public double getValor() {
        return valor;
    }
    
    public double calcularIndicador(double totalEsperado){
        
        if(totalEsperado == 0)
            return 0;
        return(valor / totalEsperado)*100;   
    }
}

class DashboardController{
    
    private List<Metrica> metricas = new ArrayList<>();
    
    public void registrarMetrica(Metrica m){
        metricas.add(m);
        System.out.println("\n[Sistema] Metrica '"+m.getNombre() + "'registrada con exito.");   
    }
    
    public void mostrarPanel(){
    System.out.println("\n UnixDasboard v1.0---\n");
    System.out.println("equipo: Metricas4");
    
        for(Metrica m: metricas){
            double indicador = m.calcularIndicador(10);
            String estatus = (indicador < 50)? "riesgo" : "optimo";

            System.out.printf("Metricas: %-15s | indicador: %.2f%% | estatus: %s%n",
                               m.getNombre(), indicador, estatus);
        }
    }
}

