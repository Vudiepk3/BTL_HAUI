package VUMINHQUAN;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

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

import com.example.anassert.ChiTietKH.ChiTietKHDAO;
import com.example.anassert.ChiTietKH.ChiTietKHObject;
import com.example.anassert.HocPhan.HocPhanDAO;
import com.example.anassert.HocPhan.HocPhanObject;
import com.example.anassert.KeHoach.KeHoachDAO;
import com.example.anassert.R;

import java.time.LocalDate;
import java.util.ArrayList;

public class SuggestedSubjects extends AppCompatActivity {
    int IDSV, IDKH;
    private final String hk[]= {"1","2","3","4","5","6","7","8"};
    private final String TARGET[] = {"AI & BIGDATA","GAME", "WEB JAVA","WEB PHP","WEB .NET","MOBILE","IOT"};
    public ArrayList<HocPhanObject> mh1,mh2,mh3,mh4,mh5,mh6,mh7,mh8;
    ArrayAdapter<String> spAdapter;
    ArrayAdapter<String> lvAdapter;
    ArrayAdapter<String> targetAdapter;
    Spinner sp;
    private ImageButton cancelButton;
    private Button ok_btn;
    Button btnTarget,btnUse;
    TextView txtTarget;
    ListView lv;
    HocPhanDAO hocPhanDAO;
    ChiTietKHDAO chiTietKHDAO;
    KeHoachDAO keHoachDAO;
    ArrayList<HocPhanObject> listHP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_subjects);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Đề xuất môn học");
        getWidget();
        define();
        app();
    }

    private void define() {
        mh1 = new ArrayList<>();
        mh2 = new ArrayList<>();
        mh3 = new ArrayList<>();
        mh4 = new ArrayList<>();
        mh5 = new ArrayList<>();
        mh6 = new ArrayList<>();
        mh7 = new ArrayList<>();
        mh8 = new ArrayList<>();

        hocPhanDAO = new HocPhanDAO(this);
        keHoachDAO = new KeHoachDAO(this);
        chiTietKHDAO = new ChiTietKHDAO(this);

        spAdapter = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,hk);
        targetAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,TARGET);
        IDSV = Integer.parseInt(getIntent().getStringExtra("IDSV"));
        IDKH = keHoachDAO.getKH(IDSV).getID();
    }

    private void app() {
        //Data
        listHP = hocPhanDAO.getHP();
        //
        sp.setAdapter(spAdapter);
        //Set các kỳ học
        changeMH(3);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeLV(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh1);
                lv.setAdapter(lvAdapter);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Tạo một Builder để xây dựng AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestedSubjects.this);
                builder.setTitle("Thông tin của học phần");

                // Inflate layout tùy chỉnh cho AlertDialog
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_detail, null);
                builder.setView(dialogView);
                TextView txtMaHP, txtTenHP, txtSoTin, txtLoaiHP, txtHocKy;
                txtMaHP = dialogView.findViewById(R.id.textViewDisplayMaHocPhan);
                txtTenHP = dialogView.findViewById(R.id.textViewDisplayTenHocPhan);
                txtSoTin = dialogView.findViewById(R.id.textViewDisplaySoTinChi);
                txtLoaiHP = dialogView.findViewById(R.id.textViewDisplayLoaiHocPhan);
                txtHocKy = dialogView.findViewById(R.id.textViewDisplayHocKy);
                HocPhanObject selectedSubject = null;
                if(sp.getSelectedItemPosition()==0) selectedSubject = mh1.get(position);
                if(sp.getSelectedItemPosition()==1) selectedSubject = mh2.get(position);
                if(sp.getSelectedItemPosition()==2) selectedSubject = mh3.get(position);
                if(sp.getSelectedItemPosition()==3) selectedSubject = mh4.get(position);
                if(sp.getSelectedItemPosition()==4) selectedSubject = mh5.get(position);
                if(sp.getSelectedItemPosition()==5) selectedSubject = mh6.get(position);
                if(sp.getSelectedItemPosition()==6) selectedSubject = mh7.get(position);
                if(sp.getSelectedItemPosition()==7) selectedSubject = mh8.get(position);
                txtMaHP.setText(selectedSubject.getMaHP());
                txtTenHP.setText(selectedSubject.getTenHP());
                txtSoTin.setText(selectedSubject.getSoTin()+"");
                txtLoaiHP.setText(selectedSubject.getLoaiHP());
                txtHocKy.setText(selectedSubject.getHocKy()+"");
                // Thiết lập nút "OK"
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                // Tạo và hiển thị AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        btnUse.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                setKH();
                Toast.makeText(SuggestedSubjects.this, "Cập nhật kế hoạch thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Tạo một Builder để xây dựng AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SuggestedSubjects.this);
                builder.setTitle("Nhập mục tiêu nghề nghiệp");

                // Inflate layout tùy chỉnh cho AlertDialog
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.custom_layout, null);
                builder.setView(dialogView);

                // Lấy tham chiếu đến Spinner trong layout tùy chỉnh
                Spinner spTarget = dialogView.findViewById(R.id.spEvaluate);
                spTarget.setAdapter(targetAdapter);

                // Thiết lập nút "OK"
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Xử lý dữ liệu được nhập ở đây
                        String userInput = spTarget.getSelectedItem().toString();
                        txtTarget.setText(userInput);
                        changeMH(spTarget.getSelectedItemPosition()+1);

                        // Thực hiện các thao tác khác với dữ liệu nhập
                        Toast.makeText(SuggestedSubjects.this, "Cập nhật thông tin thành công "+userInput, Toast.LENGTH_SHORT).show();
                    }
                });
                // Thiết lập nút "Cancel"
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                // Tạo và hiển thị AlertDialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setKH() {
        chiTietKHDAO.delete(IDKH);
        // Lấy ngày hiện tại
        LocalDate currentDate = LocalDate.now();

        for(HocPhanObject item : mh1){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh2){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh3){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh4){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh5){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh6){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh7){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
        for(HocPhanObject item : mh8){
            ChiTietKHObject newOne = new ChiTietKHObject(currentDate+"",IDKH,item.getID());
            chiTietKHDAO.insert(newOne);
        }
    }
    private void changeMH(int input){
        // Duyệt qua ArrayList bằng vòng lặp for-each
        mh1.clear();
        mh2.clear();
        mh3.clear();
        mh4.clear();
        mh5.clear();
        mh6.clear();
        mh7.clear();
        mh8.clear();
        for (HocPhanObject item : listHP) {
            if(item.getHocKy() == 1){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                     mh1.add(item);
            }
            if(item.getHocKy() == 2){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh2.add(item);
            }
            if(item.getHocKy() == 3){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh3.add(item);
            }
            if(item.getHocKy() == 4){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh4.add(item);
            }
            if(item.getHocKy() == 5){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh5.add(item);
            }
            if(item.getHocKy() == 6){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh6.add(item);
            }
            if(item.getHocKy() == 7){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh7.add(item);
            }
            if(item.getHocKy() == 8){
                String tieuChi = item.getTieuChi();
                if(tieuChi.charAt(0) == '0' || (tieuChi.charAt(0) == '1' && tieuChi.charAt(input) == '1'))
                    mh8.add(item);
            }
        }
        changeLV(sp.getSelectedItemPosition());
    }

    private void changeLV(int position){
        if(position==0) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh1);
        if(position==1) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh2);
        if(position==2) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh3);
        if(position==3) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh4);
        if(position==4) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh5);
        if(position==5) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh6);
        if(position==6) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh7);
        if(position==7) lvAdapter = new MyAdapterSGS(SuggestedSubjects.this,R.layout.custom_lv_sgs,mh8);
        lv.setAdapter(lvAdapter);
    }

    private void getWidget() {
        sp = findViewById(R.id.sp);
        lv = findViewById(R.id.lv);
        btnTarget = findViewById(R.id.btnTarget);
        txtTarget = findViewById(R.id.txtTarget);
        btnUse = findViewById(R.id.btnUse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(SuggestedSubjects.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(SuggestedSubjects.this);

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
                    Toast.makeText(SuggestedSubjects.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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
}