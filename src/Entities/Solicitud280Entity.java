/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author Mijael
 */
public class Solicitud280Entity {
    private int id;
    private SolicitudEntity solicitud;
    private int solicitudId;
    private String nitDependencia;
    private String nroDocumento;
    private String nroBoletaPago;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    public Solicitud280Entity(int id, int solicitudId, String nitDependencia, String nroDocumento, String nroBoletaPago, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.nitDependencia = nitDependencia;
        this.nroDocumento = nroDocumento;
        this.nroBoletaPago = nroBoletaPago;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public Solicitud280Entity(int id, int solicitudId, String nitDependencia, String nroDocumento, String nroBoletaPago, byte estado) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.nitDependencia = nitDependencia;
        this.nroDocumento = nroDocumento;
        this.nroBoletaPago = nroBoletaPago;
        this.estado = estado;
    }

    public Solicitud280Entity(int solicitudId, String nitDependencia, String nroDocumento, String nroBoletaPago) {
        this.solicitudId = solicitudId;
        this.nitDependencia = nitDependencia;
        this.nroDocumento = nroDocumento;
        this.nroBoletaPago = nroBoletaPago;
    }

    public Solicitud280Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SolicitudEntity getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudEntity solicitud) {
        this.solicitud = solicitud;
    }

    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public String getNitDependencia() {
        return nitDependencia;
    }

    public void setNitDependencia(String nitDependencia) {
        this.nitDependencia = nitDependencia;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNroBoletaPago() {
        return nroBoletaPago;
    }

    public void setNroBoletaPago(String nroBoletaPago) {
        this.nroBoletaPago = nroBoletaPago;
    }

    public Timestamp getFechaReg() {
        return fechaReg;
    }

    public void setFechaReg(Timestamp fechaReg) {
        this.fechaReg = fechaReg;
    }

    public Timestamp getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(Timestamp fechaMod) {
        this.fechaMod = fechaMod;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nitDependencia;
    }
}
