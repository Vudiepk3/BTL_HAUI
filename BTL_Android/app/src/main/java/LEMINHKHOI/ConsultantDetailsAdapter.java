package LEMINHKHOI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anassert.CoVanHocTap.HocPhanDto;
import com.example.anassert.R;

import java.util.ArrayList;
import java.util.List;

public class ConsultantDetailsAdapter extends ArrayAdapter {
    private Activity context;
    private int layoutID;
    private ArrayList<HocPhanDto> list = null;

    public ConsultantDetailsAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context= (Activity) context;
        this.layoutID=resource;
        this.list= (ArrayList<HocPhanDto>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);

        if(position>=0 && list.size()>0){
            TextView txtMaHocPhan = convertView.findViewById(R.id.mahocphan_consulant_details);
            TextView txtTenHocPhan = convertView.findViewById(R.id.tenhocphan_consulant_details);
            TextView txtSoTinChi = convertView.findViewById(R.id.sotinchi_consulant_details);

            String tinchi = String.valueOf( list.get(position).SoTinCHi);
            txtMaHocPhan.setText(list.get(position).MaHocPhan);
            txtTenHocPhan.setText(list.get(position).TenHocPhan);
            txtSoTinChi.setText(tinchi);
        }
        return convertView;
    }
}
