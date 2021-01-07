package com.omralcorut.githubsearcher.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.omralcorut.githubsearcher.databinding.FragmentSearchBinding
import com.omralcorut.githubsearcher.models.Repository
import com.omralcorut.githubsearcher.models.User
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!

    private lateinit var searchEpoxyController: SearchEpoxyController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        setAdapter()
        viewModelObserver()
        return binding.root
    }

    private fun setAdapter() {
        searchEpoxyController =
            SearchEpoxyController(object : SearchEpoxyController.SearchEpoxyAdapterCallbacks {
                override fun userClick(user: User) {
                    TODO("Not yet implemented")
                }

                override fun repositoryClick(repository: Repository) {
                    TODO("Not yet implemented")
                }
            })

        searchEpoxyController.isDebugLoggingEnabled = true
        binding.recyclerViewSearch.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSearch.adapter = searchEpoxyController.adapter
    }

    private fun viewModelObserver() {
        searchViewModel.users.observe(viewLifecycleOwner, {
            searchEpoxyController.setData(
                searchViewModel.users.value,
                searchViewModel.repositories.value
            )
            binding.recyclerViewSearch.layoutManager?.scrollToPosition(0)
        })

        searchViewModel.repositories.observe(viewLifecycleOwner, {
            searchEpoxyController.setData(
                searchViewModel.users.value,
                searchViewModel.repositories.value
            )
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}