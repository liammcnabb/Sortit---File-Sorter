import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Dingle on 02/06/2015.
 */
public class MainInterface extends JFrame {

    final String INPUT_FILE = "Input File:  ";
    final String OUTPUT_FILE = "Output File:  ";
    final String BROWSE_BUTTON = "...";
    Dimension defaultSize = new Dimension(800,600);
    Dimension buttonSize = new Dimension(35,20);
    EmptyBorder defaultBorder = new EmptyBorder(5,5,5,5);
    JLabel lblInputFile, lblOutputFile;
    JTextField txtInputFile, txtOutputFile;
    JButton btnInputFile, btnOutputFile;
    JPanel pnlInputFile, pnlOutputFile, pnlFileSelector;
    JFileChooser inputChooser, outputChooser;


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
        pnlFileSelector.setPreferredSize(new Dimension(800,200));
        initInputFile();
        initOutputFile();
        add(pnlFileSelector, BorderLayout.NORTH);



        return true;
    }

    public boolean initInputFile()
    {
        lblInputFile = new JLabel();
        lblInputFile.setText(INPUT_FILE);
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

}
