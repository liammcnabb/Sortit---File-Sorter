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
    JButton btnInputFile, btnOutputFile, btnApply;
    JPanel pnlInputFile, pnlOutputFile, pnlFileSelector, pnlFolderExample,
            pnlMain, pnlSettings, pnlMatches;
    JTextArea txaProgress, txaMatches;
    JFileChooser inputChooser, outputChooser;
    JScrollPane scrollPaneExample, scrollPaneProgress, scrollPaneSettings;
    List<Match> sortedLists;
    List matches;


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
                return new Dimension(200,250);
            }
        };
        pnlMatches = new JPanel();
        pnlMatches.setBorder(BorderFactory.createBevelBorder(1));
        btnApply = new JButton();
        btnApply.setText("Apply");
        btnApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                sort();
            }
        });


        pnlMatches.add(scrollPaneSettings, BorderLayout.NORTH);
        pnlMatches.add(btnApply, BorderLayout.SOUTH);
        pnlMain.add(pnlMatches, BorderLayout.WEST);


        return true;
    }

    public boolean initProgress() {
        txaProgress = new JTextArea();
        //txaProgress.setPreferredSize(new Dimension(750,300));
        txaProgress.setEnabled(false);
        scrollPaneProgress = new JScrollPane(txaProgress) {
            public Dimension getPreferredSize() {
                return new Dimension(750, 150);
            }
        };

        add(scrollPaneProgress, BorderLayout.SOUTH);


        return true;
    }

    public boolean addFilestoPanel(String directory) {
        File sortFolder = new File(directory);
        File[] fileList = sortFolder.listFiles();
        String file = "";
        for (int i = 0; i < fileList.length; i++) {
            //TODO Display Folders on panel
        }

        return true;
    }

    public boolean createSortingNames(String directory) {
        File sortFolder = new File(directory);
        File[] fileList = sortFolder.listFiles();
        String file = "";

        List<Object> toRemove = new ArrayList<Object>();
        matches = new ArrayList();
        sortedLists = new ArrayList<Match>();

        for (int i = 0; i < fileList.length; i++) {
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


                    StringBuffer sb = new StringBuffer();
                    boolean flag = true;
                    for (int j = 0; j < items.size(); j++) {

                        if (items.get(j).equals("-")) {
                            if (items.get(j + 1).contains("c") || items.get(j + 1).contains("v")) {
                                for (int k = 0; k < 10; k++) {
                                    if (items.get(j + 1).contains(Integer.toString(k))) {
                                        flag = false;
                                    }
                                }
                            }
                        } else if (items.get(j).contains("c") || items.get(j).contains("v")) {
                            for (int k = 0; k < 10; k++) {
                                if (items.get(j).contains(Integer.toString(k))) {
                                    flag = false;
                                }
                            }
                        } else if (items.get(j).contains("chapter") || items.get(j).contains("Chapter")) {
                            flag = false;
                        } else if (flag) {
                            sb.append(items.get(j) + " ");
                        }

                    }

                    String displayString = sb.toString().trim();

                    if (sortedLists.size() == 0) {
                        Match match = new Match(displayString,fileList[i]);
                        //matches.add(displayString);
                        sortedLists.add(match);
                    } else {
                        flag = false;
                        for (int j = 0; j < sortedLists.size(); j++) {
                            if (displayString.equals(sortedLists.get(j).getTitle())) {
                                flag = true;
                                sortedLists.get(j).addFile(fileList[i]);
                            }


                        }
                        if (!flag) {
                            Match match = new Match(displayString,fileList[i]);
                            sortedLists.add(match);
                        }

                    }

                }
            }
        }
        setMatches();

        return true;
    }

    public boolean setMatches() {
        txaMatches = new JTextArea();

        for (int i = 0; i < sortedLists.size(); i++) {
            txaMatches.append("(" + sortedLists.get(i).getMatchedFiles().size() +
                    ") -" + sortedLists.get(i).getTitle() + "\n");
        }

        pnlSettings.add(txaMatches, BorderLayout.NORTH);
        pnlSettings.updateUI();
        return true;
    }

    public boolean sort()
    {
        File outputFolder = new File(txtOutputFile.getText());
        File[] outputDirectories = outputFolder.listFiles();
        boolean flag= false;
        for(int i=0; i<sortedLists.size(); i++)
        {
            for(int j=0; j <outputDirectories.length; j++)
            {
                System.out.println(sortedLists.get(i).getTitle());
                System.out.println(outputDirectories[j].getName());
                if(sortedLists.get(i).getTitle().equals(outputDirectories[j].getName()))
                {
                    for(int k=0; k<sortedLists.get(i).getMatchedFiles().size(); k++)
                    {
                        sortedLists.get(i).getMatchedFiles().get(k).renameTo(new File(txtOutputFile.getText()
                                +"/"+ outputDirectories[j].getName() +"/"+
                                sortedLists.get(i).getMatchedFiles().get(k).getName()));

                        txaProgress.append(sortedLists.get(i).getMatchedFiles().get(k).getName()
                                + ": was moved to folder '" + outputDirectories[j].getName() +"'\n");

                        txaProgress.updateUI();
                    }
                }
            }
        }


        return true;
    }
}
