package com.negocio.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InitElement {

    @JsonProperty("producto.nombre")
    private String productoNombre;
    
    @JsonProperty("proveedor.nombre")
    private String proveedorNombre;

    @JsonProperty("detalle.descripcion")
    private String detalleDescripcion;

    @JsonProperty("proveedor.id")
    private int proveedorId;

    @JsonProperty("detalle.id")
    private int detalleId;

    

    
}
