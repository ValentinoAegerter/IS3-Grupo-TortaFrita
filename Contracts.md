# Global Contracts and Technical Constraints

## Stack Tecnológico y Entorno
* **Lenguaje y Entorno:** Java (JDK 21).
* **Entorno de Desarrollo Sugerido:** Visual Studio Code.
* **Plataforma:** Aplicación Web.

## Reglas de Arquitectura y Base de Datos
* **Diseño de Base de Datos:** Al generar modelos conceptuales, el agente debe respetar estrictamente el estándar conceptual del diagrama Entidad-Relación (ER). No se deben mezclar conceptos del Modelo Relacional en esta etapa; por lo tanto, no se deben incluir claves primarias (PK) ni claves foráneas (FK) como atributos dentro de las entidades en la fase conceptual.
* **Estilo de Código:** Priorizar código limpio, modular y documentado.

## Reglas Generales de SDD
* Cada especificación de característica (spec) debe contener el volumen de contenido adecuado para la ejecución de tareas automatizadas y debe respetar la estructura obligatoria de 7 puntos.