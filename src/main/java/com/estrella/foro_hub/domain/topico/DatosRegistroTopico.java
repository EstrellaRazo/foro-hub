package com.estrella.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank(message = "Por favor ingrese un título")
        String titulo,
        @NotBlank(message = "Ingrese el mensaje del tópico")
        String mensaje,
        @NotNull(message = "Ingrese la categoría del tópico")
        Categoria categoria,
        @NotBlank(message = "Ingrese el autor del tópico")
        String autor
) {


}
