package com.estrella.foro_hub.domain.topico;


public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        String curso,
        Categoria categoria,
        Status status,
        String autor
) {
}
