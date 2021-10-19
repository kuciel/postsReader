package com.andysworkshop.postsreader.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.databinding.FragmentMainScreenListBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class MainScreenFragment : Fragment() {

    private val listData : MutableList<PostListData> = mutableListOf()
    private val recyclerViewAdapter = MainScreenRecyclerViewAdapter(listData)

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel : MainScreenViewModel by viewModels { viewModelFactory}

    private fun setupDataObserver() {
        viewModel.postListData.observe(viewLifecycleOwner, {
            println("Observer $it")
            listData.clear()
            listData.addAll(it)
            recyclerViewAdapter.notifyDataSetChanged()
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