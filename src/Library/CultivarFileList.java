/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.util.Hashtable;

/**
 *
 * @author Jazzy
 */
public class CultivarFileList {

    private static Hashtable cultivars = new Hashtable();

    public static void AddNew(String cropCode, String fileName)
    {
        cultivars.put(cropCode, fileName);
    }

    public static String GetCultivarFile(Crop crop)
    {
        String fileName = null;
        try {
            fileName = (String) cultivars.get(crop.CropCode);
        } catch (Exception e) {
        }
        return fileName;
    }
}
