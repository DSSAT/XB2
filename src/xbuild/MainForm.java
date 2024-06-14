/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * MainForm.java
 *
 * Created on Mar 11, 2010, 11:27:37 AM
 */
package xbuild;

import xbuild.Events.RemoveLevelEvent;
import xbuild.Events.XEvent;
import xbuild.Events.AddLevelEvent;
import FileXModel.FileX;
import DSSATModel.DssatProfile;
import DSSATModel.ExperimentType;
import DSSATModel.Setup;
import DSSATModel.SimulationControlDefaults;
import Extensions.Utils;
import Extensions.Variables;
import FileXModel.ManagementList;
import FileXModel.ModelXBase;
import FileXModel.Treatment;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.util.logging.*;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.jdesktop.swingx.JXFrame;
import FileXService.FileXService;
import FileXService.FileXValidationService;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import xbuild.Components.CustomDefaultTreeCellRenderer;
import xbuild.Components.IXInternalFrame;
import xbuild.Components.InputDialog;
import xbuild.Components.XInternalFrame;
import xbuild.Events.FieldUpdateEvent;
import xbuild.Events.MenuDirection;
import xbuild.Events.NewFrameEvent;
import xbuild.Events.SelectionEvent;
import xbuild.Events.UpdateLevelEvent;
import xbuild.Events.ValidationEvent;
import xbuild.Events.XEventListener;

/**
 *
 * @author Jazzy
 */
public class MainForm extends javax.swing.JFrame implements XEventListener {

    /**
     * Creates new form MainForm
     */
    protected Content content;

    private final HashMap<String, String> mainMenuList = new HashMap<String, String>() {
        {
            put("General Information", "GeneralInfoFrame");
            put("Notes", "GeneralNotesFrame");
            put("Fields", "FieldFrame");
            put("Initial Conditions", "InitialConditionFrame");
            put("Soil Analysis", "SoilAnalysisFrame");
            put("Environmental Modifications", "EnvironmentalFrame");
            put("Cultivars", "CultivarsFrame");
            put("Planting", "PlantingFrame");
            put("Irrigation", "IrrigationFrame");
            put("Fertilizer", "FertilizerFrame");
            put("Organic Amendments", "OrganicFrame");
            put("Tillage", "TillageFrame");
            put("Harvest", "HarvestFrame");
            put("Chemical Applications", "ChemicalFrame");
            put("Simulation Controls", "SimulationFrame");
            put("Treatments", "TreatmentFrame");
        }
    };

    private final ArrayList<String> menuNoFrame = new ArrayList<String>() {
        {
            add("Fields");
            add("Initial Conditions");
            add("Soil Analysis");
            add("Environmental Modifications");
            add("Planting");
            add("Irrigation");
            add("Fertilizer");
            add("Organic Amendments");
            add("Tillage");
            add("Harvest");
            add("Chemical Applications");
            add("Simulation Controls");

        }
    };
    private final ArrayList<String> menuIgnore = new ArrayList<String>() {
        {
            add("General Information");
            add("Notes");
        }
    };
    
    private final ArrayList<String> menuRequired = new ArrayList<String>(){
        {
            add("General Information");
            add("Fields");
            add("Cultivars");
            add("Planting");
            add("Simulation Controls");
            add("Treatments");
        }
    };
    
    private final ArrayList<String> menuAll = new ArrayList<String>(){
        {
            add("General Information");
            add("Fields");
            add("Initial Conditions");
            add("Soil Analysis");
            add("Environmental Modifications");
            add("Cultivars");
            add("Planting");
            add("Irrigation");
            add("Fertilizer");
            add("Organic Amendments");
            add("Tillage");
            add("Harvest");
            add("Chemical Applications");
            add("Simulation Controls");
            add("Treatments");
        }
    };
    
    private String currentFrameName = "General Information";

    public MainForm() {

        initComponents();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2, (screenHeight - winSize.height) / 2);

        jXTree1.setVisible(false);
        jXTree1.setCellRenderer(new CustomDefaultTreeCellRenderer());

        setIconImage(Variables.getIconImage(getClass()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenuAdd = new javax.swing.JPopupMenu();
        jMenuItemSimAdd = new javax.swing.JMenuItem();
        jPopupMenuItem = new javax.swing.JPopupMenu();
        jPopupMenuSimItemCopy = new javax.swing.JMenuItem();
        jPopupMenuSimItemRename = new javax.swing.JMenuItem();
        jPopupMenuSimItemRemove = new javax.swing.JMenuItem();
        jPopupMenuSimItemMoveUp = new javax.swing.JMenuItem();
        jPopupMenuSimItemMoveDown = new javax.swing.JMenuItem();
        desktopPane = new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jXTree1 = new org.jdesktop.swingx.JXTree();
        bnPrevious = new javax.swing.JButton();
        bnNext = new javax.swing.JButton();
        bnAddLevel = new javax.swing.JButton();
        bnDeleteLevel = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuNewFile = new javax.swing.JMenuItem();
        jMenuOpenFile = new javax.swing.JMenuItem();
        jMenuCloseFile = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuSaveFile = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuExit = new javax.swing.JMenuItem();
        jMenuRefresh = new javax.swing.JMenu();
        jSetupMenu = new javax.swing.JMenu();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItemSimAdd.setText("Add New");
        jMenuItemSimAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSimAddActionPerformed(evt);
            }
        });
        jPopupMenuAdd.add(jMenuItemSimAdd);

        jPopupMenuSimItemCopy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file_copy.png"))); // NOI18N
        jPopupMenuSimItemCopy.setText("Copy Level");
        jPopupMenuSimItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopupMenuSimItemCopyActionPerformed(evt);
            }
        });
        jPopupMenuItem.add(jPopupMenuSimItemCopy);

        jPopupMenuSimItemRename.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/text_format.png"))); // NOI18N
        jPopupMenuSimItemRename.setText("Rename Level");
        jPopupMenuSimItemRename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopupMenuSimItemRenameActionPerformed(evt);
            }
        });
        jPopupMenuItem.add(jPopupMenuSimItemRename);

        jPopupMenuSimItemRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete_forever.png"))); // NOI18N
        jPopupMenuSimItemRemove.setText("Remove Level");
        jPopupMenuSimItemRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopupMenuSimItemRemoveActionPerformed(evt);
            }
        });
        jPopupMenuItem.add(jPopupMenuSimItemRemove);

        jPopupMenuSimItemMoveUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Up.png"))); // NOI18N
        jPopupMenuSimItemMoveUp.setText("Move Up");
        jPopupMenuSimItemMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopupMenuSimItemMoveUpActionPerformed(evt);
            }
        });
        jPopupMenuItem.add(jPopupMenuSimItemMoveUp);

        jPopupMenuSimItemMoveDown.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/Down.png"))); // NOI18N
        jPopupMenuSimItemMoveDown.setText("Move Down");
        jPopupMenuSimItemMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPopupMenuSimItemMoveDownActionPerformed(evt);
            }
        });
        jPopupMenuItem.add(jPopupMenuSimItemMoveDown);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(" XB2 v0.11.0.0.");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jXTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jXTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jXTree1MouseReleased(evt);
            }
        });
        jXTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jXTree1ValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jXTree1);

        bnPrevious.setText("Previous Section");
        bnPrevious.setEnabled(false);
        bnPrevious.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnPreviousActionPerformed(evt);
            }
        });

        bnNext.setText("Next Section");
        bnNext.setEnabled(false);
        bnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnNextActionPerformed(evt);
            }
        });

        bnAddLevel.setText("Add Level");
        bnAddLevel.setEnabled(false);
        bnAddLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnAddLevelActionPerformed(evt);
            }
        });

        bnDeleteLevel.setText("Delete Level");
        bnDeleteLevel.setEnabled(false);
        bnDeleteLevel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnDeleteLevelActionPerformed(evt);
            }
        });

        jMenuFile.setText("File");

        jMenuNewFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/filenew.png"))); // NOI18N
        jMenuNewFile.setText("New File");
        jMenuNewFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuNewFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuNewFile);

        jMenuOpenFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/fileopen.png"))); // NOI18N
        jMenuOpenFile.setText("Open File");
        jMenuOpenFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuOpenFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuOpenFile);

        jMenuCloseFile.setText("Close File");
        jMenuCloseFile.setEnabled(false);
        jMenuCloseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCloseFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuCloseFile);
        jMenuFile.add(jSeparator1);

        jMenuSaveFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/16/filesave.png"))); // NOI18N
        jMenuSaveFile.setText("Save File");
        jMenuSaveFile.setEnabled(false);
        jMenuSaveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSaveFileActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuSaveFile);
        jMenuFile.add(jSeparator2);

        jMenuExit.setText("Exit");
        jMenuExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenuExitMouseClicked(evt);
            }
        });
        jMenuFile.add(jMenuExit);

        jMenuBar1.add(jMenuFile);

        jMenuRefresh.setText("Reload");
        jMenuRefresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuRefreshMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuRefresh);

        jSetupMenu.setText("Setup");
        jSetupMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSetupMenuMouseClicked(evt);
            }
        });
        jMenuBar1.add(jSetupMenu);

        jMenuHelp.setText("Help");

        jMenuItem1.setText("Content");
        jMenuItem1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem1MouseClicked(evt);
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItem1);

        jMenuItem2.setText("Technical Support");
        jMenuHelp.add(jMenuItem2);

        jMenuItem3.setText("About");
        jMenuHelp.add(jMenuItem3);

        jMenuBar1.add(jMenuHelp);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 962, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bnAddLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnDeleteLevel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(bnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnAddLevel)
                    .addComponent(bnDeleteLevel)
                    .addComponent(bnPrevious)
                    .addComponent(bnNext))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(desktopPane))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName(" XB2 v0.11.0.0.");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @SuppressWarnings("empty-statement")
    private void jXTree1ValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jXTree1ValueChanged
        showFrame();
}//GEN-LAST:event_jXTree1ValueChanged

    private void jMenuExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuExitMouseClicked
        if(onClose()){
            dispose();
        }
    }//GEN-LAST:event_jMenuExitMouseClicked

    private void jMenuRefreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuRefreshMouseClicked
        Setup setup = new Setup();
        new LoadingDataFrame(setup.GetDSSATPath()).show();
}//GEN-LAST:event_jMenuRefreshMouseClicked

    private void jSetupMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSetupMenuMouseClicked
        SetupFrame frame = new SetupFrame();
        frame.show();

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent evt) {
                if (frame.IsOk) {
                    Setup setup = new Setup();
                    new LoadingDataFrame(setup.GetDSSATPath()).show();
                }
            }
        });
    }//GEN-LAST:event_jSetupMenuMouseClicked

    private void jMenuNewFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuNewFileActionPerformed
        jMenuSaveFile.setEnabled(true);
        jMenuCloseFile.setEnabled(true);
        jMenuOpenFile.setEnabled(false);
        jMenuNewFile.setEnabled(false);

        FileX.NewFileX();

        ResetTree();
        jXTree1.setVisible(true);

        GeneralInfoFrame generalFrame = new GeneralInfoFrame();

        setRootPaneCheckingEnabled(false);
        javax.swing.plaf.InternalFrameUI ui = generalFrame.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);

        desktopPane.add(generalFrame);
        try {
            generalFrame.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        generalFrame.show();

        generalFrame.addMyEventListener(this);
        
        setAddDeleteButton();
        setPrevNextButton();
        
        FileX.isReady = true;
    }//GEN-LAST:event_jMenuNewFileActionPerformed

    private void jMenuSaveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSaveFileActionPerformed
        if (FileX.treatments.GetSize() == 0) {
            final ConfirmDialog d = new ConfirmDialog(this, true);
            d.show();

            d.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    if (d.isContinue) {
                        saveFile();
                    }
                }
            });
        } else {
            saveFile();
        }
        
        FileX.isDirty = false;
        EventQueue.invokeLater(() -> {
                jXTree1.repaint();
            });
    }//GEN-LAST:event_jMenuSaveFileActionPerformed

    private void saveFile() {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();

        String target;
        
        if(FileX.GetAbsoluteFileName() == null || "".equals(FileX.GetAbsoluteFileName())){
            try {
                if (FileX.general.crop != null && FileX.general.crop.CropCode != null && !"".equals(FileX.general.crop.CropCode)) {
                    target = DssatProfile.GetAt(FileX.general.crop.CropCode + "D");
                } else if (FileX.general.FileType == ExperimentType.Seasonal) {
                    target = DssatProfile.GetAt("ASD");
                } else if (FileX.general.FileType == ExperimentType.Sequential) {
                    target = DssatProfile.GetAt("AQD");
                } else if (FileX.general.FileType == ExperimentType.Spatial) {
                    target = DssatProfile.GetAt("APD");
                } else {
                    target = new Setup().GetDSSATPath();
                }
            } catch (Exception e) {
                target = new Setup().GetDSSATPath();
            }
        }else{
            File f = new File(FileX.GetAbsoluteFileName());
            target = f.getPath().replace(f.getName(), "");            
        }
        
        File file = new File(target + "\\" + root.getUserObject().toString().replace("*", ""));
        File path = new File(file.getPath().replace(file.getName(), "" ));
        
        if (!path.exists()) {
            path.mkdirs();
        }
        FileXService.SaveFile(file);
    }
    private void jMenuCloseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCloseFileActionPerformed
        onClose();
    }//GEN-LAST:event_jMenuCloseFileActionPerformed

    private boolean onClose(){
        
        boolean result = false;
        
        if(FileX.isDirty){
            int confirmSave = JOptionPane.showConfirmDialog(null, "Do you want you want to save the file?", "XB2", JOptionPane.YES_NO_CANCEL_OPTION);

            if (confirmSave == 2) // Cancel
            {
                return false;
            }
            else if (confirmSave == 0) //Yes
            {
                saveFile();
                result = true;
            }
        }

        jMenuNewFile.setEnabled(true);
        jMenuSaveFile.setEnabled(false);
        jMenuCloseFile.setEnabled(false);
        jMenuOpenFile.setEnabled(true);

        for (JInternalFrame innerFrame : desktopPane.getAllFrames()) {
            innerFrame.dispose();
        }
        jXTree1.setVisible(false);

        FileX.CloseFile();
        setPrevNextButton();
        setAddDeleteButton();
        
        return true;
    }
    
    private void jMenuOpenFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuOpenFileActionPerformed
        JFileChooser fc = new JFileChooser(new Setup().GetDSSATPath());
        FileFilter filter1 = new ExtensionFileFilter("File x", new String[]{"x", "X"});

        fc.setFileFilter(filter1);

        int returnVal = fc.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ResetTree();

            File file = fc.getSelectedFile();

            FileXService.OpenFileX(file);

            DefaultMutableTreeNode root = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
            root.setUserObject(file.getName());

            AddTreeMenu("Environment", "Fields");
            AddTreeMenu("Environment", "Initial Conditions");
            AddTreeMenu("Environment", "Soil Analysis");
            AddTreeMenu("Environment", "Environmental Modifications");
            AddTreeMenu("Management", "Cultivars");
            AddTreeMenu("Management", "Planting");
            AddTreeMenu("Management", "Irrigation");
            AddTreeMenu("Management", "Fertilizer");
            AddTreeMenu("Management", "Organic Amendments");
            AddTreeMenu("Management", "Tillage");
            AddTreeMenu("Management", "Harvest");
            AddTreeMenu("Management", "Chemical Applications");
            AddTreeMenu(root, "Simulation Controls");
            AddTreeMenu(root, "Treatments");

            jXTree1.collapseAll();
            jXTree1.expandAll();
            jXTree1.setVisible(true);

            GeneralInfoFrame generalFrame = new GeneralInfoFrame();

            setRootPaneCheckingEnabled(false);
            javax.swing.plaf.InternalFrameUI ui = generalFrame.getUI();
            ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);

            desktopPane.add(generalFrame);
            try {
                generalFrame.setMaximum(true);
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            generalFrame.show();
            generalFrame.addMyEventListener(this);

            jMenuNewFile.setEnabled(false);
            jMenuSaveFile.setEnabled(true);
            jMenuCloseFile.setEnabled(true);
            jMenuOpenFile.setEnabled(false);
            
            FileX.isReady = true;
        }
        
        setAddDeleteButton();
        setPrevNextButton();
    }//GEN-LAST:event_jMenuOpenFileActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem1MouseClicked
        desktopPane.add(content);
        content.show();
    }//GEN-LAST:event_jMenuItem1MouseClicked

    private void jXTree1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jXTree1MouseReleased
        int row = jXTree1.getClosestRowForLocation(evt.getX(), evt.getY());
        jXTree1.setSelectionRow(row);
        
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        if (node == null) {
            return;
        }

        boolean enabled = true;
        String nodeName = node.toString();

        if (node.getParent() != null && !nodeName.equals("General Information")) {
            enabled = FileXValidationService.IsGeneralValid();
        }

        if (nodeName.equals("Cultivars")) {
            enabled = FileXValidationService.IsCropEnabled()
                    && FileXValidationService.IsGeneralValid();
        }
        else if (nodeName.equals("Treatments")) {
            enabled = FileXValidationService.IsMinimumRequired();
        }

        if (!enabled) {
            return;
        }

        if (node.getParent() != null && mainMenuList.keySet().contains(node.toString()) && !menuIgnore.contains(node.toString())) {
            if (SwingUtilities.isRightMouseButton(evt) || node.getChildCount() == 0) {
                jPopupMenuAdd.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        } else if (SwingUtilities.isRightMouseButton(evt) && node.getParent() != null && mainMenuList.keySet().contains(node.getParent().toString())) {
            jPopupMenuSimItemCopy.setEnabled(true);
            jPopupMenuSimItemRename.setEnabled(true);
            if ("Cultivars".equals(node.getParent().toString())) {
                jPopupMenuSimItemCopy.setEnabled(false);
                jPopupMenuSimItemRename.setEnabled(false);
            }

            EventQueue.invokeLater(() -> {
                jPopupMenuItem.show(evt.getComponent(), evt.getX(), evt.getY());
            });
        }
    }//GEN-LAST:event_jXTree1MouseReleased
    
    private void jMenuItemSimAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSimAddActionPerformed
        addLevel();
    }//GEN-LAST:event_jMenuItemSimAddActionPerformed

    private void jPopupMenuSimItemRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopupMenuSimItemRemoveActionPerformed
        // TODO add your handling code here:
        removeLevel();
    }//GEN-LAST:event_jPopupMenuSimItemRemoveActionPerformed

    private void jPopupMenuSimItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopupMenuSimItemCopyActionPerformed
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

        ManagementList modelList = GetManagementList(parentNode.toString());
        String newName = modelList.GetCopyName(node.toString().split(":")[1].trim());

        String r = JOptionPane.showInputDialog(new JXFrame(), "Please enter your description", newName);
        if (r.length() > 0) {
            if (modelList.GetAt(r) != null) {
                JOptionPane.showMessageDialog(new JXFrame(), "This name is already add", "ERROR", 0);
                return;
            }

            int index = modelList.GetIndex(getLevel(node.toString()));
            ModelXBase modelClone = modelList.Clone(index, r);
            
            if(modelClone.getClass() == Treatment.class && FileX.general.FileType == ExperimentType.Sequential){
                modelClone.SetLevel(modelList.GetAtIndex(modelList.GetSize() - 1).GetLevel());
                Integer R = Utils.ParseInteger(((Treatment)modelList.GetAtIndex(modelList.GetSize() - 1)).R) + 1;
                ((Treatment)modelClone).R = R.toString();
            }
            else{
                modelClone.SetLevel(modelList.GetAtIndex(modelList.GetSize() - 1).GetLevel() + 1);
            }
            
            
            modelList.AddNew(modelClone);

            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
            String newCopyName = "Level " + modelClone.GetLevel() + ": " + r;
            newNode.setUserObject(newCopyName);
            parentNode.add(newNode);

            DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
            model.reload(parentNode);
            jXTree1.expandAll();

            int[] rows = jXTree1.getSelectionRows();
            if (rows.length > 0) {
                jXTree1.setSelectionRow(rows[0] + FileX.simulationList.GetSize());
            }

            IXInternalFrame frame = XInternalFrame.newInstance(mainMenuList.get(parentNode.toString()), newNode.toString());
            ShowFrame(frame);
        }
    }//GEN-LAST:event_jPopupMenuSimItemCopyActionPerformed

    private void jPopupMenuSimItemRenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopupMenuSimItemRenameActionPerformed
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

        ManagementList modelList = GetManagementList(parentNode.toString());
        
        int[] selectRows = {0};
        GetNodeIndex(parentNode, node.toString(), selectRows);
        int level = selectRows[0] - 1;        
        ModelXBase model = modelList.GetAt(level);
        String oldName = model.GetName();

        String r = JOptionPane.showInputDialog(new JXFrame(), "Please enter your description", oldName);
        if ((null == oldName ? r != null : !oldName.equals(r)) && 0 <= r.length()) {
            if (modelList.GetAt(r) != null) {
                JOptionPane.showMessageDialog(new JXFrame(), "This name is already add", "ERROR", 0);
                return;
            }

            model.SetName(r);
            String newName = "Level " + model.GetLevel() + ": " + r;
            node.setUserObject(newName);

            DefaultTreeModel treeModel = (DefaultTreeModel) jXTree1.getModel();
            treeModel.reload(parentNode);
            jXTree1.expandAll();

            IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
            currentFrame.updatePanelName(newName);
            currentFrame.updatePanelList();
        }
    }//GEN-LAST:event_jPopupMenuSimItemRenameActionPerformed

    private void jPopupMenuSimItemMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopupMenuSimItemMoveUpActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

        ManagementList modelList = GetManagementList(parentNode.toString());
        int[] selectRows = {0};
        GetNodeIndex(parentNode, node.toString(), selectRows);
        int level = selectRows[0] - 1;

        if (modelList.MoveUp(level)) {

            EventQueue.invokeLater(() -> {
                ModelXBase modelCurrent = modelList.GetAtIndex(level);
                String newName = "Level " + (modelCurrent.GetLevel()) + ": " + modelCurrent.GetName();
                node.setUserObject(newName);
                IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
                currentFrame.updatePanelName(newName);

                ModelXBase modelUp = modelList.GetAtIndex(level - 1);
                DefaultMutableTreeNode nodeUp = (DefaultMutableTreeNode) parentNode.getChildAt(level - 1);
                String newUpName = "Level " + modelUp.GetLevel() + ": " + modelUp.GetName();
                nodeUp.setUserObject(newUpName);

                DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
                model.reload(parentNode);
            });
        }
    }//GEN-LAST:event_jPopupMenuSimItemMoveUpActionPerformed

    protected int getLevel(String nodeName) {
        String[] level1 = nodeName.split(":");
        String[] level2 = level1[0].split(" ");

        return Integer.parseInt(level2[1]);
    }
    
    private void jPopupMenuSimItemMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPopupMenuSimItemMoveDownActionPerformed
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

        ManagementList modelList = GetManagementList(parentNode.toString());
        int[] selectRows = {0};
        GetNodeIndex(parentNode, node.toString(), selectRows);
        int level = selectRows[0] - 1;

        if (modelList.MoveDown(level)) {
            EventQueue.invokeLater(() -> {
                ModelXBase modelCurrent = modelList.GetAtIndex(level);
                String newName = "Level " + modelCurrent.GetLevel() + ": " + modelCurrent.GetName();
                node.setUserObject(newName);
                IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
                currentFrame.updatePanelName(newName);

                ModelXBase modelDown = modelList.GetAtIndex(level + 1);
                DefaultMutableTreeNode nodeUp = (DefaultMutableTreeNode) parentNode.getChildAt(level + 1);
                String newUpName = "Level " + modelDown.GetLevel() + ": " + modelDown.GetName();
                nodeUp.setUserObject(newUpName);

                DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
                model.reload(parentNode);
            });
        }
    }//GEN-LAST:event_jPopupMenuSimItemMoveDownActionPerformed

    private void bnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnNextActionPerformed
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        ArrayList<String> nodeList = new ArrayList<>();
        getCellIndex(rootNode, nodeList);
        
        IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
        String management = currentFrame.getManagementName();

        int mIndex = menuAll.indexOf(management) + 1;

        String frameName = menuAll.get(mIndex);
        int select = nodeList.indexOf(frameName);

        showTargetFrame(frameName, select, nodeList, MenuDirection.NEXT);
    }//GEN-LAST:event_bnNextActionPerformed

    private void bnPreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnPreviousActionPerformed
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        ArrayList<String> nodeList = new ArrayList<>();
        getCellIndex(rootNode, nodeList);

        int mIndex = menuAll.indexOf(currentFrameName) - 1;

        String frameName = menuAll.get(mIndex);
        int select = nodeList.indexOf(frameName);

        showTargetFrame(frameName, select, nodeList, MenuDirection.PREVIOUS);
    }//GEN-LAST:event_bnPreviousActionPerformed

    private void bnAddLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnAddLevelActionPerformed
        IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
        
        String managementName = currentFrame.getManagementName();

        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        int[] selectRows = {0};
        GetNodeIndex(rootNode, managementName, selectRows);
        jXTree1.setSelectionRow(selectRows[0]);
        
        DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();        
        ManagementList modelList = currentFrame.getManagementList();        

        addNewLevel(parentNode, modelList);
    }//GEN-LAST:event_bnAddLevelActionPerformed

    private void bnDeleteLevelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnDeleteLevelActionPerformed
        IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
        String managementName = currentFrame.getManagementName();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        int[] selectRows = {0};
        GetNodeIndex(rootNode, managementName, selectRows);
        
        jXTree1.setSelectionRow(selectRows[0] + currentFrame.getLevel() + ("Treatments".equals(managementName) ? 1 : 0));
        
        removeLevel();
    }//GEN-LAST:event_bnDeleteLevelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if(!onClose()){
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        }
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnAddLevel;
    private javax.swing.JButton bnDeleteLevel;
    private javax.swing.JButton bnNext;
    private javax.swing.JButton bnPrevious;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuCloseFile;
    private javax.swing.JMenuItem jMenuExit;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemSimAdd;
    private javax.swing.JMenuItem jMenuNewFile;
    private javax.swing.JMenuItem jMenuOpenFile;
    private javax.swing.JMenu jMenuRefresh;
    private javax.swing.JMenuItem jMenuSaveFile;
    private javax.swing.JPopupMenu jPopupMenuAdd;
    private javax.swing.JPopupMenu jPopupMenuItem;
    private javax.swing.JMenuItem jPopupMenuSimItemCopy;
    private javax.swing.JMenuItem jPopupMenuSimItemMoveDown;
    private javax.swing.JMenuItem jPopupMenuSimItemMoveUp;
    private javax.swing.JMenuItem jPopupMenuSimItemRemove;
    private javax.swing.JMenuItem jPopupMenuSimItemRename;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenu jSetupMenu;
    private org.jdesktop.swingx.JXTree jXTree1;
    // End of variables declaration//GEN-END:variables
    
    private void ShowFrame(IXInternalFrame frame) {

        if (frame != null) {
            setRootPaneCheckingEnabled(false);
            javax.swing.plaf.InternalFrameUI ui = frame.getUI();
            ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);

            try{
                desktopPane.add(frame);            
                frame.setMaximum(true);
                
                EventQueue.invokeLater(() -> {
                    frame.initialData();
                });
                
            } catch (PropertyVetoException ex) {
                Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.show();
        }
    }

    private DefaultMutableTreeNode GetNode(String parentNode, String childNode) {

        DefaultMutableTreeNode returnNode = null;
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(i);
            if (child.toString().equals(parentNode)) {
                for (int n = 0; n < child.getChildCount(); n++) {
                    DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) child.getChildAt(n);
                    if (leaf.toString().equals(childNode)) {

                        leaf.removeAllChildren();
                        DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
                        model.reload(leaf);

                        returnNode = leaf;
                        break;
                    }
                }
            }
        }
        return returnNode;
    }
    
    private boolean GetNodeIndex(DefaultMutableTreeNode root, String childNode, int[] index) {

        //int index = 0;

        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode m = (DefaultMutableTreeNode) root.getChildAt(i);
            index[0]++;
            if (m.toString().equals(childNode)) {
                return true;
            }
            
            if(m.getChildCount() > 0){
                if(GetNodeIndex(m, childNode, index)){
                    return true;
                }
            }

//            for (int n = 0; n < m.getChildCount(); n++) {
//                DefaultMutableTreeNode leaf = (DefaultMutableTreeNode) m.getChildAt(n);
//                index++;
//                if (leaf.toString().equals(childNode)) {
//                    return index;
//                }
//                
//                for (int l = 0; l < leaf.getChildCount(); l++) {
//                    DefaultMutableTreeNode level = (DefaultMutableTreeNode) leaf.getChildAt(l);
//                    index++;
//                    if (level.toString().equals(childNode)) {
//                        return index;
//                    }
//                }
//            }
        }
        return false;
    }

    private ManagementList GetManagementList(String nodeName) {
        if (null != nodeName) {
            switch (nodeName) {
                case "Fields":
                    return FileX.fieldList;
                case "Initial Conditions":
                    return FileX.initialList;
                case "Soil Analysis":
                    return FileX.soilAnalysis;
                case "Environmental Modifications":
                    return FileX.environmentals;
                case "Cultivars":
                    return FileX.cultivars;
                case "Planting":
                    return FileX.plantings;
                case "Irrigation":
                    return FileX.irrigations;
                case "Fertilizer":
                    return FileX.fertilizerList;
                case "Organic Amendments":
                    return FileX.organicList;
                case "Tillage":
                    return FileX.tillageList;
                case "Harvest":
                    return FileX.harvestList;
                case "Chemical Applications":
                    return FileX.chemicalList;
                case "Simulation Controls":
                    return FileX.simulationList;
                case "Treatments":
                    return FileX.treatments;
                default:
                    break;
            }
        }

        return null;
    }

    private void ResetTree() {
        jXTree1.removeAll();

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("FileX");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("General Information");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Notes");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Environment");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fields");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Initial Conditions");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Soil Analysis");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Environmental Modifications");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Management");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Cultivars");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Planting");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Irrigation");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Fertilizer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Organic Amendments");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Tillage");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Harvest");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Chemical Applications");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Simulation Controls");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Treatments");
        treeNode1.add(treeNode2);
        jXTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));

        jXTree1.expandAll();
    }

    private void AddTreeMenu(DefaultMutableTreeNode root, String node) {
        DefaultMutableTreeNode parent = null;
        for (int i = 0; i < root.getChildCount(); i++) {
            DefaultMutableTreeNode tmp = (DefaultMutableTreeNode) root.getChildAt(i);
            if (tmp.toString().equals(node)) {
                parent = tmp;
                break;
            }
        }

        if (parent != null) {
            parent.removeAllChildren();
            DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
            model.reload(parent);

            AddToParent(parent, node);
        }
    }

    private void AddTreeMenu(String parent, String child) {

        DefaultMutableTreeNode parentNode = GetNode(parent, child);
        AddToParent(parentNode, child);
    }

    private void AddToParent(DefaultMutableTreeNode parentNode, String child) {
        ManagementList list = GetManagementList(child);
        for (ModelXBase item : list.GetAll()) {
            try {
                DefaultMutableTreeNode leaf = new DefaultMutableTreeNode();
                leaf.setUserObject("Level " + item.GetLevel() + ": " + item.GetName());
                parentNode.add(leaf);
            } catch (Exception ex) {
                String me = ex.getMessage();
            }
        }
    }

    /**
     *
     * @param e
     */
    @Override
    public void myAction(XEvent e) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();

        EventQueue.invokeLater(() -> {
            root.setUserObject(e.getN());

            jXTree1.repaint();

            TreeSelectionListener[] ls = jXTree1.getListeners(TreeSelectionListener.class);
            MouseAdapter[] ms = jXTree1.getListeners(MouseAdapter.class);

            for (TreeSelectionListener l : ls) {
                jXTree1.removeTreeSelectionListener(l);
            }

            for (MouseAdapter m : ms) {
                jXTree1.removeMouseListener(m);
            }

            TreePath path = jXTree1.getSelectionPath();
            jXTree1.collapseAll();
            jXTree1.expandAll();

            jXTree1.setSelectionPath(path);

            for (TreeSelectionListener l : ls) {
                jXTree1.addTreeSelectionListener(l);
            }

            for (MouseAdapter m : ms) {
                jXTree1.addMouseListener(m);
            }
            
            setPrevNextButton();
        });
    }

    @Override
    public void myAction(AddLevelEvent e) {
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        DefaultMutableTreeNode targetNode = null;
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);

            if (child.toString().equals(e.getParent())) {
                targetNode = child;
                break;
            }

            for (int n = 0; n < child.getChildCount(); n++) {
                DefaultMutableTreeNode child1 = (DefaultMutableTreeNode) child.getChildAt(n);
                if (child1.toString().equals(e.getParent())) {
                    targetNode = child1;
                    break;
                }
            }
        }

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();
        String newName = e.getName();
        newNode.setUserObject(newName);
        targetNode.add(newNode);

        jXTree1.expandAll();

        DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
        model.reload(targetNode);
    }

    @Override
    public void myAction(RemoveLevelEvent e) {
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        DefaultMutableTreeNode targetNode = null;
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);

            if (child.toString().equals(e.getParent())) {
                targetNode = child;
                break;
            }

            for (int n = 0; n < child.getChildCount(); n++) {
                DefaultMutableTreeNode child1 = (DefaultMutableTreeNode) child.getChildAt(n);
                if (child1.toString().equals(e.getParent())) {
                    targetNode = child1;
                    break;
                }
            }
        }

        DefaultMutableTreeNode removeNode = null;
        for (int i = 0; i < targetNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) targetNode.getChildAt(i);
            if (child.toString().equals(e.getName())) {
                removeNode = child;
                break;
            }
        }

        DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();

        targetNode.remove(removeNode);

        for (int i = 0; i < targetNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) targetNode.getChildAt(i);
            String[] oldName = child.getUserObject().toString().split(":");

            child.setUserObject("Level " + (i + 1) + ": " + oldName[1].trim());
        }

        model.reload(targetNode);
    }

    @Override
    public void myAction(UpdateLevelEvent e) {
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        DefaultMutableTreeNode targetNode = null;
        for (int i = 0; i < rootNode.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) rootNode.getChildAt(i);

            if (child.toString().equals(e.getParent())) {
                targetNode = child;
                break;
            }

            for (int n = 0; n < child.getChildCount(); n++) {
                DefaultMutableTreeNode child1 = (DefaultMutableTreeNode) child.getChildAt(n);
                if (child1.toString().equals(e.getParent())) {
                    targetNode = child1;
                    break;
                }
            }
        }

        DefaultMutableTreeNode childUpdate = (DefaultMutableTreeNode) targetNode.getChildAt(e.getRow());
        childUpdate.setUserObject(e.getName());

        DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
        model.reload(targetNode);
    }

    @Override
    public void myAction(ValidationEvent e) {
        jXTree1.repaint();
    }

    @Override
    public void myAction(NewFrameEvent e) {
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) jXTree1.getModel().getRoot();
        ArrayList<String> nodeList = new ArrayList<>();
        getCellIndex(rootNode, nodeList);

        String frameName;
        int select;

        int mIndex = menuAll.indexOf(e.getCurrentFrameName());
        if (e.getDirection() == MenuDirection.PREVIOUS) {
            mIndex--;
        } else if (e.getDirection() == MenuDirection.NEXT) {
            mIndex++;
        }

        frameName = menuAll.get(mIndex);
        select = nodeList.indexOf(frameName);

        showTargetFrame(frameName, select, nodeList, e.getDirection());
    }
    
    @Override
    public void myAction(SelectionEvent e) {
        bnDeleteLevel.setEnabled(e.canDelete());
    }
    
    @Override
    public void myAction(FieldUpdateEvent e) {
        if(FileX.isReady && !FileX.isDirty){
            FileX.isDirty = true;
            
            EventQueue.invokeLater(() -> {
                jXTree1.repaint();
            });
        }
    }
    
    private void showTargetFrame(String frameName, int select, ArrayList<String> nodeList, MenuDirection direction) {
        currentFrameName = frameName;
        if (!frameName.equals("General Information")) {
            ManagementList modelList = (ManagementList) GetManagementList(frameName);
            if (modelList.GetSize() > 0) {
                jXTree1.expandRow(select);
                
                jXTree1.setSelectionRow(select + 1);
                jXTree1.scrollRowToVisible(select + 1);
                
                EventQueue.invokeLater(() -> {
                    showFrame();
                });                
            } else if (menuRequired.indexOf(frameName) > 0) {
                jXTree1.expandRow(select);
                jXTree1.setSelectionRow(select);
                jXTree1.scrollRowToVisible(select);
                addLevel();
            } else {
                int mIndex = menuAll.indexOf(frameName);

                if (direction == MenuDirection.PREVIOUS) {
                    mIndex--;
                } else if (direction == MenuDirection.NEXT) {
                    mIndex++;
                }

                frameName = menuAll.get(mIndex);
                select = nodeList.indexOf(frameName);
                showTargetFrame(frameName, select, nodeList, direction);
            }
        } else {
            jXTree1.expandRow(select);
            jXTree1.setSelectionRow(select);
            jXTree1.scrollRowToVisible(select);
            
            EventQueue.invokeLater(() -> {
                showFrame();
            });
        }
    }

    private void getCellIndex(TreeNode node, ArrayList<String> selects) {
        selects.add(node.toString());

        if (node.getChildCount() > 0) {
            for (int i = 0; i < node.getChildCount(); i++) {
                getCellIndex(node.getChildAt(i), selects);
            }
        }
    }
    
    private void addLevel(){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();
        ManagementList modelList = (ManagementList) GetManagementList(node.toString());

        addNewLevel(node, modelList);
    }
    
    private void addNewLevel(DefaultMutableTreeNode node, ManagementList modelList){
        if (modelList != null && !"Cultivars".equals(node.toString())) {
            String defaultName = !"Simulation Controls".equals(node.toString()) ? "UNKNOWN_" + (modelList.GetSize() + 1) : SimulationControlDefaults.Get(FileX.general.FileType).SNAME;
            
             IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
            int currentLevel = "Treatments".equals(node.toString()) && modelList.GetSize() > 0 ? currentFrame.getLevel() : -1;
            
            if(currentLevel >= 0){
                defaultName = FileX.treatments.GetAt(currentLevel + 1).GetName();
            }
                            
            InputDialog input = "Treatments".equals(node.toString()) ? new InputDialog(this, true, defaultName, 25) : new InputDialog(this, true, defaultName);
            input.show();
            
            input.addWindowListener(new java.awt.event.WindowAdapter() {                
                @Override
                public void windowClosed(WindowEvent e) {
                    if(input.isOK()){
                        String nodeName = input.getDescription();

                        if (nodeName.length() > 0) {
                            int level = 0;
                            for (ModelXBase m : modelList.GetAll()) {
                                if (m.GetName().equalsIgnoreCase(nodeName)) {
                                    JOptionPane.showMessageDialog(new JXFrame(), "This name is already add", "ERROR", 0);
                                    return;
                                }
                                level = m.GetLevel();
                            }
                            level++;
                            
                            ModelXBase newModel = modelList.AddNew(nodeName, level, currentLevel);

                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode();

                            String newName = "Level " + level + ": " + nodeName;

                            newNode.setUserObject(newName);
                            node.add(newNode);

                            DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
                            model.reload(node);

                            jXTree1.expandAll();
                            int[] rows = jXTree1.getSelectionRows();
                            //int selectRow = GetNodeIndex((DefaultMutableTreeNode)jXTree1.getModel().getRoot(), node.getUserObject().toString());

                            jXTree1.setSelectionRow(rows[0] + modelList.GetIndex(newModel));

                            IXInternalFrame frame = XInternalFrame.newInstance(mainMenuList.get(nodeName), node.toString());
                            ShowFrame(frame);
                        }                        
                    }
                }
            });
        } else if (modelList != null && "Cultivars".equals(node.toString())) {
            CultivarsFrame currentFrame = (CultivarsFrame) desktopPane.getSelectedFrame();
            currentFrame.AddNewCultivar();
        }
    }
    
    private void removeLevel(){
        if (JOptionPane.showConfirmDialog(new JXFrame(), "Do you want to delete this level") == 0) {
            
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();

            DefaultTreeModel model = (DefaultTreeModel) jXTree1.getModel();
            DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) node.getParent();

            ManagementList modelList = GetManagementList(parentNode.toString());
            int[] selectRows = {0};
            GetNodeIndex(parentNode, node.toString(), selectRows);
            int level = selectRows[0] - 1;
            
            if(!modelList.IsUseInTreatment(level + 1)){
                modelList.RemoveAt(level);
                model.removeNodeFromParent(node);

                desktopPane.removeAll();
                desktopPane.repaint();

                EventQueue.invokeLater(() -> {
                    for (int i = level; i < modelList.GetSize(); i++) {
                        ModelXBase m = modelList.GetAtIndex(i);

                        if (m.getClass() == Treatment.class && FileX.general.FileType == ExperimentType.Sequential) {

                        } else {
                            m.SetLevel(m.GetLevel() - 1);
                            DefaultMutableTreeNode child = (DefaultMutableTreeNode) parentNode.getChildAt(i);
                            String newName = "Level " + m.GetLevel() + ": " + m.GetName();
                            child.setUserObject(newName);
                        }
                    }
                    model.reload(parentNode);
                    
                    jXTree1.setSelectionPath(new TreePath(parentNode.getPath()));
                    if(modelList.GetSize() > 0){

                        int select = jXTree1.getSelectionRows()[0];
                        jXTree1.setSelectionRow(select + (level < modelList.GetSize() ? level + 1 : level));
                    }
                });
            }
            else {
                JOptionPane.showMessageDialog(this, "<html>Cannot remove this level<br>This level is use in treatments", "Invalid!", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void showFrame(){
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jXTree1.getLastSelectedPathComponent();

        boolean isValid = true;
        if (node != null && !menuNoFrame.contains(node.toString()) && node.getParent() != null) {
            if ("General Information".equalsIgnoreCase(node.toString())) {
                isValid = true;
            } else if (!FileXValidationService.IsGeneralValid()) {
                isValid = false;
            } 
            else if ("Cultivars".equalsIgnoreCase(node.toString()) && !FileXValidationService.IsCropEnabled()){
                isValid = false;
            }
            else if ("Treatments".equalsIgnoreCase(node.toString()) && !FileXValidationService.IsMinimumRequired()) {
                isValid = false;
            }

            if (isValid) {
                String nodeName = node.toString();
                if (mainMenuList.get(nodeName) == null) {
                    nodeName = node.getParent().toString();
                }

                currentFrameName = nodeName;
                IXInternalFrame frame;
                int level = node.getParent().getIndex(node);
                
                if("Treatments".equalsIgnoreCase(node.toString())){
                    frame = (IXInternalFrame) desktopPane.getSelectedFrame();
                    level = frame.getLevel();
                }
                else{
                    frame = XInternalFrame.newInstance(mainMenuList.get(nodeName), node.toString());
                    
                    if (frame != null) {
                    ShowFrame(frame);
                    frame.addMyEventListener(this);

                    frame.setSelection(level + 1);
                    
                    EventQueue.invokeLater(() -> {
                        bnDeleteLevel.setEnabled(frame.isDeleteButtonEnabled());
                    });                    
                }
                }
            }
        }
        
        setPrevNextButton();
        setAddDeleteButton();
    }
    
    private void setPrevNextButton(){
        IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
        
        if(currentFrame != null){
            bnPrevious.setEnabled(currentFrame.isPrevButtonEnabled());
            bnNext.setEnabled(currentFrame.isNextButtonEnabled());
        }
        else {
            bnPrevious.setEnabled(false);
            bnNext.setEnabled(false);
        }
    }
    
    private void setAddDeleteButton(){
        IXInternalFrame currentFrame = (IXInternalFrame) desktopPane.getSelectedFrame();
        
        if(currentFrame != null){
            bnAddLevel.setEnabled(currentFrame.isAddButtonEnabled());
            bnDeleteLevel.setEnabled(currentFrame.isDeleteButtonEnabled());
        }
        else {
            bnAddLevel.setEnabled(false);
            bnDeleteLevel.setEnabled(false);
        }
    }
}

class ExtensionFileFilter extends FileFilter {

    String description;

    String extensions[];

    public ExtensionFileFilter(String description, String extension) {
        this(description, new String[]{extension});
    }

    public ExtensionFileFilter(String description, String extensions[]) {
        if (description == null) {
            this.description = extensions[0];
        } else {
            this.description = description;
        }
        this.extensions = (String[]) extensions.clone();
        toLower(this.extensions);
    }

    private void toLower(String array[]) {
        for (int i = 0, n = array.length; i < n; i++) {
            array[i] = array[i].toLowerCase();
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        } else {
            String fileName = file.getName().toLowerCase();
            if (fileName.endsWith("x")) {
                return true;
            }
        }
        return false;
    }
}
