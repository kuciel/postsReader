package com.andysworkshop.postsreader.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.databinding.FragmentMainScreenListBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class MainScreenFragment : Fragment() {

    private val listData : MutableList<PostListData> = mutableListOf()
    private val recyclerViewAdapter = MainScreenRecyclerViewAdapter(listData)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel : MainScreenViewModel by viewModels { viewModelFactory}

    private fun setupDataObserver() {
        viewModel.postListData.observe(viewLifecycleOwner, {
            listData.clear()
            listData.addAll(it)
            recyclerViewAdapter.notifyDataSetChanged()
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            Toast.makeText(context, it, LENGTH_LONG).show()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainScreenListBinding.inflate(inflater, container, false)
        val view = binding.root

        view.layoutManager = LinearLayoutManager(context)
        view.adapter = recyclerViewAdapter

        setupDataObserver()

        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.fragmentResumed()
    }
}