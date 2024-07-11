package xbuild.Components;

import DSSATModel.Setup;
import FileXModel.ManagementList;
import FileXModel.ModelXBase;
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

    protected XEventListener listener;
    protected Setup setup = new Setup();
    protected ModelXBase model;
    
    protected Integer level;
    
    public abstract ManagementList getManagementList();
   
    
    public IXInternalFrame(ManagementList managementList, String name){
        level = 0;
        
        if(managementList != null && !"".equals(name) ){
            for (ModelXBase xModel : managementList.GetAll()) {
                level++;
                if (getLevel(name) == level) {
                    model = xModel;
                    break;
                }
            }
        }
        if(!"".equals(name)){
            setTitle(name);
        }
    }
    
    public String getManagementName(){
        return "";
    }
    
    public int getLevel(){
        return level;
    }
    
    public void setSelection(int level){
        this.level = level;
    }

    public void updatePanelName(String name) {

    }

    public void updatePanelList() {

    }

    public void addMyEventListener(XEventListener l) {
        if (this.listener == null) {
            this.listener = l;
        }
    }
    
    public boolean isPrevButtonEnabled(){
        return false;
    }
    
    public boolean isNextButtonEnabled(){
        return false;
    }
    
    public boolean isAddButtonEnabled(){
        return false;
    }
    
    public boolean isDeleteButtonEnabled(){
        return false;
    }
    
    public void initialData(){
        
    }
    
    public String getDescription(){
        return model.GetName();
    }
    
    public ModelXBase getModel(){
        return model;
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

    protected void setImage(JLabel imagePanel, String imageFile) {
        EventQueue.invokeLater(() -> {
            File playerimage = new File(setup.GetDSSATPath() + "\\Tools\\XBuild\\" + imageFile);

            if (playerimage.exists()) {
                BufferedImage img;
                try {
                    img = ImageIO.read(playerimage);
                    
                    int imgWidth = img.getWidth();
                    int imgHeight = img.getHeight();
                    int panelWidth = imagePanel.getWidth();
                    int panelHeight = imagePanel.getHeight();
                    
                    if(imgWidth != imgHeight){
                        if(imgWidth > imgHeight){
                            panelHeight = (int)((float)imgHeight * ((float)panelWidth / (float)imgWidth));
                        }
                        else{
                            panelWidth = (int)((float)imgWidth * ((float)panelHeight / (float)imgHeight));
                        }
                    }                    
                    
                    Image scaledImage = img.getScaledInstance(panelWidth, panelHeight, Image.SCALE_SMOOTH);
                    ImageIcon imageIcon = new ImageIcon(scaledImage);
                    imagePanel.setIcon(imageIcon);
                } catch (IOException ex) {
                    Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}
