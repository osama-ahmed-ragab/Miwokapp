package com.example.coco;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    //عشان ما عملش واحد جديد كل مره
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener(){
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager mAudioManager;
    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                // The AUDIOFOCUS_LOSS_TRANSIENT case means that we've lost audio focus for a
                // short amount of time. The AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK case means that
                // our app is allowed to continue playing sound but at a lower volume. We'll treat
                // both cases the same way because our app is playing short sound files.

                // Pause playback and reset player to the start of the file. That way, we can
                // play the word from the beginning when we resume playback.
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                // The AUDIOFOCUS_GAIN case means we have regained focus and can resume playback.
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                // The AUDIOFOCUS_LOSS case means we've lost audio focus and
                // Stop playback and clean up resources
                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        /*   /// the first wqay to creat array
        String[] listnum = new String[10];
        listnum[0] = "one";
        Log.v("Numbersactivity", "this is the i+1 number of my strinf =  " + lists.get(i));*/
        /*/// the second wqay to creat arraylist
        ArrayList<String> lists = new ArrayList<String>();
        lists.add(0, "one");
        lists.add(1, "two");
        lists.add(2, "three");
        lists.add(3, "four");
        lists.add(4, "fife");
        lists.add(5, "six");
        lists.add(6, "seven");
        lists.add(7, "eight");
        lists.add(8, "nine");
        lists.add(9, "ten");

        LinearLayout rootview = (LinearLayout) findViewById(R.id.rootView);

        for (int i = 0; i< lists.size(); i++){
                Log.v("NumbersActivity",lists.get(i));
                TextView wordview = new TextView(this);
                wordview.setText(lists.get(i));
                rootview.addView(wordview);
            }*/
        /*/// the wat to make recycleing view
        ArrayList<String> lists = new ArrayList<String>();
        lists.add(0, "one");
        lists.add(1, "two");
        lists.add(2, "three");
        lists.add(3, "four");
        lists.add(4, "fife");
        lists.add(5, "six");
        lists.add(6, "seven");
        lists.add(7, "eight");
        lists.add(8, "nine");
        lists.add(9, "ten");

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lists);

            ListView listView = (ListView) findViewById(R.id.list);
            listView.setAdapter(itemsAdapter);*/

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        // Create a list of words
        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko",R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu",R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa",R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka",R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka",R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku",R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta",R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo’e",R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na’aacha",R.drawable.number_ten, R.raw.number_ten));

        // Create an {@link WordAdapter}, whose data source is a list of {@link Word}s. The
        // adapter knows how to create list items for each item in the list.
        WordAdapter adapter = new WordAdapter(this, words,R.color.category_numbers);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link WordAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Word} in the list.
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Word word= words.get(position);
            releaseMediaPlayer();

            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, word.getmAudioResourceId());
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(mCompletionListener);
            }


        }
    });

}
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }
    /**
     * Clean up the media player by releasing its resources.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

//            mAudioManager.abandonAudioFocusRequest(mOnAudioFocusChangeListener);
        }
    }
    
}