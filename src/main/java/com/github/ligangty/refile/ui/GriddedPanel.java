package com.github.ligangty.refile.ui;

import javax.swing.*;
import java.awt.*;

public class GriddedPanel extends JPanel {

    private GridBagConstraints constraints;
    // Default constraints value definitions
    private static final int C_HORZ = GridBagConstraints.HORIZONTAL;
    private static final int C_NONE = GridBagConstraints.NONE;
    private static final int C_WEST = GridBagConstraints.WEST;
    private static final int C_WIDTH = 1;
    private static final int C_HEIGHT = 1;

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

    // Add a component to the specified row and column
    public void addComponent(JComponent component, int row, int col) {
        addComponent(component, row, col, C_WIDTH, C_HEIGHT, C_WEST, C_NONE);
    }

    // Add a component to the specified row and column, spanning across
    // a specified number of columns and rows
    public void addComponent(JComponent component, int row, int col, int width,
            int height) {
        addComponent(component, row, col, width, height, C_WEST, C_NONE);
    }

    // Add a component to the specified row and column, using a specified
    // anchor constraint
    public void addAnchoredComponent(JComponent component, int row, int col,
            int anchor) {
        addComponent(component, row, col, C_WIDTH, C_HEIGHT, anchor, C_NONE);
    }

    // Add a component to the specified row and column, spanning across
    // a specified number of columns and rows, using a specified
    // anchor constraint
    public void addAnchoredComponent(JComponent component, int row, int col,
            int width, int height, int anchor) {
        addComponent(component, row, col, width, height, anchor, C_NONE);
    }

    // Add a component to the specified row and column,
    // filling the column horizontally
    public void addFilledComponent(JComponent component, int row, int col) {
        addComponent(component, row, col, C_WIDTH, C_HEIGHT, C_WEST, C_HORZ);
    }

    // Add a component to the specified row and column
    // with the specified fill constraint
    public void addFilledComponent(JComponent component, int row, int col,
            int fill) {
        addComponent(component, row, col, C_WIDTH, C_HEIGHT, C_WEST, fill);
    }

    // Add a component to the specified row and column,
    // spanning a specified number of columns and rows,
    // with the specified fill constraint
    public void addFilledComponent(JComponent component, int row, int col,
            int width, int height, int fill) {
        addComponent(component, row, col, width, height, C_WEST, fill);
    }

    // Add a component to the specified row and column,
    // spanning the specified number of columns and rows, with
    // the specified fill and anchor constraints
    public void addComponent(JComponent component, int row, int col, int width,
            int height, int anchor, int fill) {
        constraints.gridx = col;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.anchor = anchor;
        double weightx = 0.0;
        double weighty = 0.0;
        // Only use extra horizontal or vertical space if a component
        // spans more than one column and/or row
        if (width > 1) {
            weightx = 1.0;
        }
        if (height > 1) {
            weighty = 1.0;
        }
        switch (fill) {
            case GridBagConstraints.HORIZONTAL:
                constraints.weightx = weightx;
                constraints.weighty = 0.0;
                break;
            case GridBagConstraints.VERTICAL:
                constraints.weighty = weighty;
                constraints.weightx = 0.0;
                break;
            case GridBagConstraints.BOTH:
                constraints.weightx = weightx;
                constraints.weighty = weighty;
                break;
            case GridBagConstraints.NONE:
                constraints.weightx = 0.0;
                constraints.weighty = 0.0;
                break;
            default:
                break;
        }
        constraints.fill = fill;
        add(component, constraints);
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
