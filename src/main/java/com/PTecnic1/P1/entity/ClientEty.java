
package com.PTecnic1.P1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**@author everf*/
@Data
@Entity
@Table(name = "CLIENTE")
public class ClientEty {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String nombre;
    private String apellido;
    private String direccion;
    private String fecha_nacimiento;
    private String telefono;
    private String email;
}
