package org.jdesktop.swingx;

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

public class JXTable extends javax.swing.JTable {
    
    public boolean isEditing() {
        return false;
    }
    
    public boolean isTerminateEditOnFocusLost() {
        return false;
    }
    
    public javax.swing.table.TableCellEditor getCellEditor() {
        return null;
    }

    class CellEditorRemover implements PropertyChangeListener {
        KeyboardFocusManager focusManager;

        public CellEditorRemover() {
            install();
        }

        private void install() {
            focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            focusManager.addPropertyChangeListener("permanentFocusOwner", this);
            focusManager.addPropertyChangeListener("managingFocus", this);
        }

        public void uninstall() {
            if (focusManager != null) {
                focusManager.removePropertyChangeListener("permanentFocusOwner", this);
                focusManager.removePropertyChangeListener("managingFocus", this);
                focusManager = null;
            }
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (evt == null) {
                return;
            }
            if ("permanentFocusOwner".equals(evt.getPropertyName())) {
                permanentFocusOwnerChange();
            }
        }

        private void permanentFocusOwnerChange() {
            if (!isEditing() || !isTerminateEditOnFocusLost()) {
                return;
            }
            Component c = focusManager.getPermanentFocusOwner();
            while (c != null) {
                if (c instanceof JPopupMenu) {
                    c = ((JPopupMenu) c).getInvoker();
                } else {
                    if (c == JXTable.this) {
                        return;
                    }
                    if (c instanceof java.awt.Window) {
                        if (c == SwingUtilities.getRoot(JXTable.this)) {
                            if (getCellEditor() != null) {
                                if (!getCellEditor().stopCellEditing()) {
                                    getCellEditor().cancelCellEditing();
                                }
                            }
                        }
                        break;
                    }
                    c = c.getParent();
                }
            }
        }
    }
}
