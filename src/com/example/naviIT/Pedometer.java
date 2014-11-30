/*
 *  Pedometer - Android App
 *  Copyright (C) 2009 Levente Bagi
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.example.naviIT;

import java.util.ArrayList;
import com.example.pedometer.PedometerSettings;
import com.polites.android.GestureImageView;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Pedometer extends Activity implements SensorEventListener{
	private static final String TAG = "Pedometer";
    private SharedPreferences mSettings;
    SharedPreferences startngoal;
    private PedometerSettings mPedometerSettings;
    //private Utils mUtils;
    
    private TextView mStepValueView;
    private TextView currentRegionView;
    /**private TextView mPaceValueView;
    private TextView mDistanceValueView;
    private TextView mSpeedValueView;
    private TextView mCaloriesValueView;
    TextView mDesiredPaceView;**/
    private int mStepValue;
    /**private int mPaceValue;
    private float mDistanceValue;
    private float mSpeedValue;
    private int mCaloriesValue;
    private float mDesiredPaceOrSpeed;**/
    private int mMaintain;
    private boolean mIsMetric, isStepServiceUnbind;
    private float mMaintainInc;
    private boolean mQuitting = false; // Set when user selected Quit from menu, can be used by onPause, onStop, onDestroy
    
    // device sensor manager
    private SensorManager mSensorManager;

    SharedPreferences  mPrefs;
    Editor prefsEditor;
    SharedPreferences.Editor ed;
    Room goalRoom;
    Room mygetGoalRoom;
    GestureImageView view;
    TextView tvHeading;
    float degree;
    float lowerDegree, upperDegree;
    Path currentPath;
    ArrayList<Path> list;
    ArrayList<Region> regionList;
    ArrayList<Path> neighbourList;
    Region start, goal, subGoal = null,subStart = null, current = null; 
    Bitmap a_groundBitMap, a_firstBitMap, a_secondBitMap, b_groundBitMap, b_firstBitMap, b_secondBitMap ;
    Boolean isStart=false, isGoal=false, chgMap=false, goUp=false, goDown=false, repeat=false, goToBlockA=false, goToBlockB=false, isInDegreeRange=false;
    ArrayList<Region> closedset = new ArrayList<Region>();
	ArrayList<Region> openset = new ArrayList<Region>();
	String selectedStart,selectedGoal;
    TextView directionInfo;

    /**
     * True, when service is running.
     */
    private boolean mIsRunning;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "[ACTIVITY] onCreate");
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_pedometer);
        
        getActionBar().setTitle("Online Navigation");
        
        mPrefs = getPreferences(MODE_PRIVATE);
        prefsEditor = mPrefs.edit();
        
        startngoal = getSharedPreferences("startngoal",0);
        ed = startngoal.edit();
              
        view = new GestureImageView(Pedometer.this);
        
        directionInfo = (TextView) findViewById(R.id.directionInfo);

      //to solve pedometer keep running <Bug>
        //Pedometer.this.getSharedPreferences("YOUR_PREFS", 0).edit().clear().commit();                 
       
        isStepServiceUnbind = false;
        mStepValue = 0;
       // mPaceValue = 0;
            
        MySQLiteHelper db = new MySQLiteHelper(this);
               
        /**
         * CRUD Operations
         * */

        
        // get all paths
        list = db.getAllPaths();
        
        // get all regions
        regionList = db.getAllRegions();
        
        db.close();
        
        
        //start = regionList.get(4); //1_3
        //goal = regionList.get(4); //1_3
        
        //need to set start n goal : havent do
        //start = regionList.get(18); //2_1
        //goal = regionList.get(4); //1_3
        
        //start = regionList.get(39); //3_1
        //goal = regionList.get(4); //1_3
        
        //start = regionList.get(66); //4_0
        //goal = regionList.get(67); //4_1
        
        selectedStart = "1_3";
        
        Intent intent = getIntent();
        if(intent.getExtras() != null){
        	selectedGoal = intent.getStringExtra("selectedGoal");
            
            ed.putString("start", selectedStart); 
        	ed.putString("goal", selectedGoal);
        	ed.commit();
        	Log.d("saveinsharedpreference", "Saved selected start: "+selectedStart+", Saved Selected goal: "+selectedGoal);
        }
             
        
        Log.d("1.selectedGoal: ", "selectedGoal: "+selectedGoal);
        
       
       
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        
       // mUtils = Utils.getInstance();
        
    }
    
    @Override
    protected void onStart() {
        Log.i(TAG, "[ACTIVITY] onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "[ACTIVITY] onResume");
        super.onResume();    
        
        // for the system's orientation sensor registered listeners
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);
        
        mSettings = PreferenceManager.getDefaultSharedPreferences(this);
        mPedometerSettings = new PedometerSettings(mSettings);
        
        startngoal = getSharedPreferences("startngoal", 0);
        String savedStart = startngoal.getString("start", "");
        String savedGoal = startngoal.getString("goal", "");
        
        if(selectedStart==null){
        	selectedStart = savedStart;
        	Log.d("saved", "savedStart: "+savedStart+", selectedStart: "+selectedStart);
        }
        
        Log.d("2.selectedGoal: ", "selectedGoal: "+selectedGoal);
        
        if(selectedGoal==null){
        	selectedGoal = savedGoal;
        	Log.d("saved", "savedGoal: "+savedGoal+", selectedGoal: "+selectedGoal);
        }
        
        for(int counterA = 0; counterA < regionList.size(); counterA++){
        	if(regionList.get(counterA).getRegionId().equals(selectedStart)){
        		start = regionList.get(counterA);
        		 Log.d("start: ", "start: "+start);
        	}
        	
        	if(regionList.get(counterA).getRegionId().equals(selectedGoal)){
        		goal = regionList.get(counterA);
        		 Log.d("goal: ", "goal: "+goal);
        	}
        } 
        
        //start = regionList.get(101); //5_1
        //goal = regionList.get(100); //5_0
        
        for(int cnt=0; cnt<list.size(); cnt++){
        	if(list.get(cnt).getCurrentRegion().equals(start.getRegionId())){
        		currentPath = list.get(cnt);//detect current region (now is 3_1)
        	}
        }
        
        Log.d("check start and goal", "1st check -Before checkformultiple block, start: "+start+", goal: "+goal);
        checkForMultipleBlock(start, goal);
        
        
        
        //mUtils.setSpeak(mSettings.getBoolean("speak", false));
        
        // Read from preferences if the service was running on the last onPause
        mIsRunning = mPedometerSettings.isServiceRunning();
        
        // Start the service if this is considered to be an application start (last onPause was long ago)
        if (!mIsRunning && mPedometerSettings.isNewStart()) {
            startStepService();
            bindStepService();
        }
        else if (mIsRunning) {
            bindStepService();
        }
        
        mPedometerSettings.clearServiceRunning();

        mStepValueView     = (TextView) findViewById(R.id.step_value);
        currentRegionView = (TextView) findViewById(R.id.current_region);
        // TextView that will tell the user what degree is he heading
        tvHeading = (TextView) findViewById(R.id.tvHeading);

        /**mPaceValueView     = (TextView) findViewById(R.id.pace_value);
        mDistanceValueView = (TextView) findViewById(R.id.distance_value);
        mSpeedValueView    = (TextView) findViewById(R.id.speed_value);
        mCaloriesValueView = (TextView) findViewById(R.id.calories_value);
        mDesiredPaceView   = (TextView) findViewById(R.id.desired_pace_value);**/

        mIsMetric = mPedometerSettings.isMetric();
        /**((TextView) findViewById(R.id.distance_units)).setText(getString(
                mIsMetric
                ? R.string.kilometers
                : R.string.miles
        ));
        ((TextView) findViewById(R.id.speed_units)).setText(getString(
                mIsMetric
                ? R.string.kilometers_per_hour
                : R.string.miles_per_hour
        ));
        
        mMaintain = mPedometerSettings.getMaintainOption();
        ((LinearLayout) this.findViewById(R.id.desired_pace_control)).setVisibility(
                mMaintain != PedometerSettings.M_NONE
                ? View.VISIBLE
                : View.GONE
            );**/
        if (mMaintain == PedometerSettings.M_PACE) {
            mMaintainInc = 5f;
            //mDesiredPaceOrSpeed = (float)mPedometerSettings.getDesiredPace();
        }
        else 
        if (mMaintain == PedometerSettings.M_SPEED) {
            //mDesiredPaceOrSpeed = mPedometerSettings.getDesiredSpeed();
            mMaintainInc = 0.1f;
        }
        /**Button button1 = (Button) findViewById(R.id.button_desired_pace_lower);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mDesiredPaceOrSpeed -= mMaintainInc;
                //mDesiredPaceOrSpeed = Math.round(mDesiredPaceOrSpeed * 10) / 10f;
                displayDesiredPaceOrSpeed();
                //etDesiredPaceOrSpeed(mDesiredPaceOrSpeed);
            }
        });
        Button button2 = (Button) findViewById(R.id.button_desired_pace_raise);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //mDesiredPaceOrSpeed += mMaintainInc;
                //mDesiredPaceOrSpeed = Math.round(mDesiredPaceOrSpeed * 10) / 10f;
                displayDesiredPaceOrSpeed();
                //setDesiredPaceOrSpeed(mDesiredPaceOrSpeed);
            }
        });
        if (mMaintain != PedometerSettings.M_NONE) {
            ((TextView) findViewById(R.id.desired_pace_label)).setText(
                    mMaintain == PedometerSettings.M_PACE
                    ? R.string.desired_pace
                    : R.string.desired_speed
            );
        }**/
        
        
        displayDesiredPaceOrSpeed();
    }
    
    private void displayDesiredPaceOrSpeed() {
        if (mMaintain == PedometerSettings.M_PACE) {
            //mDesiredPaceView.setText("" + (int)mDesiredPaceOrSpeed);
        }
        else {
            //mDesiredPaceView.setText("" + mDesiredPaceOrSpeed);
        }
    }
    
    @Override
    protected void onPause() {
        Log.i(TAG, "[ACTIVITY] onPause");
        
        // to stop the listener and save battery
        mSensorManager.unregisterListener(this);
        
        if (mIsRunning) {
            unbindStepService();
        }
        if (mQuitting) {
            mPedometerSettings.saveServiceRunningWithNullTimestamp(mIsRunning);
        }
        else {
            mPedometerSettings.saveServiceRunningWithTimestamp(mIsRunning);
        }

        super.onPause();
        savePaceSetting();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "[ACTIVITY] onStop");
        super.onStop();
    }

    protected void onDestroy() {
        Log.i(TAG, "[ACTIVITY] onDestroy");
        super.onDestroy();
    }
    
    protected void onRestart() {
        Log.i(TAG, "[ACTIVITY] onRestart");
        super.onDestroy();
    }

   /** private void setDesiredPaceOrSpeed(float desiredPaceOrSpeed) {
        if (mService != null) {
            if (mMaintain == PedometerSettings.M_PACE) {
                mService.setDesiredPace((int)desiredPaceOrSpeed);
            }
            else
            if (mMaintain == PedometerSettings.M_SPEED) {
                mService.setDesiredSpeed(desiredPaceOrSpeed);
            }
        }
    }**/
    
    private void savePaceSetting() {
        //mPedometerSettings.savePaceOrSpeedSetting(mMaintain, mDesiredPaceOrSpeed);
    }

    private StepService mService;
    
    private ServiceConnection mConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName className, IBinder service) {
            mService = ((StepService.StepBinder)service).getService();

            mService.registerCallback(mCallback);
            mService.reloadSettings();
            
        }

        public void onServiceDisconnected(ComponentName className) {
            mService = null;
        }
    };
    

    private void startStepService() {
        if (! mIsRunning) {
            Log.i(TAG, "[SERVICE] Start");
            mIsRunning = true;
            startService(new Intent(Pedometer.this,
                    StepService.class));
        }
    }
    
    private void bindStepService() {
        Log.i(TAG, "[SERVICE] Bind");
        bindService(new Intent(Pedometer.this, 
                StepService.class), mConnection, Context.BIND_AUTO_CREATE + Context.BIND_DEBUG_UNBIND);
    }

    private void unbindStepService() {
        Log.i(TAG, "[SERVICE] Unbind");
        unbindService(mConnection);
        isStepServiceUnbind = true;
    }
    
    private void stopStepService() {
        Log.i(TAG, "[SERVICE] Stop");
        if (mService != null) {
            Log.i(TAG, "[SERVICE] stopService");
            stopService(new Intent(Pedometer.this,
                  StepService.class));
        }
        mIsRunning = false;
    }
    
    private void resetValues(boolean updateDisplay) {
        if (mService != null && mIsRunning) {
            mService.resetValues();                    
        }
        else {
            mStepValueView.setText("0");
            /**mPaceValueView.setText("0");
            mDistanceValueView.setText("0");
            mSpeedValueView.setText("0");
            mCaloriesValueView.setText("0");**/
            SharedPreferences state = getSharedPreferences("state", 0);
            SharedPreferences.Editor stateEditor = state.edit();
            if (updateDisplay) {
                stateEditor.putInt("steps", 0);
                stateEditor.putInt("pace", 0);
                stateEditor.putFloat("distance", 0);
                stateEditor.putFloat("speed", 0);
                stateEditor.putFloat("calories", 0);
                stateEditor.commit();
            }
        }
    }

    private static final int MENU_SETTINGS = 8;
    private static final int MENU_QUIT     = 9;

    private static final int MENU_PAUSE = 1;
    private static final int MENU_RESUME = 2;
    private static final int MENU_RESET = 3;
    
    /* Creates the menu items */
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (mIsRunning) {
            menu.add(0, MENU_PAUSE, 0, "Pause")
            .setIcon(android.R.drawable.ic_media_pause)
            .setShortcut('1', 'p');
        }
        else {
            menu.add(0, MENU_RESUME, 0, "Resume")
            .setIcon(android.R.drawable.ic_media_play)
            .setShortcut('1', 'p');
        }
        menu.add(0, MENU_RESET, 0, "Reset")
        .setIcon(android.R.drawable.ic_menu_close_clear_cancel)
        .setShortcut('2', 'r');
        menu.add(0, MENU_QUIT, 0, "Quit")
        .setIcon(android.R.drawable.ic_lock_power_off)
        .setShortcut('9', 'q');
        return true;
    }

    /* Handles item selections */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_PAUSE:
                unbindStepService();
                stopStepService();
                return true;
            case MENU_RESUME:
                startStepService();
                bindStepService();
                return true;
            case MENU_RESET:
                resetValues(true);
                return true;
            case MENU_QUIT:
                resetValues(false);
                if(isStepServiceUnbind == false){
                	unbindStepService();
                }               
                stopStepService();
                ed.clear();
	            ed.commit();
                mQuitting = true;
                finish();
                return true;
        }
        return false;
    }
 
    // TODO: unite all into 1 type of message
    private StepService.ICallback mCallback = new StepService.ICallback() {
        public void stepsChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(STEPS_MSG, value, 0));
        }
        public void paceChanged(int value) {
            mHandler.sendMessage(mHandler.obtainMessage(PACE_MSG, value, 0));
        }
        public void distanceChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(DISTANCE_MSG, (int)(value*1000), 0));
        }
        public void speedChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(SPEED_MSG, (int)(value*1000), 0));
        }
        public void caloriesChanged(float value) {
            mHandler.sendMessage(mHandler.obtainMessage(CALORIES_MSG, (int)(value), 0));
        }
    };
    
    private static final int STEPS_MSG = 1;
    private static final int PACE_MSG = 2;
    private static final int DISTANCE_MSG = 3;
    private static final int SPEED_MSG = 4;
    private static final int CALORIES_MSG = 5;
    
    
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case STEPS_MSG:
                	mStepValue = (int)msg.arg1;
                    mStepValueView.setText("" + mStepValue);
                    
                    neighbourList = new ArrayList<Path>();
                    
                    currentRegionView.setText(currentPath.getCurrentRegion());                    
               
                    for(int count=0; count<list.size(); count++){
                    	if(currentPath.getCurrentRegion().equals(list.get(count).getCurrentRegion())){                                       	
                    			neighbourList.add(list.get(count));		
                    			
                    	}
                    }	
	            
                    Log.d("NeighbourList: ", " "+neighbourList);
                    
            		for(int count2=0; count2<neighbourList.size(); count2++){	
            			
            			if(neighbourList.get(count2).getDegree()-30<0){
            				lowerDegree = 360 + (degree-30);
            				upperDegree = neighbourList.get(count2).getDegree()+30;
            				
            				isInDegreeRange = ((degree > lowerDegree && degree < 360) || ((degree > 0 || degree == 0) && degree < upperDegree) );
            				
            			}else if(neighbourList.get(count2).getDegree()+30>360){
            				lowerDegree = neighbourList.get(count2).getDegree()-30;
            				upperDegree = (degree+30)-360;
            				
            				isInDegreeRange = ((degree > lowerDegree && degree < 360) || ((degree > 0 || degree == 0) && degree < upperDegree) );

            			}else{
            				lowerDegree = neighbourList.get(count2).getDegree()-30;
            				upperDegree = neighbourList.get(count2).getDegree()+30;
            				isInDegreeRange = (degree > lowerDegree && degree < upperDegree);
            			}
            			
                		if(mStepValue>neighbourList.get(count2).getStepCount() && isInDegreeRange){
                        	//String nextRegion = currentPath.getNextRegion();
                			//Toast.makeText(Pedometer.this, "Match value", Toast.LENGTH_SHORT).show();
                			
                			for(int count3=0; count3<list.size(); count3++){	
	                         	if(neighbourList.get(count2).getNextRegion().equals(list.get(count3).getCurrentRegion())){
	                    	
	                         		//Toast.makeText(Pedometer.this, "Chg current with next", Toast.LENGTH_SHORT).show();
	                         		currentPath = list.get(count3);
	                         		
	                         		for(int count4=0; count4<regionList.size(); count4++){
	                         			if(list.get(count3).getCurrentRegion().equals("1a") || list.get(count3).getCurrentRegion().equals("1b") || list.get(count3).getCurrentRegion().equals("1c")){
	                         				checkForMultipleBlock(regionList.get(0),goal);
	                         			}else if(list.get(count3).getCurrentRegion().equals("2a") || list.get(count3).getCurrentRegion().equals("2b") || list.get(count3).getCurrentRegion().equals("2c")){
	                         				checkForMultipleBlock(regionList.get(16),goal);
	                         			}
	                         			else if(regionList.get(count4).getRegionId().equals(list.get(count3).getCurrentRegion())){
	                         				checkForMultipleBlock(regionList.get(count4),goal);
	                         			}
	                         		}                     		
	                         		
	                         		resetValues(true);
	                         		break;
	                 				//currentRegionView.setText(currentPath.getCurrentRegion());
	                         	}
                			}
                		}     
                	}
            		
                    //int threshold=10;
                    /*
                    if(mStepValue>10 && mStepValue<20 && degree>0 && degree<90){
                    	current = "B";
                    	currentRegionView.setText(current);
                    	
                    }                  
                    
                    if(mStepValue>20 && mStepValue<30 && degree>90 && degree<180){
                    	current = "C";
                    	currentRegionView.setText(current);
                    }
                    */
                    
                    break;
                /**case PACE_MSG:
                    mPaceValue = msg.arg1;
                    if (mPaceValue <= 0) { 
                        mPaceValueView.setText("0");
                    }
                    else {
                        mPaceValueView.setText("" + (int)mPaceValue);
                    }
                    break;
                case DISTANCE_MSG:
                    mDistanceValue = ((int)msg.arg1)/1000f;
                    if (mDistanceValue <= 0) { 
                        mDistanceValueView.setText("0");
                    }
                    else {
                        mDistanceValueView.setText(
                                ("" + (mDistanceValue + 0.000001f)).substring(0, 5)
                        );
                    }
                    break;
                case SPEED_MSG:
                    mSpeedValue = ((int)msg.arg1)/1000f;
                    if (mSpeedValue <= 0) { 
                        mSpeedValueView.setText("0");
                    }
                    else {
                        mSpeedValueView.setText(
                                ("" + (mSpeedValue + 0.000001f)).substring(0, 4)
                        );
                    }
                    break;
                case CALORIES_MSG:
                    mCaloriesValue = msg.arg1;
                    if (mCaloriesValue <= 0) { 
                        mCaloriesValueView.setText("0");
                    }
                    else {
                        mCaloriesValueView.setText("" + (int)mCaloriesValue);
                    }
                    break;**/
                default:
                    super.handleMessage(msg);
            }
        }
        
    };

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		 // get the angle around the z-axis rotated
		degree = Math.round(event.values[0]);
		tvHeading.setText("Heading: " + Float.toString(degree) + " degrees");

		/*   can use map as the rotate image 
		 *     
		// create a rotation animation (reverse turn degree degrees)
		RotateAnimation ra = new RotateAnimation(currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
		// how long the animation will take place
		ra.setDuration(210);
		// set the animation after the end of the reservation status
		ra.setFillAfter(true);
		// Start the animation
		image.startAnimation(ra);
		currentDegree = -degree;
		*/

	}
	
	//checkforBlock
		private void checkForMultipleBlock(Region start, Region goal){
			subGoal = goal;	
			
				Log.d("Once enter checkformultipleBlock", "start: "+start+", goal: "+goal);
						
				//if start and goal at different block
				if(!start.getBlock().equals(goal.getBlock())){
					
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
				
				//maybe is here got prob - from one blk to another	
				checkForMultipleFloor(start,subGoal);
				//checkForMultipleFloor(subStart,goal);	
			}else{
				checkForMultipleFloor(start,goal);
				Log.d("check start and goal", "After checkformultiple block, start: "+start+", goal: "+goal);
			}
			
		}
    
		private void checkForMultipleFloor(Region start, Region goal){
			subGoal = goal;	
			
			Log.d("check start and goal", "Before checkformultiple floor, start: "+start+", goal: "+goal);
			
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
						//checkForMultipleFloor(subStart,goal);
						Log.d("Message", "check for multiple floor again");
					}else{
						//ArrayList<Region> path =  findRoute(subStart,goal);
						//Log.d("Message", "substart: " + subStart +"goal:"+ goal +",2.Route found: "+path);
						//a_secondBitMap = ProcessBitmap(path, R.drawable.a_second, true);			
						//selectBitMapToProcess(subStart,path);
					}
					
				}else{
					//if start n goal at same floor
					ArrayList<Region> path =  findRoute(start,goal);
					Log.d("Message", "start: " + start +"goal:"+ goal +", same floor Route found: "+path);
					selectBitMapToProcess(start,path);		
					/*
					if(start.getBlock().equals("A") && start.getFloor()==1){
						showBitmap(a_groundBitMap);
					}else if(start.getBlock().equals("A") && start.getFloor()==2){
						showBitmap(a_firstBitMap);
					}else if(start.getBlock().equals("A") && start.getFloor()==3){
						showBitmap(a_secondBitMap);
					}else if(start.getBlock().equals("B") && start.getFloor()==4){
						showBitmap(b_groundBitMap);
					}else if(start.getBlock().equals("B") && start.getFloor()==5){
						showBitmap(b_firstBitMap);
					}else if(start.getBlock().equals("B") && start.getFloor()==6){
						showBitmap(b_secondBitMap);
					}
					*/
				}		
				
				if(start.getBlock().equals("A") && start.getFloor()==1){
					showBitmap(a_groundBitMap);
					directionInfo.setText("Block A, Ground Floor");
				}else if(start.getBlock().equals("A") && start.getFloor()==2){
					showBitmap(a_firstBitMap);
					directionInfo.setText("Block A, First Floor");
				}else if(start.getBlock().equals("A") && start.getFloor()==3){
					showBitmap(a_secondBitMap);
					directionInfo.setText("Block A, Second Floor");
				}else if(start.getBlock().equals("B") && start.getFloor()==4){
					showBitmap(b_groundBitMap);
					directionInfo.setText("Block B, Ground Floor");
				}else if(start.getBlock().equals("B") && start.getFloor()==5){
					showBitmap(b_firstBitMap);
					directionInfo.setText("Block B, First Floor");
				}else if(start.getBlock().equals("B") && start.getFloor()==6){
					showBitmap(b_secondBitMap);
					directionInfo.setText("Block B, Second Floor");

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
			   bm1 = BitmapFactory.decodeResource(getApplicationContext().getResources(), drawable);	
			   
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
		/*
		public Room getRoom(){
	    	Gson gson = new Gson();
	        String json = mPrefs.getString("goalRoom", "");
	        Room obj = gson.fromJson(json, Room.class);
	        return obj ;
	    }
	    
	    public void saveRoom(Room goalRoom){
	    	 Gson gson = new Gson();
	         String json = gson.toJson(goalRoom);
	         prefsEditor.putString("goalRoom", json);
	         prefsEditor.commit();
	    }
	    */
	    
	    @Override
	    public void onBackPressed() {
	            super.onBackPressed();
	            
	            ed.clear();
	            ed.commit();
	            
	            resetValues(false);
                if(isStepServiceUnbind == false){
                	unbindStepService();
                }               
                stopStepService();
                mQuitting = true;
                this.finish();
	    }
	    
}
