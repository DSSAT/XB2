package xbuild;

import DSSATModel.ChemicalMaterialList;
import DSSATModel.FertilizerMethodList;
import Extensions.Variables;
import FileXModel.Chemical;
import FileXModel.ChemicalApplication;
import FileXModel.FileX;
import FileXModel.IModelXBase;
import java.awt.EventQueue;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.util.Locale;
import javax.swing.table.DefaultTableModel;
import xbuild.Components.IXInternalFrame;
import xbuild.Events.UpdateLevelEvent;

/**
 *
 * @author Jazzy
 */
public class ChemicalFrame extends IXInternalFrame {

    protected Chemical chem;
    private int selectedRowIndex = -1;
    private Integer level;
    /**
     * Creates new form ChemicalFrame
     * @param nodeName
     */
    public ChemicalFrame(String nodeName) {
        initComponents();
        
        level = 0;
        for(IModelXBase ch : FileX.chemicalList.GetAll()){
            level++;
            if(getLevel(nodeName) == level){
                this.chem = (Chemical)ch;
                break;
            }
        }
        
        LoadChemical();
        
        lblLevel.setText("Level " + level.toString());
        txtDescription.Init(chem, "CHNAME", chem.CHNAME);
        
        setImage(imagePanel, "irrigation2.jpg");
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
        
        Integer level = 0;
        for (IModelXBase f : FileX.chemicalList.GetAll()) {
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

        lblLevel = new org.jdesktop.swingx.JXLabel();
        txtDescription = new xbuild.Components.XTextField();
        jXPanel1 = new org.jdesktop.swingx.JXPanel();
        bnAddApplication = new javax.swing.JButton();
        bnDeleteApplication = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTable1 = new org.jdesktop.swingx.JXTable();
        imagePanel = new javax.swing.JLabel();
        lblLevel1 = new org.jdesktop.swingx.JXLabel();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        lblLevel.setText("Level");
        lblLevel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        txtDescription.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescriptionFocusLost(evt);
            }
        });

        jXPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bnAddApplication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Plus.png"))); // NOI18N
        bnAddApplication.setText("Add Application");
        bnAddApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddApplicationActionPerformed(evt);
            }
        });

        bnDeleteApplication.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Minus.png"))); // NOI18N
        bnDeleteApplication.setText("DeleteApplication");
        bnDeleteApplication.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteApplicationActionPerformed(evt);
            }
        });

        jXTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Chemical Material", "<html><p align='center'>Application<br>Amount<br>kg/ha</p></html>", "Chemical Applicatin Method", "<html><p align='center'>Chemical Application<br>Depth<br>cm</p></html>", "<html><p align='center'>Chemical<br>Targets</p></html>"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class, java.lang.Float.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jXTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jXTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jXTable1);

        imagePanel.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jXPanel1Layout = new javax.swing.GroupLayout(jXPanel1);
        jXPanel1.setLayout(jXPanel1Layout);
        jXPanel1Layout.setHorizontalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addComponent(bnAddApplication)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnDeleteApplication))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 828, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jXPanel1Layout.setVerticalGroup(
            jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jXPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(imagePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jXPanel1Layout.createSequentialGroup()
                        .addGroup(jXPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bnAddApplication)
                            .addComponent(bnDeleteApplication))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        lblLevel1.setText("Chemical Applications");
        lblLevel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(903, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLevel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(246, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnAddApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAddApplicationActionPerformed
        ChemicalApplication chemApp = null;
        if (selectedRowIndex >= 0 && selectedRowIndex < chem.GetSize()) {
            ChemicalApplication tmp = chem.GetApp(selectedRowIndex);
            chemApp = tmp.Clone();
        } else {
            chemApp = new ChemicalApplication();
        }

        final ChemicalDialog chemDialog = new ChemicalDialog(null, true, chemApp);
        chemDialog.show();

        chemDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                ChemicalApplication chemApp = chemDialog.GetData();
                if(chemApp != null){
                    DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();

                    while(model.getRowCount() > 0)
                        model.removeRow(0);
                    
                    chem.AddApp(chemApp);
                    
                    for (int i = 0; i < chem.GetSize(); i++) {                        
                        model.addRow(SetRow(chem.GetApp(i)));
                    }                    
                }
                chemDialog.SetNull();
            }
        });
    }//GEN-LAST:event_bnAddApplicationActionPerformed

    private void bnDeleteApplicationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteApplicationActionPerformed
        int nRow = jXTable1.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();
        model.removeRow(nRow);

        chem.RemoveAt(nRow);
    }//GEN-LAST:event_bnDeleteApplicationActionPerformed

    private void jXTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTable1MouseClicked
        if(evt.getClickCount() == 2)
        {
            final ChemicalDialog chemDialog = new ChemicalDialog(null, true, chem.GetApp(jXTable1.getSelectedRow()));
            chemDialog.show();

            chemDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    ChemicalApplication chemApp = chemDialog.GetData();
                    if(chemApp != null){
                        DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();
                        Object[] row = SetRow(chemApp);
                        for (int n = 0; n < row.length; n++)
                            model.setValueAt(row[n], jXTable1.getSelectedRow(), n);
                    }
                    chemDialog.SetNull();
                }
            });
        }
        else {
            int nRow = jXTable1.getSelectedRow();

            if(nRow != selectedRowIndex){
                selectedRowIndex = nRow;
            }
            else{
                selectedRowIndex = -1;
                jXTable1.clearSelection();
            }
        }
    }//GEN-LAST:event_jXTable1MouseClicked

    private void txtDescriptionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescriptionFocusLost
        if(txtDescription.getText() == null ? chem.CHNAME != null : !txtDescription.getText().equals(chem.CHNAME)){
            l.myAction(new UpdateLevelEvent(this, "Chemical Applications", "Level " + level + ": " + txtDescription.getText(), level - 1));
        }
    }//GEN-LAST:event_txtDescriptionFocusLost

    private void LoadChemical() {

        DefaultTableModel model = (DefaultTableModel) jXTable1.getModel();
        for(int i = 0;i < chem.GetSize();i++)
        {
            model.addRow(SetRow(chem.GetApp(i)));
        }
    }

    private Object[] SetRow(ChemicalApplication chemApp) {
        Object day;
        Object CHCOD;
        Object CHAMT;
        Object CHME;
        Object CHDEP;
        Object CHT;
        try
        {
            day = Variables.getDateFormat().format(chemApp.CDATE);
        }
        catch(Exception ex)
        {
            day = "";
        }

        try
        {
            CHCOD = ChemicalMaterialList.GetAt(chemApp.CHCOD).Description;
        }
        catch(Exception ex) {
            CHCOD = "";
        }
        try
        {
            CHAMT = chemApp.CHAMT;
        }
        catch(Exception ex) {
            CHAMT = "";
        }
        try
        {
            CHME = FertilizerMethodList.GetAt(chemApp.CHME).Description;
        }
        catch(Exception ex) {
            CHME = "";
        }
        try
        {
            CHDEP = chemApp.CHDEP;
        }
        catch(Exception ex) {
            CHDEP = "";
        }
        try
        {
            CHT = chemApp.CHT;
        }
        catch(Exception ex) {
            CHT = "";
        }
        return new Object[]{day, CHCOD, CHAMT, CHME, CHDEP, CHT};
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAddApplication;
    private javax.swing.JButton bnDeleteApplication;
    private javax.swing.JLabel imagePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXPanel jXPanel1;
    private org.jdesktop.swingx.JXTable jXTable1;
    private org.jdesktop.swingx.JXLabel lblLevel;
    private org.jdesktop.swingx.JXLabel lblLevel1;
    private xbuild.Components.XTextField txtDescription;
    // End of variables declaration//GEN-END:variables
}
