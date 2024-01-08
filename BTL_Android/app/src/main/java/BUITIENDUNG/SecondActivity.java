package BUITIENDUNG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.anassert.R;

public class SecondActivity extends AppCompatActivity {
    private TextView txtTBC,txt,txtTin,txtXL,e111,e222,e333,e444,e555,e666,e777,e888;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtTBC=findViewById(R.id.txtTBC);
        txtTin=findViewById(R.id.txtTin);
        txtXL=findViewById(R.id.txtXL);
        e111=findViewById(R.id.e111);
        e222=findViewById(R.id.e222);
        e333=findViewById(R.id.e333);
        e444=findViewById(R.id.e444);
        e555=findViewById(R.id.e5555);
        e666=findViewById(R.id.e6666);
        e777=findViewById(R.id.e7777);
        e888=findViewById(R.id.e8888);
        img_back=findViewById(R.id.img_back);
        Intent intent1=getIntent();
        Float d=intent1.getFloatExtra("data0",1);
        Float d1=intent1.getFloatExtra("data01",1);
        Float d2=intent1.getFloatExtra("data02",1);
        Float d3=intent1.getFloatExtra("data03",1);
        Float d4=intent1.getFloatExtra("data04",1);
        Float d5=intent1.getFloatExtra("data05",1);
        Float d6=intent1.getFloatExtra("data06",1);
        Float d7=intent1.getFloatExtra("data07",1);
        Float d8=intent1.getFloatExtra("data08",1);
        Float t=intent1.getFloatExtra("data00",1);


        txtTBC.setText(String.format("%.2f", d).toString());
        txtTin.setText(t.toString());
        if (d < 2.5f) {
            txtXL.setText("Trung Bình");
        } else if (d >= 2.5f && d < 3.2f) {
            txtXL.setText("Khá");
        } else if (d >= 3.2f && d < 3.6f) {
            txtXL.setText("Giỏi");
        } else {
            txtXL.setText("Xuất Sắc");
        }
        e111.setText(String.format("%.2f", d1).toString());
        e222.setText(String.format("%.2f", d2).toString());
        e333.setText(String.format("%.2f", d3).toString());
//        e444.setText(String.format("%.2f", d4).toString());
        //
//        e555.setText(String.format("%.2f", d5).toString());
//        e666.setText(String.format("%.2f", d6).toString());
//        e777.setText(String.format("%.2f", d7).toString());
//        e888.setText(String.format("%.2f", d8).toString());

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}