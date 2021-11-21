/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoadingData.java
 *
 * Created on 19 ก.พ. 2553, 11:44:27
 */

package xbuild;

import Library.*;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Jazzy
 */
public class LoadingDataFrame extends javax.swing.JFrame implements PropertyChangeListener {

    /** Creates new form LoadingData */
    private Task task;
    protected String dir;
    protected String version;
    private boolean isValid = true;

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            //int progress = 0;
            //Initialize progress property.
            setProgress(0);

            // Declare variable
            String strRead;

            boolean bCrop = false;
            boolean bChem = false;
            boolean bDrain = false;
            boolean bSoilTexture = false;
            boolean bSoilAnalysis = false;
            boolean bPlantingMethod = false;
            boolean bPlantDistribution = false;
            boolean bIrrigation = false;
            boolean bFertilizer = false;
            boolean bFertilizerMethod = false;
            boolean bEnvironment = false;
            boolean bResidues = false;
            boolean bTillage = false;
            boolean bHarvestComp = false;
            boolean bHarvestSize = false;
            boolean bFieldHistory = false;

            boolean bCropModel = false;

            boolean bGStage = false;

            ArrayList<String> dssatList = new ArrayList<String>() {};

            ArrayList<String> cropList = new ArrayList<String>() {};
            ArrayList<String> chemList = new ArrayList<String>() {};
            ArrayList<String> drainList = new ArrayList<String>() {};
            ArrayList<String> soilTextureList = new ArrayList<String>() {};
            ArrayList<String> soilAnalysis = new ArrayList<String>() {};
            ArrayList<String> plantingMethod = new ArrayList<String>() {};
            ArrayList<String> plantDistribution = new ArrayList<String>() {};
            ArrayList<String> irrigationMethod = new ArrayList<String>() {};
            ArrayList<String> fertilizerList = new ArrayList<String>() {};
            ArrayList<String> fertilizerMethodList = new ArrayList<String>() {};
            ArrayList<String> environmentList = new ArrayList<String>() {};
            ArrayList<String> residuesList = new ArrayList<String>() {};
            ArrayList<String> tillageList = new ArrayList<String>() {};
            ArrayList<String> harvestCompList = new ArrayList<String>() {};
            ArrayList<String> harvestSizeList = new ArrayList<String>() {};
            ArrayList<String> fieldHistoryList = new ArrayList<String>() {};
            ArrayList<String> weatherList = new ArrayList<String>() {};

            ArrayList<String> cropModel = new ArrayList<String>() {};

            ArrayList<String> gStage = new ArrayList<String>() {};

            // Set File to Read
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.US);
            
           
            
            FileReader file = null;
            System.out.println("Start Read File DSSATPRO." + version + " : " + df.format(new Date()));
            BufferedReader br = null;

            // <editor-fold defaultstate="collapsed" desc="DSSATPRO.v45">
            try {
                file = new FileReader(dir + "\\DSSATPRO." + version);
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            br = new BufferedReader(file);
            try {
                while ((strRead = br.readLine()) != null) {
                    if (!strRead.isEmpty() && !strRead.startsWith("*") && !strRead.startsWith("!")) {
                        dssatList.add(strRead);
                    }
                }
            } catch (Exception ex) {
                taskOutput.append("!DSSATPro loading error: " + ex.getMessage() + " \n");
                isValid = false;
            }
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
            }// </editor-fold>

            System.out.println("Start Read File Detail.cde : " + df.format(new Date()));
            try {
                file = new FileReader(dir + "\\DETAIL.CDE");
            } catch (FileNotFoundException ex) { 
                System.out.println(ex); 
                taskOutput.append("!Detail.cde not found \n");
                isValid = false;
            }
            br = new BufferedReader(file);

            // Start read
            try {
                while ((strRead = br.readLine()) != null) {
                    // <editor-fold defaultstate="collapsed" desc="Crop and Weed Species">
                    if (strRead.trim().equals("*Crop and Weed Species")) {
                        bCrop = true;
                    } else if (bCrop) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                cropList.add(s);
                            }
                        }
                        else {
                            bCrop = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Chemicals (Herbicides, Insecticides, Fungicides, etc.)">
                    else if (strRead.trim().equals("*Chemicals (Herbicides, Insecticides, Fungicides, etc.)")) {
                        bChem = true;
                    } else if (bChem) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                chemList.add(s);
                            }
                        }
                        else {
                            bChem = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Drainage">
                    else if (strRead.trim().equals("*Drainage")) {
                        bDrain = true;
                    } else if (bDrain) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                drainList.add(s);
                            }
                        }
                        else {
                            bDrain = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Soil Texture">
                    else if (strRead.trim().equals("*Soil Texture")) {
                        bSoilTexture = true;
                    } else if (bSoilTexture) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                soilTextureList.add(s);
                            }
                        }
                        else {
                            bSoilTexture = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Methods - Soil Analysis">
                    else if (strRead.trim().equals("*Methods - Soil Analysis")) {
                        bSoilAnalysis = true;
                    } else if (bSoilAnalysis) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                soilAnalysis.add(s);
                            }
                        }
                        else {
                            bSoilAnalysis = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Planting Material/Method">
                    else if (strRead.trim().equals("*Planting Material/Method")) {
                        bPlantingMethod = true;
                    } else if (bPlantingMethod) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                plantingMethod.add(s);
                            }
                        }
                        else {
                            bPlantingMethod = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Plant Distribution">
                    else if (strRead.trim().equals("*Plant Distribution")) {
                        bPlantDistribution = true;
                    } else if (bPlantDistribution) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                plantDistribution.add(s);
                            }
                        }
                        else {
                            bPlantDistribution = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Methods - Irrigation and Water Management (Units for associated data)">
                    else if (strRead.trim().equals("*Methods - Irrigation and Water Management (Units for associated data)")) {
                        bIrrigation = true;
                    } else if (bIrrigation) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                irrigationMethod.add(s);
                            }
                        }
                        else {
                            bIrrigation = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Fertilizers, Inoculants and Amendments">
                    else if (strRead.trim().equals("*Fertilizers, Inoculants and Amendments")) {
                        bFertilizer = true;
                    } else if (bFertilizer) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                fertilizerList.add(s);
                            }
                        }
                        else {
                            bFertilizer = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Methods - Fertilizer and Chemical Applications">
                    else if (strRead.trim().equals("*Methods - Fertilizer and Chemical Applications")) {
                        bFertilizerMethod = true;
                    } else if (bFertilizerMethod) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                fertilizerMethodList.add(s);
                            }
                        }
                        else {
                            bFertilizerMethod = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Environment Modification Factors">
                    else if (strRead.trim().equals("*Environment Modification Factors")) {
                        bEnvironment = true;
                    } else if (bEnvironment) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                environmentList.add(s);
                            }
                        }
                        else {
                            bEnvironment = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Tillage Implements">
                    else if (strRead.trim().equals("*Tillage Implements")) {
                        bTillage = true;
                    } else if (bTillage) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                tillageList.add(s);
                            }
                        }
                        else {
                            bTillage = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Residues and Organic Fertilizer">
                    else if (strRead.trim().equals("*Residues and Organic Fertilizer")) {
                        bResidues = true;
                    } else if (bResidues) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                residuesList.add(s);
                            }
                        }
                        else {
                            bResidues = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Harvest components">
                    else if (strRead.trim().equals("*Harvest components")) {
                        bHarvestComp = true;
                    } else if (bHarvestComp) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                harvestCompList.add(s);
                            }
                        }
                        else {
                            bHarvestComp = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Harvest size categories">
                    else if (strRead.trim().equals("*Harvest size categories")) {
                        bHarvestSize = true;
                    } else if (bHarvestSize) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                harvestSizeList.add(s);
                            }
                        }
                        else {
                            bHarvestSize = false;
                        }
                    }
                    //</editor-fold>

                    // <editor-fold defaultstate="collapsed" desc="Field History">
                    else if (strRead.trim().equals("*Field History")) {
                        bFieldHistory = true;
                    } else if (bFieldHistory) {
                        if(strRead.length() > 0)
                        {
                            if (!strRead.substring(0, 1).equals("@") && !strRead.substring(0, 1).equals("!")) {
                                String s = strRead;
                                fieldHistoryList.add(s);
                            }
                        }
                        else {
                            bFieldHistory = false;
                        }
                    }
                    //</editor-fold>
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                taskOutput.append("!Error" + ex.getMessage() + " \n");
                isValid = false;
            }

            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                file.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            file = null;
// taskOutput.append(dir + "\\DSSATPRO.v48\n");
            // <editor-fold defaultstate="collapsed" desc="Add DSSAT Profile">
            taskOutput.append("Loading DSSAT profile....\n");
            for (int i = 0; i < dssatList.size(); i++) {
                String tmp = dssatList.get(i);
                String code = tmp.substring(0, 3).trim();
                String description = tmp.substring(3, tmp.length()).trim();
                description = description.replace(" ", "");
                DssatProfile.AddNew(code, description);
            }
            // </editor-fold>
                      
            System.out.println("Start Read Simulation Code : " + df.format(new Date()));
            //<editor-fold defaultstate="collapsed" desc="Read Simulatin Code">
            try {
                file = new FileReader(dir + "\\Simulation.cde");
            } catch (FileNotFoundException ex) { 
                taskOutput.append("!" + dir + "\\Simulation.cde not found \n");
                isValid = false;
            }
            br = new BufferedReader(file);
            // Start read
            try {
                Boolean bSimStart = false;
                Boolean bSimOptionWater = false;
                Boolean bSimOptionSymbiosis = false;
                Boolean bSimOptionCO2 = false;
                Boolean bSimMethodWeather = false;
                Boolean bSimMethodEvap = false;
                Boolean bSimMethodInitial = false;
                Boolean bSimMethodInfil = false;
                Boolean bSimMethodPhoto = false;
                Boolean bSimMethodHydrology = false;
                Boolean bSimMethodSOM = false;
                Boolean bSimMethodSoilEvap = false;
                Boolean bSimMethodSoilLayer = false;
                Boolean bSimManagePlanting = false;
                Boolean bSimManageIrrigation = false;
                Boolean bSimManageFertilizer = false;
                Boolean bSimManageResidue = false;
                Boolean bSimManageHarvest = false;
                Boolean bSimOutput = false;
                Boolean bSimOutputOption = false;
                Boolean bSimOutputVerbose = false;
                
                
                while ((strRead = br.readLine()) != null) {
                    // <editor-fold defaultstate="collapsed" desc="Simulation Start">
                    if (strRead.trim().startsWith("*Start Simulation")) {
                        bSimStart = true;
                    } else if (bSimStart) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationStart.AddNew(Code, Description);
                            }
                        } else {
                            bSimStart = false;
                            
                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Options/Water">
                    /*
                    else if (strRead.trim().startsWith("*Simulation/Options/Water")) {
                        bSimOptionWater = true;
                    } else if (bSimOptionWater) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOptionWater.AddNew(Code, Description);
                            }
                        } else {
                            bSimOptionWater = false;
                            
                        }
                    }*/
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Options/Symbiosis">
                    else if (strRead.trim().startsWith("*Simulation/Options/Symbiosis")) {
                        bSimOptionSymbiosis = true;
                    } else if (bSimOptionSymbiosis) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOptionSymbiosis.AddNew(Code, Description);
                            }
                        } else {
                            bSimOptionSymbiosis = false;
                            
                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Options/CO2">
                    /*
                    else if (strRead.trim().startsWith("*Simulation/Options/CO2")) {
                        bSimOptionCO2 = true;
                    } else if (bSimOptionCO2) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOptionCO2.AddNew(Code, Description);
                            }
                        } else {
                            bSimOptionCO2 = false;
                            
                        }
                    }*/
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Weather">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Weather")) {
                        bSimMethodWeather = true;
                    } else if (bSimMethodWeather) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodWeather.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodWeather = false;
                            
                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Initial Soil Conditions">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Initial Soil Conditions")) {
                        bSimMethodInitial = true;
                    } else if (bSimMethodInitial) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodInitial.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodInitial = false;
                            
                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Evapotransportation">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Evapotransportation")) {
                        bSimMethodEvap = true;
                    } else if (bSimMethodEvap) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodEvap.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodEvap = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Infiltration">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Infiltration")) {
                        bSimMethodInfil = true;
                    } else if (bSimMethodInfil) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodInfil.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodInfil = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Photosynthesis">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Photosynthesis")) {
                        bSimMethodPhoto = true;
                    } else if (bSimMethodPhoto) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodPhoto.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodPhoto = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Hydrology">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Hydrology")) {
                        bSimMethodHydrology = true;
                    } else if (bSimMethodHydrology) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodHydrology.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodHydrology = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/SOM">
                    else if (strRead.trim().startsWith("*Simulation/Methods/SOM")) {
                        bSimMethodSOM = true;
                    } else if (bSimMethodSOM) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodSOM.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodSOM = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Soil Evaporation">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Soil Evaporation")) {
                        bSimMethodSoilEvap = true;
                    } else if (bSimMethodSoilEvap) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodSoilEvap.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodSoilEvap = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Methods/Soil Layer Distribution">
                    else if (strRead.trim().startsWith("*Simulation/Methods/Soil Layer Distribution")) {
                        bSimMethodSoilLayer = true;
                    } else if (bSimMethodSoilLayer) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationMethodSoilLayer.AddNew(Code, Description);
                            }
                        } else {
                            bSimMethodSoilLayer = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Management/Planting">
                    else if (strRead.trim().startsWith("*Simulation/Management/Planting")) {
                        bSimManagePlanting = true;
                    } else if (bSimManagePlanting) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationManagePlanting.AddNew(Code, Description);
                            }
                        } else {
                            bSimManagePlanting = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Management/Irrigation">
                    else if (strRead.trim().startsWith("*Simulation/Management/Irrigation")) {
                        bSimManageIrrigation = true;
                    } else if (bSimManageIrrigation) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationManagerIrrigation.AddNew(Code, Description);
                            }
                        } else {
                            bSimManageIrrigation = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Management/Fertilization">
                    else if (strRead.trim().startsWith("*Simulation/Management/Fertilization")) {
                        bSimManageFertilizer = true;
                    } else if (bSimManageFertilizer) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationManageFertilzation.AddNew(Code, Description);
                            }
                        } else {
                            bSimManageFertilizer = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Management/Residue">
                    else if (strRead.trim().startsWith("*Simulation/Management/Residue")) {
                        bSimManageResidue = true;
                    } else if (bSimManageResidue) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationManageResidue.AddNew(Code, Description);
                            }
                        } else {
                            bSimManageResidue = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Management/Harvest">
                    else if (strRead.trim().startsWith("*Simulation/Management/Harvest")) {
                        bSimManageHarvest = true;
                    } else if (bSimManageHarvest) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationManageHarvest.AddNew(Code, Description);
                            }
                        } else {
                            bSimManageHarvest = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Outputs">
                    else if (strRead.trim().startsWith("*Simulation/Outputs")) {
                        bSimOutput = true;
                    } else if (bSimOutput) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOutput.AddNew(Code, Description);
                            }
                        } else {
                            bSimOutput = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Outputs/Options">
                    else if (strRead.trim().startsWith("*Simulation/Outputs/Options")) {
                        bSimOutputOption = true;
                    } else if (bSimOutputOption) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOutputOption.AddNew(Code, Description);
                            }
                        } else {
                            bSimOutputOption = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Simulation/Outputs/Verbose">
                    else if (strRead.trim().startsWith("*Simulation/Outputs/Verbose")) {
                        bSimOutputVerbose = true;
                    } else if (bSimOutputVerbose) {
                        if (!strRead.trim().isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String tmp = strRead.trim();
                                String Code = tmp.substring(0, 8).trim();
                                String Description = tmp.substring(9, tmp.length() - 2).trim();
                                SimulationOutputVerbose.AddNew(Code, Description);
                            }
                        } else {
                            bSimOutputVerbose = false;

                        }
                    }
                    // </editor-fold>
                    // <editor-fold defaultstate="collapsed" desc="Crop Models">
                    else if (strRead.trim().startsWith("*Simulation/Crop Models")) {
                        bCropModel = true;
                    } else if (bCropModel) {
                        if (!strRead.isEmpty()) {
                            if (!strRead.trim().startsWith("@") && !strRead.trim().startsWith("!")) {
                                String s = strRead;
                                cropModel.add(s);
                            }
                        } else {
                            bCropModel = false;
                        }
                    }
                    // </editor-fold>
                }
                SimulationOptionWater.AddNew("Y", "Yes");
                SimulationOptionWater.AddNew("N", "No");

                SimulationOptionCO2.AddNew("W", "Read from weather file");
                SimulationOptionCO2.AddNew("M", "Actual CO2; Mauna Loa, Hawaii (Keeling curve)");
                SimulationOptionCO2.AddNew("D", "Use default value (380 vpm)");
            }
            catch(Exception ex) {
                taskOutput.append("!Error:" + ex.getMessage() + "\n");
                isValid = false;
            }

            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                file.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            file = null;
            //</editor-fold>

            System.out.println("Start Read Growth Stage : " + df.format(new Date()));
            //<editor-fold defaultstate="collapsed" desc="Read Growth and Development Codes">
            try {
                file = new FileReader(dir + "\\GRSTAGE.cde");
            } catch (FileNotFoundException ex) {
                taskOutput.append("!" + dir + "\\GRSTAGE.cde not found \n");
                isValid = false;
            }
            br = new BufferedReader(file);
            // Start read
            try {
                String header = "";
                while ((strRead = br.readLine()) != null) {
                    ///////////// Read Growth and Development Codes  ////////////////////////////
                    if (strRead.contains("*Growth and Development Codes")) {
                        header = strRead.replace("*Growth and Development Codes - ","");
                        if(header.contains("(")) header = header.substring(0, header.indexOf("(")).trim();
                        bGStage = true;
                    } else if (bGStage) {
                        if(strRead.trim().length() > 0)
                        {
                            if (!strRead.startsWith("@") && !strRead.startsWith("!")) {
                                String s = strRead + header;
                                gStage.add(s);
                            }
                        }
                        else {
                            bGStage = false;
                        }
                    }
                }
            }
            catch(Exception ex) {
                taskOutput.append("!Error: " + ex.getMessage() + "\n");
                isValid = false;
            }

            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                file.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            file = null;
            //</editor-fold>

            System.out.println("Start Read Soil.LST : " + df.format(new Date()));
            //<editor-fold defaultstate="collapsed" desc="Read Soil.SOL">
            
            File soilFile = new File(dir + "\\Soil\\Soil.lst");
            FileReader soilFileRead = null;
            try {
                soilFileRead = new FileReader(soilFile);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                taskOutput.append("!" + dir + "\\Soil\\Soil.lst not found \n");
                isValid = false;
            }
            if(soilFileRead != null){
                String strSRead = "";
                try {
                    BufferedReader soilBufferReader = new BufferedReader(soilFileRead);
                    boolean isStartAdd = false;
                    while ((strSRead = soilBufferReader.readLine()) != null) {
                        if (!isStartAdd && strSRead.startsWith("@#")) {
                            isStartAdd = true;
                        } else if (isStartAdd) {
                            String soilFileCode = strSRead.substring(5, 13).trim();
                            String soilFileExt = strSRead.substring(14, 17).trim();
                            String soilCode = strSRead.substring(18, 28).trim();
                            String soilDescription = strSRead.substring(44, strSRead.length() - 1).trim();

                            Soil soil = new Soil();
                            soil.Code = soilCode;
                            soil.Description = soilDescription;
                            soil.Unknow1 = soilFileCode;
                            soil.Unknow2 = soilFileExt;

                            SoilList.AddNew(soil);
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    soilFileRead.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //</editor-fold>

            System.out.println("Start Read Weather : " + df.format(new Date()));
            //<editor-fold defaultstate="collapsed" desc="Read Folder Weather">
           
            File w = new File(dir + "\\Weather\\WTH.LST");
            System.out.println("Start Read Weather File : " + w.getName() + " : " + df.format(new Date()) + "\n");
            FileReader fileRead = null;
            try {
                fileRead = new FileReader(w);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                taskOutput.append("!" + dir + "\\Weather\\WTH.LST not found \n");
                isValid = false;
            }
            if(fileRead != null){
                String strWRead = "";
                try {
                    BufferedReader wReader = new BufferedReader(fileRead);
                    boolean isStartAdd = false;
                    while ((strWRead = wReader.readLine()) != null) {
                        if (!isStartAdd && strWRead.startsWith("@#")) {
                            isStartAdd = true;
                        } else if (isStartAdd) {
                            WeatherStation weather = new WeatherStation();
                            weather.StationName = strWRead.substring(18, 45).trim();
                            weather.Code = strWRead.substring(5, 9).trim();
                            if (!WeatherStationList.Exists(weather)) {
                                WeatherStationList.AddNew(weather);
                            }
                        }
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                    taskOutput.append("!Error:" + ex.getMessage() + " \n");
                    isValid = false;
                }

                try {
                    fileRead.close();
                } catch (IOException ex) {
                    Logger.getLogger(LoadingDataFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }          
            
            //</editor-fold>
            System.out.println("Start End Read File : " + df.format(new Date()));

            ////////  Set Max Progress  /////////////////////
            double max = cropList.size() + chemList.size() + drainList.size() + soilTextureList.size() + soilAnalysis.size() + plantingMethod.size() +
                         plantDistribution.size() + irrigationMethod.size() + fertilizerList.size() + fertilizerMethodList.size() + environmentList.size() +
                         residuesList.size() + tillageList.size() + harvestCompList.size() + harvestSizeList.size() + fieldHistoryList.size() +
                         cropModel.size() + gStage.size();
            int progress = 1;

            //<editor-fold defaultstate="collapsed" desc="Loading crop">
            taskOutput.append("Loading crop....\n");
            for(int i = 0;i < cropList.size();i++)
            {
                try {
                    Crop crop = new Crop();
                    String tmp = cropList.get(i);

                    crop.CropCode = tmp.substring(0, 2);
                    crop.CropName = tmp.substring(9, Math.min(tmp.length(), 78)).trim();
                    CropList.AddNew(crop);

                
                    //String culFile = dir + "\\Genotype\\" + CultivarFileList.GetCultivarFile(crop);
                    String culFile = "";
                    File wc = new File(dir + "\\Genotype\\");
                    File culList[] = wc.listFiles(new ExtendFilter(crop.CropCode, ".cul"));

                    for (File ci : culList) {
                        if(culFile.equals("")) culFile = dir + "\\Genotype\\" + ci.getName();
                        //else culFile += " " + dir + "\\Genotype\\" + ci.getName();
                    }
                    if (culFile != null && !"".equals(culFile)) {
                        CultivarList.AddNew(crop, culFile);
                    }
                } catch (Exception e) {
                    taskOutput.append("!Error:" + e.getMessage() + "\n");
                    isValid = false;
                }

                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            /*
            System.out.println("Start Read Cultivars : " + df.format(new Date()));
            // <editor-fold defaultstate="collapsed" desc="Setup Cultivars file">
            try {
                file = new FileReader("Cultivars.fle");
            } catch (FileNotFoundException ex) {
            }
            br = new BufferedReader(file);
            // Start read
            try {
                while ((strRead = br.readLine()) != null) {
                    try {
                        String tmp[] = strRead.split(" ");
                        CultivarFileList.AddNew(tmp[0], tmp[1]);
                    } catch (Exception e) {
                        System.out.println(strRead + " : " + e.getMessage() + "\n");
                    }
                }
            } catch (Exception ex) {
            }

            try {
                br.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            try {
                file.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            file = null;// </editor-fold>
            */
            //<editor-fold defaultstate="collapsed" desc="Loading Growth Stage">
            taskOutput.append("Loading Growth Stage....\n");
            for(int i = 0;i < gStage.size();i++)
            {
                GrowthStage growth = new GrowthStage();
                String tmp = gStage.get(i);

                try
                {
                    growth.Code = tmp.substring(0, 5).trim();
                    growth.Name = tmp.substring(5, 10).trim();
                    growth.Description = tmp.substring(12, 81).trim();
                    Crop crop = CropList.GetAtName(tmp.substring(82,tmp.length()));
                    if(crop != null)
                    {
                        growth.crop = crop;
                        GrowthStageList.AddNew(growth);
                    }
                }
                catch(Exception ex) {}
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading chemical material">
            taskOutput.append("Loading chemical....\n");
            for(int i = 0;i < chemList.size();i++)
            {
                ChemicalMaterial chem = new ChemicalMaterial();
                String tmp = chemList.get(i);

                chem.Code = tmp.substring(0, 5);
                chem.Description = tmp.substring(9, 78).trim();
                ChemicalMaterialList.AddNew(chem);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading drainage">
            taskOutput.append("Loading drainage....\n");
            for(int i = 0;i < drainList.size();i++)
            {
                Drainage drain = new Drainage();
                String tmp = drainList.get(i);

                drain.Code = tmp.substring(0, 5).trim();
                drain.Description = tmp.substring(9, 78).trim();
                DrainageList.AddNew(drain);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading soil texture">
            taskOutput.append("Loading soil texture....\n");
            for(int i = 0;i < soilTextureList.size();i++)
            {
                SoilTexture soil = new SoilTexture();
                String tmp = soilTextureList.get(i);

                soil.Code = tmp.substring(0, 5).trim();
                soil.Description = tmp.substring(9, 78).trim();
                SoilTextureList.AddNew(soil);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Method : Soil Analysis">
            taskOutput.append("Loading Method : Soil Analysis....\n");
            for(int i = 0;i < soilAnalysis.size();i++)
            {
                if(i >= 0 && i < 10)
                {
                    SoilAnalysisMethodPhosphorus phos = new SoilAnalysisMethodPhosphorus();
                    String tmp = soilAnalysis.get(i);

                    phos.Code = tmp.substring(0, 5).trim();
                    phos.Description = tmp.substring(9, 78).trim();
                    //SoilAnalysisMethodPhosphorusList.methods[i] = phos;
                    SoilAnalysisMethodPhosphorusList.AddNew(phos);
                }
                if(i >= 10 && i < 12)
                {
                    SoilAnalysisMethodPh ph = new SoilAnalysisMethodPh();
                    String tmp = soilAnalysis.get(i);

                    ph.Code = tmp.substring(0, 5).trim();
                    ph.Description = tmp.substring(9, 78).trim();
                    //SoilAnalysisMethodPhList.methods[i - 10] = ph;
                    SoilAnalysisMethodPhList.AddNew(ph);
                }
                if(i >= 12)
                {
                    SoilAnalysisMethodPotassium potass = new SoilAnalysisMethodPotassium();
                    String tmp = soilAnalysis.get(i);

                    potass.Code = tmp.substring(0, 5).trim();
                    potass.Description = tmp.substring(9, 78).trim();
                    //SoilAnalysisMethodPotassiumList.methods[i - 12] = potass;
                    SoilAnalysisMethodPotassiumList.AddNew(potass);
                }
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading planting method">
            taskOutput.append("Loading planting method....\n");
            for(int i = 0;i < plantingMethod.size();i++)
            {
                PlantingMethod plant = new PlantingMethod();
                String tmp = plantingMethod.get(i);

                plant.Code = tmp.substring(0, 5).trim();
                plant.Description = tmp.substring(9, 78).trim();
                PlantingMethodList.AddNew(plant);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading plant distribution">
            taskOutput.append("Loading plant distribution....\n");
            for(int i = 0;i < plantDistribution.size();i++)
            {
                PlantDistribution plant = new PlantDistribution();
                String tmp = plantDistribution.get(i);

                plant.Code = tmp.substring(0, 5).trim();
                plant.Description = tmp.substring(9, 78).trim();
                PlantDistributionList.AddNew(plant);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading irrigation and water management">
            taskOutput.append("Loading irrigation and water management....\n");
            for(int i = 0;i < irrigationMethod.size();i++)
            {
                IrrigationMethod irrig = new IrrigationMethod();
                String tmp = irrigationMethod.get(i);

                irrig.Code = tmp.substring(0, 5).trim();
                irrig.Description = tmp.substring(9, 78).trim();
                IrrigationMethodList.AddNew(irrig);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading fertilizers material">
            taskOutput.append("Loading fertilizers material....\n");
            for(int i = 0;i < fertilizerList.size();i++)
            {
                FertilizerMaterial fertil = new FertilizerMaterial();
                String tmp = fertilizerList.get(i);

                fertil.Code = tmp.substring(0, 5).trim();
                fertil.Description = tmp.substring(9, 78).trim();
                FertilizerMaterialList.AddNew(fertil);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading fertilizers method">
            taskOutput.append("Loading fertilizers method....\n");
            for(int i = 0;i < fertilizerMethodList.size();i++)
            {
                FertilizerMethod fertil = new FertilizerMethod();
                String tmp = fertilizerMethodList.get(i);

                fertil.Code = tmp.substring(0, 5).trim();
                fertil.Description = tmp.substring(9, 78).trim();
                FertilizerMethodList.AddNew(fertil);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Environment Modification Factors">
            taskOutput.append("Loading Environment Modification Factors....\n");
            for(int i = 0;i < environmentList.size();i++)
            {
                EnvironmentFactor env = new EnvironmentFactor();
                String tmp = environmentList.get(i);

                env.Code = tmp.substring(0, 5).trim();
                env.Description = tmp.substring(9, 78).trim();
                //EnvironmentFactorList.factors[i] = env;
                EnvironmentFactorList.Add(env);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Residues and Organic Fertilizer">
            taskOutput.append("Loading Residues and Organic Fertilizer....\n");
            for(int i = 0;i < residuesList.size();i++)
            {
                Residues res = new Residues();
                String tmp = residuesList.get(i);

                res.Code = tmp.substring(0, 5).trim();
                res.Description = tmp.substring(9, 78).trim();
                //EnvironmentFactorList.factors[i] = env;
                ResiduesList.AddNew(res);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Tillage Implements">
            taskOutput.append("Loading Tillage Implements....\n");
            for(int i = 0;i < tillageList.size();i++)
            {
                TillageImplement tillage = new TillageImplement();
                String tmp = tillageList.get(i);

                try
                {
                    tillage.Code = tmp.substring(0, 5).trim();
                    tillage.Description = tmp.substring(9, tmp.length()-2).trim();
                    //EnvironmentFactorList.factors[i] = env;
                    TillageImplementList.AddNew(tillage);
                }
                catch(Exception ex) {
                    taskOutput.append("!Error:" + ex.getMessage() + "\n");
                    isValid = false;
                }

                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Harvest Components">
            taskOutput.append("Loading Harvest Components....\n");
            for(int i = 0;i < harvestCompList.size();i++)
            {
                HarvestComponent harvest = new HarvestComponent();
                String tmp = harvestCompList.get(i);

                try
                {
                    harvest.Code = tmp.substring(0, 5).trim();
                    harvest.Description = tmp.substring(9, tmp.length()-2).trim();
                    //EnvironmentFactorList.factors[i] = env;
                    HarvestComponentList.AddNew(harvest);
                }
                catch(Exception ex) {
                    taskOutput.append("!Error:" + ex.getMessage() + "\n");
                    isValid = false;
                }

                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Harvest size categories">
            taskOutput.append("Loading Harvest size categories....\n");
            for(int i = 0;i < harvestSizeList.size();i++)
            {
                HarvestSize harvest = new HarvestSize();
                String tmp = harvestSizeList.get(i);

                try
                {
                    harvest.Code = tmp.substring(0, 5).trim();
                    harvest.Description = tmp.substring(9, tmp.length()-2).trim();
                    HarvestSizeList.AddNew(harvest);
                }
                catch(Exception ex) {
                    taskOutput.append("!Error:" + ex.getMessage() + "\n");
                    isValid = false;
                }

                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading Field History">
            taskOutput.append("Loading Field History....\n");
            for(int i = 0;i < fieldHistoryList.size();i++)
            {
                FieldHistory field = new FieldHistory();
                String tmp = fieldHistoryList.get(i);

                try
                {
                    field.Code = tmp.substring(0, 5).trim();
                    field.Description = tmp.substring(9, tmp.length()-2).trim();
                    FieldHistoryList.AddNew(field);
                }
                catch(Exception ex) {
                    taskOutput.append("!Error:" + ex.getMessage() + "\n");
                    isValid = false;
                }

                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                    try {
                        Thread.sleep(0);
                    } catch (InterruptedException ex) { }
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Loading crop model">
            taskOutput.append("Loading crop model....\n");
            for(int i = 0;i < cropModel.size();i++)
            {
                CropModel cModel = new CropModel();
                String tmp = cropModel.get(i);

                cModel.ModelCode = tmp.substring(0, 6).trim();
                cModel.Code = tmp.substring(8, 11).trim();
                try
                {
                    cModel.Description = tmp.substring(14, tmp.length() - 1).trim();
                }
                catch(Exception ex)
                {
                    cModel.Description = "";
                }
                CropModelList.AddNew(cModel);
                double prog = ((progress++) / max) * 100.00;
                setProgress((int) prog);
                try {
                    Thread.sleep(0);
                } catch (InterruptedException ex) { }
            }
            //</editor-fold>            

            //<editor-fold defaultstate="collapsed" desc="Loading Weather Station">
//            taskOutput.append("Loading Weather Station....\n");
//            for(int i = 0;i < weatherList.size();i++)
//            {
//                WeatherStation wsta = new WeatherStation();
//                String tmp[] = weatherList.get(i).split(",");
//                try
//                {
//                    wsta.Code = tmp[0];
//                    wsta.StationName = tmp[1];
//                    WeatherStationList.AddNew(wsta);
//                }
//                catch(Exception ex) {
//                    System.out.print(ex.getMessage());
//                }
//                double prog = ((progress++) / max) * 100.00;
//                try{
//                setProgress((int) prog);
//                }
//                catch(Exception ex)
//                {
//                    System.out.print(ex.getMessage());
//                    taskOutput.append("!Error:" + ex.getMessage() + "\n");
//                    isValid = false;
//                }
//                    try {
//                        Thread.sleep(0);
//                    } catch (InterruptedException ex) { }
//            }
            //</editor-fold>

            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            //setCursor(null); //turn off the wait cursor
            if(isValid){
                taskOutput.append("Done!\n");
                dispose();
            }
            else
                taskOutput.append("ERROR!\n");                
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public LoadingDataFrame(String dir, String version) {
        this.dir = dir;
        this.version = version;

        initComponents();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2 , (screenHeight - winSize.height) / 2);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent evt){
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                tt();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pb = new javax.swing.JProgressBar();
        l = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taskOutput.setColumns(20);
        taskOutput.setRows(5);
        jScrollPane1.setViewportView(taskOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addComponent(l, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tt() {
        // TODO add your handling code here:
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            pb.setValue(progress);
            taskOutput.append(String.format(
                    " Completed %d%% of task.\n", task.getProgress()));
            //taskOutput.setSelectionStart(taskOutput.getRows()-1);
            //taskOutput.setSelectionEnd(taskOutput.getRows()-1);
            //taskOutput.setCaretPosition(taskOutput.getRows()-1);
        }
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JProgressBar pb;
    private javax.swing.JTextArea taskOutput;
    // End of variables declaration//GEN-END:variables

}
