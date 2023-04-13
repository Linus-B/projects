import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Linus Bendel-Stenzel
 * Creates the GUI for the WordCount class
 */
public class WordCountGui extends JFrame {

    // The button
    private final JButton selectFile = new JButton( "Select File");
    // The Explanation lavel
    final JLabel confirmation = new JLabel("Please select a file.");

    /**
     * Instantiator which creates all the gui elements and where to position them. 
     * @param control a Controller which impplements ActionListener
     */
    public WordCountGui(WordCountController control){
        // Creates title, and makes it stop program when closing. 
        super( "WordFrequency");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        this.setResizable( false);

        // Window size
        this.setSize( 300, 200);
        this.getContentPane().setLayout( null);

        // Label position and size
        this.confirmation.setBounds( 20, 20, 240, 30);
        this.getContentPane().add( this.confirmation);

        // Add button with action listener
        this.selectFile.setBounds( 20, 70, 120, 30);
        this.selectFile.addActionListener( control);
        this.getContentPane().add( this.selectFile);


    }
    
}