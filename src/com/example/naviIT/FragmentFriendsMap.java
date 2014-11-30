package com.example.naviIT;
 
import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.example.naviIT.R;
 
public class FragmentFriendsMap extends Fragment{
 
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
      public static final String ITEM_NAME = "itemName";
 
      private TabHost mTabHost;
      private ViewPager mViewPager;
      private TabsAdapter mTabsAdapter;
      static boolean timeline = false;
      
      public FragmentFriendsMap()
      {
 
      }
 
      @Override
  		public void onCreate(Bundle savedInstanceState) {
  		// TODO Auto-generated method stub
  			super.onCreate(savedInstanceState);
  			
  			setHasOptionsMenu(true);
  		}
      
      public View onCreateView(LayoutInflater inflater, ViewGroup container,
                  Bundle savedInstanceState) {
 
            View v=inflater.inflate(R.layout.activity_friendsmap,container, false);
            mTabHost = (TabHost) v.findViewById(android.R.id.tabhost);
            mTabHost.setup();

            mViewPager = (ViewPager) v.findViewById(R.id.pager);
            mTabsAdapter = new TabsAdapter(getActivity(), mTabHost, mViewPager);

            // Here we load the content for each tab. 
            //mTabsAdapter.addTab(mTabHost.newTabSpec("Map").setIndicator("Map", getResources().getDrawable(R.drawable.ic_action_map)), FragmentFriendsMap_Map.class, null);
            mTabsAdapter.addTab(mTabHost.newTabSpec("Timeline").setIndicator("Timeline", getResources().getDrawable(R.drawable.ic_action_time)), FragmentFriendsMap_Timeline.class, null);
            mTabsAdapter.addTab(mTabHost.newTabSpec("Friends").setIndicator("Friends", getResources().getDrawable(R.drawable.ic_action_group)), FragmentFriendsMap_Friends.class, null);

            for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++) 
            {
                TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
                tv.setTextColor(getResources().getColor(R.color.white));
            } 
            
            return v;
      }
      
      
      @Override
  	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
  		// TODO Auto-generated method stub
      	 
           menu.findItem(R.id.action_login).setVisible(false);
           
           if(timeline){
        	   menu.findItem(R.id.action_refresh).setVisible(true);
           }
           super.onCreateOptionsMenu(menu, inflater);
   		 
  	}
      
      public static class TabsAdapter extends FragmentPagerAdapter implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener
      {
          private final Context mContext;
          private final TabHost mTabHost;
          private final ViewPager mViewPager;
          private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

          static final class TabInfo
          {
              private final String tag;
              private final Class<?> clss;
              private final Bundle args;

              TabInfo(String _tag, Class<?> _class, Bundle _args)
              {
                  tag = _tag;
                  clss = _class;
                  args = _args;
              }
          }

          static class DummyTabFactory implements TabHost.TabContentFactory
          {
              private final Context mContext;

              public DummyTabFactory(Context context)
              {
                  mContext = context;
              }

              public View createTabContent(String tag)
              {
                  View v = new View(mContext);
                  v.setMinimumWidth(0);
                  v.setMinimumHeight(0);
                  return v;
              }
          }

          public TabsAdapter(FragmentActivity activity, TabHost tabHost, ViewPager pager)
          {
              super(activity.getSupportFragmentManager());
              mContext = activity;
              mTabHost = tabHost;
              mViewPager = pager;
              mTabHost.setOnTabChangedListener(this);
              mViewPager.setAdapter(this);
              mViewPager.setOnPageChangeListener(this);
          }

          public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args)
          {
              tabSpec.setContent(new DummyTabFactory(mContext));
              String tag = tabSpec.getTag();

              TabInfo info = new TabInfo(tag, clss, args);
              mTabs.add(info);
              mTabHost.addTab(tabSpec);
              notifyDataSetChanged();
          }

          @Override
          public int getCount()
          {
              return mTabs.size();
          }

          @Override
          public Fragment getItem(int position)
          {
              TabInfo info = mTabs.get(position);

              return Fragment.instantiate(mContext, info.clss.getName(), info.args);

          }

          public void onTabChanged(String tabId)
          {
              int position = mTabHost.getCurrentTab();
              mViewPager.setCurrentItem(position);
              
          }

          public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
          {
          }

          public void onPageSelected(int position)
          {
              // Unfortunately when TabHost changes the current tab, it kindly
              // also takes care of putting focus on it when not in touch mode.
              // The jerk.
              // This hack tries to prevent this from pulling focus out of our
              // ViewPager.
        	  
              TabWidget widget = mTabHost.getTabWidget();
              int oldFocusability = widget.getDescendantFocusability();
              widget.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
              mTabHost.setCurrentTab(position);
              widget.setDescendantFocusability(oldFocusability);
              
              if(position==0){
            	  timeline=true;
              }
          }

          public void onPageScrollStateChanged(int state)
          {
          }
      }
 
}