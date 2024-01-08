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

import com.nhom3.sqliteapplication.DAO.PersonDAO;
import com.nhom3.sqliteapplication.DTO.Person;
import com.nhom3.sqliteapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Person> list;

    public PersonAdapter(Context context, ArrayList<Person> list){
        this.context= context;
        this.list = list;
    }
    public void setFilteredList(ArrayList<Person> filteredList){
        this.list=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView =
                inflater.inflate(R.layout.item_information, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Person NHANVIEN = list.get(position);

        holder.txtID.setText("ID:"+list.get(position).getId());
        holder.txtName.setText("Tên:"+list.get(position).getName());
        holder.txtPhone.setText("Số điện thoại:"+list.get(position).getPhone());
        holder.txtAddress.setText("Địa chỉ:"+list.get(position).getAddress());
        holder.txtSex.setText("Giới tính:"+list.get(position).getSex());
        holder.txtFunction.setText("Chức vụ:"+list.get(position).getFunction());
        holder.txtEmail.setText("Email:"+list.get(position).getEmail());
        holder.txtBirtday.setText("Ngày sinh:"+list.get(position).getBirthday());

        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        PersonDAO dao = new PersonDAO(context);
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_person);
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
                EditText ed_name = dialog.findViewById(R.id.edName);
                EditText ed_phone = dialog.findViewById(R.id.edPhone);
                EditText ed_address = dialog.findViewById(R.id.edAddress);
                EditText ed_sex = dialog.findViewById(R.id.edSex);
                EditText ed_email = dialog.findViewById(R.id.edEmail);
                EditText ed_function = dialog.findViewById(R.id.edFunction);
                EditText ed_birthday = dialog.findViewById(R.id.edBirthday);
                TextView txt = dialog.findViewById(R.id.txtTitel);
                txt.setText("SỬA KHÁCH HÀNG");
                ed_name.setText(NHANVIEN.getName());
                ed_phone.setText(NHANVIEN.getPhone()+"");
                ed_address.setText(NHANVIEN.getAddress()+"");
                ed_sex.setText(NHANVIEN.getSex()+"");
                ed_email.setText(NHANVIEN.getEmail()+"");
                ed_function.setText(NHANVIEN.getFunction()+"");
                ed_birthday.setText(NHANVIEN.getBirthday()+"");
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
                                ed_birthday.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_name.getText().length()==0||
                                ed_phone.getText().length()==0||
                                ed_address.getText().length()==0||
                                ed_sex.getText().length()==0||
                                ed_email.getText().length()==0||
                                ed_function.getText().length()==0||
                                ed_birthday.getText().length()==0){
                            Toast.makeText(context,"Không được để trống",Toast.LENGTH_SHORT).show();
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_birthday.getText().toString()))){
                            Toast.makeText(context,"Không đúng định dạng ngày",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            NHANVIEN.setName(ed_name.getText().toString());
                            NHANVIEN.setPhone(ed_phone.getText().toString());
                            NHANVIEN.setSex(ed_sex.getText().toString());
                            NHANVIEN.setAddress(ed_address.getText().toString());
                            NHANVIEN.setEmail(ed_email.getText().toString());
                            NHANVIEN.setFunction(ed_function.getText().toString());
                            NHANVIEN.setBirthday(ed_birthday.getText().toString());
                            long res = dao.update(NHANVIEN);
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
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getId());
                        switch (check){
                            case  1 :
                                list.clear();
                                list.addAll(dao.getAll());
                                notifyDataSetChanged();
                                Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                                break;
                            case -1:
                                Toast.makeText(context,"Không thể xóa ",Toast.LENGTH_SHORT).show();
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
        TextView txtID,txtName , txtPhone , txtBirtday,txtAddress,txtSex,txtEmail,txtFunction;
        ImageView imgDel,imgEdit;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtName);
            txtPhone = itemView.findViewById(R.id.txtPhone);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtSex = itemView.findViewById(R.id.txtSex);
            txtBirtday = itemView.findViewById(R.id.txtBirthday);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtFunction = itemView.findViewById(R.id.txtFunction);
            imgDel = itemView.findViewById(R.id.imgdel);
            imgEdit = itemView.findViewById(R.id.imgedit);
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
