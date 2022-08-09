package xbuild.Events;

import java.util.EventObject;

/**
 *
 * @author Jazz
 */
public class UpdateLevelEvent extends EventObject {
    private String name;
    private String parent;
    private int row;
    
    public UpdateLevelEvent(Object o, String parent, String name, int row){
        super(o);
        this.name = name;
        this.parent = parent;
        this.row = row;
    }
    
    public String getName(){
        return name;
    }
    
    public String getParent(){
        return parent;
    }
    
    public int getRow(){
        return row;
    }
}
