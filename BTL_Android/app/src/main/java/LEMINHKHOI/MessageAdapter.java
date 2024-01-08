package LEMINHKHOI;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.anassert.CoVanHocTap.CoVanHocTapObject;
import com.example.anassert.R;

import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends ArrayAdapter {
    private Activity context;
    private int layoutID;
    private ArrayList<CoVanHocTapObject> list = null;

    public MessageAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context= (Activity) context;
        this.layoutID=resource;
        this.list= (ArrayList<CoVanHocTapObject>) objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutID,null);

        if(position>=0 && list.size()>0){
            TextView txtFullname = convertView.findViewById(R.id.txt_fullNameMessage);
            TextView txtEmail = convertView.findViewById(R.id.txt_emailMessage);
            TextView txtPhone = convertView.findViewById(R.id.txt_phoneNumberMessage);
            ImageView imageView = convertView.findViewById(R.id.image_message);

            String imageUrl = "";
            txtFullname.setText(list.get(position).FullName);
            txtEmail.setText(list.get(position).Email);
            txtPhone.setText(list.get(position).PhoneNumber);
        }
        return convertView;
    }
}
