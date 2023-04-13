 //
//  MainMenuViewController.swift
//  Code Cracker
//
//  Created by Linus Bendel-Stenzel on 6/16/21.
//

import UIKit

class MainMenuViewController: UIViewController {

    var game = Game()
    let id:String = "MainMenu"
    
    
    override func viewDidLoad() {
        overrideUserInterfaceStyle = .light
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    @IBAction func normalGameMode(_ sender: Any) {
        game.normalMode()
    }
    @IBAction func hardGameMode(_ sender: Any) {
        game.hardMode()
    }
    @IBAction func expertGameMode(_ sender: Any) {
        game.expertMode()
    }
    @IBAction func brainiacGameMode(_ sender: Any) {
        game.brainiacMode()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let gamePlayVC = segue.destination as? ViewController
        gamePlayVC?.game = game
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
