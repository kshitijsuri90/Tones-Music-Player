package com.example.android.tones;


import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {

    public ArrayList<Audio> audio;
    ListView listView;
    AudioAdapter adapter;
    private static final int MY_PERMISSION_REQUEST = 1;
    public SongFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.listview,container,false);
        if(ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }
        else {
            doStuff(view);
        }
        return view;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSION_REQUEST:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                            Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getActivity(), "Permission Granted",Toast.LENGTH_SHORT).show();
                        doStuff(Objects.requireNonNull(getView()));
                    }
                    else {
                        Toast.makeText(getActivity(),"Permission Failed",Toast.LENGTH_SHORT).show();

                    }
                }
        }
    }

    private void doStuff(View view) {
        listView = view.findViewById(R.id.listview);
        audio= new ArrayList<>();
        getMusic(view);
        adapter = new AudioAdapter(getActivity(),android.R.layout.simple_list_item_1,audio);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //open player
                Toast.makeText(getContext(),"Playing "+ audio.get(position).getmSong_name() + " \n by " + audio.get(position).getmArtist(),
                        Toast.LENGTH_SHORT).show();
                String song_name = audio.get(position).getmSong_name();
                if(song_name.length()>25){
                    song_name = song_name.substring(0,25);
                }
                String Artist = audio.get(position).getmSong_name();
                if(Artist.length()>20){
                    Artist = Artist.substring(0,20);
                }

                String path = audio.get(position).getmPath()
                        ;
                String Duration = audio.get(position).getmDuration();
                Intent intent = new Intent(getContext(),PlayingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("artist",Artist);
                bundle.putString("song",song_name);
                bundle.putString("duration",Duration);
                bundle.putString("path",path);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void getMusic(View view){
        ContentResolver contentResolver = view.getContext().getContentResolver();
        Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            songCursor = contentResolver.query(songUri,null,null,null);
        }
        if(songCursor != null && songCursor.moveToFirst()){
            int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist = songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int duration = songCursor.getColumnIndex(MediaStore.Audio.Media.DURATION);

            do{
                String current_title = songCursor.getString(songTitle);
                String current_artist = songCursor.getString(songArtist);
                String length = songCursor.getString(duration);
                String path = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                if(String.valueOf(length) != null){
                    try{
                        Long time = Long.valueOf(length);
                        long seconds = time/1000;
                        long minutes = seconds/60;
                        seconds = seconds % 60;

                        if(seconds<10){
                            length = String.valueOf(minutes) + ":0" + String.valueOf(seconds);
                        }else{
                            length = String.valueOf(minutes) + ":" + String.valueOf(seconds);
                        }
                    }catch(NumberFormatException e){
                        length="";
                    }
                }else{
                    length="0:00";
                }
                audio.add(new Audio(current_title ,current_artist,length,path));

            }while (songCursor.moveToNext());
        }
    }

}
