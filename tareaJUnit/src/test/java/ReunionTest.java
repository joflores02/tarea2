import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.Duration;
import java.util.List;

class ReunionTest {

    private Empleado empleado1;
    private Empleado empleado2;
    private Empleado empleado3;
    private Departamento departamento;
    private ReunionPresencial reunion;

    @BeforeEach
    void setUp() {
        empleado1 = new Empleado("1", "Fernando", "Pérez", "Fernando@correo.com");
        empleado2 = new Empleado("2", "Jose", "Díaz", "dfjsdofj@gmail.com");
        empleado3 = new Empleado("3", "Sara", "González", "sahahaes@gmail.com");


        departamento = new Departamento("dep1");
        departamento.agregarEmpleado(empleado1);
        departamento.agregarEmpleado(empleado2);
        departamento.agregarEmpleado(empleado3);

        // Crear una reunión
        Instant horaPrevista = Instant.now();
        Duration duracionPrevista = Duration.ofMinutes(45);
        reunion = new ReunionPresencial(new java.util.Date(), horaPrevista, duracionPrevista, tipoReunion.TECNICA, "Sala 1");
    }

    @Test
    public void testListaInvitadosVaciaInicialmente() {

        assertTrue(reunion.obtenerInvitados().isEmpty());
    }

    @Test
    void testCrearInvitacionEmpleados() {

        reunion.crearInvitación(empleado1);
        reunion.crearInvitación(empleado3);

        List<Invitacion> listaInvitados = reunion.obtenerInvitados();
        assertEquals(2, listaInvitados.size(), "Deberían haber dos invitaciones");
        assertTrue(listaInvitados.get(0).toString().contains(empleado1.getNombre()), "nombre del primer empleado.");
        assertTrue(listaInvitados.get(1).toString().contains(empleado3.getNombre()), "nombre del tercer empleado.");
    }

    @Test
    void testInvitarDepartamentoCompleto(){
        reunion.invitarEmpleadosDelDepartamento(departamento);

        List<Invitacion> listaInvitados = reunion.obtenerInvitados();
        assertEquals(3, listaInvitados.size(), "La lista debería ser de tres invitados");
        assertTrue(listaInvitados.get(0).toString().contains(empleado1.getNombre()), "nombre del primer empleado.");
        assertTrue(listaInvitados.get(1).toString().contains(empleado2.getNombre()), "nombre del segundo empleado.");
        assertTrue(listaInvitados.get(2).toString().contains(empleado3.getNombre()), "nombre del tercer empleado.");

    }

    @Test
    public void testInvitaciónDuplicada() {

        reunion.crearInvitación(empleado1);
        reunion.crearInvitación(empleado1);

        assertEquals(1, reunion.obtenerInvitados().size());
    }

}
