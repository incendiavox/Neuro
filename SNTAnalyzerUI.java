import jdk.nashorn.internal.objects.Global;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * Created by davi2705 on 12/29/2017.
 */
public class SNTAnalyzerUI extends JPanel implements ActionListener{

    /*
     * ComboBoxDemo.java uses these additional files:
     *   images/Bird.gif
     *   images/Cat.gif
     *   images/Dog.gif
     *   images/Rabbit.gif
     *   images/Pig.gif
     */
    //try to git it
        public static String totError = "0";
        JLabel picture;
        String minString = "1";
        String maxString = "1000";
        String pathLenPosString = "E";
        String pathIDPosString = "A";
        String startsOnPosString = "G";
        String primPathPosString = "D";
        String directoryPathString = "";
        File directoryPathFile = null;


        static final String minLabelString = "Minimum value:";
        static final String maxLabelString  ="Maximum value:" ;
        static final String pathLenLabelString ="Path length column:";
        static final String pathIDLabelString ="Path id column:" ;
        static final String strtOnLabelString ="Starts on column:";
        static final String primPathLabelString = "Primary path label:";

        static final String processButtonLabelString = "Process files:";
        static final String directoryButtonLabelString = "Select directory:";

        JButton processButton = null;
        JButton directoryButton = null;
        JComboBox maxList = null;
        JComboBox minList = null;
        JComboBox pathLenPosList = null;
        JComboBox pathIDPosList = null;
        JComboBox startsOnPosList = null;
        JComboBox primPathPosList = null;
        List<String> colValsList= null;
        List<String> maxValsList = null;

        public SNTAnalyzerUI() {
            super(new BorderLayout());

            //String[] petStrings = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };
            String[] maxVals = {"Select","1","10","100","1000","10,000"};
            String[] minVals = {"Select","0","1","10","100"};
            String[] colVals = {"Select","A","B","C","D","E","F","G","H","I","J"};
            JLabel minLabel = new JLabel(minLabelString);
            minLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel maxLabel = new JLabel(maxLabelString);
            maxLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel pathLenLabel = new JLabel(pathLenLabelString);
            pathLenLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel pathIDLabel = new JLabel(pathIDLabelString);
            pathIDLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel strtOnLabel = new JLabel(strtOnLabelString);
            strtOnLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel primPathLabel = new JLabel(primPathLabelString);
            primPathLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel directoryLabel = new JLabel("Select directory:");
            directoryLabel.setFont(new Font("Dialog",Font.BOLD,20));
            JLabel processLabel = new JLabel("Click to process files: ");
            processLabel.setFont(new Font("Dialog",Font.BOLD,25));
            colValsList = Arrays.asList(colVals);
            maxValsList = Arrays.asList(maxVals);


            //Create the combo box, select the item at index 4.
            //Indices start at 0, so 4 specifies the pig.
            maxList = new JComboBox(maxVals);
            maxList.setSelectedIndex(0);
            maxList.addActionListener(this);
            maxList.setToolTipText(maxLabel.getText());

            minList = new JComboBox(minVals);
            minList.setSelectedIndex(0);
            minList.addActionListener(this);
            minList.setToolTipText(minLabel.getText());

            pathLenPosList = new JComboBox(colVals);
            pathLenPosList.setSelectedIndex(colValsList.indexOf(pathLenPosString));
            pathLenPosList.addActionListener(this);
            pathLenPosList.setToolTipText(pathLenLabel.getText());

            pathIDPosList = new JComboBox(colVals);
            pathIDPosList.setSelectedIndex(colValsList.indexOf(pathIDPosString));
            pathIDPosList.addActionListener(this);
            pathIDPosList.setToolTipText(pathIDLabel.getText());

            startsOnPosList = new JComboBox(colVals);
            startsOnPosList.setSelectedIndex(colValsList.indexOf(startsOnPosString));
            startsOnPosList.addActionListener(this);
            startsOnPosList.setToolTipText(strtOnLabel.getText());

            primPathPosList = new JComboBox(colVals);
            primPathPosList.setSelectedIndex(colValsList.indexOf(primPathPosString));
            primPathPosList.addActionListener(this);
            primPathPosList.setToolTipText(primPathLabel.getText());

            processButton = new JButton(processButtonLabelString);
            processButton.addActionListener(this);
            processButton.setToolTipText("Select all the values above then this button will be available to click.");
            processButton.setEnabled(false);

            directoryButton = new JButton(directoryButtonLabelString);
            directoryButton.addActionListener(this);
            directoryButton.setToolTipText("Select directory location for files.");




            JPanel secPanel = new JPanel(new GridLayout(8, 2));
            secPanel.add(minLabel);
            //pathIDPosList.setAlignmentX(Component.LEFT_ALIGNMENT);
            secPanel.add(minList);
            secPanel.add(maxLabel);
            secPanel.add(maxList);
            secPanel.add(pathLenLabel);
            secPanel.add(pathLenPosList);
            secPanel.add(pathIDLabel);
            secPanel.add(pathIDPosList);
            secPanel.add(strtOnLabel);
            secPanel.add(startsOnPosList);
            secPanel.add(primPathLabel);
            secPanel.add(primPathPosList);
            secPanel.add(directoryLabel);
            secPanel.add(directoryButton);
            secPanel.add(processLabel);
            secPanel.add(processButton);

            secPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

            //add(Box.createRigidArea(new Dimension(0, 10)));
            add(secPanel,BorderLayout.PAGE_START);


            setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        }

        /** Listens to the combo box. */
        public void actionPerformed(ActionEvent e) {
            //System.out.println("PRD: " + e);
            System.out.println("PRD: " + e.getActionCommand());
            if("comboBoxChanged".equals(e.getActionCommand())) {
                // Must be a ComboBox updated for a selection change
                JComboBox cb = (JComboBox) e.getSource();
                String petName = (String) cb.getSelectedItem();
                System.out.println("CD: " + petName + " : " + cb.getToolTipText());
                switch (cb.getToolTipText()) {
                    case minLabelString:
                        minString = petName;
                        break;
                    case maxLabelString:
                        maxString = petName;
                        break;
                    case pathLenLabelString:
                        pathLenPosString = petName;
                        break;
                    case pathIDLabelString:
                        pathIDPosString = petName;
                        break;
                    case strtOnLabelString:
                        startsOnPosString = petName;
                        break;
                    case primPathLabelString:
                        primPathPosString = petName;
                        break;
                    default:
                        System.out.println("unknown tooltip: " + cb.getToolTipText());
                        break;
                }

            }
            else if (processButtonLabelString.equals(e.getActionCommand())) {
                // Must be the process button getting clicked so process things
                System.out.println("Call code with the following values: \n\tMin = " + minString +
                        "\n\tMax = " + maxString +
                        "\n\tPath Length Position = " + pathLenPosString +
                        "\n\tPath ID Position = " + pathIDPosString +
                        "\n\tStart On Position = " + startsOnPosString +
                        "\n\tPrimary Path Position = " + primPathPosString +
                        "\n\tDirectory to use = " + directoryPathString);
                try {
                    nsort.callNsort(directoryPathFile,colValsList.indexOf(pathLenPosString)-1,
                            Integer.parseInt(minString),Integer.parseInt(maxString),colValsList.indexOf(pathIDPosString)-1,
                            colValsList.indexOf(startsOnPosString)-1);
                   // System.out.println(colValsList.indexOf(startsOnPosString));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
            else if (directoryButtonLabelString.equals(e.getActionCommand())) {
                System.out.println("Directory selected: ");
                final JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showOpenDialog(this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    directoryPathString = fc.getSelectedFile().getPath();
                    directoryPathFile = fc.getSelectedFile();
                }
                System.out.println("Return value: " + returnVal);
                System.out.println("Selected file: " + fc.getSelectedFile());
            }
            if(//minList.getSelectedIndex()> 0 &&
                    maxList.getSelectedIndex() > 0 &&
                    pathLenPosList.getSelectedIndex() > 0 &&
                    pathIDPosList.getSelectedIndex() > 0 &&
                    startsOnPosList.getSelectedIndex() > 0 &&
                    primPathPosList.getSelectedIndex() > 0 &&
                    maxList.getSelectedIndex() > minList.getSelectedIndex()&&
                    directoryPathFile!= null) {
                processButton.setEnabled(true);
            }
            else {
                processButton.setEnabled(false);
            }
        }


        /**
         * Create the GUI and show it.  For thread safety,
         * this method should be invoked from the
         * event-dispatching thread.
         */
        private static void createAndShowGUI() {
            //Create and set up the window.
            JFrame frame = new JFrame("SNT Analyzer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
            JComponent newContentPane = new SNTAnalyzerUI();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

            //Display the window.
            frame.pack();
            frame.setVisible(true);

        }
        public static void infoBox(String infoMessage, String titleBar)
            {
            JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
            }

        public static void main(String[] args) {
            //Schedule a job for the event-dispatching thread:
            //creating and showing this application's GUI.
            javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createAndShowGUI();
                }

            });
            //System.out.println("min: "+)
        }
    }

