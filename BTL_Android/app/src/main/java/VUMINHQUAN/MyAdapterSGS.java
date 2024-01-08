package VUMINHQUAN;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anassert.HocPhan.HocPhanObject;
import com.example.anassert.R;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterSGS extends ArrayAdapter {
    private Activity context;
    private int layoutID;
    private ArrayList<HocPhanObject> list=null;

    public MyAdapterSGS(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context= (Activity) context;
        this.layoutID=resource;
        this.list= (ArrayList<HocPhanObject>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);

        if(position>=0 && list.size()>0){
            TextView txtMH = convertView.findViewById(R.id.txtMH);
            TextView txtSoTin = convertView.findViewById(R.id.txtSoTin);
            TextView txtType = convertView.findViewById(R.id.txtType);
            txtMH.setText(list.get(position).getTenHP()+"");
            txtSoTin.setText(list.get(position).getSoTin()+"");
            txtType.setText(list.get(position).getLoaiHP()+"");
        }
        return convertView;
    }
}



