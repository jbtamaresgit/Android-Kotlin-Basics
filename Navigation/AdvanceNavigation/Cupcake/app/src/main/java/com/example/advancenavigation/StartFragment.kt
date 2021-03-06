package com.example.advancenavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.advancenavigation.databinding.FragmentStartBinding
import com.example.advancenavigation.viewmodel.OrderViewModel

class StartFragment : Fragment() {

    private var binding : FragmentStartBinding? = null

    //get reference to shared view model
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    fun orderCupcake(quantity: Int){

        sharedViewModel.setQuantity(quantity)
        //set up default flavour if no flavour is set
        if(sharedViewModel.hasNoFlavourSet()){
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }

        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}