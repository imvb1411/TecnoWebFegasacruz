/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fegasacruz;

import Entities.*;
import Models.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Mijael
 */
public class Commands {

    private String command;
    private String args;

    public Commands(String command) {
        this.command = command;

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

    public Map<String, Object> executeCommand() {
        Map<String, Object> result = new HashMap<>();
        int idInserted = -1;
        int index = command.indexOf("(");
        int index2 = command.indexOf(")");
        if (index < 0 && index2 < 0) {
            result.put("result", idInserted);
            return result;
        }
        args = command.substring(index + 1, index2).trim();
        command = command.substring(0, index).trim();
        Object[] contents = args.split(",");
        switch (command.toUpperCase()) {
            case "INSERTAR_ACTIVIDAD":
                try {
                    ActividadEntity actividadEntity = new ActividadEntity(contents[0].toString(), Integer.valueOf(contents[1].toString()), contents[2].toString());
                    actividadEntity.setEstado(Byte.valueOf("1"));
                    ActividadModel actividadModel = new ActividadModel(actividadEntity);
                    idInserted = actividadModel.insert();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "INSERTAR_PLANO":
                try {
                    PlanoEntity planoEntity = new PlanoEntity(contents[0].toString().getBytes(), contents[1].toString());
                    planoEntity.setEstado(Byte.valueOf("1"));
                    PlanoModel planoModel = new PlanoModel(planoEntity);
                    idInserted = planoModel.insert();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "INSERTAR_MARCA":
                try {
                    MarcaEntity marcaEntity = new MarcaEntity(contents[0].toString().getBytes(), contents[1].toString());
                    marcaEntity.setEstado(Byte.valueOf("1"));
                    MarcaModel marcaModel = new MarcaModel(marcaEntity);
                    idInserted = marcaModel.insert();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "INSERTAR_TITULO":
                try {
                    TituloEntity tituloEntity = new TituloEntity(contents[0].toString().getBytes(), contents[1].toString());
                    tituloEntity.setEstado(Byte.valueOf("1"));
                    TituloModel tituloModel = new TituloModel(tituloEntity);
                    idInserted = tituloModel.insert();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            case "INSERTAR_PERSONA":
                PersonaEntity personaEntity=new PersonaEntity(contents[0].toString(), contents[1].toString(),contents[2].toString(),contents[3].toString(), contents[4].toString(), Integer.valueOf(contents[5].toString()));
                personaEntity.setEstado(Byte.valueOf("1"));
                PersonaModel personaModel = new PersonaModel(personaEntity);                
                idInserted = personaModel.insert();
                break;
            case "INSERT_PET":
                UbicacionEntity ubicacionEntity=new UbicacionEntity(contents[0].toString(), Integer.valueOf(contents[1].toString()),Integer.valueOf(contents[2].toString()));
                ubicacionEntity.setEstado(Byte.valueOf("1"));
                UbicacionModel ubicacionModel = new UbicacionModel(ubicacionEntity);                
                idInserted = ubicacionModel.insert();
                break;
//            case "INSERT_SURGERY":
//                SurgeriesModel modelSurgery = new SurgeriesModel();
//                modelSurgery.setInsertModel(
//                        Integer.valueOf(contents[0].toString()),
//                        contents[1].toString(),
//                        new BigDecimal(contents[2].toString()));
//                idInserted = modelSurgery.insert();
//                break;
//            case "INSERT_VACCINATION":
//                VaccinationsModel modelVaccination = new VaccinationsModel();
//                modelVaccination.setInsertModel(Integer.valueOf(contents[0].toString()), contents[1].toString(), new BigDecimal(contents[2].toString()));
//                idInserted = modelVaccination.insert();
//                break;
//            case "INSERT_PLANVACCINATION":
//                VaccinationsPlanModel modelVaccinationPlan = new VaccinationsPlanModel();
//                modelVaccinationPlan.setInsertModel(
//                        Integer.valueOf(contents[0].toString()),
//                        Integer.valueOf(contents[1].toString()),
//                        Integer.valueOf(contents[2].toString()));
//                idInserted = modelVaccinationPlan.insert();
//                break;
//            case "INSERT_MEDICALHISTORY":
//                MedicalHistoryModel modelHistory = new MedicalHistoryModel();
//                modelHistory.setInsertModel(
//                        Integer.valueOf(contents[0].toString()));
//                idInserted = modelHistory.insert();
//                break;
//            case "INSERT_CONSULTATION":
//                ConsultationsModel modelConsultation = new ConsultationsModel();
//                modelConsultation.setInsertModel(
//                        Integer.valueOf(contents[0].toString()),
//                        Integer.valueOf(contents[1].toString()),
//                        1,
//                        new BigDecimal(contents[2].toString()),
//                        new BigDecimal(contents[3].toString()),
//                        contents[4].toString(),
//                        contents[5].toString()
//                );
//                idInserted = modelConsultation.insert();
//                break;
//            case "INSERT_SURGERYHISTORY":
//                HistorySurgeryModel modelHistorySurgery = new HistorySurgeryModel();
//                modelHistorySurgery.setInsertModel(Integer.valueOf(contents[0].toString()), Integer.valueOf(contents[1].toString()), new BigDecimal(contents[2].toString()));
//                idInserted = modelHistorySurgery.insert();
//                break;
//            case "INSERT_VACCINATIONHISTORY":
//                HistoryVaccinationModel modelHistoryVaccination = new HistoryVaccinationModel();
//                modelHistoryVaccination.setInsertModel(Integer.valueOf(contents[0].toString()), Integer.valueOf(contents[1].toString()), new BigDecimal(contents[2].toString()));
//                idInserted = modelHistoryVaccination.insert();
//                break;
//            case "INSERT_PRODUCTUNIT":
//                ProductUnitModel modelProductUnit = new ProductUnitModel();
//                modelProductUnit.setInsertModel(Integer.valueOf(contents[0].toString()), contents[1].toString(), contents[2].toString());
//                idInserted = modelProductUnit.insert();
//                break;
//            case "INSERT_PRODUCTCATEGORY":
//                ProductCategoryModel modelProductCategory = new ProductCategoryModel();
//                modelProductCategory.setInsertModel(Integer.valueOf(contents[0].toString()), contents[1].toString());
//                idInserted = modelProductCategory.insert();
//                break;
//            case "INSERT_PRODUCT":
//                ProductModel modelProduct = new ProductModel();
//                modelProduct.setInsertModel(
//                        Integer.valueOf(contents[0].toString()),
//                        Integer.valueOf(contents[1].toString()),
//                        Integer.valueOf(contents[2].toString()),
//                        contents[3].toString(),
//                        new BigDecimal(contents[4].toString()),
//                        new BigDecimal(contents[5].toString()),
//                        new BigDecimal(contents[6].toString()));
//                idInserted = modelProduct.insert();
//                break;
//            case "INSERT_PURCHASE":
//                PurchaseModel modelEntry = new PurchaseModel(
//                        contents[0].toString(),
//                        Double.valueOf(contents[1].toString())
//                );
//                idInserted = modelEntry.insert();
//                break;
//            case "INSERT_PRODUCTPURCHASE":
//                ProductPurchaseModel productPurchaseModel = new ProductPurchaseModel(
//                        Integer.valueOf(contents[0].toString()),
//                        Integer.valueOf(contents[1].toString()),
//                        Integer.valueOf(contents[2].toString()),
//                        Double.valueOf(contents[3].toString())
//                );
//                idInserted = productPurchaseModel.insert();
//                break;
//            case "INSERT_CONSULTATIONPRODUCT":
//                ConsultationProductModel modelConsultationProduct = new ConsultationProductModel();
//                modelConsultationProduct.setInsertModel(
//                        Integer.valueOf(contents[0].toString()),
//                        Integer.valueOf(contents[1].toString()),
//                        new BigDecimal(contents[2].toString()),
//                        contents[3].toString());
//                idInserted = modelConsultationProduct.insert();
//                break;
////-----------------------------------------------------REPORTES--------------------------------------------------------------------
/*            case "RPT_SOCIOS":
                List<PersonaModel> listPersonal = new PersonaModel().findByTipoPersona(2);
                result.put("result", listPersonal);
                return result;
                
            case "RPT_PERSONAL":
                List<PersonaModel> listPersonal = new PersonaModel().findByTipoPersona(1);
                result.put("result", listPersonal);
                return result;
            case "RPT_ACTIVIDADES":
                List<ActividadModel> listActividades = new ActividadModel().findById(null);
                result.put("result", listActividades);
                return result;*/
//            case "RPT_SPECIE":
//                List<SpeciesModel> listSpecies = new SpeciesModel().findById(null);
//                result.put("result", listSpecies);
//                return result;
//            case "RPT_RACE":
//                List<RacesModel> listRaces = new RacesModel().findById(null);
//                result.put("result", listRaces);
//                return result;
//            case "RPT_PET":
//                List<PetsModel> listPets = new PetsModel().findById(null);
//                result.put("result", listPets);
//                return result;
//            case "RPT_SURGERY":
//                List<SurgeriesModel> listSurgeries = new SurgeriesModel().findById(null);
//                result.put("result", listSurgeries);
//                return result;
//            case "RPT_VACCINATION":
//                List<VaccinationsModel> listVaccinations = new VaccinationsModel().findById(null);
//                result.put("result", listVaccinations);
//                return result;
//            case "RPT_PLANVACCINATION":
//                List<VaccinationsPlanModel> listPlans = new VaccinationsPlanModel().findById(null);
//                result.put("result", listPlans);
//                return result;
//            case "RPT_MEDICALHISTORY":
//                List<MedicalHistoryModel> listHistories = new MedicalHistoryModel().findById(null);
//                result.put("result", listHistories);
//                return result;
//            case "RPT_CONSULTATION":
//                List<ConsultationsModel> listConsultations = new ConsultationsModel().findById(null);
//                result.put("result", listConsultations);
//                return result;
//            case "RPT_SURGERYHISTORY":
//                List<HistorySurgeryModel> listHSurgeries = new HistorySurgeryModel().findById(null);
//                result.put("result", listHSurgeries);
//                return result;
//            case "RPT_VACCINATIONHISTORY":
//                List<HistoryVaccinationModel> listHVaccinations = new HistoryVaccinationModel().findById(null);
//                result.put("result", listHVaccinations);
//                return result;
//            case "RPT_UNITS":
//                List<ProductUnitModel> listUnits = new ProductUnitModel().findById(null);
//                result.put("result", listUnits);
//                return result;
//            case "RPT_CATEGORIES":
//                List<ProductCategoryModel> listCategories = new ProductCategoryModel().findById(null);
//                result.put("result", listCategories);
//                return result;
//            case "RPT_PRODUCTS":
//                List<ProductModel> listProducts = new ProductModel().findById(null);
//                result.put("result", listProducts);
//                return result;
//            case "RPT_PURCHASES":
//                List<PurchaseModel> listEntries = new PurchaseModel().findById(null);
//                result.put("result", listEntries);
//                return result;
//            case "RPT_PRODUCTPURCHASES":
//                List<ProductPurchaseModel> listPPurchases = new ProductPurchaseModel().findById(null);
//                result.put("result", listPPurchases);
//                return result;
//            case "RPT_CONSULTATIONPRODUCT":
//                List<ConsultationProductModel> listCProducts = new ConsultationProductModel().findById(null);
//                result.put("result", listCProducts);
//                return result;
//            case "HELP":
//                result.put("result", Variables.HELP);
//                return result;                
//            default:
//                System.out.println("NO SE ENCUENTRA EL COMANDO: " + command + "VERIFIQUE POR FAVOR");
//                break;
        }
        result.put("result", idInserted);
        return result;
    }
}
