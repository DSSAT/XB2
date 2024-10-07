/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GeneralNotesFrame.java
 *
 * Created on 21 พ.ค. 2553, 11:15:27
 */

package xbuild;

import FileXModel.FileX;
import FileXModel.ModelXBase;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import xbuild.Components.IXInternalFrame;

/**
 *
 * @author Jazzy
 */
public class GeneralNotesFrame extends IXInternalFrame implements KeyListener {

    public IXInternalFrame NewFrame(){
        return new GeneralNotesFrame();
    }
    
    /** Creates new form GeneralNotesFrame */
    public GeneralNotesFrame() {
        super();
        initComponents();

        txtNotes.setText(FileX.general.Notes);

        txtNotes.addKeyListener(this);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNotes = new javax.swing.JTextArea();

        setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        setPreferredSize(new java.awt.Dimension(767, 677));

        jXLabel1.setText("Notes");

        txtNotes.setColumns(20);
        txtNotes.setRows(5);
        jScrollPane1.setViewportView(txtNotes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                    .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private javax.swing.JTextArea txtNotes;
    // End of variables declaration//GEN-END:variables

    public void keyTyped(KeyEvent e) {
        FileX.general.Notes = txtNotes.getText();
    }

    public void keyPressed(KeyEvent e) {
        FileX.general.Notes = txtNotes.getText();
    }

    public void keyReleased(KeyEvent e) {
        
        String tmp = txtNotes.getText();
        String txt = "";
        int n = 1;
        for(int i = 0;i < tmp.length();i++){
            if(n % 80 == 0)
            {
                //try {
                //    if (tmp.charAt(i + 1) != '\n') {
                        txt += "\n";
                //    }
                //} catch (Exception ex) {
                //    System.out.println(ex.getMessage());
                //}
                n = 1;
            }
             else if(tmp.charAt(i) == '\n')
             {
                 txt += "\n";
                 n = 1;
             }
            else if(tmp.charAt(i) != '\n')
            {
                try {
                    if (!(txt.charAt(i - 1) == '\n' && tmp.charAt(i) == ' ')) {
                        txt += tmp.charAt(i);
                        n++;
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    txt += tmp.charAt(i);
                    n++;
                }
            }
        }

        txtNotes.setText(txt);
        
        FileX.general.Notes = txtNotes.getText();
    }

    @Override
    public String getParentName() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelXBase newModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
