package utez.edu.mx.aplicacionprincipios.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import utez.edu.mx.aplicacionprincipios.model.Cede;
import utez.edu.mx.aplicacionprincipios.repository.CedeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CedeService {
    private final CedeRepository repo;

    public List<Cede> listar() { return repo.findAll(); }

    public Cede guardar(Cede c) { return repo.save(c); }

    public Cede actualizar(Long id, Cede nueva) {
        Cede c = repo.findById(id).orElseThrow();
        c.setEstado(nueva.getEstado());
        c.setMunicipio(nueva.getMunicipio());
        return repo.save(c);
    }
    public Cede obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cede no encontrada"));
    }


    public void eliminar(Long id) { repo.deleteById(id); }
}