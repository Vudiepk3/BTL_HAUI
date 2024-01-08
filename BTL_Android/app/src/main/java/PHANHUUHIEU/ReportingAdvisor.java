package PHANHUUHIEU;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.anassert.R;

public class ReportingAdvisor extends AppCompatActivity {

    private final String EVALUATE[] = {"A","B+","B","C+","C","D+","D","F","Cảnh báo"};
    // Dữ liệu mẫu
    String[] maSinhVienArray = {"SV001", "SV002", "SV003", "SV004", "SV005", "SV006", "SV007", "SV008"};
    String[] tenSinhVienArray = {"Nguyen Van Anh", "Tran Thi Binh", "Le Van Cuong", "Pham Thi Duong", "Phan Đinh Hiep", "Dang Ba Nam", "Vu Thuy Nga", "Vu Dinh Nghia"};
    ListView listView;
    ArrayAdapter evaluateAdapter;
    private ImageButton cancelButton;
    private Button ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting_advisor);
        Toolbar toolbar = findViewById(R.id.toolbar);
        evaluateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,EVALUATE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false); // Ẩn tiêu đề mặc định của Action Bar
        app();
    }

    private void app(){
        // Tạo danh sách cặp Mã và Tên sinh viên
        String[] combinedArray = new String[maSinhVienArray.length];
        for (int i = 0; i < maSinhVienArray.length; i++) {
            combinedArray[i] = maSinhVienArray[i] + " - " + tenSinhVienArray[i];
        }

        // Tạo ArrayAdapter để hiển thị danh sách trên ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, combinedArray);
        // Liên kết ArrayAdapter với ListView
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialog();
                return false;
            }
        });
    }

    private  void dialog(){
        // Tạo một Builder để xây dựng AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(ReportingAdvisor.this);
        builder.setTitle("Nhập dữ liệu đánh giá");

        // Inflate layout tùy chỉnh cho AlertDialog
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_layout, null);
        builder.setView(dialogView);

        // Lấy tham chiếu đến Spinner trong layout tùy chỉnh
        Spinner spEvaluate = dialogView.findViewById(R.id.spEvaluate);
        spEvaluate.setAdapter(evaluateAdapter);

        // Thiết lập nút "OK"
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý dữ liệu được nhập ở đây
                String userInput = spEvaluate.getSelectedItem().toString();
                // Thực hiện các thao tác khác với dữ liệu nhập
                Toast.makeText(ReportingAdvisor.this, "Cập nhật thông tin thành công "+userInput, Toast.LENGTH_SHORT).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(ReportingAdvisor.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ReportingAdvisor.this);

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
                    Toast.makeText(ReportingAdvisor.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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
