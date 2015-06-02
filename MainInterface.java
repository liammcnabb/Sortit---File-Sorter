import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dingle on 02/06/2015.
 */
public class MainInterface extends JFrame {

    final String INPUT_FILE =   "Input File:    ";
    final String OUTPUT_FILE =  "Output File: ";
    final String BROWSE_BUTTON = "...";
    Dimension defaultSize = new Dimension(800,600);
    Dimension buttonSize = new Dimension(35,20);
    EmptyBorder defaultBorder = new EmptyBorder(5,5,5,5);
    JLabel lblInputFile, lblOutputFile;
    JTextField txtInputFile, txtOutputFile;
    JButton btnInputFile, btnOutputFile;
    JPanel pnlInputFile, pnlOutputFile, pnlFileSelector, pnlFolderExample,
            pnlMain, pnlSettings;
    JTextArea txaProgress;
    JFileChooser inputChooser, outputChooser;
    JScrollPane scrollPaneExample, scrollPaneProgress, scrollPaneSettings;


    public MainInterface()
    {
        initialiseComponents();
    }

    public boolean initialiseComponents()
    {
        this.setResizable(true);
        this.setMinimumSize(defaultSize);
        this.setPreferredSize(defaultSize);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        pnlFileSelector = new JPanel();
        pnlFileSelector.setPreferredSize(new Dimension(800,85));
        initInputFile();
        initOutputFile();
        add(pnlFileSelector, BorderLayout.NORTH);
        pnlMain = new JPanel();
        initExampleFolder();
        initSettings();
        add(pnlMain, BorderLayout.CENTER);
        initProgress();



        return true;
    }

    public boolean initInputFile()
    {
        lblInputFile = new JLabel();
        lblInputFile.setText(INPUT_FILE);
        lblInputFile.setHorizontalAlignment(JLabel.RIGHT);
        lblInputFile.setVerticalAlignment(JLabel.CENTER);
        txtInputFile = new JTextField(55);

        btnInputFile = new JButton();
        btnInputFile.setText(BROWSE_BUTTON);
        btnInputFile.setPreferredSize(buttonSize);
        btnInputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                inputChooser = new JFileChooser();
                inputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (inputChooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION)
                {
                    txtInputFile.setText(inputChooser.getSelectedFile().getAbsolutePath());
                }



            }
        });

        pnlInputFile = new JPanel();
        pnlInputFile.add(lblInputFile, BorderLayout.WEST);
        pnlInputFile.add(txtInputFile, BorderLayout.NORTH);
        pnlInputFile.add(btnInputFile, BorderLayout.EAST);

        pnlFileSelector.add(pnlInputFile, BorderLayout.NORTH);

        return true;
    }

    public boolean initOutputFile()
    {
        lblOutputFile = new JLabel();
        lblOutputFile.setText(OUTPUT_FILE);
        lblOutputFile.setHorizontalAlignment(JLabel.RIGHT);
        lblOutputFile.setVerticalAlignment(JLabel.CENTER);
        txtOutputFile = new JTextField(55);

        btnOutputFile = new JButton();
        btnOutputFile.setText(BROWSE_BUTTON);
        btnOutputFile.setPreferredSize(buttonSize);
        btnOutputFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                outputChooser = new JFileChooser();
                outputChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                if (outputChooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION)
                {
                    txtOutputFile.setText(outputChooser.getSelectedFile().getAbsolutePath());
                }
            }
        });

        pnlOutputFile = new JPanel();
        pnlOutputFile.add(lblOutputFile, BorderLayout.WEST);
        pnlOutputFile.add(txtOutputFile, BorderLayout.NORTH);
        pnlOutputFile.add(btnOutputFile, BorderLayout.EAST);

        pnlFileSelector.add(pnlOutputFile, BorderLayout.CENTER);

        return true;
    }

    public boolean initExampleFolder()
    {
        pnlFolderExample = new JPanel();
        //pnlFolderExample.setPreferredSize(new Dimension(750,300));
        pnlFolderExample.setBackground(Color.WHITE);
        scrollPaneExample = new JScrollPane(pnlFolderExample)
        {
            public Dimension getPreferredSize() {
                return new Dimension(550,300);
            }
        };

        pnlMain.add(scrollPaneExample, BorderLayout.CENTER);


        return true;
    }

    public boolean initSettings()
    {
        pnlSettings = new JPanel();
        //pnlFolderExample.setPreferredSize(new Dimension(750,300));
        pnlSettings.setBackground(Color.WHITE);
        scrollPaneSettings = new JScrollPane(pnlSettings)
        {
            public Dimension getPreferredSize() {
                return new Dimension(200,300);
            }
        };

        pnlMain.add(scrollPaneSettings, BorderLayout.WEST);


        return true;
    }

    public boolean initProgress()
    {
        txaProgress = new JTextArea();
        //txaProgress.setPreferredSize(new Dimension(750,300));
        txaProgress.setEnabled(false);
        scrollPaneProgress = new JScrollPane(txaProgress)
        {
            public Dimension getPreferredSize() {
                return new Dimension(750,150);
            }
        };

        add(scrollPaneProgress, BorderLayout.SOUTH);


        return true;
    }
}
