package tchatha.tading;

/**
 * Created by tchat_000 on 12/31/2014.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter{
        ArrayList<Model> modelItems = null;
        Context context;
        TextView name;

public CustomAdapter(Context context, ArrayList<Model>  resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems = resource;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        name = (TextView) convertView.findViewById(R.id.textView1);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        name.setText(modelItems.get(position).getName());
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    name.setBackgroundResource((R.drawable.bg_strikethrough));}
                else{
                    name.setBackgroundResource(0);
                }

            }
        });
        if(modelItems.get(position).getValue() == 1) {
            cb.setChecked(true);
        }
        else
            cb.setChecked(false);

        return convertView;
        }
        }