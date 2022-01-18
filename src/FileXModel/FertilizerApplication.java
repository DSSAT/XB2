/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazzy
 */
public class FertilizerApplication implements Cloneable {
    public Date FDATE;
    public Integer FDAY;
    public String FMCD;
    public String FACD;
    public Integer FDEP;
    public Integer FAMN;
    public Integer FAMP;
    public Integer FAMK;
    public Integer FAMC;
    public Integer FAMO;
    public String FOCD;
    
    public FertilizerApplication Clone(){
        try {
            return (FertilizerApplication) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(FertilizerApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
