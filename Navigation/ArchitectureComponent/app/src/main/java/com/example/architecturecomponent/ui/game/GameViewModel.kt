package com.example.architecturecomponent.ui.game


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturecomponent.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameViewModel : ViewModel() {
    private var _currWordCount = MutableLiveData(0)
    val currWordCount : LiveData<Int>
        get() = _currWordCount
    private var _currScrambledWord = MutableLiveData<String>()
    val currentScrambledWord : LiveData<String>
        get() = _currScrambledWord
    private var _score = MutableLiveData(0)
    val score: LiveData<Int>
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

            _currScrambledWord.value = String(tempWord)
            //null-safety live data increment
            _currWordCount.value = (_currWordCount.value)?.inc()
            wordList.add(currWord)
        }

    }
    //Accessed in game fragment and returns true if the current word count is less than the max no of words
    //updates the next word
    fun nextWord(): Boolean{
        return if(_currWordCount.value!! < MAX_NO_OF_WORDS){
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
        _score.value = 0
        _currWordCount.value = 0
        wordList.clear()
        getNextWord()
    }

    private fun increaseScore(){
        _score.value = (_score.value)?.plus(SCORE_INCREASE)
    }
}