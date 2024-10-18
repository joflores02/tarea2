import java.util.ArrayList;
import java.util.List;
public class Departamento {
    private String nombreDepartamento;
    private List<Empleado> empleados;

    public Departamento(String nombreDepartamento){
        this.nombreDepartamento = nombreDepartamento;
        empleados = new ArrayList<>();

    }
    public void agregarEmpleado(Empleado empleado) {
        empleados.add(empleado);
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public int obtenerCantidadEmpleados() {
        return empleados.size();
    }

    public String getNombreDepartamento(){
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    @Override
    public String toString() {
        return "Departamento: " + nombreDepartamento + "\nEmpleados: " + empleados;
    }


}
