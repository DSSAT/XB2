/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Extensions;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 *
 * @author Jazzy
 */
public class CustomNode extends DefaultMutableTreeNode {
    protected boolean enabled;

  public CustomNode() {
    this(null, true, true);
  }

  public CustomNode(Object userObject) {
    this(userObject, true, true);
  }

  public CustomNode(Object userObject, boolean allowsChildren) {
    this(userObject, allowsChildren, true);
  }

  public CustomNode(Object userObject, boolean allowsChildren,
      boolean enabled) {
    super(userObject, allowsChildren);
    this.enabled = enabled;
  }

  public int getChildCount() {
    if (enabled) {
      return super.getChildCount();
    } else {
      return 0;
    }
  }

  public boolean isLeaf() {
    return (super.getChildCount() == 0);
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public boolean isEnabled() {
    return enabled;
  }
}
