package com.negocio.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    @ManyToOne(targetEntity = Proveedor.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "proveedor_id", nullable = false)
    private Proveedor proveedor;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detalle_id", referencedColumnName = "id")
    private Detalle detalle;

    public Producto(String nombre, Proveedor proveedor, Detalle detalle){
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.detalle = detalle;
    }
    

}
