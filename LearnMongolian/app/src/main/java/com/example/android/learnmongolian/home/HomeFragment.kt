package com.example.android.learnmongolian.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android.learnmongolian.R
import com.example.android.learnmongolian.databinding.HomeFragmentBinding
import com.example.android.learnmongolian.databinding.HomeViewItemBinding

class HomeFragment : Fragment() {
//    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
//    override fun onRefresh() {
//        Log.i("HELLO", "onRefresh called from SwipeRefreshLayout")
//        swipeRefreshLayout.isRefreshing = false
//    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    //private val viewModel: HomeViewModel by lazy {
    //    ViewModelProviders.of(this).get(HomeViewModel::class.java)
    //}

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val binding = HomeFragmentBinding.inflate(inflater)
        //val binding = HomeViewItemBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.cardGrid.adapter = HomeViewAdapter(HomeViewAdapter.OnClickListner{
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedProperty.observe(this, Observer {
            if (null != it) {
                //Log.d("DEBUG", viewModel.navigateToSelectedProperty.value.toString())
                this.findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToWordCollectionFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        })

        //swipeRefreshLayout = binding.swipeContainer
        //binding.swipeContainer.setOnRefreshListener(this)
        // Configure the refreshing colors
//        binding.swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
//            android.R.color.holo_green_light,
//            android.R.color.holo_orange_light,
//            android.R.color.holo_red_light)


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,
            view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

}
