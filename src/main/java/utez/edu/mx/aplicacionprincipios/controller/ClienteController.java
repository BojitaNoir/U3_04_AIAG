package utez.edu.mx.aplicacionprincipios.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import utez.edu.mx.aplicacionprincipios.model.Cliente;
import utez.edu.mx.aplicacionprincipios.service.ClienteService;

import java.util.*;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Cliente cliente = service.obtenerPorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Cliente no encontrado"));
        }
    }

    @PostMapping
    public ResponseEntity<?> guardar(@Valid @RequestBody Cliente cliente) {
        Cliente guardado = service.guardar(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "mensaje", "Cliente creado exitosamente",
                "cliente", guardado
        ));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
        Cliente actualizado = service.actualizar(id, cliente);
        if (actualizado != null) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Cliente actualizado correctamente",
                    "cliente", actualizado
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("mensaje", "Cliente no encontrado"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.ok(Map.of("mensaje", "Cliente eliminado con éxito"));
    }
}
