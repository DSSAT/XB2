/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HarvestDialog.java
 *
 * Created on Mar 13, 2010, 9:27:36 PM
 */

package xbuild;

import FileXModel.HarvestApplication;
import FileXModel.FileX;
import DSSATModel.GrowthStage;
import DSSATModel.GrowthStageList;
import DSSATModel.HarvestComponent;
import DSSATModel.HarvestComponentList;
import DSSATModel.HarvestSize;
import DSSATModel.HarvestSizeList;
import ListDialog.GStageDialog;
import ListDialog.HarvestComponentDialog;
import ListDialog.HarvestSizeDialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Jazzy
 */
public class HarvestDialog extends javax.swing.JDialog {

    /** Creates new form HarvestDialog */

    protected HarvestApplication harvestApp;
    protected boolean bDay;
    protected String HSTG;
    protected String HCOM;
    protected String HSIZE;

    public HarvestDialog(java.awt.Frame parent, boolean modal, boolean bDay, HarvestApplication harvestApp) {
        super(parent, modal);
        initComponents();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2 , (screenHeight - winSize.height) / 2);

        this.bDay = bDay;
        this.harvestApp = harvestApp;
        
        this.HSTG = harvestApp.HSTG;
        this.HCOM = harvestApp.HCOM;
        this.HSIZE = harvestApp.HSIZE;    

        lbDay.setVisible(bDay);
        txtHDATE.setVisible(bDay);
        lbDate.setVisible(!bDay);
        dpHDATE.setVisible(!bDay);

        LoadHarvestApp();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbDay = new org.jdesktop.swingx.JXLabel();
        lbDate = new org.jdesktop.swingx.JXLabel();
        dpHDATE = new org.jdesktop.swingx.JXDatePicker();
        txtHDATE = new javax.swing.JFormattedTextField();
        txtHSTG = new javax.swing.JTextField();
        txtHCOM = new javax.swing.JTextField();
        txtHSIZE = new javax.swing.JTextField();
        txtHPC = new javax.swing.JFormattedTextField();
        txtHBPC = new javax.swing.JFormattedTextField();
        bnOK = new javax.swing.JButton();
        bnCancel = new javax.swing.JButton();
        bnHSTG = new javax.swing.JButton();
        bnHCOM = new javax.swing.JButton();
        bnHSIZE = new javax.swing.JButton();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();
        jXLabel7 = new org.jdesktop.swingx.JXLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbDay.setText("Day");

        lbDate.setText("Date");

        dpHDATE.setFormats(new SimpleDateFormat("dd/MM/yyyy", new Locale("en","US")));
        dpHDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dpHDATEActionPerformed(evt);
            }
        });

        txtHPC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));

        txtHBPC.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.0"))));

        bnOK.setText("OK");
        bnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnOKActionPerformed(evt);
            }
        });

        bnCancel.setText("Cancel");
        bnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnCancelActionPerformed(evt);
            }
        });

        bnHSTG.setText("...");
        bnHSTG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnHSTGActionPerformed(evt);
            }
        });

        bnHCOM.setText("...");
        bnHCOM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnHCOMActionPerformed(evt);
            }
        });

        bnHSIZE.setText("...");
        bnHSIZE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnHSIZEActionPerformed(evt);
            }
        });

        jXLabel1.setText("Stage");

        jXLabel2.setText("Component");

        jXLabel3.setText("Size Group");

        jXLabel4.setText("Grain Harvest");

        jXLabel5.setText("Byproduct Takeoff");

        jXLabel6.setText("%");

        jXLabel7.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnCancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHBPC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHPC, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnHSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnHCOM, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtHSTG, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnHSTG, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtHDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpHDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHDATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dpHDATE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHSTG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnHSTG)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHCOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnHCOM)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHSIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bnHSIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHBPC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnOK)
                    .addComponent(bnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void dpHDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dpHDATEActionPerformed
        Update();
}//GEN-LAST:event_dpHDATEActionPerformed

    private void bnHSTGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnHSTGActionPerformed
        final GStageDialog dialog = new GStageDialog(null, true, FileX.general.crop);
        dialog.show();
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                GrowthStage gStage = dialog.GetSelected();
                txtHSTG.setText(gStage.Description);
                HSTG = gStage.Code;
            }
        });
    }//GEN-LAST:event_bnHSTGActionPerformed

    private void bnHCOMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnHCOMActionPerformed
        final HarvestComponentDialog dialog = new HarvestComponentDialog(null, true);
        dialog.show();
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HarvestComponent harvestComp = dialog.GetSelected();
                txtHCOM.setText(harvestComp.Description);
                HCOM = harvestComp.Code;
            }
        });
    }//GEN-LAST:event_bnHCOMActionPerformed

    private void bnHSIZEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnHSIZEActionPerformed
        final HarvestSizeDialog dialog = new HarvestSizeDialog(null, true);
        dialog.show();
        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                HarvestSize harvestSize = dialog.GetSelected();
                txtHSIZE.setText(harvestSize.Description);
                HSIZE = harvestSize.Code;
            }
        });
    }//GEN-LAST:event_bnHSIZEActionPerformed

    private void bnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnOKActionPerformed
        Update();
        dispose();
    }//GEN-LAST:event_bnOKActionPerformed

    private void bnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnCancelActionPerformed
        harvestApp = null;
        dispose();
    }//GEN-LAST:event_bnCancelActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnCancel;
    private javax.swing.JButton bnHCOM;
    private javax.swing.JButton bnHSIZE;
    private javax.swing.JButton bnHSTG;
    private javax.swing.JButton bnOK;
    private org.jdesktop.swingx.JXDatePicker dpHDATE;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel lbDate;
    private org.jdesktop.swingx.JXLabel lbDay;
    private javax.swing.JFormattedTextField txtHBPC;
    private javax.swing.JTextField txtHCOM;
    private javax.swing.JFormattedTextField txtHDATE;
    private javax.swing.JFormattedTextField txtHPC;
    private javax.swing.JTextField txtHSIZE;
    private javax.swing.JTextField txtHSTG;
    // End of variables declaration//GEN-END:variables

    private void Update() {
        if(bDay)
        {
            try
            {
                harvestApp.HDAY = Integer.parseInt(txtHDATE.getText());
            }
            catch(Exception ex)
            {
                harvestApp.HDAY = null;
            }
        }
        else
        {
            try
            {
                harvestApp.HDATE = dpHDATE.getDate();
            }
            catch(Exception ex)
            {
                harvestApp.HDATE = null;
            }
        }

        if(txtHSTG.getText().equals("")) harvestApp.HSTG = "";
        else harvestApp.HSTG = HSTG;
        if(txtHCOM.getText().equals("")) harvestApp.HCOM = "";
        else harvestApp.HCOM = HCOM;
        if(txtHSIZE.getText().equals("")) harvestApp.HSIZE = "";
        else harvestApp.HSIZE = HSIZE;

        try
        {
            harvestApp.HPC = Float.parseFloat(txtHPC.getText());
        }
        catch(Exception ex)
        {
            harvestApp.HPC = null;
        }

        try
        {
            harvestApp.HBPC = Float.parseFloat(txtHBPC.getText());
        }
        catch(Exception ex)
        {
            harvestApp.HBPC = null;
        }
    }
    private void LoadHarvestApp() {
        if(bDay)
        {
            try
            {
                txtHDATE.setText(harvestApp.HDAY.toString());
            }
            catch(Exception ex)
            {
                txtHDATE.setText("");
            }
        }
        else
        {
            try
            {
                dpHDATE.setDate(harvestApp.HDATE);
            }
            catch(Exception ex)
            {
                dpHDATE.setDate(null);
            }
        }

        try
        {
            txtHSTG.setText(GrowthStageList.GetAt(harvestApp.HSTG, FileX.general.crop).Description);
            HSTG = harvestApp.HSTG;
        }
        catch(Exception ex)
        {
            txtHSTG.setText("");
        }

        try
        {
            txtHCOM.setText(HarvestComponentList.GetAt(harvestApp.HCOM).Description);
            HCOM = harvestApp.HCOM;
        }
        catch(Exception ex)
        {
            txtHCOM.setText("");
        }

        try
        {
            txtHSIZE.setText(HarvestSizeList.GetAt(harvestApp.HSIZE).Description);
            HSIZE = harvestApp.HSIZE;
        }
        catch(Exception ex)
        {
            txtHSIZE.setText("");
        }

        try
        {
            txtHPC.setText(harvestApp.HPC.toString());
        }
        catch(Exception ex)
        {
            txtHPC.setText("");
        }

        try
        {
            txtHBPC.setText(harvestApp.HBPC.toString());
        }
        catch(Exception ex)
        {
            txtHBPC.setText("");
        }
    }

    public HarvestApplication GetData() {
        return harvestApp;
    }

    void SetNull() {
        harvestApp = null;
    }

}
