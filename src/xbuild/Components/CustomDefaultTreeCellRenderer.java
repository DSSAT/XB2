package xbuild.Components;

import Extensions.Icons;
import FileXModel.FileX;
import FileXService.FileXValidationService;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Collections;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author Jazzy
 */
public class CustomDefaultTreeCellRenderer extends DefaultTreeCellRenderer {

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        boolean enabled = true;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        String nodeName = node.toString();
        
        Component treeCellRendererComponent = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (node.getParent() != null && !nodeName.equals("General Information")) {
            enabled = FileXValidationService.IsGeneralValid();
        }

        if (nodeName.equals("Treatments")) {
            enabled = FileXValidationService.IsMinimumRequired();
        }        

        if (nodeName.equals("General Information") || nodeName.equals("Fields") || nodeName.equals("Cultivars") || nodeName.equals("Planting") || nodeName.equals("Simulation Controls") || nodeName.equals("Treatments")) {
            Font font = treeCellRendererComponent.getFont();
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD));

            treeCellRendererComponent.setFont(font);
        } 
        else if(node.getParent() == null && FileX.isDirty){            
            Font font = treeCellRendererComponent.getFont();
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD));

            treeCellRendererComponent.setFont(font);
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));
        }
        else {
            Font font = treeCellRendererComponent.getFont();
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT));

            treeCellRendererComponent.setFont(font);
        }
        setEnabled(enabled);
        
        if (Icons.hasIcon(nodeName) && getIcon().getIconWidth() != 24) {
            setIcon(Icons.getIcon(nodeName));
        }
        
        String nodeParentName = node.getParent() != null ? node.getParent().toString() : "";
        
        if((nodeName.equals("General Information") && !FileXValidationService.IsGeneralValid())
                || (nodeName.equals("Fields") && !FileXValidationService.IsFieldsValid())
                || (nodeName.equals("Cultivars") && !FileXValidationService.IsCultivarsValid())
                || (nodeName.equals("Planting") && !FileXValidationService.IsPlantingsValid())
                || (nodeName.equals("Simulation Controls") && !FileXValidationService.IsSimulationControlsValid()))
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));
        else if(node.isLeaf() && nodeParentName.equals("Fields") && !FileXValidationService.IsFieldValid(nodeName))
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));
        else if(node.isLeaf() && nodeParentName.equals("Planting") && !FileXValidationService.IsPlantingValid(nodeName))
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));
        else if(node.isLeaf() && nodeParentName.equals("Simulation Controls") && !FileXValidationService.IsSimulationControlValid(nodeName))
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));
        else if(nodeName.equals("Treatments") && !FileXValidationService.IsTreatmentValid(nodeName))
            treeCellRendererComponent.setForeground(new Color(200, 20, 20));        

        return treeCellRendererComponent;
    }
}
