package com.lucascabral.x_menapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.lucascabral.x_menapp.databinding.ActivityCharactersBinding
import com.lucascabral.x_menapp.ui.adapter.CharactersAdapter
import com.lucascabral.x_menapp.ui.viewmodel.CharactersViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersActivity : AppCompatActivity() {

    private val viewModel: CharactersViewModel by viewModel()
    private lateinit var binding: ActivityCharactersBinding
    private lateinit var charactersAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        binding.charactersRecyclerView.apply {
            setHasFixedSize(true)
            charactersAdapter = CharactersAdapter()
            adapter = charactersAdapter
            charactersAdapter.addLoadStateListener { combinedLoadStates ->
                binding.progressBar.isVisible =
                    combinedLoadStates.source.refresh is LoadState.Loading
            }
        }
    }

    private fun initViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.getCharacters().collectLatest {
                charactersAdapter.submitData(it)
            }
        }
    }
}