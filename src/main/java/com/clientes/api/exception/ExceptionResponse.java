
package com.clientes.api.exception;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable {
    private Date timestamp;
    private String mensagem;
    private String detalhes;
}
