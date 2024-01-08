package VUXUANDIEP;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.anassert.R;
import com.example.anassert.ThoiKhoaBieu.ThoiKhoaBieuObject;

import java.util.ArrayList;

public class TimeTableAdapter extends RecyclerView.Adapter<TimeTableAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<ThoiKhoaBieuObject> list;

    public TimeTableAdapter(Context context, ArrayList<ThoiKhoaBieuObject> list){
        this.context= context;
        this.list = list;
    }
    public void setFilteredList(ArrayList<ThoiKhoaBieuObject> filteredList){
        this.list=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView =
                inflater.inflate(R.layout.item_timetable, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        final ThoiKhoaBieuObject timeTableObject = list.get(position);
        if(timeTableObject == null){
            return;
        }
        holder.txtSubject.setText(""+list.get(position).getMonHoc());
        holder.txtDay.setText(""+list.get(position).getThu());
        holder.txtDate.setText(""+list.get(position).getNgay());
        holder.txtTeacher.setText(""+list.get(position).getThongTinGiangVien());
        holder.txtRoom.setText(""+list.get(position).getPhong());
        holder.txtTime.setText(""+list.get(position).getCaHoc());
        holder.txtLocation.setText(""+list.get(position).getDiaDiem());
        holder.txtLocation.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               TimeTableAdapter.this.onClick(timeTableObject);
           }
       });
    }
    private void onClick(ThoiKhoaBieuObject timeTableObject){
        Intent url = new Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/cXfrmjrgxDkCmNgWA?g_st=ic"));
        context.startActivity(url);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView txtSubject,txtDay,txtDate,txtTeacher,txtRoom,txtTime,txtLocation;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubject = itemView.findViewById(R.id.txtSubject);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            txtRoom = itemView.findViewById(R.id.txtRoom);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtLocation = itemView.findViewById(R.id.txtLocation);
        }
    }


}
