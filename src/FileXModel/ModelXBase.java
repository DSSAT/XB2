package FileXModel;

/**
 *
 * @author Jazz
 */
public abstract class ModelXBase implements IModelXBase {
    
    private Integer level;
    
    public Integer GetLevel(){
        return level;
    }
    
    public void SetLevel(int level){
        this.level = level;
    }
}
