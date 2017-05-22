package com.segundo.piso.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saragon
 */
@XmlRootElement
public class ReportePagoMaestros {
    
    @XmlElement
    private double pago;
    
    @XmlElement
    private List<PagoMaestro> pagoMaestros;

    public double getPago() {
        return pago;
    }

    public void setPago(double pago) {
        this.pago = pago;
    }

    public List<PagoMaestro> getPagoMaestros() {
        return pagoMaestros;
    }

    public void setPagoMaestros(List<PagoMaestro> pagoMaestros) {
        this.pagoMaestros = pagoMaestros;
    }
}
