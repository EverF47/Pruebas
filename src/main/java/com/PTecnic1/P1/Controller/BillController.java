package com.PTecnic1.P1.Controller;

import com.PTecnic1.P1.Service.BillService;
import com.PTecnic1.P1.dto.BillDto;
import com.PTecnic1.P1.entity.BillEty;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**@author everf*/
@RestController
@RequestMapping("/factura")/*MAPEA A SOLICITUD ESPECIFICA "factura"*/
public class BillController {
 
    @Autowired
    private BillService facturaService;
    
    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarFactura(@RequestBody BillDto factura) {
        Long id = facturaService.guardarFactura(factura);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<BillDto> obtenerPorId(@PathVariable Long id) {
        BillDto facturaId = facturaService.obtenerPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(facturaId);
    }

    @GetMapping("/obtenertodos")
    public ResponseEntity<List<BillDto>> obtenerTodos() {
        List<BillDto> listaFactura = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.status(HttpStatus.OK).body(listaFactura);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFacturaPorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
    
    
    
    
}
