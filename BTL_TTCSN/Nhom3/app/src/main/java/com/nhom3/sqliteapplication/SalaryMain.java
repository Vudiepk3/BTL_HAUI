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
import com.nhom3.sqliteapplication.Database.DbHelper;
import com.nhom3.sqliteapplication.Adapter.SalaryAdapter;
import com.nhom3.sqliteapplication.DAO.SalaryDAO;
import com.nhom3.sqliteapplication.DTO.Salary;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SalaryMain extends AppCompatActivity {
    private RecyclerView rcv;
    int a = 0 ,b = 0, c = 0;
    private FloatingActionButton btnAddPerson;
    private LinearLayoutManager linearLayoutManager;
    private SalaryDAO dao ;
    private DbHelper  dbHelper;
    private SalaryAdapter adapter;
    private ArrayList<Salary> list ;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salary);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        String tab = "";
        for (int i = 0; i < 4; i++) {
            tab += "\t";
        }
        actionBar.setTitle(tab+"Quản Lý Lương");
        actionBar.setDisplayHomeAsUpEnabled(true);
        rcv = findViewById(R.id.rcvPerson);
        btnAddPerson = findViewById(R.id.btnAddPerson);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        dao = new SalaryDAO(this);
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
        adapter = new SalaryAdapter(this,list);
        rcv.setAdapter(adapter);
        Calendar calendar =Calendar.getInstance();
        btnAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(SalaryMain.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_salary);
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
                EditText ed_ware = dialog.findViewById(R.id.edWare);
                EditText ed_bonus = dialog.findViewById(R.id.edBonus);
                EditText ed_deduct = dialog.findViewById(R.id.edDeduct);
                EditText ed_datein = dialog.findViewById(R.id.edDatein);
                ImageView img = dialog.findViewById(R.id.imgdate);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog1 = new DatePickerDialog(SalaryMain.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int myear = year ;
                                int mmonth = month ;
                                int mdayOfMonth = dayOfMonth ;
                                GregorianCalendar c = new GregorianCalendar(myear,mmonth,mdayOfMonth);
                                ed_datein.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });

                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_ware.getText().toString().isEmpty()){
                            ed_ware.setError("không được để trống");
                        } if (ed_deduct.getText().toString().isEmpty()) {
                            ed_deduct.setError("Không được để trống");
                        }if (ed_id.getText().toString().isEmpty()) {
                            ed_id.setError("Không được để trống");
                        } if (ed_bonus.getText().toString().isEmpty()) {
                            ed_bonus.setError("Không được để trống");
                        }if (ed_datein.getText().toString().isEmpty()) {
                            ed_datein.setError("Ngày Sinh không được để trống");
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_datein.getText().toString()))){
                            ed_datein.setError("Không đúng định dạng ngày");
                        } else {
                            Salary person = new Salary();

                            person.setId(Integer.parseInt(ed_id.getText().toString()));
                            person.setSalaryid(Integer.parseInt(ed_id.getText().toString()));
                            person.setWare(ed_ware.getText().toString());
                            person.setDeduct(ed_deduct.getText().toString());
                            person.setBonus(ed_bonus.getText().toString());
                            person.setDatein(ed_datein.getText().toString());
                            long res = dao.insert(person);
                            if (res>0){
                                Toast.makeText(SalaryMain.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(dao.getAll());
                                adapter.notifyDataSetChanged();
                            } else {
                                Toast.makeText(SalaryMain.this,"Thêm thất bại",Toast.LENGTH_SHORT).show();
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
        ArrayList<Salary> filteredList=new ArrayList<>();
//        list=dao.getAll();

        for (Salary person: list){
            if (String.valueOf(person.getSalaryid()).toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(person);
            }

        }
        if (filteredList.isEmpty()){
            Toast.makeText(SalaryMain.this, "Không tìm thấy dữ liệu", Toast.LENGTH_SHORT).show();
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