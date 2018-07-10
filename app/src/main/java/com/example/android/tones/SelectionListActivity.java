package com.example.android.tones;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SelectionListActivity extends AppCompatActivity {
    private ArrayList<Audio> audio = new ArrayList<>();

    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_list);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        String url = bundle.getString("image");
        String name = bundle.getString("name");
        int position = Integer.parseInt(bundle.getString("position"));
        ImageView cover = findViewById(R.id.cover_image);
        TextView name_of_list = findViewById(R.id.name_of_list);
        FloatingActionButton floatingActionButton = findViewById(R.id.play_start);
        ListView listView = findViewById(R.id.album_list_view);
        Glide.with(getApplicationContext()).asBitmap().load(url).into(cover);
        name_of_list.setText(name);
        if (position == 0) {
            AddData_condition1();
        }
        if (position == 1) {
            AddData_condition2();
        }
        if (position == 2) {
            AddData_condition3();
        }
        AudioAdapter audioAdapter = new AudioAdapter(this, audio);
        ImageButton imageButton = findViewById(R.id.back_to_array);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                finish();
            }
        });
        listView.setAdapter(audioAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String song_name = audio.get(position).getmSong_name();
                String artist = audio.get(position).getmArtist();
                String duration = audio.get(position).getmDuration();
                Intent intent = new Intent(SelectionListActivity.this,PlayingActivity.class);
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
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String song_name = audio.get(0).getmSong_name();
                String artist = audio.get(0).getmArtist();
                String duration = audio.get(0).getmDuration();
                Intent intent = new Intent(SelectionListActivity.this,PlayingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",song_name);
                bundle.putString("artist",artist);
                bundle.putString("duration",duration);
                String url = audio.get(0).getmImageUrl();
                bundle.putString("url",url);
                bundle.putParcelableArrayList("list",audio);
                String position_of_item = "" + 0;
                bundle.putString("position",position_of_item);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public void AddData_condition1() {
        audio.add(new Audio("All we Know", "The Chainsmokers", "3:14", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        audio.add(new Audio("Baba Bolta Hain Bas Ho Gaya", "Ranbir Kapoor,Papon,Supriya Pathak", "4:45", "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        audio.add(new Audio("Bom Diggy", "Zack Night", "3:27", "https://images-na.ssl-images-amazon.com/images/I/514gZx759CL._SS500.jpg"));
        audio.add(new Audio("Chasing The Sun", "The Wanted", "3:18", "https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        audio.add(new Audio("Drunk On Love", "The Wanted", "3:23", "https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
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

    public void AddData_condition2(){
        audio.add(new Audio("All we Know", "The Chainsmokers", "3:14", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        audio.add(new Audio("Baba Bolta Hain Bas Ho Gaya", "Ranbir Kapoor,Papon,Supriya Pathak", "4:45", "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        audio.add(new Audio("Bom Diggy", "Zack Night", "3:27", "https://images-na.ssl-images-amazon.com/images/I/514gZx759CL._SS500.jpg"));
        audio.add(new Audio("Chasing The Sun", "The Wanted", "3:18", "https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        audio.add(new Audio("Drunk On Love", "The Wanted", "3:23", "https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        audio.add(new Audio("God's Plan", "Drake", "3:18", "https://i1.sndcdn.com/artworks-000290354802-8lvnq7-t500x500.jpg"));
        audio.add(new Audio("Girls Like You", "Maroon 5", "4:30", "https://is1-ssl.mzstatic.com/image/thumb/Music/v4/4f/90/b4/4f90b495-91e7-dcf3-fef6-154b7bd66e45/source/1200x1200bb.jpg"));
    }

    public void AddData_condition3(){
        audio.add(new Audio("Baba Bolta Hain Bas Ho Gaya", "Ranbir Kapoor,Papon,Supriya Pathak", "4:45", "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        audio.add(new Audio("Girls Like You", "Maroon 5", "4:30", "https://is1-ssl.mzstatic.com/image/thumb/Music/v4/4f/90/b4/4f90b495-91e7-dcf3-fef6-154b7bd66e45/source/1200x1200bb.jpg"));
        audio.add(new Audio("I Took a Pill In Ibiza", "Mike Posner", "3:56", "https://a2-images.myspacecdn.com/images04/3/bdbcf1a618c240c4acbd89887ab4e675/600x600.jpg"));
        audio.add(new Audio("Tareefan", "Badshah", "3:04", "http://lq.djring.com/covers/66172/Tareefan%20Remix.jpg"));
        audio.add(new Audio("Tera Yaar Hu Main", "Arijit Singh", "4:24", "https://blog.thrifter.com/sites/thrifter.com/files/styles/w400h400crop/public/apple-music.jpg?itok=tWPtJy84"));
        audio.add(new Audio("Wolves", "Selena Gomez", "3:17", "https://upload.wikimedia.org/wikipedia/en/thumb/7/73/Selena_Gomez_and_Marshmello_Wolves.jpg/220px-Selena_Gomez_and_Marshmello_Wolves.jpg"));
        audio.add(new Audio("You owe Me", "The Chainsmokers", "2:14", "https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
    }
}
