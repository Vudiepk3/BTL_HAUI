package BUITIENDUNG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.example.anassert.R;
import android.widget.Button;
import android.widget.ImageButton;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

public class StatisticalActivity extends AppCompatActivity {
    TextView sA,sBB,sB,sC,sCC,sDD,sD,sF,tA,tBB,tCC,tDD,tF,tB,tC,tD;
    private ImageButton cancelButton;
    private Button ok_btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thống kê");
        sA=findViewById(R.id.sA);
        sBB=findViewById(R.id.sBB);
        sB=findViewById(R.id.sB);
        sCC=findViewById(R.id.sCC);
        sC=findViewById(R.id.sC);
        sDD=findViewById(R.id.sĐ);
        sD=findViewById(R.id.sD);
        sF=findViewById(R.id.sF);
        tA=findViewById(R.id.sA);
        tBB=findViewById(R.id.sBB);
        tB=findViewById(R.id.sB);
        tCC=findViewById(R.id.tCC);
        tC=findViewById(R.id.tC);
        tDD=findViewById(R.id.tĐ);
        tD=findViewById(R.id.tD);
        tF=findViewById(R.id.tF);


        Intent intent11=getIntent();
        int SA=intent11.getIntExtra("SA",2);
        int SB=intent11.getIntExtra("SB",7);
        int SC=intent11.getIntExtra("SC",1);
        int SD=intent11.getIntExtra("SD",1);
        int SF=intent11.getIntExtra("SF",1);
        int SBB=intent11.getIntExtra("SBB",1);
        int SCC=intent11.getIntExtra("SCC",1);
        int SDD=intent11.getIntExtra("SDD",1);
        int TA=intent11.getIntExtra("TA",1);
        int TB=intent11.getIntExtra("TB",1);
        int TC=intent11.getIntExtra("TC",1);
        int TD=intent11.getIntExtra("TD",1);
        int TF=intent11.getIntExtra("TF",1);
        int TBB=intent11.getIntExtra("TBB",1);
        int TCC=intent11.getIntExtra("TCC",1);
        int TDD=intent11.getIntExtra("TDD",1);

//        sA.setText(""+SA);
//        sB.setText(""+SB);
//        sC.setText(""+SC);
//        sD.setText(""+SD);
//        sF.setText(""+SF);
//        sBB.setText(""+SBB);
//        sCC.setText(""+SCC);
//        sDD.setText(""+SDD);

        sA.setText(String.valueOf(SA));
        sB.setText(String.valueOf(SB));
        sC.setText(String.valueOf(SC));
        sD.setText(String.valueOf(SD));
        sF.setText(String.valueOf(SF));
        sBB.setText(String.valueOf(SBB));
        sCC.setText(String.valueOf(SCC));
        sDD.setText(String.valueOf(SDD));

        tA.setText(TA+" %");
        tB.setText(TB+" %");
        tBB.setText(TBB+" %");
        tCC.setText(TCC+" %");
        tC.setText(TC+" %");
        tDD.setText(TDD+" %");
        tF.setText(TF+" %");
        tD.setText(TD+" %");

    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(StatisticalActivity.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(StatisticalActivity.this);

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
                    Toast.makeText(StatisticalActivity.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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