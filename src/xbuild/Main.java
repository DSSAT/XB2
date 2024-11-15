/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild;

import DSSATModel.Setup;
import java.awt.event.*;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXFrame;
import xbuild.Components.UpdateComponent;

/**
 *
 * @author Jazzy
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        if(lockInstance()){
            JOptionPane.showMessageDialog(new JXFrame(), "XB2 is already opened.", "ERROR", 0);
            System.exit(0);
            return;
        }

        MainForm mainForm = new MainForm();
        mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainForm.show();

        UpdateComponent.setEventListener(mainForm);

        final Setup setup = new Setup();
        if (setup.GetDSSATPath() == null) {
            SetupFrame frame = new SetupFrame();
            frame.show();

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent evt) {
                    new LoadingDataFrame(setup.GetDSSATPath()).show();
                }
            });
        } else {
            new LoadingDataFrame(setup.GetDSSATPath()).show();
        }
    }
    
    private static boolean lockInstance() {
        final String lockFile = "XB2.lock" ;
        
        try {            
            final File file = new File(lockFile);
            final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            final FileLock fileLock = randomAccessFile.getChannel().tryLock();
            if (fileLock != null) {
                Runtime.getRuntime().addShutdownHook(new Thread() {
                    public void run() {
                        try {
                            fileLock.release();
                            randomAccessFile.close();
                            file.delete();
                        } catch (Exception e) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Unable to remove lock file: " + lockFile, e);
                        }
                    }
                });
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Unable to create and/or lock file: " + lockFile, e);
        }
        return true;
    }
}
