package com.andysworkshop.postsreader.mainscreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.andysworkshop.postsreader.R
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 */
class MainScreenFragment : Fragment() {

    private var columnCount = 1
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
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_screen_list, container, false)

        setupDataObserver()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = recyclerViewAdapter
            }
        }
        return view
    }

    override fun onResume() {
        super.onResume()
        viewModel.fragmentResumed()
    }
}