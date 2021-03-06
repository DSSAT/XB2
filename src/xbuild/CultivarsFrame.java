/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * CultivarsFrame.java
 *
 * Created on Mar 12, 2010, 9:30:23 AM
 */

package xbuild;

import FileXModel.Cultivar;
import FileXModel.FileX;
import DSSATModel.CropList;
import ListDialog.CultivarListDialog;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import xbuild.Components.IXInternalFrame;

/**
 *
 * @author Jazzy
 */
public class CultivarsFrame extends IXInternalFrame {

    /** Creates new form CultivarsFrame */
    public CultivarsFrame() {
        initComponents();

        LoadCultivar();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bnDeleteLayer = new javax.swing.JButton();
        bnAddLayer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(767, 677));

        bnDeleteLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Minus.png"))); // NOI18N
        bnDeleteLayer.setText("Delete");
        bnDeleteLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteLayerActionPerformed(evt);
            }
        });

        bnAddLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Plus.png"))); // NOI18N
        bnAddLayer.setText("Add");
        bnAddLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddLayerActionPerformed(evt);
            }
        });

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Level", "Crop", "Cultivar"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jXTable1);
        jXTable1.getColumnModel().getColumn(0).setMinWidth(50);
        jXTable1.getColumnModel().getColumn(0).setPreferredWidth(50);
        jXTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
        jXTable1.getColumnModel().getColumn(2).setPreferredWidth(350);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bnAddLayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnDeleteLayer)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnDeleteLayer)
                    .addComponent(bnAddLayer))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnAddLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAddLayerActionPerformed
        final CultivarListDialog dialog = new CultivarListDialog(null, true);
        dialog.show();

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                DSSATModel.Cultivar cul = dialog.GetData();
                if(cul != null){
                    DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();

                    Cultivar c = new Cultivar();
                    c.CR = cul.CropCode;
                    c.INGENO = cul.CulCode;
                    c.CNAME = cul.CulName;

                    model.addRow(SetRow(c));

                    FileX.cultivars.AddNew(c);
                }
                dialog.SetNull();
            }
        });
    }//GEN-LAST:event_bnAddLayerActionPerformed

    private void bnDeleteLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteLayerActionPerformed
        int nRow = jXTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();
        model.removeRow(nRow);
        for(int i = nRow;i < jXTable1.getRowCount();i++)
            model.setValueAt(i+1, nRow, 0);

        FileX.cultivars.RemoveAt(nRow);
    }//GEN-LAST:event_bnDeleteLayerActionPerformed

    private Vector SetRow(Cultivar cul) {

        Vector vector = new Vector();
        vector.add(jXTable1.getRowCount()+1);
        vector.add(CropList.GetAt(cul.CR).CropName);
        vector.add(cul.CNAME);
        return vector;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAddLayer;
    private javax.swing.JButton bnDeleteLayer;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXTable jXTable1;
    // End of variables declaration//GEN-END:variables

    private void LoadCultivar() {
        for(int i = 0;i < FileX.cultivars.GetSize();i++)
        {
            DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();
            model.addRow(SetRow(FileX.cultivars.GetAt(i)));
        }
    }

}
