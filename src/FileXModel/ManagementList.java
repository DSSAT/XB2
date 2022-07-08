package FileXModel;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public abstract class ManagementList {
    protected ArrayList<IModelXBase> modelList = new ArrayList<>();
    
    public abstract void AddNew(String name);
    public abstract IModelXBase Clone(String sourceName, String newName);
    
    public void AddNew(IModelXBase model){
        modelList.add(model);
    }
    
    public ArrayList<IModelXBase> GetAll(){
        return modelList;
    }
    
    public int GetSize(){
        return modelList.size();
    }
    
    public void RemoveAt(String name)
    {
        for(IModelXBase model : GetAll()){
            if(model.GetName().equals(name)){
                modelList.remove(model);
                break;
            }
        }
    }
    
    public IModelXBase GetAt(String name)
    {
        name = ExtractDescription(name);
        for(IModelXBase model : GetAll()){
            if(model.GetName().equals(name)){
                return model;
            }
        }
        return null;
    }
    
    public IModelXBase GetAt(int level)
    {
        return modelList.get(level);
    }
    
    public int GetLevel(String name)
    {
        int level = -1;
        for(IModelXBase model : GetAll()){
            level++;
            if(model.GetName().equals(name)){
                break;
            }
        }
        return level;
    }
    
    public String GetCopyName(String name){
        int max = 0;
        for(IModelXBase model : GetAll()){
            if(model.GetName().startsWith(name)){
                max++;
            }
        }
        
        return name + " (" + (max + 1) + ")";
    }
    
    public void Rename(String oldName, String newName){
        for(IModelXBase model : GetAll()){
            if(model.GetName().equals(oldName)){
                model.SetName(newName);
                break;
            }
        }
    }
    
    public String ExtractDescription(String name){
        String description = name;
 
        if(name.matches("^Level\\s[0-9]+?[?::].*")){
            description = name.split(":")[1].trim();
        }
        
        return description;
    }
    
    public int ExtractLevel(String name){
        int level = -1;
        if(name.matches("^Level\\s[0-9]+?[?::].*")){
            level = Integer.parseInt(name.split(":")[0].split(" ")[1].trim());
        }
        
        return level;
    } 
}
