package com.vgates.customerportal.util;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author chamith
 */
public class SearchComboBox {
    private JTextField txt;
    private ArrayList<String> list;

    public void search(final JComboBox cmb, boolean sort, final String emptyPrint) {
        //--------------remove Dublicate------------------//
        list = new ArrayList<String>();
        int itemCount = cmb.getItemCount();
        boolean exsist;
        for (int i = 0; i < itemCount; i++) {
            exsist = false;
            for (int j = 0; j < list.size(); j++) {
                if (cmb.getItemAt(i).equals(list.get(j))) {
                    exsist = true;
                    break;
                }
            }
            if (!exsist) {
                list.add(String.valueOf(cmb.getItemAt(i)));
            }

            if (sort) {
                Collections.sort(list);
            }

        }

        //-------------search combo---------------------------//

        cmb.setEditable(true);
        txt = (JTextField) cmb.getEditor().getEditorComponent();

        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                int key = evt.getKeyCode();
                if (!(key == KeyEvent.VK_ESCAPE || key == KeyEvent.VK_ENTER || key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP)) {
                    String s = txt.getText();
                    int caret = txt.getCaretPosition();
                    cmb.hidePopup();
                    cmb.removeAllItems();
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).toUpperCase().startsWith(s.substring(0, caret).toUpperCase())) {
                            cmb.addItem(list.get(i));
                        }
                    }
                    cmb.showPopup();
                    if (cmb.getItemCount() == 0) {
                        cmb.addItem(emptyPrint);
                    }
                    txt.setText(s);
                    txt.setCaretPosition(caret);
                } else if (key == KeyEvent.VK_ESCAPE) {
                    cmb.setSelectedItem(txt.getText());
                    cmb.hidePopup();
                } else if (key == KeyEvent.VK_ENTER && cmb.getSelectedIndex() == 0) {
                    if (cmb.getItemCount() == 1 && !cmb.getItemAt(0).equals(emptyPrint)) {
                        cmb.setSelectedIndex(0);
                    } else if (cmb.getItemCount() > 1) {
                        cmb.setSelectedIndex(0);
                    }
                }

            }
        });

    }
}
