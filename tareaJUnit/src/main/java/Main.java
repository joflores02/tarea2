import java.io.IOException;
import java.util.Date;
import java.time.Instant;
import java.time.Duration;

public class Main {
    public static void main(String[] args) {

        // Creación de empleados
        Empleado empleado1 = new Empleado("1", "Fernando", "Pérez", "Fernando@correo.com");
        Empleado empleado2 = new Empleado("2", "Jose", "Díaz", "dfjsdofj@gmail.com");
        Empleado empleado3 = new Empleado("3", "Josefa", "Rivas", "jrivas@gmail.com");

        // Creación de departamentos
        Departamento dep1 = new Departamento("dep1");
        Departamento dep2 = new Departamento("dep2");

        // Agregar empleados al departamento
        dep1.agregarEmpleado(empleado1);
        dep1.agregarEmpleado(empleado2);
        dep1.agregarEmpleado(empleado3);

        // Definición de la fecha, hora y duración de la reunión
        Date fecha = new Date();
        Instant horaPrevista = Instant.now();
        Duration duracionPrevista = Duration.ofMinutes(45);
        String sala = "Sala 1";

        // Creamos una reunión presencial de tipo Técnica
        ReunionPresencial reunion = new ReunionPresencial(fecha, horaPrevista, duracionPrevista, tipoReunion.TECNICA, sala);

        // Se invitan a los empleados del departamento
        reunion.invitarEmpleadosDelDepartamento(dep1);

        // Imprimir información de la reunión
        System.out.println(reunion);

        // Registrar asistencia de los empleados (suponiendo que llegan a tiempo)
        reunion.registrarAsistencia(empleado1, Instant.now());
        reunion.registrarAsistencia(empleado2, Instant.now());
        reunion.registrarAsistencia(empleado3, Instant.now());

        // Iniciar y finalizar la reunión
        reunion.iniciar();
        reunion.añadirNota("Esta es una reunión técnica");
        reunion.finalizar();

        // Generar informe de la reunión y guardar en un archivo
        try {
            reunion.generarInforme("informe_reunion.txt");
            System.out.println("Informe generado exitosamente.");
        } catch (IOException e) {
            // Manejo de errores en la generación del informe
            System.err.println("Error al generar el informe: " + e.getMessage());
        }
    }
}
