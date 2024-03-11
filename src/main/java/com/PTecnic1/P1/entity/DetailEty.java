
package com.PTecnic1.P1.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import jakarta.persistence.*;
/**@author everf*/
@Entity
@Data
@Table(name = "DETALLE")


public class DetailEty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private Integer cantidad;
    private Integer precio;
    
    @ManyToOne
    private ProductEty producto;
    
    @ManyToOne
    @JsonManagedReference
    private BillEty factura;
    
    
}
