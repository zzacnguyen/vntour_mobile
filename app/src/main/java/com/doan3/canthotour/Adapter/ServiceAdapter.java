package com.doan3.canthotour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.doan3.canthotour.Model.ObjectClass.Service;
import com.doan3.canthotour.R;
import com.doan3.canthotour.View.Main.ActivityServiceInfo;

import java.util.ArrayList;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    private ArrayList<Service> services;
    private Context context;

    public ServiceAdapter(ArrayList<Service> service, Context context) {
        this.services = service;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { //Khi gọi DiaDanhAdapter thì hàm này chạy đầu tiên
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.custom_main_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) { //Mỗi 1 lần chạy hàm này tương ứng với load 1 item trong recycler view
        Service service = services.get(position);
        holder.txtTen.setText(service.getName());
        holder.imgHinh.setImageBitmap(service.getImage());
        holder.cardView.setTag(service.getId());

        holder.cardView.setOnClickListener(new View.OnClickListener() {  //Bắt sự kiện click vào 1 item cardview
            @Override
            public void onClick(View view) {
                Intent iServiceInfo = new Intent(context, ActivityServiceInfo.class);
                iServiceInfo.putExtra("id", (int) view.getTag());
                iServiceInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(iServiceInfo);
            }
        });
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder { //ViewHolder chạy thứ 2, phần này giúp cho recycler view ko bị load lại dữ liệu khi thực hiện thao tác vuốt màn hình
        TextView txtTen;
        ImageView imgHinh;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);

            txtTen = itemView.findViewById(R.id.txtPlaceName);
            imgHinh = itemView.findViewById(R.id.imgPlacePhoto);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
