package com.example.android.learnmongolian.learn

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager

import com.example.android.learnmongolian.R
import com.example.android.learnmongolian.databinding.WordCollectionFragmentBinding
import com.google.android.material.tabs.TabLayout

class WordCollectionFragment : Fragment() {

    companion object {
        fun newInstance() = WordCollectionFragment()
    }

    private lateinit var viewModel: WordCollectionViewModel
    private lateinit var viewPager: ViewPager
    private lateinit var wordCollectionPagerAdapter: WordCollectionPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //val application = requireNotNull(activity).application
        val groupId = WordCollectionFragmentArgs.fromBundle(arguments!!).selectedGroupId
        val viewModelFactory = WordCollectionViewModelFactory(groupId)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WordCollectionViewModel::class.java)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WordCollectionViewModel::class.java)
        return inflater.inflate(R.layout.word_collection_fragment, container, false)

        //val binding = WordCollectionFragmentBinding.inflate(inflater)
        //binding.lifecycleOwner = this
        //binding.viewModel = viewModel
        //return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.properties.observe(this, Observer {
            if (null != it) {
                wordCollectionPagerAdapter = WordCollectionPagerAdapter(childFragmentManager, viewModel.properties.value!!)
                viewPager = view.findViewById(R.id.pager)
                viewPager.adapter = wordCollectionPagerAdapter

                val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
                tabLayout.setupWithViewPager(viewPager)
            }
        })

    }

}
