package com.lyk.apiweather20200417;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

//    private ArrayList data;
    private List<dataTextImag> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        View listView;
        ImageView userImage;
        TextView textView;

        public MyViewHolder(View view) {
            super(view);
            listView = view;
            textView = view.findViewById(R.id.T_text);
            userImage = view.findViewById(R.id.user_image);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(List<dataTextImag> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_myholder, parent, false);
        final MyViewHolder vh = new MyViewHolder(v);
        vh.listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = vh.getAdapterPosition();
                dataTextImag DataTextImag = mDataset.get(position);
                Toast.makeText(view.getContext(), "你點擊了View"+ DataTextImag.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
//        holder.textView.setText(mDataset.get(position));
        dataTextImag DataTextImag = mDataset.get(position);
        holder.userImage.setImageResource(DataTextImag.getImageId());
        holder.textView.setText(DataTextImag.getName());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
