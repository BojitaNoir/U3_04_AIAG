package utez.edu.mx.aplicacionprincipios.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.Cede;
import utez.edu.mx.aplicacionprincipios.service.CedeService;

import java.util.List;

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
    public ResponseEntity<Cede> obtenerPorId(@PathVariable Long id) {
        Cede cede = service.obtenerPorId(id);
        return ResponseEntity.ok(cede);
    }

    @PostMapping
    public ResponseEntity<Cede> guardar(@Valid @RequestBody Cede c) {
        return new ResponseEntity<>(service.guardar(c), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Cede actualizar(@PathVariable Long id, @RequestBody Cede c) {
        return service.actualizar(id, c);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}