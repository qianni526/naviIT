package com.example.naviIT;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.naviIT.R;
 
public class CustomDrawerAdapter extends ArrayAdapter<NavDrawerItem> {
 
	  private LayoutInflater inflater;
 
      public CustomDrawerAdapter(Context context, int layoutResourceID, NavDrawerItem[]  listItems) {
    	  
    	  super(context, layoutResourceID, listItems);
    	  this.inflater = LayoutInflater.from(context);
      }
 
      @Override
      public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
 
            View view = null;
            
            NavDrawerItem menuItem = this.getItem(position);
            if ( menuItem.getType() == NavMenuItem.ITEM_TYPE ) {
                view = getItemView(convertView, parent, menuItem );
            }
            else {
                view = getSectionView(convertView, parent, menuItem);
            }
             
            return view;
      }
      
      public View getItemView( View convertView, ViewGroup parentView, NavDrawerItem navDrawerItem ) {
          
          NavMenuItem menuItem = (NavMenuItem) navDrawerItem ;
          NavMenuItemHolder navMenuItemHolder = null;
          
          if (convertView == null) {
              convertView = inflater.inflate( R.layout.custom_drawer_item, parentView, false);

              TextView labelView = (TextView) convertView
                      .findViewById( R.id.drawer_itemName );
              ImageView iconView = (ImageView) convertView
                      .findViewById( R.id.drawer_icon );

              navMenuItemHolder = new NavMenuItemHolder();
              navMenuItemHolder.labelView = labelView ;
              navMenuItemHolder.iconView = iconView ;

              convertView.setTag(navMenuItemHolder);
          }

          if ( navMenuItemHolder == null ) {
              navMenuItemHolder = (NavMenuItemHolder) convertView.getTag();
          }
                      
          navMenuItemHolder.labelView.setText(menuItem.getItemName());
          navMenuItemHolder.iconView.setImageResource(menuItem.getImgResID());
          
          return convertView ;
      }

      public View getSectionView(View convertView, ViewGroup parentView,
              NavDrawerItem navDrawerItem) {
          
          NavMenuSection menuSection = (NavMenuSection) navDrawerItem ;
          NavMenuSectionHolder navMenuItemHolder = null;
          
          if (convertView == null) {
              convertView = inflater.inflate( R.layout.navdrawer_section, parentView, false);
              TextView labelView = (TextView) convertView
                      .findViewById( R.id.navmenusection_label );

              navMenuItemHolder = new NavMenuSectionHolder();
              navMenuItemHolder.labelView = labelView ;
              convertView.setTag(navMenuItemHolder);
          }

          if ( navMenuItemHolder == null ) {
              navMenuItemHolder = (NavMenuSectionHolder) convertView.getTag();
          }
                      
          navMenuItemHolder.labelView.setText(menuSection.getItemName());
          
          return convertView ;
      }
      
      @Override
      public int getViewTypeCount() {
          return 2;
      }
      
      @Override
      public int getItemViewType(int position) {
          return this.getItem(position).getType();
      }
      
      @Override
      public boolean isEnabled(int position) {
          return getItem(position).isEnabled();
      }
      
      
      private static class NavMenuItemHolder {
          private TextView labelView;
          private ImageView iconView;
      }
      
      private class NavMenuSectionHolder {
          private TextView labelView;
      }
}