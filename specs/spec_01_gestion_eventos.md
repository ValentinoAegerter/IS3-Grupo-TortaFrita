# Spec 1: Gestión y Publicación de Eventos

**1. Objetivo y Contexto**
Permitir a los usuarios con rol de "Organizador" crear, configurar y publicar eventos académicos para que sean visibles al público. Esta es la funcionalidad núcleo que permite establecer la oferta de la plataforma.

**2. Historias de Usuario y Criterios de Aceptación**
* **HU1: Crear Evento.** Como Organizador, quiero dar de alta un evento indicando su tipo, fecha de realización y restricciones de cupo, para habilitar la inscripción.
  * *Criterios:* El sistema debe validar que la fecha de realización sea futura en el momento de la creación. Debe permitir configurar un cupo mínimo y un cupo máximo (opcionales).
* **HU2: Listado Público.** Como Usuario anónimo o registrado, quiero ver el listado de eventos públicos con su tipo y fecha, y poder filtrarlos por "futuros" o "pasados", para informarme sobre la oferta académica.
  * *Criterios:* El listado por defecto debe mostrar solo los eventos futuros.

**3. Requisitos Funcionales y Reglas de Negocio**
* Todo evento debe tener: Título, Tipo (curso, jornada, congreso, etc.), Fecha de realización y un Estado de publicación.
* Se pueden establecer fechas límite para la inscripción. Las fechas límite de inscripción nunca pueden ser posteriores a la fecha de realización del evento.

**4. Restricciones técnicas específicas de este módulo**
* La interfaz del listado de eventos debe ser responsiva y adaptarse a dispositivos móviles.
* La consulta a la base de datos para listar eventos futuros debe estar indexada y optimizada por fecha para un rendimiento adecuado.

**5. Modelo de datos de este módulo**
* Entidades principales implicadas: `Evento`, `TipoEvento`.
* *Nota de modelado:* Mantener el diseño a nivel conceptual (Diagrama ER puro). Definir entidades, atributos y relaciones conceptuales sin incluir detalles de implementación del modelo relacional (sin claves primarias ni foráneas).

**6. Plan de Tareas**
1. Modelar la entidad Evento en la base de datos.
2. Desarrollar la API/Backend para el alta de eventos con validaciones de fecha y cupo.
3. Desarrollar la API/Backend para listar eventos con aplicación de filtros (futuro/pasado).
4. Crear la interfaz de usuario (UI) para el formulario de alta.
5. Crear la UI pública para el catálogo de eventos.

**7. Estrategia de Verificación**
* Escribir pruebas para la lógica que impide establecer una fecha límite de inscripción posterior a la del evento.
* Verificar que los eventos marcados como "pasados" no aparezcan en la vista por defecto del catálogo público.