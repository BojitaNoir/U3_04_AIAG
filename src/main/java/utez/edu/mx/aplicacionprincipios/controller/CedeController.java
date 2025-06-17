package utez.edu.mx.aplicacionprincipios.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.Cede;
import utez.edu.mx.aplicacionprincipios.service.CedeService;

import java.util.*;

@RestController
@RequestMapping("/api/cedes")
@RequiredArgsConstructor
public class CedeController {

    private final CedeService service;

    @GetMapping
    public List<Cede> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Cede cede = service.obtenerPorId(id);
        if (cede != null) {
            return ResponseEntity.ok(cede);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Cede no encontrada"));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cede c) {
        Cede guardada = service.guardar(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensaje", "Cede registrada con éxito",
                "cede", guardada
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cede c) {
        Cede actualizada = service.actualizar(id, c);
        if (actualizada != null) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Cede actualizada correctamente",
                    "cede", actualizada
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Cede no encontrada"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Cede eliminada con éxito"));
    }
}
