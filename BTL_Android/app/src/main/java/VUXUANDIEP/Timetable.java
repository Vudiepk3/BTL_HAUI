package VUXUANDIEP;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
;import com.example.anassert.R;
import com.example.anassert.ThoiKhoaBieu.ThoiKhoaBieuDAO;
import com.example.anassert.ThoiKhoaBieu.ThoiKhoaBieuObject;

import java.util.ArrayList;

public class Timetable extends AppCompatActivity {
    RecyclerView recyclerView;
    private SearchView searchView;
    private RecyclerView rcv;
    private LinearLayoutManager linearLayoutManager;
    private ThoiKhoaBieuDAO dao ;
    private TimeTableAdapter adapter;
    private ArrayList<ThoiKhoaBieuObject> list ;
    private ImageButton cancelButton;
    private Button ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thời khoá biểu");
        rcv = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        rcv.setLayoutManager(linearLayoutManager);
        dao = new ThoiKhoaBieuDAO(this);
        list = dao.getAll();
        searchView=findViewById(R.id.searchPerson);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });
        adapter = new TimeTableAdapter(this,list);
        rcv.setAdapter(adapter);
    }
    private void search(String query) {
        ArrayList<ThoiKhoaBieuObject> filteredList = new ArrayList<>();
        for (ThoiKhoaBieuObject timetable : list) {
            if (timetable.getMonHoc().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(timetable);
            }
        }
        adapter.setFilteredList(filteredList);
    }
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return true;
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(Timetable.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Timetable.this);

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
                    Toast.makeText(Timetable.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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