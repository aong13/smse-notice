package com.example.smse_notice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smse_notice.data.NoticeData;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private static List<NoticeData> noticeDataList;

    public MyAdapter(List<NoticeData> noticeDataList) {
        this.noticeDataList = noticeDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chat_box, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NoticeData noticeData = noticeDataList.get(position);
        holder.name.setText(noticeData.getName());
        holder.content.setText(noticeData.getContent());
        holder.time.setText(noticeData.getTime());
    }

    @Override
    public int getItemCount() {
        return noticeDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView content;
        public TextView time;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            content = view.findViewById(R.id.content);
            time = view.findViewById(R.id.time);
        }
    }

    public void updateData(List<NoticeData> chatMessages) {
        noticeDataList.clear(); // 데이터 초기화
        noticeDataList.addAll(chatMessages); // 새로운 데이터로 업데이트
        notifyDataSetChanged(); // RecyclerView에 데이터 변경 알림
    }


}
