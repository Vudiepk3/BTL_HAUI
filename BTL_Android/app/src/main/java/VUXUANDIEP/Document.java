package VUXUANDIEP;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anassert.R;
import com.example.anassert.TaiLieu.DocumentDAO;
import com.example.anassert.TaiLieu.DocumentObject;

import java.util.ArrayList;

public class Document extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DocumentDAO dao ;
    private DocumentAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<DocumentObject> list;
    private SearchView searchView;
    private  DocumentAdapter documentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Tài liệu tham khảo");
        recyclerView = findViewById(R.id.recyclerview);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView .setLayoutManager(linearLayoutManager);
        dao = new DocumentDAO(this);
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
                search(newText);
                return true;
            }
        });
        adapter = new DocumentAdapter(this,list);
        recyclerView.setAdapter(adapter);
    }
    private void search(String query) {
        ArrayList<DocumentObject> filteredList = new ArrayList<>();
        for (DocumentObject document : list) {
            if (document.getMonHoc().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(document);
            }
        }
        adapter.setFilteredList(filteredList);
    }

    public boolean onQueryTextChange(String newText) {
        search(newText);
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_document,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.action_save) {
           Intent  save = new Intent(Document.this,SaveDocument.class);
           startActivity(save);
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