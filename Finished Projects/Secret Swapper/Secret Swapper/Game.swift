//
//  Game.swift
//  Code Cracker
//
//  Created by Linus Bendel-Stenzel on 6/15/21.
//

import Foundation

class Game {
    
    private var mobius:Bool = false
    private var brainiac:Bool = false
    private var length:Int = 6
    private var repeats:Int = 1
    private var guesses:Int = 8
    private var reveals:[Int] = [3, 7]
    private var secret:String = ""
    private var revealedPositions:[Character] = []
    
    
    public func didWin(input:String) -> Bool {
        return input == secret
    }
    
    public func createPassword(){
        secret = ""
        // Creates an array of every possible number
        var tmp:Int = 0
        var randomLocation:Int = 0
        var tempPass:[Int] = []
        for index in 1...length {
            for _ in 1...repeats {
                tempPass.append(index)
            }
        }
        // Randomizes the array
        for index in 0...(tempPass.count * 2 - 1) {
            randomLocation = Int.random(in: 0...tempPass.count - 1)
            
            // Swaps positions to randomize the value.
            tmp = tempPass[index % length]
            tempPass[index % length] = tempPass[randomLocation]
            tempPass[randomLocation] = tmp
        }
        
        // Takes first LENGTH items and makes that the secret code
        for index in 0...(length - 1) {
            secret += String(tempPass[index])
        }
        // print(secret)
    }
    
    public func calculateScore(input:String) -> Int {
        var inputAsArray:[Character] = []
        for character in input {
            inputAsArray.append(character)
        }
        var counter = 0
        var score = 0
        
        
        if (mobius){
            counter = 2
            var tmp:[Character] = [inputAsArray[length - 2], inputAsArray[length - 1]]
            tmp += inputAsArray
            tmp.append(inputAsArray[0])
            tmp.append(inputAsArray[1])
            inputAsArray = tmp
            
            // Calculate Mobius Score
            for character in secret {
                // Spot on
                if (character == inputAsArray[counter]){
                    score += 4
                }
                // Two Away
                if (character == inputAsArray[counter - 2]){
                    score += 1
                }
                if (character == inputAsArray[counter + 2]){
                    score += 1
                }
                // One away
                if (character == inputAsArray[counter - 1]){
                    score += 2
                }
                if (character == inputAsArray[counter + 1]){
                    score += 2
                }
                
                counter += 1
            }
        } else {
            // Calculate Score non-mobius
            for character in secret {
                // Spot on
                if (character == inputAsArray[counter]){
                    score += 4
                }
                // Two Away
                if (counter - 2 > -1 && character == inputAsArray[counter - 2]){
                    score += 1
                }
                if (counter + 2 < length && character == inputAsArray[counter + 2]){
                    score += 1
                }
                // One away
                if (counter - 1 > -1 && character == inputAsArray[counter - 1]){
                    score += 2
                }
                if (counter + 1 < length && character == inputAsArray[counter + 1]){
                    score += 2
                }
                
                counter += 1
            }
        }
        //print(score)
        return score
    }
    
    public func revealPosition(position:Int) -> String {
        var counter:Int = 1
        for character in secret {
            if (position == counter) {
                return String(character)
            }
            counter += 1
        }
        return ""
    }
    
    public func reset(){
        createPassword()
        revealedPositions = []
        for _ in 0...length - 1 {
            revealedPositions.append("0")
        }
    }
    
    // Gamemode Options
    public func normalMode(){
        mobius = false
        brainiac = false
        length = 6
        repeats = 1
        guesses = 8
        reveals = [3, 5]
    }
    public func hardMode(){
        mobius = false
        brainiac = false
        length = 6
        repeats = 2
        guesses = 6
        reveals = [3, 5]
    }
    public func expertMode(){
        mobius = false
        brainiac = false
        length = 7
        repeats = 2
        guesses = 6
        reveals = [4]
    }
    public func brainiacMode(){
        mobius = false
        brainiac = true
        length = 6
        repeats = 1
        guesses = 6
        reveals = []
    }
    public func customMode(IMobius:Bool, IBrainiac:Bool, ILength:Int, IRepeats:Int, IGuesses:Int, IReveals:[Int]){
        mobius = IMobius
        brainiac = IBrainiac
        length = ILength
        repeats = IRepeats
        guesses = IGuesses
        reveals = IReveals
    }
    
    // Brainiac Methods
    
    public func createRandomGuess() -> String {
        var output:String = ""
        var tmp:Int = 0
        var randomLocation:Int = 0
        var tempPass:[Int] = []
        for index in 1...length {
            tempPass.append(index)
        }
        // Randomizes the array
        for index in 0...(tempPass.count * 2 - 1) {
            randomLocation = Int.random(in: 0...tempPass.count - 1)
            
            // Swaps positions to randomize the value.
            tmp = tempPass[index % length]
            tempPass[index % length] = tempPass[randomLocation]
            tempPass[randomLocation] = tmp
        }
        
        for index in 0...(length - 1) {
            output += String(tempPass[index])
        }
        return output
    }
    
    
    
    
    // Get and Set Functions
    public func getLength() -> Int{
        return length
    }
    
    public func getSecret() -> String{
        return secret
    }
    
    public func getGuesses() -> Int{
        return guesses
    }
    
    public func getReveals() -> [Int]{
        return reveals
    }
    
    public func getPosition(input:Int) -> Character{
        var x = 1
        for character in secret{
            if (x == input) {
                return character
            }
            x += 1
        }
        return "0"
    }
    
    public func getRevealedPositions() -> [Character]{
        return revealedPositions
    }
    
    public func setRevealedPositions(input:[Character]){
        revealedPositions = input
    }
    
    public func setMobius(input:Bool){
        mobius = input
    }
    
    public func getMobius() -> Bool {
        return mobius
    }
    
    public func setBrainiac(input:Bool){
        brainiac = input
    }
    
    public func getBrainiac() -> Bool {
        return brainiac
    }
    
    
}
