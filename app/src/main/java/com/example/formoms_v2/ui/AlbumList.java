package com.example.formoms_v2.ui;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.formoms_v2.R;

import java.util.List;

import androidx.annotation.NonNull;

public class AlbumList extends ArrayAdapter {
    private Activity con;
    private List<Album> notelist;

    public AlbumList(Activity con, List<Album> list){
        super(con, R.layout.album_row,list);
        this.con = con;
        this.notelist = list;
    }

    @NonNull
    @Override
    public View getView(int position, View cview, ViewGroup parent){
        LayoutInflater inflater = con.getLayoutInflater();

        View ListView = inflater.inflate(R.layout.album_row,null,true);

        TextView jdl = ListView.findViewById(R.id.judul);
        TextView tanggal = ListView.findViewById(R.id.txt_tanggal);

        Album note = notelist.get(position);

        jdl.setText(note.getAlbumName());
        tanggal.setText(note.getCreatedAt());

        return ListView;
    }
}
