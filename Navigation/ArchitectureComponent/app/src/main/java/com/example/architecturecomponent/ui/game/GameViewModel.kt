package com.example.architecturecomponent.ui.game

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var score = 0
    private var currWordCount = 0
    private var _currScrambledWord = "test"
    val currentScrambledWord : String
        get() = _currScrambledWord
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
        //checks if the current word fetched is in the wordlist
        if(wordList.contains(currWord)){
            getNextWord()
        } else {
            _currScrambledWord = String(tempWord)
            ++currWordCount
            wordList.add(currWord)
        }
        //keeps shuffling until the word shuffled is not equals to the current word fetched
        while(tempWord.toString().equals(currWord, false)){
            tempWord.shuffle()
        }
    }
    //Returns true if the current word count is less than the max no of words
    //updates the next word
    fun nextWord(): Boolean{
        return if(currWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

}