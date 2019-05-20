package com.github.ligangty.refile.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Locale;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import com.github.ligangty.refile.util.LocaleHelper;
import com.github.ligangty.refile.util.UIConstants;

public class RenameUI {

    private LocaleHelper localHelper = LocaleHelper.getInstance();
    private final JFrame mainBoard = new JFrame();
    private final JMenuBar menuBar = new JMenuBar();
    private final JMenu fileMenu = new JMenu();
    private final JMenuItem fileOpen = new JMenuItem();
    private final JMenu languageMenu = new JMenu();
    private final JMenuItem fileClose = new JMenuItem();
    private final JMenuItem eng = new JMenuItem("English");
    private final JMenuItem chn = new JMenuItem("Chinese");
    private final JPanel panel = new JPanel();
    private final TitledBorder upperPanelTitle = new TitledBorder("");
    private final JScrollPane downPane = new JScrollPane();
    private final JTable talbe = new JTable();

    /**
     * Launch the application
     *
     * @param args -
     */
    public static void main(String[] args) {
        try {
            RenameUI window = new RenameUI();
            window.mainBoard.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the application
     */
    private RenameUI() {
        initialize();
    }

    /**
     * Initialize the contents of the frame
     */
    private void initialize() {
        setTextByLocale();

        final BorderLayout borderLayout = new BorderLayout();
        borderLayout.setHgap(10);
        mainBoard.getContentPane().setLayout(borderLayout);

        mainBoard.setForeground(new Color(255, 128, 128));
        mainBoard.setBounds(100, 100, 436, 491);
        mainBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainBoard.setJMenuBar(menuBar);

        menuBar.add(fileMenu);

        fileOpen.addActionListener(e -> {
            JFileChooser fileDialog = new JFileChooser();
            fileDialog.setMultiSelectionEnabled(true);
            // fileDialog.setVisible(true);
            int result = fileDialog.showOpenDialog(mainBoard);
            String msg;
            if (result == JFileChooser.APPROVE_OPTION) {
                File[] files = fileDialog.getSelectedFiles();
                for (File file : files) {
                    System.out.println(file.getName());
                    System.out.println(file.getAbsolutePath());
                }
                String fname = fileDialog.getName(fileDialog
                        .getSelectedFile());
                mainBoard.setTitle(fname);
                msg = "File Open Approved";
            } else {
                msg = "File Open Cancelled";
            }
            JOptionPane.showMessageDialog(mainBoard, msg);
        });
        fileMenu.add(fileOpen);

        fileClose.addActionListener(e -> System.exit(0));
        fileMenu.add(fileClose);

        menuBar.add(languageMenu);

        eng.addActionListener(e -> {
            RenameUI.this.changeLocale(new Locale("en", "us"));
            setTextByLocale();
        });
        languageMenu.add(eng);

        chn.addActionListener(e -> {
            RenameUI.this.changeLocale(new Locale("zh", "cn"));
            setTextByLocale();
        });
        languageMenu.add(chn);

        final GridBagLayout upperJpanelLayout = new GridBagLayout();
        panel.setLayout(upperJpanelLayout);
        panel.setBorder(upperPanelTitle);
        mainBoard.getContentPane().add(panel, BorderLayout.NORTH);

        downPane.add(talbe);
        mainBoard.getContentPane().add(downPane, BorderLayout.CENTER);
    }

    private void setTextByLocale() {
        mainBoard.setTitle(localHelper
                .getLocaleString(UIConstants.MAIN_WINDOW_TITLE));
        fileMenu.setText(localHelper.getLocaleString(UIConstants.MENU_FILE));
        fileOpen
                .setText(localHelper.getLocaleString(UIConstants.MENU_FILE_OPEN));
        fileClose.setText(localHelper
                .getLocaleString(UIConstants.MENU_FILE_EXIT));
        languageMenu.setText(localHelper
                .getLocaleString(UIConstants.MENU_LANGUAGE));
        upperPanelTitle.setTitle(localHelper
                .getLocaleString(UIConstants.BORDER_OPTIONS_TITLE));
    }

    private void changeLocale(Locale locale) {
        this.localHelper = LocaleHelper.getInstance(locale);
    }
}
