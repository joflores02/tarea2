import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmpleadoTest {

    @Test
    public void testEmpleadoConstructorYGetters() {

        Empleado emp = new Empleado("1", "Juan", "Pérez", "juan.perez@gmail.com");

        assertEquals("1", emp.getId());
        assertEquals("Juan", emp.getNombre());
        assertEquals("Pérez", emp.getApellido());
        assertEquals("juan.perez@gmail.com", emp.getCorreo());
    }

    @Test
    public void testSetters() {

        Empleado emp = new Empleado("1", "Juan", "Pérez", "juan.perez@gmail.com");

        emp.setId("2");
        emp.setNombre("Carlos");
        emp.setApellido("Gómez");
        emp.setCorreo("carlos.gomez@gmail.com");

        assertEquals("2", emp.getId());
        assertEquals("Carlos", emp.getNombre());
        assertEquals("Gómez", emp.getApellido());
        assertEquals("carlos.gomez@gmail.com", emp.getCorreo());
    }

    @Test
    public void testToString() {
        Empleado emp = new Empleado("1", "Juan", "Pérez", "juan.perez@gmail.com");

        String expected = "1 Juan Pérez juan.perez@gmail.com";
        assertEquals(expected, emp.toString());
    }
}
