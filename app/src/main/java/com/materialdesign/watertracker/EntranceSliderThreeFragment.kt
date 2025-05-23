package com.materialdesign.watertracker

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
import com.materialdesign.watertracker.databinding.FragmentEntranceSliderThreeBinding
import com.materialdesign.watertracker.mainMenu.GreetingUtil

class EntranceSliderThreeFragment : Fragment() {

    private var _binding: FragmentEntranceSliderThreeBinding? = null
    private val binding get() = _binding!!

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEntranceSliderThreeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get Started button click
        binding.nextButton.setOnClickListener {
            showNameInputDialog()
        }
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
                Toast.makeText(requireContext(), greetingMessage, Toast.LENGTH_SHORT).show()


                findNavController().navigate(R.id.action_entranceSliderThreeFragment_to_mainMenuFragment)
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

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EntranceSliderThreeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}