/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;

/**
 *
 * @author ASUS
 */
public class MarcaEntity {
    private int id;
    private int solicitudId;
    private byte[] imagen;
    private String descripcion;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    private SolicitudEntity solicitud;
    
    public MarcaEntity(int id,int solicitudId, byte[] imagen, String descripcion, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.solicitudId=solicitudId;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public MarcaEntity(int id,int solicitudId, byte[] imagen, String descripcion, byte estado) {
        this.id = id;
        this.solicitudId=solicitudId;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public MarcaEntity(byte[] imagen, String descripcion) {
        this.imagen = imagen;
        this.descripcion = descripcion;
    }
    
    public MarcaEntity() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSolicitudId() {
        return solicitudId;
    }

    public void setSolicitudId(int solicitudId) {
        this.solicitudId = solicitudId;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public SolicitudEntity getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudEntity solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
