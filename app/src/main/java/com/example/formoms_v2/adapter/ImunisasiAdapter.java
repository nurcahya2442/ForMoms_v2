package com.example.formoms_v2.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Imunisasi;

import java.util.List;

import androidx.annotation.NonNull;

public class ImunisasiAdapter extends ArrayAdapter {
    private Activity con;
    private List<Imunisasi> notelist;

    public ImunisasiAdapter(Activity con, List<Imunisasi> list){
        super(con, R.layout.imunisasi_row,list);
        this.con = con;
        this.notelist = list;
    }

    @NonNull
    @Override
    public View getView(int position, View cview, ViewGroup parent){
        LayoutInflater inflater = con.getLayoutInflater();

        View ListView = inflater.inflate(R.layout.imunisasi_row,null,true);

        TextView jdl = ListView.findViewById(R.id.judul);
        TextView detail = ListView.findViewById(R.id.detail);
        TextView tanggal = ListView.findViewById(R.id.txt_tanggal);

        Imunisasi note = notelist.get(position);

        jdl.setText(note.getJudul());
        detail.setText(note.getDeskripsi());
        tanggal.setText(note.getTanggal());

        return ListView;
    }
}
