import java.time.Instant;

/**
 * Clase abstracta que representa la asistencia de un empleado a una reunión.
 */
abstract class Asistencia {
    private Empleado empleado;
    private Instant horaLlegada;

    /**
     * Constructor para crear una instancia de asistencia.
     *
     * @param empleado    El empleado que asiste.
     * @param horaLlegada La hora a la que llegó el empleado.
     */
    public Asistencia(Empleado empleado, Instant horaLlegada) {
        this.empleado = empleado;
        this.horaLlegada = horaLlegada;
    }

    /**
     * Obtiene el empleado que asistió.
     *
     * @return El empleado.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Obtiene la hora de llegada del empleado.
     *
     * @return La hora de llegada.
     */
    public Instant getHoraLlegada() {
        return horaLlegada;
    }

    /**
     * Establece el empleado que asistió.
     *
     * @param empleado El empleado.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Establece la hora de llegada del empleado.
     *
     * @param horaLlegada La hora de llegada.
     */
    public void setHoraLlegada(Instant horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    @Override
    public String toString() {
        return "Asistencia{" + "empleado=" + empleado.getNombre() + ", horaLlegada=" + horaLlegada + '}';
    }
}

/**
 * Clase que representa un retraso de un empleado en una reunión.
 */
class Retraso extends Asistencia {

    /**
     * Constructor para crear una instancia de retraso.
     *
     * @param empleado    El empleado que llegó tarde.
     * @param horaLlegada La hora a la que llegó el empleado.
     */
    public Retraso(Empleado empleado, Instant horaLlegada) {
        super(empleado, horaLlegada);
    }

    @Override
    public String toString() {
        return getEmpleado().getNombre() + " horaLlegada= " + getHoraLlegada();
    }
}
