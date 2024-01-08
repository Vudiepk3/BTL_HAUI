package com.nhom3.sqliteapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nhom3.sqliteapplication.Adapter.RelaxAdapter;
import com.nhom3.sqliteapplication.DAO.RelaxDAO;
import com.nhom3.sqliteapplication.DTO.Relax;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RelaxMain extends AppCompatActivity {
    private RecyclerView rcv;
    private FloatingActionButton btnAddPerson;
    private LinearLayoutManager linearLayoutManager;
    private RelaxDAO dao ;
    private RelaxAdapter adapter;
    private ArrayList<Relax> list ;

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relax);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        String tab = "";
        for (int i = 0; i < 4; i++) {
            tab += "\t";
        }
        actionBar.setTitle(tab+"Quản Lý Nghỉ Phép");
        actionBar.setDisplayHomeAsUpEnabled(true);
        rcv = findViewById(R.id.rcvPerson);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        dao = new RelaxDAO(this);
        list = dao.getAll();
        searchView=findViewById(R.id.searchPerson);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                FinterList(newText);
                return true;
            }
        });
        adapter = new RelaxAdapter(this,list);
        rcv.setAdapter(adapter);
        Calendar calendar =Calendar.getInstance();

        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(RelaxMain.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_relax);
                Window window = dialog.getWindow();
                if(window==null){
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowacc = window.getAttributes();
                windowacc.gravity = Gravity.NO_GRAVITY ;
                window.setAttributes(windowacc);
                Button btnCancel = dialog.findViewById(R.id.btnCancel);
                Button btnAdd = dialog.findViewById(R.id.btnAdd_KH);
                EditText ed_id = dialog.findViewById(R.id.edId);
                EditText ed_reason = dialog.findViewById(R.id.edReason);
                EditText ed_date = dialog.findViewById(R.id.edDate);
                EditText ed_day = dialog.findViewById(R.id.edDay);

                ImageView img = dialog.findViewById(R.id.imgdate);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog1 = new DatePickerDialog(RelaxMain.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int myear = year ;
                                int mmonth = month ;
                                int mdayOfMonth = dayOfMonth ;
                                GregorianCalendar c = new GregorianCalendar(myear,mmonth,mdayOfMonth);
                                ed_date.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_day.getText().toString().isEmpty()){
                            ed_day.setError("không được để trống");
                        } if(ed_id.getText().toString().isEmpty()){
                                ed_id.setError("không được để trống");
                        } if (ed_reason.getText().toString().isEmpty()) {
                            ed_reason.setError("Số điện thoại không được để trống");
                        } if (ed_date.getText().toString().isEmpty()) {
                            ed_date.setError("Không được để trống");
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_date.getText().toString()))){
                            ed_date.setError("Không đúng định dạng ngày");
                        }
                        else {
                            Relax person = new Relax();

                            person.setRelaxid(Integer.parseInt(ed_id.getText().toString()));
                            person.setId(Integer.parseInt(ed_id.getText().toString()));
                            person.setReason(ed_reason.getText().toString());
                            person.setDay(ed_day.getText().toString());
                            person.setDate(ed_date.getText().toString());

                            long res = dao.insert(person);
                            if (res>0){
                                Toast.makeText(RelaxMain.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(dao.getAll());
                                adapter.notifyDataSetChanged();
                            }else {
                                Toast.makeText(RelaxMain.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
    }

    private void FinterList(String text) {
        ArrayList<Relax> filteredList=new ArrayList<>();
//        list=dao.getAll();

        for (Relax person: list){
            if (String.valueOf(person.getRelaxid()).toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(person);
            }

        }
        if (filteredList.isEmpty()){
            Toast.makeText(RelaxMain.this, "no data", Toast.LENGTH_SHORT).show();
        }else {
            adapter.setFilteredList(filteredList);
        }
    }
    public boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_report) {
            Toast.makeText(getApplicationContext(), "Chức năng đang trong quá trình phát triển",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_close) {
            finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

}