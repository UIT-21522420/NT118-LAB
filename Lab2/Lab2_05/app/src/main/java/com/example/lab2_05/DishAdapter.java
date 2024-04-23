package com.example.lab2_05;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DishAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Dish> DishList;


    public DishAdapter(Context context, int layout, ArrayList<Dish> DishList) {
        this.context = context;
        this.layout = layout;
        this.DishList = DishList;
    }
    @Override
    public int getCount(){
        return DishList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent)
    {
        if (convertView == null) {
            convertView =
                    LayoutInflater.from(context).inflate(R.layout.dish_item, null,
                            false);
        }

        Dish Dish = DishList.get(position);
        ImageView imgDish = (ImageView) convertView.findViewById(R.id.imgDish);
        TextView txtDish = (TextView) convertView.findViewById(R.id.txtDish);
        txtDish.setSelected(true);
        ImageView icnStar = (ImageView) convertView.findViewById(R.id.icnStar);

        if (Dish.isPromotion())
        {
            icnStar.setVisibility(View.VISIBLE);

        }
        else
        {
            icnStar.setVisibility(View.GONE);
        }

        imgDish.setImageResource(Dish.getThumbnail());
        txtDish.setText(Dish.getName());
        return convertView;
    }
}