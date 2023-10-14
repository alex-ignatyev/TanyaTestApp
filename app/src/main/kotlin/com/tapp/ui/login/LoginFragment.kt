package com.tapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tapp.MainViewModel
import com.tapp.R
import com.tapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.signin.isEnabled = !isLoading
            binding.signin.isClickable = !isLoading
            binding.loader.isVisible = isLoading
        }
        mainViewModel.error.observe(viewLifecycleOwner) { errorValue ->
            binding.error.text = errorValue
        }

        binding.signin.setOnClickListener {
            mainViewModel.login(
                binding.login.text.toString(),
                binding.password.text.toString()
            )
        }

        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_productsFragment)
        }
        return binding.root
    }
}