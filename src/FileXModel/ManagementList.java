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
    
    public void RemoveAt(int level)
    {
        modelList.remove(level);
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
    
    public Boolean MoveUp(int level){
        if(level > 0 && GetSize() > 1){
//            ArrayList<IModelXBase> list = new ArrayList<>();
//            for (int i = 0; i < level - 1; i++) {
//                list.add(modelList.get(i));
//            }
//            list.add(modelList.get(level));
//            list.add(modelList.get(level - 1));
//
//            for (int i = level + 1; i < GetSize(); i++) {
//                list.add(modelList.get(i));
//            }
//            
//            modelList = list;

            IModelXBase tmp = modelList.get(level);
            IModelXBase tmp2 = modelList.get(level - 1);
            modelList.set(level, tmp2);
            modelList.set(level - 1, tmp);
            return true;
        }
        return false;
    }
    
    public Boolean MoveDown(int level){
        if(level < GetSize() - 1 && GetSize() > 1){
//            ArrayList<IModelXBase> list = new ArrayList<>();
//            for (int i = 0; i < level - 1; i++) {
//                list.add(modelList.get(i));
//            }
//            list.add(modelList.get(level));
//            list.add(modelList.get(level - 1));
//
//            for (int i = level + 1; i < GetSize(); i++) {
//                list.add(modelList.get(i));
//            }
//            
//            modelList = list;

            IModelXBase tmp = modelList.get(level);
            IModelXBase tmp2 = modelList.get(level + 1);
            modelList.set(level, tmp2);
            modelList.set(level + 1, tmp);
            return true;
        }
        return false;
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
