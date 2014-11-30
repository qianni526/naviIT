package com.example.naviIT;
 
public interface NavDrawerItem {
 
	public int getId();
	public String getItemName();
    public int getType();
    public boolean isEnabled();
    public boolean updateActionBarTitle();  
 
}