package com.example.architecturecomponent.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturecomponent.R
import com.example.architecturecomponent.databinding.GameFragmentBinding

class GameFragment : Fragment() {
    private val viewModel : GameViewModel by viewModels()
    private lateinit var binding : GameFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = GameFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submit.setOnClickListener { onSubmitWord() }
        binding.skip.setOnClickListener{ onSkipWord() }
        updateNextWordOnScreen()
        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(R.string.word_count, 0, MAX_NO_OF_WORDS)
    }

    private fun onSubmitWord(){
        currScrambledWord = getNextScrambledWord()
        currWordCount++
        score += SCORE_INCREASE
        binding.wordCount.text = getString(R.string.word_count, currWordCount, MAX_NO_OF_WORDS)
        binding.score.text = getString(R.string.score, score)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun onSkipWord(){
        currScrambledWord = getNextScrambledWord()
        currWordCount++
        binding.wordCount.text = getString(R.string.word_count, currWordCount, MAX_NO_OF_WORDS)
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun getNextScrambledWord() : String {
        val tempWord = allWordsList.random().toCharArray()
        tempWord.shuffle()
        return String(tempWord)
    }

    private fun restartGame(){
        setErrorTextField(false)
        updateNextWordOnScreen()
    }

    private fun exitGame(){
        activity?.finish()
    }

    private fun setErrorTextField(error: Boolean) {
        if (error) {
            binding.textField.isErrorEnabled = true
            binding.textField.error = getString(R.string.try_again)
        } else {
            binding.textField.isErrorEnabled = false
            binding.textInputEditText.text = null
        }
    }

    private fun updateNextWordOnScreen(){
        binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord
    }


}