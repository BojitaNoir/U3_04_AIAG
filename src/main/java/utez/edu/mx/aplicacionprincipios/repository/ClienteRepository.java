package utez.edu.mx.aplicacionprincipios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utez.edu.mx.aplicacionprincipios.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
