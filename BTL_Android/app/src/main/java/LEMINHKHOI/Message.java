package LEMINHKHOI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import com.example.anassert.CoVanHocTap.CoVanHocTapDAO;
import com.example.anassert.CoVanHocTap.CoVanHocTapObject;
import com.example.anassert.R;
import java.util.ArrayList;


import androidx.appcompat.app.AlertDialog;

public class Message extends AppCompatActivity {
    Button btnSearch;
    EditText edtSearch;
    ListView lv;
    CoVanHocTapDAO _service;
    ArrayList<CoVanHocTapObject> listData = new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ImageButton cancelButton;
    private Button ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        _service = new CoVanHocTapDAO(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách cố vấn học tập");
        setup();
        eventFuntion();
    }
    private void setup(){
        btnSearch = findViewById(R.id.btn_searchMessage);
        edtSearch = findViewById(R.id.edt_searchMessage);
        lv = findViewById(R.id.lvMessage);
        searchData("");
    }
    private void eventFuntion(){


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent  intentView  = new Intent(Message.this, ConsultantDetail.class);
                CoVanHocTapObject data = listData.get(position);
                intentView.putExtra("CVHTObject", data);
                startActivity(intentView);
            }
        });
       btnSearch.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String request = "";
               if(edtSearch.getText().toString() != null){
                   request =  edtSearch.getText().toString();
               }
               searchData(request);
           }
       });
    }

    private void searchData(String request){
        listData = _service.getAll(request);
        adapter = new MessageAdapter(Message.this,R.layout.adapte_message, listData);
        lv.setAdapter(adapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(Message.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Message.this);

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
                    Toast.makeText(Message.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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