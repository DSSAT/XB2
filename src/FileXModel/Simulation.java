/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.Date;

/**
 *
 * @author Jazzy
 */
public class Simulation implements Cloneable {
    public String SNAME;

    public Simulation(String SNAME)
    {
        this.SNAME = SNAME;
        
        this.FNAME = "N";
        this.OVVEW = "Y";
        this.SUMRY = "Y";
        this.FROPT = null;
        this.GROUT = "Y";
        this.CAOUT = "Y";
        this.WAOUT = "Y";
        this.NIOUT = "Y";
        this.MIOUT = "Y";
        this.DIOUT = "Y";
        this.VBOSE = "Y";
        this.CHOUT = "Y";
        this.OPOUT = "Y";
        this.FMOPT = "A";
        
        this.WATER = "N";
        this.NITRO = "N";
        this.SYMBI = "N";
        this.PHOSP = "N";
        this.POTAS = "N";
        this.DISES = "N";
        this.CHEM = "N";
        this.TILL = "N";
        this.CO2 = "W";
        
        this.WTHER = "M";
        this.INCON = "M";
        this.LIGHT = null;
        this.EVAPO = "F";
        this.INFIL = "R";
        this.PHOTO = "R";
        this.HYDRO = "R";
        this.NSWIT = null;
        this.MESOM = "P";
        this.MESEV = "R";
        this.MESOL = "1";
        
        // <editor-fold defaultstate="collapsed" desc="MANAGEMENT">
        this.PLANT = "R";
        this.IRRIG = "A";
        this.FERTI = "R";
        this.RESID = "N";
        this.HARVS = "M";

        // <editor-fold defaultstate="collapsed" desc="PLANTING">
        this.PFRST = null;
        this.PLAST = null;
        this.PFRST_Day = null;
        this.PLAST_Day = null;
        this.PH2OL = null;
        this.PH2OU = null;
        this.PH2OD = null;
        this.PSTMX = null;
        this.PSTMN = null;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="IRRIGATION">
        this.IMDEP = 50f;
        this.ITHRL = 50f;
        this.ITHRU = 100f;
        this.IROFF = "06";
        this.IMETH = "IR001";
        this.IRAMT = null;
        this.IREFF = 1f;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="NITROGEN">
        this.NMDEP = null;
        this.NMTHR = null;
        this.NAMNT = null;
        this.NCODE = null;
        this.NAOFF = null;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="RESIDUES">
        this.RIPCN = 100F;
        this.RTIME = 1F;
        this.RIDEP = 20F;
        // </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="HARVEST">
        this.HFRST = null;
        this.HLAST = null;
        this.HLAST_Day = null;
        this.HPCNP = null;
        this.HPCNR = null;
        // </editor-fold>

    // </editor-fold>
    }

    public Simulation()
    {
    }

    // <editor-fold defaultstate="collapsed" desc="GENERAL">
    public Integer NYERS;
    public Integer NREPS;
    public String START;
    public Date SDATE;
    public Float RSEED;
    //public String SNAME;
    public String SMODEL;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="OPTIONS">
    public String WATER;
    public String NITRO;
    public String SYMBI;
    public String PHOSP;
    public String POTAS;
    public String DISES;
    public String CHEM;
    public String TILL;
    public String CO2;// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="METHODS">
    public String WTHER;
    public String INCON;
    public String LIGHT;
    public String EVAPO;
    public String INFIL;
    public String PHOTO;
    public String HYDRO;
    public String NSWIT;
    public String MESOM;
    public String MESEV;
    public String MESOL;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="MANAGEMENT">
    public String PLANT;
    public String IRRIG;
    public String FERTI;
    public String RESID;
    public String HARVS;

    // <editor-fold defaultstate="collapsed" desc="PLANTING">
    public Date PFRST;
    public Date PLAST;
    public Integer PFRST_Day;
    public Integer PLAST_Day;
    public Float PH2OL;
    public Float PH2OU;
    public Float PH2OD;
    public Float PSTMX;
    public Float PSTMN;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="IRRIGATION">
    public Float IMDEP;
    public Float ITHRL;
    public Float ITHRU;
    public String IROFF;
    public String IMETH;
    public Float IRAMT;
    public Float IREFF;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="NITROGEN">
    public Float NMDEP;
    public Float NMTHR;
    public Float NAMNT;
    public String NCODE;
    public String NAOFF;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="RESIDUES">
    public Float RIPCN = 100F;
    public Float RTIME = 1F;
    public Float RIDEP = 20F;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="HARVEST">
    public Float HFRST;
    public Date HLAST;
    public Integer HLAST_Day;
    public Float HPCNP;
    public Float HPCNR;
    // </editor-fold>

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="OUTPUTS">
    public String FNAME;    
    public String OVVEW;
    public String SUMRY;
    public Integer FROPT;
    public String GROUT;
    public String CAOUT;
    public String WAOUT;
    public String NIOUT;
    public String MIOUT;
    public String DIOUT;
    public String VBOSE;
    public String CHOUT;
    public String OPOUT;
    public String FMOPT;
    // </editor-fold>
    
    public Simulation clone() throws CloneNotSupportedException {
        return (Simulation)super.clone();    // return shallow copy
    }
}
