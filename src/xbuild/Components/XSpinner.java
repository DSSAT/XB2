/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import Extensions.Utils;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author Jazzy
 */
public class XSpinner extends JSpinner {
    
    private JTextField textField;
    private Integer value;
    private Object model;
    private String fieldName;
    
    public XSpinner(Object model, String fieldName, Integer value){
        DefaultEditor editor = (DefaultEditor)this.getEditor();
        this.textField = (JTextField)editor.getTextField();
        this.model = model;
        this.fieldName = fieldName;
        this.value = value;
        if(value == null)
            setBlank();
        else
            this.setValue(value);
        
        setFocusLost();
    }
    
    private void setFocusLost(){
        this.textField.addFocusListener(new java.awt.event.FocusAdapter(){
            public void focusLost(java.awt.event.FocusEvent evt) {
                performFocusLost(evt);
            }
        });
    }
    
    public void performFocusLost(java.awt.event.FocusEvent evt){
        if("".equals(this.textField.getText())){
            setBlank();
        }
        else{
            Utils.setTimeout(() -> {
                this.value = this.getValue();
                UpdateComponent.updateModel(this.model, this.fieldName, this.value);
            }, 100);
        }
    }
    
    private void setBlank(){
        Utils.setTimeout(() -> {
            this.textField.setText("");
            this.value = null;
            UpdateComponent.updateModel(this.model, this.fieldName, this.value);
        }, 300);
    }
    
    public Integer getValue(){
        if(this.textField != null && "".equals(this.textField.getText())){
            return null;
        }
        else{
            return (Integer)super.getValue();
        }
    }
}
