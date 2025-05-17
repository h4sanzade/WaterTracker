package com.materialdesign.watertracker

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.materialdesign.watertracker.databinding.FragmentAnalysisBinding

class AnalysisFragment : Fragment() {

    private var _binding: FragmentAnalysisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottomNavigation.selectedItemId = R.id.nav_analysis

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    findNavController().navigate(R.id.mainMenuFragment)
                    true
                }
                else -> false
            }
        }


        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )
        val iconColors = intArrayOf(
            Color.parseColor("#08ABFF"),
            Color.parseColor("#808080")
        )
        val colorStateList = ColorStateList(states, iconColors)
        binding.bottomNavigation.itemIconTintList = colorStateList

        binding.bottomNavigation.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#808080")))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
