/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ASUS
 */
public class SolicitudEntity {

    public static enum TIPO {
        Formulario280 {
            @Override
            public String toString() {
                return "280";
            }
        }, Formulario701 {
            @Override
            public String toString() {
                return "701";
            }
        }
    }
    private int id;
    public int actividadId;
    public int clienteId;
    public int registradorId;
    public int tituloId;
    public int ubicacionId;
    public TIPO tipo;
    public int tipoSolicitud;
    public String nroOrden;
    public int gestion;
    public double nroHectareas;
    public Date fechaSolicitud;
    public Date fechaFinalizacion;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    private ActividadEntity actividad;
    private PersonaEntity cliente;
    private PersonaEntity registrador;
    private TituloEntity titulo;
    private UbicacionEntity ubicacion;

    public SolicitudEntity(int id, int actividadId, int clienteId, int registradorId, int tituloId, int ubicacionId, TIPO tipo, String nroOrden, int gestion, double nroHectareas, Date fechaSolicitud, Date fechaFinalizacion, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.actividadId = actividadId;
        this.clienteId = clienteId;
        this.registradorId = registradorId;
        this.tituloId = tituloId;
        this.ubicacionId = ubicacionId;
        this.tipo = tipo;
        this.tipoSolicitud = Integer.valueOf(tipo.toString());
        this.nroOrden = nroOrden;
        this.gestion = gestion;
        this.nroHectareas = nroHectareas;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaFinalizacion = fechaFinalizacion;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public SolicitudEntity(int actividadId, int clienteId, int registradorId, int tituloId, int ubicacionId, TIPO tipo, String nroOrden, int gestion, double nroHectareas, Date fechaSolicitud) {
        this.actividadId = actividadId;
        this.clienteId = clienteId;
        this.registradorId = registradorId;
        this.tituloId = tituloId;
        this.ubicacionId = ubicacionId;
        this.tipo = tipo;
        this.tipoSolicitud = Integer.valueOf(tipo.toString());
        this.nroOrden = nroOrden;
        this.gestion = gestion;
        this.nroHectareas = nroHectareas;
        this.fechaSolicitud = fechaSolicitud;
    }

    public SolicitudEntity(int actividadId, int clienteId, int registradorId, int tituloId, int ubicacionId, TIPO tipo, String nroOrden, int gestion, double nroHectareas, Date fechaSolicitud, Date fechaFinalizacion, byte estado) {
        this.actividadId = actividadId;
        this.clienteId = clienteId;
        this.registradorId = registradorId;
        this.tituloId = tituloId;
        this.ubicacionId = ubicacionId;
        this.tipo = tipo;
        this.tipoSolicitud = Integer.valueOf(tipo.toString());
        this.nroOrden = nroOrden;
        this.gestion = gestion;
        this.nroHectareas = nroHectareas;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaFinalizacion = fechaFinalizacion;
        this.estado = estado;
    }

    public SolicitudEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getActividadId() {
        return actividadId;
    }

    public void setActividadId(int actividadId) {
        this.actividadId = actividadId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getRegistradorId() {
        return registradorId;
    }

    public void setRegistradorId(int registradorId) {
        this.registradorId = registradorId;
    }

    public int getTituloId() {
        return tituloId;
    }

    public void setTituloId(int tituloId) {
        this.tituloId = tituloId;
    }

    public int getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(int ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public int getTipoSolicitud() {
        if (tipo != null) {
            return Integer.valueOf(tipo.toString());
        }
        return -1;
    }

    public boolean compareTipo(TIPO tipo) {
        return this.tipo == tipo;
    }

    public String getNroOrden() {
        return nroOrden;
    }

    public void setNroOrden(String nroOrden) {
        this.nroOrden = nroOrden;
    }

    public int getGestion() {
        return gestion;
    }

    public void setGestion(int gestion) {
        this.gestion = gestion;
    }

    public double getNroHectareas() {
        return nroHectareas;
    }

    public void setNroHectareas(double nroHectareas) {
        this.nroHectareas = nroHectareas;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Date getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(Date fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
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

    public ActividadEntity getActividad() {
        return actividad;
    }

    public void setActividad(ActividadEntity actividad) {
        this.actividad = actividad;
    }

    public PersonaEntity getCliente() {
        return cliente;
    }

    public void setCliente(PersonaEntity cliente) {
        this.cliente = cliente;
    }

    public PersonaEntity getRegistrador() {
        return registrador;
    }

    public void setRegistrador(PersonaEntity registrador) {
        this.registrador = registrador;
    }

    public TituloEntity getTitulo() {
        return titulo;
    }

    public void setTitulo(TituloEntity titulo) {
        this.titulo = titulo;
    }

    public UbicacionEntity getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(UbicacionEntity ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return nroOrden;
    }
}
