package com.PTecnic1.P1.Service;

import com.PTecnic1.P1.dto.ClientDto;
import com.PTecnic1.P1.repository.ClientRep;
import com.PTecnic1.P1.entity.ClientEty;
import javax.swing.*;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* @author everf*/
@Service
public class ClientService {
    
    
    @Autowired
    private ClientRep clienteRepository;
    
    public Long guardarCliente(ClientDto clientedto){
        ClientEty cliente = convertirAEntidad(clientedto);
        
        cliente = clienteRepository.save(cliente);
        
        return cliente.getId();
    }
 
    public ClientDto obtenerPorId(Long id){
        
        Optional<ClientEty> clienteId= clienteRepository.findById(id);
        if (clienteId.isPresent()){
            ClientDto dto = convertirADto(clienteId.get());
            return dto;
        }else{
            return null;
        }
    }        
    
    
    
    public List<ClientDto> obtenerTodosLosClientes(){
        return clienteRepository.findAll().stream().map(this::convertirADto).toList();
    }
    
    public ClientDto actualizarCliente(ClientDto cliente){
    
        Optional<ClientEty> clienteActualizarId = clienteRepository.findById(cliente.getId()); 
            if(clienteActualizarId.isPresent()){
                
                ClientEty clienteConsultado = clienteActualizarId.get();
                
                clienteConsultado.setNombre(cliente.getNombre());
                clienteConsultado.setApellido(cliente.getApellido());
                clienteConsultado.setDireccion(cliente.getDireccion());
                clienteConsultado.setFecha_nacimiento(cliente.getFecha_nacimiento());
                clienteConsultado.setTelefono(cliente.getTelefono());
                clienteConsultado.setEmail(cliente.getEmail());
                
                ClientEty clienteActualizado = clienteRepository.save(clienteConsultado);

                
                 return convertirADto(clienteConsultado);
            }
         return null;   
    }
    
    public void eliminarClientePorId(Long id){
    
        Optional<ClientEty>clienteEliminarId = clienteRepository.findById(id);
        if (clienteEliminarId.isPresent()){
                clienteRepository.deleteById(id);
        }else{
               throw new RuntimeException("No existe el cliente con id: " + id);
        }
    
    
    }
    
    private ClientEty convertirAEntidad(ClientDto dto){
        ClientEty entity = new ClientEty();
        entity.setApellido(dto.getApellido());
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        entity.setFecha_nacimiento(dto.getFecha_nacimiento());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        entity.setId(dto.getId());
        
        return entity;
    }
    
    private ClientDto convertirADto(ClientEty entity){
        ClientDto dto = new ClientDto();
       
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDireccion(entity.getDireccion());
        dto.setEmail(entity.getEmail());
        dto.setFecha_nacimiento(entity.getFecha_nacimiento());
        dto.setTelefono(entity.getTelefono());
        dto.setId(entity.getId());
        
        
        return dto;
    }
}
