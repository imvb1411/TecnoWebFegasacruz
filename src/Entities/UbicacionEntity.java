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
    
    public static enum TIPO{
        DEPARTAMENTO{
            @Override
            public String toString() {
                return "1";
            }
        },PROVINCIA{
            @Override
            public String toString() {
                return "2";
            }
        },MUNICIPIO{
            @Override
            public String toString() {
                return "3";
            }
        },ZONA{
            @Override
            public String toString() {
                return "4";
            }
        },SUBZONA{
            @Override
            public String toString() {
                return "5";
            }
        },SITIO{
            @Override
            public String toString() {
                return "6";
            }
        }
    }
    
    private int id;
    private int ubicacionId;
    private String nombre;
    private TIPO tipo;
    private String tipoUbicacion;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;
    
    private UbicacionEntity ubicacion;

    public UbicacionEntity(int id, int ubicacionId, String nombre, TIPO tipo, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.ubicacionId = ubicacionId;
        this.nombre = nombre;
        this.tipoUbicacion = tipo.toString();
        this.tipo=tipo;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public UbicacionEntity(int id, int ubicacionId, String nombre, TIPO tipo, byte estado) {
        this.id = id;
        this.ubicacionId = ubicacionId;
        this.nombre = nombre;
        this.tipoUbicacion = tipo.toString();
        this.tipo=tipo;
        this.estado = estado;
    }

    public UbicacionEntity(int ubicacionid, String nombre, TIPO tipo) {
        this.ubicacionId = ubicacionid;
        this.nombre = nombre;
        this.tipoUbicacion = tipo.toString();
        this.tipo=tipo;
    }
    
    public UbicacionEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TIPO getTipo() {
        return tipo;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public String getTipoUbicacion() {
        return tipoUbicacion;
    }

    public void setTipoUbicacion(String tipoUbicacion) {
        this.tipoUbicacion = tipoUbicacion;
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

    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return nombre;
    }    
}
