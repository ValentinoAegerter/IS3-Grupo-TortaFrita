Título: ADR-001 Selección de motor de base de datos (PostgreSQL)
Estado: Aceptado
Fecha: 2026-06-11
Decisores: Valentino Aegerter, Nicolas Firka
Relacionado: Project.md

Contexto
- Qué problema se está resolviendo
  Seleccionar un motor de base de datos relacional para el almacenamiento principal de la aplicación (usuarios, eventos, inscripciones, certificados, informes).
- Qué restricciones aplican (negocio, técnica, legal)
  Requerimiento de consultas ACID para transacciones de inscripción y generación de certificados; preferencia por soluciones open-source y buena integración con ORMs de Node.js/ORMs de backend.
- Qué datos de proyecto sustentan la decisión
  Volumen estimado moderado (varios miles de usuarios, crecimiento en períodos/eventos), necesidad de joins y transacciones.

Decisión
Qué se decide exactamente
  Usar PostgreSQL como motor de base de datos relacional primario.
- Alcance (qué cubre y qué no cubre)
  Cubre almacenamiento relacional de dominio (usuarios, roles, eventos, inscripciones, certificados, encuestas). No cubre almacenamiento de objetos grandes (imágenes/archivos): estos se almacenarán en un bucket object storage (p. ej. S3 compatible) y se referenciarán desde la BD.

Alternativas consideradas
Opción A: PostgreSQL — pros, contras
  Pros: robusto, ACID, funciones avanzadas (JSONB, índices), buena comunidad y herramientas, integración con ORMs. Contras: requiere configuración para escalado horizontal si la carga crece mucho.
- Opción B: MySQL/MariaDB — pros, contras
  Pros: maduro y ampliamente usado. Contras: menor flexibilidad en tipos avanzados (JSONB), ecosistema de extensiones menos rico en algunos casos.
- Opción C: Base NoSQL (MongoDB) — pros, contras
  Pros: flexible para esquemas dinámicos. Contras: consistencia en transacciones multi-colección menos inmediata; aquí necesitamos transacciones claras para inscripciones y certificados.

Consecuencias
Beneficios esperados
  Seguridad de transacción, consultas complejas eficientes, madurez y herramientas de respaldo/restauración.
- Costos o riesgos que se aceptan
  Requerimiento de administración de instancias (backup, vacuum, tuning) y esfuerzo adicional para escalado horizontal en caso de crecimiento muy grande.
- Impacto en operación y equipo
  Necesidad de que el equipo se familiarice con pg_dump/pg_restore, tuning y monitoreo (pg_stat, herramientas externas).

Plan de implementación
- Pasos mínimos para ejecutarla
  1) Provisionar instancia PostgreSQL (local/dev y cloud/staging/production). 2) Definir esquema inicial y migraciones (usar herramienta de migrations). 3) Configurar backups automáticos y monitoreo básico.

Dependencias
  Herramienta de migrations (ej. Knex, TypeORM, Sequelize Migrations) y pipeline de despliegue para secrets/credentials.

Métrica de éxito
  Todas las operaciones críticas (inscripción, generación de certificados) funcionan consistentemente y las transacciones críticas pasan tests automáticos; latencia de consultas principales dentro de los SLO definidos (p. ej. <200ms para consultas claves en carga promedio).

Triggers de revisión
- Qué condiciones obligan a reabrir esta ADR
  Crecimiento sostenido que obligue a particionamiento, necesidad de replicación geográfica, o latencias persistentes en queries críticas.
- Fecha sugerida de revisión
  2027-06-01 (o al alcanzar 10x del volumen inicial estimado).
