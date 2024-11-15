import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.time.Instant;
import java.time.Duration;
import java.util.List;

/**
 * Clase abstracta que representa una reunión.
 */
abstract class Reunion {
    private Date fecha;
    private tipoReunion tipoReunion;
    private Instant horaPrevista;
    private Duration duracionPrevista;
    private Instant horaInicio;
    private Instant horaFin;
    private List<Empleado> empleadosAsistentes;
    private List<Empleado> empleadosInasistentes;
    private List<Asistencia> atrasos;
    private List<Nota> notas = new ArrayList<>();
    private List<Invitacion> listaInvitados;

    /**
     * Constructor para crear una nueva reunión.
     *
     * @param fecha          La fecha de la reunión.
     * @param horaPrevista   La hora prevista de inicio de la reunión.
     * @param duracionPrevista La duración prevista de la reunión.
     * @param Tipo           El tipo de la reunión.
     */
    public Reunion(Date fecha, Instant horaPrevista, Duration duracionPrevista, tipoReunion Tipo) {
        this.fecha = fecha;
        this.horaPrevista = horaPrevista;
        this.duracionPrevista = duracionPrevista;
        this.tipoReunion = Tipo;
        this.listaInvitados = new ArrayList<>();
        this.empleadosAsistentes = new ArrayList<>();
        this.empleadosInasistentes = new ArrayList<>();
        this.atrasos = new ArrayList<>();
    }

    /**
     * Crea una invitación para un empleado y la envía verificando que el empleado
     * no esté en la lista.
     *
     * @param empleado El empleado al que se le envía la invitación.
     */
    public void crearInvitación(Empleado empleado) {
        boolean verificacionInvitado = false;

        for (Invitacion invitacion : listaInvitados) {
            if (invitacion.getEmpleado().equals(empleado)) {
                verificacionInvitado = true;
                break;
            }
        }

        if (!verificacionInvitado) {
            Invitacion invitacion = new Invitacion(empleado);
            this.listaInvitados.add(invitacion);
            invitacion.invitar();
        }
    }

    /**
     * Muestra todas las invitaciones enviadas para la reunión.
     */
    public void mostrarInvitaciones() {
        System.out.println("Invitaciones para la reunión:");
        for (Invitacion invitacion : listaInvitados) {
            System.out.println(invitacion);
        }
    }

    /**
     * Invita a todos los empleados de un departamento a la reunión.
     *
     * @param departamento El departamento cuyos empleados serán invitados.
     */
    public void invitarEmpleadosDelDepartamento(Departamento departamento) {
        List<Empleado> empleadosDelDepartamento = departamento.getEmpleados();
        for (Empleado empleado : empleadosDelDepartamento) {
            crearInvitación(empleado);
        }
    }

    /**
     * Entrega empleados invitados.
     *
     * @return lista de invitados.
     */

    public List<Invitacion> obtenerInvitados() {
        return listaInvitados;
    }

    /**
     * Añade una nota a la reunión.
     *
     * @param contenido El contenido de la nota.
     */
    public void añadirNota(String contenido) {
        notas.add(new Nota(contenido));
    }

    /**
     * Registra la asistencia de un empleado a la reunión.
     *
     * @param empleado    El empleado que asiste.
     * @param horaLlegada La hora de llegada del empleado.
     */
    
    public void registrarAsistencia(Empleado empleado, Instant horaLlegada) {
    Instant terminoPrevisto = horaPrevista.plus(duracionPrevista);
    if (horaLlegada == null) {
        empleadosInasistentes.add(empleado); // empleado que no asiste
    } else if (horaLlegada.isAfter(terminoPrevisto)) {
        empleadosInasistentes.add(empleado);
    } else if (horaLlegada.isAfter(horaPrevista) && horaLlegada.isBefore(terminoPrevisto)) {
        atrasos.add(new Retraso(empleado, horaLlegada)); // empleado que asiste pero llega tarde
        empleadosAsistentes.add(empleado);
    } else {
        empleadosAsistentes.add(empleado); // empleado que asiste y llega a la hora
    }
}

    public List<Empleado> obtenerAsistencias() {
        return empleadosAsistentes;
    }

    public List<Empleado> obtenerInasistencias() {
        return empleadosInasistentes;
    }

    public List<Asistencia> obtenerRetrasos() {
        return atrasos;
    }

    public int obtenerTotalAsistencia() {
        return empleadosAsistentes.size();
    }

    public float obtenerPorcentajeAsistencia() {
        int totalInvitados = listaInvitados.size();
        int totalAsistentes = obtenerTotalAsistencia();
        return totalInvitados == 0 ? 0 : (float) totalAsistentes / totalInvitados * 100;
    }

    public float calcularTiempoReal() {
        return (horaInicio != null && horaFin != null) ? Duration.between(horaInicio, horaFin).toMinutes() : 0;
    }

    public tipoReunion getTipoReunion() {
        return tipoReunion;
    }

    public void setHoraInicio(Instant horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Instant getHoraInicio() {
        return horaInicio;
    }

    public void setHoraFin(Instant horaFin) {
        this.horaFin = horaFin;
    }

    public Instant getHoraFin() {
        return horaFin;
    }

    public Duration getDuracionPrevista() {
        return duracionPrevista;
    }

    public Instant getHoraPrevista() {
        return horaPrevista;
    }
    public Date getFecha() {
        return fecha;
    }

    public abstract void iniciar();

    public abstract void finalizar();

    @Override
    public String toString() {
        return "Reunión " +
                "tipo= " + tipoReunion + ", fecha= " + fecha + ", hora prevista=" + horaPrevista +
                ", duración= " + duracionPrevista.toMinutes() + " minutos" + ", asistentes= " + empleadosAsistentes.size() +
                ", inasistentes= " + empleadosInasistentes.size() + ", retrasos= " + atrasos.size();
    }
    
    // Método para generar un informe de la reunión
    /**
     * Genera un informe de la reunión y lo guarda en un archivo de texto.
     *
     * @param nombreArchivo El nombre del archivo donde se guardará el informe.
     * @throws IOException Si ocurre un error al escribir en el archivo.
     */
    public void generarInforme(String nombreArchivo) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Informe de la Reunión\n");
            writer.write("Fecha: " + fecha.toString() + "\n");
            writer.write("Hora Prevista: " + horaPrevista.toString() + "\n");
            writer.write("Hora de Inicio: " + horaInicio.toString() + "\n");
            writer.write("Hora de Fin: " + horaFin.toString() + "\n");
            writer.write("Duración Total: " + duracionPrevista.toMinutes() + " minutos\n");
            writer.write("Tipo de Reunión: " + tipoReunion.toString() + "\n");
            writer.write("Enlace/Sala: " + (this instanceof ReunionVirtual ? ((ReunionVirtual) this).getEnlace() : ((ReunionPresencial) this).getSala()) + "\n");
            writer.write("Participantes: \n");

            for (Empleado empleado : empleadosAsistentes) {
                writer.write(" - " + empleado.getNombre() + " " + empleado.getApellido() + "\n");
            }

            writer.write("Notas:\n");
            for (Nota nota : notas) {
                writer.write(" - " + nota.getContenido() + "\n");
            }
        }
    }
}
