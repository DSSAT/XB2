package xbuild.Components;

import java.lang.reflect.Constructor;
import javax.swing.JInternalFrame;

/**
 *
 * @author Jazzy
 */
public class XInternalFrame {
    
    
    public static JInternalFrame newInstance(String frameName, String nodeName){
        try{
            
            Class<?> clazz = Class.forName("xbuild." + frameName);
            Constructor<?> ctor = clazz.getConstructor();
            Object[] object = null;            
            JInternalFrame instance = (JInternalFrame) ctor.newInstance(object);
            
            return instance;
        }
        catch(Exception ex) {
            try {
                Class<?> clazz = Class.forName("xbuild." + frameName);

                Constructor<?> ctor = clazz.getConstructor(String.class);
                Object[] object = new Object[]{nodeName};

                JInternalFrame instance = (JInternalFrame) ctor.newInstance(object);

                return instance;
            } catch (Exception e) {
                String m = e.getMessage();

            }
        }
        return null;
    }
}
