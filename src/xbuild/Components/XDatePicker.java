/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import java.util.Date;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author PCMIWS04
 */
public class XDatePicker extends JXDatePicker {
 
    private Object model;
    private String fieldName;
    private Date value;
    
    public XDatePicker(Object model, String fieldName, Date value) {
        this.model = model;
        this.fieldName = fieldName;
        this.value = value;
        
        if(value != null)
            this.setDate(value);
        
        setFocusLost();
    }
    
    private void setFocusLost(){
        this.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performFocusLost(evt);
            }
        });
    }

    public void performFocusLost(java.awt.event.ActionEvent evt) {
        this.value = this.getDate();

        UpdateComponent.updateModel(this.model, this.fieldName, this.value);
    }
}
