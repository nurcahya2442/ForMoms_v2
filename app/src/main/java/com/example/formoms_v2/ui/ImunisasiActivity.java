package com.example.formoms_v2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.ImunisasiAdapter;
import com.example.formoms_v2.adapter.pojo.Imunisasi;

import java.util.ArrayList;

public class ImunisasiActivity extends AppCompatActivity {

    private ArrayList<Imunisasi> listImunisasi;
    private RecyclerView recyclerViewImun;
    private ImunisasiAdapter adapterImun;

    private String[] namaImun = new String[]{
            "Hepatitis A",
            "Hepatitis B",
            "Hepatitis A",
            "Hepatitis B",
            "Hepatitis B",
            "Hepatitis A"
    };

    private String[] umurBayi = new String[]{
            "2 Bulan",
            "1 Bulan",
            "3 Bulan",
            "2 Tahun",
            "2 Bulan",
            "2 Bulan"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imunisasi);

        recyclerViewImun = (RecyclerView)findViewById(R.id.recyclerImun);
        listImunisasi = addListImun();
        adapterImun = new ImunisasiAdapter(listImunisasi);
        recyclerViewImun.setAdapter(adapterImun);
        recyclerViewImun.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    private ArrayList<Imunisasi> addListImun(){
        ArrayList<Imunisasi> listImunisasi = new ArrayList<>();

        for (int i = 0; i<6; i++){
            Imunisasi imunisasi = new Imunisasi();
            imunisasi.setNamaImun(namaImun[i]);
            imunisasi.setUmurImun(umurBayi[i]);
            listImunisasi.add(imunisasi);
        }
        return listImunisasi;
    }
}
