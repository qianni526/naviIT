package com.example.naviIT;

import android.content.Context;
 
public class NavMenuItem implements NavDrawerItem {
 
	public static final int ITEM_TYPE = 1 ;

	private int id ;
    private String ItemName;
    private int imgResID;
    private boolean updateActionBarTitle ;
    
    private NavMenuItem() {
    }

    public static NavMenuItem create(int id, String itemName, int imgResID, boolean updateActionBarTitle, Context context ) {
        NavMenuItem item = new NavMenuItem();
        item.setId(id);
        item.setItemName(itemName);
        item.setImgResID(imgResID);
        item.setUpdateActionBarTitle(updateActionBarTitle);
        return item;
    }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
      public String getItemName() {
            return ItemName;
      }
      public void setItemName(String itemName) {
            ItemName = itemName;
      }
      public int getImgResID() {
            return imgResID;
      }
      public void setImgResID(int imgResID) {
            this.imgResID = imgResID;
      }

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return ITEM_TYPE;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateActionBarTitle() {
		// TODO Auto-generated method stub
		return this.updateActionBarTitle;
	}
	
	public void setUpdateActionBarTitle(boolean updateActionBarTitle) {
        this.updateActionBarTitle = updateActionBarTitle;
    }
 
}