package com.example.android.tones;

import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlayingActivity extends AppCompatActivity {

    private TextView SongName;
    private TextView Artist;
    private TextView Duration;
    private CircleImageView play_pause;
    private CircleImageView previous;
    private CircleImageView next;
    private static String path;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Handler handler;
    Runnable runnable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing);
        handler = new Handler();

        play_pause = findViewById(R.id.play_pause_btn);
        previous = findViewById(R.id.btn_previous);
        next = findViewById(R.id.btn_next);

        play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play_pause.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    play_pause.setImageResource(R.drawable.pause_image);
                }
            }
        });

        seekBar = findViewById(R.id.seekbar);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        path = bundle.getString("path");
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(path);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                seekBar.setMax(mediaPlayer.getDuration());
                playCycle();
                mediaPlayer.start();
            }
        });


        playCycle();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Artist = findViewById(R.id.artist_name);
        SongName = findViewById(R.id.song_name);
        Duration = findViewById(R.id.duration);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        Artist.setText(bundle.getString("artist"));
        SongName.setText(bundle.getString("song"));
        Duration.setText(bundle.getString("duration"));
        path = bundle.getString("path");

    }

    public void playCycle(){
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if(mediaPlayer.isPlaying()){
            runnable = new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            };
            handler.postDelayed(runnable,1000);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        play_pause.setImageResource(R.drawable.play);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
        play_pause.setImageResource(R.drawable.pause_image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        handler.removeCallbacks(runnable);
    }
}
