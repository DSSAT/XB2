/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

/**
 *
 * @author Jazzy
 */
public class Cultivar extends Crop {
    public String CulCode;
    public String CulName;

    public Cultivar()
    {

    }

    public Cultivar(Crop crop) {
        CropCode = crop.CropCode;
        CropName = crop.CropName;
    }
}
