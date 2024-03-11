
package com.PTecnic1.P1.dto;
import lombok.Data;

/**@author everf*/
@Data
public class ClientDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String fecha_nacimiento;
    private String telefono;
    private String email;
}
