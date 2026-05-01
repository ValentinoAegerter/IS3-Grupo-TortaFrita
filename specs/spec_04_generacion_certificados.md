# Spec 4: Generación de Certificados

**1. Objetivo y Contexto**
Automatizar la emisión de certificados para los participantes y disertantes de los eventos académicos. Esto reduce drásticamente la carga administrativa post-evento de los organizadores y le permite a los usuarios obtener su comprobante de manera inmediata y en formato digital.

**2. Historias de Usuario y Criterios de Aceptación**
* **HU1: Certificado de Asistencia.** Como Participante, quiero descargar mi certificado de asistencia en formato PDF desde mi perfil, para poder presentarlo en mi trabajo o institución.
  * *Criterios:* El botón de descarga solo debe estar habilitado si el estado del participante en ese evento figura como "Acreditado" o "Presente".
* **HU2: Certificado de Disertante.** Como Disertante, quiero obtener un certificado que acredite mi participación como expositor en una actividad específica de la agenda.
  * *Criterios:* El sistema debe reconocer el rol asignado en la agenda (creada en la Spec 3) y emitir un diploma con un texto distinto al de un asistente regular.
* **HU3: Validación de Autenticidad.** Como Institución externa, quiero poder verificar que un certificado presentado por un alumno es real y fue emitido por la plataforma.
  * *Criterios:* Cada certificado generado debe incluir un código único de verificación (hash o alfanumérico) que se pueda consultar en la web pública del sistema.

**3. Requisitos Funcionales y Reglas de Negocio**
* Un certificado de "Asistencia" no puede ser generado si no existe un registro previo de check-in (acreditación) para ese usuario en ese evento.
* El documento generado debe contener obligatoriamente: Nombre y Apellido del usuario, DNI, Nombre del Evento, Fecha de emisión, Tipo de participación (Asistente/Expositor/Organizador) y el Código de Verificación.
* Los certificados deben ser inmutables; una vez generados, si el usuario cambia su nombre en el perfil, el certificado antiguo debe conservar el nombre original con el que se emitió.

**4. Restricciones técnicas específicas de este módulo**
* La generación de los documentos debe realizarse en formato estándar PDF para evitar alteraciones fáciles.
* Si el organizador solicita una "generación por lotes" (todos los certificados a la vez), el proceso debe manejarse de forma asíncrona en el backend para no bloquear la interfaz web, ya que procesar cientos de PDFs consume mucha memoria.

**5. Modelo de datos de este módulo**
* Entidades principales implicadas: `Certificado`. Se relaciona conceptualmente con las entidades `Participante`, `Evento` y `Rol`.
* *Atributos conceptuales de Certificado:* Tipo de Certificado (Asistencia, Aprobación, Expositor), Fecha de Emisión, Código de Verificación Único, Ruta del Archivo (URL temporal o persistente).
* *Nota de modelado:* Se mantiene el rigor del modelo Entidad-Relación puro. La relación entre el Certificado, el Participante y el Evento se establece conceptualmente sin la inclusión de claves primarias ni foráneas como atributos.

**6. Plan de Tareas**
1. Diseñar el modelo conceptual de la entidad Certificado y sus vínculos.
2. Integrar una librería de generación de PDFs en el backend (ej. iText, Apache PDFBox).
3. Desarrollar el endpoint para validar que el usuario cumple los requisitos (asistencia/rol) y generar el PDF dinámicamente con sus datos.
4. Desarrollar el endpoint público para la consulta del Código de Verificación.
5. Crear la interfaz (UI) para que el participante vea y descargue sus certificados disponibles.

**7. Estrategia de Verificación**
* Pruebas unitarias: Forzar la solicitud de descarga de un certificado para un usuario que se inscribió pero no fue acreditado, verificando que el sistema devuelva un error de permisos (403 Forbidden).
* Pruebas de integración: Generar un certificado, copiar su código de verificación y consultarlo en el endpoint público asegurando que devuelva los datos correctos del documento.