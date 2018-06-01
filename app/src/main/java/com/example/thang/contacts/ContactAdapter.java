package com.example.thang.contacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact>{
    private Context context;
    private int resource;
    private List<Contact> arrayContact;
    public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.arrayContact=objects;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact_lv, parent, false);
            viewHolder.imgAvt = (ImageView) convertView.findViewById(R.id.img_avt);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = arrayContact.get(position);
        viewHolder.tvName.setText(contact.getName());
        viewHolder.tvNumber.setText(contact.getNumber());

        if (contact.isMale())
        {
            viewHolder.imgAvt.setBackgroundResource(R.drawable.male);
        }
        else
        {
            viewHolder.imgAvt.setBackgroundResource(R.drawable.female);
        }

        return convertView;
    }
    public class ViewHolder{
        ImageView imgAvt;
        TextView tvName;
        TextView tvNumber;
    }
}
