package com.github.ligangty.refile.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.github.ligangty.refile.handle.FileNameObj;
import com.github.ligangty.refile.handle.TemplateException;
import com.github.ligangty.refile.util.FileRenameUtil;
import com.github.ligangty.refile.util.LocaleHelper;
import com.github.ligangty.refile.util.UIConstants;

/**
 * main GUI class for this project
 *
 * P.S.: 1.change postfix function not implemented. 2.change template for start
 * by alphabeta replacing the ? not implemented. 3.Upper Lower transformation
 * was not implemented
 *
 * @author ligangty@github.com
 * @date Apri 14, 2009
 */
public class RenameView {

    // localization helper
    private LocaleHelper localeHelper = LocaleHelper.getInstance();
    // the main GUI container
    private final JFrame mainFrame = new JFrame();
    private final TitledBorder optionsBorder = new TitledBorder("");
    // showing label for main file name template string "templateList"
    private final JLabel templateLabel = new JLabel();
    // selected to determine if to use the template all
    private final JCheckBox useTemplateCheckBox = new JCheckBox();
    // the input box for template string of changing the main file name
    private final JComboBox templateList = new JComboBox();
    // the input box for template string of changing the postfix file name
    // not build the handler for this box
    private final JComboBox suffixList = new JComboBox();
    // Upper Lower transformation options list
    private final JComboBox ulChangeList = new JComboBox();
    // readme show in this text area
    private final JTextArea textArea = new JTextArea();
    // for containing the useNumberRadioButton and useAlphaBetaRadioButton
    private final ButtonGroup buttonGroup = new ButtonGroup();
    // selected for change the ? to number
    private final JRadioButton useNumberRadioButton = new JRadioButton();
    // selected for change the ? to alphabeta
    private final JRadioButton useAlphaBetaRadioButton = new JRadioButton();
    // showing label for start position startPosSpn
    private final JLabel startPositionLabel = new JLabel();
    // for change the ? which start from, number or alphabeta
    private final JSpinner startPosSpn = new JSpinner();
    private final JButton previewButton = new JButton();
    private final JButton beginButton = new JButton();
    private final JButton addButton = new JButton();
    private final JButton removeButton = new JButton();
    private final JCheckBox changeSuffixCheck = new JCheckBox();
    private final JLabel previewLabel = new JLabel();
    private final JLabel upperLowerTransformLabel = new JLabel();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu();
    private final JMenuItem fileOpen = new JMenuItem();
    private final JMenuItem fileClose = new JMenuItem();
    // main table to show the changing preview results
    private final JTable reviewTable = new JTable();
    private static final int MAIN_FRAME_PREFERRED_WIDTH = 520;
    private static final int MAIN_FRAME_PREFERRED_HEIGHT = 550;
    private static final int TABLE_SCROLL_VIEW_PREFER_HEIGHT = 200;

    // used for language selection, for future add-on
    // private final JMenu languageMenu = new JMenu();
    // private final JMenuItem eng = new JMenuItem();
    // private final JMenuItem chn = new JMenuItem();
    /**
     * Create the frame
     */
    public RenameView() {

        mainFrame.setResizable(false);
        mainFrame.setBounds(400, 250, MAIN_FRAME_PREFERRED_WIDTH,
                MAIN_FRAME_PREFERRED_HEIGHT);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // previewButton.setBorderPainted(false);
        // beginButton.setBorderPainted(false);
        // addButton.setBorderPainted(false);
        // removeButton.setBorderPainted(false);

        initMenu();

        final JPanel upperPanel = new JPanel();
        upperPanel.setLayout(new BorderLayout());
        mainFrame.getContentPane().add(upperPanel, BorderLayout.NORTH);

        final GriddedPanel optionsPanel = new GriddedPanel();
        optionsPanel.setBorder(optionsBorder);
        upperPanel.add(optionsPanel, BorderLayout.NORTH);

        optionsPanel.addComponent(templateLabel, 5, 0, 1, 1,
                GridBagConstraints.WEST, 0, 0, 0, -5, null,
                GridBagConstraints.NONE);

        textArea.setEditable(false);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setBackground(mainFrame.getBackground());
        optionsPanel.addComponent(textArea, 0, 2, 7, 5,
                GridBagConstraints.CENTER, 1, 0, 170, 0, null,
                GridBagConstraints.BOTH);

        useTemplateCheckBox.setSelected(true);
        useTemplateCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (Enumeration enm = buttonGroup.getElements(); enm.hasMoreElements();) {
                    JComponent subComp = (JComponent) enm.nextElement();
                    subComp.setEnabled(useTemplateCheckBox.isSelected());
                }
                startPosSpn.setEnabled(useTemplateCheckBox.isSelected());
                templateList.setEnabled(useTemplateCheckBox.isSelected());
                suffixList.setEnabled(useTemplateCheckBox.isSelected() ? changeSuffixCheck.isSelected()
                        : false);
                changeSuffixCheck.setEnabled(useTemplateCheckBox.isSelected());
                startPositionLabel.setEnabled(useTemplateCheckBox.isSelected());
                templateLabel.setEnabled(useTemplateCheckBox.isSelected());

            }
        });
        optionsPanel.addComponent(useTemplateCheckBox, 0, 0, 2, 1,
                GridBagConstraints.WEST, 0, 0, 0, 0, null,
                GridBagConstraints.NONE);

        buttonGroup.add(useNumberRadioButton);
        useNumberRadioButton.setSelected(true);
        optionsPanel.addComponent(useNumberRadioButton, 1, 0, 2, 1,
                GridBagConstraints.WEST, 0, 0, 0, 0, new Insets(0, 10, 0, 0),
                GridBagConstraints.NONE);

        buttonGroup.add(useAlphaBetaRadioButton);
        optionsPanel.addComponent(useAlphaBetaRadioButton, 2, 0, 2, 1,
                GridBagConstraints.WEST, 0, 0, 0, 0, new Insets(0, 10, 0, 0),
                GridBagConstraints.NONE);

        optionsPanel.addComponent(startPositionLabel, 4, 0, 1, 1,
                GridBagConstraints.EAST, 0, 0, 0, 0, null,
                GridBagConstraints.NONE);

        templateList.setEditor(new FixedWidthComboBoxEditor(8));
        templateList.setEditable(true);
        optionsPanel.addComponent(templateList, 5, 1, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, -5, new Insets(5, 0, 0, 0),
                GridBagConstraints.NONE);

        optionsPanel.addComponent(startPosSpn, 4, 1, 1, 1,
                GridBagConstraints.WEST, 0, 0, 10, 0, new Insets(0, 5, 0, 0),
                GridBagConstraints.NONE);

        optionsPanel.addComponent(changeSuffixCheck, 5, 5, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, 0, null,
                GridBagConstraints.NONE);
        String[] sampleSuffixs = {"", ".exe", ".txt", ".pdf", ".doc", ".jpg",
            ".gif", ".png"};
        suffixList.setModel(new DefaultComboBoxModel(sampleSuffixs));
        suffixList.setEditor(new FixedWidthComboBoxEditor());
        suffixList.setEditable(true);
        suffixList.setEnabled(false); // when initializing, make the suffix name cannot be changed
        optionsPanel.addComponent(suffixList, 5, 6, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, -5, new Insets(3, 0, 0, 0),
                GridBagConstraints.NONE);
        changeSuffixCheck.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                suffixList.setEnabled(changeSuffixCheck.isSelected());
            }
        });

        previewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (useTemplateCheckBox.isSelected()) {
                    String template = templateList.getSelectedItem().toString();
                    String startIndexStr = startPosSpn.getModel().getValue().toString();
                    int startIndex = Integer.parseInt(startIndexStr);
                    Vector fileNames = ((FileChooseTableModel) reviewTable.getModel()).getFileNameSet();
                    if (useNumberRadioButton.isSelected()) {
                        try {
                            FileRenameUtil.renameFilesUsingNum(fileNames, template, startIndex, false);
                        } catch (TemplateException ex) {
                            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
                        }
                    }
                    ((AbstractTableModel) reviewTable.getModel()).fireTableDataChanged();
                }
            }
        });

        beginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (useTemplateCheckBox.isSelected()) {
                    String template = templateList.getSelectedItem().toString();
                    String startIndexStr = startPosSpn.getModel().getValue().toString();
                    int startIndex = Integer.parseInt(startIndexStr);
                    Vector fileNames = ((FileChooseTableModel) reviewTable.getModel()).getFileNameSet();
                    if (useNumberRadioButton.isSelected()) {
                        try {
                            FileRenameUtil.renameFilesUsingNum(fileNames, template, startIndex, true);
                            JOptionPane.showMessageDialog(mainFrame, localeHelper.getLocaleString(
                                    UIConstants.MESSAGE_FILERENAMED));
                        } catch (TemplateException ex) {
                            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
                        } catch (FileNotFoundException ex) {
                            JOptionPane.showMessageDialog(mainFrame, ex.getMessage());
                        }
                    }
                    ((AbstractTableModel) reviewTable.getModel()).fireTableDataChanged();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 0));
        buttonPanel.add(previewButton);
        buttonPanel.add(beginButton);
        optionsPanel.addComponent(buttonPanel, 5, 7, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, -5, new Insets(3, 5, 0, 0),
                GridBagConstraints.NONE);

        final GriddedPanel fileChoosePanel = new GriddedPanel();
        upperPanel.add(fileChoosePanel, BorderLayout.CENTER);

        fileChoosePanel.addComponent(previewLabel, 0, 0, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, 0, new Insets(3, 5, 3, 5),
                GridBagConstraints.NONE);

        addButton.setMaximumSize(new Dimension(0, 0));
        addButton.addActionListener(new FileAddListener());
        fileChoosePanel.addComponent(addButton, 0, 1, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, -5, new Insets(3, 0, 3, 5),
                GridBagConstraints.NONE);

        removeButton.addActionListener(new FileDelListener());
        fileChoosePanel.addComponent(removeButton, 0, 2, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, -5, new Insets(3, 0, 3, 5),
                GridBagConstraints.NONE);

        fileChoosePanel.addComponent(upperLowerTransformLabel, 0, 3, 1, 1,
                GridBagConstraints.CENTER, 0, 0, 0, 0, new Insets(3, 0, 3, 0),
                GridBagConstraints.NONE);

        String[] sampleUpperLowerTemps = {""};
        ulChangeList.setModel(new DefaultComboBoxModel(sampleUpperLowerTemps));
        fileChoosePanel.addComponent(ulChangeList, 0, 4, 1, 1,
                GridBagConstraints.WEST, 1, 0, 0, -5, new Insets(3, 0, 3, 0),
                GridBagConstraints.NONE);

        final JScrollPane reviewPane = new JScrollPane();
        mainFrame.getContentPane().add(reviewPane, BorderLayout.CENTER);
        reviewPane.getViewport().setPreferredSize(
                new Dimension(520, TABLE_SCROLL_VIEW_PREFER_HEIGHT));

        initReviewTable();
        reviewPane.getViewport().setView(reviewTable);

        setTextByLocale(Locale.getDefault());

        mainFrame.setVisible(true);
    }

    protected void initMenu() {
        mainFrame.setJMenuBar(menuBar);

        menuBar.add(fileMenu);

        fileOpen.addActionListener(new FileAddListener());
        fileMenu.add(fileOpen);

        fileClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileMenu.add(fileClose);

        // used for language selection, for future add-on
		/*
         menuBar.add(languageMenu);
         eng.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         setTextByLocale(new Locale("en", "us"));
         }
         });
         languageMenu.add(eng);
         chn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
         setTextByLocale(new Locale("zh", "cn"));
         }
         });
         languageMenu.add(chn);
         */
    }

    private void initReviewTable() {
        reviewTable.setAutoCreateColumnsFromModel(false);
        reviewTable.setModel(new FileChooseTableModel());
        reviewTable.addColumn(new TableColumn(0, 100));
        reviewTable.addColumn(new TableColumn(1, 100));
        reviewTable.addColumn(new TableColumn(2, 320));
        reviewTable.getTableHeader().setReorderingAllowed(false);
    }

    protected void setTextByLocale(Locale locale) {
        changeLocale(locale);
        // remove all template item to re-add other locale-spec items
        // if (templateList.getModel().getSize() > 0) {
        // templateList.removeAllItems();
        // }
        initTemplates();

        mainFrame.setTitle(localeHelper.getLocaleString(UIConstants.MAIN_WINDOW_TITLE));
        optionsBorder.setTitle(localeHelper.getLocaleString(UIConstants.BORDER_OPTIONS_TITLE));
        templateLabel.setText(localeHelper.getLocaleString(UIConstants.LABEL_TEMPLATE));
        useTemplateCheckBox.setText(localeHelper.getLocaleString(UIConstants.CHECKBOX_USETEMPLATE));
        useNumberRadioButton.setText(localeHelper.getLocaleString(UIConstants.RADIO_NUMTOCHANGE));
        useAlphaBetaRadioButton.setText(localeHelper.getLocaleString(UIConstants.RADIO_ALPHATOCHANGE));
        startPositionLabel.setText(localeHelper.getLocaleString(UIConstants.LABEL_STARTPOSITION));
        previewButton.setText(localeHelper.getLocaleString(UIConstants.BUTTON_PREVIEW));
        beginButton.setText(localeHelper.getLocaleString(UIConstants.BUTTON_BEGIN));
        changeSuffixCheck.setText(localeHelper.getLocaleString(UIConstants.LABEL_CHANGESUFFIX));
        previewLabel.setText(localeHelper.getLocaleString(UIConstants.LABEL_PREVIEW));
        addButton.setText(localeHelper.getLocaleString(UIConstants.BUTTON_ADD));
        removeButton.setText(localeHelper.getLocaleString(UIConstants.BUTTON_REMOVE));
        upperLowerTransformLabel.setText(localeHelper.getLocaleString(UIConstants.LABEL_ULTRANSFORM));

        fileMenu.setText(localeHelper.getLocaleString(UIConstants.MENU_FILE));
        fileOpen.setText(localeHelper.getLocaleString(UIConstants.MENU_FILE_OPEN));
        fileClose.setText(localeHelper.getLocaleString(UIConstants.MENU_FILE_EXIT));

        // used for language selection, for future add-on
		/*
         languageMenu.setText(localHelper
         .getLocalString(UIConstants.MENU_LANGUAGE));
         eng.setText(localHelper.getLocalString(UIConstants.MENU_LANGUAGE_ENG));
         chn.setText(localHelper.getLocalString(UIConstants.MENU_LANGUAGE_CHN));
         */

        textArea.replaceRange(localeHelper.getLocaleString(UIConstants.TEMPLATE_README), 0, textArea.getDocument().getLength());

        mainFrame.pack();
    }

    protected void initTemplates() {
        int templatesCount = Integer.parseInt(localeHelper.getLocaleString(UIConstants.TEMPLATE_COUNT));
        String[] templates = new String[templatesCount];
        for (int i = 0; i < templatesCount; i++) {
            templates[i] = localeHelper.getLocaleString(UIConstants.TEMPLATE_BASE + i);
        }
        templateList.setModel(new DefaultComboBoxModel(templates));
    }

    public void changeLocale(Locale locale) {
        this.localeHelper = LocaleHelper.getInstance(locale);
    }

    class FixedWidthComboBoxEditor extends BasicComboBoxEditor {

        public FixedWidthComboBoxEditor() {
            this(2);
        }

        public FixedWidthComboBoxEditor(int columns) {
            editor = new JTextField("", columns);
            editor.setBorder(null);
        }
    }

    class FileAddListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            FileChooseTableModel model = (FileChooseTableModel) reviewTable.getModel();
            Vector fileNames = model.getFileNameSet();
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setMultiSelectionEnabled(true);
            int result = fileDialog.showOpenDialog(mainFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File[] files = fileDialog.getSelectedFiles();
                for (int i = 0; i < files.length; i++) {
                    FileNameObj fNameObj = new FileNameObj(files[i].getAbsolutePath());
                    boolean isAdded = false;
                    Iterator iter = fileNames.iterator();
                    while (iter.hasNext()) {
                        if (fNameObj.equals(iter.next())) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded) {
                        fileNames.add(fNameObj);
                    }
                }
            }
            model.fireTableDataChanged();
        }
    }

    class FileDelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            FileChooseTableModel model = (FileChooseTableModel) reviewTable.getModel();
            Vector fileNames = model.getFileNameSet();
            int[] columnsInds = reviewTable.getSelectedRows();
            Vector selectedFileNames = new Vector();
            for (int i = 0; i < columnsInds.length; i++) {
                if (columnsInds[i] < fileNames.size()) {
                    selectedFileNames.add(fileNames.get(columnsInds[i]));
                }
            }
            fileNames.removeAll(selectedFileNames);
            model.fireTableDataChanged();
        }
    }

    class FileChooseTableModel extends AbstractTableModel {

        private Vector fileNames;
        private boolean isNewFileInPath;
        private final int INIT_ROW_CNT;
        private String[] colHeaderNames = {
            localeHelper.getLocaleString(UIConstants.TABLE_HEAD_COL_FIR),
            localeHelper.getLocaleString(UIConstants.TABLE_HEAD_COL_SEC),
            localeHelper.getLocaleString(UIConstants.TABLE_HEAD_COL_THI)};

        FileChooseTableModel() {
            this(new Vector());
        }

        FileChooseTableModel(Vector fileNames) {
            setFileNameSet(fileNames);
            INIT_ROW_CNT = (int) Math.floor(TABLE_SCROLL_VIEW_PREFER_HEIGHT / reviewTable.getRowHeight());
        }

        public void setFileNameSet(Vector fileNames) {
            this.fileNames = fileNames;
        }

        public Vector getFileNameSet() {
            return fileNames;
        }

        public boolean isNewFileInPath() {
            return isNewFileInPath;
        }

        public void setNewFileInPath(boolean isNewFileInPath) {
            this.isNewFileInPath = isNewFileInPath;
        }

        public int getColumnCount() {
            return colHeaderNames.length;
        }

        public int getRowCount() {
            int realCount = fileNames.size();
            return realCount > INIT_ROW_CNT ? realCount : INIT_ROW_CNT;
        }

        public String getColumnName(int column) {
            return colHeaderNames[column];
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            if (rowIndex < fileNames.size()) {
                FileNameObj fileName = (FileNameObj) fileNames.get(rowIndex);
                switch (columnIndex) {
                    case 0:
                        return fileName.getOldFileName() + "." + fileName.getOldPostFix();
                    case 1:
                        return fileName.getNewFileName() + "." + fileName.getNewPostFix();
                    case 2:
                        return fileName.getFilePath() + "/";
                    default:
                        return "";
                }
            } else {
                return "";
            }
        }
    }

    /**
     * Launch the application
     *
     * @param args
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new RenameView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
