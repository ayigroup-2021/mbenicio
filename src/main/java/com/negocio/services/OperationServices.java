package com.negocio.services;

import com.negocio.model.Detalle;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.negocio.model.Proveedor;
import com.negocio.model.Producto;
import com.negocio.repository.DetalleRepository;
import com.negocio.repository.ProveedorRepository;
import com.negocio.repository.ProductoRepository;


@Service
public class OperationServices {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private DetalleRepository detalleRepository;
    
    /* 
    Devuelve la lista de todos los productos.
    */
    public List<Producto> getProductos(){
    
        return productoRepository.findAll();
    
    }
    
    /* 
    Devuelve el producto buscado con el id recibido.
    */
    public Producto getBuscarProducto(int id){

        Optional<Producto> producto = productoRepository.findById(id);
     
        return producto.get();     
    }
    
    /* 
    Devuelve el proveedor buscado con el id recibido.
    */
    public Proveedor getProveedor(int id){

        Optional<Proveedor> proveedor = proveedorRepository.findById(id);
    
        return proveedor.get();   
    
    }

    /* 
    ELimina el producto buscado con el id recibido.
    */
    
    public String eliminarProducto(int id) {
        try {

            Optional<Producto> producto = productoRepository.findById(id);
            productoRepository.delete(producto.get());

            return "Exito";
            
        } catch (Exception e) {
            
            return "Error";
        
        }
    }
    
    /* 
    Modifica el nombre del producto con el id recibido.
    */
    
    public String modificarProducto(String nombre, int id) {
        try {

            Optional<Producto> producto = productoRepository.findById(id);
            producto.get().setNombre(nombre);
            productoRepository.save(producto.get());

            return "Exito";
            
        } catch (Exception e) {
        
            return "Error";
        
        }
    }
    
    
    /*
    Crea un nuevo detalle correspondiente un producto a partir de su descripción.
    */
    
    public String nuevoDetalle(String descripcion){
        try {
            
            Detalle detalle = new Detalle(descripcion);
            detalleRepository.save(detalle);

            return "Exito, detalle creado. -> id: " + detalle.getId() +
                    " descripción: " + detalle.getDescripcion();
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            
            return "Error - El detalle no pudo ser creado.";
        
        }     
    }

    /* 
    Crea un nuevo proveedor a partir de su nombre.
    */
   
    public String nuevoProveedor(String nombre){
        try {

            Proveedor proveedor = new Proveedor(nombre);
            proveedorRepository.save(proveedor);

            return "Exito, proveedor creado. -> id: " + proveedor.getId() +
                    " nombre: " + proveedor.getNombre();
            
        } catch (Exception e) {
        
            System.out.println("Mensaje: " + e.getMessage());
            
            return "Error - El detalle no pudo ser creado.";
        }     
    }
    
    /* 
    Crea un nuevo producto dado el nombre del mismo,y los id de proveedor y detalle.
    */
    
    public String nuevoProducto(String nombre, int proveedor_id, int detalle_id){
        try {

            Optional<Proveedor> proveedor = proveedorRepository.findById(proveedor_id);
            Optional<Detalle> detalle = detalleRepository.findById(detalle_id);
            Producto producto = new Producto(nombre,proveedor.get(), detalle.get());
            productoRepository.save(producto);
            
            return "Exito, producto creado -> id: " +producto.getId() +
                    " - idProveedor: " + producto.getProveedor().getId() +
                    "  - idDetalle: " + producto.getDetalle().getId();
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            
            return "Error - El producto no pudo ser creado.";
        
        }
    }

    
}
