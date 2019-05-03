package com.example.moviemanagement2.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.moviemanagement2.MainMenu.FavoriteActivity;
import com.example.moviemanagement2.ModelClasses.ModelClassDrama.Episodes;
import com.example.moviemanagement2.R;

import java.util.List;

public class EpisodeAdapter extends ArrayAdapter<Episodes> {
    List<Episodes> episodesList;
    LayoutInflater inflater;

    public EpisodeAdapter(Context context, List objects) {
        super(context, R.layout.mythirdlist, objects);
        episodesList = objects;
        inflater = LayoutInflater.from(context);
        Log.i("TienS", "EpisodeAdapter: ");
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.mythirdlist, parent, false);
        }else{
            Log.i("TienS","MSg:");

        }
        TextView name = convertView.findViewById(R.id.nameEpisode);
        TextView duration = convertView.findViewById(R.id.durationEpisode);

        Episodes episode = getItem(position);
        name.setText(episode.getEpisodeName());
        duration.setText(episode.getEpisodeDuration());

        return convertView;
    }
}
