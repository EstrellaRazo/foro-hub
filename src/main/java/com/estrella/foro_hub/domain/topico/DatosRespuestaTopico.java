package com.estrella.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosRespuestaTopico(
        Long Id,
         String titulo,
         String mensaje,
         String curso,
         LocalDateTime fechaCreacion,
         Status status,
         Categoria categoria,
         String autor
) {
}
