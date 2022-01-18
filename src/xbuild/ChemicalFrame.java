/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ChemicalFrame.java
 *
 * Created on Mar 13, 2010, 11:22:43 PM
 */

package xbuild;

import FileXModel.FileX;
import FileXModel.Chemical;
import Extensions.Utils;
import FileXModel.ChemicalApplication;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXFrame;

/**
 *
 * @author Jazzy
 */
public class ChemicalFrame extends javax.swing.JInternalFrame {

    /** Creates new form ChemicalFrame */
    public ChemicalFrame() {
        initComponents();

        LoadChemical();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnuCopy = new javax.swing.JMenuItem();
        mnuRemove = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();

        mnuCopy.setText("Copy");
        mnuCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuCopy);

        mnuRemove.setText("Remove");
        mnuRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuRemoveActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnuRemove);

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(767, 677));

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 586, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("<html><center>Add New Level<br>Click Here</center></html>", jXPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        if(evt.getClickCount() == 1) {
            if(jTabbedPane1.getSelectedIndex() == jTabbedPane1.getTabCount()-1)   // Add New App
            {
                String r = JOptionPane.showInputDialog(new JXFrame(), "Please enter your description","UNKNOWN");
                if(r.length() > 0) {
                    int newLevel = 1;
                    try {
                        String newTitle = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()-1);
                        newLevel = Integer.parseInt(newTitle.substring(29, 31).trim()) + 1;
                    } catch(Exception ex) {}

                    String newTitle = "<html><p align='center'>Level " + newLevel + "<br>" + r + "</p></html>";

                    Chemical chem = new Chemical(r);
                    FileX.chemicalList.AddNew(chem);

                    jTabbedPane1.insertTab(newTitle, null, new ChemicalPanel(chem), "", jTabbedPane1.getTabCount()-1);
                    jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount()-2);
                }
            }
        } else if(evt.getClickCount() == 2 && jTabbedPane1.getSelectedIndex() < jTabbedPane1.getTabCount()-1) // Edit
        {
            String renameTitle = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
            String[] tmp = renameTitle.split("<br>");
            boolean b = false;
            String level = "";
            String description = "";

            for(int i = 0;i < tmp[0].length();i++) {
                if(tmp[0].charAt(i) == '<') b = false;
                else if(tmp[0].charAt(i) == '>') b = true;
                else if(b)
                    level += tmp[0].charAt(i);
            }
            b = true;
            for(int i = 0;i < tmp[1].length();i++) {
                if(tmp[1].charAt(i) == '<') b = false;
                else if(tmp[1].charAt(i) == '>') b = true;
                else if(b)
                    description += tmp[1].charAt(i);
            }
            String r = (String) JOptionPane.showInputDialog(new JXFrame(), "Please enter your description", level, 0, null, null, description);
            String newTitle = "<html><p align='center'>" + level + "<br>" + r + "</p></html>";
            jTabbedPane1.setTitleAt(jTabbedPane1.getSelectedIndex(), newTitle);

            Chemical chem = FileX.chemicalList.GetAt(jTabbedPane1.getSelectedIndex());
            chem.CHNAME = r;
        }
}//GEN-LAST:event_jTabbedPane1MouseClicked

    private void mnuCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCopyActionPerformed
        String r = JOptionPane.showInputDialog(new JXFrame(), "Please enter your description", "UNKNOWN");
        if (r.length() > 0) {
            int newLevel = 1;
            try {
                String selectedTitle = jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex());
                newLevel = Integer.parseInt(selectedTitle.substring(29, 31).trim()) + 1;
            } catch (Exception ex) {
            }

            String newTitle = "<html><p align='center'>Level " + newLevel + "<br>" + r + "</p></html>";

            Chemical chem = new Chemical(r);
            FileX.chemicalList.GetAt(jTabbedPane1.getSelectedIndex()).GetApps().forEach(c -> {
                ChemicalApplication ca = (ChemicalApplication) c.Clone();
                chem.AddApp(ca);
            });
            FileX.chemicalList.AddNew(chem);

            jTabbedPane1.insertTab(newTitle, null, new ChemicalPanel(chem), "", jTabbedPane1.getTabCount() - 1);
            jTabbedPane1.setSelectedIndex(jTabbedPane1.getTabCount() - 2);
        }
    }//GEN-LAST:event_mnuCopyActionPerformed

    private void mnuRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuRemoveActionPerformed
        if(JOptionPane.showConfirmDialog(new JXFrame(), "Do you want to delete this level") == 0){
            int index = jTabbedPane1.getSelectedIndex();
            FileX.chemicalList.RemoveAt(index);
            jTabbedPane1.remove(index);
            Utils.setTimeout(() -> jTabbedPane1.setSelectedIndex(Math.min(0, index - 1)), 100);
        }
    }//GEN-LAST:event_mnuRemoveActionPerformed

    private void jTabbedPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseReleased
        if(evt.getButton() == 3 && jTabbedPane1.getSelectedIndex() < jTabbedPane1.getTabCount()-1){
            if(evt.isPopupTrigger()){
                jPopupMenu1.show(this, evt.getX() + 5, evt.getY() + 5);
            }
        }
    }//GEN-LAST:event_jTabbedPane1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private javax.swing.JMenuItem mnuCopy;
    private javax.swing.JMenuItem mnuRemove;
    // End of variables declaration//GEN-END:variables

    private void LoadChemical() {
        for(int n = 0;n < FileX.chemicalList.GetSize();n++){
            int newLevel = n + 1;
            Chemical chem = FileX.chemicalList.GetAt(n);
            String newTitle = "<html><p align='center'>Level " + newLevel + "<br>" + chem.CHNAME + "</p></html>";
            jTabbedPane1.insertTab(newTitle, null, new ChemicalPanel(chem), "", jTabbedPane1.getTabCount()-1);
        }
        jTabbedPane1.setSelectedIndex(0);
    }

}
