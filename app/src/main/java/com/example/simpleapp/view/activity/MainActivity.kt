package com.example.simpleapp.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpleapp.databinding.ActivityMainBinding
import com.example.simpleapp.viewmodel.CatViewModel
import com.example.simpleapp.viewmodel.CatListUI
import com.example.simpleapp.view.adapter.CatAdapter
import com.example.simpleapp.viewmodel.CatViewModelFactory

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!

    private var adapter: CatAdapter? = null

    private val viewModel: CatViewModel by lazy {
        CatViewModelFactory().create(CatViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CatAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        configureSearch()
        configureObserver()

        viewModel.fetchCatList()
    }

    private fun configureSearch() {
        binding.searchEditText.setOnEditorActionListener { textView, actionId, event ->
            if (actionId == android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH ||
                event?.action == android.view.KeyEvent.ACTION_DOWN) {
                val query = textView.text.toString()
                viewModel.searchCats(query)
                true
            } else {
                false
            }
        }
    }

    private fun configureObserver() {
        viewModel.catListUI.observe(this) { state ->
            when (state) {
                is CatListUI.Success -> adapter?.submitList(state.catList)
                is CatListUI.Error -> handleError(state.errorMessage)
                is CatListUI.Empty -> handleEmptyState()
                is CatListUI.Loading -> binding.progressBar.isVisible = state.isLoading
            }
        }
    }

    private fun handleEmptyState() {
        Toast.makeText(this, "Список котов пуст", Toast.LENGTH_SHORT).show()
    }

    private fun handleError(@StringRes errorMessage: Int) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}