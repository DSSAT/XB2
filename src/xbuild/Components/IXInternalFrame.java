package xbuild.Components;

import DSSATModel.Setup;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import xbuild.Events.XEventListener;

/**
 *
 * @author Jazz
 */
public abstract class IXInternalFrame extends JInternalFrame {

    protected XEventListener l;
    protected Setup setup = new Setup();

    public void updatePanelName(String name) {

    }

    public void updatePanelList() {

    }

    protected int getLevel(String nodeName) {
        String[] level1 = nodeName.split(":");
        String[] level2 = level1[0].split(" ");

        return Integer.parseInt(level2[1]);
    }

    protected String getDescription(String nodeName) {
        String[] level1 = nodeName.split(":");

        return level1[1].trim();
    }

    public void addMyEventListener(XEventListener l) {
        if (this.l == null) {
            this.l = l;
        }
    }

    protected void setImage(JLabel imagePanel, String imageFile) {
        File playerimage = new File(imageFile);
        if (playerimage.exists()) {
            try {
                ImageIcon pimage = new ImageIcon(ImageIO.read(playerimage));
                imagePanel.setIcon(pimage);
            } catch (IOException ex) {
                Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
