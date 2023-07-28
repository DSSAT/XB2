package xbuild.Events;

import java.util.EventObject;

/**
 *
 * @author JAZZJAIKLA
 */
public class SelectionEvent extends EventObject {
    
    private boolean canDel;
    
    public SelectionEvent(Object source, boolean canDel) {
        super(source);
        
        this.canDel = canDel;
    }
    
    public boolean canDelete(){
        return canDel;
    }    
}
