package utez.edu.mx.aplicacionprincipios.model;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Almacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clave;

    @NotNull(message = "La fecha de registro es obligatoria")
    private LocalDate fechaRegistro;

    @NotNull(message = "El precio de venta es obligatorio")
    @PositiveOrZero(message = "El precio de venta debe ser mayor o igual a cero")
    private Double precioVenta;

    @NotNull(message = "El precio de renta es obligatorio")
    @PositiveOrZero(message = "El precio de renta debe ser mayor o igual a cero")
    private Double precioRenta;

    @NotBlank(message = "El tamaño es obligatorio")
    @Pattern(regexp = "G|M|P", message = "El tamaño debe ser G, M o P")
    private String tamaño;

    @ManyToOne
    @NotNull(message = "La cede es obligatoria")
    private Cede cede;

    @ManyToOne
    @JoinColumn(name = "idCliente") // Opcionalmente puedes nombrar la columna
    private Cliente cliente;

    @PrePersist
    public void generarClave() {
        this.fechaRegistro = LocalDate.now();
        if (clave == null && cede != null) {
            this.clave = cede.getClave() + "-A" + id;
        }
    }
}