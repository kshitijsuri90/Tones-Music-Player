package com.example.android.tones;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AudioAdapter extends ArrayAdapter<Audio> {
    private static final String TAG = "AudioAdapter";

    AudioAdapter(Activity context, ArrayList<Audio> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listview = convertView;
        Log.d(TAG, "Listview");
        if (listview == null) {
            Log.d(TAG, "list_view");
            listview = LayoutInflater.from(getContext()).inflate(R.layout.list_item_template, parent, false);

        }
        Audio audio = getItem(position);
        assert audio != null;
        ImageView image = listview.findViewById(R.id.music_image);
        Glide.with(getContext()).asBitmap().load(audio.getmImageUrl()).into(image);

        TextView song = listview.findViewById(R.id.name_of_song);
        song.setMovementMethod(new ScrollingMovementMethod());
        song.setText(audio.getmSong_name());

        TextView duration = listview.findViewById(R.id.artist);
        duration.setText(audio.getmArtist());

        TextView artist = listview.findViewById(R.id.song_length);
        artist.setMovementMethod(new ScrollingMovementMethod());
        artist.setText(audio.getmDuration());
        return listview;
    }
}
