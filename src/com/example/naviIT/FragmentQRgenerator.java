package com.example.naviIT;
 
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.naviIT.R;
 
public class FragmentQRgenerator   extends Fragment {
 
	public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    private ViewPager viewPager;
    
    private EditText editData;
	  private Button btnGenerate, btnSave, btnEmail;
	  private ImageView qrCode;
	
	  String strData;
	  int qrCodeDimention;
	  protected Bitmap bitmap;
	  protected String email;
	  protected boolean checkSend;
	  
	//load spinner()
	InputStream is=null;
	String result=null;
	String line=null;

	String[] username;  
	
	final List<String> list1 = new ArrayList<String>();
	
	String blockName;
	
	private ArrayList<Block> blockList;
	
	String[] lid;
	String[] locationName;
	
	String finalLocationName;
	AlertDialog alertLocation;
    
   

    public FragmentQRgenerator()
    {
  	 
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

          //View view=inflater.inflate(R.layout.fragment_layout_two,container, false);

         
          //return view;
  	  
  	  View rootView = inflater.inflate(R.layout.fullscreen_pager, container, false);
		 	
		 	boolean ori = true;
		 	
		viewPager = (ViewPager)rootView. findViewById(R.id.pager);
		
		View dialogView = getActivity().getLayoutInflater()
					.inflate(R.layout.choose_box_dialog, null, false);
		
		
		final View dialogView2 = getActivity().getLayoutInflater()
					.inflate(R.layout.choose_location_dialog, null, false);
		
		final CheckBox checkA = (CheckBox)dialogView.findViewById(R.id.checkA);
		final CheckBox checkB = (CheckBox)dialogView.findViewById(R.id.checkB);
		
		final EditText editBlockName = (EditText)dialogView2.findViewById(R.id.editBlockName);
		final Spinner spinnerBlockName = (Spinner)dialogView2.findViewById(R.id.spinnerBlockName);
		final TextView labelBlockName = (TextView)dialogView2.findViewById(R.id.labelBlockName);
		final Button btnConfirmLocation = (Button)dialogView2.findViewById(R.id.btnConfirmLocation);
		
		final ProgressBar progressBarInfo = (ProgressBar)dialogView2.findViewById(R.id.progressBarInfo);
		
		editBlockName.setVisibility(View.INVISIBLE);
		spinnerBlockName.setVisibility(View.INVISIBLE);
		btnConfirmLocation.setVisibility(View.INVISIBLE);
		labelBlockName.setText("Loading... ...");
		
		
		
		final AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(getActivity());
		alertDialog1.setTitle("Choose a Block");
			alertDialog1.setView(dialogView);
			alertDialog1.setPositiveButton("Ok",new DialogInterface.OnClickListener() {
			

			public void onClick(DialogInterface dialog, int which) {
				
					if(checkA.isChecked() && !checkB.isChecked()){
							
						
							blockName = "a";
							
							dialog.cancel();
							
							loadSpinner2();
							
							alertLocation = new AlertDialog.Builder(getActivity()).create();
									alertLocation.setTitle("Choose a Location");
									alertLocation.setView(dialogView2);
							/**.setPositiveButton("Ok", new DialogInterface.OnClickListener(){

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									InfoPlanSettingPager adapter = new InfoPlanSettingPager(getActivity(), finalLocationName);
				        			viewPager.setAdapter(adapter);
								}
								
							})*/alertLocation.show();
					
							
							
						}
						
						if(checkB.isChecked() && !checkA.isChecked()){
							Toast.makeText(getActivity(), "Under Construction", Toast.LENGTH_LONG).show();
						
						}
						
						if(checkA.isChecked() && checkB.isChecked()){
							Toast.makeText(getActivity(), "Please choose only one.", Toast.LENGTH_LONG).show();
						}
						
						if(!checkA.isChecked() && !checkB.isChecked()){
							Toast.makeText(getActivity(), "Please choose one block to access.", Toast.LENGTH_LONG).show();
						}
						
			}

			private void loadSpinner2() {
				class BackgroundSpinner extends AsyncTask<String, Void, String>{
					 ProgressDialog progress;
					 
					 @Override
					    protected void onPreExecute() {
					        super.onPreExecute();
					        /**progress = new ProgressDialog(getActivity());
					        progress.setMessage("Loading... ...");
					        progress.setCancelable(false);
					        progress.show();*/
					        
					       progressBarInfo.setActivated(true);
					 
					    }
					 

					@Override
					protected String doInBackground(String... params) {
						// TODO Auto-generated method stub
						try
						{
							
							HttpClient httpclient = new DefaultHttpClient(); 
						    String postURL = "http://navifsktm.comule.com/get_block_location.php";
						    HttpPost httppost = new HttpPost(postURL); 
						    List<NameValuePair> params2 = new ArrayList<NameValuePair>();
				            params2.add(new BasicNameValuePair("block_name", blockName));
				            Log.i("Block name in tester", blockName);
						    UrlEncodedFormEntity ent = new UrlEncodedFormEntity(params2,HTTP.UTF_8);
				            httppost.setEntity(ent);
				            HttpResponse responsePOST = httpclient.execute(httppost);  
				            HttpEntity resEntity = responsePOST.getEntity();
						   
						    is = resEntity.getContent();
						    Log.i("Spinner", "connection success ");

						} catch (Exception e) {
							e.printStackTrace();
							Log.i("Error 1", e.toString());
							Toast.makeText(getActivity(), "Invalid IP Address",Toast.LENGTH_LONG).show();
						}

						try
						{
							BufferedReader reader = new BufferedReader(new InputStreamReader(
				                    is, "UTF-8"), 8);
				            StringBuilder sb = new StringBuilder();
				            String line = null;
				            while ((line = reader.readLine()) != null) {
				                sb.append(line + "\n");
				            }
				            is.close();
				           
							is.close();
							result = sb.toString();
							Log.i("Result", result);
						}
						catch(Exception e)
						{
							Log.i("Error 2", e.toString());
						}    
						   
						try
						{
				                JSONObject jsonObj = new JSONObject(result);
				                if (jsonObj != null) {
				                    JSONArray blockArray = jsonObj
				                            .getJSONArray("block");        
				                    
				                    lid = new String[(blockArray).length()];
				                    locationName = new String[(blockArray.length())];
				 
				                    for (int i = 0; i < blockArray.length(); i++) {
				                        JSONObject blockObj = (JSONObject) blockArray.get(i);
				                        Block block = new Block(blockObj.getInt("lid"),
				                                blockObj.getString("display_location_name"));
				                        
				                        Log.i("Lid", "lid = " + blockObj.getInt("lid"));
				                        Log.i("Location name", blockObj.getString("display_location_name"));
				                        
				                        lid[i] = Integer.toString(blockObj.getInt("lid"));
				                        locationName[i] = blockObj.getString("display_location_name");
				                        
				                        //blockList.add(block);
				                    }
				                }
				 
				            }
						  
							//getSpinner();
						                                                               
						
							catch(JSONException e)
							{

								Log.e("Error 3", e.toString());

						}
						return result;
					}
					protected void onPostExecute(String result) {
					    super.onPostExecute(result);
					    /**if (progress.isShowing())
					    	progress.dismiss();*/
					   getSpinner2();
					   progressBarInfo.setActivated(false);
					   
					} 
				 }
				 
				 BackgroundSpinner background = new BackgroundSpinner();
				 background.execute();
				
			}
			
			private void getSpinner2(){
				List<String> labels = new ArrayList<String>();
			     
				 
			    for (int i = 0; i < locationName.length; i++) {
			        labels.add(locationName[i]);
			    }
			 
			    
			    
				ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_spinner_item, labels);
				dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinnerBlockName.setAdapter(dataAdapter1);
				
				progressBarInfo.setVisibility(View.INVISIBLE);
				editBlockName.setVisibility(View.VISIBLE);
				spinnerBlockName.setVisibility(View.VISIBLE);
				
				labelBlockName.setText("Available locations: ");

				spinnerBlockName.setOnItemSelectedListener(new OnItemSelectedListener()
				{

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
						// TODO Auto-generated method stub
						editBlockName.setText(arg0.getSelectedItem().toString());
						finalLocationName = arg0.getSelectedItem().toString(); 
						
						btnConfirmLocation.setVisibility(View.VISIBLE);
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub
				
					}

				}); 
				
				btnConfirmLocation.setOnClickListener(new Button.OnClickListener(){

					@Override
					public void onClick(View v) {
						QRCodeGeneratorPager adapter = new QRCodeGeneratorPager(getActivity(), finalLocationName);
		    			viewPager.setAdapter(adapter);
						
		    			alertLocation.dismiss();
					}
					
				});
			}
			
			
        
	})
		//.setNegativeButton("Cancel",null)
		.show();
	 	
			return rootView;
			
    }

}