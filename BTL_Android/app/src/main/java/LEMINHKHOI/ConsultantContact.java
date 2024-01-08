package LEMINHKHOI;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anassert.CoVanHocTap.CoVanHocTapObject;
import com.example.anassert.R;

public class ConsultantContact extends AppCompatActivity {
    TextView txtEmail;
    EditText txtTile, txtDescription;
    Button btnSend;
    private CoVanHocTapObject data;
    private ImageButton cancelButton;
    private Button ok_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consultant_contact);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //vd4 set back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Gửi email");
        setup();
    }

    private void setup(){
        txtEmail = findViewById(R.id.txt_email_contact);
        txtTile = findViewById(R.id.txt_title_contact);
        txtDescription = findViewById(R.id.txt_description_contact);
        btnSend = findViewById(R.id.btn_sendEmail_contact);

        Intent intent = getIntent();
        if (intent != null) {
            // Nhận đối tượng từ Intent
            data = (CoVanHocTapObject) intent.getSerializableExtra("email_contact");
            // Sử dụng đối tượng nhận được
            if (data != null) {
                txtEmail.setText(data.Email.toString());
            }
        }


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    private void sendEmail() {
        String[] TO = {data.Email};
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, txtTile.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, txtDescription.getText().toString());

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle case where no email app is available
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timetable,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        View alertCustomDialog = LayoutInflater.from(ConsultantContact.this).inflate(R.layout.custom_dialog,null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ConsultantContact.this);

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
                    Toast.makeText(ConsultantContact.this, "Cảm ơn bạn đã phản hồi.Báo cáo đã được gửi đến nhà phát triển.", Toast.LENGTH_SHORT).show();
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