package com.example.android.tones;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment {
    private final ArrayList<Array_Audio> details = new ArrayList<>();


    public AlbumsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.grid_view, container, false);
        if(details.size()==0){
            AddData();
        }
        GridView gridView = view.findViewById(R.id.gridview);
        Grid_Array_adapter adapter = new Grid_Array_adapter(getActivity(),details);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),AlbumListActivity.class);
                String playlist_number = "" + position;
                String imageUrl = details.get(position).getmAlbum_cover();
                String name = details.get(position).getmName_of_playlist();
                Bundle bundle = new Bundle();
                bundle.putString("position",playlist_number);
                bundle.putString("image",imageUrl);
                bundle.putString("name",name);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }
    public void AddData(){
        details.add(new Array_Audio("The Chainsmokers",3,"https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        details.add(new Array_Audio("The Wanted",2,"https://is4-ssl.mzstatic.com/image/thumb/Music/v4/e9/66/f1/e966f14c-30de-b0f0-f5c1-489ee5a59b3a/source/1200x1200bb.jpg"));
        details.add(new Array_Audio("Maroon 5",1,"https://is1-ssl.mzstatic.com/image/thumb/Music/v4/4f/90/b4/4f90b495-91e7-dcf3-fef6-154b7bd66e45/source/1200x1200bb.jpg"));
        details.add(new Array_Audio("Sanju", 2, "http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
        details.add(new Array_Audio("Sonu Titu Ki Sweety", 2,  "https://images-na.ssl-images-amazon.com/images/I/514gZx759CL._SS500.jpg"));
        details.add(new Array_Audio("God's Plan", 1, "https://i1.sndcdn.com/artworks-000290354802-8lvnq7-t500x500.jpg"));
        details.add(new Array_Audio("I Took a Pill In Ibiza", 1,  "https://a2-images.myspacecdn.com/images04/3/bdbcf1a618c240c4acbd89887ab4e675/600x600.jpg"));
        details.add(new Array_Audio("Love Me Again", 1, "https://upload.wikimedia.org/wikipedia/en/5/53/John-Newman-Love-Me-Again.jpg"));
        details.add(new Array_Audio("Mine", 1, "https://direct.rhapsody.com/imageserver/images/Alb.284833279/500x500.jpg"));
        details.add(new Array_Audio("Strip That Down", 1, "https://upload.wikimedia.org/wikipedia/en/e/ee/Liam_Payne_-_Strip_That_Down_%28Official_Single_Cover%29.png"));
        details.add(new Array_Audio("Tareefan", 1, "http://lq.djring.com/covers/66172/Tareefan%20Remix.jpg"));
        details.add(new Array_Audio("Wolves", 1,  "https://upload.wikimedia.org/wikipedia/en/thumb/7/73/Selena_Gomez_and_Marshmello_Wolves.jpg/220px-Selena_Gomez_and_Marshmello_Wolves.jpg"));

    }

}
