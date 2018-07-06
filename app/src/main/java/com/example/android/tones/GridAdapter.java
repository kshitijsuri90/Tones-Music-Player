package com.example.android.tones;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<Audio> {

    private static final String TAG = "AudioAdapter";

    public GridAdapter(Activity context,int resource, ArrayList<Audio> list) {
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
        TextView song = listview.findViewById(R.id.name_of_song);
        song.setText(audio.getmSong_name());

        TextView duration = listview.findViewById(R.id.song_length);
        duration.setText(audio.getmDuration());

        TextView artist = listview.findViewById(R.id.artist);
        artist.setText(audio.getmArtist());
        return listview;
    }
}
