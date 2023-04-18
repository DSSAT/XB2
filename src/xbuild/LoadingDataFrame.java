/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoadingData.java
 *
 * Created on 19 ก.พ. 2553, 11:44:27
 */

package xbuild;

import DSSATServices.*;
import Extensions.Icons;
import Extensions.Variables;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.*;



/**
 *
 * @author Jazzy
 */
public class LoadingDataFrame extends javax.swing.JFrame implements PropertyChangeListener {

    /** Creates new form LoadingData */
    private Task task;
    protected String dir;
    protected String version;
    private boolean isValid = true;   

    class Task extends SwingWorker<Void, Void> {
        /*
         * Main task. Executed in background thread.
         */
        @Override
        public Void doInBackground() {
            //int progress = 0;
            //Initialize progress property.
            setProgress(0);
            Variables.setLocale(getLocale());
            
            ArrayList<DSSATServiceBase> parseList = new ArrayList<>();
            
            parseList.add(new CropService(dir));
            parseList.add(new ChemicalService(dir));
            parseList.add(new DrainageService(dir));
            parseList.add(new SoilTextureService(dir));
            parseList.add(new SoilAnalysisService(dir));
            parseList.add(new PlantingMethodService(dir));
            parseList.add(new PlantDistributionService(dir));
            parseList.add(new IrrigationMethodService(dir));
            parseList.add(new FertilizerService(dir));
            parseList.add(new FertilizerMethodService(dir));
            parseList.add(new EnvironmentService(dir));
            parseList.add(new TillageService(dir));
            parseList.add(new ResiduesService(dir));
            parseList.add(new HarvestComponentService(dir));
            parseList.add(new HarvestSizeService(dir));
            parseList.add(new FieldHistoryService(dir));
            parseList.add(new SimulationService(dir));
            parseList.add(new GrowthStageService(dir));
            parseList.add(new SoilService(dir));
            parseList.add(new WeatherService(dir));

            try{
                taskOutput.append("Loading DSSAT profile....\n");
                DSSATProfileService dssatProfileService = new DSSATProfileService(version, dir);
                dssatProfileService.Parse();
            }
            catch(Exception ex){
                taskOutput.append("!DSSATPro loading error: " + ex.getMessage() + " \n");
                isValid = false;
            }
            
            parseList.forEach(service -> {
                try {
                    taskOutput.append("Loading " + service.getName() + "....\n");
                    
                    service.Parse();
                } catch (Exception ex) {
                    taskOutput.append(ex.getMessage() + "\n");
                    isValid = false;
                }
            });
            
            try{
                taskOutput.append("Loading Simulation Default....\n");
                SimulationDefaultService simulationDefaultService = new SimulationDefaultService(dir);
                simulationDefaultService.Parse();
            }
            catch(Exception ex){
                taskOutput.append("!Simulation Default loading error: " + ex.getMessage() + " \n");
                isValid = false;
            }
            
            Icons.Init(getClass());

            return null;
        }

        /*
         * Executed in event dispatching thread
         */
        @Override
        public void done() {
            //setCursor(null); //turn off the wait cursor
            if(isValid){
                taskOutput.append("Done!\n");
                dispose();
            }
            else
                taskOutput.append("ERROR!\n");                
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }

    public LoadingDataFrame(String dir, String version) {
        this.dir = dir;
        this.version = version;

        initComponents();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2 , (screenHeight - winSize.height) / 2);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent evt){
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                tt();
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pb = new javax.swing.JProgressBar();
        l = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taskOutput = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taskOutput.setColumns(20);
        taskOutput.setRows(5);
        jScrollPane1.setViewportView(taskOutput);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addComponent(l, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pb, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tt() {
        // TODO add your handling code here:
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //Instances of javax.swing.SwingWorker are not reusuable, so
        //we create new instances as needed.
        task = new Task();
        task.addPropertyChangeListener(this);
        task.execute();
    }

    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            int progress = (Integer) evt.getNewValue();
            pb.setValue(progress);
            taskOutput.append(String.format(
                    " Completed %d%% of task.\n", task.getProgress()));
            //taskOutput.setSelectionStart(taskOutput.getRows()-1);
            //taskOutput.setSelectionEnd(taskOutput.getRows()-1);
            //taskOutput.setCaretPosition(taskOutput.getRows()-1);
        }
    }
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l;
    private javax.swing.JProgressBar pb;
    private javax.swing.JTextArea taskOutput;
    // End of variables declaration//GEN-END:variables

}
