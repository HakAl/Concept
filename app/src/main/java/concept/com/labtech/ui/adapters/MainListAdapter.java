package concept.com.labtech.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import concept.com.labtech.R;

/**
 * Created by alex on 1/10/15.
 */
public class MainListAdapter extends ArrayAdapter<String> {
    private Context context;
    //todo
    private String[] data, descripts;

    public MainListAdapter(Context context, String[] data) {
        super(context, R.layout.list_card);
        this.context = context;
        this.data = data;
        this.descripts = context.getResources().getStringArray(R.array.list_descripts);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_card, parent, false);
            holder.title = (TextView) convertView.findViewById(R.id.card_header);
            holder.descript = (TextView) convertView.findViewById(R.id.card_descript);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(data[position]);
        holder.descript.setText(descripts[position]);
        return convertView;
    }

    static class ViewHolder
    {
        TextView title, descript;
    }
}
