package com.example.advancenavigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.advancenavigation.databinding.FragmentSummaryBinding
import com.example.advancenavigation.viewmodel.OrderViewModel

class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null

    //get reference to shared view model
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var fragmentBinding = FragmentSummaryBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            //gets the view model from the fragment_flavor.xml and bind it to the sharedViewModel [OrderVM]
            viewModel = sharedViewModel
            //sets the lifecycleOwner to observe LiveData objects
            lifecycleOwner = viewLifecycleOwner
            //bind fragment data variable with the fragment instance
            summaryFragment = this@SummaryFragment
        }
    }

    fun sendOrder() {
        Toast.makeText(activity, "Send Order", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}