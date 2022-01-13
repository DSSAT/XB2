/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package xbuild;

import DSSATModel.Setup;
import java.awt.event.*;

/**
 *
 * @author Jazzy
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        new MainForm().show();
        
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
