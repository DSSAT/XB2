/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xbuild;

import DSSATModel.Setup;
import java.awt.event.*;
import javax.swing.JFrame;
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

        MainForm mainForm = new MainForm();
        mainForm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainForm.show();
        
        UpdateComponent.setEventListener(mainForm);
        
        final Setup setup = new Setup();
        if(setup.GetDSSATPath() == null)
        {
            SetupFrame frame = new SetupFrame();
            frame.show();
            
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent evt) {
                    new LoadingDataFrame(setup.GetDSSATPath(), setup.GetDSSATVersion()).show();
                } 
            });
        }
        else
            new LoadingDataFrame(setup.GetDSSATPath(), setup.GetDSSATVersion()).show();
    }

}
