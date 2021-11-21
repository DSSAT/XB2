/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package FileX;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class SoilAnalysis implements Cloneable {
     protected Vector  soilLayer = new Vector();
     public Date SADAT;
     public String SMHB;;
     public String SMPX;
     public String SMKE;
     public String SANAME;

     public SoilAnalysis() {
     }

     public SoilAnalysis(String SANAME)
     {
         this.SANAME = SANAME;
     }

     public void AddLayer(SoilAnalysisLayer sLayer)
    {
        soilLayer.add(sLayer);
    }

    public void RemoveLayer(int level)
    {
        soilLayer.remove(level);
    }

    public void SetAt(int level, SoilAnalysisLayer sLayer)
    {
        soilLayer.set(level, sLayer);
    }

    public SoilAnalysisLayer[] GetLayers()
    {
        return (SoilAnalysisLayer[]) soilLayer.toArray();
    }

    public SoilAnalysisLayer GetLayer(int level)
    {
        return (SoilAnalysisLayer)soilLayer.get(level);
    }

    public void ClearLayer()
    {
        soilLayer.clear();
    }

    public int GetSize()
    {
        return soilLayer.size();
    }
    
    public SoilAnalysis clone() throws CloneNotSupportedException{
        return (SoilAnalysis)super.clone();
    }
}
