package com.negocio.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Proveedor{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String nombre;

    @OneToMany(targetEntity = Producto.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "proveedor_id", referencedColumnName = "id")
    private List<Producto> producto;

    public Proveedor(String nombre){
        this.nombre = nombre;
    }
    
}
