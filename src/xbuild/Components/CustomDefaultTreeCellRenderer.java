package xbuild.Components;

import DSSATModel.ExperimentType;
import Extensions.Utils;
import FileXModel.FileX;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        String nodeName = node.toString();
        
        if(node.getParent() != null && !nodeName.equals("General Information")){
            if(FileX.general != null && (Utils.IsEmpty(FileX.general.SiteCode) || Utils.IsEmpty(FileX.general.InstituteCode) || Utils.IsEmpty(FileX.general.Year))){
                enabled = false;
            }
            if (FileX.general != null && FileX.general.FileType == ExperimentType.Experimental && (FileX.general.crop == null || Utils.IsEmpty(FileX.general.crop.CropCode))) {
                enabled = false;
            }
        }

        if (enabled && nodeName.equals("Treatment")) {
            enabled = FileX.fieldList != null && FileX.fieldList.GetSize() > 0 && !Utils.IsEmpty(FileX.fieldList.GetAt(0).WSTA) && !Utils.IsEmpty(FileX.fieldList.GetAt(0).ID_SOIL)
                    && FileX.cultivars != null && FileX.cultivars.GetSize() > 0
                    && FileX.plantings != null && FileX.plantings.GetSize() > 0
                    && FileX.simulationList != null && FileX.simulationList.GetSize() > 0;
        }

        Component treeCellRendererComponent = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        if (nodeName.equals("General Information")|| nodeName.equals("Fields") || nodeName.equals("Cultivars") || nodeName.equals("Planting") || nodeName.equals("Simulation Controls") || nodeName.equals("Treatment")) {
            Font font = treeCellRendererComponent.getFont();
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_ULTRABOLD));

            //treeCellRendererComponent.setFont(font);
            
            JLabel label = new JLabel();

            ImageIcon icon;
            icon = new ImageIcon(getClass().getResource("/icons/requirednode.png"));
            label.setIcon(icon);
            label.setText(nodeName);
            label.setFont(font);
            label.setEnabled(enabled);
            return label;        
        }
        else{
            Font font = treeCellRendererComponent.getFont();
            font = font.deriveFont(
                    Collections.singletonMap(
                            TextAttribute.WEIGHT, TextAttribute.WEIGHT_LIGHT));

            treeCellRendererComponent.setFont(font);
        }
        treeCellRendererComponent.setEnabled(enabled);

        return treeCellRendererComponent;
    }
}
