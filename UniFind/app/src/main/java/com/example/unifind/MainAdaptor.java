package com.example.unifind;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.util.Log;
import java.util.List;

public class MainAdaptor extends BaseExpandableListAdapter {

    Context context;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;

    // constructors
    public MainAdaptor(Context context, List<String> listGroup, HashMap<String,List<String>> listItem) {
        this.context = context;
        this.listGroup = listGroup; // list of university name with order
        this.listItem = listItem; // detail information of each university
        Log.i("myapp","adaptor created"); //pass
    }

    @Override
    // size of groups(universities)
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    // size of each child under the university
    public int getChildrenCount(int groupPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).size();
    }

    @Override
    // getter for group(university)
    public Object getGroup(int groupPosition) {
        return this.listGroup.get(groupPosition);
    }

    @Override
    // getter for child
    public Object getChild(int groupPosition, int childPosition) {
        return this.listItem.get(this.listGroup.get(groupPosition)).get(childPosition);
    }

    @Override
    // getter for the position(index) of the group
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    // getter for the position(index) of the child
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    // display listGroup
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String group = (String) getGroup(groupPosition); // get order + university name
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        // set group name
        TextView textView = convertView.findViewById(R.id.list_parent);
        textView.setText(group);
        return convertView;
    }

    @Override
    // display listItem
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String child = (String) getChild(groupPosition,childPosition); // get child contents
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, null);
        }
        // set child contents
        TextView textView = convertView.findViewById(R.id.list_child);
        textView.setText(child);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
