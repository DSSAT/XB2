/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import DSSATModel.EnvironmentFactor;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazzy
 */
public class EnvironmentApplication implements Cloneable {
    public Date ODATE;
    public Integer EDAY;
    public EnvironmentFactor EDAY_Fact;
    public Float ERAD;
    public EnvironmentFactor ERAD_Fact;
    public Float EMAX;
    public EnvironmentFactor EMAX_Fact;
    public Float EMIN;
    public EnvironmentFactor EMIN_Fact;
    public Float ERAIN;
    public EnvironmentFactor ERAIN_Fact;
    public Float ECO2;
    public EnvironmentFactor ECO2_Fact;
    public Float EDEW;
    public EnvironmentFactor EDEW_Fact;
    public Float EWIND;
    public EnvironmentFactor EWIND_Fact;
    
    public EnvironmentApplication Clone(){
        try {
            return (EnvironmentApplication) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(EnvironmentApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
