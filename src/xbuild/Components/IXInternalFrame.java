package xbuild.Components;

import DSSATModel.Setup;
import FileXModel.FileX;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.image.BufferedImage;
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
        EventQueue.invokeLater(() -> {
            File playerimage = new File(setup.GetDSSATPath() + "\\Tools\\XBuild\\" + imageFile);

            if (playerimage.exists()) {
                BufferedImage img;
                try {
                    img = ImageIO.read(playerimage);
                    Image scaledImage = img.getScaledInstance(imagePanel.getWidth(), imagePanel.getHeight(), Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaledImage);
                    imagePanel.setIcon(imageIcon);
                } catch (IOException ex) {
                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
