package com.example.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {



    protected MediaPlayer mp=null;
    protected ArrayList<Word> words=new ArrayList<>();
    protected AudioManager audioManager;
    protected View rootView;
    protected ListView listView;
    public WordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.words_list, container, false);

        return rootView;
    }
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }


    protected void setList(int color)
    {
        WordAdapter itemsAdapter = new WordAdapter(getActivity(), this.words,color);
        listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);

        audioManager=(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word word=WordFragment.this.words.get(position);
                audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                mp= MediaPlayer.create(getActivity(),word.getAudioID());
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });
                mp.start();
            }
        });
    }


    protected void releaseMediaPlayer()
    {
        if (mp!=null)
        {
            mp.release();
            mp=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    protected AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            switch (focusChange) {
                case AudioManager.AUDIOFOCUS_GAIN:
                    mp.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    mp.stop();
                    mp.release();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mp.pause();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mp.pause();
                    break;

            }
        }
    };

}
