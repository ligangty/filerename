package com.github.ligangty.refile.ui;

import javax.swing.*;
import java.awt.*;

public class GriddedPanel extends JPanel {

    private final GridBagConstraints constraints;

    // Create a GridBagLayout panel using a default insets constraint
    public GriddedPanel() {
        this(new Insets(2, 2, 2, 2));
    }

    // Create a GridBagLayout panel using the specified insets
    // constraint
    public GriddedPanel(Insets insets) {
        super(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = insets;
    }

    // Add a component to the specified constraints constaints,
    // which insets can be null replaced by new insets(0,0,0,0)
    public void addComponent(JComponent component, int row, int col, int width,
            int height, int anchor, int weightx, int weighty, int ipadx,
            int ipady, Insets insets, int fill) {
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.anchor = anchor;
        constraints.weightx = weightx;
        constraints.weighty = weighty;
        constraints.ipadx = ipadx;
        constraints.ipady = ipady;
        constraints.insets = insets == null ? new Insets(0, 0, 0, 0) : insets;
        constraints.fill = fill;
        add(component, constraints);
    }
}
