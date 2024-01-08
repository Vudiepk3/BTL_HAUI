package BUITIENDUNG;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anassert.R;

public class RegistedSubjectAdapter extends ArrayAdapter<RegistedSubjectObject> {
    private Context context;
    private RegistedSubjectObject[] arrMonDangKy;
    public RegistedSubjectAdapter(@NonNull Context context, RegistedSubjectObject[] arrMonDangKy) {
        super(context, R.layout.item_mon_dang_ky,arrMonDangKy);
        this.context=context;
        this.arrMonDangKy=arrMonDangKy;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_mon_dang_ky,null,true);
        TextView tenMonDangKy=view.findViewById(R.id.txttenMonDangKy);
        TextView diemTongKetChu=view.findViewById(R.id.txtdiemTongKetChu);
        ImageView imageMuiTen=view.findViewById(R.id.imageMuiTen);
        RegistedSubjectObject arr=arrMonDangKy[position];
        tenMonDangKy.setText(arr.getTenMonDangKy());
        diemTongKetChu.setText(arr.getDiemTongKetChu());
        imageMuiTen.setImageResource(R.drawable.baseline_double_arrow_24);
        return view;
    }
    public RegistedSubjectObject getItem(int postion){
        return arrMonDangKy[postion];
    }
}

