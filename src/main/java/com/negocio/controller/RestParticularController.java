package com.negocio.controller;

import com.negocio.model.InitElement;
import com.negocio.services.OperationServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestParticularController {

    @Autowired
    private OperationServices operationServices;
    
    /* 
    Servicio Post para crear un nuevo detalle dado la descripción del producto.
    */
    
    @PostMapping("/admin/nuevoDetalle")
    public String nuevoDetalle(@RequestBody InitElement initElement) {
        
        String mensaje = operationServices.nuevoDetalle(initElement.getDetalleDescripcion()); 

        return mensaje;
    }

    /* 
    Servicio Post para crear un nuevo proveedor dado el nombre del mismo.
    */
   
    @PostMapping("/admin/nuevoProveedor")
    public String nuevoProveedor(@RequestBody InitElement initElement) {
        
        String mensaje = operationServices.nuevoProveedor(initElement.getProveedorNombre()); 

        return mensaje;
    }

    /*    
    Servicio Post para crear un nuevo producto dado el nombre del mismo, los id 
    de proveedor y del detalle.
    */
    
    @PostMapping("/admin/nuevoProducto")
    public String nuevoProducto(@RequestBody InitElement initElement) {
        
        String mensaje = operationServices.nuevoProducto(initElement.getProductoNombre(), initElement.getProveedorId(), initElement.getDetalleId());
    
        return mensaje;
    }
    

}
