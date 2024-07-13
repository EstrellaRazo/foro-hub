package com.estrella.foro_hub.controller;

import com.estrella.foro_hub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        Topico topico = topicoRepository.save(new Topico(datosRegistroTopico));
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus(), topico.getCategoria(), topico.getAutor());
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listaTopicos(@PageableDefault(size= 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion)
                .map(DatosListarTopico::new));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosActualizarTopico datos) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            Topico topicoActualizado = topico.get();
            topicoActualizado.actualizarDatos(datos);
            topicoRepository.save(topicoActualizado);
            return ResponseEntity.ok(topicoActualizado);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> detallarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosRespuestaTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getFechaCreacion(), topico.getStatus(), topico.getCategoria(), topico.getAutor());
        return ResponseEntity.ok(datosTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent())
        {
            Topico topicoAEliminar = topico.get();
            topicoRepository.delete(topicoAEliminar);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
