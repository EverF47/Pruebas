
package com.PTecnic1.P1.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**@author everf*/
@Data
public class BillDto {
    
    private Long id;
    private LocalDateTime fecha;
    private Long cliente;
}
