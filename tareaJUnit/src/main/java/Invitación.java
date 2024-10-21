import java.time.Instant;

/**
 * Clase que representa una invitación enviada a un empleado para asistir a una reunión.
 */
class Invitacion implements Invitable {
    private Instant hora;
    private Empleado empleado;

    /**
     * Constructor para crear una nueva invitación.
     *
     * @param empleado El empleado al que se le envía la invitación.
     */
    public Invitacion(Empleado empleado) {
        this.empleado = empleado;
        this.hora = Instant.now();
    }

    /**
     * Envía la invitación al empleado.
     */
    @Override
    public void invitar() {
        System.out.println("Invitación enviada a: " + empleado.getCorreo() + " a las: " + hora);
    }

    /**
     * Obtiene la hora en que se envió la invitación.
     *
     * @return La hora de la invitación.
     */
    public Instant getHora() {
        return hora;
    }

    /**
     * Obtiene el empleado asociado a la invitación.
     *
     * @return El empleado al que se le envió la invitación.
     */
    public Empleado getEmpleado() {
        return empleado;
    }

    /**
     * Establece el empleado al que se le enviará la invitación.
     *
     * @param empleado El nuevo empleado asociado a la invitación.
     */
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    /**
     * Establece la hora en que se envió la invitación.
     *
     * @param hora La nueva hora de la invitación.
     */
    public void setHora(Instant hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Invitación a: " + empleado.getNombre() + " " + empleado.getApellido() + " (" + empleado.getCorreo() + ") a las: " + hora;
    }
}
