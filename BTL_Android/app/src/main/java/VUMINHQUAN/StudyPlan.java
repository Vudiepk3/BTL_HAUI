package VUMINHQUAN;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import com.example.anassert.ChiTietKH.ChiTietKHDAO;
import com.example.anassert.HocPhan.HocPhanDAO;
import com.example.anassert.HocPhan.HocPhanObject;
import com.example.anassert.KeHoach.KeHoachDAO;
import com.example.anassert.KeHoach.KeHoachObject;
import com.example.anassert.R;

import java.time.LocalDate;
import java.util.ArrayList;


public class StudyPlan extends AppCompatActivity {
    int IDSV;
    String hk[]= {"1","2","3","4","5","6","7","8"};
    ListView lv;
    public static ArrayList<ArrayList<HocPhanObject>> listmh = null;
    ArrayList<HocPhanObject> listHP;
    Spinner sp;
    TextView txtUpdateDate;
    ArrayAdapter<String> lvAdapter;
    ArrayAdapter<String> spAdapter;
    private ImageButton cancelButton;
    private Button ok_btn;
    HocPhanDAO hocPhanDAO;
    KeHoachDAO keHoachDAO;
    KeHoachObject keHoachObject;
    ChiTietKHDAO chiTietKHDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyplan);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Kế hoạch học tập");
        getWidget();
        define();
        app();
    }

    private void define() {
        IDSV = Integer.parseInt(getIntent().getStringExtra("IDSV"));
        hocPhanDAO = new HocPhanDAO(this);
        keHoachDAO = new KeHoachDAO(this);
        chiTietKHDAO = new ChiTietKHDAO(this);

        spAdapter = new ArrayAdapter<>(StudyPlan.this, android.R.layout.simple_spinner_dropdown_item,hk);
        sp.setAdapter(spAdapter);
        getData();
    }

    private void getData(){
        keHoachObject = keHoachDAO.getKH(IDSV);
        txtUpdateDate.setText(keHoachObject.getUpdateDate());
        if(keHoachObject != null){
            listmh = new ArrayList<>();
            txtUpdateDate.setText(keHoachObject.getUpdateDate());
            listHP = hocPhanDAO.getKH(keHoachObject.getID());
            ArrayList<HocPhanObject> mh1 = new ArrayList<>();
            ArrayList<HocPhanObject> mh2 = new ArrayList<>();
            ArrayList<HocPhanObject> mh3 = new ArrayList<>();
            ArrayList<HocPhanObject> mh4 = new ArrayList<>();
            ArrayList<HocPhanObject> mh5 = new ArrayList<>();
            ArrayList<HocPhanObject> mh6 = new ArrayList<>();
            ArrayList<HocPhanObject> mh7 = new ArrayList<>();
            ArrayList<HocPhanObject> mh8 = new ArrayList<>();

            for(HocPhanObject item : listHP){
                if(item.getHocKy() == 1) mh1.add(item);
                if(item.getHocKy() == 2) mh2.add(item);
                if(item.getHocKy() == 3) mh3.add(item);
                if(item.getHocKy() == 4) mh4.add(item);
                if(item.getHocKy() == 5) mh5.add(item);
                if(item.getHocKy() == 6) mh6.add(item);
                if(item.getHocKy() == 7) mh7.add(item);
                if(item.getHocKy() == 8) mh8.add(item);
            }
            listmh.add(mh1);
            listmh.add(mh2);
            listmh.add(mh3);
            listmh.add(mh4);
            listmh.add(mh5);
            listmh.add(mh6);
            listmh.add(mh7);
            listmh.add(mh8);
        }
    }
    private void setLV(int position){
            lvAdapter = new MyAdapterSGS(StudyPlan.this,R.layout.custom_lv_sgs,listmh.get(position));
            lv.setAdapter(lvAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(StudyPlan.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StudyPlan.this);

        alertDialog.setView(alertCustomDialog);
        cancelButton = (ImageButton) alertCustomDialog.findViewById(R.id.cancelID);
        ok_btn = (Button) alertCustomDialog.findViewById(R.id.ok_btn_id);
        final  AlertDialog dialog = alertDialog.create();
        int id = item.getItemId();
        if (id==R.id.action_report) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                }
            });
            ok_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.cancel();
                    Toast.makeText(StudyPlan.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
                }
            });
            return true;
        }
        else if (id==R.id.action_close) {
            finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }

    private void app() {
        if(listmh != null || listmh.size() != 0){

            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    setLV(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    setLV(0);
                }
            });
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder Option = new AlertDialog.Builder(StudyPlan.this);
                    Option.setTitle("Có muốn xóa môn học?");
                    ArrayList<HocPhanObject> mh = listmh.get(sp.getSelectedItemPosition());
                    Option.setMessage(mh.get(position).getTenHP().toString());
                    Option.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            Toast.makeText(StudyPlan.this, "Xóa thành công "+mh.get(position).getTenHP().toString(), Toast.LENGTH_SHORT).show();
                            chiTietKHDAO.deleteSubject(keHoachObject.getID(),mh.get(position).getID());
                            mh.remove(position);
                            // Lấy ngày và giờ hiện tại
                            LocalDate currentDate = LocalDate.now();
                            KeHoachObject newKH = new KeHoachObject(currentDate+"",IDSV);
                            keHoachDAO.update(keHoachObject.getID(), newKH);
                            keHoachObject = keHoachDAO.getKH(IDSV);
                            txtUpdateDate.setText(keHoachObject.getUpdateDate());

                            dialogInterface.cancel();
                            lvAdapter.notifyDataSetChanged();
                        }
                    });
                    Option.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    Option.create().show();
                }
            });
        }
    }

    private void dialog(){

    }

    private void getWidget() {
        lv = findViewById(R.id.lv);
        sp = findViewById(R.id.sp);
        txtUpdateDate = findViewById(R.id.txtUpdateDate);
    }
}
