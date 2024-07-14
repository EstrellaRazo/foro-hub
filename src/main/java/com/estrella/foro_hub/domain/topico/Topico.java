package com.estrella.foro_hub.domain.topico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos") //plural y minúscula
@Entity(name = "Topico") //singular y mayúscula
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private String curso;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "PENDIENTE", nullable = false)
    private Status status;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String autor;

    public Topico(DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.curso = datosRegistroTopico.curso();
        this.categoria = datosRegistroTopico.categoria();
        this.autor = datosRegistroTopico.autor();
        this.fechaCreacion = LocalDateTime.now();
        this.status = Status.PENDIENTE;
    }

    public void actualizarDatos(DatosActualizarTopico datos) {
        if (datos.titulo() != null) {
            this.titulo = datos.titulo();
        }
        if (datos.mensaje() != null) {
            this.mensaje = datos.mensaje();
        }
        if (datos.curso() != null) {
            this.curso = datos.curso();
        }
        if (datos.categoria() != null) {
            this.categoria = datos.categoria();
        }
        if (datos.status() != null) {
            this.status = datos.status();
        }
        if (datos.autor() != null) {
            this.autor = datos.autor();
        }
    }
}
