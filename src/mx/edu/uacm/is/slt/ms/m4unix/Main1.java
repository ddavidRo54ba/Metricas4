package mx.edu.uacm.is.slt.ms.m4unix;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main1 extends JFrame {

    private List<Double> tiempos = new ArrayList<>();
    private JTextField txtNombre, txtTiempo;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextPane area;

    public Main1(){

        setTitle("Promedio de tiempo por tarea");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(2, 2, 5, 5));
        panelTop.setBorder(BorderFactory.createTitledBorder("Datos de tarea"));

        panelTop.add(new JLabel("Nombre de la tarea:"));
        txtNombre = new JTextField();
        panelTop.add(txtNombre);

        panelTop.add(new JLabel("Tiempo (horas):"));
        txtTiempo = new JTextField();
        panelTop.add(txtTiempo);

        add(panelTop, BorderLayout.NORTH);

        modelo = new DefaultTableModel(new Object[]{"Tareas", "Tiempo (hrs)"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Acciones"));

        JButton btnAgregar = new JButton("AGREGAR");
        JButton btnEliminar = new JButton("ELIMINAR");
        JButton btnCalcular = new JButton("CALCULAR");
        JButton btnLimpiar = new JButton("LIMPIAR");

        btnAgregar.setBackground(new Color(76, 175, 80));
        btnAgregar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);

        btnCalcular.setBackground(new Color(33, 150, 243));
        btnCalcular.setForeground(Color.WHITE);

        btnLimpiar.setBackground(Color.GRAY);
        btnLimpiar.setForeground(Color.WHITE);

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCalcular);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.EAST);

        area = new JTextPane();
        area.setEditable(false);
        area.setBorder(BorderFactory.createTitledBorder("Dashboard"));
        add(new JScrollPane(area), BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText().trim();
                String tiempoTexto = txtTiempo.getText().trim();

                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa el nombre de la tarea");
                    return;
                }

                if (tiempoTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingresa el tiempo de la tarea");
                    return;
                }

                double tiempo = Double.parseDouble(tiempoTexto);

                if (tiempo < 0) {
                    JOptionPane.showMessageDialog(this, "El tiempo no puede ser negativo");
                    return;
                }

                tiempos.add(tiempo);
                modelo.addRow(new Object[]{nombre, tiempo});

                txtNombre.setText("");
                txtTiempo.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingresa un numero valido en tiempo");
            }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tabla.getSelectedRow();

            if (fila >= 0) {
                modelo.removeRow(fila);
                tiempos.remove(fila);
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona una tarea");
            }
        });

        btnCalcular.addActionListener(e -> {

            if (tiempos.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No hay tareas registradas");
                return;
            }

            double total = 0;

            for (double t : tiempos) {
                total += t;
            }

            int num = tiempos.size();
            double promedio = total / num;

            area.setText("");

            agregarTexto("--------- RESULTADOS ---------\n", Color.BLACK);
            agregarTexto("Numero de tareas: " + num + "\n", Color.BLACK);
            agregarTexto("Tiempo total: " + total + " hrs\n", Color.BLACK);
            agregarTexto("Promedio: " + promedio + " hrs\n\n", Color.BLACK);

            String resultado = interpretacionDeIndicador(promedio);

            if (promedio < 4) {
                agregarTexto("" + resultado, new Color(0, 150, 0));
            } else if (promedio <= 8) {
                agregarTexto("" + resultado, new Color(255, 140, 0));
            } else {
                agregarTexto("" + resultado, Color.RED);
            }

            JOptionPane.showMessageDialog(this, "Calculo listo");
        });

        btnLimpiar.addActionListener(e -> {
            modelo.setRowCount(0);
            tiempos.clear();
            area.setText("");
        });
    }

    private String interpretacionDeIndicador(double promedio) {

        if (promedio < 4) {
            return "Nivel bajo: alta eficiencia";
        } else if (promedio <= 8) {
            return "Nivel medio: eficiencia aceptable";
        } else {
            return "Nivel alto: baja eficiencia";
        }
    }

    private void agregarTexto(String texto, Color color) {

        StyledDocument doc = area.getStyledDocument();
        Style style = area.addStyle("style", null);
        StyleConstants.setForeground(style, color);

        try {
            doc.insertString(doc.getLength(), texto, style);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main1().setVisible(true));
    }
}