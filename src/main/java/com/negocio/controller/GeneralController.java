package com.negocio.controller;

import com.negocio.model.Producto;
import com.negocio.model.Proveedor;
import com.negocio.services.OperationServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GeneralController {

    @Autowired
    private OperationServices operationServices;
    
    /* 
    Lista todos los productos existentes.
    */
    
    @GetMapping("/user/listarTodosProductos")
    public String listarProductos(Model model) {
        try {
            
            List<Producto> productos= operationServices.getProductos();
            model.addAttribute("productos", productos);
            
            return "listarTodosProductos";
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            model.addAttribute("mensaje", "No se pudo listar los productos.");
            
            return "error";
        }       
    }
    
    /* 
    Lista los productos correspondientes a un proveedor, dado el id.
    */
    @GetMapping("/user/listarProductos")
    public String listarProductos(@RequestParam(name = "id") int id, Model model) {
        try {

            Proveedor proveedor = operationServices.getProveedor(id);
            List<Producto> productos= proveedor.getProducto();

            model.addAttribute("proveedor_nombre", proveedor.getNombre());
            model.addAttribute("productos", productos);

            return "listarProductos";
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            model.addAttribute("mensaje", "El proveedor no existe.");
            
            return "error";
        }       
    }

    /* 
    Devuelve información del producto dado el id correspondiente.
    */
    
    @GetMapping("/user/buscarProducto")
    public String buscarProducto(@RequestParam(name = "id") int id, Model model) {
        try {
            
            Producto producto = operationServices.getBuscarProducto(id);

            model.addAttribute("producto", producto);

            return "producto";
            
        } catch (Exception e) {

            System.out.println("Mensaje: " + e.getMessage());
            model.addAttribute("mensaje", "El producto no existe.");
            
            return "error";
        }
    }
    
    /* 
    Elimina un producto dado el id correspondiente.
    */
    
    @GetMapping("/admin/eliminarProducto")
    public String eliminarProductos(@RequestParam(name = "id") int id, Model model) {
        try {
            
            model.addAttribute("mensaje", operationServices.eliminarProducto(id));
            
            return "eliminarProducto";
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            model.addAttribute("mensaje", "El producto no existe.");
            
            return "error";
        }       
    }
    
    /* 
    Modifica el nombre del producto dado el id correspondiente.
    */
    
    @GetMapping("/admin/modificarProducto")
    public String modificarProducto(@RequestParam(name = "id") int id,
                                @RequestParam(name = "nombre") String nombre, 
                                Model model) {
        try {
            
            model.addAttribute("mensaje", operationServices.modificarProducto(nombre, id));
            model.addAttribute("productoModificado", operationServices.getBuscarProducto(id));
            
            return "modificarProducto";
            
        } catch (Exception e) {
            
            System.out.println("Mensaje: " + e.getMessage());
            model.addAttribute("mensaje", "El producto no existe.");
            
            return "error";
        }       
    }
    
     
   
 
    
   
    
}
