/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package xbuild;

import DSSATModel.Setup;
import java.awt.event.*;
import java.io.IOException;
import java.net.ServerSocket;
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
    private static int instanceStatus = 0;
    private static ServerSocket servers;

    public static void main(String[] args) {

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                closeConnection();
            }
        });

        while (instanceStatus == 0) {
            assertNoOtherInstanceRunning();
        }

        if (instanceStatus == 2) {
            JOptionPane.showMessageDialog(new JXFrame(), "XB2 is already opened.", "ERROR", 0);
            System.exit(0);
            return;
        }

        MainForm mainForm = new MainForm();
        mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainForm.show();

        mainForm.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt){
                closeConnection();
            }
            
            @Override
            public void windowClosed(WindowEvent evt) {
                closeConnection();
            }
        });

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

    public static void assertNoOtherInstanceRunning() {
        new Thread(() -> {
            try {
                servers = new ServerSocket(9000);
                instanceStatus = 1;
                servers.accept();
            } catch (IOException e) {
                instanceStatus = 2;
            }
        }).start();
    }

    public static void closeConnection() {
        if (servers != null) {
            try {
                servers.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
