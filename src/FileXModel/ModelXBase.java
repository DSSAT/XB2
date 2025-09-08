package FileXModel;

/**
 *
 * @author Jazz
 */
public abstract class ModelXBase implements IModelXBase, Cloneable {
    
    private boolean enabled;
    private Integer level;
    
    public Integer GetLevel(){
        return level;
    }
    
    public void SetLevel(int level){
        this.level = level;
    }
    
    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }
    
    public boolean getDisabled(){
        return enabled;
    }
    
    public ModelXBase Clone() throws CloneNotSupportedException{
        return (ModelXBase) clone();
    }
}
