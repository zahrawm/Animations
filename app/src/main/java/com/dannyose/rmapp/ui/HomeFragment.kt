package com.dannyose.rmapp.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.dannyose.rmapp.R
import com.dannyose.rmapp.databinding.FragmentHomeBinding
import com.dannyose.rmapp.ui.adapter.CharactersAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel:HomeViewModel by viewModels {
        HomeViewModel.HomeViewModelFactory(
            requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CharactersAdapter(requireContext())

        binding.rvCharacters.adapter = adapter
        binding.progressBar.isVisible = true

        homeViewModel.characterList.observe(viewLifecycleOwner, Observer { response ->
            adapter.submitList(response.characters)
            binding.progressBar.isVisible = false
        })
    }
}