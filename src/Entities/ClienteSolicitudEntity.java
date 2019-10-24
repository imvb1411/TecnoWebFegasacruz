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
public class ClienteSolicitudEntity {
    
    private int id;
    private int detalleRubroId;
    private int solicitud701Id;
    private double valor;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    private Solicitud701Entity solicitud701;
    private DetalleRubroEntity detalleRubro;

    public ClienteSolicitudEntity(int id, int detalleRubroId, int solicitud701Id, double valor, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.detalleRubroId = detalleRubroId;
        this.solicitud701Id = solicitud701Id;
        this.valor = valor;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public ClienteSolicitudEntity(int id, int detalleRubroId, int solicitud701Id, double valor, byte estado) {
        this.id = id;
        this.detalleRubroId = detalleRubroId;
        this.solicitud701Id = solicitud701Id;
        this.valor = valor;
        this.estado = estado;
    }

    public ClienteSolicitudEntity(int detalleRubroId, int solicitud701Id, double valor) {
        this.detalleRubroId = detalleRubroId;
        this.solicitud701Id = solicitud701Id;
        this.valor = valor;
    }

    public ClienteSolicitudEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDetalleRubroId() {
        return detalleRubroId;
    }

    public void setDetalleRubroId(int detalleRubroId) {
        this.detalleRubroId = detalleRubroId;
    }

    public int getSolicitud701Id() {
        return solicitud701Id;
    }

    public void setSolicitud701Id(int solicitud701Id) {
        this.solicitud701Id = solicitud701Id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public Solicitud701Entity getSolicitud701() {
        return solicitud701;
    }

    public void setSolicitud701(Solicitud701Entity solicitud701) {
        this.solicitud701 = solicitud701;
    }

    public DetalleRubroEntity getDetalleRubro() {
        return detalleRubro;
    }

    public void setDetalleRubro(DetalleRubroEntity detalleRubro) {
        this.detalleRubro = detalleRubro;
    }

    @Override
    public String toString() {
        return String.valueOf(valor);
    }
}
