/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import java.util.ArrayList;

/**
 *
 * @author Jazzy
 */
public class SimulationMethodWeather {

    private static ArrayList<String[]> sims = new ArrayList<>();

    public static void Clear() {
        sims.clear();
    }

    public static void AddNew(String Code, String Description){
        sims.add(new String[] {Code, Description});
    }

    public static int GetSize()
    {
        return sims.size();
    }

    public static String[] GetAt(String Code) {

        for (String[] s : sims) {
            if (s[0].equalsIgnoreCase(Code)) {
                return s;
            }
        }
        return null;
    }

    public static String[] GetAt(int n)
    {
        return sims.get(n);
    }

    public static ArrayList<String[]> GetAll(WstaType wstaType)
    {
        ArrayList<String[]> simList = new ArrayList<>();
        if (wstaType == null) {
            simList.addAll(sims);
            return simList;
        }
        switch(wstaType){
            case WTH:
                addIfPresent(simList, GetAt("M"));
                break;
            case WTG:
                addIfPresent(simList, GetAt("G"));
                break;
            case CLI:
                addIfPresent(simList, GetAt("W"));
                addIfPresent(simList, GetAt("S"));
                break;
        }
        
        return simList;
    }

    private static void addIfPresent(ArrayList<String[]> simList, String[] item) {
        if (item != null) {
            simList.add(item);
        }
    }
}
