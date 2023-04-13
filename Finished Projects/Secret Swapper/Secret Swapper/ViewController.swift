//
//  ViewController.swift
//  Code Cracker
//
//  Created by Linus Bendel-Stenzel on 6/12/21.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var instruction: UILabel!
    
    // Game Play
    @IBOutlet weak var textEntry: UITextField!
    @IBOutlet weak var revealedLetters: UILabel!
    @IBOutlet weak var guess1: UILabel!
    @IBOutlet weak var guess2: UILabel!
    @IBOutlet weak var guess3: UILabel!
    @IBOutlet weak var guess4: UILabel!
    @IBOutlet weak var guess5: UILabel!
    @IBOutlet weak var guess6: UILabel!
    @IBOutlet weak var guess7: UILabel!
    
    @IBOutlet weak var score1: UILabel!
    @IBOutlet weak var score2: UILabel!
    @IBOutlet weak var score3: UILabel!
    @IBOutlet weak var score4: UILabel!
    @IBOutlet weak var score5: UILabel!
    @IBOutlet weak var score6: UILabel!
    @IBOutlet weak var score7: UILabel!
    
    @IBOutlet weak var guess1bg: UIImageView!
    @IBOutlet weak var guess2bg: UIImageView!
    @IBOutlet weak var guess3bg: UIImageView!
    @IBOutlet weak var guess4bg: UIImageView!
    @IBOutlet weak var guess5bg: UIImageView!
    @IBOutlet weak var guess6bg: UIImageView!
    @IBOutlet weak var guess7bg: UIImageView!
    
    @IBOutlet weak var score1bg: UIImageView!
    @IBOutlet weak var score2bg: UIImageView!
    @IBOutlet weak var score3bg: UIImageView!
    @IBOutlet weak var score4bg: UIImageView!
    @IBOutlet weak var score5bg: UIImageView!
    @IBOutlet weak var score6bg: UIImageView!
    @IBOutlet weak var score7bg: UIImageView!
    
    
    
    
    // Main Menu
    
    
    
    var guesses:[UILabel] = []
    var scores:[UILabel] = []
    var guessesbg:[UIImageView] = []
    var scoresbg:[UIImageView] = []
    
    var justRevealed:Bool = false
    var game = Game()
    let id:String = "GamePlay"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        overrideUserInterfaceStyle = .light
        
        textEntry.delegate = self
        
        guesses.append(guess1)
        guesses.append(guess2)
        guesses.append(guess3)
        guesses.append(guess4)
        guesses.append(guess5)
        guesses.append(guess6)
        guesses.append(guess7)
        
        scores.append(score1)
        scores.append(score2)
        scores.append(score3)
        scores.append(score4)
        scores.append(score5)
        scores.append(score6)
        scores.append(score7)
        
        guessesbg.append(guess1bg)
        guessesbg.append(guess2bg)
        guessesbg.append(guess3bg)
        guessesbg.append(guess4bg)
        guessesbg.append(guess5bg)
        guessesbg.append(guess6bg)
        guessesbg.append(guess7bg)
        
        scoresbg.append(score1bg)
        scoresbg.append(score2bg)
        scoresbg.append(score3bg)
        scoresbg.append(score4bg)
        scoresbg.append(score5bg)
        scoresbg.append(score6bg)
        scoresbg.append(score7bg)
        
        
        reset()
        
        
        // Do any additional setup after 6oading the view.
    }
    
    func play_brainiac(){
        var tmpGuess:String = ""
        var tmpScore:Int = 0
        for num in 1...game.getGuesses(){
            tmpGuess = game.createRandomGuess()
            tmpScore = game.calculateScore(input: tmpGuess)
            if (num - 1 < 7){
                guesses[num - 1].text = tmpGuess
                scores[num - 1].text = String(tmpScore)
            }
        }
        instruction.text = "Final Guess: "
    }
    
    func reset() {
        revealedLetters.text = ""
        for tmp in guesses {
            tmp.text = ""
        }
        for tmp in scores {
            tmp.text = ""
        }
        instruction.text = "Guess #1"
        textEntry.text = ""
        
        game.reset()
        revealedLetters.text = "Known: \t"
        revealedLetters.text! += String(game.getRevealedPositions())
        
        if (game.getBrainiac()){
            play_brainiac()
        }
        
        var count = 0
        for item in guesses{
            if (item.text == ""){
                guessesbg[count].isHidden = true
                scoresbg[count].isHidden = true
            } else {
                guessesbg[count].isHidden = false
                scoresbg[count].isHidden = false
            }
            count += 1
        }
        
    }
    

    @IBAction func submitButton(_ sender: Any) {
        
        var x = 7
        for item in guesses{
            if (item.text == ""){
                x -= 1
            }
        }
        if (game.getBrainiac()){
            if (game.didWin(input: textEntry.text ?? "")){
                instruction.text = "Win!"
                textEntry.isUserInteractionEnabled = false;
            } else {
                var tempText = "Lose! "
                tempText += String(game.getSecret())
                instruction.text = tempText
                textEntry.isUserInteractionEnabled = false;
            }
        } else {
        // Checks if they win
            if (game.didWin(input: textEntry.text ?? "")){
                instruction.text = "Win!"
                textEntry.isUserInteractionEnabled = false;
            } else if (textEntry.text?.count == game.getLength() && instruction.text != "Reveal a Position"){
                justRevealed = false
                
                // Checks for valid input
                
                if (x < game.getGuesses() - 1){
                    let score:Int = game.calculateScore(input: textEntry.text ?? "")
                    guesses[x].text = textEntry.text ?? ""
                    scores[x].text = String(score)
                    
                    // Dealing with what should show at top
                    textEntry.text = ""
                    instruction.text = "Guess #\(x + 2)"
                    for index in game.getReveals(){
                        if (x + 1 == index){
                            instruction.text = "Reveal a Position"
                        }
                    }
                } else {
                    
                    // Checks if they used all their guesses
                    var tempText = "Lose! "
                    tempText += String(game.getSecret())
                    instruction.text = tempText
                    textEntry.isUserInteractionEnabled = false;
                }

            // if not a valid input, check if valid input for reveal
            } else if (textEntry.text?.count == 1 && !justRevealed){
                let position:Int = Int(textEntry.text ?? "") ?? 0
                if (position > 0 && position < game.getLength() + 1){
                    for index in game.getReveals() {
                        if (index == x){
                            var rev = game.getRevealedPositions()
                            rev[position - 1] = game.getPosition(input: position)
                            game.setRevealedPositions(input: rev)
                            revealedLetters.text = "Known: \t"
                            revealedLetters.text! += String(rev)
                        }
                    }
                    justRevealed = true
                    textEntry.text = ""
                    instruction.text = "Guess #\(x + 1)"
                }
            }
        }
        var count = 0
        for item in guesses{
            if (item.text == ""){
                guessesbg[count].isHidden = true
                scoresbg[count].isHidden = true
            } else {
                guessesbg[count].isHidden = false
                scoresbg[count].isHidden = false
            }
            count += 1
        }
    }
    
    
    
    
    @IBAction func restart(_ sender: Any) {
        textEntry.isUserInteractionEnabled = true
        reset()
    }

    
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent?) {
        textEntry.resignFirstResponder()
    }
}

extension ViewController : UITextFieldDelegate {
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
}

