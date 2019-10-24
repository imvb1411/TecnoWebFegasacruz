/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Mijael
 */
public class Solicitud701Entity {
    private int id;
    private int solicitudId;
    private int ddjjOriginal;
    private int folio;
    private String nroTituloPropiedad;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;
    
    private SolicitudEntity solicitud;
    private List<ClienteSolicitudEntity> detalle;

    public Solicitud701Entity(int id, int solicitudId, int ddjjOriginal, int folio, String nroTituloPropiedad, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.ddjjOriginal = ddjjOriginal;
        this.folio = folio;
        this.nroTituloPropiedad = nroTituloPropiedad;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public Solicitud701Entity(int id, int solicitudId, int ddjjOriginal, int folio, String nroTituloPropiedad, byte estado) {
        this.id = id;
        this.solicitudId = solicitudId;
        this.ddjjOriginal = ddjjOriginal;
        this.folio = folio;
        this.nroTituloPropiedad = nroTituloPropiedad;
        this.estado = estado;
    }

    public Solicitud701Entity(int solicitudId, int ddjjOriginal, int folio, String nroTituloPropiedad) {
        this.solicitudId = solicitudId;
        this.ddjjOriginal = ddjjOriginal;
        this.folio = folio;
        this.nroTituloPropiedad = nroTituloPropiedad;
    }

    public Solicitud701Entity() {
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

    public int getDdjjOriginal() {
        return ddjjOriginal;
    }

    public void setDdjjOriginal(int ddjjOriginal) {
        this.ddjjOriginal = ddjjOriginal;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getNroTituloPropiedad() {
        return nroTituloPropiedad;
    }

    public void setNroTituloPropiedad(String nroTituloPropiedad) {
        this.nroTituloPropiedad = nroTituloPropiedad;
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

    public List<ClienteSolicitudEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<ClienteSolicitudEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return nroTituloPropiedad;
    }
}
