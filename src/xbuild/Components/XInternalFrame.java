package xbuild.Components;

import java.lang.reflect.Constructor;

/**
 *
 * @author Jazzy
 */
public class XInternalFrame {
    
    
    public static IXInternalFrame newInstance(String frameName, String nodeName){
        try{
            
            Class<?> clazz = Class.forName("xbuild." + frameName);
            Constructor<?> ctor = clazz.getConstructor();
            Object[] object = null;            
            IXInternalFrame instance = (IXInternalFrame) ctor.newInstance(object);
            
            return instance;
        }
        catch(Exception ex) {
            try {
                Class<?> clazz = Class.forName("xbuild." + frameName);

                Constructor<?> ctor = clazz.getConstructor(String.class);
                Object[] object = new Object[]{nodeName};

                IXInternalFrame instance = (IXInternalFrame) ctor.newInstance(object);

                return instance;
            } catch (Exception e) {
                String m = e.getMessage();

            }
        }
        return null;
    }
}
