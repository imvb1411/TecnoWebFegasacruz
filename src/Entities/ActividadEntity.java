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
 * @author ASUS
 */
public class ActividadEntity {
    
    private int id;
    private int codigo;
    private String nombre;
    private String descripcion;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    private List<SolicitudEntity> solicitudes;
    public ActividadEntity() {
    }

    public ActividadEntity(int id, String nombre, int codigo, String descripcion, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public ActividadEntity(int id, String nombre, int codigo, String descripcion, byte estado) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public ActividadEntity(String nombre, int codigo, String descripcion) {
        this.nombre = nombre;
        this.codigo = codigo;
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
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SolicitudEntity> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudEntity> solicitudes) {
        this.solicitudes = solicitudes;
    }
}
