package com.PTecnic1.P1.Service;

import com.PTecnic1.P1.dto.BillDto;
import com.PTecnic1.P1.dto.ClientDto;
import com.PTecnic1.P1.repository.BillRep;
import com.PTecnic1.P1.entity.BillEty;
import com.PTecnic1.P1.entity.ClientEty;
import com.PTecnic1.P1.repository.ClientRep;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* @author everf*/

@Service
public class BillService {
    
    @Autowired
    private BillRep facturaRepository;
    @Autowired
    private ClientRep clienteRepository;
    
    public Long guardarFactura(BillDto factura){
        factura.setFecha(LocalDateTime.now());
        BillEty entity = convertirAEntidad(factura);
        entity=facturaRepository.save(entity);
        return entity.getId();
    }
    
    public BillDto obtenerPorId(Long id){
    Optional<BillEty> facturaId= facturaRepository.findById(id);
    if (facturaId.isPresent()){
       
        return convertirADto(facturaId.get());
    }else{
            throw new RuntimeException("No se encontró factura con id: " + id);
        }
    }
    
    public List<BillDto> obtenerTodasLasFacturas(){
        return facturaRepository.findAll().stream().map(this::convertirADto).toList();
    }
    
    public void eliminarFacturaPorId(Long id){
    Optional<BillEty>clienteEliminarId = facturaRepository.findById(id);
    if (clienteEliminarId.isPresent()){
    
        facturaRepository.deleteById(id);
    }else{
            throw new RuntimeException("No se encontró factura con id: " + id);
        }
    }
    
    
    private BillEty convertirAEntidad(BillDto dto){
        BillEty entity = new BillEty();
        Optional<ClientEty> cliente=clienteRepository.findById(dto.getCliente());
        if(cliente.isPresent()){
            entity.setCliente(cliente.get());
        }else{
            throw new RuntimeException("No se encontró cliente con id: " + dto.getCliente());
        }
        entity.setId(dto.getId());
        entity.setFecha(dto.getFecha());

        
        return entity;
    }
    
    private BillDto convertirADto(BillEty entity){
        BillDto dto = new BillDto();
       
        
        dto.setCliente(entity.getCliente().getId());
        dto.setFecha(entity.getFecha());
        dto.setId(entity.getId());
     
        return dto;
    }
}
