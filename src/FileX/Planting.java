/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileX;

import java.util.Date;

/**
 *
 * @author Jazzy
 */
public class Planting implements Cloneable {
    public Planting(String PLNAME)
    {
        this.PLNAME = PLNAME;
    }
    
    public Planting()
    {
    }

    public Date PDATE;
    public Date EDATE;
    public Float PPOP;
    public Float PPOE;
    public String PLME;
    public String PLDS;
    public Float PLRS;
    public Float PLRD;
    public Float PLDP;
    public Float PLWT;
    public Float PAGE;
    public Float PENV;
    public Float PLPH;
    public Float SPRL;
    public String PLNAME;
    
    public Planting clone() throws CloneNotSupportedException{
        return (Planting) super.clone();
    }
}
