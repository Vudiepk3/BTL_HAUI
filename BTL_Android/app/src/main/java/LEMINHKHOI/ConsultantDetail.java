package LEMINHKHOI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anassert.CoVanHocTap.CoVanHocTapDAO;
import com.example.anassert.CoVanHocTap.CoVanHocTapObject;
import com.example.anassert.CoVanHocTap.HocPhanDto;
import com.example.anassert.R;

import java.util.ArrayList;

public class ConsultantDetail extends AppCompatActivity {
   Button btnContact;
   TextView txtName, txtPhone, txtEmail;
   ListView lv;
   private CoVanHocTapObject data;
   private CoVanHocTapDAO _service;
    ArrayList<HocPhanDto> listData = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ImageButton cancelButton;
    private Button ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultant_detail);
        _service = new CoVanHocTapDAO(ConsultantDetail.this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Cố vấn học tập");
        getData();
        app();
    }
    private void getData(){
        btnContact = findViewById(R.id.btn_contact_messageDetails);
        txtEmail = findViewById(R.id.txt_email_messageDetails);
        txtPhone = findViewById(R.id.txt_phone_messageDetails);
        txtName = findViewById(R.id.txt_fulleName_messageDetails);
        lv = findViewById(R.id.lv_consultant_details);

        Intent intent = getIntent();
        if (intent != null) {
            // Nhận đối tượng từ Intent
            data = (CoVanHocTapObject) intent.getSerializableExtra("CVHTObject");

            // Sử dụng đối tượng nhận được
            if (data != null) {
                txtEmail.setText(data.Email.toString());
                txtPhone.setText(data.PhoneNumber.toString());
                txtName.setText(data.FullName.toString());

                listData = _service.getAllHP(data.ID);
                adapter = new ConsultantDetailsAdapter(ConsultantDetail.this,R.layout.adapter_consultant_details, listData);
                lv.setAdapter(adapter);
            }
        }
    }
    private void app(){
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContact = new Intent(ConsultantDetail.this, ConsultantContact.class);
                intentContact.putExtra("email_contact", data);
                startActivity(intentContact);
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(ConsultantDetail.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConsultantDetail.this);

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
                    Toast.makeText(ConsultantDetail.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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