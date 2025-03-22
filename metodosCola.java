import java.util.ArrayDeque;
import java.util.Queue;
import javax.swing.JOptionPane;

public class metodosCola {

    public void LlenarCola() {

        Queue<Repuestos> cola = new ArrayDeque<>();
        boolean continuar = true;
        String agregar = "";

        while (continuar) {

            Repuestos o = new Repuestos();
            o.setMarca(JOptionPane.showInputDialog("Ingrese la marca"));
            o.setReferencia(JOptionPane.showInputDialog("Ingrese la referencia"));
            o.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad")));
            o.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio")));
            cola.offer(o);  // Agregar a la cola

            agregar = JOptionPane.showInputDialog("Desea agregar más registros? (S/N)");
            if (agregar.equalsIgnoreCase("N")) {
                continuar = false;
            }
        }

        MostrarCola(cola);

        int opt;

        do {

            opt = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1: consultar, 2: eliminar, 3: modificar, 4: vender, 5: salir"));
            
            if (opt != 5) {

                cola = AccionesRegistro(cola, opt);
                MostrarCola(cola);

            }

        }
        while (opt != 5);

        System.out.println("Programa finalizado");

    }

    public void MostrarCola(Queue<Repuestos> cola) {

        System.out.println("Cola de repuestos:");

        for (Repuestos r : cola) {

            System.out.println("Marca: " + r.getMarca());
            System.out.println("Referencia: " + r.getReferencia());
            System.out.println("Cantidad: " + r.getCantidad());
            System.out.println("Precio: " + r.getPrecio());
            System.out.println();

        }

    }

    public Queue<Repuestos> AccionesRegistro(Queue<Repuestos> cola, int opt) {

        String dato = JOptionPane.showInputDialog("Ingrese la referencia del repuesto");
        Queue<Repuestos> nuevaCola = new ArrayDeque<>();

        while (!cola.isEmpty()) {

            Repuestos repuesto = cola.poll();  // Saca el primer elemento

            if (repuesto.getReferencia().equalsIgnoreCase(dato)) {

                switch (opt) {

                    case 1: // Consultar

                        System.out.println("Registro encontrado: " + repuesto.getMarca() + " - $" + repuesto.getPrecio());
                        break;

                    case 2: // Eliminar

                        System.out.println("Repuesto eliminado: " + repuesto.getMarca());
                        continue;  // No lo agregamos a la nueva cola

                    case 3: // Modificar

                        repuesto.setMarca(JOptionPane.showInputDialog("Ingrese la nueva marca"));
                        repuesto.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad")));
                        repuesto.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio")));
                        break;

                    case 4: // Vender

                        int cantidadV = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a vender"));
                        if (cantidadV <= repuesto.getCantidad()) {

                            repuesto.setCantidad(repuesto.getCantidad() - cantidadV);
                            System.out.println("Venta realizada: " + cantidadV + " unidades vendidas");

                        } 
                        else {

                            System.out.println("Error: No hay suficiente stock");

                        }

                        break;

                }

            }

            nuevaCola.offer(repuesto);

        }

        return nuevaCola;  // Devuelve la cola actualizada
        
    }


}
