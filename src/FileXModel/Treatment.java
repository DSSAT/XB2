/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazzy
 */
public class Treatment implements Cloneable {
    public Integer N;
    
    public String R;
    public String O;
    public String C;
    
    public String TNAME;
    public Integer CU;
    public Integer FL;
    public Integer SA;
    public Integer IC;
    public Integer MP;
    public Integer MI;
    public Integer MF;
    public Integer MR;
    public Integer MC;
    public Integer MT;
    public Integer ME;
    public Integer MH;
    public Integer SM;
    
    public Treatment Clone(){
        try {
            return (Treatment) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Treatment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    } 
}
