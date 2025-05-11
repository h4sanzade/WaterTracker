package com.materialdesign.watertracker

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.materialdesign.watertracker.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {

    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding get() = _binding!!
    private val animationDuration = 100L
    private val delayBetweenDots = 50L
    private val splashDelay = 5000L // 5 saniye
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dots = listOf(
            binding.dot1, binding.dot2, binding.dot3, binding.dot4, binding.dot5
        )

        startSequentialAnimation(dots, 0)

        // 5 saniye sonra diğer fragment'e geçiş yap
        handler.postDelayed({
            navigateToNextFragment()
        }, splashDelay)
    }

    private fun navigateToNextFragment() {
        if (isAdded && !isDetached()) {  // Fragment hala ekli mi kontrol et
            // Navigation Component ile diğer fragment'e geçiş
            findNavController().navigate(R.id.action_welcomeScreenFragment_to_entranceSliderOneFragment)
        }
    }

    private fun startSequentialAnimation(dots: List<View>, index: Int) {
        if (index >= dots.size) {
            startSequentialAnimation(dots, 0)
            return
        }

        val dot = dots[index]

        val downAnim = TranslateAnimation(0f, 0f, 0f, -25f).apply {
            duration = animationDuration
            setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                override fun onAnimationStart(animation: android.view.animation.Animation?) {}

                override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                    val upAnim = TranslateAnimation(0f, 0f, -25f, 0f).apply {
                        duration = animationDuration
                        setAnimationListener(object : android.view.animation.Animation.AnimationListener {
                            override fun onAnimationStart(animation: android.view.animation.Animation?) {}

                            override fun onAnimationEnd(animation: android.view.animation.Animation?) {
                                dot.postDelayed({
                                    startSequentialAnimation(dots, index + 1)
                                }, delayBetweenDots)
                            }

                            override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
                        })
                    }
                    dot.startAnimation(upAnim)
                }

                override fun onAnimationRepeat(animation: android.view.animation.Animation?) {}
            })
        }

        dot.startAnimation(downAnim)
    }

    override fun onDestroyView() {
        // Handler mesajlarını temizle ve animasyonları durdur
        handler.removeCallbacksAndMessages(null)
        listOf(binding.dot1, binding.dot2, binding.dot3, binding.dot4, binding.dot5).forEach {
            it.clearAnimation()
        }
        super.onDestroyView()
        _binding = null
    }
}