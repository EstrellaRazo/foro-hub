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

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        topicoRepository.save(new Topico(datosRegistroTopico));
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
}
