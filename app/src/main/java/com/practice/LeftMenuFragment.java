package com.practice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by user1 on 2016/11/8.
 */

public class LeftMenuFragment extends ListFragment {
    private static final int SIZE_MENU_ITEM = 3;
    private MenuItem[] mItems = new MenuItem[SIZE_MENU_ITEM];
    private LeftMenuAdapter mAdapter;
    private OnMenuItemSelectedListener listener;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < SIZE_MENU_ITEM; ++i) {
            mItems[i] = new MenuItem(getResources().getStringArray(R.array.array_left_menu)[i], false, R.drawable.music_36px, R.drawable.music_36px_light);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setBackgroundColor(0xffffffff);
        setListAdapter(mAdapter = new LeftMenuAdapter(getContext(),mItems));


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(listener!=null){
            listener.menuItemSelected((((MenuItem)getListAdapter().getItem(position)).text));
        }
        mAdapter.setSelected(position);
    }


    public interface OnMenuItemSelectedListener {
        void menuItemSelected(String title);
    }

    public void setListener(OnMenuItemSelectedListener listener) {
        this.listener = listener;
    }

    public class MenuItem {
        boolean isSelected;
        String text;
        int icon;
        int iconSelected;

        public MenuItem(String text, boolean isSelected, int icon, int iconSelected) {
            this.isSelected = isSelected;
            this.text = text;
            this.icon = icon;
            this.iconSelected = iconSelected;
        }
    }

    public class LeftMenuAdapter extends BaseAdapter {
        private int mSelected;
        MenuItem[] menuItems;
        Context context;

        public LeftMenuAdapter(Context context, MenuItem[] menuItems) {
            this.context = context;
            this.menuItems = menuItems;
        }

        @Override
        public int getCount() {
            return menuItems.length;
        }

        @Override
        public Object getItem(int position) {
            return menuItems[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MenuItem currentMenuItem = (MenuItem) getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
            }
            ImageView itemIv = (ImageView) convertView.findViewById(R.id.itemIv);
            TextView itemTv = (TextView) convertView.findViewById(R.id.itemTv);
            itemTv.setText(currentMenuItem.text);
            itemIv.setImageResource(currentMenuItem.icon);
            convertView.setBackgroundColor(0x00000000);
            if (position == mSelected) {
                itemIv.setImageResource(currentMenuItem.iconSelected);
                convertView.setBackgroundColor(0xffaabbcc);
            }
            return convertView;
        }

        public void setSelected(int position) {
            mSelected = position;
            notifyDataSetChanged();
        }
    }
}
