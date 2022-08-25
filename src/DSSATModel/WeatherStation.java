/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DSSATModel;

/**
 *
 * @author Jazzy
 */
public class WeatherStation {
    public String Code;
    public String StationName;
    public WstaType Type;
    public int Begin;
    public int Number;
    
    @Override
    public String toString(){
        return StationName;
    }
}
