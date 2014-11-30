package com.example.naviIT;


import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;








import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



































import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.CursorJoiner.Result;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QRCodeGeneratorPager extends PagerAdapter {
 
    private Activity _activity;
    private ArrayList<Integer> imageLocationA;
    private LayoutInflater inflater;
    private String locationName;
    
    int valueX, valueY;
    int finalPosition;
   
    private static String KEY_SUCCESS = "success";
	private static String KEY_ID = "lid";
	private static String KEY_NAME = "location_name";
	private static String KEY_DISPLAY_NAME = "display_location_name";
	private static String KEY_DESCRIPTION = "location_description";
	private static String KEY_LOCATION ="location";
	
	private String location_name, location_display_name, location_description;
	private String id, name, description; //final 
	
	private JSONArray location;
	
	JSONParser jparser = new JSONParser();
	private ProgressDialog progress;
	
	private static String locationURL = "http://navifsktm.comule.com/get_location_details.php";
	//private static String uploadURL = "http://navifsktm.comule.com/upload_qr_server.php";
	private static String uploadURL = "http://10.10.117.25/um/images/upload.php";
	
    int imageSize;
    int currentSize;
	
    Handler myHandler;
	Runnable myRunner;
	
	JSONParser jsonParser = new JSONParser();
	
	private ProgressDialog pDialog;
	private TextView editData;
	private Button btnGenerate;
	private Button btnSave;
	private Button btnSaveQRDb;
	private Button btnEmail;
	private ImageView qrCode;
	
	String strData;
	int qrCodeDimention;
	protected Bitmap bitmap;
	protected String email;
	protected boolean checkSend;
	protected String finalAddress;
	private String root;
	public InputStream inputStream;
    
    public QRCodeGeneratorPager(Activity activity, String locationName) {
        this._activity = activity;
        this.location_name = locationName;
        imageLocationA = new ArrayList(100);
        imageLocationA.add(R.drawable.background_a);
        imageLocationA.add(R.drawable.images_3);
    }
 
    @Override
    public int getCount() {
    	return 1;
    }
 
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ScrollView) object);
    }
     
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
    	
       
    	
        inflater = (LayoutInflater) _activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.fragment_second, container, false);
        
   		qrCodeDimention = 500;
		checkSend = false;
		
		Log.i("location name in qr code", "location name = " + location_name);
		
		editData = (TextView)viewLayout. findViewById(R.id.editData);
		btnGenerate = (Button)viewLayout. findViewById(R.id.btnGenerate);
		btnSave = (Button)viewLayout. findViewById(R.id.btnSaveQRCode);
		btnSaveQRDb = (Button)viewLayout.findViewById(R.id.btnSaveQRDb);
		btnEmail = (Button)viewLayout. findViewById(R.id.btnEmailQRCode);
		qrCode = (ImageView)viewLayout. findViewById(R.id.qrCode);
		
		btnSave.setText("Save to SD card");
		
		if(location_name != null){
			editData.setText("Location name = " + location_name);
		}
		
		btnSave.setVisibility(View.INVISIBLE);
		btnEmail.setVisibility(View.INVISIBLE);
		btnSaveQRDb.setVisibility(View.INVISIBLE);
		//to be enable internet to be run in main thread
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		
		btnGenerate.setOnClickListener(new Button.OnClickListener(){


			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new LoadQRDetails().execute();
				
			}
			
		});
		
		btnSave.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				saveToFolder(strData, false);
				
			}
			
		});
		
		btnSaveQRDb.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				saveToFolder(strData, true);
				//new LoadQRDetails().execute();
				
			}
			
		});
		
		btnEmail.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				saveToFolder("temp", false);
				getEmailAddress();
			}
			
		});
       
		
  
        
  
        ((ViewPager) container).addView(viewLayout);
  
        return viewLayout;
    }
    

		protected void getEmailAddress() {
    		final View dialogView = _activity.getLayoutInflater()
					.inflate(R.layout.enter_email_address, null, false);
    		
    		final EditText editEmailAddress = (EditText)dialogView.findViewById(R.id.editEmailAddress);
    		final Button btnConfirmEmail = (Button)dialogView.findViewById(R.id.btnConfirmEmail); 
    		
    		btnConfirmEmail.setEnabled(false);
    		
    		final AlertDialog alertEmail = new AlertDialog.Builder(_activity).create();
    		alertEmail.setTitle("Recepient's email address");
			alertEmail.setView(dialogView);
			alertEmail.show();
    		
    		editEmailAddress.setOnKeyListener(new EditText.OnKeyListener(){

				@Override
				public boolean onKey(View v, int keyCode, KeyEvent event) {
					if(isEmailValid(editEmailAddress.getText().toString())){
						finalAddress = editEmailAddress.getText().toString();
						btnConfirmEmail.setEnabled(true);
					}else{
						btnConfirmEmail.setEnabled(false);
						Toast.makeText(_activity, "invalid email", Toast.LENGTH_SHORT).show();
					}
					return false;
				}
    			
    		});
    		
    		btnConfirmEmail.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View v) {

					EmailToMe(finalAddress);
					alertEmail.dismiss();
				}
    			
    		});
    		
    		
			
			
		
	}

		protected boolean isEmailValid(String email) {
			boolean isValid = false;

		    //valid format
		    String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
		    CharSequence inputStr = email;

		    //compile pattern
		    Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
		    
		    //match pattern with the email address entered by user
		    Matcher matcher = pattern.matcher(inputStr);
		    if (matcher.matches()) {
		        isValid = true;
		    }
		    return isValid;
		}

		protected void EmailToMe(String finalAddress) {
    		
    		
    		Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("lkatherine739@gmail.com", "17011991");
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("lkatherine739@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(finalAddress));
                message.setSubject("QR Code");
                message.setContent("QR Code", "text/html; charset=utf-8");
                message.setFileName("/" + strData +".jpg");
              
               //get the attachment file
                MimeBodyPart pdfPart = new MimeBodyPart();
                pdfPart.attachFile(Environment.getExternalStorageDirectory().getPath() + "/QR_Code"+ "/" + "temp" +".jpg");
                
                //attach file to email
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(pdfPart);

                message.setContent(multipart);

                //send email with attachment
                Transport.send(message);
                
                //a toast to notify user that the email is sending...
                Toast.makeText(_activity, "Sending email... ...", Toast.LENGTH_LONG).show();
                
                //delete the temporary file after the email is sent
                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/temp.jpg");
                boolean deleted = file.delete();
                
                checkSend = true;
                
                

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		
	}

		protected void saveToFolder(String image_name, boolean saveDb) {
    		root = Environment.getExternalStorageDirectory().toString();
			File myDir = new File(root + "/QR_Code");    
			boolean success = true;
			boolean write = false;
			
			if (!myDir.exists()) {
			    success = myDir.mkdir();
			}else{
				write = true;
			}
			
			if (success) {
				//Toast.makeText(getActivity(), "A new folder named QR_Code has been "
						//+ "created successfully in SD Card", Toast.LENGTH_LONG).show();
				write = true;
			   
			} else {
			    
			}
			
			if(write){
				String fname = image_name +".jpg";
				File file = new File (myDir, fname);
				if (file.exists ()) file.delete (); 
				try {
				       FileOutputStream out = new FileOutputStream(file);
				       bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
				       out.flush();
				       out.close();
				       //btnEmail.setVisibility(View.VISIBLE);
				       Log.i("Load", "save to folder");
				       Toast.makeText(_activity, "QR code has been saved", Toast.LENGTH_SHORT).show();
				       
				       if(saveDb){
				    	   new SaveQRDb().execute();
				       }

				} catch (Exception e) {
				       e.printStackTrace();
				}
				
			}else{
				Log.e("Generator", "QR Code is not writing to file");
			}
			
		
		
	}

		protected void generateQRCode() {
    		
    		strData = name;
    		
    		Log.i("Load", "strData = " + name);
			
			if(strData.equals("") || strData.equals(null)){
				Toast.makeText(_activity, "Please enter data for QR Code", Toast.LENGTH_SHORT).show();
			}else{
				/**Intent intent = new Intent("com.google.zxing.client.android.ENCODE");
				intent.putExtra("ENCODE_FORMAT", "QR_CODE");
				intent.putExtra("ENCODE_TYPE", "TEXT_TYPE");
				intent.putExtra("ENCODE_DATA", strData);

				try {
					startActivityForResult(intent,0);
				} catch (ActivityNotFoundException e) {
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + "com.google.zxing.client.android")));
				}*/
				
				QRCodeEncoder qrCodeEncoder = new QRCodeEncoder(strData, null,
				        Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), qrCodeDimention);

				try {
				    bitmap = qrCodeEncoder.encodeAsBitmap();
				    qrCode.setImageBitmap(bitmap);
				    
				    
				    
				    btnSave.setVisibility(View.VISIBLE);
				    //btnSaveQRDb.setVisibility(View.VISIBLE);
				    btnEmail.setVisibility(View.VISIBLE);
				} catch (WriterException e) {
				    e.printStackTrace();
				}
			}
			
			
	}


		 class LoadQRDetails extends AsyncTask<String, String, String> {
			 
		        /**
		         * Before starting background thread Show Progress Dialog
		         * */
		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            progress = new ProgressDialog(_activity);
		            progress.setMessage("Loading ... ....");
		            progress.setIndeterminate(false);
		            progress.setCancelable(false);
		            progress.show();
		        }
		 
		        /**
		         * getting All products from url
		         * */
		        protected String doInBackground(String... args) {
		            // Building Parameters
		            List<NameValuePair> params = new ArrayList<NameValuePair>();
		            params.add(new BasicNameValuePair("location_name", location_name));
		            // getting JSON string from URL
		            JSONObject json = jparser.makeHttpRequest(locationURL, "POST", params);
		 
		            // Check your log cat for JSON reponse
		            Log.d("All locations: ", json.toString());
		 
		            try {
		                // Checking for SUCCESS TAG
		                int success = json.getInt(KEY_SUCCESS);
		 
		                if (success == 1) {
		                    // products found
		                    // Getting Array of Products
		                    location = json.getJSONArray(KEY_LOCATION);
		 
		                    // looping through All Products
		                    for (int i = 0; i < location.length(); i++) {
		                        JSONObject c = location.getJSONObject(i);
		 
		                        // Storing each json item in variable
		                         id = c.getString(KEY_ID);
		                         name = c.getString(KEY_NAME);
		                         description = c.getString(KEY_DESCRIPTION);
		                        
		                        Log.i("tester", "Testing~~~~");
		                        Log.i("id", id);
		                        Log.i("name", name);
		                        Log.i("desc", description);
		                        
		                        
		 
		                        
		                    }
		                } else {
		                   
		                }
		            } catch (JSONException e) {
		                e.printStackTrace();
		            }
		 
		            return null;
		        }
		 
		        /**
		         * After completing background task Dismiss the progress dialog
		         * **/
		        protected void onPostExecute(String file_url) {
		            // dismiss the dialog after getting all products
		            progress.dismiss();
		            // updating UI from Background Thread
		            _activity.runOnUiThread(new Runnable() {
		                public void run() {
		                  Log.i("Load", "load details completed");
		                  generateQRCode();
		                 
		                	
		                }
		            });
		 
		        }
		 
		 }


		 class SaveQRDb extends AsyncTask<String, String, String> {
			 
		        private String result;
				private HttpURLConnection connection;
				private DataOutputStream outputStream;
				
				String lineEnd = "\r\n";
	            String twoHyphens = "--";
	            String boundary = "*****";
	            int bytesRead, bytesAvailable, bufferSize;
	            byte[] buffer;
	            int maxBufferSize = 1 * 500 * 500; 
		        
		       // URLConnection connection;

				//private URL url;

				/**
		         * Before starting background thread Show Progress Dialog
		         * */
		        @Override
		        protected void onPreExecute() {
		            super.onPreExecute();
		            progress = new ProgressDialog(_activity);
		            progress.setMessage("Loading ... ....");
		            progress.setIndeterminate(false);
		            progress.setCancelable(false);
		            progress.show();
		            
		        }
		 
		        /**
		         * getting All products from url
		         * */
		        protected String doInBackground(String... args) {
		        	
		        	
		        	
		        	/** try  
		             {
		        	String path = Environment.getExternalStorageDirectory().getPath() + "/QR_Code"+ "/" + name +".jpg";
		             //Toast.makeText(this, pathToOurFile.toString(), 600).show();
		             //Toast.makeText(this, pathToOurFile, 600).show();
		             FileInputStream fileInputStream = new FileInputStream(new File(path) );

		             URL url = new URL(uploadURL);
		             connection = (HttpURLConnection) url.openConnection();

		             // Allow Inputs & Outputs
		             connection.setDoInput(true);
		             connection.setDoOutput(true);
		             connection.setUseCaches(false);

		             // Enable POST method
		             connection.setRequestMethod("POST");

		             connection.setRequestProperty("Connection", "Keep-Alive");
		             connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

		             outputStream = new DataOutputStream( connection.getOutputStream() );
		             outputStream.writeBytes(twoHyphens + boundary + lineEnd);
		             outputStream.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + path +"\"" + lineEnd);
		             outputStream.writeBytes(lineEnd);

		             bytesAvailable = fileInputStream.available();
		             bufferSize = Math.min(bytesAvailable, maxBufferSize);
		             buffer = new byte[bufferSize];

		             // Read file 
		             bytesRead = fileInputStream.read(buffer, 0, bufferSize);

		             while (bytesRead > 0)
		             {
		             outputStream.write(buffer, 0, bufferSize);
		             bytesAvailable = fileInputStream.available();
		             bufferSize = Math.min(bytesAvailable, maxBufferSize);
		             bytesRead = fileInputStream.read(buffer, 0, bufferSize);
		             }

		             outputStream.writeBytes(lineEnd);
		             outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

		             // Responses from the server (code and message)
		             connection.getResponseCode();
		             connection.getResponseMessage();

		             fileInputStream.close();
		             outputStream.flush();
		             outputStream.close();
		             }
		             catch (Exception ex)
		             {
		             //Exception handling
		             }*/
		        	
		        	/**HttpURLConnection conn = null;
		            DataOutputStream dos = null;  
		            String lineEnd = "\r\n";
		            String twoHyphens = "--";
		            String boundary = "*****";
		            int bytesRead, bytesAvailable, bufferSize;
		            byte[] buffer;
		            int maxBufferSize = 1 * 500 * 500; 
		        	
		        	String path = Environment.getExternalStorageDirectory().getPath() + "/QR_Code"+ "/" + name +".jpg";
		        	
		        	String fileName = name + ".jpg";
		   		 
		            File sourceFile = new File(path);
		            
		            if (!sourceFile.isFile()) {
		                
		                progress.dismiss(); 
		                 
		                Log.e("uploadFile", "Source File not exist");
		                                   
		             
		           }
		           else
		           {
		        	   int serverResponseCode = 0;
					try{
		        	   FileInputStream fileInputStream = new FileInputStream(sourceFile);
	                   URL url = new URL(uploadURL);
	                    
	                   // Open a HTTP  connection to  the URL
	                   conn = (HttpURLConnection) url.openConnection(); 
	                   conn.setDoInput(true); // Allow Inputs
	                   conn.setDoOutput(true); // Allow Outputs
	                   conn.setUseCaches(false); // Don't use a Cached Copy
	                   conn.setRequestMethod("POST");
	                   conn.setRequestProperty("Connection", "Keep-Alive");
	                   conn.setRequestProperty("ENCTYPE", "multipart/form-data");
	                   conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
	                   conn.setRequestProperty("uploaded_file", fileName); 
	                    
	                   dos = new DataOutputStream(conn.getOutputStream());
	          
	                   dos.writeBytes(twoHyphens + boundary + lineEnd); 
	                   dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName +"\"" + lineEnd);
	                    
	                   dos.writeBytes(lineEnd);
	          
	                   // create a buffer of  maximum size
	                   bytesAvailable = fileInputStream.available(); 
	          
	                   bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                   buffer = new byte[bufferSize];
	          
	                   // read file and write it into form...
	                   bytesRead = fileInputStream.read(buffer, 0, bufferSize);  
	                      
	                   while (bytesRead > 0) {
	                        
	                     dos.write(buffer, 0, bufferSize);
	                     bytesAvailable = fileInputStream.available();
	                     bufferSize = Math.min(bytesAvailable, maxBufferSize);
	                     bytesRead = fileInputStream.read(buffer, 0, bufferSize);   
	                      
	                    }
	          
	                   // send multipart form data necesssary after file data...
	                   dos.writeBytes(lineEnd);
	                   dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
	          
	                   // Responses from the server (code and message)
	                   serverResponseCode = conn.getResponseCode();
	                   String serverResponseMessage = conn.getResponseMessage();
	                     
	                   Log.i("uploadFile", "HTTP Response is : "
	                           + serverResponseMessage + ": " + serverResponseCode);
	                    
	                   if(serverResponseCode == HttpURLConnection.HTTP_OK){
	                        
	                      
	                               Log.i("File","File upload completed");
	                                
	                                //delete the temporary file after the email is sent
	                                File file = new File(Environment.getExternalStorageDirectory().getPath() + "/QR_Code"+ "/" + name +".jpg");
	                                boolean deleted = file.delete();
	                          
	                   }    
	                    
	                   //close the streams //
	                   //fileInputStream.close();
	                   //dos.flush();
	                   //dos.close();
	                     
	              } catch (MalformedURLException ex) {
	                   
	                  progress.dismiss();  
	                  ex.printStackTrace();
	                   
	                  
	                  Log.e("Upload file to server", "error: " + ex.getMessage(), ex);  
	              } catch (Exception e) {
	                   
	                  progress.dismiss();  
	                  e.printStackTrace();
	                   
	                  
	                  Log.e("Upload file to server Exception", "Exception : "
	                                                   + e.getMessage(), e);  
	              }
	              progress.dismiss();       
	              return Integer.toString(serverResponseCode); 
	               
	           }**/ // End else block 
		        	
		        	
		        	
		   
					try {

			        	Bitmap uploadBitmap = bitmap;
			        	ByteArrayOutputStream bos = new ByteArrayOutputStream();
			        	uploadBitmap.compress(CompressFormat.JPEG,50, bos);
			        	byte[] bitmapBytes = bos.toByteArray();
			        	
						URL url = new URL(uploadURL);
						HttpURLConnection connection = (HttpURLConnection)url.openConnection();
						connection.setConnectTimeout(5000);
						connection.setRequestMethod("POST");
						connection.setRequestProperty("content_type", "multipart/form-data");
			        	connection.setDoOutput(true);
			        	OutputStream os = null;
			        	os = connection.getOutputStream();
						os.write(bitmapBytes);
						os.close();
						
			        	InputStream is = connection.getInputStream();
			        	int responseCode = 0;
			        	responseCode = connection.getResponseCode();
			        	if(responseCode == HttpURLConnection.HTTP_OK){
			        		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			        		

			        		BufferedReader reader = new BufferedReader(isr);
			        		StringBuilder sb = new StringBuilder();
			        		String line = null;
			        		
			        		while((line = reader.readLine()) != null){
								sb.append(line + "\n");
								result = sb.toString();
				        		Log.i("result", result);
			        		}
			        	}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
					 catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      

					return result;
		            
		        }
		 
		        /**
		         * After completing background task Dismiss the progress dialog
		         * **/
		        protected void onPostExecute(String file_url) {
		            // dismiss the dialog after getting all products
		            progress.dismiss();
		            // updating UI from Background Thread
		            _activity.runOnUiThread(new Runnable() {
		                public void run() {
		                  //new SaveQRDb().execute();
		                	
		                }
		            });
		 
		        }
		 
		 }

	
   
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);
  
    }
}