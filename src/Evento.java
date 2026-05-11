import java.time.LocalDate;

public class Evento {
    private String titulo;
    private String tipo;
    private LocalDate fechaRealizacion;
    private Integer cupoMinimo;
    private Integer cupoMaximo;
    private String estadoPublicacion;

    public Evento(String titulo, String tipo, LocalDate fechaRealizacion) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.fechaRealizacion = fechaRealizacion;
        this.estadoPublicacion = "BORRADOR";
    }

    public void publicarEvento() {
        // Simulación de error para validación: la fecha debe ser futura
        if (this.fechaRealizacion != null && this.fechaRealizacion.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Error simulado: La fecha de realización no puede ser en el pasado para publicar.");
        }
        
        this.estadoPublicacion = "PUBLICADO";
        System.out.println("El evento '" + this.titulo + "' ha sido publicado exitosamente.");
    }
    // Getters y setters omitidos por brevedad
}