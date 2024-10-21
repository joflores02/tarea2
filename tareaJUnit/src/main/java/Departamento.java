import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un departamento en la empresa.
 */
public class Departamento {
    private String nombreDepartamento;
    private List<Empleado> empleados;

    /**
     * Constructor para crear un nuevo departamento.
     *
     * @param nombreDepartamento El nombre del departamento.
     */
    public Departamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        empleados = new ArrayList<>();
    }

    /**
     * Agrega un empleado al departamento.
     *
     * @param empleado El empleado a agregar.
     */
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    /**
     * Obtiene la lista de empleados del departamento.
     *
     * @return La lista de empleados.
     */
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    /**
     * Obtiene la cantidad de empleados en el departamento.
     *
     * @return El número de empleados.
     */
    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }

    /**
     * Obtiene el nombre del departamento.
     *
     * @return El nombre del departamento.
     */
    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    /**
     * Establece el nombre del departamento.
     *
     * @param nombreDepartamento El nuevo nombre del departamento.
     */
    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento: " + nombreDepartamento + "\nEmpleados: " + empleados;
    }
}
