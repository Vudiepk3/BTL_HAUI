package VUXUANDIEP;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anassert.LuuTruTaiLieu.LuuTruTaiLieuDAO;
import com.example.anassert.LuuTruTaiLieu.LuuTruTaiLieuObject;
import com.example.anassert.R;

import java.util.ArrayList;

public class SaveDocument extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtUrl;
    private LuuTruTaiLieuDAO dao ;
    private SaveDocumentAdapter adapter;

    private LinearLayoutManager linearLayoutManager;
    private ArrayList<LuuTruTaiLieuObject> list;
    private  SaveDocumentAdapter savedocumentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_document);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        String tab = "";
        for (int i = 0; i < 13; i++) {
            tab += "\t";
        }
        actionBar.setTitle(tab+"Tài liệu tham khảo");
        recyclerView = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView .setLayoutManager(linearLayoutManager);
        dao = new LuuTruTaiLieuDAO(this);
        list = dao.getAll();
        adapter = new SaveDocumentAdapter(this,list);
        recyclerView.setAdapter(adapter);
        /* RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        documentAdapter = new DocumentAdapter(Document.this,List());
        recyclerView.setAdapter(documentAdapter);*/
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_document,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_close) {
            finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}