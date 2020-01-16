package com.santander.rio.springboot.springboot.soap;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;

@Entity
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(name = "procedure-uno", procedureName = "agregarTranferencia", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "fecha", type = Date.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "monto", type = Double.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cbuLlegada", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "cbuSalida", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado", type = String.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "imagen", type = Boolean.class)
        }),
        @NamedStoredProcedureQuery(name = "procedure-dos", procedureName = "cambiarEstadoTransferencia", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "id", type = Long.class),
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "estado", type = String.class)
        })
})
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private Date fecha;

    private Double monto;

    private String cbuSalida;

    private String cbuEntrada;

    private String estado;

    private Boolean imagen;

    public String getEstado() {
        return estado;
    }

    public Date getFecha() {
        return fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public String getCbuSalida() {
        return cbuSalida;
    }

    public String getCbuEntrada() {
        return cbuEntrada;
    }

    public Boolean getImagen() {
        return imagen;
    }

    public Transferencia() {}

    public long getId() {
        return id;
    }
}
