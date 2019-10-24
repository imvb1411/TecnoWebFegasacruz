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
public class PersonalEntity{

    private int id;
    private int personaId;
    private String nick;
    private String password;
    private String tipoPersonal;
    private Timestamp fechaReg;
    private Timestamp fechaMod;
    private byte estado;
    
    private PersonaEntity persona;
    private List<SolicitudEntity> solicitudes;

    public PersonalEntity(int id, int personaId, String nick, String password, String tipoPersonal, Timestamp fechaReg, Timestamp fechaMod, byte estado) {
        this.id = id;
        this.personaId = personaId;
        this.nick = nick;
        this.password = password;
        this.tipoPersonal = tipoPersonal;
        this.fechaReg = fechaReg;
        this.fechaMod = fechaMod;
        this.estado = estado;
    }

    public PersonalEntity(int id, int personaId, String nick, String password, String tipoPersonal, byte estado) {
        this.id = id;
        this.personaId = personaId;
        this.nick = nick;
        this.password = password;
        this.tipoPersonal = tipoPersonal;
        this.estado = estado;
    }
    
    public PersonalEntity(int personaId, String nick, String password, String tipoPersonal) {
        this.personaId = personaId;
        this.nick = nick;
        this.password = password;
        this.tipoPersonal = tipoPersonal;
    }

    public PersonalEntity() {
    }
    
    public int getId() {
        return id;
    }

    public int getPersonaId() {
        return personaId;
    }

    public void setPersonaId(int personaId) {
        this.personaId = personaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipoPersonal() {
        return tipoPersonal;
    }

    public void setTipoPersonal(String tipoPersonal) {
        this.tipoPersonal = tipoPersonal;
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

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    public List<SolicitudEntity> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<SolicitudEntity> solicitudes) {
        this.solicitudes = solicitudes;
    }

    @Override
    public String toString() {
        return tipoPersonal;
    }
    
}
