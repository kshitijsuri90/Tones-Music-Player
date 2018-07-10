package com.example.android.tones;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongFragment extends Fragment {
    private ArrayList<Audio> audio = new ArrayList<>();
    public SongFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.listview, container, false);
        if(audio.size()==0){
            AddData();
        }
        ListView listView = view.findViewById(R.id.list_view_songs);
        AudioAdapter audioAdapter = new AudioAdapter(getActivity(), audio);
        listView.setAdapter(audioAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String song_name = audio.get(position).getmSong_name();
                String artist = audio.get(position).getmArtist();
                String duration = audio.get(position).getmDuration();
                Intent intent = new Intent(getContext(),PlayingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",song_name);
                bundle.putString("artist",artist);
                bundle.putString("duration",duration);
                String url = audio.get(position).getmImageUrl();
                bundle.putString("url",url);
                bundle.putParcelableArrayList("list",audio);
                String position_of_item = "" + position;
                bundle.putString("position",position_of_item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }
    public void AddData(){
        audio.add(new Audio("All we Know", "The Chainsmokers", "3:14", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        audio.add(new Audio("Baba Bolta Hain Bas Ho Gaya", "Ranbir Kapoor,Papon,Supriya Pathak", "4:45", "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        audio.add(new Audio("Bom Diggy", "Zack Night", "3:27", "https://images-na.ssl-images-amazon.com/images/I/514gZx759CL._SS500.jpg"));
        audio.add(new Audio("Chasing The Sun","The Wanted","3:18","https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        audio.add(new Audio("Drunk On Love","The Wanted","3:23","https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        audio.add(new Audio("Girls Like You", "Maroon 5", "4:30", "https://is1-ssl.mzstatic.com/image/thumb/Music/v4/4f/90/b4/4f90b495-91e7-dcf3-fef6-154b7bd66e45/source/1200x1200bb.jpg"));
        audio.add(new Audio("God's Plan", "Drake", "3:18", "https://i1.sndcdn.com/artworks-000290354802-8lvnq7-t500x500.jpg"));
        audio.add(new Audio("I Took a Pill In Ibiza", "Mike Posner", "3:56", "https://a2-images.myspacecdn.com/images04/3/bdbcf1a618c240c4acbd89887ab4e675/600x600.jpg"));
        audio.add(new Audio("Kar Har Maidaan Fateh", "Sukhwinder Singh,Sherya Ghoshal", "5:11", "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        audio.add(new Audio("Love Me Again", "John Newman", "3:54", "https://upload.wikimedia.org/wikipedia/en/5/53/John-Newman-Love-Me-Again.jpg"));
        audio.add(new Audio("Mine", "Bazzi", "2:14", "https://direct.rhapsody.com/imageserver/images/Alb.284833279/500x500.jpg"));
        audio.add(new Audio("Something Like This", "The Chainsmokers", "4:07", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        audio.add(new Audio("Strip That Down", "Liam Payne", "3:24", "https://upload.wikimedia.org/wikipedia/en/e/ee/Liam_Payne_-_Strip_That_Down_%28Official_Single_Cover%29.png"));
        audio.add(new Audio("Tareefan", "Badshah", "3:04", "http://lq.djring.com/covers/66172/Tareefan%20Remix.jpg"));
        audio.add(new Audio("Tera Yaar Hu Main", "Arijit Singh", "4:24", "https://blog.thrifter.com/sites/thrifter.com/files/styles/w400h400crop/public/apple-music.jpg?itok=tWPtJy84"));
        audio.add(new Audio("Wolves", "Selena Gomez", "3:17", "https://upload.wikimedia.org/wikipedia/en/thumb/7/73/Selena_Gomez_and_Marshmello_Wolves.jpg/220px-Selena_Gomez_and_Marshmello_Wolves.jpg"));
        audio.add(new Audio("You owe Me", "The Chainsmokers", "2:14", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
    }
}
