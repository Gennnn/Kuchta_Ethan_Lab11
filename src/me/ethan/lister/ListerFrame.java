package me.ethan.lister;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ListerFrame extends JFrame {
    JPanel mainPnl = new JPanel();
    JPanel displayPnl = new JPanel();
    JPanel selectPnl = new JPanel();

    JFileChooser chooser = new JFileChooser();

    JButton pickBtn = new JButton();
    JButton quitBtn = new JButton();

    public static JTextPane displayText = new JTextPane();
    public static JScrollPane displayScroller = new JScrollPane();

    public ListerFrame() {
        mainPnl = new JPanel();
        mainPnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        createDisplayPnl();
        createSelectPnl();
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 2;
        c.ipady = 200;
        c.insets = new Insets(0,0,0,0);
        c.weighty = 0.5;
        c.weightx = 0.5;
        c.fill = GridBagConstraints.BOTH;
        mainPnl.add(displayPnl, c);
        c.fill = GridBagConstraints.REMAINDER;
        c.gridy = 2;
        c.ipady = 0;
        c.gridheight = 1;
        mainPnl.add(selectPnl, c);
        add(mainPnl);
        setSize(700,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void createDisplayPnl() {
        displayPnl = new JPanel();
        displayPnl.setLayout(new BorderLayout());
        JPanel displayPnlInternal = new JPanel();
        displayPnlInternal.setLayout(new GridLayout(1,1));
        displayText = new JTextPane();
        displayText.setEditable(false);
        displayText.setBackground(UIManager.getColor ( "Panel.background" ));
        displayScroller = new JScrollPane(displayText);
        displayPnlInternal.add(displayScroller);
        displayPnl.add(displayPnlInternal, BorderLayout.CENTER);
        displayPnlInternal.setBorder(new TitledBorder("Directory Lister"));
        TitledBorder border = (TitledBorder) displayPnlInternal.getBorder();
        border.setTitleJustification(TitledBorder.CENTER);
        border.setTitleFont(new Font("SansSerif", Font.BOLD, 30));
    }
    public void createSelectPnl() {
        selectPnl = new JPanel();
        JPanel selectPnlInternal = new JPanel();
        selectPnlInternal.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        pickBtn = new JButton("Select Directory");
        quitBtn = new JButton("Quit");
        pickBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    int r = chooser.showOpenDialog(null);
                    if (r == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        displayText.setText("");
                        DirectoryListing.listFilesAndDirectories(file);
                    }
                }
        );
        quitBtn.addActionListener(
                (ActionEvent ae) ->
                {
                    System.exit(0);
                }
        );
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        selectPnlInternal.add(pickBtn, c);
        c.gridx = 1;
        selectPnlInternal.add(quitBtn, c);
        selectPnl.add(selectPnlInternal);
    }
}
