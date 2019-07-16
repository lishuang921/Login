package com.example.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.bean.ArticlesData;

import java.util.ArrayList;
import java.util.List;

public class WeChatItemAdapter extends RecyclerView.Adapter<WeChatItemAdapter.MyViewHolder> {

    private Context context;

    public WeChatItemAdapter(Context context) {
        this.context = context;
    }

    List<ArticlesData.Articles> articlesData = new ArrayList<>();
   public void getlist(List<ArticlesData.Articles> list){
       if (list != null){
           articlesData.addAll(list);
           notifyDataSetChanged();
       }

   }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_wechat_article, parent,false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.wechat_item_tv_author.setText(articlesData.get(position).getAuthor());
        holder.wechat_item_tv_chapter_name.setText(articlesData.get(position).getChapterName());
        holder.wechat_item_tv_date.setText(articlesData.get(position).getNiceDate());
        holder.wechat_item_tv_title.setText(articlesData.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return  articlesData == null ? 0 : articlesData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView wechat_item_tv_author;
        private final TextView wechat_item_tv_date;
        private final TextView wechat_item_tv_title;
        private final TextView wechat_item_tv_chapter_name;

        public MyViewHolder(@NonNull View itemView) {
          super(itemView);
            wechat_item_tv_author = itemView.findViewById(R.id.wechat_item_tv_author);
            wechat_item_tv_date = itemView.findViewById(R.id.wechat_item_tv_date);
            wechat_item_tv_title = itemView.findViewById(R.id.wechat_item_tv_title);
            wechat_item_tv_chapter_name = itemView.findViewById(R.id.wechat_item_tv_chapter_name);
        }
  }
}
