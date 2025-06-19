package utez.edu.mx.aplicacionprincipios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.Almacen;
import utez.edu.mx.aplicacionprincipios.model.Cliente;
import utez.edu.mx.aplicacionprincipios.repository.AlmacenRepository;
import utez.edu.mx.aplicacionprincipios.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlmacenService {

    private final AlmacenRepository repo;
    private final ClienteRepository clienteRepo; // <-- Agrega esto


    public List<Almacen> listar() {
        return repo.findAll();
    }

    @Transactional
    public Almacen guardar(Almacen a) {
        return repo.save(a);
    }

    @Transactional
    public Almacen actualizar(Long id, Almacen nuevo) {
        Almacen existente = repo.findById(id).orElseThrow(() -> new RuntimeException("Almacén no encontrado"));
        existente.setPrecioVenta(nuevo.getPrecioVenta());
        existente.setPrecioRenta(nuevo.getPrecioRenta());
        existente.setTamaño(nuevo.getTamaño());
        existente.setCede(nuevo.getCede());
        return repo.save(existente);
    }

    @Transactional
    public Almacen obtenerPorId(Long id) {
        return repo.findById(id).orElse(null);
    }


    public Almacen asignarCliente(Long id, Long clienteId) {
        Optional<Almacen> almacenOpt = repo.findById(id);
        Optional<Cliente> clienteOpt = clienteRepo.findById(clienteId);

        if (almacenOpt.isPresent() && clienteOpt.isPresent()) {
            Almacen almacen = almacenOpt.get();
            almacen.setCliente(clienteOpt.get());
            return repo.save(almacen);
        }

        return null;
    }

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Almacén no encontrado");
        }
        repo.deleteById(id);
    }
}