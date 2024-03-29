/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * EnvironmentalAppDialog.java
 *
 * Created on 9 มี.ค. 2553, 15:21:17
 */

package xbuild;

import FileXModel.EnvironmentApplication;
import DSSATModel.EnvironmentFactorList;
import Extensions.Utils;
import Extensions.Variables;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Jazzy
 */
public class EnvironmentalAppDialog extends javax.swing.JDialog {

    /** Creates new form EnvironmentalAppDialog */

    protected EnvironmentApplication envApp;

    public EnvironmentalAppDialog(java.awt.Frame parent, boolean modal, EnvironmentApplication envApp) {
        super(parent, modal);
        initComponents();

        this.envApp = envApp;

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2 , (screenHeight - winSize.height) / 2);

        DefaultComboBoxModel dm1 = (DefaultComboBoxModel) cbDaylengthFactor.getModel();
        DefaultComboBoxModel dm2 = (DefaultComboBoxModel) cbRadiationFactor.getModel();
        DefaultComboBoxModel dm3 = (DefaultComboBoxModel) cbMaxTempFactor.getModel();
        DefaultComboBoxModel dm4 = (DefaultComboBoxModel) cbMinTempFactor.getModel();
        DefaultComboBoxModel dm5 = (DefaultComboBoxModel) cbPrecipitationFactor.getModel();
        DefaultComboBoxModel dm6 = (DefaultComboBoxModel) cbCo2Factor.getModel();
        DefaultComboBoxModel dm7 = (DefaultComboBoxModel) cbHumidityFactor.getModel();
        DefaultComboBoxModel dm8 = (DefaultComboBoxModel) cbWindFactor.getModel();
        for(int i = 0;i < EnvironmentFactorList.size();i++)
        {
            dm1.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm2.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm3.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm4.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm5.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm6.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm7.addElement(EnvironmentFactorList.GetAt(i).Description);
            dm8.addElement(EnvironmentFactorList.GetAt(i).Description);
        }

        SetEnvApp();
    }

    public void SetEnvApp(){
        try {
            dpDate.setDate(envApp.ODATE);
        } catch (Exception e) {
        }
        try {
            spDaylength.setValue(envApp.EDAY);
        } catch (Exception e) {
        }
        try {
            cbDaylengthFactor.setSelectedItem(envApp.EDAY_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtRadiation.setValue(envApp.ERAD);
        } catch (Exception e) {
        }
        try {
            cbRadiationFactor.setSelectedItem(envApp.ERAD_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtMaxTemp.setValue(envApp.EMAX);
        } catch (Exception e) {
        }
        try {
            cbMaxTempFactor.setSelectedItem(envApp.EMAX_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtMinTemp.setValue(envApp.EMIN);
        } catch (Exception e) {
        }
        try {
            cbMinTempFactor.setSelectedItem(envApp.EMIN_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtPrecipitation.setValue(envApp.ERAIN);
        } catch (Exception e) {
        }
        try {
            cbPrecipitationFactor.setSelectedItem(envApp.ERAIN_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtCo2.setValue(envApp.ECO2);
        } catch (Exception e) {
        }
        try {
            cbCo2Factor.setSelectedItem(envApp.ECO2_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtHumidity.setValue(envApp.EDEW);
        } catch (Exception e) {
        }
        try {
            cbHumidityFactor.setSelectedItem(envApp.EDEW_Fact.Description);
        } catch (Exception e) {
        }
        try {
            txtWind.setValue(envApp.EWIND);
        } catch (Exception e) {
        }
        try {
            cbWindFactor.setSelectedItem(envApp.EWIND_Fact.Description);
        } catch (Exception e) {
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dpDate = new org.jdesktop.swingx.JXDatePicker();
        cbDaylengthFactor = new javax.swing.JComboBox();
        cbRadiationFactor = new javax.swing.JComboBox();
        txtMaxTemp = new javax.swing.JFormattedTextField();
        cbMaxTempFactor = new javax.swing.JComboBox();
        txtMinTemp = new javax.swing.JFormattedTextField();
        cbMinTempFactor = new javax.swing.JComboBox();
        txtPrecipitation = new javax.swing.JFormattedTextField();
        cbPrecipitationFactor = new javax.swing.JComboBox();
        txtCo2 = new javax.swing.JFormattedTextField();
        cbCo2Factor = new javax.swing.JComboBox();
        txtHumidity = new javax.swing.JFormattedTextField();
        cbHumidityFactor = new javax.swing.JComboBox();
        txtWind = new javax.swing.JFormattedTextField();
        cbWindFactor = new javax.swing.JComboBox();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jXLabel2 = new org.jdesktop.swingx.JXLabel();
        jXLabel3 = new org.jdesktop.swingx.JXLabel();
        jXLabel4 = new org.jdesktop.swingx.JXLabel();
        jXLabel5 = new org.jdesktop.swingx.JXLabel();
        jXLabel6 = new org.jdesktop.swingx.JXLabel();
        jXLabel7 = new org.jdesktop.swingx.JXLabel();
        jXLabel8 = new org.jdesktop.swingx.JXLabel();
        jButton1 = new javax.swing.JButton();
        bnCancel = new javax.swing.JButton();
        jXLabel9 = new org.jdesktop.swingx.JXLabel();
        jXLabel10 = new org.jdesktop.swingx.JXLabel();
        jXLabel11 = new org.jdesktop.swingx.JXLabel();
        jXLabel12 = new org.jdesktop.swingx.JXLabel();
        jXLabel13 = new org.jdesktop.swingx.JXLabel();
        jXLabel14 = new org.jdesktop.swingx.JXLabel();
        jXLabel15 = new org.jdesktop.swingx.JXLabel();
        jXLabel16 = new org.jdesktop.swingx.JXLabel();
        jXLabel17 = new org.jdesktop.swingx.JXLabel();
        jLabel1 = new javax.swing.JLabel();
        spDaylength = new javax.swing.JFormattedTextField();
        txtRadiation = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        dpDate.setFormats(Variables.getDateFormat());

        txtMaxTemp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtMaxTemp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtMinTemp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtMinTemp.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtPrecipitation.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecipitation.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtCo2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtCo2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtHumidity.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtHumidity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtWind.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtWind.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jXLabel1.setText("Daylength");

        jXLabel2.setText("Radiation");

        jXLabel3.setText("Max Temp");

        jXLabel4.setText("Min Temp");

        jXLabel5.setText("Precipitation");

        jXLabel6.setText("<html>CO<sub>2</sub></html>");

        jXLabel7.setText("Humidity %");

        jXLabel8.setText("Wind");

        jButton1.setText("Ok");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        bnCancel.setText("Cancel");
        bnCancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bnCancelMouseClicked(evt);
            }
        });
        bnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnCancelActionPerformed(evt);
            }
        });

        jXLabel9.setText("Date");

        jXLabel10.setText("hours");

        jXLabel11.setText("<html>MJ/m<sup>2</sup>/d</html>");

        jXLabel12.setText("<html><sup>๐</sup>C</html>");

        jXLabel13.setText("<html><sup>๐</sup>C</html>");

        jXLabel14.setText("mm");

        jXLabel15.setText("vpm");

        jXLabel16.setText("%");

        jXLabel17.setText("km/hours");

        jLabel1.setText(Variables.getDateFormatString());

        spDaylength.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        spDaylength.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        txtRadiation.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtRadiation.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbDaylengthFactor, 0, 184, Short.MAX_VALUE)
                    .addComponent(cbRadiationFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMaxTemp)
                    .addComponent(cbMaxTempFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtMinTemp)
                    .addComponent(cbMinTempFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrecipitation)
                    .addComponent(cbPrecipitationFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCo2)
                    .addComponent(cbCo2Factor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtHumidity)
                    .addComponent(cbHumidityFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtWind)
                    .addComponent(cbWindFactor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dpDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(spDaylength)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnCancel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(txtRadiation))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(dpDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jXLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spDaylength, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDaylengthFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jXLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRadiation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbRadiationFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaxTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMaxTempFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMinTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbMinTempFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecipitation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPrecipitationFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbCo2Factor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHumidity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbHumidityFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtWind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbWindFactor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jButton1)
                    .addComponent(bnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public EnvironmentApplication GetData()
    {
        return envApp;
    }
    public void SetNull()
    {
        envApp = null;
    }

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        try {
            envApp.ODATE = dpDate.getDate();
        } catch (Exception e) {
            envApp.ODATE = null;
        }
        try {
            envApp.EDAY = Utils.ParseDouble(spDaylength.getValue());
        } catch (Exception e) {
            envApp.EDAY = 0.0;
        }
        envApp.EDAY_Fact = EnvironmentFactorList.GetAt(1, (String) cbDaylengthFactor.getSelectedItem());
        
        try {
            envApp.ERAD = Utils.ParseDouble(txtRadiation.getValue());
        } catch (Exception e) {
            envApp.ERAD = 0.0;
        }
        envApp.ERAD_Fact = EnvironmentFactorList.GetAt(1, (String) cbRadiationFactor.getSelectedItem());
        
        try {
            envApp.EMAX = Utils.ParseDouble(txtMaxTemp.getValue());
        } catch (Exception e) {
            envApp.EMAX = 0.0;
        }
        envApp.EMAX_Fact = EnvironmentFactorList.GetAt(1, (String) cbMaxTempFactor.getSelectedItem());
        
        try {
            envApp.EMIN = Utils.ParseDouble(txtMinTemp.getValue());
        } catch (Exception e) {
            envApp.EMIN = 0.0;
        }
        envApp.EMIN_Fact = EnvironmentFactorList.GetAt(1, (String) cbMinTempFactor.getSelectedItem());
        
        try {
            envApp.ERAIN = Utils.ParseDouble(txtPrecipitation.getValue());
        } catch (Exception e) {
            envApp.ERAIN = 0.0;
        }
        envApp.ERAIN_Fact = EnvironmentFactorList.GetAt(1, (String) cbPrecipitationFactor.getSelectedItem());
        
        try {
            envApp.ECO2 = Utils.ParseDouble(txtCo2.getValue());
        } catch (Exception e) {
            envApp.ECO2 = 0.0;
        }
        envApp.ECO2_Fact = EnvironmentFactorList.GetAt(1, (String) cbCo2Factor.getSelectedItem());
        
        try {
            envApp.EDEW = Utils.ParseDouble(txtHumidity.getValue());
        } catch (Exception e) {
            envApp.EDEW = 0.0;
        }
        envApp.EDEW_Fact = EnvironmentFactorList.GetAt(1, (String) cbHumidityFactor.getSelectedItem());
        
        try {
            envApp.EWIND = Utils.ParseDouble(txtWind.getValue());
        } catch (Exception e) {
            envApp.EWIND = 0.0;
        }
        envApp.EWIND_Fact = EnvironmentFactorList.GetAt(1, (String) cbWindFactor.getSelectedItem());

        dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void bnCancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bnCancelMouseClicked
        envApp = null;
        dispose();
    }//GEN-LAST:event_bnCancelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1MouseClicked(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnCancelActionPerformed
        envApp = null;
        dispose();
    }//GEN-LAST:event_bnCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnCancel;
    private javax.swing.JComboBox cbCo2Factor;
    private javax.swing.JComboBox cbDaylengthFactor;
    private javax.swing.JComboBox cbHumidityFactor;
    private javax.swing.JComboBox cbMaxTempFactor;
    private javax.swing.JComboBox cbMinTempFactor;
    private javax.swing.JComboBox cbPrecipitationFactor;
    private javax.swing.JComboBox cbRadiationFactor;
    private javax.swing.JComboBox cbWindFactor;
    private org.jdesktop.swingx.JXDatePicker dpDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXLabel jXLabel10;
    private org.jdesktop.swingx.JXLabel jXLabel11;
    private org.jdesktop.swingx.JXLabel jXLabel12;
    private org.jdesktop.swingx.JXLabel jXLabel13;
    private org.jdesktop.swingx.JXLabel jXLabel14;
    private org.jdesktop.swingx.JXLabel jXLabel15;
    private org.jdesktop.swingx.JXLabel jXLabel16;
    private org.jdesktop.swingx.JXLabel jXLabel17;
    private org.jdesktop.swingx.JXLabel jXLabel2;
    private org.jdesktop.swingx.JXLabel jXLabel3;
    private org.jdesktop.swingx.JXLabel jXLabel4;
    private org.jdesktop.swingx.JXLabel jXLabel5;
    private org.jdesktop.swingx.JXLabel jXLabel6;
    private org.jdesktop.swingx.JXLabel jXLabel7;
    private org.jdesktop.swingx.JXLabel jXLabel8;
    private org.jdesktop.swingx.JXLabel jXLabel9;
    private javax.swing.JFormattedTextField spDaylength;
    private javax.swing.JFormattedTextField txtCo2;
    private javax.swing.JFormattedTextField txtHumidity;
    private javax.swing.JFormattedTextField txtMaxTemp;
    private javax.swing.JFormattedTextField txtMinTemp;
    private javax.swing.JFormattedTextField txtPrecipitation;
    private javax.swing.JFormattedTextField txtRadiation;
    private javax.swing.JFormattedTextField txtWind;
    // End of variables declaration//GEN-END:variables

}
