package xbuild.Events;

import java.util.EventObject;

/**
 *
 * @author JAZZJAIKLA
 */
public class LevelSelectionChangedEvent extends EventObject {
 
    private String managementName;
    private int level;
    
    public LevelSelectionChangedEvent(Object source, String managementName, int level){
        super(source);
        
        this.managementName = managementName;
        this.level = level;
    }
    
    public String getManagementName(){
        return managementName;
    }
    
    public int getLevel(){
        return level;
    }
}
