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
public class PersonaEntity {

    public static enum TIPO {
        Cliente {
            @Override
            public String toString() {
                return "CLI";
            }
        }, Personal {
            @Override
            public String toString() {
                return "PER";
            }
        }
    }
    private int id;
    private String ci;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String email;
    private TIPO tipo;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;

    private List<SolicitudEntity> solicitudes;

    public PersonaEntity(int id, String ci, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String email, TIPO tipo, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public PersonaEntity(String ci, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String email, TIPO tipo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
    }

    public PersonaEntity(String ci, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, String email, TIPO tipo, byte estado) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.email = email;
        this.tipo = tipo;
        this.estado = estado;
    }

    public PersonaEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTipo(TIPO tipo) {
        this.tipo = tipo;
    }

    public String getTipoPersona() {
        if (tipo != null) {
            return tipo.toString();
        } else {
            return "";
        }
    }

    public boolean compareTipoPersona(TIPO tipo) {
        return this.tipo == tipo;
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

    public List<SolicitudEntity> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudEntity> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return apellidoPaterno + " " + apellidoMaterno + " " + nombre;
    }
}
