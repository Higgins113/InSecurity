package com.example.stephen.InSecurity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 24-04-2016.
 */
public class ContactAdapter extends ArrayAdapter {
    List list = new ArrayList<>();
    public ContactAdapter(Context context, int resource) {
        super(context, resource);
    }

    public void add(Contacts object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View row;
        row = convertView;
        ContactHolder contactHolder;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.activity_pi,parent,false);
            contactHolder = new ContactHolder();
            contactHolder.power = (TextView) row.findViewById(R.id.power);
            contactHolder.temp = (TextView) row.findViewById(R.id.temp);
            contactHolder.alarm = (TextView) row.findViewById(R.id.alarm);
            row.setTag(contactHolder);
        }

        else
        {
            contactHolder = (ContactHolder)row.getTag();
        }

        Contacts contacts = (Contacts) this.getItem(position);
        contactHolder.power.setText(contacts.getPower());
        contactHolder.temp.setText(contacts.getTemp());
        contactHolder.alarm.setText(contacts.getAlarm());

        return row;
    }

    static class ContactHolder
    {
        TextView power,temp,alarm;
    }

}
