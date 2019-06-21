package com.nettox.nettoxwapps.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.API.HrvReport;
import com.nettox.nettoxwapps.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class HrvReportAdapter extends RecyclerView.Adapter<HrvReportAdapter.ProductViewHolder> {

    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<HrvReport> hrvReportList;

    private View.OnClickListener onItemClickListener;

    public void setItemClickListener (View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textDate, textResult;
        ImageView imageEmoji;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textResult = itemView.findViewById(R.id.textV_layoutHrvReport_hrvScale);
            textDate = itemView.findViewById(R.id.textV_layoutHrvReport_date);
            imageEmoji = itemView.findViewById(R.id.imageV_layoutHrvReport_imageEmoji);

            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    //getting the context and product list with constructor
    public HrvReportAdapter(Context mCtx, List<HrvReport> hrvReportList) {
        this.mCtx = mCtx;
        this.hrvReportList = hrvReportList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_hrvreport, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        //getting the product of the specified position
        HrvReport hrvReport = hrvReportList.get(position);

        //binding the data with the viewholder views
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy - HH:mm:ss");

        holder.textDate.setText(hrvReport.getNowDate());
        holder.textResult.setText(String.valueOf(hrvReport.getHrvResult()));

        holder.imageEmoji.setImageDrawable(mCtx.getResources().getDrawable(hrvReport.getEmot()));


    }

    @Override
    public int getItemCount() {
        return hrvReportList.size();
    }
}
