/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild.Components;

import java.awt.Color;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import xbuild.ModelItem;

/**
 *
 * @author PCMIWS04
 */
public class XButtonGroup extends ButtonGroup {

    private Object model;
    private String fieldName;
    private String value;
    private List<ModelItem> modelItems;

    public XButtonGroup() {
    }

    public void Init(Object model, String fieldName, String value, List<ModelItem> modelItems) {
        this.model = model;
        this.fieldName = fieldName;
        this.value = value;
        this.modelItems = modelItems;

        for (int i = 0; i < this.getButtonCount(); i++) {
            AbstractButton b = this.buttons.get(i);
            String desc = "";
            for (ModelItem item : modelItems) {
                if (item.key.equalsIgnoreCase(value)) {
                    desc = item.description;
                    break;
                }
            }
            if (b.getText().equalsIgnoreCase(desc)) {
                ((XRadioButton) b).setSelectedItem(this.modelItems, value);
                b.setSelected(true);
                break;
            }
        }

    }

    @Override
    public void add(AbstractButton b) {
        super.add(b);

        ((XRadioButton) b).setSelectedItem(modelItems, value);

        b.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                buttonStateChanged(evt, (JRadioButton) b);
            }
        });
    }

    public void buttonStateChanged(javax.swing.event.ChangeEvent evt, JRadioButton rdButton) {
        if (rdButton.isSelected()) {
            rdButton.setForeground(new Color(0, 150, 0));

            for (ModelItem item : modelItems) {
                if (rdButton.getText().equalsIgnoreCase(item.description)) {
                    this.value = item.key;
                    UpdateComponent.updateModel(model, fieldName, value);
                    break;
                }
            }
        } else {
            rdButton.setForeground(Color.BLACK);
        }
    }
}
