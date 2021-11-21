/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import Tools.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author Jazzy
 */
public class XComboBox extends JComboBox {
    private Object model;
    private String fieldName;
    private String value;
    private List<XComboBoxItem> items;
    private int index;
    
    public XComboBox(Object model, String fieldName, String value, List<XComboBoxItem> items) {
        this.model = model;
        this.fieldName = fieldName;
        this.value = value;
        this.items = items;
        
        List<String> modelItems = new ArrayList();
        items.forEach(x -> modelItems.add(x.item));
        
        this.setModel(new javax.swing.DefaultComboBoxModel(modelItems.toArray()));
        
        if(value == null || "".equals(value)){
            this.value = items.get(0).index;
            UpdateComponent.updateModel(this.model, this.fieldName, this.value);
        }
        else{
            for(int i = 0; i < items.size();i++)
            {
                if(items.get(i).index.equals(value)){
                    index = i;
                    Utils.setTimeout(() -> this.setSelectedIndex(index), 100);
                    break;
                }
            }
        }
        
        setAction();
    }   
 
 private void setAction(){
        this.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                performFocusLost(evt);
            }
        });
    }
     
     public void performFocusLost(java.awt.event.ActionEvent evt) {
        int index = this.getSelectedIndex();
        this.value = items.get(index).index;        
        UpdateComponent.updateModel(this.model, this.fieldName, this.value);
    }
}
