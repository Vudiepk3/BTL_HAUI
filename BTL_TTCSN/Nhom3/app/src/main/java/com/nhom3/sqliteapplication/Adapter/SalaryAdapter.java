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

import com.nhom3.sqliteapplication.DAO.SalaryDAO;
import com.nhom3.sqliteapplication.DTO.Salary;
import com.nhom3.sqliteapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SalaryAdapter extends RecyclerView.Adapter<SalaryAdapter.PersonViewHolder> {

    private Context context;
    private ArrayList<Salary> list;

    public SalaryAdapter(Context context, ArrayList<Salary> list){
        this.context= context;
        this.list = list;
    }
    public void setFilteredList(ArrayList<Salary> filteredList){
        this.list=filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View studentView =
                inflater.inflate(R.layout.item_salary, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(studentView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        Salary person = list.get(position);

        holder.txtWalletid.setText("ID :"+list.get(position).getSalaryid());
        holder.txtId.setText("Mã Lương :"+list.get(position).getId());
        holder.txtWare.setText("Lương cứng:"+list.get(position).getWare());
        holder.txtDeduct.setText("Khấu trừ :"+list.get(position).getDeduct());
        holder.txtBonus.setText("Thưởng :"+list.get(position).getBonus());
        holder.txtDatein.setText("Ngày tăng lương :"+list.get(position).getDatein());

        int ware = Integer.parseInt(list.get(position).getWare());
        int deduct = Integer.parseInt(list.get(position).getDeduct());
        int bonus = Integer.parseInt(list.get(position).getBonus());
        holder.txtSum.setText("Tổng :" + (ware + bonus - deduct));
        Calendar calendar =Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SalaryDAO dao = new SalaryDAO(context);

        holder.imagedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_add_salary);
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

                EditText ed_ware = dialog.findViewById(R.id.edWare);
                EditText ed_bonus = dialog.findViewById(R.id.edBonus);
                EditText ed_deduct = dialog.findViewById(R.id.edDeduct);
                EditText ed_id = dialog.findViewById(R.id.edId);
                EditText ed_datein = dialog.findViewById(R.id.edDatein);
                TextView txt = dialog.findViewById(R.id.txtTitel);
                txt.setText("SỬA THÔNG TIN");

                ed_ware.setText(person.getWare()+"");
                ed_deduct.setText(person.getDeduct()+"");
                ed_bonus.setText(person.getBonus()+"");
                ed_datein.setText(person.getDatein()+"");
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
                                ed_datein.setText(sdf.format(c.getTime()));
                            }
                        },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));
                        dialog1.show();
                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ed_ware.getText().length()==0||
                                ed_deduct.getText().length()==0||
                                ed_datein.getText().length()==0||
                                ed_bonus.getText().length()==0){
                            Toast.makeText(context,"Không được để trống",Toast.LENGTH_SHORT).show();
                        }else if(!(isValidFormat("dd/MM/yyyy",ed_datein.getText().toString()))){
                        Toast.makeText(context,"Không đúng định dạng ngày",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            person.setWare(ed_ware.getText().toString());
                            person.setDeduct(ed_deduct.getText().toString());
                            person.setBonus(ed_bonus.getText().toString());
                            person.setDatein(ed_datein.getText().toString());
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
                        int check = dao.delete(list.get(holder.getAdapterPosition()).getSalaryid());
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
        TextView txtWalletid,txtWare,txtBonus,txtDeduct,txtSum,txtId,txtDatein;
        ImageView imgDel,imagedit;
        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWalletid = itemView.findViewById(R.id.txtWalletid);
            txtId = itemView.findViewById(R.id.txtID);
            txtWare = itemView.findViewById(R.id.txtWare);
            txtBonus = itemView.findViewById(R.id.txtBonus);
            txtDeduct = itemView.findViewById(R.id.txtDeduct);
            txtSum = itemView.findViewById(R.id.txtSum);
            txtDatein = itemView.findViewById(R.id.txtDatein);
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
