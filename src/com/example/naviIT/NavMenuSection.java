package com.example.naviIT;
 
public class NavMenuSection implements NavDrawerItem{
 
	private int id;
	public static final int SECTION_TYPE = 0;
    private String ItemName;

    private NavMenuSection() {
    }
    
    public static NavMenuSection create(int id, String itemName) {
        NavMenuSection section = new NavMenuSection();
        section.setId(id);
        section.setItemName(itemName);
        return section;
    }
 
      public String getItemName() {
            return ItemName;
      }
      public void setItemName(String itemName) {
            ItemName = itemName;
      }

		@Override
		public int getType() {
			// TODO Auto-generated method stub
			return SECTION_TYPE;
		}
	
		@Override
		public boolean isEnabled() {
			// TODO Auto-generated method stub
			return false;
		}
	
		@Override
		public boolean updateActionBarTitle() {
			// TODO Auto-generated method stub
			return false;
		}

		public int getId() {
	        return id;
	    }

	    public void setId(int id) {
	        this.id = id;
	    }
      
}