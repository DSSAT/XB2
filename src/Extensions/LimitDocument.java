/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Extensions;

import javax.swing.text.*;

/**
 *
 * @author Jazzy
 */
public class LimitDocument extends PlainDocument  {

    private int limit;
    private boolean digitsOnly;

    public LimitDocument(int limit)
    {
        this(limit, false);
    }

    public LimitDocument(int limit, boolean digitsOnly)
    {
        super();
        setLimit(limit);  // store the limit
        this.digitsOnly = digitsOnly;
    }
    public final int getLimit()
    {
    return limit;
    }
    @Override
    public void insertString(int offset, String s, AttributeSet attributeSet)
        throws BadLocationException
    {
        if (s == null) {
            return;
        }

        if (digitsOnly) {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) {
                    return; // reject the whole insertion if any char is not a digit
                }
            }
        }

        if (getLength() + s.length() <= limit) // only insert while within the limit
    {
        super.insertString(offset,s,attributeSet);
    } // otherwise, just lose the string
    }
    public final void setLimit(int newValue)
    {
        this.limit = newValue;
    }

}
