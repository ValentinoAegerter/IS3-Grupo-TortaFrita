Título: ADR-002 Introducción de Redis para caching y control de accesos
Estado: Propuesto
Fecha: 2026-06-11
Decisores: Valentino Aegerter, Nicolas Firka
Relacionado: Project.md

Contexto
- Qué problema se está resolviendo
  Se detectó latencia alta en consultas frecuentes que agregan y devuelven datos de eventos y listados de asistentes durante picos de uso (inscripciones masivas, apertura de inscripciones).
- Qué restricciones aplican (negocio, técnica, legal)
  Se necesita respuesta de baja latencia para la UI; preferencia por soluciones open-source y compatibles con despliegue en contenedores.
- Qué datos de proyecto sustentan la decisión
  Consultas que devuelven listados y agregaciones son responsables de la mayoría de la carga en momentos pico.

Decisión
Qué se decide exactamente
  Integrar Redis como capa de cache en lectura para consultas frecuentes y como mecanismo de coordinación simple para control de acceso a módulos que requieren throttling (por ejemplo, limitar concurrencia en endpoints de inscripción masiva).
- Alcance (qué cubre y qué no cubre)
  Cubre cacheo de resultados de consultas de listados y uso de Redis para locks/distributed semaphores. No cubre reemplazar la base de datos primaria ni usar Redis como fuente de verdad.

Alternativas consideradas
Opción A: Redis — pros, contras
  Pros: baja latencia, estructuras de datos ricas, soporte para TTL, mecanismos de locking (Redlock). Contras: supervisión y manejo de fallas; requiere políticas de invalidación.
- Opción B: Memcached — pros, contras
  Pros: simple y extremadamente rápido para caching básico. Contras: menor soporte para estructuras avanzadas y para locks distribuidos.
- Opción C: Query optimization / materialized views — pros, contras
  Pros: reduce necesidad de caching externo; Contras: puede aumentar complejidad en la BD y no resolver casos de alta concurrencia en operaciones de escritura/consistencia inmediata.

Consecuencias
Beneficios esperados
  Reducción de latencia en endpoints críticos, mejor experiencia de usuario durante picos.
- Costos o riesgos que se aceptan
  Complejidad añadida (estrategias de invalidación, coherencia), costo operativo de desplegar y monitorear Redis.
- Impacto en operación y equipo
  El equipo deberá aprender patrones de caching e instrumentar métricas (hit ratio, TTL, eviction).

Plan de implementación
- Pasos mínimos para ejecutarla
  1) Provisionar instancia Redis en entorno de staging. 2) Seleccionar librería cliente y middleware de cache (p. ej. ioredis + cache middleware). 3) Implementar cache para endpoints de listados con TTL razonable y endpoints que invaliden claves tras cambios. 4) Añadir monitorización y alertas.

Dependencias
  Redis, cliente Redis para el backend, pipeline de despliegue para la instancia.

Métrica de éxito
  Reducción del p95 de latencia en endpoints cacheados en al menos 50% bajo las mismas pruebas de carga; hit ratio > 70%.

Triggers de revisión
- Qué condiciones obligan a reabrir esta ADR
  Si el cache no mejora la latencia, o causa inconsistencia de datos percibida por usuarios. También si la carga es tan alta que Redis se convierte en cuello de botella.
- Fecha sugerida de revisión
  2026-09-01 (o después de la primera prueba de carga en producción).
