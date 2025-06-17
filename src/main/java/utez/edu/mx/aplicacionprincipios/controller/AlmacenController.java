package utez.edu.mx.aplicacionprincipios.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.Almacen;
import utez.edu.mx.aplicacionprincipios.service.AlmacenService;

import java.util.List;

@RestController
@RequestMapping("/api/almacenes")
@RequiredArgsConstructor
public class AlmacenController {

    private final AlmacenService service;

    @GetMapping
    public List<Almacen> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Almacen> obtenerPorId(@PathVariable Long id) {
        Almacen almacen = service.obtenerPorId(id);
        if (almacen != null) {
            return ResponseEntity.ok(almacen);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Almacen> guardar(@RequestBody Almacen a) {
        return new ResponseEntity<>(service.guardar(a), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Almacen actualizar(@PathVariable Long id, @RequestBody Almacen a) {
        return service.actualizar(id, a);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
