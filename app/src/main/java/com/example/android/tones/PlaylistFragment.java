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

public class PlaylistFragment extends Fragment {
    private final ArrayList<Array_Audio> details = new ArrayList<>();

    public PlaylistFragment() {
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
                Intent intent = new Intent(getContext(),SelectionListActivity.class);
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
        details.add(new Array_Audio("Recently Added",17,"https://i.pinimg.com/736x/47/7a/72/477a72d603084b0284d2362ddd660a48.jpg"));
        details.add(new Array_Audio("Most Played",6,"https://i1.sndcdn.com/artworks-000290354802-8lvnq7-t500x500.jpg"));
        details.add(new Array_Audio("Recently Played",7,"http://content.hungama.com/audio%20album/display%20image/300x300%20jpeg/5027207642.jpg"));
    }

}
