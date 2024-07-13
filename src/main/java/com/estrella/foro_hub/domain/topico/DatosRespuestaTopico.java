package com.estrella.foro_hub.domain.topico;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long Id,
         String titulo,
         String mensaje,
         LocalDateTime fechaCreacion,
        
         Status status,
        
         Categoria categoria,
         String autor
) {
}
