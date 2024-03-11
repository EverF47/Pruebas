package com.PTecnic1.P1.Controller;

import com.PTecnic1.P1.Service.ProductService;
import com.PTecnic1.P1.dto.ProductDto;
import com.PTecnic1.P1.entity.ProductEty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**@author everf*/
@RestController
@RequestMapping("producto")
public class ProductController {
    
    @Autowired
    private ProductService productoService;

    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarProducto(@RequestBody ProductDto producto){
        Long id = productoService.guardarProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ProductDto> obtenerPorId(@PathVariable Long id) {
        ProductDto  productoId = productoService.obtenerPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(productoId);
    }

    @GetMapping("/obtenertodos")
    public ResponseEntity<List<ProductDto>> obtenerTodos(){
        List<ProductDto> listaProducto= productoService.obtenerTodosLosProductos();
        return ResponseEntity.status(HttpStatus.OK).body(listaProducto);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Boolean> actualizarProducto(@RequestBody ProductDto  producto) {
        productoService.actualizarProducto(producto);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProductoPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
}
