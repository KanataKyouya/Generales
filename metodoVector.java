import java.util.Vector;
import javax.swing.JOptionPane;

public class metodoVector {
    public void LlenarVector() {
        Vector<Repuestos> vector = new Vector<>();
        boolean continuar = true;
        String agregar;

        while (continuar) {
            Repuestos o = new Repuestos();
            o.setMarca(JOptionPane.showInputDialog("Ingrese la marca"));
            o.setReferencia(JOptionPane.showInputDialog("Ingrese la referencia"));
            o.setCantidad(Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad")));
            o.setPrecio(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio")));
            vector.add(o);  // Agregar al vector

            agregar = JOptionPane.showInputDialog("¿Desea agregar más registros? (S/N)");
            if (agregar.equalsIgnoreCase("N")) {
                continuar = false;
            }
        }

        MostrarVector(vector);

        int opt;
        do {
            opt = Integer.parseInt(JOptionPane.showInputDialog("Ingrese 1: consultar, 2: eliminar, 3: modificar, 4: vender, 5: salir"));
            if (opt != 5) {
                vector = AccionesRegistro(vector, opt);
                MostrarVector(vector);
            }
        } while (opt != 5);

        System.out.println("Programa finalizado");
    }

    public void MostrarVector(Vector<Repuestos> vector) {
        System.out.println("Lista de repuestos:");
        for (Repuestos r : vector) {
            System.out.println("Marca: " + r.getMarca());
            System.out.println("Referencia: " + r.getReferencia());
            System.out.println("Cantidad: " + r.getCantidad());
            System.out.println("Precio: " + r.getPrecio());
            System.out.println();
        }
    }

    public Vector<Repuestos> AccionesRegistro(Vector<Repuestos> vector, int opt) {
        String dato = JOptionPane.showInputDialog("Ingrese la referencia del repuesto");
        Vector<Repuestos> nuevoVector = new Vector<>();

        for (Repuestos repuesto : vector) {
            if (repuesto.getReferencia().equalsIgnoreCase(dato)) {
                switch (opt) {
                    case 1: // Consultar
                        System.out.println("Registro encontrado: " + repuesto.getMarca() + " - $" + repuesto.getPrecio());
                        break;
                    case 2: // Eliminar
                        System.out.println("Repuesto eliminado: " + repuesto.getMarca());
                        continue;  // No lo agregamos al nuevo vector
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
                        } else {
                            System.out.println("Error: No hay suficiente stock");
                        }
                        break;
                }
            }
            nuevoVector.add(repuesto);
        }

        return nuevoVector;  // Devuelve el vector actualizado
    }
}