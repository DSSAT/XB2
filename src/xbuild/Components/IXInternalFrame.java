package xbuild.Components;

import javax.swing.JInternalFrame;
import xbuild.Events.XEventListener;

/**
 *
 * @author Jazz
 */
public abstract class IXInternalFrame extends JInternalFrame {
    protected XEventListener l;
    public void updatePanelName(String name){
        
    }
    
    public void updatePanelList(){
        
    }
    
    protected int getLevel(String nodeName){
        String[] level1 = nodeName.split(":");
        String[] level2 = level1[0].split(" ");
        
        return Integer.parseInt(level2[1]);
    }
    
    protected String getDescription(String nodeName){
        String[] level1 = nodeName.split(":");
        
        return level1[1].trim();
    }
    
    public void addMyEventListener(XEventListener l) {
        if(this.l == null)
            this.l = l;
    } 
}
