/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PTecnic1.P1.Service;

import com.PTecnic1.P1.dto.ProductDto;
import com.PTecnic1.P1.repository.ProductRep;
import com.PTecnic1.P1.entity.ProductEty;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author everf
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRep productoRepository;
    
    public Long guardarProducto(ProductDto producto){
        ProductEty entity = convertirAEntidad(producto);
        entity = productoRepository.save(entity);
        return entity.getId();
    }
    
    public ProductDto obtenerPorId(Long id){
        Optional<ProductEty> productoId = productoRepository.findById(id);
        if(productoId.isPresent()){
            return convertirADto(productoId.get());
        }else {
            throw new RuntimeException("No existe el producto con id: " + id);
        }
    }
    

    
    public List<ProductDto>obtenerTodosLosProductos(){
        return productoRepository.findAll().stream().map(this::convertirADto).toList();
    }
    
    public ProductDto actualizarProducto(ProductDto producto){
    
        Optional<ProductEty> productoActualizarId= productoRepository.findById(producto.getId());
        if(productoActualizarId.isPresent()){
        
            ProductEty productoConsultado= productoActualizarId.get();
            
            productoConsultado.setNombre(producto.getNombre());
            productoConsultado.setPrecio(producto.getPrecio());
            productoConsultado.setStock(producto.getStock());
            
            ProductEty productoActualizado= productoRepository.save(productoConsultado);
            return convertirADto(productoActualizado);
           
        }else {
            throw new RuntimeException("No existe el producto con id: " + producto.getId());
        }
    }
    public void eliminarProductoPorId(Long id){
    
        Optional<ProductEty>productoEliminarId= productoRepository.findById(id);
        
        if(productoEliminarId.isPresent()){
        
            productoRepository.deleteById(id);
        } else {
            throw new RuntimeException("No existe el producto con id: " + id);
        }
        
    }
    
    private ProductEty convertirAEntidad(ProductDto dto){
        ProductEty entity = new ProductEty();
        entity.setPrecio(dto.getPrecio());
        entity.setId(dto.getId());
        entity.setStock(dto.getStock());
        entity.setNombre(dto.getNombre());
        return entity;
    }
    
    private ProductDto convertirADto(ProductEty entity){
        ProductDto dto = new ProductDto();
        dto.setPrecio(entity.getPrecio());
        dto.setId(entity.getId());
        dto.setStock(entity.getStock());
        dto.setNombre(entity.getNombre());
        return dto;
    }
}
