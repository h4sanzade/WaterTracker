package com.materialdesign.watertracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.materialdesign.watertracker.databinding.FragmentEntranceSliderOneBinding

class EntranceSliderOneFragment : Fragment() {

    private var _binding: FragmentEntranceSliderOneBinding? = null
    private val binding get() = _binding!!
    private var currentPage = 0 // 0, 1, 2 değerlerini alabilir

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEntranceSliderOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Başlangıçta ilk nokta aktif olsun
        updateIndicators(currentPage)

        binding.nextButton.setOnClickListener {
            currentPage++
            if (currentPage > 2) {
                // Son sayfada Next'e basıldığında yapılacak işlem
                // Örneğin ana ekrana geçiş
                // findNavController().navigate(R.id.action_to_home)
                currentPage = 0 // Veya döngü yapmak istemiyorsanız bu satırı silin
            }
            updateIndicators(currentPage)
        }
    }

    private fun updateIndicators(position: Int) {
        // Tüm noktaları pasif yap
        binding.dot1.setImageResource(R.drawable.dot_unselected)
        binding.dot2.setImageResource(R.drawable.dot_unselected)
        binding.dot3.setImageResource(R.drawable.dot_unselected)

        // Seçili pozisyondaki noktayı aktif yap
        when (position) {
            0 -> {
                binding.dot1.setImageResource(R.drawable.dot_selected)
                // İlk sayfa içeriğini güncelle
                binding.mainText.text = getString(R.string.track_your_daily_water_intake_with_us)
                binding.subText.text = getString(R.string.achieve_your_hydration_goals_with_a_simple_tap)
                binding.drinkWaterImg.setImageResource(R.drawable.drinkwater_img)
            }
            1 -> {
//                binding.dot2.setImageResource(R.drawable.dot_selected)
//                // İkinci sayfa içeriğini güncelle
//                binding.mainText.text = getString(R.string.set_your_daily_water_goal)
//                binding.subText.text = getString(R.string.personalize_your_hydration_target)
//                binding.drinkWaterImg.setImageResource(R.drawable.imgWaterGoal)
            }
            2 -> {
//                binding.dot3.setImageResource(R.drawable.dot_selected)
                // Üçüncü sayfa içeriğini güncelle
//                binding.mainText.text = getString(R.string.get_reminders)
//                binding.subText.text = getString(R.string.never_forget_to_drink_water)
//                binding.drinkWaterImg.setImageResource(R.drawable.imgReminder)
//                binding.nextButton.text = getString(R.string.get_started)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}