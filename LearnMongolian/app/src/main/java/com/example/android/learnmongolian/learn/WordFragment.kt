package com.example.android.learnmongolian.learn

import android.graphics.Color
import android.graphics.PorterDuff
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.android.learnmongolian.R
import com.example.android.learnmongolian.databinding.WordFragmentBinding
import com.example.android.learnmongolian.network.WordProperty
import kotlinx.android.synthetic.main.word_fragment.view.*

private const val ARG_PARAM_WORD = "wordObject"

class WordFragment : Fragment() {

    companion object {
        fun newInstance(wordProperty: WordProperty) =
            WordFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_WORD, wordProperty)
                }
            }
    }

    private lateinit var viewModel: WordViewModel
    private lateinit var param1: WordProperty

    lateinit var mediaPlayer: MediaPlayer
    lateinit var audioUrl: String

    // before onCreateView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM_WORD)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val viewModelFactory = WordViewModelFactory(param1)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(WordViewModel::class.java)

        val binding = WordFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.duyinButton.setOnClickListener {
            it.alpha = 0.4f
            it.isClickable = false
            audioUrl = viewModel.word.value?.audio_url.toString()
            mediaPlayer = MediaPlayer().apply {
                setAudioStreamType(AudioManager.STREAM_MUSIC)
                setDataSource(audioUrl)
                prepareAsync()
            }

            mediaPlayer.setOnPreparedListener(object: MediaPlayer.OnPreparedListener{
                override fun onPrepared(mp: MediaPlayer?) {
                    mp?.start()
                }
            })

            // release the resource on completion
            mediaPlayer.setOnCompletionListener(object: MediaPlayer.OnCompletionListener{
                override fun onCompletion(mp: MediaPlayer?) {
                    mp?.release()
                    it.alpha = 1f
                    it.isClickable = true
                }
            })
        }

        return binding.root
    }


}


