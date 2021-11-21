/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileX;

import Library.CropList;
import Library.EnvironmentFactorList;
import Library.Tools;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazzy
 */
public class FileX {
    public static GeneralInformation general;
    public static FieldList fieldList;
    public static InitialConditionList initialList;
    public static SoilAnalysisList soilAnalysis;
    public static EnvironmentalList environmentals;
    public static CultivarList cultivars;
    public static PlantingList plantings;
    public static IrrigationList irrigations;
    public static FertilizerList fertilizerList;
    public static OrganicList organicList;
    public static TillageList tillageList;
    public static HarvestList harvestList;
    public static ChemicalList chemicalList;
    public static SimulationList simulationList;
    public static TreatmentList treaments;
    private static boolean opened = false;

    public static boolean GetStatus() {
        return opened;
    }

    public static void NewFileX() {
        general = new GeneralInformation();
        fieldList = new FieldList();
        initialList = new InitialConditionList();
        soilAnalysis = new SoilAnalysisList();
        environmentals = new EnvironmentalList();
        cultivars = new CultivarList();
        plantings = new PlantingList();
        irrigations = new IrrigationList();
        fertilizerList = new FertilizerList();
        organicList = new OrganicList();
        tillageList = new TillageList();
        harvestList = new HarvestList();
        chemicalList = new ChemicalList();
        simulationList = new SimulationList();
        treaments = new TreatmentList();

        opened = true;
    }

    public static void CloseFile() {
        general = null;
        fieldList = null;
        initialList = null;
        soilAnalysis = null;
        environmentals = null;
        cultivars = null;
        plantings = null;
        irrigations = null;
        fertilizerList = null;
        organicList = null;
        tillageList = null;
        harvestList = null;
        chemicalList = null;
        simulationList = null;
        treaments = null;

        opened = false;
    }

    public static void SaveFile(File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        PrintWriter pw = new PrintWriter(writer);

        // <editor-fold defaultstate="collapsed" desc="GENERAL">
        pw.println("*EXP.DETAILS: " + general.InstituteCode + general.SiteCode + general.Year.substring(2,4) + general.ExperimentNumber + general.crop.CropCode + " " + general.ExperimentName);
        pw.println();
        pw.println("*GENERAL");

        pw.println("@PEOPLE");
        if (general.People != null && !general.People.isEmpty()) {
            pw.println(general.People);

        } else {
            pw.println("-99");


        }
        pw.println("@ADDRESS");
        if (general.Adress != null && !general.Adress.isEmpty()) {
            pw.println(general.Adress);

        } else {
            pw.println("-99");

        }
        pw.println("@SITE");
        if (general.Site != null && !general.Site.isEmpty()) {
            pw.println(general.Site);

        } else {
            pw.println("-99");
        }

        if (general.Notes != null && !general.Notes.isEmpty()) {
            pw.println("@NOTES");
            String[] tmp = general.Notes.split("\n");
            for(int i = 0;i < tmp.length;i++)
                pw.println("        " + tmp[i]);
        }

        // <editor-fold defaultstate="collapsed" desc="PLOT">

        if (general.PAREA != null || general.PRNO != null || general.PLEN != null || general.PLDR != null || general.PLSP != null || general.PLAY != null || general.HAREA != null || general.HRNO != null || general.HLEN != null || general.HARM != null) {
            pw.println("@ PAREA  PRNO  PLEN  PLDR  PLSP  PLAY HAREA  HRNO  HLEN  HARM.........");
            pw.print(Tools.PadLeft(general.PAREA, 7, ' '));

            pw.print(Tools.PadLeft(general.PRNO, 6, ' '));
            pw.print(Tools.PadLeft(general.PLEN, 6, ' '));
            pw.print(Tools.PadLeft(general.PLDR, 6, ' '));
            pw.print(Tools.PadLeft(general.PLSP, 6, ' '));
            pw.print(Tools.PadLeft(general.PLAY, 6, ' '));
            pw.print(Tools.PadLeft(general.HAREA, 6, ' '));
            pw.print(Tools.PadLeft(general.HRNO, 6, ' '));
            pw.print(Tools.PadLeft(general.HLEN, 6, ' '));
            pw.print(Tools.PadLeft(general.HARM, 15, ' '));
        }
        // </editor-fold>
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Treatment">
        if (treaments.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*TREATMENTS                        -------------FACTOR LEVELS------------");
            pw.println("@N R O C TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM");
            for (int i = 0; i < treaments.GetSize(); i++) {
                Treatment treat = treaments.GetAt(i);
                Integer level = i + 1;
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" 1 0 0");
                try {
                    if (!treat.TNAME.isEmpty()) {
                        pw.print(" " + Tools.PadRight(treat.TNAME, 25, ' '));
                    } else {
                        pw.print(" " + Tools.PadRight("", 25, ' '));
                    }
                } catch (Exception e) {
                    pw.print(" " + Tools.PadRight("", 25, ' '));
                }
                try {
                    String tmp = treat.CU.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.FL.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.SA.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.IC.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MP.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MI.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MF.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MR.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MC.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MT.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.ME.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.MH.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                try {
                    String tmp = treat.SM.toString();
                    pw.print(" " + Tools.PadLeft(tmp, 2, ' '));
                } catch (Exception e) {
                    pw.print("  0");
                }
                pw.println();
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Cultivars">
        if (cultivars.GetSize() > 0) {
            pw.println();
            pw.println("*CULTIVARS");
            pw.println("@C CR INGENO CNAME");
            for (int i = 0; i < cultivars.GetSize(); i++) {
                Cultivar cul = cultivars.GetAt(i);
                Integer level = i + 1;
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadLeft(cul.CR, 2, ' '));
                pw.print(" " + Tools.PadLeft(cul.INGENO, 6, ' '));
                pw.print(" " + Tools.PadLeft(cul.CNAME, 0, ' '));
                pw.println();
            }
        }

        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Fields">
        if (fieldList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*FIELDS");
            pw.println("@L ID_FIELD WSTA....  FLSA  FLOB  FLDT  FLDD  FLDS  FLST SLTX  SLDP  ID_SOIL    FLNAME");
            for (int i = 0; i < fieldList.GetSize(); i++) {
                Integer level = i + 1;
                FieldDetail field = fieldList.GetAt(i);
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadRight(field.ID_FIELD, 8, ' '));
                pw.print(" " + Tools.PadRight(field.WSTA, 8, ' '));
                pw.print(" " + Tools.PadLeft(field.FLSA, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLOB, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLDT, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLDD, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLDS, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLST, 5, ' '));
                pw.print(" " + Tools.PadRight(field.SLTX, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.SLDP, 4, ' '));
                pw.print("  " + Tools.PadRight(field.ID_SOIL, 10, ' '));
                if (field.FLNAME != null) {
                    pw.print(" " + field.FLNAME);
                } else {
                    pw.print(" -99");
                }
                pw.println();
            }

            pw.println("@L ...........XCRD ...........YCRD .....ELEV .............AREA .SLEN .FLWR .SLAS FLHST FHDUR");
            for (int i = 0; i < fieldList.GetSize(); i++) {
                Integer level = i + 1;
                FieldDetail field = fieldList.GetAt(i);
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadLeft(field.XCRD, 15, ' '));
                pw.print(" " + Tools.PadLeft(field.YCRD, 15, ' '));
                pw.print(" " + Tools.PadLeft(field.ELEV, 9, ' '));
                pw.print(" " + Tools.PadLeft(field.AREA, 17, ' '));
                pw.print(" " + Tools.PadLeft(field.SLEN, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLWR, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.SLAS, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FLHST, 5, ' '));
                pw.print(" " + Tools.PadLeft(field.FHDUR, 5, ' '));
                pw.println();
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Soil Analysis">
        if (soilAnalysis.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*SOIL ANALYSIS");
            for (int i = 0; i < soilAnalysis.GetSize(); i++) {
                Integer level = i + 1;
                SoilAnalysis soil = soilAnalysis.GetAnalysis(i);

                pw.println("@A SADAT  SMHB  SMPX  SMKE  SANAME");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.JulianDate(soil.SADAT));
                pw.print(" " + Tools.PadLeft(soil.SMHB, 5, ' '));
                pw.print(" " + Tools.PadLeft(soil.SMPX, 5, ' '));
                pw.print(" " + Tools.PadLeft(soil.SMKE, 5, ' '));
                if (soil.SANAME != null) {
                    pw.print("  " + soil.SANAME);
                } else {
                    pw.print("  -99");
                }
                pw.println();
                if (soil.GetSize() > 0) {
                    for (int n = 0; n < soil.GetSize(); n++) {
                        SoilAnalysisLayer soilLayer = soil.GetLayer(n);
                        pw.println("@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC");
                        pw.print(Tools.PadLeft(level, 2, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SABL, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SADM, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SAOC, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SANI, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SAPHW, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SAPHB, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SAPX, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SAKE, 5, ' '));
                        pw.print(" " + Tools.PadLeft(soilLayer.SASC, 5, ' '));
                        pw.println();
                    }
                }
            }
        }
        // </editor-fold>
        
        // <editor-fold defaultstate="collapsed" desc="Initial Condition">
        if (initialList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*INITIAL CONDITIONS");
            for (int i = 0; i < initialList.GetSize(); i++) {
                Integer level = i + 1;
                InitialCondition init = initialList.GetAt(i);

                pw.println("@C   PCR ICDAT  ICRT  ICND  ICRN  ICRE  ICWD ICRES ICREN ICREP ICRIP ICRID ICNAME");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadLeft(init.PCR, 5, ' '));
                pw.print(" " + Tools.PadRight(Tools.JulianDate(init.ICDAT), 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRT, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICND, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRN, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRE, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICWD, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRES, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICREN, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICREP, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRIP, 5, ' '));
                pw.print(" " + Tools.PadLeft(init.ICRID, 5, ' '));
                if (init.ICNAME != null) {
                    pw.print(" " + init.ICNAME);
                } else {
                    pw.print(" -99");
                }
                pw.println();
                if (init.GetSize() > 0) {
                    pw.println("@C  ICBL  SH2O  SNH4  SNO3");

                    for (int n = 0; n < init.GetSize(); n++) {
                        InitialConditionApplication initApp = init.GetApp(n);
                        pw.print(Tools.PadLeft(level, 2, ' '));
                        pw.print(" " + Tools.PadLeft(initApp.ICBL, 5, ' '));
                        pw.print(" " + Tools.PadLeft(initApp.SH2O, 5, ' '));
                        pw.print(" " + Tools.PadLeft(initApp.SNH4, 5, ' '));
                        pw.print(" " + Tools.PadLeft(initApp.SNO3, 5, ' '));
                        pw.println();
                    }
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Planting">
        if (plantings.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*PLANTING DETAILS");
            pw.println("@P PDATE EDATE  PPOP  PPOE  PLME  PLDS  PLRS  PLRD  PLDP  PLWT  PAGE  PENV  PLPH  SPRL                        PLNAME");
            for (int i = 0; i < plantings.GetSize(); i++) {
                Integer level = i + 1;
                Planting plants = plantings.GetAt(i);
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadRight(Tools.JulianDate(plants.PDATE), 5, ' '));
                pw.print(" " + Tools.PadRight(Tools.JulianDate(plants.EDATE), 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PPOP, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PPOE, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLME, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLDS, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLRS, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLRD, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLDP, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLWT, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PAGE, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PENV, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.PLPH, 5, ' '));
                pw.print(" " + Tools.PadLeft(plants.SPRL, 5, ' '));
                if (plants.PLNAME != null) {
                    pw.print("                        " + plants.PLNAME);
                } else {
                    pw.print("                        -99");
                }
                pw.println();
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Irrigation">
        if (irrigations.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*IRRIGATION AND WATER MANAGEMENT");

            for (int i = 0; i < irrigations.GetSize(); i++) {
                Integer level = i + 1;
                Irrigation irrig = irrigations.GetAt(i);

                pw.println("@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" " + Tools.PadLeft(irrig.EFIR, 5, ' '));
                pw.print("   -99   -99   -99   -99   -99     1");
                if (irrig.IRNAME != null) {
                    pw.print(" " + irrig.IRNAME);
                } else {
                    pw.print(" -99");
                }
                pw.println();
                if (irrig.GetSize() > 0) {
                    pw.println("@I IDATE  IROP IRVAL");
                    for (int n = 0; n < irrig.GetSize(); n++) {
                        IrrigationApplication irrigApp = irrig.GetApp(n);
                        pw.print(Tools.PadLeft(level, 2, ' '));

                        try {
                            pw.print(" " + Tools.PadRight(Tools.JulianDate(irrigApp.IDATE), 5, ' '));
                        } catch (Exception e) {
                            pw.print(" " + Tools.PadLeft(irrigApp.IDAY, 5, ' '));
                        }

                        pw.print(" " + Tools.PadRight(irrigApp.IROP, 5, ' '));
                        pw.print(" " + Tools.PadLeft(irrigApp.IRVAL, 5, ' '));
                        pw.println();
                    }
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Fertilizer">
        if (fertilizerList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*FERTILIZERS (INORGANIC)");
            pw.println("@F FDATE  FMCD  FACD  FDEP  FAMN  FAMP  FAMK  FAMC  FAMO  FOCD FERNAME");
            for (int i = 0; i < fertilizerList.GetSize(); i++) {
                Fertilizer fertil = fertilizerList.GetAt(i);
                for (int n = 0; n < fertil.GetSize(); n++) {
                    FertilizerApplication ferApp = fertil.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(ferApp.FDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Tools.PadLeft(ferApp.FDAY, 5, ' '));
                    }

                    pw.print(" " + Tools.PadRight(ferApp.FMCD, 5, ' '));
                    pw.print(" " + Tools.PadRight(ferApp.FACD, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FDEP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FAMN, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FAMP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FAMK, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FAMC, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FAMO, 5, ' '));
                    pw.print(" " + Tools.PadLeft(ferApp.FOCD, 5, ' '));
                    if (fertil.FERNAME != null) {
                        pw.print(" " + fertil.FERNAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Organic Amendment">
        if (organicList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*RESIDUES AND ORGANIC FERTILIZER");
            pw.println("@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME");
            for (int i = 0; i < organicList.GetSize(); i++) {
                Organic organ = organicList.GetAt(i);
                for (int n = 0; n < organ.GetSize(); n++) {
                    OrganicApplication organApp = organ.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(organApp.RDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Tools.PadLeft(organApp.RDAY, 5, ' '));
                    }

                    pw.print(" " + Tools.PadLeft(organApp.RCOD, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RAMT, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RESN, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RESP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RESK, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RINP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RDEP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(organApp.RMET, 5, ' '));
                    if (organ.RENAME != null) {
                        pw.print(" " + organ.RENAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Chemicals">
        if (chemicalList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*CHEMICAL APPLICATIONS");
            pw.println("@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME");
            for (int i = 0; i < chemicalList.GetSize(); i++) {
                Chemical chem = chemicalList.GetAt(i);
                for (int n = 0; n < chem.GetSize(); n++) {
                    ChemicalApplication chemApp = chem.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(chemApp.CDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Tools.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + Tools.PadRight(chemApp.CHCOD, 5, ' '));
                    pw.print(" " + Tools.PadLeft(chemApp.CHAMT, 5, ' '));
                    pw.print(" " + Tools.PadRight(chemApp.CHME, 5, ' '));
                    pw.print(" " + Tools.PadLeft(chemApp.CHDEP, 5, ' '));
                    pw.print(" " + Tools.PadLeft(chemApp.CHT, 5, ' '));
                    if (chem.CHNAME != null) {
                        pw.print("  " + chem.CHNAME);
                    } else {
                        pw.print("  -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Harvest">
        if (harvestList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*HARVEST DETAILS");
            pw.println("@H HDATE  HSTG  HCOM HSIZE   HPC  HBPC HNAME");
            for (int i = 0; i < harvestList.GetSize(); i++) {
                Harvest harvest = harvestList.GetAt(i);
                for (int n = 0; n < harvest.GetSize(); n++) {
                    HarvestApplication harvestApp = harvest.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(harvestApp.HDATE), 5, ' '));
                    } catch (Exception e) {
                        try {
                            pw.print(" " + Tools.PadLeft(harvestApp.HDAY, 5, ' '));
                        } catch (Exception ex) {
                            pw.print(" " + Tools.PadLeft("-99", 5, ' '));
                        }
                    }

                    pw.print(" " + Tools.PadRight(harvestApp.HSTG, 5, ' '));
                    pw.print(" " + Tools.PadLeft(harvestApp.HCOM, 5, ' '));
                    pw.print(" " + Tools.PadLeft(harvestApp.HSIZE, 5, ' '));
                    pw.print(" " + Tools.PadLeft(harvestApp.HPC, 5, ' '));
                    pw.print(" " + Tools.PadLeft(harvestApp.HBPC, 5, ' '));
                    if (harvest.HNAME != null) {
                        pw.print(" " + harvest.HNAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Environmental">
        if (environmentals.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*ENVIRONMENT MODIFICATIONS");
            pw.println("@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME");

            for (int i = 0; i < environmentals.GetSize(); i++) {
                Environmental env = environmentals.GetAt(i);
                for (int n = 0; n < env.GetSize(); n++) {
                    EnvironmentApplication envApp = env.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(envApp.ODATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Tools.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + envApp.EDAY_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.EDAY, 4, ' '));
                    pw.print(" " + envApp.ERAD_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.ERAD, 4, ' '));
                    pw.print(" " + envApp.EMAX_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.EMAX, 4, ' '));
                    pw.print(" " + envApp.EMIN_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.EMIN, 4, ' '));
                    pw.print(" " + envApp.ERAIN_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.ERAIN, 4, ' '));
                    pw.print(" " + envApp.ECO2_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.ECO2, 4, ' '));
                    pw.print(" " + envApp.EDEW_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.EDEW, 4, ' '));
                    pw.print(" " + envApp.EWIND_Fact.Code);
                    pw.print(Tools.PadLeft(envApp.EWIND, 4, ' '));
                    if (env.ENVNAME != null) {
                        pw.print(" " + env.ENVNAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Tillage">
        if (tillageList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*TILLAGE AND ROTATIONS");
            pw.println("@T TDATE TIMPL  TDEP TNAME");
            for (int i = 0; i < tillageList.GetSize(); i++) {
                Tillage tillage = tillageList.GetAt(i);
                for (int n = 0; n < tillage.GetSize(); n++) {
                    TillageApplication tilApp = tillage.GetApp(n);

                    Integer level = i + 1;
                    pw.print(Tools.PadLeft(level, 2, ' '));

                    try {
                        pw.print(" " + Tools.PadRight(Tools.JulianDate(tilApp.TDATE), 5, ' '));
                    } catch (Exception e) {
                        pw.print(" " + Tools.PadLeft("-99", 5, ' '));
                    }

                    pw.print(" " + Tools.PadRight(tilApp.TIMPL, 5, ' '));
                    pw.print(" " + Tools.PadLeft(tilApp.TDEP, 5, ' '));
                    if (tillage.TNAME != null) {
                        pw.print(" " + tillage.TNAME);
                    } else {
                        pw.print(" -99");
                    }
                    pw.println();
                }
            }
        }
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Simulation Options">
        if (simulationList.GetSize() > 0) {
            pw.println();
            pw.println();
            pw.println("*SIMULATION CONTROLS");
            for (int i = 0; i < simulationList.GetSize(); i++) {
                Simulation sim = simulationList.GetAt(i);
                Integer level = i + 1;

                pw.println("@N GENERAL     NYERS NREPS START SDATE RSEED SNAME.................... SMODEL");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" GE         ");
                pw.print(" " + Tools.PadLeft(sim.NYERS, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NREPS, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.START, 5, ' '));
                pw.print(" " + Tools.PadRight(Tools.JulianDate(sim.SDATE), 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.RSEED, 5, ' '));
                pw.print(" " + Tools.PadRight(sim.SNAME, 25, ' '));
                if (sim.SMODEL != null) {
                    pw.print(" " + sim.SMODEL);
                } else {
                    pw.print(" -99");
                }
                pw.println();

                pw.println("@N OPTIONS     WATER NITRO SYMBI PHOSP POTAS DISES  CHEM  TILL   CO2");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" OP         ");
                pw.print(" " + Tools.PadLeft(sim.WATER, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NITRO, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.SYMBI, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PHOSP, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.POTAS, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.DISES, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.CHEM, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.TILL, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.CO2, 5, ' '));
                pw.println();

                pw.println("@N METHODS     WTHER INCON LIGHT EVAPO INFIL PHOTO HYDRO NSWIT MESOM MESEV MESOL");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" ME         ");
                pw.print(" " + Tools.PadLeft(sim.WTHER, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.INCON, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.LIGHT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.EVAPO, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.INFIL, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PHOTO, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.HYDRO, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NSWIT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.MESOM, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.MESEV, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.MESOL, 5, ' '));
                pw.println();

                pw.println("@N MANAGEMENT  PLANT IRRIG FERTI RESID HARVS");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" MA         ");
                pw.print(" " + Tools.PadLeft(sim.PLANT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.IRRIG, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.FERTI, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.RESID, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.HARVS, 5, ' '));
                pw.println();

                pw.println("@N OUTPUTS     FNAME OVVEW SUMRY FROPT GROUT CAOUT WAOUT NIOUT MIOUT DIOUT VBOSE CHOUT OPOUT");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" OU         ");
                pw.print(" " + Tools.PadLeft(sim.FNAME, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.OVVEW, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.SUMRY, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.FROPT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.GROUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.CAOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.WAOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NIOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.MIOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.DIOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.VBOSE, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.CHOUT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.OPOUT, 5, ' '));
                pw.println();

                pw.println();
                pw.println("@  AUTOMATIC MANAGEMENT");

                pw.println("@N PLANTING    PFRST PLAST PH2OL PH2OU PH2OD PSTMX PSTMN");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" PL         ");
                if(sim.PFRST != null)
                    pw.print(" " + Tools.PadRight(Tools.JulianDate(sim.PFRST), 5, ' '));
                else
                    pw.print(" " + Tools.PadLeft(sim.PFRST_Day, 5, ' '));
                if(sim.PLAST != null)
                    pw.print(" " + Tools.PadRight(Tools.JulianDate(sim.PLAST), 5, ' '));
                else
                    pw.print(" " + Tools.PadLeft(sim.PLAST_Day, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PH2OL, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PH2OU, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PH2OD, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PSTMX, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.PSTMN, 5, ' '));
                pw.println();

                pw.println("@N IRRIGATION  IMDEP ITHRL ITHRU IROFF IMETH IRAMT IREFF");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" IR         ");
                pw.print(" " + Tools.PadLeft(sim.IMDEP, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.ITHRL, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.ITHRU, 5, ' '));
                pw.print(" " + Tools.PadRight(sim.IROFF, 5, ' '));
                pw.print(" " + Tools.PadRight(sim.IMETH, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.IRAMT, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.IREFF, 5, ' '));
                pw.println();

                pw.println("@N NITROGEN    NMDEP NMTHR NAMNT NCODE NAOFF");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" NI         ");
                pw.print(" " + Tools.PadLeft(sim.NMDEP, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NMTHR, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.NAMNT, 5, ' '));
                pw.print(" " + Tools.PadRight(sim.NCODE, 5, ' '));
                pw.print(" " + Tools.PadRight(sim.NAOFF, 5, ' '));
                pw.println();

                pw.println("@N RESIDUES    RIPCN RTIME RIDEP");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" RE         ");
                pw.print(" " + Tools.PadLeft(sim.RIPCN, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.RTIME, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.RIDEP, 5, ' '));
                pw.println();

                pw.println("@N HARVEST     HFRST HLAST HPCNP HPCNR");
                pw.print(Tools.PadLeft(level, 2, ' '));
                pw.print(" HA         ");
                pw.print(" " + Tools.PadLeft(sim.HFRST, 5, ' '));
                pw.print(" " + Tools.PadRight(Tools.JulianDate(sim.HLAST), 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.HPCNP, 5, ' '));
                pw.print(" " + Tools.PadLeft(sim.HPCNR, 5, ' '));
                pw.println();
                pw.println();
            }
        }
        // </editor-fold>

        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(FileX.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void OpenFileX(File fileName) {
        NewFileX();

        FileReader fReader = null;
        BufferedReader br = null;
        try {
            fReader = new FileReader(fileName);

            try {
                //String tmpFile[] = fileName.split("\\");
                String xFile = fileName.getName();

                general.InstituteCode = xFile.substring(0, 2);
                general.SiteCode = xFile.substring(2, 4);
                general.Year = (Integer.parseInt(xFile.substring(4, 6)) > 80) ? "19" + xFile.substring(4, 6) : "20" + xFile.substring(4, 6);
                general.ExperimentNumber = xFile.substring(6, 8);

                if(xFile.endsWith("SQX")) general.FileType = "Sequential";
                else if(xFile.endsWith("SNX")) general.FileType = "Seasonal";
                else if(xFile.endsWith("GSX")) general.FileType = "Spatial";
                else {
                    general.FileType = "Experimental";
                    try {
                        general.crop = CropList.GetAt(xFile.substring(9, 11));
                    } catch (Exception e) {
                    }
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            return;
        }

        br = new BufferedReader(fReader);

        String strRead = "";
        try {
            Boolean bPeople = false;
            Boolean bAddress = false;
            Boolean bSite = false;
            Boolean bNotes = false;

            Boolean bPlot = false;
            String plotHeader = "@ PAREA  PRNO  PLEN  PLDR  PLSP  PLAY HAREA  HRNO  HLEN  HARM.........";

            Boolean bTreatment = false;
            Boolean bTreatmentHeader = false;
            String treatmentHeader = "";

            Boolean bCultivar = false;
            Boolean bCultivarHeader = false;
            String cultivarHeader = "";

            Boolean bField = false;
            Boolean bFieldHeader1 = false;
            Boolean bFieldHeader2 = false;
            String fieldHeader1 = "";
            String fieldHeader2 = "";

            Boolean bSoil = false;
            Boolean bSoilHeader1 = false;
            Boolean bSoilHeader2 = false;
            String soilHeader1 = "";
            String soilHeader2 = "";

            Boolean bInit = false;
            Boolean bInitHeader1 = false;
            Boolean bInitHeader2 = false;
            String initHeader1 = "";
            String initHeader2 = "";

            Boolean bPlanting = false;
            Boolean bPlantingHeader = false;
            String plantingHeader = "";

            Boolean bIrrig = false;
            Boolean bIrrigHeader1 = false;
            Boolean bIrrigHeader2 = false;
            String irrigHeader1 = "";
            String irrigHeader2 = "";

            Boolean bFertilizer = false;
            Boolean bFertilizerHeader = false;
            String fertilizerHeader = "";

            Boolean bOrganic = false;
            Boolean bOrganicHeader = false;
            String organicHeader = "";

            Boolean bChemical = false;
            Boolean bChemicalHeader = false;
            String chemicalHeader = "";

            Boolean bTillage = false;
            Boolean bTillageHeader = false;
            String tillageHeader = "";

            Boolean bEnvironment = false;
            Boolean bEnvironmentHeader = false;
            String environmentHeader = "";

            Boolean bHarvest = false;
            Boolean bHarvestHeader = false;
            String harvestHeader = "";

            Boolean bSimulation = false;
            int nSimulation = -1;
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

            while ((strRead = br.readLine()) != null) {
                String tmp = strRead;

                //if(strRead.trim().isEmpty()) continue;

                if(tmp.trim().startsWith("*EXP.DETAILS:"))
                {
                    String temp = tmp.substring(13, tmp.length()).trim();
                    String startS = general.InstituteCode + general.SiteCode + general.Year.substring(2,4) + general.ExperimentNumber;
                    if(general.crop != null) startS += general.crop.CropCode;
                    if(temp.startsWith(startS))
                        temp = temp.replaceFirst(startS, "").trim();
                    general.ExperimentName = temp;
                }

                // <editor-fold defaultstate="collapsed" desc="PEOPLE">
                else if (tmp.startsWith("@PEOPLE")) {
                    bPeople = true;

                } else if (bPeople) {
                    if(!tmp.equals("-99"))  general.People = tmp.trim();
                    else general.People = null;
                    bPeople = false;
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="@ADDRESS">
                else if (tmp.startsWith("@ADDRESS")) {
                    bAddress = true;
                } else if (bAddress) {
                    if(!tmp.equals("-99"))  general.Adress = tmp.trim();
                    else general.Adress = null;
                    bAddress = false;
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="@SITE">
                else if (tmp.startsWith("@SITE")) {
                    bSite = true;
                } else if (bSite) {
                    if (!tmp.equals("-99")) {
                        general.Site = tmp.trim();

                    } else {
                        general.Site = null;

                    }
                    bSite = false;
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="@NOTES">
                else if (tmp.startsWith("@NOTES")) {
                    bNotes = true;
                } else if (bNotes) {
                    if (!tmp.trim().equals("-99")) {
                        if(tmp.trim().startsWith("!") || tmp.trim().startsWith("@") || tmp.trim().startsWith("*") || tmp.trim().isEmpty()) {
                            bNotes = false;
                        } else {
                            general.Notes = (general.Notes == null || general.Notes.isEmpty()) ? tmp.trim() : general.Notes + "\n" + tmp.trim();
                        }
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="PLOT (Plot information & Harvest Information)">
                else if (tmp.startsWith(plotHeader)) {
                    bPlot = true;
                } else if (bPlot) {
                    tmp = Tools.PadRight(tmp, plotHeader.length(), ' ');
                    general.PAREA = GetFloat(plotHeader, tmp, "PAREA", 5);
                    general.PRNO = GetInteger(plotHeader, tmp, "PRNO", 5);
                    general.PLEN = GetFloat(plotHeader, tmp, "PLEN", 5);
                    general.PLDR = GetInteger(plotHeader, tmp, "PLDR", 5);
                    general.PLSP = GetFloat(plotHeader, tmp, "PLSP", 5);
                    general.PLAY = GetString(plotHeader, tmp, "PLAY", 5);
                    general.HAREA = GetFloat(plotHeader, tmp, "HAREA", 5);
                    general.HRNO = GetInteger(plotHeader, tmp, "HRNO", 5);
                    general.HLEN = GetFloat(plotHeader, tmp, "HLEN", 5);
                    general.HARM = GetString(plotHeader, tmp, "HARM", 13);
                    bPlot = false;
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="TREATMENTS">
                else if (tmp.trim().startsWith("*TREATMENTS")) {
                    bTreatment = true;

                } else if (bTreatment && !bTreatmentHeader && tmp.trim().startsWith("@")) {
                    treatmentHeader = tmp.trim();
                    bTreatmentHeader = true;
                } else if (bTreatment && bTreatmentHeader) {
                    if (tmp.trim().isEmpty()) {
                        bTreatment = false;
                        bTreatmentHeader = false;
                        continue;
                    }
                    //TNAME.................... CU FL SA IC MP MI MF MR MC MT ME MH SM
                    Treatment treatment = new Treatment();
                    treatment.TNAME = GetString(treatmentHeader, tmp, "TNAME", 25);
                    treatment.CU = GetInteger(treatmentHeader, tmp, "CU", 2);
                    treatment.FL = GetInteger(treatmentHeader, tmp, "FL", 2);
                    treatment.SA = GetInteger(treatmentHeader, tmp, "SA", 2);
                    treatment.IC = GetInteger(treatmentHeader, tmp, "IC", 2);
                    treatment.MP = GetInteger(treatmentHeader, tmp, "MP", 2);
                    treatment.MI = GetInteger(treatmentHeader, tmp, "MI", 2);
                    treatment.MF = GetInteger(treatmentHeader, tmp, "MF", 2);
                    treatment.MR = GetInteger(treatmentHeader, tmp, "MR", 2);
                    treatment.MC = GetInteger(treatmentHeader, tmp, "MC", 2);
                    treatment.MT = GetInteger(treatmentHeader, tmp, "MT", 2);
                    treatment.ME = GetInteger(treatmentHeader, tmp, "ME", 2);
                    treatment.MH = GetInteger(treatmentHeader, tmp, "MH", 2);
                    treatment.SM = GetInteger(treatmentHeader, tmp, "SM", 2);
                    treaments.AddNew(treatment);
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="CULTIVARS">
                else if (tmp.trim().startsWith("*CULTIVARS")) {
                    bCultivar = true;

                } else if (bCultivar && !bCultivarHeader && tmp.trim().startsWith("@")) {
                    cultivarHeader = tmp.trim();
                    bCultivarHeader = true;
                } else if (bCultivar && bCultivarHeader) {
                    if (tmp.trim().isEmpty()) {
                        bCultivar = false;
                        bCultivarHeader = false;
                        continue;
                    }
                    //@C CR INGENO CNAME
                    Cultivar cul = new Cultivar();
                    cul.CR = GetString(cultivarHeader, tmp, "CR", 2);
                    cul.INGENO = GetString(cultivarHeader, tmp, "INGENO", 6);
                    cul.CNAME = GetString(cultivarHeader, tmp, "CNAME", tmp.length() - 13);
                    cultivars.AddNew(cul);
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="FIELDS">
                else if (tmp.trim().startsWith("*FIELDS")) {
                    bField = true;
                } else if (bField && !bFieldHeader1 && !bFieldHeader2 && tmp.trim().startsWith("@")) {
                    fieldHeader1 = tmp.trim();
                    bFieldHeader1 = true;
                } else if (bField && bFieldHeader1 && !bFieldHeader2 && tmp.trim().startsWith("@")) {
                    fieldHeader2 = tmp.trim();
                    bFieldHeader2 = true;
                } else if (bField && bFieldHeader1 && !bFieldHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bField = false;
                        bFieldHeader1 = false;
                        bFieldHeader2 = false;
                        continue;
                    }
                    //@L ID_FIELD WSTA....  FLSA  FLOB  FLDT  FLDD  FLDS  FLST SLTX  SLDP  ID_SOIL    FLNAME
                    FieldDetail field = new FieldDetail();
                    field.ID_FIELD = GetString(fieldHeader1, tmp, "ID_FIELD", 8);
                    field.WSTA = GetString(fieldHeader1, tmp, "WSTA", 8);
                    field.FLSA = GetFloat(fieldHeader1, tmp, "FLSA", 5);
                    field.FLOB = GetFloat(fieldHeader1, tmp, "FLOB", 5);
                    field.FLDT = GetString(fieldHeader1, tmp, " FLDT", 5);
                    field.FLDD = GetFloat(fieldHeader1, tmp, "FLDD", 5);
                    field.FLDS = GetFloat(fieldHeader1, tmp, "FLDS", 5);
                    field.FLST = GetFloat(fieldHeader1, tmp, " FLST", 5);
                    field.SLTX = GetString(fieldHeader1, tmp, "SLTX", 5);
                    field.SLDP = GetFloat(fieldHeader1, tmp, "SLDP", 5);
                    field.ID_SOIL = GetString(fieldHeader1, tmp, "ID_SOIL", 10);
                    field.FLNAME = GetString(fieldHeader1, tmp, "FLNAME", tmp.length() - fieldHeader1.indexOf("FLNAME"));

                    fieldList.AddNew(field);
                } else if (bField && bFieldHeader1 && bFieldHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bField = false;
                        bFieldHeader1 = false;
                        bFieldHeader2 = false;
                        continue;
                    }
                    //@L ...........XCRD ...........YCRD .....ELEV .............AREA .SLEN .FLWR .SLAS FLHST FHDUR

                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        FieldDetail field = fieldList.GetAt(level);

                        field.XCRD = GetFloat(fieldHeader2, tmp, "XCRD", 15);
                        field.YCRD = GetFloat(fieldHeader2, tmp, "YCRD", 15);
                        field.ELEV = GetFloat(fieldHeader2, tmp, "ELEV", 9);
                        field.AREA = GetFloat(fieldHeader2, tmp, "AREA", 17);
                        field.SLEN = GetFloat(fieldHeader2, tmp, "SLEN", 5);
                        field.FLWR = GetFloat(fieldHeader2, tmp, "FLWR", 5);
                        field.SLAS = GetFloat(fieldHeader2, tmp, "SLAS", 5);
                        field.FLHST = GetString(fieldHeader2, tmp, "FLHST", 5);
                        field.FHDUR = GetFloat(fieldHeader2, tmp, "FHDUR", 5);
                    } catch (NumberFormatException numberFormatException) {
                    }
                }

                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="SOIL ANALYSIS">
                else if (tmp.trim().startsWith("*SOIL ANALYSIS")) {
                    bSoil = true;
                } else if (bSoil && !bSoilHeader1 && !bSoilHeader2 && tmp.trim().startsWith("@")) {
                    soilHeader1 = tmp.trim();
                    bSoilHeader1 = true;
                } else if (bSoil && bSoilHeader1 && !bSoilHeader2 && tmp.trim().startsWith("@")) {
                    soilHeader2 = tmp.trim();
                    bSoilHeader2 = true;
                } else if (bSoil && bSoilHeader1 && bSoilHeader2 && tmp.trim().startsWith("@")) {
                    bSoilHeader2 = false;
                }else if (bSoil && bSoilHeader1 && !bSoilHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bSoil = false;
                        bSoilHeader1 = false;
                        bSoilHeader2 = false;
                        continue;
                    }
                    //@A SADAT  SMHB  SMPX  SMKE  SANAME
                    SoilAnalysis soil = new SoilAnalysis();
                    soil.SADAT = GetDate(soilHeader1, tmp, "SADAT", 5);
                    soil.SMHB = GetString(soilHeader1, tmp, " SMHB", 5);
                    soil.SMPX = GetString(soilHeader1, tmp, " SMPX", 5);
                    soil.SMKE = GetString(soilHeader1, tmp, " SMKE", 5);
                    soil.SANAME = GetString(soilHeader1, tmp, "SANAME", tmp.length() - soilHeader1.indexOf("SANAME"));

                    soilAnalysis.AddAnalysis(soil);
                } else if (bSoil && bSoilHeader1 && bSoilHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bSoil = false;
                        bSoilHeader1 = false;
                        bSoilHeader2 = false;
                        continue;
                    }
                    //@A  SABL  SADM  SAOC  SANI SAPHW SAPHB  SAPX  SAKE  SASC

                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        SoilAnalysis soil = soilAnalysis.GetAnalysis(level);
                        SoilAnalysisLayer soilLayer = new SoilAnalysisLayer();

                        soilLayer.SABL = GetFloat(soilHeader2, tmp, "SABL", 5);
                        soilLayer.SADM = GetFloat(soilHeader2, tmp, "SADM", 5);
                        soilLayer.SAOC = GetFloat(soilHeader2, tmp, "SAOC", 5);
                        soilLayer.SANI = GetFloat(soilHeader2, tmp, "SANI", 5);
                        soilLayer.SAPHW = GetFloat(soilHeader2, tmp, "SAPHW", 5);
                        soilLayer.SAPHB = GetFloat(soilHeader2, tmp, "SAPHB", 5);
                        soilLayer.SAPX = GetFloat(soilHeader2, tmp, "SAPX", 5);
                        soilLayer.SAKE = GetFloat(soilHeader2, tmp, "SAKE", 5);
                        soilLayer.SASC = GetFloat(soilHeader2, tmp, "SASC", 5);
                        soil.AddLayer(soilLayer);

                    } catch (NumberFormatException numberFormatException) {
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="INITIAL CONDITIONS">
                else if (tmp.trim().startsWith("*INITIAL CONDITIONS")) {
                    bInit = true;
                } else if (bInit && !bInitHeader1 && !bInitHeader2 && tmp.trim().startsWith("@")) {
                    initHeader1 = tmp.trim();
                    bInitHeader1 = true;
                } else if (bInit && bInitHeader1 && !bInitHeader2 && tmp.trim().startsWith("@")) {
                    initHeader2 = tmp.trim();
                    bInitHeader2 = true;
                } else if (bInit && bInitHeader1 && bInitHeader2 && tmp.trim().startsWith("@")) {
                    bInitHeader2 = false;
                }else if (bInit && bInitHeader1 && !bInitHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bInit = false;
                        bInitHeader1 = false;
                        bInitHeader2 = false;
                        continue;
                    }
                    //@C   PCR ICDAT  ICRT  ICND  ICRN  ICRE  ICWD ICRES ICREN ICREP ICREP ICRID ICNAME
                    InitialCondition init = new InitialCondition();
                    init.PCR = GetString(initHeader1, tmp, "  PCR", 5);
                    init.ICDAT = GetDate(initHeader1, tmp, "ICDAT", 5);
                    init.ICRT = GetFloat(initHeader1, tmp, "ICRT", 5);
                    init.ICND = GetFloat(initHeader1, tmp, "ICND", 5);
                    init.ICRN = GetFloat(initHeader1, tmp, "ICRN", 5);
                    init.ICRE = GetFloat(initHeader1, tmp, "ICRE", 5);
                    init.ICWD = GetFloat(initHeader1, tmp, "ICWD", 5);
                    init.ICRES = GetFloat(initHeader1, tmp, "ICRES", 5);
                    init.ICREN = GetFloat(initHeader1, tmp, "ICREN", 5);
                    init.ICREP = GetFloat(initHeader1, tmp, "ICREP", 5);
                    init.ICRIP = GetFloat(initHeader1, tmp, "ICRIP", 5);
                    init.ICRID = GetFloat(initHeader1, tmp, "ICRID", 5);
                    init.ICNAME = GetString(initHeader1, tmp, "ICNAME", tmp.length() - initHeader1.indexOf("ICNAME"));

                    initialList.AddNew(init);
                } else if (bInit && bInitHeader1 && bInitHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bInit = false;
                        bInitHeader1 = false;
                        bInitHeader2 = false;
                        continue;
                    }
                    //@C  ICBL  SH2O  SNH4  SNO3

                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        InitialCondition init = initialList.GetAt(level);
                        InitialConditionApplication initApp = new InitialConditionApplication();

                        initApp.ICBL = GetFloat(initHeader2, tmp, "ICBL", 5);
                        initApp.SH2O = GetFloat(initHeader2, tmp, "SH2O", 5);
                        initApp.SNH4 = GetFloat(initHeader2, tmp, "SNH4", 5);
                        initApp.SNO3 = GetFloat(initHeader2, tmp, "SNO3", 5);
                        init.AddApp(initApp);

                    } catch (NumberFormatException numberFormatException) {
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="PLANTING DETAILS">
                else if (tmp.trim().startsWith("*PLANTING DETAILS")) {
                    bPlanting = true;

                } else if (bPlanting && !bPlantingHeader && tmp.trim().startsWith("@")) {
                    plantingHeader = tmp.trim();
                    bPlantingHeader = true;
                } else if (bPlanting && bPlantingHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bPlanting = false;
                        bPlantingHeader = false;
                        continue;
                    }
                    //@P PDATE EDATE  PPOP  PPOE  PLME  PLDS  PLRS  PLRD  PLDP  PLWT  PAGE  PENV  PLPH  SPRL                        PLNAME
                    Planting planting = new Planting();

                    planting.PDATE = GetDate(plantingHeader, tmp, "PDATE", 5);
                    planting.EDATE = GetDate(plantingHeader, tmp, "EDATE", 5);
                    planting.PPOP = GetFloat(plantingHeader, tmp, "PPOP", 5);
                    planting.PPOE = GetFloat(plantingHeader, tmp, "PPOE", 5);
                    planting.PLME = GetString(plantingHeader, tmp, " PLME", 5);
                    planting.PLDS = GetString(plantingHeader, tmp, " PLDS", 5);
                    planting.PLRS = GetFloat(plantingHeader, tmp, "PLRS", 5);
                    planting.PLRD = GetFloat(plantingHeader, tmp, "PLRD", 5);
                    planting.PLDP = GetFloat(plantingHeader, tmp, "PLDP", 5);
                    planting.PLWT = GetFloat(plantingHeader, tmp, "PLWT", 5);
                    planting.PAGE = GetFloat(plantingHeader, tmp, "PAGE", 5);
                    planting.PENV = GetFloat(plantingHeader, tmp, "PENV", 5);
                    planting.PLPH = GetFloat(plantingHeader, tmp, "PLPH", 5);
                    planting.SPRL = GetFloat(plantingHeader, tmp, "SPRL", 5);
                    planting.PLNAME = GetString(plantingHeader, tmp, "PLNAME", tmp.length() - plantingHeader.indexOf("PLNAME"));
                    plantings.AddNew(planting);
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="IRRIGATION AND WATER MANAGEMENT">
                else if (tmp.trim().startsWith("*IRRIGATION AND WATER MANAGEMENT")) {
                    bIrrig = true;
                } else if (bIrrig && !bIrrigHeader1 && !bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    irrigHeader1 = tmp.trim();
                    bIrrigHeader1 = true;
                } else if (bIrrig && bIrrigHeader1 && !bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    irrigHeader2 = tmp.trim();
                    bIrrigHeader2 = true;
                } else if (bIrrig && bIrrigHeader1 && bIrrigHeader2 && tmp.trim().startsWith("@")) {
                    bIrrigHeader2 = false;
                }else if (bIrrig && bIrrigHeader1 && !bIrrigHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bIrrig = false;
                        bIrrigHeader1 = false;
                        bIrrigHeader2 = false;
                        continue;
                    }
                    //@I  EFIR  IDEP  ITHR  IEPT  IOFF  IAME  IAMT IRNAME
                    Irrigation irrig = new Irrigation();
                    irrig.EFIR = GetFloat(irrigHeader1, tmp, "  EFIR", 5);

                    irrig.IRNAME = GetString(irrigHeader1, tmp, "IRNAME", tmp.length() - irrigHeader1.indexOf("IRNAME"));
                    irrigations.AddNew(irrig);
                } else if (bIrrig && bIrrigHeader1 && bIrrigHeader2) {
                    if (tmp.trim().isEmpty()) {
                        bIrrig = false;
                        bIrrigHeader1 = false;
                        bIrrigHeader2 = false;
                        continue;
                    }
                    //@I IDATE  IROP IRVAL
                    try {
                        Integer level = Integer.parseInt(tmp.substring(0, 2).trim()) - 1;
                        Irrigation irrig = irrigations.GetAt(level);
                        IrrigationApplication irrigApp = new IrrigationApplication();
                        try{
                            irrigApp.IDATE = GetDate(irrigHeader2, tmp, "IDATE", 5);
                        }
                        catch(Exception ex)
                        {
                            irrigApp.IDAY = GetInteger(irrigHeader2, tmp, "IDATE", 5);
                        }
                        irrigApp.IROP = GetString(irrigHeader2, tmp, " IROP", 5);
                        irrigApp.IRVAL = GetFloat(irrigHeader2, tmp, "IRVAL", 5);
                        irrig.AddApp(irrigApp);

                    } catch (NumberFormatException numberFormatException) {
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="FERTILIZERS (INORGANIC)">
                else if (tmp.trim().startsWith("*FERTILIZERS (INORGANIC)")) {
                    bFertilizer = true;

                } else if (bFertilizer && !bFertilizerHeader && tmp.trim().startsWith("@")) {
                    fertilizerHeader = tmp.trim();
                    bFertilizerHeader = true;
                } else if (bFertilizer && bFertilizerHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bFertilizer = false;
                        bFertilizerHeader = false;
                        continue;
                    }
                    //@F FDATE  FMCD  FACD  FDEP  FAMN  FAMP  FAMK  FAMC  FAMO  FOCD FERNAME
                    Fertilizer fertil = null;
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > fertilizerList.GetSize()) {
                        fertil = new Fertilizer();
                    } else {
                        fertil = fertilizerList.GetAt(level - 1);
                    }

                    FertilizerApplication fertilApp = new FertilizerApplication();


                    try {
                        fertilApp.FDATE = GetDate(fertilizerHeader, tmp, "FDATE", 5);
                    } catch (Exception e) {
                        fertilApp.FDAY = GetInteger(fertilizerHeader, tmp, "FDATE", 5);
                    }

                    fertilApp.FMCD = GetString(fertilizerHeader, tmp, " FMCD", 5);
                    fertilApp.FACD = GetString(fertilizerHeader, tmp, " FACD", 5);
                    fertilApp.FDEP = GetInteger(fertilizerHeader, tmp, "FDEP", 5);
                    fertilApp.FAMN = GetInteger(fertilizerHeader, tmp, "FAMN", 5);
                    fertilApp.FAMP = GetInteger(fertilizerHeader, tmp, "FAMP", 5);
                    fertilApp.FAMK = GetInteger(fertilizerHeader, tmp, "FAMK", 5);
                    fertilApp.FAMC = GetInteger(fertilizerHeader, tmp, "FAMC", 5);
                    fertilApp.FAMO = GetInteger(fertilizerHeader, tmp, "FAMO", 5);
                    fertilApp.FOCD = GetString(fertilizerHeader, tmp, " FOCD", 5);
                    fertil.FERNAME = GetString(fertilizerHeader, tmp, "FERNAME", tmp.length() - fertilizerHeader.indexOf("FERNAME"));
                    fertil.AddApp(fertilApp);

                    if(level > fertilizerList.GetSize()) {
                        fertilizerList.AddNew(fertil);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="RESIDUES AND ORGANIC FERTILIZER">
                else if (tmp.trim().startsWith("*RESIDUES AND ORGANIC FERTILIZER")) {
                    bOrganic = true;

                } else if (bOrganic && !bOrganicHeader && tmp.trim().startsWith("@")) {
                    organicHeader = tmp.trim();
                    bOrganicHeader = true;
                } else if (bOrganic && bOrganicHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bOrganic = false;
                        bOrganicHeader = false;
                        continue;
                    }
                    //@R RDATE  RCOD  RAMT  RESN  RESP  RESK  RINP  RDEP  RMET RENAME
                    Organic organic = null;
                    OrganicApplication organicApp = new OrganicApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > organicList.GetSize()) {
                        organic = new Organic();
                    } else {
                        organic = organicList.GetAt(level - 1);
                    }

                    try {
                        organicApp.RDATE = GetDate(organicHeader, tmp, "RDATE", 5);
                    } catch (Exception e) {
                        organicApp.RDAY = GetInteger(organicHeader, tmp, "RDATE", 5);
                    }

                    organicApp.RCOD = GetString(organicHeader, tmp, " RCOD", 5);
                    organicApp.RAMT = GetInteger(organicHeader, tmp, "RAMT", 5);
                    organicApp.RESN = GetFloat(organicHeader, tmp, "RESN", 5);
                    organicApp.RESP = GetFloat(organicHeader, tmp, "RESP", 5);
                    organicApp.RESK = GetFloat(organicHeader, tmp, "RESK", 5);
                    organicApp.RINP = GetInteger(organicHeader, tmp, "RINP", 5);
                    organicApp.RDEP = GetInteger(organicHeader, tmp, "RDEP", 5);
                    organicApp.RMET = GetString(organicHeader, tmp, " RMET", 5);
                    organic.RENAME = GetString(organicHeader, tmp, "RENAME", tmp.length() - organicHeader.indexOf("RENAME"));
                    organic.AddApp(organicApp);

                    if(level > organicList.GetSize()) {
                        organicList.AddNew(organic);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="CHEMICAL APPLICATIONS">
                else if (tmp.trim().startsWith("*CHEMICAL APPLICATIONS")) {
                    bChemical = true;

                } else if (bChemical && !bChemicalHeader && tmp.trim().startsWith("@")) {
                    chemicalHeader = tmp.trim();
                    bChemicalHeader = true;
                } else if (bChemical && bChemicalHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bChemical = false;
                        bChemicalHeader = false;
                        continue;
                    }
                    //@C CDATE CHCOD CHAMT  CHME CHDEP   CHT..CHNAME
                    Chemical chem = null;
                    ChemicalApplication chemApp = new ChemicalApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > chemicalList.GetSize()) {
                        chem = new Chemical();
                    } else {
                        chem = chemicalList.GetAt(level - 1);
                    }

                    chemApp.CDATE = GetDate(chemicalHeader, tmp, "CDATE", 5);
                    chemApp.CHCOD = GetString(chemicalHeader, tmp, "CHCOD", 5);
                    chemApp.CHAMT = GetFloat(chemicalHeader, tmp, "CHAMT", 5);
                    chemApp.CHME = GetString(chemicalHeader, tmp, " CHME", 5);
                    chemApp.CHDEP = GetInteger(chemicalHeader, tmp, "CHDEP", 5);
                    chemApp.CHT = GetString(chemicalHeader, tmp, "  CHT", 5);
                    chem.CHNAME = GetString(chemicalHeader, tmp, "CHNAME", tmp.length() - chemicalHeader.indexOf("CHNAME"));
                    chem.AddApp(chemApp);

                    if(level > chemicalList.GetSize()) {
                        chemicalList.AddNew(chem);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="TILLAGE AND ROTATIONS">
                else if (tmp.trim().startsWith("*TILLAGE AND ROTATIONS")) {
                    bTillage = true;

                } else if (bTillage && !bTillageHeader && tmp.trim().startsWith("@")) {
                    tillageHeader = tmp.trim();
                    bTillageHeader = true;
                } else if (bTillage && bTillageHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bTillage = false;
                        bTillageHeader = false;
                        continue;
                    }
                    //@T TDATE TIMPL  TDEP TNAME
                    Tillage tillage = null;
                    TillageApplication tillageApp = new TillageApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > tillageList.GetSize()) {
                        tillage = new Tillage();
                    } else {
                        tillage = tillageList.GetAt(level - 1);
                    }

                    tillageApp.TDATE = GetDate(tillageHeader, tmp, "TDATE", 5);
                    tillageApp.TIMPL = GetString(tillageHeader, tmp, "TIMPL", 5);
                    tillageApp.TDEP = GetInteger(tillageHeader, tmp, "TDEP", 5);
                    tillage.TNAME = GetString(tillageHeader, tmp, "TNAME", tmp.length() - tillageHeader.indexOf("TNAME"));
                    tillage.AddApp(tillageApp);

                    if(level > tillageList.GetSize()) {
                        tillageList.AddNew(tillage);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="ENVIRONMENT MODIFICATIONS">
                else if (tmp.trim().startsWith("*ENVIRONMENT MODIFICATIONS")) {
                    bEnvironment = true;

                } else if (bEnvironment && !bEnvironmentHeader && tmp.trim().startsWith("@")) {
                    environmentHeader = tmp.trim();
                    bEnvironmentHeader = true;
                } else if (bEnvironment && bEnvironmentHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bEnvironment = false;
                        bEnvironmentHeader = false;
                        continue;
                    }
                    //@E ODATE EDAY  ERAD  EMAX  EMIN  ERAIN ECO2  EDEW  EWIND ENVNAME
                    Environmental env = null;
                    EnvironmentApplication envApp = new EnvironmentApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > environmentals.GetSize()) {
                        env = new Environmental();
                    } else {
                        env = environmentals.GetAt(level - 1);
                    }

                    envApp.ODATE = GetDate(environmentHeader, tmp, "ODATE", 5);
                    envApp.EDAY_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "EDAY", 1));
                    envApp.EDAY = GetInteger(environmentHeader, tmp, "EDAY ", 3);
                    envApp.ERAD_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "ERAD", 1));
                    envApp.ERAD = GetFloat(environmentHeader, tmp, "ERAD ", 3);
                    envApp.EMAX_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "EMAX", 1));
                    envApp.EMAX = GetFloat(environmentHeader, tmp, "EMAX ", 3);
                    envApp.EMIN_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "EMIN", 1));
                    envApp.EMIN = GetFloat(environmentHeader, tmp, "EMIN ", 3);
                    envApp.ERAIN_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "ERAIN", 1));
                    envApp.ERAIN = GetFloat(environmentHeader, tmp, "ERAIN", 3);
                    envApp.ECO2_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "ECO2", 1));
                    envApp.ECO2 = GetFloat(environmentHeader, tmp, "ECO2 ", 3);
                    envApp.EDEW_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "EDEW", 1));
                    envApp.EDEW = GetFloat(environmentHeader, tmp, "EDEW ", 3);
                    envApp.EWIND_Fact = EnvironmentFactorList.GetAt(0, GetString(environmentHeader, tmp, "EWIND", 1));
                    envApp.EWIND = GetFloat(environmentHeader, tmp, " EWIND", 3);
                    env.ENVNAME = GetString(environmentHeader, tmp, "ENVNAME", tmp.length() - environmentHeader.indexOf("ENVNAME"));
                    env.AddApp(envApp);

                    if(level > environmentals.GetSize()) {
                        environmentals.AddNew(env);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="HARVEST DETAILS">
                else if (tmp.trim().startsWith("*HARVEST DETAILS")) {
                    bHarvest = true;

                } else if (bHarvest && !bHarvestHeader && tmp.trim().startsWith("@")) {
                    harvestHeader = tmp.trim();
                    bHarvestHeader = true;
                } else if (bHarvest && bHarvestHeader && !tmp.trim().startsWith("!")) {
                    if (tmp.trim().isEmpty() || tmp.trim().startsWith("*")) {
                        bHarvest = false;
                        bHarvestHeader = false;
                        continue;
                    }
                    //@H HDATE  HSTG  HCOM HSIZE   HPC  HBPC HNAME
                    Harvest harvest = null;
                    HarvestApplication harvestApp = new HarvestApplication();
                    Integer level = Integer.parseInt(tmp.substring(0, 2).trim());

                    if(level > harvestList.GetSize()) {
                        harvest = new Harvest();
                    } else {
                        harvest = harvestList.GetAt(level - 1);
                    }

                    try {
                        harvestApp.HDATE = GetDate(harvestHeader, tmp, "HDATE", 5);
                    } catch (Exception e) {
                        harvestApp.HDAY = GetInteger(harvestHeader, tmp, "HDATE", 5);
                    }
                    harvestApp.HSTG = GetString(harvestHeader, tmp, " HSTG", 5);
                    harvestApp.HCOM = GetString(harvestHeader, tmp, " HCOM", 5);
                    harvestApp.HSIZE = GetString(harvestHeader, tmp, "HSIZE", 5);
                    harvestApp.HPC = GetFloat(harvestHeader, tmp, "HPC", 5);
                    harvestApp.HBPC = GetFloat(harvestHeader, tmp, "HBPC", 5);
                    harvest.HNAME = GetString(harvestHeader, tmp, "HNAME", tmp.length() - harvestHeader.indexOf("HNAME"));
                    harvest.AddApp(harvestApp);

                    if(level > harvestList.GetSize()) {
                        harvestList.AddNew(harvest);
                    }
                }
                // </editor-fold>

                // <editor-fold defaultstate="collapsed" desc="SIMULATION CONTROLS">
                else if (tmp.trim().startsWith("*SIMULATION CONTROLS")) {
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
                        sim = simulationList.GetAt(level - 1);
                    }

                    tmp = Tools.PadRight(tmp, simGeneralHeader.length(), ' ');

                    sim.NYERS = GetInteger(simGeneralHeader, tmp, "NREPS", 5);
                    sim.NREPS = GetInteger(simGeneralHeader, tmp, "NREPS", 5);
                    sim.START = GetString(simGeneralHeader, tmp, "START", 5);
                    sim.SDATE = GetDate(simGeneralHeader, tmp, "SDATE", 5);
                    sim.RSEED = GetFloat(simGeneralHeader, tmp, "RSEED", 5);
                    sim.SNAME = GetString(simGeneralHeader, tmp, "SNAME", 25);
                    sim.SMODEL = GetString(simGeneralHeader, tmp, "SMODEL", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.WATER = GetString(simOptionHeader, tmp, "WATER", 5);
                    sim.NITRO = GetString(simOptionHeader, tmp, "NITRO", 5);
                    sim.SYMBI = GetString(simOptionHeader, tmp, "SYMBI", 5);
                    sim.PHOSP = GetString(simOptionHeader, tmp, "PHOSP", 5);
                    sim.POTAS = GetString(simOptionHeader, tmp, "POTAS", 5);
                    sim.DISES = GetString(simOptionHeader, tmp, "DISES", 5);
                    sim.CHEM = GetString(simOptionHeader, tmp, " CHEM", 5);
                    sim.TILL = GetString(simOptionHeader, tmp, " TILL", 5);
                    sim.CO2 = GetString(simOptionHeader, tmp, "  CO2", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.WTHER = GetString(simMethodHeader, tmp, "WTHER", 5);
                    sim.INCON = GetString(simMethodHeader, tmp, "INCON", 5);
                    sim.LIGHT = GetString(simMethodHeader, tmp, "LIGHT", 5);
                    sim.EVAPO = GetString(simMethodHeader, tmp, "EVAPO", 5);
                    sim.INFIL = GetString(simMethodHeader, tmp, "INFIL", 5);
                    sim.PHOTO = GetString(simMethodHeader, tmp, "PHOTO", 5);
                    sim.HYDRO = GetString(simMethodHeader, tmp, "HYDRO", 5);
                    sim.NSWIT = GetString(simMethodHeader, tmp, "NSWIT", 5);
                    sim.MESOM = GetString(simMethodHeader, tmp, "MESOM", 5);
                    sim.MESEV = GetString(simMethodHeader, tmp, "MESEV", 5);
                    sim.MESOL = GetString(simMethodHeader, tmp, "MESOL", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.PLANT = GetString(simManagementHeader, tmp, "PLANT", 5);
                    sim.IRRIG = GetString(simManagementHeader, tmp, "IRRIG", 5);
                    sim.FERTI = GetString(simManagementHeader, tmp, "FERTI", 5);
                    sim.RESID = GetString(simManagementHeader, tmp, "RESID", 5);
                    sim.HARVS = GetString(simManagementHeader, tmp, "HARVS", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.FNAME = GetString(simOutputHeader, tmp, "FNAME", 5);
                    sim.OVVEW = GetString(simOutputHeader, tmp, "OVVEW", 5);
                    sim.SUMRY = GetString(simOutputHeader, tmp, "SUMRY", 5);
                    sim.FROPT = GetInteger(simOutputHeader, tmp, "FROPT", 5);
                    sim.GROUT = GetString(simOutputHeader, tmp, "GROUT", 5);
                    sim.CAOUT = GetString(simOutputHeader, tmp, "CAOUT", 5);
                    sim.WAOUT = GetString(simOutputHeader, tmp, "WAOUT", 5);
                    sim.NIOUT = GetString(simOutputHeader, tmp, "NIOUT", 5);
                    sim.MIOUT = GetString(simOutputHeader, tmp, "MIOUT", 5);
                    sim.DIOUT = GetString(simOutputHeader, tmp, "DIOUT", 5);
                    sim.VBOSE = GetString(simOutputHeader, tmp, "VBOSE", 5);
                    sim.CHOUT = GetString(simOutputHeader, tmp, "CHOUT", 5);
                    sim.OPOUT = GetString(simOutputHeader, tmp, "OPOUT", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    try{
                        sim.PFRST = GetDate(simPlantingHeader, tmp, "PFRST", 5);
                    }
                    catch(Exception ex) {
                        sim.PFRST_Day = GetInteger(simPlantingHeader, tmp, "PFRST", 2);
                    }
                    try{
                        sim.PLAST = GetDate(simPlantingHeader, tmp, "PLAST", 5);
                    }
                    catch(Exception ex) {
                        sim.PLAST_Day = GetInteger(simPlantingHeader, tmp, "PLAST", 2);
                    }
                    sim.PH2OL = GetFloat(simPlantingHeader, tmp, "PH2OL", 5);
                    sim.PH2OU = GetFloat(simPlantingHeader, tmp, "PH2OU", 5);
                    sim.PH2OD = GetFloat(simPlantingHeader, tmp, "PH2OD", 5);
                    sim.PSTMX = GetFloat(simPlantingHeader, tmp, "PSTMX", 5);
                    sim.PSTMN = GetFloat(simPlantingHeader, tmp, "PSTMN", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.IMDEP = GetFloat(simIrrigationHeader, tmp, "IMDEP", 5);
                    sim.ITHRL = GetFloat(simIrrigationHeader, tmp, "ITHRL", 5);
                    sim.ITHRU = GetFloat(simIrrigationHeader, tmp, "ITHRU", 5);
                    sim.IROFF = GetString(simIrrigationHeader, tmp, "IROFF", 5);
                    sim.IMETH = GetString(simIrrigationHeader, tmp, "IMETH", 5);
                    sim.IRAMT = GetFloat(simIrrigationHeader, tmp, "IRAMT", 5);
                    sim.IREFF = GetFloat(simIrrigationHeader, tmp, "IREFF", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.NMDEP = GetFloat(simNitrogenHeader, tmp, "NMDEP", 5);
                    sim.NMTHR = GetFloat(simNitrogenHeader, tmp, "NMTHR", 5);
                    sim.NAMNT = GetFloat(simNitrogenHeader, tmp, "NAMNT", 5);
                    sim.NCODE = GetString(simNitrogenHeader, tmp, "NCODE", 5);
                    sim.NAOFF = GetString(simNitrogenHeader, tmp, "NAOFF", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.RIPCN = GetFloat(simResidueHeader, tmp, "RIPCN", 5);
                    sim.RTIME = GetFloat(simResidueHeader, tmp, "RTIME", 5);
                    sim.RIDEP = GetFloat(simResidueHeader, tmp, "RIDEP", 5);

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
                        sim = simulationList.GetAt(level - 1);
                    }

                    sim.HFRST = GetFloat(simHarvestHeader, tmp, "HFRST", 5);
                    try{
                        sim.HLAST = GetDate(simHarvestHeader, tmp, "HLAST", 5);
                    }
                    catch(Exception ex) {
                        sim.HLAST_Day = GetInteger(simHarvestHeader, tmp, "HLAST", 5);
                    }
                    sim.HPCNP = GetFloat(simHarvestHeader, tmp, "HPCNP", 5);
                    sim.HPCNR = GetFloat(simHarvestHeader, tmp, "HPCNR", 5);

                    if(level > simulationList.GetSize()) {
                        simulationList.AddNew(sim);
                    }
                    nSimulation = -1;
                }
                // </editor-fold>
            }
        } catch (IOException ex) {
            Logger.getLogger(FileX.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(FileX.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fReader.close();
        } catch (IOException ex) {
            Logger.getLogger(FileX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static Float GetFloat(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Float val = null;

        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    val = Float.parseFloat(tmp);
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
        return val;
    }

    private static Date GetDate(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field) + field.length() - fieldLength - 1;
        Date val = null;

        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    Integer year = Integer.parseInt(tmp.substring(0, 2));
                    if (year >= 80) {
                        year += 1900;
                    } else {
                        year += 2000;
                    }
                    int day = Integer.parseInt(tmp.substring(2, 5));

                    int month[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
                    month[1] += ((year % 4) == 0) ? 1 : 0;

                    int dayCount = 0;
                    int nDay = 0;
                    int nMonth = 0;
                    for(int i = 0;i < 12;i++) {
                        if(day <= (dayCount + month[i]))
                        {
                            nDay = day - dayCount;
                            nMonth = i;
                            break;
                        }
                        dayCount += month[i];
                    }

                    //val = new Date(year, nMonth, nDay);
                    Calendar ca = Calendar.getInstance(Locale.US);
                    ca.set(year, nMonth, nDay);
                    val = ca.getTime();
                } catch (NumberFormatException numberFormatException) {
                    throw numberFormatException;
                }
            }
        }
        return val;
    }
    
    private static Integer GetInteger(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(" " + field) + field.length() - fieldLength;
        Integer val = null;
        
        if(start >= 0) {
            int stop = start + fieldLength + 1;

            String tmp = value.substring(start,stop).trim();

            if(!tmp.equals("-99")){
                try {
                    val = Integer.parseInt(tmp);
                } catch (NumberFormatException numberFormatException) {
                }
            }
        }
        return val;
    }

    private static String GetString(String Header, String value, String field, int fieldLength) {
        int start = Header.indexOf(field);
        String val = null;
        
        if(start >= 0) {
            int stop = start + fieldLength;

            String tmp = value.substring(start,stop).trim();


            if(!tmp.equals("-99")) val = tmp;
        }
        return val;
    }
}
