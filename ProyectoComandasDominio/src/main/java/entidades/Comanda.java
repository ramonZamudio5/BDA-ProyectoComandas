/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Cricri
 */
@Entity
public class Comanda implements Serializable {
 @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String folio;

    private LocalDateTime fechaHoraCreacion;

    private double totalVenta;

    @Enumerated(EnumType.STRING)
    private EstadoComanda estado;

    @ManyToOne
    private Cliente cliente; // Asociación opcional con Cliente

    @OneToMany(mappedBy = "comanda")
    private List<DetalleComanda> detalles;

}
