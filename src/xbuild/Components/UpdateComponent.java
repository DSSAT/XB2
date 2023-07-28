/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import xbuild.Events.FieldUpdateEvent;
import xbuild.Events.XEventListener;

/**
 *
 * @author Jazz
 */
public class UpdateComponent {
    private static XEventListener eventListener;
    
    public static void setEventListener(XEventListener eventListener){
        if (UpdateComponent.eventListener == null) {
            UpdateComponent.eventListener = eventListener;
        }
    }
    
    public static void updateModel(Object component,Object model, String fieldName, Object value){
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
                    if(field.get(model) != Utils.ParseFloat(value)){
                        field.set(model, Utils.ParseFloat(value));
                        eventListener.myAction(new FieldUpdateEvent(component));
                    }
                }
                else if(field.getType() == Integer.class){
                    if(field.get(model) != Utils.ParseInteger(value)){
                        field.set(model, Utils.ParseInteger(value));
                        eventListener.myAction(new FieldUpdateEvent(component));
                    }
                }
                else if(field.getType() == ExperimentType.class){
                    if(field.get(model) != ExperimentType.valueOf(value.toString())){
                        field.set(model, ExperimentType.valueOf(value.toString()));
                        eventListener.myAction(new FieldUpdateEvent(component));
                    }
                }
                else if(field.getType() == Date.class){
                    if(field.get(model) == null 
                            || ((Date)field.get(model)).getDate() != ((Date)value).getDate()
                            || ((Date)field.get(model)).getMonth()!= ((Date)value).getMonth()
                            || ((Date)field.get(model)).getYear()!= ((Date)value).getYear()){
                        field.set(model, value);
                        eventListener.myAction(new FieldUpdateEvent(component));
                    }
                }
                else{ 
                    if(field.get(model) == null || !field.get(model).equals(value)){
                        field.set(model, value);
                        eventListener.myAction(new FieldUpdateEvent(component));
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Logger.getLogger(UpdateComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
