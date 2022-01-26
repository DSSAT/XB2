/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class Cultivar implements IModelXBase {
    public String CR;
    public String INGENO;
    public String CNAME;

    @Override
    public String GetName() {
        return this.CNAME == null ? "" : this.CNAME;
    }
    
    @Override
    public void SetName(String name) {
        CNAME = name;
    }
}
