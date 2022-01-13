/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

import java.util.Vector;

/**
 *
 * @author Jazzy
 */
public class EnvironmentFactorList {
    //protected static EnvironmentFactor factors[];

    protected static Vector factors = new Vector();

    //public static void setSize(int size)
    //{
    //    EnvironmentFactor factorList[] = new EnvironmentFactor[size];

        //EnvironmentFactorList.factors = factorList;
    //}
    
    public static void Clear()
    {
        factors.clear();
    }

    public static void Add(EnvironmentFactor factor)
    {
        factors.add(factor);
    }

    public static int size()
    {
        return factors.size();
    }

    public static EnvironmentFactor GetAt(int index)
    {
        return (EnvironmentFactor) factors.get(index);
    }

    public static EnvironmentFactor GetAt(int col,String strGet)
    {
        EnvironmentFactor envFact = null;

        for(int n = 0;n < factors.size();n++)
        {
            if(col == 0 && ((EnvironmentFactor)factors.get(n)).Code.equals(strGet))
                envFact = (EnvironmentFactor)factors.get(n);
            else if(col == 1 && ((EnvironmentFactor)factors.get(n)).Description.equals(strGet))
                envFact = (EnvironmentFactor)factors.get(n);
        }

        return envFact;
    }
}
