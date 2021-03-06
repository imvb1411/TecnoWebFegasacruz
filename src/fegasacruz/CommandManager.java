/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import Entities.*;
import Framework.Status;
import Models.*;
import Utils.AyudaComandos;
import Utils.ValidatorCommand;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mijael
 */
public class CommandManager {

    private Object[] quitarEspacios(Object[] contents) {
        Object[] r = new Object[contents.length];
        for (int i = 0; i < contents.length; i++) {
            if (!contents[i].toString().isEmpty()) {
                r[i] = contents[i].toString().trim();
            } else {
                r[i] = " ";
            }
        }
        return r;
    }

    public enum TipoComando {
        Insercion, Reporte, Estadistica, Desconocido
    }
    private String command;
    private String args;
    private final AyudaComandos ayudaComandos;
    private TipoComando tipoComando;
    private String nombreEstadistica;
    Map<String, Object> result;

    public CommandManager(String command) {
        this.command = command;
        result = new HashMap<>();
        ayudaComandos = new AyudaComandos();
        tipoComando = TipoComando.Insercion;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }

    public TipoComando getTipoComando() {
        return tipoComando;
    }

    public void setTipoComando(TipoComando tipoComando) {
        this.tipoComando = tipoComando;
    }

    public String getNombreEstadistica() {
        return nombreEstadistica;
    }

    public void executeCommand(ValidatorCommand validatorCommand) {
        //Map<String, Object> result = new HashMap<>();
        int idInserted = -1;
        DefaultTableModel table = null;
        List<EstadisticaEntity> list = null;
        int index = command.indexOf("(");
        int index2 = command.indexOf(")");
        if (index < 0 && index2 < 0) {
            result.put("result", idInserted);
            //return result;
        }
        try {
            args = command.substring(index + 1, index2);
            command = command.substring(0, index).trim();
            Object[] contents = args.split(",");
            contents = quitarEspacios(contents);
            switch (command.toUpperCase()) {
                case "AYUDA":
                    table = ayudaComandos.toTable();
                    tipoComando = TipoComando.Reporte;
                    break;
                case "INSERTAR_ACTIVIDAD":
                    ActividadEntity actividadEntity = new ActividadEntity(contents[0].toString(), Integer.valueOf(contents[1].toString()), contents[2].toString());
                    actividadEntity.setEstado(Byte.valueOf("1"));
                    ActividadModel actividadModel = new ActividadModel(actividadEntity);
                    idInserted = actividadModel.insert();
                    break;
                case "INSERTAR_PLANO":
                    PlanoEntity planoEntity = new PlanoEntity(Integer.valueOf(contents[0].toString()), new byte[]{}, contents[1].toString());
                    planoEntity.setEstado(Byte.valueOf("1"));
                    PlanoModel planoModel = new PlanoModel(planoEntity);
                    idInserted = planoModel.insert();
                    break;
                case "INSERTAR_MARCA":
                    MarcaEntity marcaEntity = new MarcaEntity(Integer.valueOf(contents[0].toString()), new byte[]{}, contents[1].toString());
                    marcaEntity.setEstado(Byte.valueOf("1"));
                    MarcaModel marcaModel = new MarcaModel(marcaEntity);
                    idInserted = marcaModel.insert();
                    break;
                case "INSERTAR_TITULO":
                    TituloEntity tituloEntity = new TituloEntity(Integer.valueOf(contents[0].toString()), new byte[]{}, contents[1].toString());
                    tituloEntity.setEstado(Byte.valueOf("1"));
                    TituloModel tituloModel = new TituloModel(tituloEntity);
                    idInserted = tituloModel.insert();
                    break;
                case "INSERTAR_RUBRO":
                    RubroEntity rubroEntity = new RubroEntity(contents[0].toString(), contents[1].toString());
                    rubroEntity.setEstado(Byte.valueOf("1"));
                    RubroModel rubroModel = new RubroModel(rubroEntity);
                    idInserted = rubroModel.insert();
                    break;
                case "INSERTAR_CLIENTE":
                    System.out.println(Arrays.toString(contents));
                    PersonaEntity clienteEntity = new PersonaEntity(contents[0].toString(), contents[1].toString(), contents[2].toString(), contents[3].toString(), contents[4].toString(), contents[5].toString(), PersonaEntity.TIPO.Cliente);
                    clienteEntity.setEstado(Byte.valueOf("1"));
                    PersonaModel clienteModel = new PersonaModel(clienteEntity);
                    idInserted = clienteModel.insert();
                    break;
                case "INSERTAR_PERSONAL":
                    PersonaEntity personalEntity = new PersonaEntity(contents[0].toString(), contents[1].toString(), contents[2].toString(), contents[3].toString(), contents[4].toString(), contents[5].toString(), PersonaEntity.TIPO.Personal);
                    personalEntity.setEstado(Byte.valueOf("1"));
                    PersonaModel personalModel = new PersonaModel(personalEntity);
                    idInserted = personalModel.insert();
                    PersonalEntity personalEntity1 = new PersonalEntity(idInserted, contents[6].toString(), contents[7].toString(), "Secretaria/o");
                    PersonalModel personalModel1 = new PersonalModel(personalEntity1);
                    idInserted = personalModel1.insert();
                    break;
                case "INSERTAR_UBICACION":
                    try {
                        UbicacionEntity ubicacionEntity = new UbicacionEntity(Integer.valueOf(contents[0].toString()), contents[1].toString(), UbicacionEntity.TIPO.valueOf(contents[2].toString().toUpperCase()));
                        ubicacionEntity.setEstado(Byte.valueOf("1"));
                        UbicacionModel ubicacionModel = new UbicacionModel(ubicacionEntity);
                        idInserted = ubicacionModel.insert();
                    } catch (Exception e) {
                        validatorCommand.onError();
                    }

                    break;
                case "INSERTAR_SOLICITUD280": {
                    try {
                        SolicitudEntity solicitudEntity = new SolicitudEntity(
                                Integer.valueOf(contents[0].toString()),
                                Integer.valueOf(contents[1].toString()),
                                Integer.valueOf(contents[2].toString()),
                                Integer.valueOf(contents[3].toString()),
                                Integer.valueOf(contents[4].toString()),
                                SolicitudEntity.TIPO.Formulario280,
                                contents[5].toString(),
                                Integer.valueOf(contents[6].toString()),
                                Double.valueOf(contents[7].toString()),
                                new SimpleDateFormat("yyyy-MM-dd").parse(contents[8].toString()));
                        solicitudEntity.setEstado(Byte.valueOf("1"));
                        SolicitudModel solicitudModel = new SolicitudModel(solicitudEntity);
                        idInserted = solicitudModel.insert();
                        Solicitud280Entity solicitud280Entity = new Solicitud280Entity(idInserted, contents[9].toString(), contents[10].toString(), contents[11].toString());
                        solicitud280Entity.setEstado(Byte.valueOf("1"));
                        Solicitud280Model solicitud280Model = new Solicitud280Model(solicitud280Entity);
                        idInserted = solicitud280Model.insert();
                    } catch (Exception e) {
                        System.out.println("CommandManager.INSERTAR_SOLICITUD280: " + e.getMessage());
                        validatorCommand.onError();
                    }
                }
                break;
                case "INSERTAR_SOLICITUD701":
                    SolicitudEntity solicitudEntity = new SolicitudEntity(
                            Integer.valueOf(contents[0].toString()),
                            Integer.valueOf(contents[1].toString()),
                            Integer.valueOf(contents[2].toString()),
                            Integer.valueOf(contents[3].toString()),
                            Integer.valueOf(contents[4].toString()),
                            SolicitudEntity.TIPO.Formulario701,
                            contents[5].toString(),
                            Integer.valueOf(contents[6].toString()),
                            Double.valueOf(contents[7].toString()),
                            new SimpleDateFormat("yyyy-MM-dd").parse(contents[8].toString()));
                    solicitudEntity.setEstado(Byte.valueOf("1"));
                    SolicitudModel solicitudModel = new SolicitudModel(solicitudEntity);
                    idInserted = solicitudModel.insert();
                    Solicitud701Entity solicitud701Entity = new Solicitud701Entity(
                            idInserted,
                            Integer.valueOf(contents[9].toString()),
                            Integer.valueOf(contents[10].toString()),
                            contents[11].toString());
                    solicitud701Entity.setEstado(Byte.valueOf("1"));
                    Solicitud701Model solicitud701Model = new Solicitud701Model(solicitud701Entity);
                    idInserted = solicitud701Model.insert();
                    break;
                case "INSERTAR_DETALLERUBRO":
                    DetalleRubroEntity detalleRubroEntity = new DetalleRubroEntity(
                            Integer.valueOf(contents[0].toString()),
                            Integer.valueOf(contents[1].toString()),
                            contents[2].toString());
                    DetalleRubroModel detalleRubroModel = new DetalleRubroModel(detalleRubroEntity);
                    idInserted = detalleRubroModel.insert();
                    break;
                case "INSERTAR_CLIENTESOLICITUD701":
                    ClienteSolicitudEntity clienteSolicitudEntity = new ClienteSolicitudEntity(Integer.valueOf(contents[0].toString()), Integer.valueOf(contents[1].toString()), Double.valueOf(contents[2].toString()));
                    ClienteSolicitudModel clienteSolicitudModel = new ClienteSolicitudModel(clienteSolicitudEntity);
                    idInserted = clienteSolicitudModel.insert();
                    break;
////-----------------------------------------------------REPORTES--------------------------------------------------------------------
                case "RPT_ACTIVIDADES":
                    List<ActividadEntity> actividades = new ActividadModel().findAll(Status.Active);
                    table = new ActividadModel().toTable(actividades);
                    tipoComando = TipoComando.Reporte;
                    break;
                case "RPT_RUBROS":
                    List<RubroEntity> rubros = new RubroModel().findAll(Status.Active);
                    table = new RubroModel().toTable(rubros);
                    tipoComando = TipoComando.Reporte;
                    break;
                case "RPT_CLIENTES":
                    List<PersonaEntity> clientes = new PersonaModel().findByTipo(PersonaEntity.TIPO.Cliente);
                    table = new PersonaModel().toTable(clientes);
                    tipoComando = TipoComando.Reporte;
                    break;
                case "RPT_PERSONALES":
                    List<PersonaEntity> personales = new PersonaModel().findByTipo(PersonaEntity.TIPO.Personal);
                    table = new PersonaModel().toTable(personales);
                    tipoComando = TipoComando.Reporte;
                    break;
                case "RPT_SOLICITUDES":
                    List<SolicitudEntity> solicitudes = new SolicitudModel().findAll(Status.Active);
                    table = new SolicitudModel().toTable(solicitudes);
                    tipoComando = TipoComando.Reporte;
                    break;
//-------------------------------------------------------ESTADISTICAS-----------------------------------------------------------------
                case "EST_SOLICITUDES_FECHAS":
                    list = new SolicitudModel().findStadisticsByDates(contents[0].toString(), contents[1].toString());
                    tipoComando = TipoComando.Estadistica;
                    nombreEstadistica = "ESTADISTICAS DE SOLICITUDES";
                    break;
                case "EST_CLIENTES_TOP":
                    list=new SolicitudModel().findStadisticsClientsTop();
                    tipoComando=TipoComando.Estadistica;
                    nombreEstadistica="ESTADISTICAS DE RANKING DE CLIENTES";
                    break;
                default:
                    tipoComando = TipoComando.Desconocido;
                    break;
            }
            switch (tipoComando) {
                case Insercion:
                    if (idInserted > 0) {
                        result.put("result", idInserted);
                        validatorCommand.onSuccess();
                    } else {
                        validatorCommand.onError();
                    }
                    break;
                case Reporte:
                    result.put("result", table);
                    validatorCommand.onSuccess();
                    break;
                case Estadistica:
                    result.put("result", list);
                    validatorCommand.onSuccess();
                    break;
                case Desconocido:
                    validatorCommand.onError();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            validatorCommand.onError();
        }
        //result.put("result", idInserted);
        //return result;
    }
}
