package com.android.susmita.takehomechallenge;

/**
 * Created by susmita on 6/23/2017.
 */
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.susmita.takehomechallenge.Info;
import com.squareup.picasso.Picasso;
public class CustomListViewAdapter extends ArrayAdapter<Info> {

    private Activity activity;

    public CustomListViewAdapter(Activity activity, int resource, List<Info> infos) {
        super(activity, resource, infos);
        this.activity = activity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        LayoutInflater inflater = (LayoutInflater) activity
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        // If holder not exist then locate all view from UI file.
        if (convertView == null) {
            // inflate UI from XML file
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
            // get all UI view
            holder = new ViewHolder(convertView);
            // set tag for holder
            convertView.setTag(holder);
        } else {
            // if holder created, get tag from view
            holder = (ViewHolder) convertView.getTag();
        }
        Info info = getItem(position);

        Picasso.with(activity).load(info.getImageUrl()).into(holder.image);
        holder.number.setText(info.getNumber());
        holder.description.setText(info.getDescription());

        return convertView;
    }

    private static class ViewHolder{
        private ImageView image;
        private TextView number;
        private TextView description;

        public ViewHolder(View v){
            image = (ImageView) v.findViewById(R.id.thumbnail);
            number = (TextView) v.findViewById(R.id.number);
            description = (TextView) v.findViewById(R.id.description);
        }
    }

    }

