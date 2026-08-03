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
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.*;
import xbuild.Events.LoadingDoneEvent;
import xbuild.Events.LoadingEventListener;
import xbuild.Events.XEventListener;



/**
 *
 * @author Jazzy
 */
public class LoadingDataFrame extends javax.swing.JFrame {

    private static final int IO_POOL_SIZE = 4;
    private static final ExecutorService IO_EXECUTOR = Executors.newFixedThreadPool(IO_POOL_SIZE);

    /** Creates new form LoadingData */
    private Phase1Task phase1Task;
    protected String dir;
    private boolean isValid = true;
    private boolean isDone = false;
    private String validationMessage = "";
    private final Object messageLock = new Object();
    private final AtomicBoolean hasError = new AtomicBoolean(false);

    protected XEventListener listener;
    private LoadingEventListener laodingEvent;

    abstract class LoadingTask extends SwingWorker<Void, Void> {

        protected boolean parseDssatProfile() {
            try {
                appendMessage("Loading DSSAT profile....");
                DSSATProfileService dssatProfileService = new DSSATProfileService(dir);
                dssatProfileService.Parse();
                appendMessage("<font color='green'>!Done</font><br>");
                return true;
            } catch (Exception ex) {
                appendMessage("<font color='red'>!Error</font><br>");
                isValid = false;
                return false;
            }
        }

        protected void parseService(DSSATServiceBase service) {
            try {
                appendMessage("Loading " + service.getName() + "....<br>");
                service.Parse();
                appendMessage("<font color='green'>" + service.getName() + " Done!</font><br>");
            } catch (Exception ex) {
                hasError.set(true);
                isValid = false;
                appendMessage("<font color='red'>" + service.getName() + " Error!</font><br>");
                if (ex.getMessage() != null) {
                    for (String message : ex.getMessage().split("\n")) {
                        appendMessage("<div style='padding-left:25px'><font color='red'>" + message + "</font></div>");
                    }
                }
            }
        }

        protected void parseServicesInParallel(ArrayList<DSSATServiceBase> services) {
            CompletableFuture<?>[] futures = services.stream()
                    .map(service -> CompletableFuture.runAsync(() -> parseService(service), IO_EXECUTOR))
                    .toArray(CompletableFuture[]::new);

            try {
                CompletableFuture.allOf(futures).join();
                if (hasError.get()) {
                    isValid = false;
                }
            } catch (Exception ex) {
                isValid = false;
            }
        }

        protected void appendMessage(String message) {
            synchronized (messageLock) {
                validationMessage += message;
                SwingUtilities.invokeLater(() -> jLabel1.setText("<html>" + validationMessage + "</html>"));
            }
        }

        protected void fireLoaded(LoadingDoneEvent.Phase phase) {
            if (laodingEvent != null) {
                laodingEvent.onLoaded(new LoadingDoneEvent(this, isValid, phase));
            }
        }
    }

    class Phase1Task extends LoadingTask {

        @Override
        public Void doInBackground() {
            setProgress(0);
            Variables.setLocale(getLocale());
            isValid = true;
            hasError.set(false);

            jScrollPane2.getVerticalScrollBar().addAdjustmentListener((AdjustmentEvent e) -> {
                ttt(e);
            });

            if (!parseDssatProfile()) {
                return null;
            }

            parseService(new CropService(dir));
            parseService(new SimulationService(dir));

            try {
                appendMessage("Loading Simulation Default....");
                SimulationDefaultService simulationDefaultService = new SimulationDefaultService(dir);
                simulationDefaultService.Parse();
                appendMessage("<font color='green'>!Done</font><br>");
            } catch (Exception ex) {
                appendMessage("<font color='red'>!Error</font><br>");
                isValid = false;
            }

            Icons.Init(getClass());

            return null;
        }

        @Override
        protected void done() {
            fireLoaded(LoadingDoneEvent.Phase.ESSENTIAL);

            phase1Task = null;
            Phase2Task phase2Task = new Phase2Task();
            phase2Task.execute();
        }
    }

    class Phase2Task extends LoadingTask {

        @Override
        public Void doInBackground() {
            ArrayList<DSSATServiceBase> parseList = new ArrayList<>();

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
            parseList.add(new SoilService(dir));
            parseList.add(new WeatherService(dir));

            parseServicesInParallel(parseList);

            try {
                GrowthStageService gService = new GrowthStageService(dir);
                appendMessage("Loading " + gService.getName() + " Default....");
                gService.Parse();
                appendMessage("<font color='green'>!Done</font><br>");
            } catch (Exception ex) {
                appendMessage("<font color='red'>!Error</font><br>");
                isValid = false;
            }

            if (isValid) {
                appendMessage("<font color='green'>!Done</font><br>");
            } else {
                appendMessage("<font color='red'>!Some of configurations are failed</font><br>");
            }

            isDone = true;

            return null;
        }

        @Override
        protected void done() {
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            fireLoaded(LoadingDoneEvent.Phase.COMPLETE);

            if (!isValid) {
                setVisible(true);
            }
        }
    }

    private void ttt(AdjustmentEvent e) {
        if (!isDone) {
            e.getAdjustable().setValue(e.getAdjustable().getMaximum());
        }
    }

    public LoadingDataFrame(String dir) {
        this.dir = dir;

        initComponents();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        Dimension winSize = getSize();
        setLocation((screenWidth - winSize.width) / 2, (screenHeight - winSize.height) / 2);
    }

    public void addListener(LoadingEventListener lEvent) {
        this.laodingEvent = lEvent;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        l = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(Variables.getIconImage(getClass()));

        jLabel1.setText("jLabel1");
        jScrollPane2.setViewportView(jLabel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(l)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(l))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void startTask() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        isDone = false;
        validationMessage = "";
        isValid = true;
        hasError.set(false);
        phase1Task = new Phase1Task();
        phase1Task.execute();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel l;
    // End of variables declaration//GEN-END:variables

}
