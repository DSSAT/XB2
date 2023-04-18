package xbuild;

import DSSATModel.SoilAnalysisMethodPhList;
import DSSATModel.SoilAnalysisMethodPhosphorusList;
import DSSATModel.SoilAnalysisMethodPotassiumList;
import Extensions.Variables;
import FileXModel.FileX;
import FileXModel.IModelXBase;
import FileXModel.SoilAnalysis;
import FileXModel.SoilAnalysisLayer;
import java.awt.EventQueue;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import javax.swing.table.DefaultTableModel;
import xbuild.Components.IXInternalFrame;
import xbuild.Components.XColumn;
import xbuild.Events.MenuDirection;
import xbuild.Events.NewFrameEvent;
import xbuild.Events.UpdateLevelEvent;

/**
 *
 * @author Jazzy
 */
public class SoilAnalysisFrame extends IXInternalFrame {

    private SoilAnalysis soilAnalysis;
    private Integer level;
    private int selectedRowIndex = -1;
    /**
     * Creates new form SoilAnalysisFrame
     * @param nodeName
     */
    public SoilAnalysisFrame(String nodeName) {
        initComponents();
        
        level = 0;
        for(IModelXBase s: FileX.soilAnalysis.GetAll()){
            level++;
            if(getLevel(nodeName) == level){
                this.soilAnalysis = (SoilAnalysis) s;
                break;
            }
        }
        
        dpAnalysisDate.Init(soilAnalysis, "SADAT", soilAnalysis.SADAT);

        
        cbSMHB.setInit(soilAnalysis, "SMHB", soilAnalysis.SMHB, SoilAnalysisMethodPhList.GetAll(), new XColumn[] { new  XColumn("Description", "Description", 200)}, "Code");
        cbSMKE.setInit(soilAnalysis, "SMKE", soilAnalysis.SMKE, SoilAnalysisMethodPotassiumList.GetAll(), new XColumn[] { new  XColumn("Description", "Description", 200)}, "Code");
        cbSMPX.setInit(soilAnalysis, "SMPX", soilAnalysis.SMPX, SoilAnalysisMethodPhosphorusList.GetAll(), new XColumn[] { new  XColumn("Description", "Description", 200)}, "Code");
        
        for(int i = 0;i < soilAnalysis.GetSize();i++)
        {
            DefaultTableModel model = (DefaultTableModel) jXTable2.getModel();
            model.addRow(SetRow(soilAnalysis.GetLayer(i)));
        }
        
        lblLevel.setText("Level " + level.toString());
        txtDescription.Init(soilAnalysis, "SANAME", soilAnalysis.SANAME);
        
        EventQueue.invokeLater(() -> {            
            setImage(imagePanel, setup.GetDSSATPath() + "\\Tools\\XBuild\\SoilAnal2.jpg");
        });
    }
    
    /**
     *
     * @param name
     */
    @Override
    public void updatePanelName(String name){
        FocusListener[] listens = txtDescription.getListeners(FocusListener.class);
        for(FocusListener li : listens)
            txtDescription.removeFocusListener(li);
        
        level = 0;
        for (IModelXBase f : FileX.soilAnalysis.GetAll()) {
            level++;
            if(getLevel(name) == level){                
                lblLevel.setText("Level " + level.toString());
                txtDescription.setText(getDescription(name));                
                break;
            }
        }
        
        for(FocusListener li : listens)
            this.addFocusListener(li);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        dpAnalysisDate = new xbuild.Components.XDatePicker();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        cbSMHB = new xbuild.Components.XDropdownTableComboBox();
        cbSMPX = new xbuild.Components.XDropdownTableComboBox();
        cbSMKE = new xbuild.Components.XDropdownTableComboBox();
        jXPanel2 = new org.jdesktop.swingx.JXPanel();
        bnAddLayer = new javax.swing.JButton();
        bnDeleteLayer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable2 = new org.jdesktop.swingx.JXTable();
        lblLevel = new org.jdesktop.swingx.JXLabel();
        txtDescription = new xbuild.Components.XTextField();
        imagePanel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bnPrevious = new javax.swing.JButton();
        bnNext = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        jXLabel1.setText("Analysis Date");

        dpAnalysisDate.setFormats(Variables.getDateFormat());
        dpAnalysisDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dpAnalysisDatePropertyChange(evt);
            }
        });

        jXPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Determination Method", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jXLabel2.setText("pH");

        jXLabel3.setText("Phosphorus");

        jXLabel4.setText("Potassium");

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jXPanel1Layout.createSequentialGroup()
                        .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbSMHB, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jXPanel1Layout.createSequentialGroup()
                        .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jXPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbSMKE, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jXPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbSMPX, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSMHB, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSMPX, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSMKE, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jXPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Soil Analysis Layers", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jXPanel2.setPreferredSize(new java.awt.Dimension(460, 446));

        bnAddLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Plus.png"))); // NOI18N
        bnAddLayer.setText("Add Layer");
        bnAddLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddLayerActionPerformed(evt);
            }
        });

        bnDeleteLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Minus.png"))); // NOI18N
        bnDeleteLayer.setText("Delete Layer");
        bnDeleteLayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteLayerActionPerformed(evt);
            }
        });

        jXTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "<html><p align='center'>Depth<br> base of layer<br> cm</p></html>", "<html><p align='center'>Bulk density<br>moist<br>g/cm<sup>3</sup></p></html>", "<html><p align='center'>Organic carbon<br>%</p></html>", "<html><p align='center'>Total nitrogen<br>%</p></html>", "<html><p align='center'>pH<br>in<br>Water</p></html>", "<html><p align='center'>pH<br>in<br>Buffer</p></html>", "<html><p align='center'>Phosphorus<br>extractable<br>mg/kg </p></html>", "<html><p align='center'>Potassium<br>exchangeable<br>cmol</p></html>", "<html><p align='center'>Stable<br>Organic<br>Carbon %</p></html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable2.setPreferredSize(new java.awt.Dimension(400, 0));
        jXTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jXTable2);

        javax.swing.GroupLayout jXPanel2Layout = new javax.swing.GroupLayout(jXPanel2);
        jXPanel2.setLayout(jXPanel2Layout);
        jXPanel2Layout.setHorizontalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jXPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnAddLayer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnDeleteLayer))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 731, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jXPanel2Layout.setVerticalGroup(
            jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel2Layout.createSequentialGroup()
                .addGroup(jXPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnDeleteLayer)
                    .addComponent(bnAddLayer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblLevel.setText("Level");
        lblLevel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescriptionFocusLost(evt);
            }
        });

        imagePanel.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setText(Variables.getDateFormatString());

        bnPrevious.setText("PREVIOUS");
        bnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnPreviousActionPerformed(evt);
            }
        });

        bnNext.setText("NEXT");
        bnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnNextActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(226, 226, 226)
                                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dpAnalysisDate, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bnPrevious)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnNext)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnPrevious)
                    .addComponent(bnNext))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dpAnalysisDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jXPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dpAnalysisDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dpAnalysisDatePropertyChange
        try {
            soilAnalysis.SADAT = dpAnalysisDate.getDate();
        } catch (Exception e) {
            soilAnalysis.SADAT = null;
        }
    }//GEN-LAST:event_dpAnalysisDatePropertyChange

    private void bnAddLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAddLayerActionPerformed
        SoilAnalysisLayer soil = null;
        if (selectedRowIndex > 0 && selectedRowIndex < soilAnalysis.GetSize()) {
            SoilAnalysisLayer tmp = soilAnalysis.GetLayer(selectedRowIndex);
            soil = tmp.Clone();
        } else {
            soil = new SoilAnalysisLayer();
        }

        final SoilAnalysisDialog dialog = new SoilAnalysisDialog(null, true, soil);
        dialog.show();

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                SoilAnalysisLayer soilLayer = dialog.GetData();
                if(soilLayer != null){
                    DefaultTableModel model = (DefaultTableModel) jXTable2.getModel();

                    while(model.getRowCount() > 0)
                        model.removeRow(0);

                    soilAnalysis.AddLayer(soilLayer);
                    
                    for (int i = 0; i < soilAnalysis.GetSize(); i++) {                        
                        model.addRow(SetRow(soilAnalysis.GetLayer(i)));
                    }
                }
                dialog.SetNull();
            }
        });
    }//GEN-LAST:event_bnAddLayerActionPerformed

    private void bnDeleteLayerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteLayerActionPerformed
        int nRow = jXTable2.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jXTable2.getModel();
        model.removeRow(nRow);

        soilAnalysis.RemoveLayer(nRow);
    }//GEN-LAST:event_bnDeleteLayerActionPerformed

    private void jXTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable2MouseClicked
        if(evt.getClickCount() == 2)
        {
            final SoilAnalysisDialog dialog = new SoilAnalysisDialog(null, true, soilAnalysis.GetLayer(jXTable2.getSelectedRow()));
            dialog.show();

            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    SoilAnalysisLayer soilLayer = dialog.GetData();
                    if(soilLayer != null){
                        DefaultTableModel model = (DefaultTableModel) jXTable2.getModel();
                        Object[] vector = SetRow(soilLayer);
                        for (int n = 0; n < vector.length; n++)
                            model.setValueAt(vector[n], jXTable2.getSelectedRow(), n);
                    }
                    dialog.SetNull();
                }
            });
        }
        else{
            int nRow = jXTable2.getSelectedRow();

            if(nRow != selectedRowIndex){
                selectedRowIndex = nRow;
            }
            else{
                selectedRowIndex = -1;
                jXTable2.clearSelection();
            }
        }
    }//GEN-LAST:event_jXTable2MouseClicked

    private void txtDescriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescriptionFocusLost
        if(txtDescription.getText() == null ? soilAnalysis.SANAME != null : !txtDescription.getText().equals(soilAnalysis.SANAME)){
            l.myAction(new UpdateLevelEvent(this, "Soil Analysis", "Level " + level + ": " + txtDescription.getText(), level - 1));
        }
    }//GEN-LAST:event_txtDescriptionFocusLost

    private void bnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnPreviousActionPerformed
        EventQueue.invokeLater(() -> {
            l.myAction(new NewFrameEvent(this, "Soil Analysis", MenuDirection.PREVIOUS));
        });
    }//GEN-LAST:event_bnPreviousActionPerformed

    private void bnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnNextActionPerformed
        EventQueue.invokeLater(() -> {
            l.myAction(new NewFrameEvent(this, "Soil Analysis", MenuDirection.NEXT));
        });
    }//GEN-LAST:event_bnNextActionPerformed

    private Object[] SetRow(SoilAnalysisLayer soilLayer) {

        Object[] vector = new Object[]{
            soilLayer.SABL,
            soilLayer.SADM,
            soilLayer.SAOC,
            soilLayer.SANI,
            soilLayer.SAPHW,
            soilLayer.SAPHB,
            soilLayer.SAPX,
            soilLayer.SAKE,
            soilLayer.SASC
        };       

        return vector;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAddLayer;
    private javax.swing.JButton bnDeleteLayer;
    private javax.swing.JButton bnNext;
    private javax.swing.JButton bnPrevious;
    private xbuild.Components.XDropdownTableComboBox cbSMHB;
    private xbuild.Components.XDropdownTableComboBox cbSMKE;
    private xbuild.Components.XDropdownTableComboBox cbSMPX;
    private xbuild.Components.XDatePicker dpAnalysisDate;
    private javax.swing.JLabel imagePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXPanel jXPanel2;
    private org.jdesktop.swingx.JXTable jXTable2;
    private org.jdesktop.swingx.JXLabel lblLevel;
    private xbuild.Components.XTextField txtDescription;
    // End of variables declaration//GEN-END:variables
}
