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
public class UbicacionEntity {
    private int id;
    private int ubicacionid;
    private String nombre;
    private String tipo;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    public UbicacionEntity() {
    }

    public UbicacionEntity(int ubicacionid, String nombre, String tipo) {
        this.ubicacionid = ubicacionid;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoUbicacionId() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getUbicacionid() {
        return ubicacionid;
    }

    public void setUbicacionid(int ubicacionid) {
        this.ubicacionid = ubicacionid;
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
}
