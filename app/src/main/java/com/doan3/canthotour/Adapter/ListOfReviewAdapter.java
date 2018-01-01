package com.doan3.canthotour.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.doan3.canthotour.Interface.OnLoadMoreListener;
import com.doan3.canthotour.Model.ObjectClass.Review;
import com.doan3.canthotour.R;

import java.util.ArrayList;


public class ListOfReviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoading;
    private Context context;
    private ArrayList<Review> reviews;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public ListOfReviewAdapter(RecyclerView recyclerView, ArrayList<Review> reviews, Context context) {
        this.context = context;
        this.reviews = reviews;

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
        return reviews.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_danhgia, parent, false);
            return new ViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Review review = reviews.get(position);
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.txtTen.setText(review.getTenNguoiDung());
            viewHolder.txtTieuDe.setText(review.getTieuDe());
            viewHolder.txtNgay.setText(review.getNgayDanhGia());
            viewHolder.rbSoSao.setRating(review.getSoSao());
            viewHolder.txtDanhGia.setText(review.getDanhGia());
        } else if (holder instanceof LoadingViewHolder) {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setLoaded() {
        isLoading = false;
    }

    // "Loading item" ViewHolder
    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = view.findViewById(R.id.progressBar);
        }
    }

    //"Normal item" Viewholder
    private class ViewHolder extends RecyclerView.ViewHolder { //ViewHolder chạy thứ 2, phần này giúp cho recycler view ko bị load lại dữ liệu khi thực hiện thao tác vuốt màn hình
        TextView txtTen, txtTieuDe, txtDanhGia, txtNgay;
        RatingBar rbSoSao;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTen = itemView.findViewById(R.id.textViewTen);
            txtTieuDe = itemView.findViewById(R.id.textViewTieuDe);
            txtDanhGia = itemView.findViewById(R.id.textViewNhanXetNd);
            txtNgay = itemView.findViewById(R.id.textViewNgayNx);
            rbSoSao = itemView.findViewById(R.id.ratingBarGetSoSao);
            cardView = itemView.findViewById(R.id.cardViewDanhSach);

        }
    }
}
