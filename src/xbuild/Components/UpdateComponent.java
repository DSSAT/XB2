/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

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
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        field.setAccessible(true);
        try {
            field.set(model, value);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
