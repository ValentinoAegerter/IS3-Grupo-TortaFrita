# Spec 3: Generación de Informes y Agenda del Evento

**1. [cite_start]Objetivo y Contexto** [cite: 14]
[cite_start]Proveer a los organizadores de herramientas para extraer información valiosa sobre la concurrencia y el estado del evento mediante la generación de informes[cite: 41]. [cite_start]Adicionalmente, dotar al sistema de la capacidad de estructurar y mostrar una agenda detallada de actividades[cite: 41], permitiendo a los participantes conocer el cronograma de los eventos académicos en los que se inscriben.

**2. [cite_start]Historias de Usuario y Criterios de Aceptación** [cite: 15]
* **HU1: Reporte de Inscritos.** Como Organizador, quiero generar un reporte con el listado completo de los participantes inscritos en mi evento para controlar la asistencia y organizar la logística.
    * *Criterios:* El sistema debe permitir exportar el listado en un formato estándar (ej. CSV o PDF). El reporte debe incluir el nombre, apellido, correo electrónico y estado de la inscripción (confirmada/cancelada).
* **HU2: Creación de la Agenda.** Como Organizador, quiero poder cargar las distintas actividades (charlas, talleres, recesos) que componen el evento, para armar la agenda oficial.
    * *Criterios:* Se debe poder ingresar un título de actividad, hora de inicio, hora de fin y, opcionalmente, asignar un disertante. Las fechas y horas de las actividades deben estar comprendidas dentro de la fecha de realización del evento.
* **HU3: Visualización de la Agenda.** Como Participante o Usuario Anónimo, quiero ver la agenda del evento en su página pública para conocer el cronograma antes o durante mi asistencia.
    * *Criterios:* La agenda debe mostrarse ordenada cronológicamente de forma ascendente (desde la primera actividad hasta la última).

**3. [cite_start]Requisitos Funcionales y Reglas de Negocio** [cite: 16]
* La generación de informes de inscritos solo está permitida para usuarios con el rol de Organizador de ese evento específico[cite: 37].
* Una actividad dentro de la agenda no puede tener una hora de fin anterior o igual a su hora de inicio.
* Las actividades de la agenda deben pertenecer estrictamente a las fechas establecidas para el evento general.
* Los reportes deben reflejar los datos en tiempo real al momento de su generación.

**4. [cite_start]Restricciones técnicas específicas de este módulo** [cite: 17]
* El procesamiento y exportación de reportes (especialmente si el evento tiene muchos participantes) debe estar optimizado a nivel de consultas a la base de datos para no bloquear la interfaz del usuario. 
* La visualización de la agenda en la vista pública debe ser responsiva, utilizando un diseño que facilite la lectura tipo "línea de tiempo" (timeline) en dispositivos móviles.

**5. [cite_start]Modelo de datos de este módulo** [cite: 18]
* Entidades principales implicadas: `Evento`, `Actividad`, `Disertante`.
* *Nota de modelado:* Mantener el diseño a nivel conceptual (Diagrama ER puro).
    * `Actividad`: atributos conceptuales como `Titulo`, `HoraInicio`, `HoraFin`, `Descripcion`.
    * Relaciones: Un `Evento` contiene muchas `Actividades` (composición). Una `Actividad` puede ser dictada por uno o más `Disertantes`.

**6. [cite_start]Plan de Tareas** [cite: 19]
1. Modelar las entidades `Actividad` y `Disertante` y sus relaciones conceptuales con `Evento`.
2. Desarrollar las consultas (queries) optimizadas para la extracción de datos de participantes inscritos.
3. Implementar el endpoint y el servicio para la generación y descarga del archivo del reporte (CSV/PDF).
4. Crear la interfaz (ABM/CRUD) para que el organizador pueda gestionar las actividades de la agenda.
5. Desarrollar el componente de interfaz de usuario (UI) público para mostrar la agenda cronológica del evento.

**7. [cite_start]Estrategia de Verificación** [cite: 20]
* **Pruebas Unitarias:** Validar las reglas de negocio de las fechas y horas (ej. que no se pueda crear una actividad fuera del rango del evento o con horarios invertidos).
* **Pruebas Funcionales:** Comprobar que la exportación del reporte de inscritos descargue un archivo válido y con los datos correctos según el estado actual de la base de datos.
* **Pruebas de Interfaz (UI):** Verificar que la agenda pública se visualice correctamente y sin solapamientos visuales tanto en resoluciones de escritorio como de teléfonos móviles.