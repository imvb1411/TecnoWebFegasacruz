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
public class RubroEntity {
    private int id;
    private String nombre;
    private String descripcion;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;
    
    private List<DetalleRubroEntity> detalle;

    public RubroEntity(int id, String nombre, String descripcion, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public RubroEntity(int id, String nombre, String descripcion, byte estado) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public RubroEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public RubroEntity() {
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

    public List<DetalleRubroEntity> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleRubroEntity> detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
