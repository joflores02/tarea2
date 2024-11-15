import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.time.Duration;
import java.io.*;
import java.util.Date;
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


        Instant horaPrevista = Instant.now();
        Duration duracionPrevista = Duration.ofMinutes(45);
        reunion = new ReunionPresencial(new java.util.Date(), horaPrevista, duracionPrevista, tipoReunion.TECNICA, "Sala 1");

        reunion.registrarAsistencia(empleado1, Instant.parse("2024-11-15T09:10:00Z")); // Fernando llega tarde
        reunion.registrarAsistencia(empleado2, Instant.parse("2024-11-15T09:00:00Z")); // Jose llega puntual

    }

    //Test crear invitaciones
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

    //test asistencia e inasistencia
    @Test
    public void testregistrarAsistencia() {
        reunion.registrarAsistencia(empleado1, Instant.now());
        reunion.registrarAsistencia(empleado2, null);

        assertTrue(reunion.obtenerAsistencias().contains(empleado1));
        assertTrue(reunion.obtenerInasistencias().contains(empleado2));
    }


    @Test
    void testObtenerAsistencias() {
        reunion.registrarAsistencia(empleado3, null); //Sara no asistió
        List<Empleado> asistentes = reunion.obtenerAsistencias();
        assertEquals(2, asistentes.size(), "El número de asistentes debe ser 2");

        assertTrue(asistentes.contains(empleado1), "Fernando debería estar en la lista de asistentes");

        assertTrue(asistentes.contains(empleado2), "Jose debería estar en la lista de asistentes");

        assertFalse(asistentes.contains(empleado3), "Sara no debería estar en la lista de asistentes");
    }

    @Test
    void testObtenerInasistencias() {
        reunion.registrarAsistencia(empleado3, null); //Sara no asistió
        List<Empleado> inasistentes = reunion.obtenerInasistencias();

        assertEquals(1, inasistentes.size(), "El número de inasistentes debe ser 1");

        assertTrue(inasistentes.contains(empleado3), "Sara debería estar en la lista de inasistentes");

        assertFalse(inasistentes.contains(empleado1), "Fernando NO debería estar en la lista de inasistentes");

        assertFalse(inasistentes.contains(empleado2), "Jose NO debería estar en la lista de inasistentes");
    }

    @Test
    void testGenerarInforme() throws IOException {
        reunion.crearInvitación(empleado1);
        reunion.crearInvitación(empleado2);
        reunion.crearInvitación(empleado3);

        reunion.registrarAsistencia(empleado1, Instant.now());
        reunion.registrarAsistencia(empleado2, Instant.now().plus(Duration.ofMinutes(10))); // llega tarde
        reunion.registrarAsistencia(empleado3, null); // No asiste

        Instant horaInicio = Instant.now();
        Instant horaFin = horaInicio.plus(Duration.ofMinutes(45));
        reunion.setHoraInicio(horaInicio);
        reunion.setHoraFin(horaFin);

        reunion.añadirNota("Reunión técnica sobre el nuevo proyecto.");
        reunion.añadirNota("Evaluar ideas");

        String archivoInforme = "informe_reunion.txt";
        reunion.generarInforme(archivoInforme);

        File archivo = new File(archivoInforme);
        assertTrue(archivo.exists(), "El archivo de informe debería haberse generado.");

        String contenidoArchivo = new String(java.nio.file.Files.readAllBytes(archivo.toPath()));

        assertTrue(contenidoArchivo.contains("Informe de la Reunión"));
        assertTrue(contenidoArchivo.contains("Fecha: " + reunion.getFecha()));
        assertTrue(contenidoArchivo.contains("Hora Prevista: " + reunion.getHoraPrevista()));
        assertTrue(contenidoArchivo.contains("Hora de Inicio: " + reunion.getHoraInicio()));
        assertTrue(contenidoArchivo.contains("Hora de Fin: " + reunion.getHoraFin()));
        assertTrue(contenidoArchivo.contains("Duración Total: " + reunion.getDuracionPrevista().toMinutes() + " minutos"));
        assertTrue(contenidoArchivo.contains("Tipo de Reunión: " + reunion.getTipoReunion()));
        assertTrue(contenidoArchivo.contains("Sala: " + reunion.getSala()));

        //ayuda a ver los participantes
        assertTrue(contenidoArchivo.contains(" - Fernando Pérez"));
        assertTrue(contenidoArchivo.contains(" - Jose Díaz"));
        assertFalse(contenidoArchivo.contains(" - Sara González"));

        assertTrue(contenidoArchivo.contains("Reunión técnica sobre el nuevo proyecto."));
        assertTrue(contenidoArchivo.contains("Evaluar ideas"));

        archivo.delete();
    }


}
