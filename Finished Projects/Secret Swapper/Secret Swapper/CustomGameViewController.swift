//
//  CustomGameViewController.swift
//  Code Cracker
//
//  Created by Linus Bendel-Stenzel on 6/16/21.
//

import UIKit

class CustomGameViewController: UIViewController, UITextFieldDelegate {
    
    var game = Game()
    let id:String = "CustomGame"
    
    @IBOutlet weak var numberOfGuesses: UITextField!
    
    @IBOutlet weak var revealAfter1: UITextField!
    @IBOutlet weak var revealAfter2: UITextField!
    @IBOutlet weak var numberOfRepeats: UITextField!
    @IBOutlet weak var lengthOfCode: UITextField!
    
    @IBOutlet weak var mobiusModeSwitch: UISwitch!
    @IBOutlet weak var brainiacModeSwitch: UISwitch!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        overrideUserInterfaceStyle = .light

        // Do any additional setup after loading the view.
    }
    
    @IBAction func playGame(_ sender: Any) {
        performSegue(withIdentifier: "goToGamePlay", sender: self)
    }
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        revealAfter2.resignFirstResponder()
        revealAfter1.resignFirstResponder()
        numberOfGuesses.resignFirstResponder()
        numberOfRepeats.resignFirstResponder()
        lengthOfCode.resignFirstResponder()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let gamePlayVC = segue.destination as? ViewController
        gamePlayVC?.game = game
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String, sender: Any?) -> Bool {
        if (identifier == "goToGamePlay"){
            let tmpNumOfGuesses:Int = Int(numberOfGuesses.text!) ?? 0
            let tmpReveals:[Int] = [Int(revealAfter1.text!) ?? -1,
                                    Int(revealAfter2.text!) ?? -1]
            let tmpNumOfRepeats:Int = Int(numberOfRepeats.text!) ?? 0
            let tmpLengthOfCode:Int = Int(lengthOfCode.text!) ?? 0
            
            if (tmpNumOfGuesses > 0 && tmpNumOfGuesses < 9){
                if (tmpNumOfRepeats > 0 && tmpNumOfRepeats < 10){
                    if (tmpLengthOfCode > 0 && tmpLengthOfCode < 10){
                        game.customMode(IMobius: mobiusModeSwitch.isOn, IBrainiac: brainiacModeSwitch.isOn, ILength: tmpLengthOfCode, IRepeats: tmpNumOfRepeats, IGuesses: tmpNumOfGuesses, IReveals: tmpReveals)
                        return true
                    }
                }
            }
            return false
        } else {
            return true
        }
    }
    
    
    
    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
