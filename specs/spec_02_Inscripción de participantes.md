# Spec 2: Inscripción de Participantes

**1. Objetivo y Contexto**[cite: 1]
Permitir a los usuarios registrarse en los eventos académicos disponibles en la plataforma, ya sea de forma autónoma o a través del personal del evento[cite: 1]. Este módulo es fundamental para controlar la asistencia, gestionar los cupos y asegurar que el registro se realice dentro de los plazos establecidos por la organización[cite: 1].

**2. Historias de Usuario y Criterios de Aceptación**[cite: 1]
* **HU1: Inscripción Autónoma.** Como Participante (usuario registrado), quiero inscribirme en un evento público para asegurar mi lugar y poder asistir al mismo[cite: 1].
    * *Criterios:* El sistema debe validar que la fecha actual sea anterior o igual a la fecha límite de inscripción del evento[cite: 1]. También debe verificar que haya disponibilidad de lugares y no se haya superado el cupo máximo[cite: 1]. Se debe mostrar un mensaje de confirmación exitosa.
* **HU2: Inscripción por Personal del Evento.** Como Organizador, quiero poder registrar manualmente a una persona en un evento para resolver inconvenientes técnicos, facilitar inscripciones en el lugar o gestionar invitados[cite: 1].
    * *Criterios:* El organizador debe poder buscar al usuario en el sistema. El sistema debe validar la disponibilidad de cupos, aunque el organizador podría tener una opción para forzar la inscripción superando el límite si fuera estrictamente necesario.
* **HU3: Cancelación de Inscripción.** Como Participante, quiero poder cancelar mi inscripción a un evento al que ya no podré asistir, para liberar el lugar en el sistema.
    * *Criterios:* El participante solo puede cancelar su inscripción antes de la fecha límite configurada. El cupo debe liberarse automáticamente y reflejarse en la disponibilidad del evento.

**3. Requisitos Funcionales y Reglas de Negocio**[cite: 1]
* El sistema no debe permitir inscripciones si la fecha actual es posterior a la "fecha límite de inscripción" configurada en el evento[cite: 1].
* El sistema debe rechazar nuevas inscripciones automáticas si el evento ya alcanzó su "cupo máximo"[cite: 1].
* Un mismo participante no puede registrarse más de una vez en un mismo evento.
* Si el evento tiene un "cupo mínimo"[cite: 1], el sistema debe notificar al organizador si este no se alcanza al llegar a la fecha límite de inscripción.

**4. Restricciones técnicas específicas de este módulo**[cite: 1]
* Manejo de concurrencia: La transacción de inscripción a nivel de base de datos debe manejar el bloqueo adecuado para evitar la sobreventa de cupos (race conditions) si múltiples usuarios intentan inscribirse simultáneamente al último lugar disponible.
* La interfaz de inscripción debe ser responsiva, garantizando que el proceso pueda realizarse fácilmente desde cualquier dispositivo móvil[cite: 1].

**5. Modelo de datos de este módulo**[cite: 1]
* Entidades principales implicadas: `Inscripcion`, `Participante`, `Evento`.
* *Nota de modelado:* Mantener el diseño a nivel conceptual (Diagrama ER puro). 
    * `Participante`: atributos conceptuales básicos (Nombre, Apellido, Email).
    * `Inscripcion`: entidad asociativa con atributos propios como `FechaHoraInscripcion` y `EstadoInscripcion` (Confirmada, Cancelada).
    * Relaciones: Un `Participante` realiza muchas `Inscripciones`, y un `Evento` recibe muchas `Inscripciones`.

**6. Plan de Tareas**[cite: 1]
1. Modelar la entidad conceptual `Inscripcion` y sus relaciones con `Evento` y `Participante`.
2. Desarrollar la lógica backend de validación de reglas de negocio (chequeo de cupo máximo, fechas límite y validación de duplicidad).
3. Implementar el endpoint para la solicitud de inscripción autónoma.
4. Crear la interfaz de usuario (UI) para que el participante realice y confirme su inscripción.
5. Desarrollar la vista administrativa para que el organizador realice inscripciones manuales.
6. Implementar la funcionalidad de cancelación y liberación automática de cupos.

**7. Estrategia de Verificación**[cite: 1]
* **Pruebas Unitarias:** Validar estrictamente las funciones de chequeo de fechas límite y cálculo matemático de cupos disponibles.
* **Pruebas de Integración (Concurrencia):** Simular múltiples peticiones simultáneas intentando tomar el último cupo disponible de un evento para garantizar que solo una petición sea exitosa.
* **Pruebas Funcionales:** Comprobar el flujo completo de inscripción desde la vista del participante y desde la vista del organizador.