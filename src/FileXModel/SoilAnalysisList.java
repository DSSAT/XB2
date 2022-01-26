/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileXModel;

/**
 *
 * @author Jazzy
 */
public class SoilAnalysisList extends ManagementList {   
       
    @Override
    public void AddNew(String sName)
    {
        modelList.add(new SoilAnalysis(sName));
    }
    
    @Override
    public IModelXBase Clone(String sourceName, String newName){
        SoilAnalysis source = (SoilAnalysis)GetAt(sourceName);
        SoilAnalysis newSource = null;
        
        try{            
            newSource = new SoilAnalysis();
            newSource.SANAME = newName;
            newSource.SADAT = source.SADAT;
            newSource.SMHB = source.SMHB;
            newSource.SMPX = source.SMPX;
            newSource.SMKE = source.SMKE;
            
            for(SoilAnalysisLayer c : source.GetLayers()) {
                SoilAnalysisLayer ca = (SoilAnalysisLayer) c.Clone();           
                newSource.AddLayer(ca);
            }
        }
        catch(Exception ex){
            
        }
        
        return newSource;
    }    
}
