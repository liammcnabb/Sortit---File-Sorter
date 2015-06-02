import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

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
    JTextArea txaProgress, txaMatches;
    JFileChooser inputChooser, outputChooser;
    JScrollPane scrollPaneExample, scrollPaneProgress, scrollPaneSettings;
    List<List<File>> sortedLists;


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
                    createSortingNames(txtInputFile.getText());
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

    public boolean addFilestoPanel(String directory)
    {
        File sortFolder = new File(directory);
        File[] fileList = sortFolder.listFiles();
        String file = "";
        for (int i=0; i < fileList.length; i++)
        {
            //TODO Display Folders on panel
        }

        return true;
    }

    public boolean createSortingNames(String directory)
    {
        File sortFolder = new File(directory);
        File[] fileList = sortFolder.listFiles();
        String file = "";

        List<Object> toRemove = new ArrayList<Object>();
        List matches = new ArrayList();
        sortedLists = new ArrayList<List<File>>();

        for (int i=0; i < fileList.length; i++) {
            //System.out.println(i);
            if (fileList[i].isFile()) {
                file = fileList[i].getName();
                //System.out.println(file);
                List<String> items = new ArrayList<String>(Arrays.asList(file.split("[_ ]")));
                System.out.println(items);
                //System.out.println(items.size());

                if (items.size() > 1) {
                    for (Iterator<String> iter = items.listIterator(); iter.hasNext(); ) {
                        String x = iter.next();
                        if (x.contains("[") || x.contains("{") || x.contains("(")) {
                            //iter.remove();
                            toRemove.add(x);
                        }
                    }
                    items.removeAll(toRemove);
                    //System.out.println(items);

                    StringBuffer sb = new StringBuffer();
                    boolean flag = true;
                    for (int j=0; j < items.size(); j++)
                    {

                        if(items.get(j).equals("-"))
                        {
                            flag = false;
                        } else if (flag) {
                            sb.append(items.get(j) + " ");
                        }

                    }

                    String displayString = sb.toString();

                    if (sortedLists.size() == 0)
                    {

                        matches.add(displayString);
                        List sortedFile = new ArrayList<File>();
                        sortedFile.add(fileList[i]);
                        sortedLists.add(sortedFile);
                    }
                    else
                    {
                        flag = false;
                        for (int j=0; j < sortedLists.size(); j++)
                        {
                            if (displayString.equals(matches.get(j)))
                            {
                                flag = true;
                                sortedLists.get(j).add(fileList[i]);
                            }


                        }
                        if(!flag)
                        {
                            matches.add(displayString);
                            List sortedFile = new ArrayList<File>();
                            sortedFile.add(fileList[i]);
                            sortedLists.add(sortedFile);
                        }

                    }

                }
            }
        }
        setMatches(matches);

        return true;
    }

    public boolean setMatches(List matches)
    {
        txaMatches = new JTextArea();

        for(int i=0; i<matches.size();i++)
        {
            txaMatches.append(matches.get(i) + "\n");
        }

        pnlSettings.add(txaMatches, BorderLayout.CENTER);
        return true;
    }
}
