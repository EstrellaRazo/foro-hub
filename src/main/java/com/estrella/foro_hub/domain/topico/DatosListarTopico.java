package com.estrella.foro_hub.domain.topico;

import java.time.LocalDateTime;

public record DatosListarTopico(
        Long Id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        Categoria categoria,
        String autor
) {
    public DatosListarTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getStatus(), topico.getCategoria(), topico.getAutor());
    }
}
