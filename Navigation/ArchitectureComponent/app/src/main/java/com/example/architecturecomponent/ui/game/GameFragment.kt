package com.example.architecturecomponent.ui.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.architecturecomponent.R
import com.example.architecturecomponent.databinding.GameFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class GameFragment : Fragment() {
    private val viewModel: GameViewModel by viewModels()
    private lateinit var binding: GameFragmentBinding

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
        binding.skip.setOnClickListener { onSkipWord() }
        binding.score.text = getString(R.string.score, 0)
        binding.wordCount.text = getString(R.string.word_count, 0, MAX_NO_OF_WORDS)
        //creates an observer for livedata -- usually created at onViewCreated
        viewModel.currentScrambledWord.observe(viewLifecycleOwner,{
            newWord -> binding.textViewUnscrambledWord.text = newWord
        })
        viewModel.currWordCount.observe(viewLifecycleOwner,{
            newWordCount -> binding.wordCount.text = getString(R.string.word_count, newWordCount, MAX_NO_OF_WORDS)
        })
        viewModel.score.observe(viewLifecycleOwner,{
            newScore -> binding.score.text = getString(R.string.score, newScore)
        })
    }

    private fun onSubmitWord() {
        //fetch the player input from the view
        val playerWord = binding.textInputEditText.text.toString()
        //use view model to check the user input
        if(viewModel.isUserInputWordCorrect(playerWord)) {
            setErrorTextField(false)
            if(!viewModel.nextWord()) {
                showFinalScoreDialog()
            }
        } else {
            setErrorTextField(true)
        }
    }

    private fun onSkipWord() {
        if(viewModel.nextWord()){
            setErrorTextField(false)
        } else {
            showFinalScoreDialog()
        }
    }

    private fun restartGame() {
        viewModel.reinitialiseData()
        setErrorTextField(false)
    }

    private fun exitGame() {
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

    //removed due to the livedata implementation
    /*private fun updateNextWordOnScreen() {
        binding.textViewUnscrambledWord.text = viewModel.currentScrambledWord
    }*/

    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.congratulations))
            .setMessage(getString(R.string.you_scored, viewModel.score.value))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->
                exitGame()
            }
            .setPositiveButton(getString(R.string.play_again)) { _, _ ->
                restartGame()
            }
            .show()
    }


}