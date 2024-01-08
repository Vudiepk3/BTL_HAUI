package com.nhom3.sqliteapplication.Adapter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nhom3.sqliteapplication.DAO.RelaxDAO;
import com.nhom3.sqliteapplication.DTO.Relax;
import com.nhom3.sqliteapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RelaxAdapter extends RecyclerView.Adapter<RelaxAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Relax> list;

    public RelaxAdapter(Context context, ArrayList<Relax> list){
        this.context= context;
        this.list = list;
    }
    public void setFilteredList(ArrayList<Relax> filteredList){
        this.list=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView =
                inflater.inflate(R.layout.item_relax, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Relax person = list.get(position);
        holder.txtRelaxid.setText("Mã nghỉ phép:"+list.get(position).getRelaxid());
        holder.txtId.setText("ID :"+list.get(position).getId());
        holder.txtReason.setText("Lý do:"+list.get(position).getReason());
        holder.txtDay.setText("Số ngày:"+list.get(position).getDay());
        holder.txtDate.setText("Ngày nghỉ:"+list.get(position).getDate());

        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        RelaxDAO dao = new RelaxDAO(context);
        holder.imagedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_relax);
                Window window = dialog.getWindow();
                if(window==null){
                    return;
                }
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                WindowManager.LayoutParams windowacc = window.getAttributes();
                windowacc.gravity = Gravity.NO_GRAVITY ;
                window.setAttributes(windowacc);
                Button btnCancel = dialog.findViewById(com.nhom3.sqliteapplication.R.id.btnCancel);

                Button btnAdd = dialog.findViewById(R.id.btnAdd_KH);
                btnAdd.setText("CẬP NHẬT");

                EditText ed_reason = dialog.findViewById(R.id.edReason);
                EditText ed_date = dialog.findViewById(R.id.edDate);
                EditText ed_day = dialog.findViewById(R.id.edDay);
                EditText ed_id = dialog.findViewById(R.id.edId);

                TextView txt = dialog.findViewById(R.id.txtTitel);
                txt.setText("SỬA THÔNG TIN");

                ed_reason.setText(person.getReason());
                ed_date.setText(person.getDate()+"");
                ed_day.setText(person.getDay()+"");
                ed_id.setText("Không phải nhập thông tin");
                ed_id.setEnabled(false);
                ed_id.setFocusable(false);
                ed_id.setClickable(false);

                ImageView img = dialog.findViewById(R.id.imgdate);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatePickerDialog dialog1 = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                int myear = year ;
                                int mmonth = month ;
                                int mdayOfMonth = dayOfMonth ;
                                GregorianCalendar c = new GregorianCalendar(myear,mmonth,mdayOfMonth);
                                ed_date.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_reason.getText().length()==0||
                                ed_date.getText().length()==0||
                                ed_day.getText().length()==0){
                            Toast.makeText(context,"Không được để trống",Toast.LENGTH_SHORT).show();
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_date.getText().toString()))){
                            Toast.makeText(context,"Không đúng định dạng ngày",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            person.setReason(ed_reason.getText().toString());
                            person.setDay(ed_day.getText().toString());
                            person.setDate(ed_date.getText().toString());
                            long res = dao.update(person);
                            if (res>0){
                                Toast.makeText(context,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                                list.clear();
                                list.addAll(dao.getAll());
                                notifyDataSetChanged();
                            }else {
                                Toast.makeText(context,"Cập nhật thất bại ",Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        }
                    }
                });
                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(context);
                builder.setTitle("Bạn có chắc muốn xóa ?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getRelaxid());
                        switch (check){
                            case  1 :
                                list.clear();
                                list.addAll(dao.getAll());
                                notifyDataSetChanged();
                                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context,"Không thể xóa vì có khách hàng trong hóa đơn",Toast.LENGTH_SHORT).show();
                                break;
                            case 0 :
                                Toast.makeText(context,"Xóa không thành công",Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }

                    }
                });
                builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class PersonViewHolder extends RecyclerView.ViewHolder{
        TextView txtRelaxid,txtDate,txtDay,txtReason,txtId,txtSum,txtedit;
        ImageView imgDel,imagedit;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRelaxid = itemView.findViewById(R.id.txtRelaxid);
            txtId = itemView.findViewById(R.id.txtID);
            txtReason = itemView.findViewById(R.id.txtReason);
            txtDay = itemView.findViewById(R.id.txtDay);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtSum = itemView.findViewById(R.id.txtSum);
            imgDel = itemView.findViewById(R.id.imgdel);
            imagedit = itemView.findViewById(R.id.imgedit);
        }

    }

    public boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = (Date) sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
}
