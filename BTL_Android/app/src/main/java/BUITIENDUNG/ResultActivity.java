package BUITIENDUNG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anassert.R;
////////////////////////////////////
//import BUITIENDUNG.THongKeActivity;

public class ResultActivity extends AppCompatActivity {
    private ListView listView;
    private Spinner spinner;
    RegistedSubjectAdapter adapter;
    private Button btnThoat,btn_TBC;
    private RegistedSubjectObject[] arrMonDangKy_all,arrMonDangKy1,arrMonDangKy2,arrMonDangKy3,arrMonDangKy4,arrMonDangKy5,arrMonDangKy6;
    private ResultObject[] arrChiTiet;
    private RegistedSubjectObject[] arrNew;
    private ArrayAdapter arrayAdapter1;
    private TextView dialog_maMonDangKy,dialog_tenMonDangKy,dialog_tinChi,dialog_trangThai,dialog_maLopMonHoc,dialog_phanTramTietNghi,dialog_diemTX1,dialog_diemTX2,dialog_diemGiuaKy,dialog_diemCuoiKy,dialog_diemTongKetChu,dialog_diemTongKetSo;
    private ImageButton cancelButton;
    private Button ok_btn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Theo dõi kết quả học tập");
        listView=findViewById(R.id.lv);
        spinner=findViewById(R.id.sp);
        btn_TBC=findViewById(R.id.btnTBC);
        String[] arrKy=new String[]{" Tất cả"," 1"," 2","3"," 4"," 5"," 6"};
         arrayAdapter1=new ArrayAdapter(ResultActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,arrKy);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter1);
         clickSpinner();
        Intent intent = getIntent();
        arrMonDangKy_all = (RegistedSubjectObject[]) intent.getSerializableExtra("data_all");
        arrChiTiet = (ResultObject[]) intent.getSerializableExtra("data10");
        arrMonDangKy1 = (RegistedSubjectObject[]) intent.getSerializableExtra("data1");
        arrMonDangKy2 = (RegistedSubjectObject[]) intent.getSerializableExtra("data2");
        arrMonDangKy3 = (RegistedSubjectObject[]) intent.getSerializableExtra("data3");
        arrMonDangKy4 = (RegistedSubjectObject[]) intent.getSerializableExtra("data4");
        arrMonDangKy5 = (RegistedSubjectObject[]) intent.getSerializableExtra("data5");
        arrMonDangKy6 = (RegistedSubjectObject[]) intent.getSerializableExtra("data6");
        adapter=new RegistedSubjectAdapter(this,arrMonDangKy_all);
        listView.setAdapter(adapter);
        int count=arrChiTiet.length;
        onclickLV(count);

        //menu

        onclickMenu();


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_context,menu);
        menu.setHeaderTitle("Kết Quả Quá Trình Học Tập");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menuTBC){
            float tong1, tong2, tong3, tong4, tong5, tong6, tong7, tong8, tong;
            tong1 = tong2 = tong3 = tong4 = tong5 = tong6 = tong7 = tong8 = tong = 0.0f;
            float tin,t1,t2,t3,t4,t5,t6,t7,t8;
                    tin = t1 =t2= t3 = t4 = t5 = t6 = t7 = t8 = tong = 0.0f;
            //context Menu
            for(int i=0;i<arrChiTiet.length;i++){
                tong+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                tin+=arrChiTiet[i].getTinChi();
            }
//            for(int i=0;i<arrChiTiet.length;i++){
//                tong1+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
//                t1+=arrChiTiet[i].getTinChi();
//            }
//            for(int i=0;i<arrChiTiet.length;i++){
//                tong2+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
//                t2+=arrChiTiet[i].getTinChi();
//            }
//            for(int i=0;i<arrChiTiet.length;i++){
//                tong3+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
//                t3+=arrChiTiet[i].getTinChi();
//            }
//            for(int i=0;i<arrChiTiet.length;i++){
//                tong4+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
//                t4+=arrChiTiet[i].getTinChi();
//            }

            for(int i=0;i<arrChiTiet.length;i++){
                if (arrChiTiet[i].getKyHoc()==1){
                    tong1+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t1+=arrChiTiet[i].getTinChi();
                } else if (arrChiTiet[i].getKyHoc()==2) {
                    tong2+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t2+=arrChiTiet[i].getTinChi();
                } else if (arrChiTiet[i].getKyHoc()==3) {
                    tong3+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t3+=arrChiTiet[i].getTinChi();
                } else if (arrChiTiet[i].getKyHoc()==4) {
                    tong4+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t4+=arrChiTiet[i].getTinChi();
                } else if (arrChiTiet[i].getKyHoc()==5) {
                    tong5+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t5+=arrChiTiet[i].getTinChi();
                }
                else if (arrChiTiet[i].getKyHoc()==6) {
                    tong6+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t6+=arrChiTiet[i].getTinChi();
                }
                else if (arrChiTiet[i].getKyHoc()==7) {
                    tong7+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t7+=arrChiTiet[i].getTinChi();
                }
                else if (arrChiTiet[i].getKyHoc()==8) {
                    tong8+=(arrChiTiet[i].getDiemTongKetSo()*arrChiTiet[i].getTinChi());
                    t8+=arrChiTiet[i].getTinChi();
                }
            }
            float d=(tong/tin)*(0.4f);
            float t=tin;
            float d1=(tong1/t1)*(0.4f);
            float d2=(tong2/t2)*(0.4f);
            float d3=(tong3/t3)*(0.4f);
            float d4=(tong4/t4)*(0.4f);
            float d5=(tong3/t3)*(0.4f);
            float d6=(tong3/t3)*(0.4f);
            float d7=(tong3/t3)*(0.4f);
            float d8=(tong8/t8)*(0.4f);

            Intent intent=new Intent(ResultActivity.this, SecondActivity.class);
            intent.putExtra("data0",d);
            intent.putExtra("data00",t);
            intent.putExtra("data01",d1);
            intent.putExtra("data02",d2);
            intent.putExtra("data03",d3);
            intent.putExtra("data05",d5);
            intent.putExtra("data04",d4);
            intent.putExtra("data06",d6);
            intent.putExtra("data07",d7);
            intent.putExtra("data08",d8);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.menuThongKe){
            thongKe();
        }
        return super.onContextItemSelected(item);
    }

    private void thongKe() {
        int SA,SBB,SB,SCC,SC,SDD,SD,SF;
        SA=SBB=SB=SCC=SC=SDD=SD=SCC=SC=SF=0;
        int TA,TB,TBB,TC,TCC,TDD,TD,TF;
        TA=TB=TBB=TC=TCC=TDD=TD=TF=0;
        for (int i=0;i<arrMonDangKy_all.length;i++){
            if (arrMonDangKy_all[i].getDiemTongKetChu().equals("A")){
                SA++;
            } else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("B+")) {
                SBB++;
            }else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("B")) {
                SB++;
            }else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("C")) {
                SC++;
            }
            else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("C+")) {
                SCC++;
            }
            else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("D+")) {
                SDD++;
            }
            else if (arrMonDangKy_all[i].getDiemTongKetChu().equals("D")) {
                SD++;
            }
            else {
                SF++;
            }
        }
        TA=(SA)/arrMonDangKy_all.length;
        TB=(SB)/arrMonDangKy_all.length;
        TC=(SC)/arrMonDangKy_all.length;
        TD=(SD)/arrMonDangKy_all.length;
        TF=(SF)/arrMonDangKy_all.length;
        TBB=(SBB)/arrMonDangKy_all.length;
        TCC=(SCC)/arrMonDangKy_all.length;
        TDD=(SDD)/arrMonDangKy_all.length;
///////////////////////////////////////////////////////////////////////////////////////////////
//        Intent intent9=new Intent(ResultActivity.this, THongKeActivity.class);
        Intent intent9=new Intent(ResultActivity.this, StatisticalActivity.class);
        intent9.putExtra("SA",SA);
        intent9.putExtra("SB",SB);
        intent9.putExtra("SC",SC);
        intent9.putExtra("SD",SD);
        intent9.putExtra("SF",SF);
        intent9.putExtra("SBB",SBB);
        intent9.putExtra("SCC",SCC);
        intent9.putExtra("SDD",SDD);

        intent9.putExtra("TA",TA);

        intent9.putExtra("TB",TB);
        intent9.putExtra("TC",TC);
        intent9.putExtra("TD",TD);
        intent9.putExtra("TF",TF);
        intent9.putExtra("TBB",TBB);
        intent9.putExtra("TCC",TCC);
        intent9.putExtra("TDD",TDD);
        startActivity(intent9);

    }

    private void onclickMenu() {
        registerForContextMenu(btn_TBC);

    }

    private void clickSpinner() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){
                    arrNew=arrMonDangKy_all;
                } else if (position==1) {
                    arrNew=arrMonDangKy1;
                } else if (position==2) {
                    arrNew=arrMonDangKy2;
                }else if (position==3){
                    arrNew=arrMonDangKy3;
                }else if (position==4){
                    arrNew=arrMonDangKy4;
                }else if (position==5){
                    arrNew=arrMonDangKy5;
                }else{
                    arrNew=arrMonDangKy6;
                }
//                else {
//                    for (int i=0;i<arrMonDangKy_all.length;i++){
//                        if (Math.abs(arrMonDangKy_all[i].getKyHoc() - p) < epsilon){
//                            arrNew[k]=arrMonDangKy_all[i];
//                            k++;
//                        }
//
//                    }
//                }
                adapter = new RegistedSubjectAdapter(ResultActivity.this, arrNew);
                listView.setAdapter(adapter);
                // Thông báo cho Adapter rằng dữ liệu đã thay đổi
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void onclickLV(int c) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              int i=0;
                RegistedSubjectObject t=adapter.getItem(position);
                while (i<c){
                    if (t.getTenMonDangKy().equals(arrChiTiet[i].getTenMonDangKy())){
                        dialog(Gravity.BOTTOM,arrChiTiet[i]);
                    }
                    i++;
                }

            }
        });
    }

    private void dialog(int gravity, ResultObject x) {
        final Dialog dialog =new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chi_tiet);

        Window window =dialog.getWindow();
        if (window==null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity=gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM==gravity){
            dialog.setCancelable(true);
        }else{
            dialog.setCancelable(false);
        }
        dialog_tenMonDangKy=dialog.findViewById(R.id.dialog_tenMonDangKy);
        dialog_maMonDangKy=dialog.findViewById(R.id.dialog_maMonDangKy);
        dialog_trangThai=dialog.findViewById(R.id.dialog_trangThai);
        dialog_maLopMonHoc=dialog.findViewById(R.id.dialog_MaLopMonHoc);
        dialog_diemTX1=dialog.findViewById(R.id.dialog_diemTX1);
        dialog_diemTX2=dialog.findViewById(R.id.dialog_diemTX2);
        dialog_diemGiuaKy=dialog.findViewById(R.id.dialog_diemGiuaKy);
        dialog_diemCuoiKy=dialog.findViewById(R.id.dialog_diemCuoiKy);
        dialog_diemTongKetChu=dialog.findViewById(R.id.dialog_diemTongKetChu);
        dialog_tinChi=dialog.findViewById(R.id.dialog_tinChi);
        dialog_diemTongKetSo=dialog.findViewById(R.id.dialog_diemTongKetSo);
        dialog_phanTramTietNghi=dialog.findViewById(R.id.dialog_phanTramTietNghi);
        btnThoat=dialog.findViewById(R.id.btnThoat);


        dialog_tenMonDangKy.setText(x.getTenMonDangKy().toString());
        dialog_maMonDangKy.setText(x.getMaMonDangKy().toString());
        dialog_trangThai.setText(x.getTrangThai().toString());
        dialog_maLopMonHoc.setText(x.getMaLopMonHoc().toString());
        dialog_diemTX1.setText(x.getDiemTX1().toString());
        dialog_diemTX2.setText(x.getDiemTX2().toString());
        dialog_diemGiuaKy.setText(x.getDiemGiuaKy().toString());
        dialog_diemCuoiKy.setText(x.getDiemCuoiKy().toString());
        dialog_diemTongKetSo.setText(x.getDiemTongKetSo().toString());
        dialog_diemTongKetChu.setText(x.getDiemTongKetChu().toString());
        dialog_tinChi.setText(x.getTinChi().toString());
        dialog_phanTramTietNghi.setText(x.getPhanTramTietNghi().toString());

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });



        dialog.show();

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(ResultActivity.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ResultActivity.this);

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
                    Toast.makeText(ResultActivity.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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