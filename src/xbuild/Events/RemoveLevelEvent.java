/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package xbuild.Events;

import java.util.EventObject;

/**
 *
 * @author PCMIWS16
 */
public class RemoveLevelEvent extends EventObject {
    private String name;
    private String parent;
    
    public RemoveLevelEvent(Object o, String parent, String name){
        super(o);
        this.name = name;
        this.parent = parent;
    }
    
    public String getName(){
        return name;
    }
    
    public String getParent(){
        return parent;
    }
}
