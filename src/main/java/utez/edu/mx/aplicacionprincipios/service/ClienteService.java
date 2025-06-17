package utez.edu.mx.aplicacionprincipios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.Cede;
import utez.edu.mx.aplicacionprincipios.model.Cliente;
import utez.edu.mx.aplicacionprincipios.repository.ClienteRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repo;

    public List<Cliente> listar() {
        return repo.findAll();
    }

    @Transactional
    public Cliente guardar(Cliente c) {
        return repo.save(c);
    }

    @Transactional
    public Cliente actualizar(Long id, Cliente nuevo) {
        Cliente existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        existente.setNombreCompleto(nuevo.getNombreCompleto());
        existente.setTelefono(nuevo.getTelefono());
        existente.setCorreo(nuevo.getCorreo());
        return repo.save(existente);
    }

    @Transactional
    public Cliente obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }
    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado");
        }
        repo.deleteById(id);
    }
}