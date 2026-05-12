using System;
using System.Linq;

public class InscripcionService
{
    // Simulación del contexto de base de datos
    private readonly AppDbContext _context; 

    public InscripcionService(AppDbContext context)
    {
        _context = context;
    }

    public string InscribirParticipante(int eventoId, int participanteId)
    {
        var evento = _context.Eventos.Find(eventoId);
        
        if (evento == null) return "Error: Evento no encontrado.";

        // Validación 1: Fecha límite
        if (DateTime.Now > evento.FechaLimiteInscripcion)
        {
            return "Error: La fecha límite de inscripción ha pasado.";
        }

        // Validación 2: Inscripción duplicada
        var inscripcionExistente = _context.Inscripciones
            .Any(i => i.EventoId == eventoId && i.ParticipanteId == participanteId && i.Estado == "Activo");
        
        if (inscripcionExistente)
        {
            return "Error: El participante ya está inscrito en este evento.";
        }

        // Validación 3: Disponibilidad de cupos (y manejo de concurrencia simplificado)
        var inscritosActuales = _context.Inscripciones
            .Count(i => i.EventoId == eventoId && i.Estado == "Activo");

        if (inscritosActuales >= evento.CupoMaximo)
        {
            return "Error: El evento ha alcanzado su cupo máximo.";
        }

        // Crear la inscripción
        var nuevaInscripcion = new Inscripcion
        {
            EventoId = eventoId,
            ParticipanteId = participanteId,
            FechaRegistro = DateTime.Now,
            Estado = "Activo"
        };

        _context.Inscripciones.Add(nuevaInscripcion);
        _context.SaveChanges();

        return "Inscripción exitosa.";
    }

    public string CancelarInscripcion(int inscripcionId)
    {
        var inscripcion = _context.Inscripciones.Find(inscripcionId);
        if (inscripcion == null) return "Error: Inscripción no encontrada.";

        var evento = _context.Eventos.Find(inscripcion.EventoId);

        // Validación: Solo cancelar antes de la fecha límite
        if (DateTime.Now > evento.FechaLimiteInscripcion)
        {
            return "Error: No se puede cancelar la inscripción pasada la fecha límite.";
        }

        inscripcion.Estado = "Cancelado";
        _context.SaveChanges();

        return "Inscripción cancelada. El cupo ha sido liberado.";
    }
}