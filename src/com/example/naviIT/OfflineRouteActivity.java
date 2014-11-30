package com.example.naviIT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.polites.android.GestureImageView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;


public class OfflineRouteActivity extends Activity{

	//For QRcode Scanner
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";    	
		
	//Others   
	Spinner blockSpinner, levelSpinner;
	ArrayAdapter<CharSequence> adapter7, adapter8;
	String selectedBlock, selectedLevel;
	String selectedStart, selectedGoal;
	String s;
	GestureImageView view;
	Bitmap a_groundBitMap, a_firstBitMap, a_secondBitMap, b_groundBitMap, b_firstBitMap, b_secondBitMap ;
	Boolean isStart=false, isGoal=false, chgMap=false, goUp=false, goDown=false, repeat=false, goToBlockA=false, goToBlockB=false;
	ArrayList<Region> regionList = new ArrayList<Region>();
	ArrayList<Region> closedset = new ArrayList<Region>();
	ArrayList<Region> openset = new ArrayList<Region>();
	
	Region start, goal, current=null, lowestGvalueRegion;
	Region subGoal = null,subStart = null;
	
	int spinnerBlockPosition, spinnerLevelPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showroute);
		
		getActionBar().setTitle("Offline Navigation");
		
		Intent i = getIntent();
		selectedStart = i.getStringExtra("selectedStart");
		selectedGoal = i.getStringExtra("selectedGoal");
		spinnerBlockPosition = i.getIntExtra("spinnerBlockPosition", 0);
		spinnerLevelPosition = i.getIntExtra("spinnerLevelPosition", 0);

		Log.d("all data passed", "selectedStart: "+selectedStart+", selectedGoal: "+selectedGoal+", spinnerBlockPosition:  "+spinnerBlockPosition+", spinnerLevelPostion: "+spinnerLevelPosition);
		
		view = new GestureImageView(OfflineRouteActivity.this);
				
		MySQLiteHelper db = new MySQLiteHelper(this);
        // get all regions
        regionList = db.getAllRegions();
        
        db.close();
		
		//Block spinner in the map
		blockSpinner = (Spinner) findViewById(R.id.blockSpinner);			
		adapter7 = ArrayAdapter.createFromResource(OfflineRouteActivity.this,
		        R.array.block, android.R.layout.simple_spinner_item);
		adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		blockSpinner.setAdapter(adapter7);
		blockSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
		//Level spinner in the map
		levelSpinner = (Spinner) findViewById(R.id.levelSpinner);			
		adapter8 = ArrayAdapter.createFromResource(OfflineRouteActivity.this,
		        R.array.level, android.R.layout.simple_spinner_item);
		adapter8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		levelSpinner.setAdapter(adapter8);
		levelSpinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
		
    	//set the spinner in activity_showroute.xml based on the selection of user at first
		blockSpinner.setSelection(spinnerBlockPosition);
    	levelSpinner.setSelection(spinnerLevelPosition);
		
		ImageButton btReset = (ImageButton) findViewById(R.id.btReset);
		btReset.setVisibility(View.INVISIBLE);
		
		btReset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				newIntent();
			}
		});		
		
		boolean finishLoop2 = false;
		
		for(int count=0; count<regionList.size(); count++){
			if(selectedStart.equals(regionList.get(count).getRegionId())){
				start = regionList.get(count);
			}
			if(selectedGoal.equals(regionList.get(count).getRegionId())){
				goal = regionList.get(count);
			}							
			if(count==regionList.size()-1){
				finishLoop2=true;
			}
		}
			
		if(finishLoop2==true){
			Log.d("Message","selectedstart = "+selectedStart);
			Log.d("Message","selectedgoal = "+selectedGoal);
			Log.d("Message","start = "+start);
			Log.d("Message","goal = "+goal);
    		
			//set manually
			//start = regions.get(5);
			//goal = regions.get(34);

			ArrayList<Region> noPath = null;						
			a_groundBitMap = ProcessBitmap(noPath, R.drawable.a_ground, false);
			a_firstBitMap = ProcessBitmap(noPath, R.drawable.a_first,false);
			a_secondBitMap = ProcessBitmap(noPath, R.drawable.a_second,false);
			b_groundBitMap = ProcessBitmap(noPath, R.drawable.b_ground, false);
			b_firstBitMap = ProcessBitmap(noPath, R.drawable.b_first,false);
			b_secondBitMap = ProcessBitmap(noPath, R.drawable.b_second,false);
			checkForMultipleBlock(start, goal);
			//checkForMultipleFloor(start, goal);
			
			//Temporarily comment to test checkForMultipleFloor
			//ArrayList<Region> path = findRoute(start,goal);
			//Log.d("Message", "path:"+path);

			/*
			Bitmap processedBitmap = ProcessingBitmap(path);

	        view.setImageBitmap(processedBitmap);
	        view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
	                                    
	        ViewGroup layout = (ViewGroup) findViewById(R.id.layout2);    
	        layout.addView(view);
	        */
		}
	}

	
	//reset by start the activity again
	public void newIntent(){
    	//reset
	}
	
   
	
	private void showBitmap(Bitmap processedBitmap){
		
		ViewGroup layout = (ViewGroup) findViewById(R.id.layout2); 
		
		Log.d("Message", "view: "+view);
		if (view != null) {
			    ViewGroup parent = (ViewGroup) view.getParent();
			    if (parent != null) {
			        parent.removeView(view);
			        view = new GestureImageView(this); 		 
			    }
			}
		
		view.setImageBitmap(processedBitmap);
	    view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

	    layout.addView(view);
	         
	}	
	
	//problematic (to be solved) !!!!	
	//isStart and isGoal cannot function yet
	private Bitmap ProcessBitmap(ArrayList<Region> path, int drawable, boolean showRoute){
		  Bitmap bm1 = null;
		  Bitmap newBitmap = null;		  	  
		  
		  try {
		   bm1 = BitmapFactory.decodeResource(getResources(), drawable);	
		   
		   Config config = bm1.getConfig();
		   if(config == null){
		    config = Bitmap.Config.ARGB_8888;
		   }
		   
		   newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
		   
			   Canvas canvas = new Canvas(newBitmap);
			   canvas.drawBitmap(bm1, 0, 0, null);    
		       
			   if(showRoute==true){
		        Paint paint = new Paint();
		        paint.setColor(Color.BLUE);
		        paint.setStrokeWidth(8);
		        
		        for(int count=0; count<path.size()-1;count++){
		        	if(path.size()!=1){
		        		canvas.drawLine(path.get(count).getX(), path.get(count).getY(), path.get(count+1).getX(), path.get(count+1).getY(), paint);
		        	}
		        }
		        
		        if(isStart=true){
			        //draw Start indicator (Circle) on bitmap
			        Paint startPaint = new Paint();
			        startPaint.setColor(Color.BLUE);
			        startPaint.setStyle(Style.FILL);
			        canvas.drawCircle(path.get(0).getX(), path.get(0).getY(), 9, startPaint);
			        
			        Paint startCircle = new Paint();
			        startCircle.setColor(Color.BLUE);
			        startCircle.setAlpha(90);
			        canvas.drawCircle(path.get(0).getX(), path.get(0).getY(), 19, startCircle);
			        isStart=false;
			    }
   
		        if(goUp==true){
	        	/*
		        	Paint mpaint= new Paint();
		            mpaint.setColor(Color.parseColor("#333333"));
		            mpaint.setStyle(Style.FILL);
		            Paint paint2= new Paint();
		            paint2.setColor(Color.WHITE);
		            paint2.setTextSize(15);  //set text size
		               
	                FontMetrics fm = new FontMetrics();
	                paint2.setTextAlign(Paint.Align.CENTER);
	                paint2.getFontMetrics(fm);  
	                canvas.drawRect(path.get(path.size()-1).getX()-paint2.measureText(s)/2, path.get(path.size()-1).getY()-50 - paint2.getTextSize(), path.get(path.size()-1).getX() + paint2.measureText(s)/2, path.get(path.size()-1).getY()-50, mpaint);   
	                canvas.drawText(s, path.get(path.size()-1).getX(), path.get(path.size()-1).getY()-50 + -(fm.ascent + fm.descent) / 2, paint2);
				*/
		        	 //draw stairs-up (image) on bitmap
				     
		        	Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.goup);				        
		        	canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-65, path.get(path.size()-1).getY()-75, null);
		        	
		        	goUp=false;
		        }else if(goDown==true){
		        	Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.godown);	
			        canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-60, path.get(path.size()-1).getY()-75, null);
		        
		        	goDown=false;
		        }else if(goToBlockA==true){
		        	Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.gotoblocka);	
			        canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-70, path.get(path.size()-1).getY()-75, null);
		        	
			        goToBlockA=false;
		        }else if(goToBlockB==true){
		        	Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.gotoblockb);	
			        canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-30, path.get(path.size()-1).getY()-75, null);
		        
			        goToBlockB=false;
		        }else{
			        //draw Goal indicator (image) on bitmap
			        Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.ic_indicator);	
			        canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-25, path.get(path.size()-1).getY()-30, null);
		        }
		   }		   
	        
		  	} catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  return newBitmap;
		  }
	
	
	private Bitmap ProcessingBitmap(ArrayList<Region> path){
		  Bitmap bm1 = null;
		  Bitmap newBitmap = null;		  	  
		  
		  try {
		   bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.a_first);	
		   
		   Config config = bm1.getConfig();
		   if(config == null){
		    config = Bitmap.Config.ARGB_8888;
		   }
		   
		   newBitmap = Bitmap.createBitmap(bm1.getWidth(), bm1.getHeight(), config);
		   Canvas canvas = new Canvas(newBitmap);
		   canvas.drawBitmap(bm1, 0, 0, null);    
	       	        
	        Paint paint = new Paint();
	        paint.setColor(Color.BLUE);
	        paint.setStrokeWidth(6);
	        
	        for(int count=0; count<path.size()-1;count++){
	        	if(path.size()!=1){
	        		canvas.drawLine(path.get(count).getX(), path.get(count).getY(), path.get(count+1).getX(), path.get(count+1).getY(), paint);
	        	}
	        }
	        
	        //draw Start indicator (Circle) on bitmap
	        Paint startPaint = new Paint();
	        startPaint.setColor(Color.MAGENTA);
	        startPaint.setStyle(Style.FILL);
	        canvas.drawCircle(path.get(0).getX(), path.get(0).getY(), 9, startPaint);
	        
	        //draw Goal indicator (image) on bitmap
	        Bitmap goalIndicator = BitmapFactory.decodeResource(getResources(), R.drawable.ic_indicator);	
	        canvas.drawBitmap(goalIndicator, path.get(path.size()-1).getX()-22, path.get(path.size()-1).getY()-25, null);
        
		  	} catch (Exception e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  
		  return newBitmap;
		  }	
	
	private Region getShortestDistRegion(ArrayList<Region> set, Region goal){
		//lowest shortest distance
		
			Region small = set.get(0);
			
	    	for(int count=0; count<set.size(); count++){  
	    		if(getDistance(set.get(count),goal)<getDistance(small,goal)){
					small = set.get(count); 
				}
	    	}		    	
	    	Log.d("Message", "small = "+small+" Distance= "+getDistance(small,goal));    	
	    	
	    	return small;	    	
	}
	
	private double getDistance(Region region, Region goal){
		
		double distance = Math.sqrt(Math.pow(goal.getX()-region.getX(), 2) + Math.pow(goal.getY()-region.getY(),2));
		Log.d("Message", "region = "+region+", goal = "+ goal +"  Distance= "+distance); 
		
		return distance;			
	}	
	
	//get neighbours of current region
	private ArrayList<Region> getCurrentNeighbour(Region region){		
		
		String[] neighbourOfCurrent = region.getNeighbour().split(",");	
		ArrayList<Region> neighbours = new ArrayList<Region>();
		for(int count=0;count<neighbourOfCurrent.length;count++){
			for(int i=0;i<regionList.size();i++){
				if(neighbourOfCurrent[count].equals(regionList.get(i).getRegionId()))
				neighbours.add(regionList.get(i));
			}
		}
		return neighbours;		
	}	
	 
	private ArrayList<Region> reconstruct_path(ArrayList<Region> set, Region current){
	    ArrayList<Region> path= new ArrayList<Region>();
		path = closedset;
		if(!set.contains(current)){
			closedset.add(current);
		}

		Log.d("Message", "Final path:"+path);
		return path;
	}
	
	//checkforBlock
	private void checkForMultipleBlock(Region start, Region goal){
		subGoal = goal;	
		
			//if start and goal at different block
			if(start.getBlock()!=goal.getBlock()){
				
				if(start.getBlock().equals("A")){
					goToBlockB=true;
					switch(start.getFloor()){
						case 1:
							//goal will be equal to the exit point of A Ground floor 
							subGoal = regionList.get(11); //1_10
							subStart = regionList.get(68);//4_2
							break;
						case 2:
							//goal will be equal to the exit point of A First floor 
							subGoal = regionList.get(35); //2_17
							subStart = regionList.get(140); //6_7
							break;
						case 3:
							//goal will be equal to the exit point of A First floor 
							subGoal = regionList.get(35); //2_17
							subStart = regionList.get(140); //6_7
							break;
					}
				
			}else if(start.getBlock().equals("B")){
				goToBlockA=true;
				switch(start.getFloor()){
				case 4:
					//goal will be equal to the exit point of B Ground floor 
					subGoal = regionList.get(68); //4_2
					subStart = regionList.get(11); //1_10
					break;
				case 5:
					//goal will be equal to the exit point of B First floor 
					subGoal = regionList.get(68); //4_2
					subStart = regionList.get(11); //1_10
					break;
				case 6:
					//goal will be equal to the exit point of B Second floor 
					subGoal = regionList.get(140); //6_7
					subStart = regionList.get(35); //2_17
					break;
				}
			}
			checkForMultipleFloor(start,subGoal);
			checkForMultipleFloor(subStart,goal);	
		}else{
			checkForMultipleFloor(start,goal);
		}
		
	}
	
	private void checkForMultipleFloor(Region start, Region goal){
		subGoal = goal;	
		
			//if start and goal at different floor
			if(start.getFloor()!=goal.getFloor()){
				
				switch(start.getFloor()){
				case 1:
					//goal will be equal to the exit point of A Ground floor 
					subGoal = regionList.get(0); //1
					break;
				case 2:
					//goal will be equal to the exit point of A First floor 
					subGoal = regionList.get(16); //2
					break;
				case 3:
					//goal will be equal to the exit point of A Second floor 
					subGoal = regionList.get(38); //3
					break;
				case 4:
					//goal will be equal to the exit point of B Ground floor 
					//if more than one exit point???
					if(start.getFloor()>goal.getFloor()){ //goDown
						subGoal = regionList.get(63); //4B
					}else if(start.getFloor()<goal.getFloor()){ //goUp
						subGoal = regionList.get(62); //4A
					}
					break;
				case 5:
					//goal will be equal to the exit point of B First floor 
					subGoal = regionList.get(95); //5A
					break;
				case 6:
					//goal will be equal to the exit point of B Second floor 
					subGoal = regionList.get(129); //6A
					break;
				}
			
				ArrayList<Region> path1 = findRoute(start,subGoal);
				Log.d("Message", "start: " + start +"subGoal:"+ subGoal +",1.Route found: "+path1);
				//a_firstBitMap = ProcessBitmap(path1, R.drawable.a_first, true);		
				//selectBitMapToProcess(start,path1);
				
				if(start.getFloor()<goal.getFloor()){
					//goUp
					
					switch(start.getFloor()){
					case 1:
						//start will be equal to the entry point of First floor 
						//s ="Go up to First Floor";
						goUp=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(16); //2
						break;
					case 2:
						//start will be equal to the entry point of Second floor 				
						//s ="Go up to Second Floor";
						goUp=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(38); //3
						break;
					case 4:
						//start will be equal to the entry point of Second floor 				
						//s ="Go up to Second Floor";
						goUp=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(95); //5A
						break;
					case 5:
						//start will be equal to the entry point of Second floor 				
						//s ="Go up to Second Floor";
						goUp=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(129); //6A
						break;
							
					}				
				}else if(start.getFloor()>goal.getFloor()){
					//goDown
					
					switch(start.getFloor()){
					case 2:
						//start will be equal to the entry point of Ground floor 
						goDown=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(0); //1
						break;
					case 3:
						//start will be equal to the entry point of First floor 
						goDown=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(16); //2
						break;		
					case 5:
						//start will be equal to the entry point of Ground floor 
						goDown=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(63); //4B
						break;
					case 6:
						//start will be equal to the entry point of First floor 
						goDown=true;
						selectBitMapToProcess(start,path1);
						subStart = regionList.get(96); //5B
						break;		
					}			
				}
				
				if(subStart.getFloor()!=goal.getFloor()){
					repeat = true;
					checkForMultipleFloor(subStart,goal);
					Log.d("Message", "check for multiple floor again");
				}else{
					ArrayList<Region> path =  findRoute(subStart,goal);
					Log.d("Message", "substart: " + subStart +"goal:"+ goal +",2.Route found: "+path);
					//a_secondBitMap = ProcessBitmap(path, R.drawable.a_second, true);			
					selectBitMapToProcess(subStart,path);
				}
				
			}else{
				//if start n goal at same floor
				ArrayList<Region> path =  findRoute(start,goal);
				Log.d("Message", "start: " + start +"goal:"+ goal +", same floor Route found: "+path);
				selectBitMapToProcess(start,path);		
			}
		
	}
	
	private void selectBitMapToProcess(Region start, ArrayList<Region> path) {
		// TODO Auto-generated method stub
		if(start.getFloor()==1){
			a_groundBitMap = ProcessBitmap(path, R.drawable.a_ground, true);			
		}else if(start.getFloor()==2){
			a_firstBitMap = ProcessBitmap(path, R.drawable.a_first,true);
		}else if(start.getFloor()==3){
			a_secondBitMap = ProcessBitmap(path, R.drawable.a_second,true);
		}else if(start.getFloor()==4){
			b_groundBitMap = ProcessBitmap(path, R.drawable.b_ground,true);
		}else if(start.getFloor()==5){
			b_firstBitMap = ProcessBitmap(path, R.drawable.b_first,true);
		}else if(start.getFloor()==6){
			b_secondBitMap = ProcessBitmap(path, R.drawable.b_second,true);
		}
	}

	private ArrayList<Region> findRoute(Region start, Region goal){
		
		closedset.clear();
		openset.clear();
		openset.add(start);
		Log.d("Message", "openset ="+openset);
	 
		ArrayList<Region> neighbourSet = new ArrayList<Region>();
		ArrayList<Region> openNeighbourSet = new ArrayList<Region>();
		
		while(!openset.isEmpty()){
			//current = getLowestGvalueRegion(openset,goal);			
			Log.d("Message", "closedset: "+closedset+", openset: "+openset);
					
			if(current!=null){
				neighbourSet = getCurrentNeighbour(current);
					if(!neighbourSet.isEmpty()){
						Log.d("neighbourSet: ",""+neighbourSet );
						Log.d("Hellooooo","Hellooooo");
						
						for(int count = 0; count<openset.size(); count++){
							for(int counter = 0; counter<neighbourSet.size(); counter++){	
								if(neighbourSet.get(counter).getRegionId().equals(openset.get(count).getRegionId())){
									openNeighbourSet.add(openset.get(count));
									Log.d("openNeighbourSet: ",""+openNeighbourSet );
								}
							}
						}
						
						if(!openNeighbourSet.isEmpty()){
							current = getShortestDistRegion(openNeighbourSet,goal);
						}else{
							current = getShortestDistRegion(openset,goal);	
						}
						
						openNeighbourSet.clear();
					}
			}else{
			
				current = getShortestDistRegion(openset,goal);	
			}
				
			

			//Log.d("Message", "current:" + current + " , goal: " + goal);
			
			if(current.getRegionId().equals(goal.getRegionId())){				
				//Log.d("Message", "Current equals goal");
				return reconstruct_path(closedset, goal);
			}
	
			//Log.d("Message", "still continue: Current not equals goal");
			Log.d("Message", "openset ="+openset);
			openset.remove(current);
			closedset.add(current);
			
			ArrayList<Region> neighbourOfCurrent = getCurrentNeighbour(current);
			Log.d("Message", "Neighbour of "+current.getRegionId()+":" + neighbourOfCurrent);			
			
			for(int count=0;count<neighbourOfCurrent.size();count++){
				
				if(closedset.contains(neighbourOfCurrent.get(count))){
					Log.d("Message", "closedset: "+closedset);
					Log.d("Message","closed set contains neighbour "+ neighbourOfCurrent.get(count) + ": "+closedset.contains(neighbourOfCurrent.get(count)));
					continue;
				}
               
				if(!openset.contains(neighbourOfCurrent.get(count))){	                	
					Log.d("Message", "closedset: "+closedset);
					Log.d("Message","closed set contains neighbour "+ neighbourOfCurrent.get(count) + ": "+closedset.contains(neighbourOfCurrent.get(count)));
                	openset.add(neighbourOfCurrent.get(count));
                }	
				
			}	
			
		}
		
		//if failure
		return reconstruct_path(closedset, goal);
		
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener{

	    public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
	        // TODO Auto-generated method stub
    	
	    	String blockA = "Block A", blockB = "Block B";
	    	
	    	//for the spinner in activity_showroute.xml	    	
		    	if (parent.getId()==R.id.blockSpinner || parent.getId()==R.id.levelSpinner){
		    		    	
			    	//Current map is displayed based on BlockSpinner and LevelSpinner
			    	if(blockSpinner.getSelectedItem().equals(blockA) && levelSpinner.getSelectedItem().equals("Ground Floor")){	
			    		//display Block A G Floor Map  
			    		showBitmap(a_groundBitMap);
			    		Toast.makeText(getApplicationContext(), "Block A Ground Floor", Toast.LENGTH_SHORT).show();
			    	}else if(blockSpinner.getSelectedItem().equals(blockA) && levelSpinner.getSelectedItem().equals("First Floor")){
			    		//display Block A First Floor Map			    		
			    		showBitmap(a_firstBitMap);
			    		Toast.makeText(getApplicationContext(), "Block A First Floor", Toast.LENGTH_SHORT).show();
			    	}else if(blockSpinner.getSelectedItem().equals(blockA) && levelSpinner.getSelectedItem().equals("Second Floor")){
			    		//display Block A Second Floor Map 
			    		showBitmap(a_secondBitMap);
			    		Toast.makeText(getApplicationContext(), "Block A Second Floor", Toast.LENGTH_SHORT).show();
			    	}else if(blockSpinner.getSelectedItem().equals(blockB) && levelSpinner.getSelectedItem().equals("Ground Floor")){
			    		//display Block B Ground Floor Map
			    		showBitmap(b_groundBitMap);
			    		Toast.makeText(getApplicationContext(), "Block B Ground Floor", Toast.LENGTH_SHORT).show();
			    	}else if(blockSpinner.getSelectedItem().equals(blockB) && levelSpinner.getSelectedItem().equals("First Floor")){
			    		//display Block B First Floor Map
			    		showBitmap(b_firstBitMap);
			    		Toast.makeText(getApplicationContext(), "Block B First Floor", Toast.LENGTH_SHORT).show();
			    	}else if(blockSpinner.getSelectedItem().equals(blockB) && levelSpinner.getSelectedItem().equals("Second Floor")){
			    		//display Block B Second Floor Map
			    		showBitmap(b_secondBitMap);
			    		Toast.makeText(getApplicationContext(), "Block B Second Floor", Toast.LENGTH_SHORT).show();
			    	}			    	   		    	
		    	}	    	
	    	
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {
	        // TODO Auto-generated method stub
	    }
	}

	

	
}
