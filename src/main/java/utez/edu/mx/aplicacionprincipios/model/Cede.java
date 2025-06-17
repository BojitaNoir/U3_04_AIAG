package utez.edu.mx.aplicacionprincipios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cede {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El municipio es obligatorio")
    private String municipio;


    @PrePersist
    public void generarClave() {
        if (clave == null) {
            String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
            int random = new Random().nextInt(9000) + 1000;
            this.clave = "C" + id + "-" + fecha + "-" + random;
        }
    }
}
