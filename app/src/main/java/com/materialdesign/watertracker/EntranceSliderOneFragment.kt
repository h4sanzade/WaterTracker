package com.materialdesign.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.materialdesign.watertracker.databinding.FragmentEntranceSliderOneBinding

class EntranceSliderOneFragment : Fragment() {

    private var _binding: FragmentEntranceSliderOneBinding? = null


    private val binding get() = _binding!!
    private var currentPage = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntranceSliderOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        updateIndicators(currentPage)

        binding.nextButton.setOnClickListener {
            currentPage++
            if (currentPage == 1) {
                findNavController().navigate(R.id.action_entranceSliderOneFragment_to_entranceSliderTwoFragment)
            }
            updateIndicators(currentPage)
        }
    }

    private fun updateIndicators(position: Int) {
        binding.dot1.setImageResource(R.drawable.dot_unselected)
        binding.dot2.setImageResource(R.drawable.dot_unselected)
        binding.dot3.setImageResource(R.drawable.dot_unselected)

        when (position) {
            0 -> {
                binding.dot1.setImageResource(R.drawable.dot_selected)
                binding.mainText.text = getString(R.string.track_your_daily_water_intake_with_us)
                binding.subText.text = getString(R.string.achieve_your_hydration_goals_with_a_simple_tap)
                binding.drinkWaterImg.setImageResource(R.drawable.drinkwater_img)
            }
            1 -> {
                binding.dot2.setImageResource(R.drawable.dot_selected)
                binding.mainText.text = getString(R.string.smart_reminders_tailored_to_you)
                binding.subText.text = getString(R.string.quick_and_easy_to_set_your_hydration_goal_and_then_track_your_daily_water_intake_progress)
                binding.drinkWaterImg.setImageResource(R.drawable.drinkwater_slide2_img)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
