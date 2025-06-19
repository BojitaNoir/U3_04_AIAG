package utez.edu.mx.aplicacionprincipios.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.Almacen;
import utez.edu.mx.aplicacionprincipios.service.AlmacenService;

import java.util.*;

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
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Almacen almacen = service.obtenerPorId(id);
        if (almacen != null) {
            return ResponseEntity.ok(almacen);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Almacén no encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Almacen a) {
        Almacen guardado = service.guardar(a);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensaje", "Almacén registrado exitosamente",
                "almacen", guardado
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Almacen a) {
        Almacen actualizado = service.actualizar(id, a);
        if (actualizado != null) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Almacén actualizado correctamente",
                    "almacen", actualizado
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Almacén no encontrado"));
        }
    }

    @PutMapping("/{id}/asignar-cliente/{idCliente}")
    public ResponseEntity<?> asignarCliente(@PathVariable Long id, @PathVariable Long idCliente) {
        Almacen actualizado = service.asignarCliente(id, idCliente);
        if (actualizado != null) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Cliente asignado correctamente al almacén",
                    "almacen", actualizado
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Almacén o Cliente no encontrado"));
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Almacén eliminado con éxito"));
    }
}
