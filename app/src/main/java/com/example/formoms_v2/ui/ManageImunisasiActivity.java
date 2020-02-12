package com.example.formoms_v2.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formoms_v2.R;
import com.example.formoms_v2.adapter.pojo.Imunisasi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class ManageImunisasiActivity extends AppCompatActivity {
    TextView act;
    EditText jdl,tgl;
    Button confirm,delete;
    ImageView ivMenuBack;
    String id = "";
    private SimpleDateFormat dateFormatter;
    DatabaseReference db;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_data);

        db = FirebaseDatabase.getInstance().getReference("Imunisasi");
        act = findViewById(R.id.action_txt);
        jdl = findViewById(R.id.edt_judul);
        tgl = findViewById(R.id.edt_tgl);
        confirm = findViewById(R.id.btn_confirm);
        delete = findViewById(R.id.btn_delete);
        ivMenuBack = findViewById(R.id.ivMenuBack);

        Intent pindah = getIntent();
        String action = pindah.getStringExtra("action");
        id = pindah.getStringExtra("id");
        act.setText(action);

        if(!id.isEmpty()){
            delete.setEnabled(true);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Delete(id);
                }
            });
        }
        initbtn(confirm, ImunisasiActivity.class,action);


        ivMenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ManageImunisasiActivity.this, ImunisasiActivity.class);
                startActivity(pindah);
            }
        });
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

    }

    private void initbtn(Button btn,final Class tanclass,final String act){
        confirm.setText(act);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(act.equals("Tambah")){
                    Insert();
                }
                Intent pindah = new Intent(ManageImunisasiActivity.this,tanclass);
                startActivity(pindah);
            }
        });
    }





    @RequiresApi(api = Build.VERSION_CODES.N)
    private void Insert(){
        String judul = jdl.getText().toString();
        String deskripsi = "Belum";
        String date = tgl.getText().toString();

        if(!TextUtils.isEmpty(judul)){

            String id = db.push().getKey();

            Imunisasi newnotes = new Imunisasi(id,judul,deskripsi,date);

            db.child(id).setValue(newnotes);

            Toast.makeText(this,"Post berhasil di masukan",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"Post gagal di masukan",Toast.LENGTH_LONG).show();
        }
    }


    private void Delete(String id){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Imunisasi").child(id);
        db.removeValue();
        Toast.makeText(this,"Berhasil dihapus",Toast.LENGTH_LONG).show();
    }
    private void showDateDialog(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog_imunisasi
         */
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                tgl.setText( dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog_imunisasi
         */
        datePickerDialog.show();
    }
}
