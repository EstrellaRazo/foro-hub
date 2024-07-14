package com.estrella.foro_hub.domain.topico;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Categoria categoria,
        Status status,
        String autor
) {
}
