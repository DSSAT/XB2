package xbuild.Events;

import java.util.EventObject;

/**
 *
 * @author Jazz
 */
public class NewFrameEvent extends EventObject {
    private final String currentFrame;
    private final MenuDirection direction;
    
    public NewFrameEvent(Object o, String currentFrame, MenuDirection direction){
        super(o);
        this.currentFrame = currentFrame;
        this.direction = direction;
    }
    
    public MenuDirection getDirection(){
        return direction;
    }
    
    public String getCurrentFrameName(){
        return currentFrame;
    }
}
