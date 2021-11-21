/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Library;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Jazzy
 */
public class SimulationMethodInfil {

    private static Hashtable sim = new Hashtable();

    public static void Clear() {
        sim.clear();
    }

    public static void AddNew(String Code, String Description){
        sim.put(Code, Description);
    }

    public static int GetSize()
    {
        return sim.size();
    }

    public static String[] GetAt(String Code) {

        String s[] = new String[2];
        try
        {
            s[1] = (String) sim.get(Code);
            s[0] = Code;
        }
        catch(Exception ex){

        }
        return s;
    }

    public static String[] GetAt(int n)
    {
        String s[] = new String[2];
        Set set = sim.entrySet();
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            if(i == n) {
                Map.Entry entry = (Map.Entry) it.next();
                s[1] = (String) entry.getValue();
                s[0] = (String) entry.getKey();
            }
        }
        return s;
    }

    public static String[][] GetAll()
    {
        String s[][] = new String[sim.size()][2];
        Set set = sim.entrySet();
        Iterator it = set.iterator();
        int i = 0;
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            s[i][0] = (String) entry.getKey();
            s[i][1] = (String) entry.getValue();
            i++;
        }
        return s;
    }
}
