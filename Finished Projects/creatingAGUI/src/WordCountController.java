import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Controller for the WordCount GUI.
 */
public class WordCountController implements ActionListener{

    // model contains the functions necessary
    private final WordCount model;
    // view is the gui
    private final WordCountGui view;

    /**
     * Constructor which instantiates the gui and model. 
     */
    public WordCountController(){
        this.view = new WordCountGui(this);
        this.model = new WordCount();
        view.setVisible(true);

    }

    /**
     * Reads the file, cleans the data, creates the map, then outputs the file. 
     * @param filename name of the input file
     */
    private void actOnFile(String filename){
        System.out.println(filename);
        model.readFile(filename);
        model.cleanData();
        model.createMap();
        model.printToFile("output-" + filename);
    }

    /**
     * Looks for a button press. 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if( ae.getActionCommand().equals("Select File")) {
            // Grab the file
            JFileChooser j = new JFileChooser(System.getProperty("user.dir") + "/input/");
            j.setAcceptAllFileFilterUsed(false);
            // Ask the question
            j.setDialogTitle("Select a .txt file");
            // only allow files of .txt extension
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
            j.addChoosableFileFilter(restrict);
            // Shows the dialog box, and returns an enum with the action performed
            int r = j.showOpenDialog(null);
            // if the user selects a file
            if (r == JFileChooser.APPROVE_OPTION){
                // Get the name of the file because that's what my code accepts as input
                String filename = j.getSelectedFile().getName();
                actOnFile(filename);
                // Changing the label to show the user what happened
                this.view.confirmation.setText("Saved as output-" + filename);

                //System.out.println("Approve option");
            } else{
                // This code happens if the action was canceled. 
                //System.out.println("Canceled");
            }
        }
    }
    
}
