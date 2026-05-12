-- Tabla de Eventos
CREATE TABLE Eventos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    cupo_maximo INT NOT NULL,
    cupo_minimo INT NOT NULL,
    fecha_limite_inscripcion TIMESTAMP NOT NULL,
    fecha_evento TIMESTAMP NOT NULL
);

-- Tabla de Participantes (Usuarios)
CREATE TABLE Participantes (
    id SERIAL PRIMARY KEY,
    nombre_completo VARCHAR(200) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL
);

-- Tabla de Inscripciones (Relación N a N)
CREATE TABLE Inscripciones (
    id SERIAL PRIMARY KEY,
    evento_id INT REFERENCES Eventos(id),
    participante_id INT REFERENCES Participantes(id),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(20) DEFAULT 'Activo', -- 'Activo' o 'Cancelado'
    
    -- Restricción: Un participante no puede inscribirse dos veces al mismo evento
    UNIQUE(evento_id, participante_id)
);