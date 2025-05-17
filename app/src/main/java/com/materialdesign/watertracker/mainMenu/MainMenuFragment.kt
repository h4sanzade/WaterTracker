package com.materialdesign.watertracker.mainMenu

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.materialdesign.watertracker.PreferenceHelper
import com.materialdesign.watertracker.R
import com.materialdesign.watertracker.databinding.FragmentMainMenuBinding

class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.bottomNavigation.selectedItemId = R.id.nav_home

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_analysis -> {
                    findNavController().navigate(R.id.analysisFragment)
                    true
                }
                else -> false
            }
        }


        val username = PreferenceHelper.getUsername(requireContext())
        if (username == null) {
            showNameInputDialog()
        } else {
            val greetingMessage = GreetingUtil.getGreetingMessage(username)
            val parts = greetingMessage.split(",")
            binding.greetingPrefix.text = parts[0]
            binding.greetingName.text = parts[1].trim()
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

    private fun showNameInputDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_name_input, null)
        val firstNameEditText = dialogView.findViewById<EditText>(R.id.etFirstName)
        val lastNameEditText = dialogView.findViewById<EditText>(R.id.etLastName)
        val saveButton = dialogView.findViewById<Button>(R.id.btnSave)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.attributes?.windowAnimations = R.style.CustomDialogAnimation
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        saveButton.setOnClickListener {
            val firstName = firstNameEditText.text.toString().trim()
            val lastName = lastNameEditText.text.toString().trim()

            if (firstName.isNotEmpty() && lastName.isNotEmpty()) {
                val fullName = "$firstName $lastName"
                PreferenceHelper.saveUsername(requireContext(), fullName)

                Toast.makeText(requireContext(), "Name saved!", Toast.LENGTH_SHORT).show()
                val greetingMessage = GreetingUtil.getGreetingMessage(fullName)
                val parts = greetingMessage.split(",")
                binding.greetingPrefix.text = parts[0]
                binding.greetingName.text = parts[1].trim()
                dialog.dismiss()
            } else {
                Toast.makeText(requireContext(), "Please enter both first and last name", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
