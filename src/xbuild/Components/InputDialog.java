package xbuild.Components;

import Extensions.LimitDocument;
import javax.swing.JOptionPane;

/**
 *
 * @author JAZZJAIKLA
 */
public class InputDialog extends javax.swing.JDialog {

    /**
     * Creates new form InputDialog
     */
    
    private boolean isOK = false;
    private String description;     
    
    public InputDialog(java.awt.Frame parent, boolean modal, String defaultDescription) {
        super(parent, modal);
        commonConstructor(parent, defaultDescription);
    }
    
    public InputDialog(java.awt.Frame parent, boolean modal, String defaultDescription, int maxChar) {
        super(parent, modal);
        commonConstructor(parent, defaultDescription);
        txtDescription.setDocument(new LimitDocument(maxChar));
        txtDescription.setText(defaultDescription);
    }
    
    private void commonConstructor(java.awt.Frame parent, String defaultDescription){
        initComponents();
        txtDescription.setText(defaultDescription);
        
        this.setLocationByPlatform(true);
        this.setLocationRelativeTo(parent);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtDescription = new javax.swing.JTextField();
        bnOK = new javax.swing.JButton();
        bnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Input");
        setAlwaysOnTop(true);

        jLabel1.setText("Please enter your description");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDescription))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addComponent(bnOK)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bnCancel)))
                .addContainerGap(123, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnOK)
                    .addComponent(bnCancel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnOKActionPerformed
        if(txtDescription.getText().length() > 0){
            isOK = true;
            description = txtDescription.getText();
            dispose();
        }
        else{
            JOptionPane.showMessageDialog(this, "Please provide description", "Invalid!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_bnOKActionPerformed

    private void bnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnCancelActionPerformed
        isOK = false;
        description = "";
        dispose();
    }//GEN-LAST:event_bnCancelActionPerformed

    public boolean isOK(){
        return isOK;
    }
    
    public String getDescription(){
        return description;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnCancel;
    private javax.swing.JButton bnOK;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtDescription;
    // End of variables declaration//GEN-END:variables
}
