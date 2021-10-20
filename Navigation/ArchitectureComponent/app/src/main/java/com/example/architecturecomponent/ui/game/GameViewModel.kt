package com.example.architecturecomponent.ui.game


import androidx.lifecycle.ViewModel
import com.example.architecturecomponent.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameViewModel : ViewModel() {
    private var _currWordCount = 0
    val currWordCount : Int
        get() = _currWordCount
    private var _currScrambledWord = "test"
    val currentScrambledWord : String
        get() = _currScrambledWord
    private var _score = 0
    val score: Int
        get() = _score
    //stores the words fetched from allWordsList
    private var wordList : MutableList<String> = mutableListOf()
    private lateinit var currWord: String

    init{
        getNextWord()
    }

    //fetch data from ListofWords.kt and updates the current word and shuffled word
    private fun getNextWord(){
        currWord = allWordsList.random()
        val tempWord = currWord.toCharArray()
        tempWord.shuffle()
        //checks if the current word fetched is in the wordlist
        if(wordList.contains(currWord)){
            getNextWord()
        } else {
            //keeps shuffling until the word shuffled is not equals to the current word fetched
            while(tempWord.toString().equals(currWord, false)){
                tempWord.shuffle()
            }

            _currScrambledWord = String(tempWord)
            ++_currWordCount
            wordList.add(currWord)
        }

    }
    //Accessed in game fragment and returns true if the current word count is less than the max no of words
    //updates the next word
    fun nextWord(): Boolean{
        return if(currWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

    fun isUserInputWordCorrect(playerWord : String) : Boolean{
        if(playerWord.equals(currWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun reinitialiseData() {
        _score = 0
        _currWordCount = 0
        wordList.clear()
        getNextWord()
    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }
}