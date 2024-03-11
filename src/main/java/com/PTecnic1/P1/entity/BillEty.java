
package com.PTecnic1.P1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author everf*/
@Data
@Entity
@Table(name = "FACTURA")
public class BillEty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;
    private LocalDateTime fecha;
    
    
    @ManyToOne
    private ClientEty cliente;
    
    @OneToMany(mappedBy = "factura")
    @JsonManagedReference
    private List<DetailEty> detalle;
            
    
    
}
