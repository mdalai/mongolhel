package com.example.android.learnmongolian.learn

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.learnmongolian.network.WordProperty

class WordCollectionPagerAdapter(
    fm: FragmentManager,
    private val wordCollection: List<WordProperty>): FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return WordFragment.newInstance(wordCollection[position])
    }

    override fun getCount(): Int {
        return wordCollection.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //return super.getPageTitle(position)
        return "${wordCollection[position].in_chinese}"
    }
}