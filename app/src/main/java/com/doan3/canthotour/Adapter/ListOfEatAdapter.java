package com.doan3.canthotour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.doan3.canthotour.Interface.OnLoadMoreListener;
import com.doan3.canthotour.Model.Eat;
import com.doan3.canthotour.R;
import com.doan3.canthotour.View.Main.ActivityEatInfo;
import com.doan3.canthotour.View.Main.Content.ActivityEat;

import java.util.ArrayList;


public class ListOfEatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Context context;
    private ArrayList<Eat> eat;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ListOfEatAdapter(RecyclerView recyclerView, ArrayList<Eat> eat, Context context) {
        this.context = context;
        this.eat = eat;

        final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    if (onLoadMoreListener != null)
                        onLoadMoreListener.onLoadMore();
                        isLoading = true;
                }
            }
        });
    }

    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.onLoadMoreListener = mOnLoadMoreListener;
    }

    @Override
    public int getItemViewType(int position) {
        return eat.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_diadiem_list, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ViewHolder) {
            Eat eats = eat.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.txtTenDD.setText(eats.getTenAU());
            viewHolder.txtDiaChiDD.setText(eats.getDiaChiAU());
            viewHolder.imgHinhDD.setImageResource(eats.getHinhAU());

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent iEatInfo = new Intent(context, ActivityEatInfo.class);
                    iEatInfo.putExtra("masp", position+1+"");
                    iEatInfo.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(iEatInfo);
                }
            });
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return  eat.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    // "Loading item" ViewHolder
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        }
    }

    //"Normal item" Viewholder
    private class ViewHolder extends RecyclerView.ViewHolder{ //ViewHolder chạy thứ 2, phần này giúp cho recycler view ko bị load lại dữ liệu khi thực hiện thao tác vuốt màn hình
        TextView txtTenDD, txtDiaChiDD;
        ImageView imgHinhDD;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTenDD = itemView.findViewById(R.id.txtTenDiaDiem);
            imgHinhDD = itemView.findViewById(R.id.imgHinhDiaDiem);
            txtDiaChiDD = itemView.findViewById(R.id.txtDiaChiDD);
            cardView = (CardView) itemView.findViewById(R.id.cardViewDiaDiem);

        }
    }
}
