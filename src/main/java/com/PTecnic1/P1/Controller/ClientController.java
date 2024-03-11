package com.PTecnic1.P1.Controller;

import com.PTecnic1.P1.Service.ClientService;
import com.PTecnic1.P1.dto.ClientDto;
import com.PTecnic1.P1.entity.ClientEty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
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
@RequestMapping("/cliente")
public class ClientController {
    
    @Autowired
    private ClientService clienteService;
    
    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarClientEty(@RequestBody ClientDto cliente){
        Long id = clienteService.guardarCliente(cliente);
        return ResponseEntity.status(CREATED).body(id);
                
    }
    
    @GetMapping("obtener/{id}")
     public ResponseEntity<ClientDto> obtenerPorId(@PathVariable long id){
        ClientDto clienteid = clienteService.obtenerPorId(id);
        return ResponseEntity.status(OK).body(clienteid);
                
    }
    
     @PutMapping("/actualizar")
    public ResponseEntity<Boolean> actualizarClienteEty (@RequestBody ClientDto cliente){
        clienteService.actualizarCliente(cliente);
        return ResponseEntity.status(HttpStatus.OK).body(Boolean.TRUE);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarCliente(@PathVariable Long id){
        clienteService.eliminarClientePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
     
}
