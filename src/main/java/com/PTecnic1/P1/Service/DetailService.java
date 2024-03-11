
package com.PTecnic1.P1.Service;

import com.PTecnic1.P1.dto.DetailDto;
import com.PTecnic1.P1.entity.BillEty;
import com.PTecnic1.P1.repository.DetailRep;
import com.PTecnic1.P1.repository.ProductRep;
import com.PTecnic1.P1.entity.DetailEty;
import com.PTecnic1.P1.entity.ProductEty;
import com.PTecnic1.P1.repository.BillRep;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*@author everf*/
@Service
public class DetailService {
    
    @Autowired
    private DetailRep detalleFacturaRepository;
    
    @Autowired
    private ProductRep productoRepository;
    
    @Autowired
    private BillRep facturaRepository;
    
    public Long guardarDetalle(DetailDto detalleFactura){
    
        if (detalleFactura.getCantidad() > 1){
            DetailEty entity = convertirAEntidad(detalleFactura);
            entity = detalleFacturaRepository.save(entity);
            return entity.getId();
        }else{
            throw new RuntimeException("La cantidad de productos debe ser mayor a 0");
        }
    }
   
    public DetailDto obtenerPorId(Long id){
    
        Optional<DetailEty> detfacId = detalleFacturaRepository.findById(id);
        if(detfacId.isPresent()){
            return convertirADto(detfacId.get());
        }else {
            throw new RuntimeException("No existe el detalle con id: " + id);
        }
    
    }
    
    public List<DetailDto> obtenerTodosLosDetalles(){
        return detalleFacturaRepository.findAll().stream().map(this::convertirADto).toList();
    }
            
    public void eliminarDetallePorId(Long id){
    
        Optional<DetailEty>clienteEliminarPorId = detalleFacturaRepository.findById(id);
        if(clienteEliminarPorId.isPresent()){
        
            detalleFacturaRepository.deleteById(id);
        }else {
            throw new RuntimeException("No existe el detalle con id: " + id);
        }
  
    }
    
    private DetailEty convertirAEntidad(DetailDto dto){
        DetailEty entity = new DetailEty();
        entity.setCantidad(dto.getCantidad());
        entity.setPrecio(dto.getPrecio());
        entity.setId(dto.getId());
        Optional<BillEty> bill = facturaRepository.findById(dto.getFactura());
        if(bill.isPresent()){
            entity.setFactura(bill.get());
        }else{
            throw new RuntimeException("No existe la factura con id: " + dto.getFactura());
        }
        Optional<ProductEty> product = productoRepository.findById(dto.getProducto());
        if(product.isPresent()){
            entity.setProducto(product.get());
        }else{
            throw new RuntimeException("No existe el producto con id: " + dto.getProducto());
        }
        return entity;
    }
    
    private DetailDto convertirADto(DetailEty entity){
        DetailDto dto = new DetailDto();
        dto.setCantidad(entity.getCantidad());
        dto.setPrecio(entity.getPrecio());
        dto.setId(entity.getId());
        dto.setFactura(entity.getFactura().getId());
        dto.setProducto(entity.getProducto().getId());
        return dto;
    }
}
