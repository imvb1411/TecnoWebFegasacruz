/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Mijael
 */
public class AyudaEntity {
    private String operacion;
    private String parametros;
    private String descripcion;
    private String comando;

    public AyudaEntity(String operacion,String parametros, String descripcion, String comando) {
        this.operacion = operacion;
        this.parametros=parametros;
        this.descripcion = descripcion;
        this.comando = comando;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getParametros() {
        return parametros;
    }

    public void setParametros(String parametros) {
        this.parametros = parametros;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    
    
}
