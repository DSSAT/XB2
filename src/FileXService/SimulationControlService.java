package FileXService;

import Extensions.Utils;
import FileXModel.FileX;
import FileXModel.Simulation;
import FileXModel.SimulationList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 *
 * @author Jazzy
 */
public class SimulationControlService {
    public static SimulationList Read(String fileName) {
        SimulationList simulationList = new SimulationList();
        
        try {
            FileReader fReader = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fReader);
            String strRead = null;
            
            String simGeneralHeader = "";
            String simOptionHeader = "";
            String simMethodHeader = "";
            String simManagementHeader = "";
            String simOutputHeader = "";
            String simPlantingHeader = "";
            String simIrrigationHeader = "";
            String simNitrogenHeader = "";
            String simResidueHeader = "";
            String simHarvestHeader = "";
            
            boolean bSimulation = false;
            int nSimulation = 0;
            
            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;
                
                if (tmp.trim().startsWith("*SIMULATION CONTROLS")) {
                    bSimulation = true;
                } else if (bSimulation && tmp.trim().startsWith("@N GENERAL")) {
                    simGeneralHeader = tmp.trim();
                    nSimulation = 1;
                } else if (bSimulation && tmp.trim().startsWith("@N OPTIONS")) {
                    simOptionHeader = tmp.trim();
                    nSimulation = 2;
                } else if (bSimulation && tmp.trim().startsWith("@N METHODS")) {
                    simMethodHeader = tmp.trim();
                    nSimulation = 3;
                } else if (bSimulation && tmp.trim().startsWith("@N MANAGEMENT")) {
                    simManagementHeader = tmp.trim();
                    nSimulation = 4;
                } else if (bSimulation && tmp.trim().startsWith("@N OUTPUTS")) {
                    simOutputHeader = tmp.trim();
                    nSimulation = 5;
                } else if (bSimulation && tmp.trim().startsWith("@N PLANTING")) {
                    simPlantingHeader = tmp.trim();
                    nSimulation = 6;
                } else if (bSimulation && tmp.trim().startsWith("@N IRRIGATION")) {
                    simIrrigationHeader = tmp.trim();
                    nSimulation = 7;
                } else if (bSimulation && tmp.trim().startsWith("@N NITROGEN")) {
                    simNitrogenHeader = tmp.trim();
                    nSimulation = 8;
                } else if (bSimulation && tmp.trim().startsWith("@N RESIDUES")) {
                    simResidueHeader = tmp.trim();
                    nSimulation = 9;
                } else if (bSimulation && tmp.trim().startsWith("@N HARVEST")) {
                    simHarvestHeader = tmp.trim();
                    nSimulation = 10;
                }

                else if (bSimulation && nSimulation == 1 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //NYERS NREPS START SDATE RSEED SNAME.................... SMODEL
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    tmp = Utils.PadRight(tmp, simGeneralHeader.length(), ' ');

                    sim.NYERS = Utils.GetInteger(simGeneralHeader, tmp, "NYERS", 5);
                    sim.NREPS = Utils.GetInteger(simGeneralHeader, tmp, "NREPS", 5);
                    sim.START = Utils.GetString(simGeneralHeader, tmp, "START", 5);
                    sim.SDATE = Utils.GetDate(simGeneralHeader, tmp, "SDATE", 5);
                    sim.RSEED = Utils.GetFloat(simGeneralHeader, tmp, "RSEED", 5);
                    sim.SNAME = Utils.GetString(simGeneralHeader, tmp, "SNAME", simGeneralHeader.contains("SMODEL") ? 25 : 30);
                    
                    if(simGeneralHeader.contains("SMODEL"))
                        sim.SMODEL = Utils.GetString(simGeneralHeader, tmp, "SMODEL", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 2 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //WATER NITRO SYMBI PHOSP POTAS DISES  CHEM  TILL   CO2
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.WATER = Utils.GetString(simOptionHeader, tmp, "WATER", 5);
                    sim.NITRO = Utils.GetString(simOptionHeader, tmp, "NITRO", 5);
                    sim.SYMBI = Utils.GetString(simOptionHeader, tmp, "SYMBI", 5);
                    sim.PHOSP = Utils.GetString(simOptionHeader, tmp, "PHOSP", 5);
                    sim.POTAS = Utils.GetString(simOptionHeader, tmp, "POTAS", 5);
                    sim.DISES = Utils.GetString(simOptionHeader, tmp, "DISES", 5);
                    sim.CHEM = Utils.GetString(simOptionHeader, tmp, " CHEM", 5);
                    sim.TILL = Utils.GetString(simOptionHeader, tmp, " TILL", 5);
                    sim.CO2 = Utils.GetString(simOptionHeader, tmp, "  CO2", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 3 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //WTHER INCON LIGHT EVAPO INFIL PHOTO HYDRO NSWIT MESOM MESEV MESOL
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.WTHER = Utils.GetString(simMethodHeader, tmp, "WTHER", 5);
                    sim.INCON = Utils.GetString(simMethodHeader, tmp, "INCON", 5);
                    sim.LIGHT = Utils.GetString(simMethodHeader, tmp, "LIGHT", 5);
                    sim.EVAPO = Utils.GetString(simMethodHeader, tmp, "EVAPO", 5);
                    sim.INFIL = Utils.GetString(simMethodHeader, tmp, "INFIL", 5);
                    sim.PHOTO = Utils.GetString(simMethodHeader, tmp, "PHOTO", 5);
                    sim.HYDRO = Utils.GetString(simMethodHeader, tmp, "HYDRO", 5);
                    sim.NSWIT = Utils.GetString(simMethodHeader, tmp, "NSWIT", 5);
                    sim.MESOM = Utils.GetString(simMethodHeader, tmp, "MESOM", 5);
                    sim.MESEV = Utils.GetString(simMethodHeader, tmp, "MESEV", 5);
                    sim.MESOL = Utils.GetString(simMethodHeader, tmp, "MESOL", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 4 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //PLANT IRRIG FERTI RESID HARVS
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.PLANT = Utils.GetString(simManagementHeader, tmp, "PLANT", 5);
                    sim.IRRIG = Utils.GetString(simManagementHeader, tmp, "IRRIG", 5);
                    sim.FERTI = Utils.GetString(simManagementHeader, tmp, "FERTI", 5);
                    sim.RESID = Utils.GetString(simManagementHeader, tmp, "RESID", 5);
                    sim.HARVS = Utils.GetString(simManagementHeader, tmp, "HARVS", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 5 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //FNAME OVVEW SUMRY FROPT GROUT CAOUT WAOUT NIOUT MIOUT DIOUT VBOSE CHOUT OPOUT
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.FNAME = Utils.GetString(simOutputHeader, tmp, "FNAME", 5);
                    sim.OVVEW = Utils.GetString(simOutputHeader, tmp, "OVVEW", 5);
                    sim.SUMRY = Utils.GetString(simOutputHeader, tmp, "SUMRY", 5);
                    sim.FROPT = Utils.GetInteger(simOutputHeader, tmp, "FROPT", 5);
                    sim.GROUT = Utils.GetString(simOutputHeader, tmp, "GROUT", 5);
                    sim.CAOUT = Utils.GetString(simOutputHeader, tmp, "CAOUT", 5);
                    sim.WAOUT = Utils.GetString(simOutputHeader, tmp, "WAOUT", 5);
                    sim.NIOUT = Utils.GetString(simOutputHeader, tmp, "NIOUT", 5);
                    sim.MIOUT = Utils.GetString(simOutputHeader, tmp, "MIOUT", 5);
                    sim.DIOUT = Utils.GetString(simOutputHeader, tmp, "DIOUT", 5);
                    sim.VBOSE = Utils.GetString(simOutputHeader, tmp, "VBOSE", 5);
                    sim.CHOUT = Utils.GetString(simOutputHeader, tmp, "CHOUT", 5);
                    sim.OPOUT = Utils.GetString(simOutputHeader, tmp, "OPOUT", 5);
                    sim.FMOPT = Utils.GetString(simOutputHeader, tmp, "FMOPT", 5);

                    if(sim.FMOPT == "" || sim.FMOPT == null)
                        sim.FMOPT = "A";

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 6 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //PFRST PLAST PH2OL PH2OU PH2OD PSTMX PSTMN
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    try{
                        sim.PFRST = Utils.GetDate(simPlantingHeader, tmp, "PFRST", 5);
                    }
                    catch(Exception ex) {
                        sim.PFRST_Day = Utils.GetInteger(simPlantingHeader, tmp, "PFRST", 2);
                    }
                    try{
                        sim.PLAST = Utils.GetDate(simPlantingHeader, tmp, "PLAST", 5);
                    }
                    catch(Exception ex) {
                        sim.PLAST_Day = Utils.GetInteger(simPlantingHeader, tmp, "PLAST", 2);
                    }
                    sim.PH2OL = Utils.GetFloat(simPlantingHeader, tmp, "PH2OL", 5);
                    sim.PH2OU = Utils.GetFloat(simPlantingHeader, tmp, "PH2OU", 5);
                    sim.PH2OD = Utils.GetFloat(simPlantingHeader, tmp, "PH2OD", 5);
                    sim.PSTMX = Utils.GetFloat(simPlantingHeader, tmp, "PSTMX", 5);
                    sim.PSTMN = Utils.GetFloat(simPlantingHeader, tmp, "PSTMN", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 7 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //IMDEP ITHRL ITHRU IROFF IMETH IRAMT IREFF
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.IMDEP = Utils.GetFloat(simIrrigationHeader, tmp, "IMDEP", 5);
                    sim.ITHRL = Utils.GetFloat(simIrrigationHeader, tmp, "ITHRL", 5);
                    sim.ITHRU = Utils.GetFloat(simIrrigationHeader, tmp, "ITHRU", 5);
                    sim.IROFF = Utils.GetString(simIrrigationHeader, tmp, "IROFF", 5);
                    sim.IMETH = Utils.GetString(simIrrigationHeader, tmp, "IMETH", 5);
                    sim.IRAMT = Utils.GetFloat(simIrrigationHeader, tmp, "IRAMT", 5);
                    sim.IREFF = Utils.GetFloat(simIrrigationHeader, tmp, "IREFF", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 8 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //NMDEP NMTHR NAMNT NCODE NAOFF
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.NMDEP = Utils.GetFloat(simNitrogenHeader, tmp, "NMDEP", 5);
                    sim.NMTHR = Utils.GetFloat(simNitrogenHeader, tmp, "NMTHR", 5);
                    sim.NAMNT = Utils.GetFloat(simNitrogenHeader, tmp, "NAMNT", 5);
                    sim.NCODE = Utils.GetString(simNitrogenHeader, tmp, "NCODE", 5);
                    sim.NAOFF = Utils.GetString(simNitrogenHeader, tmp, "NAOFF", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                }
                else if (bSimulation && nSimulation == 9 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    // RIPCN RTIME RIDEP
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.RIPCN = Utils.GetFloat(simResidueHeader, tmp, "RIPCN", 5);
                    sim.RTIME = Utils.GetFloat(simResidueHeader, tmp, "RTIME", 5);
                    sim.RIDEP = Utils.GetFloat(simResidueHeader, tmp, "RIDEP", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                else if (bSimulation && nSimulation == 10 && !tmp.trim().startsWith("!") && !tmp.trim().isEmpty() && !tmp.trim().startsWith("@  AUTOMATIC MANAGEMENT")) {
                    Simulation sim = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    //HFRST HLAST HPCNP HPCNR
                    if(level > simulationList.GetSize()) {
                        sim = new Simulation();
                    } else {
                        sim = (Simulation)simulationList.GetAt(level - 1);
                    }

                    sim.HFRST = Utils.GetFloat(simHarvestHeader, tmp, "HFRST", 5);
                    try{
                        sim.HLAST = Utils.GetDate(simHarvestHeader, tmp, "HLAST", 5);
                    }
                    catch(Exception ex) {
                        sim.HLAST_Day = Utils.GetInteger(simHarvestHeader, tmp, "HLAST", 5);
                    }
                    sim.HPCNP = Utils.GetFloat(simHarvestHeader, tmp, "HPCNP", 5);
                    sim.HPCNR = Utils.GetFloat(simHarvestHeader, tmp, "HPCNR", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
        return simulationList;
    }
    
    public static void Extract(PrintWriter pw) {
        // <editor-fold defaultstate="collapsed" desc="Simulation Options">
        if (FileX.simulationList.GetSize() > 0) {
            pw.println();
            pw.println("*SIMULATION CONTROLS");
            for (int i = 0; i < FileX.simulationList.GetSize(); i++) {
                Simulation sim = (Simulation)FileX.simulationList.GetAt(i);
                Integer level = i + 1;

                pw.println("@N GENERAL     NYERS NREPS START SDATE RSEED SNAME.................... SMODEL");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" GE         ");
                pw.print(" " + Utils.PadLeft(sim.NYERS, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NREPS, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.START, 5, ' '));
                pw.print(" " + Utils.PadRight(Utils.JulianDate(sim.SDATE), 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.RSEED, 5, ' '));
                pw.print(" " + Utils.PadRight(sim.SNAME, 25, ' '));
                if (sim.SMODEL != null) {
                    pw.print(" " + sim.SMODEL);
                } else {
                    pw.print(" -99");
                }
                pw.println();

                pw.println("@N OPTIONS     WATER NITRO SYMBI PHOSP POTAS DISES  CHEM  TILL   CO2");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" OP         ");
                pw.print(" " + Utils.PadLeft(sim.WATER, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NITRO, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.SYMBI, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PHOSP, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.POTAS, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.DISES, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.CHEM, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.TILL, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.CO2, 5, ' '));
                pw.println();

                pw.println("@N METHODS     WTHER INCON LIGHT EVAPO INFIL PHOTO HYDRO NSWIT MESOM MESEV MESOL");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" ME         ");
                pw.print(" " + Utils.PadLeft(sim.WTHER, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.INCON, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.LIGHT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.EVAPO, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.INFIL, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PHOTO, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.HYDRO, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NSWIT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.MESOM, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.MESEV, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.MESOL, 5, ' '));
                pw.println();

                pw.println("@N MANAGEMENT  PLANT IRRIG FERTI RESID HARVS");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" MA         ");
                pw.print(" " + Utils.PadLeft(sim.PLANT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.IRRIG, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.FERTI, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.RESID, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.HARVS, 5, ' '));
                pw.println();

                pw.println("@N OUTPUTS     FNAME OVVEW SUMRY FROPT GROUT CAOUT WAOUT NIOUT MIOUT DIOUT VBOSE CHOUT OPOUT FMOPT");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" OU         ");
                pw.print(" " + Utils.PadLeft(sim.FNAME, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.OVVEW, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.SUMRY, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.FROPT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.GROUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.CAOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.WAOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NIOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.MIOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.DIOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.VBOSE, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.CHOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.OPOUT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.FMOPT, 5, ' '));
                pw.println();

                pw.println();
                pw.println("@  AUTOMATIC MANAGEMENT");

                pw.println("@N PLANTING    PFRST PLAST PH2OL PH2OU PH2OD PSTMX PSTMN");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" PL         ");
                if(sim.PFRST != null)
                    pw.print(" " + Utils.PadRight(Utils.JulianDate(sim.PFRST), 5, ' '));
                else
                    pw.print(" " + Utils.PadLeft(sim.PFRST_Day, 5, ' '));
                if(sim.PLAST != null)
                    pw.print(" " + Utils.PadRight(Utils.JulianDate(sim.PLAST), 5, ' '));
                else
                    pw.print(" " + Utils.PadLeft(sim.PLAST_Day, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PH2OL, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PH2OU, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PH2OD, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PSTMX, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.PSTMN, 5, ' '));
                pw.println();

                pw.println("@N IRRIGATION  IMDEP ITHRL ITHRU IROFF IMETH IRAMT IREFF");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" IR         ");
                pw.print(" " + Utils.PadLeft(sim.IMDEP, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.ITHRL, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.ITHRU, 5, ' '));
                pw.print(" " + Utils.PadRight(sim.IROFF, 5, ' '));
                pw.print(" " + Utils.PadRight(sim.IMETH, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.IRAMT, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.IREFF, 5, ' '));
                pw.println();

                pw.println("@N NITROGEN    NMDEP NMTHR NAMNT NCODE NAOFF");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" NI         ");
                pw.print(" " + Utils.PadLeft(sim.NMDEP, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NMTHR, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.NAMNT, 5, ' '));
                pw.print(" " + Utils.PadRight(sim.NCODE, 5, ' '));
                pw.print(" " + Utils.PadRight(sim.NAOFF, 5, ' '));
                pw.println();

                pw.println("@N RESIDUES    RIPCN RTIME RIDEP");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" RE         ");
                pw.print(" " + Utils.PadLeft(sim.RIPCN, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.RTIME, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.RIDEP, 5, ' '));
                pw.println();

                pw.println("@N HARVEST     HFRST HLAST HPCNP HPCNR");
                pw.print(Utils.PadLeft(level, 2, ' '));
                pw.print(" HA         ");
                pw.print(" " + Utils.PadLeft(sim.HFRST, 5, ' '));
                pw.print(" " + Utils.PadRight(Utils.JulianDate(sim.HLAST), 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.HPCNP, 5, ' '));
                pw.print(" " + Utils.PadLeft(sim.HPCNR, 5, ' '));
                pw.println();
                pw.println();
            }
        }
        // </editor-fold>
    }
}
