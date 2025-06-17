package utez.edu.mx.aplicacionprincipios.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.Almacen;
import utez.edu.mx.aplicacionprincipios.repository.AlmacenRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlmacenService {

    private final AlmacenRepository repo;

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

    @Transactional
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Almacén no encontrado");
        }
        repo.deleteById(id);
    }
}