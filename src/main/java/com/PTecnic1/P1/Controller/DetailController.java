package com.PTecnic1.P1.Controller;

import com.PTecnic1.P1.Service.DetailService;
import com.PTecnic1.P1.dto.DetailDto;
import com.PTecnic1.P1.entity.DetailEty;
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
@RequestMapping("/detallefactura")

public class DetailController {
    
    @Autowired
    private DetailService detallefacturaService;

    @PostMapping("/guardar")
    public ResponseEntity<Long> guardarDetalleFactura(@RequestBody DetailDto detalleFactura) {
        Long id = detallefacturaService.guardarDetalle(detalleFactura);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<DetailDto> obtenerPorId(@PathVariable Long id) {
        DetailDto detalleFacturaId = detallefacturaService.obtenerPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalleFacturaId);
    }

    @GetMapping("/obtenertodos")
    public ResponseEntity<List<DetailDto>> obtenerTodos() {
        List<DetailDto> listaFactura = detallefacturaService.obtenerTodosLosDetalles();
        return ResponseEntity.status(HttpStatus.OK).body(listaFactura);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Boolean> eliminarFactura(@PathVariable Long id) {
        detallefacturaService.eliminarDetallePorId(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Boolean.TRUE);
    }
}
