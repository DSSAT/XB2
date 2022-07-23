/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import Extensions.Utils;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jazz
 */
public class UpdateComponent {
    public static void updateModel(Object model, String fieldName, Object value){
        Field field = null;
        try {
            field = model.getClass().getDeclaredField(fieldName);
        } catch (NoSuchFieldException | SecurityException ex) {
            Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        if(field != null){
            field.setAccessible(true);
            try {
                if(field.getType() == Float.class){
                    field.set(model, Utils.ParseFloat(value));
                }
                else if(field.getType() == Integer.class){
                    field.set(model, Utils.ParseInteger(value));
                }
                else 
                    field.set(model, value);
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
