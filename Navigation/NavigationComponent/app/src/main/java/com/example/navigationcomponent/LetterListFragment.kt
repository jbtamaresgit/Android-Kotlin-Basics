package com.example.navigationcomponent

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationcomponent.Adapter.LetterAdapter
import com.example.navigationcomponent.databinding.FragmentLetterListBinding

class LetterListFragment : Fragment() {
    private var isLinearLayout = true
    //binding is null due to inflation of the layout until onCreateView is called
    private var _binding: FragmentLetterListBinding? = null
    //append !! if the value won't be null when accessed
    private val binding get () = _binding!!
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLetterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.recyclerView
        chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.layout_menu, menu)
        val layoutButton = menu.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)
    }

    private fun chooseLayout(){
        if(isLinearLayout){
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }
        else{
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 4)
        }

        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?){
        if(menuItem == null)
            return
        menuItem.icon = if(isLinearLayout)
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_linear_layout)
        else
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_grid_layout)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_switch_layout -> {
                isLinearLayout = !isLinearLayout
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}