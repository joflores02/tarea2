import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DepartamentoTest {
    @Test
    public void testCantidadInicialDeEmpleados() {

        Departamento departamento = new Departamento("Marketing");

        assertEquals(0, departamento.obtenerCantidadEmpleados());
    }

    @Test
    public void testAgregarEmpleado() {

        Departamento departamento = new Departamento("Recursos Humanos");

        Empleado empleado1 = new Empleado("1", "Juan", "Pérez", "juan.perez@gmail.com");

        departamento.agregarEmpleado(empleado1);

        assertEquals(1, departamento.obtenerCantidadEmpleados());

        assertTrue(departamento.getEmpleados().contains(empleado1));
    }

    @Test
    public void testBuscarEmpleadoSegunId() {

        Empleado emp1 = new Empleado("1", "Juan", "Pérez", "juan@gmail.com");
        Empleado emp2 = new Empleado("2", "Ana", "Gómez", "ana@gmail.com");

        Departamento departamento = new Departamento("Marketing");
        departamento.agregarEmpleado(emp1);
        departamento.agregarEmpleado(emp2);

        String idBuscado = "2";
        Empleado encontrado = null;

        for (int i = 0; i < departamento.getEmpleados().size(); i++) {
            Empleado e = departamento.getEmpleados().get(i);
            if (e.getId().equals(idBuscado)) {
                encontrado = e;
                break;
            }
        }

        assertNotNull(encontrado);
        assertEquals(idBuscado, encontrado.getId());
    }


    @Test
    public void testToString() {
        Departamento departamento = new Departamento("Marketing");

        Empleado empleado = new Empleado("2", "Ana", "Gómez", "ana.gomez@gmail.com");
        departamento.agregarEmpleado(empleado);

        String expected = "Departamento: Marketing\nEmpleados: [" + empleado.toString() + "]";
        assertEquals(expected, departamento.toString());
    }
}
