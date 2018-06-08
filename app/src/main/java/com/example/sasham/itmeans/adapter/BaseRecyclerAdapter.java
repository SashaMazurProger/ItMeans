package com.example.sasham.itmeans.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.Holder> {

    private List<T> items;
    private final int layoutId;
    private int variableId;
    private Listener<T> listener;

    public BaseRecyclerAdapter(int layoutId, int variableId) {
        this.layoutId = layoutId;
        this.variableId = variableId;
    }

    public void setItems(List<T> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                layoutId,
                parent,
                false);

        return new Holder<T>(binding);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.bind(items.get(position));
        holder.binding.getRoot()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            listener.onItemClick(v, items.get(position), position);
                        }
                    }
                });
        holder.binding.getRoot()
                .setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (listener != null) {
                            return listener.onLongItemClick(v, items.get(position), position);
                        }
                        return false;
                    }
                })
        ;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(Listener<T> listener) {
        this.listener = listener;
    }

    class Holder<T> extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public Holder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(T item) {
            binding.setVariable(variableId, item);
            binding.executePendingBindings();
        }
    }


    public interface Listener<T> {
        void onItemClick(View itemRoot, T item, int position);
        boolean onLongItemClick(View v, T t, int position);
    }
}
