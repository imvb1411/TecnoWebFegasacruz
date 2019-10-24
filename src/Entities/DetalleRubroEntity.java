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
public class DetalleRubroEntity {
    private int id;
    private int rubroId;
    private int codigo;
    private String nombre;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;
    
    private RubroEntity rubro;

    public DetalleRubroEntity(int id,int rubroId, int codigo, String nombre, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.rubroId=rubroId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public DetalleRubroEntity(int id,int rubroId, int codigo, String nombre, byte estado) {
        this.id = id;
        this.rubroId=rubroId;
        this.codigo = codigo;
        this.nombre = nombre;
        this.estado = estado;
    }

    public DetalleRubroEntity(int rubroId,int codigo, String nombre) {
        this.rubroId=rubroId;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public DetalleRubroEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRubroId() {
        return rubroId;
    }

    public void setRubroId(int rubroId) {
        this.rubroId = rubroId;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public RubroEntity getRubro() {
        return rubro;
    }

    public void setRubro(RubroEntity rubro) {
        this.rubro = rubro;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
