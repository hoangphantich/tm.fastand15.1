package com.nguyentiendung.mediaplayer;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextPaint;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;



public class VidduActivity extends Activity implements
        AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView mPhotosList;
    private ViewGroup mContainer;
    private ImageView mImageView;
    final Context context = this;	 
    private static final String[] PHOTOS_NAMES = new String[] {
            "Photo1",
            "Photo2",
            "Photo3",
    };
   
    int luu=0, dem=0;
    int i=0;
    View luuview;
    int soanh;
	public int demx;

    Matrix matrix = new Matrix();
    Matrix savedMatrix = new Matrix();
    PointF startPoint = new PointF();
    PointF midPoint = new PointF();
    float oldDist = 1f;
    static final int NONE = 0;
    static final int DRAG = 1;
    static final int ZOOM = 2;
    int mode = NONE;

    File root;
	File[] fileArray;
	File[] fileArray1;
	File[] fileArray2;
	File[] fileArray3;
	File[] fileArray4;
	String file;   
	
	
	String text;
	TextPaint myTextPaint;
	int textSize = 20;
	int textColor = 0xffA7573E;
	int pageColor = 0xffFDF8A6;
	int width, height, topPadding = 30, leftPadding =10;
	boolean done = false;
	
	int position=0;	
	int back=0;
	
	  private long startTime = 0L;
	  
	    private Handler customHandler = new Handler();

	    long timeInMilliseconds = 0L;
  long timeSwapBuff = 0L;
	    long updatedTime = 0L;
	    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);
        
       

        
        root = new File(Environment.getExternalStorageDirectory()
				.getAbsolutePath());
		fileArray = root.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.toLowerCase().endsWith(".jpg");
			}
		});
		
		fileArray1 = root.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.toLowerCase().endsWith(".bmp");
			}
		});
		
		fileArray2 = root.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String filename) {
				return filename.toLowerCase().endsWith(".png");
			}
		});
		
        mPhotosList = (ListView) findViewById(android.R.id.list);
        mImageView = (ImageView) findViewById(R.id.picture);
        
 
 
        mContainer = (ViewGroup) findViewById(R.id.container);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PHOTOS_NAMES);
        mPhotosList.setAdapter(adapter);
        mPhotosList.setOnItemClickListener(this);
        mImageView.setClickable(true);
        mImageView.setFocusable(true);
        mImageView.setOnClickListener(this);
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
    }
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;
        final Rotate3dAnimation rotation =
        new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));
        mContainer.startAnimation(rotation);
    }
    public void onItemClick(AdapterView parent, View v, int position, long id) {

    	//Toast.makeText(TransitionAnimationActivity.this, luu+"", Toast.LENGTH_SHORT).show();
 if(position==0)
 {
	 fileArray3=fileArray1;
	 back=1;
 }
 
 if(position==1)
 {
	 Intent goi=new Intent(VidduActivity.this, MainActivity1.class);
	 startActivity(goi);
	 finish();
	 fileArray3=fileArray1;
	 Toast.makeText(getApplicationContext(), "Cac buc anh yeu thich", Toast.LENGTH_SHORT).show();
 }
 
 if(position==2)
 {
	 back=2;
	 fileArray3=fileArray;
	 getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,   
             WindowManager.LayoutParams.FLAG_FULLSCREEN);

Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
width = display.getWidth();
height = display.getHeight();

this.setContentView(new MphotoView(this, width, height, topPadding, leftPadding, myTextPaint));


 }
	 
 
 if (fileArray3.length>0)
 {
	 file="/sdcard/"+fileArray3[0].getName();
  mImageView.setImageBitmap(BitmapFactory.decodeFile(file));
  applyRotation(position, 0, 90);
 }
  mImageView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			
			 if (fileArray3.length>0)
			 {

			dem=dem+1;
			file="/sdcard/"+fileArray3[dem].getName();
			
			mImageView.setImageBitmap(BitmapFactory.decodeFile(file));
			 applyRotation(dem, 0, 90);
			 if (dem==fileArray3.length-1)
				 {
				// Toast.makeText(TransitionAnimationActivity.this, luu+"", Toast.LENGTH_SHORT).show();
				 dem=0;
				 back=1;
				 	 mImageView.setVisibility(View.GONE);
		                mPhotosList.setVisibility(View.VISIBLE);
		                mPhotosList.requestFocus();
		               // rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
		                applyRotation(-1, 180, 90);
				 }
			
			 float scale=1.0f;
			 matrix.postScale(scale, scale, 3.0f, 4.0f);
			 mImageView.setImageMatrix(matrix);
			 savedMatrix.set(matrix);
			 
		}
		}
	});
		
    	mImageView.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

				alertDialogBuilder.setTitle( "Alert");
				alertDialogBuilder
				.setMessage("Chon anh lam hinh nen?")
				.setCancelable(true)
				.setPositiveButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
					 	
						dialog.cancel();
					}
				  })
				  .setNegativeButton("OK",new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							WallpaperManager myWallpaperManager
					        = WallpaperManager.getInstance(getApplicationContext());
					        try {
					            myWallpaperManager.setBitmap(BitmapFactory.decodeFile(file));

					        } catch (IOException e) {
					            // TODO Auto-generated catch block
					            e.printStackTrace();
					        }
						}

					});
				
			
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				
				
				
				return false;
			}
		});
       
    	
   		
        
    }
    public void onClick(View v) {
        applyRotation(-1, 180, 90);
    }
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;
        private DisplayNextView(int position) {
            mPosition = position;
        }
        public void onAnimationStart(Animation animation) {
        }
        public void onAnimationEnd(Animation animation) {
            mContainer.post(new SwapViews(mPosition));
        }
        public void onAnimationRepeat(Animation animation) {
        }
    }
    private final class SwapViews implements Runnable {
        private final int mPosition;
        public SwapViews(int position) {
            mPosition = position;
        }
        public void run() {
            final float centerX = mContainer.getWidth() / 2.0f;
            final float centerY = mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            if (mPosition > -1) {
                mPhotosList.setVisibility(View.GONE);
                mImageView.setVisibility(View.VISIBLE);
                mImageView.requestFocus();
                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
            } else {
                mImageView.setVisibility(View.GONE);
                mPhotosList.setVisibility(View.VISIBLE);
                mPhotosList.requestFocus();
                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }
            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());
            mContainer.startAnimation(rotation);
        }
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
if (back!=0)
{
        	Intent gui1=new Intent(VidduActivity.this, VidduActivity.class);

       	 startActivity(gui1);
}

       	 finish();
       	 

            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_MENU)) {
        	finish();
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)) {
          
            return true;
        } else if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    

    @Override
    public void onDestroy()
    {
    	super.onDestroy();
    	System.gc();
    	//pages1.clear();
    	//finish();
    }
    
    
    public class MphotoView extends View 
    {
    	// MyHandler myHandler = new MyHandler();
    	private int DEFAULT_FLIP_VALUE = 20;
    	private int FLIP_SPEED = 30;
    	private long mMoveDelay = 1000 / 30;
    	float xTouchValue = DEFAULT_FLIP_VALUE, yTouchValue = DEFAULT_FLIP_VALUE;
    	private File root;
    	private File[] fileArray;
    	private String filex;

    	//String result = ""
    	class FlippingHandler extends Handler 
    	{
    		@Override
    		public void handleMessage(Message msg) 
    		{
    			Log.i("Thong bao: ", "Clock Handler is still running");
    			MphotoView.this.flip();
    		}

    		public void sleep(long delayMillis) 
    		{
    			this.removeMessages(0);
    			sendMessageDelayed(obtainMessage(0), delayMillis);
    		}
    	}

    	FlippingHandler flippingHandler;
    	
    	private Bitmap page1;
    	private Bitmap page2;
    	
    	private ArrayList<Bitmap> pages=new ArrayList<Bitmap>(); // moi them
    	int curindex=0;			//moi them
    	boolean isnext=true;	//moi them
    	int totalphoto;			//moi them
    	
    	int width;
    	int height;

    	float oldTouchX, oldTouchY;
    	boolean flipping = false;
    	boolean next;

    	Point A, B, C, D, E, F;
    	

    	Bitmap visiblePage;
    	Bitmap invisiblePage;
    	Paint flipPagePaint;

    	boolean flip = false;

    	Context context;
    	
    	int loadedPages = 0;
    	long timeToLoad = 0;

    	//boolean loadingDone = false;

    	boolean onloading = true;
    	boolean onMoving = false;

    	// constructor
    	public MphotoView(Context context) 
    	{
    		super(context);
    		// TODO Auto-generated constructor stub
    		// initMyView(context);
    		this.context = context;
    		init();
    		setFocusable(true);
    		setFocusableInTouchMode(true);
    	}

    	public MphotoView(Context context, int width, int height, int topPadding,
    			int leftPadding, TextPaint myTextPaint) 
    	{
    		super(context);
    		this.context = context;
    		this.width = width;
    		this.height = height;
    		//this.pages= _page;				//moi them
    		//this.totalphoto=_page.size()-1;	//moi them
    		
    		/*page1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.h1);
    		page2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.h2);*/
    		
    		//page1 = pages.get(0);
    		//page2 = pages.get(1);
    		
    		// moi them
    		
    		  

    		
    		for (int x=0; x<fileArray3.length; x++)	{
    			if (fileArray3[x].getName().subSequence(0, 1).equals("h"))
    			{
    			pages.add(BitmapFactory.decodeFile("/sdcard/"+fileArray3[x].getName()));
    			soanh=soanh+1;
       			}
    		}	   
    		
    						
    		page1 = pages.get(0);
    		page2 = pages.get(1);
    		
    		totalphoto=pages.size()-1;	//moi them.
    		
    		init();
    		
    	}
    	
    	private void init() 
    	{
    		flippingHandler = new FlippingHandler();
    		flipPagePaint = new Paint();
    		flipPagePaint.setColor(Color.rgb(180, 180, 180));		//moi them
    		flipPagePaint.setShadowLayer(5, -5, 5, 0x99000000);		
    		A = new Point(10, 0);
    		B = new Point(width, height);
    		C = new Point(width, 0);
    		D = new Point(0, 0);
    		E = new Point(0, 0);
    		F = new Point(0, 0);

    		xTouchValue = yTouchValue = DEFAULT_FLIP_VALUE;
    		visiblePage = page1;
    		invisiblePage = page2;
    		onMoving=false;
    		flipping=false;

    		loadData();
    	}

    	private void loadData() 
    	{
    		onloading = false;
    	}
    	
    	@Override
    	public boolean onTouchEvent(MotionEvent event) 
    	{
    		if (!onloading) 
    		{
    			
    			
    			
    			switch (event.getAction()) 
    			{
    			case MotionEvent.ACTION_DOWN:	
    				oldTouchX = event.getX();
    				oldTouchY = event.getY();
    				flip = true;
    				if (oldTouchX > (width >> 1)) 
    				{
    					xTouchValue = DEFAULT_FLIP_VALUE;
    					yTouchValue = DEFAULT_FLIP_VALUE;
    					// set invisible page's content
    					
    					next = true;
    					
    					if (isnext)			//moi them
    					{
    					if (curindex==totalphoto)	//moi them
    						curindex=0;
    					else
    						curindex++;		//moi them
    					}
    					isnext=true;		//moi them
    					
    				} else {
    					// set invisible page's content
    					// invisiblePage.setContent(index-1, null);
    					
    					next = false;		
    					
    					if (isnext==false)	//moi them
    					{
    					if (curindex==0)	//moi them
    						curindex=totalphoto;
    					else
    						curindex--;		//moi them
    					}
    					isnext=false;		//moi them
    					
    					
    					swap2Page();					
    					xTouchValue = width;
    					yTouchValue = DEFAULT_FLIP_VALUE;
    				}
    				break;
    			case MotionEvent.ACTION_UP:				
    				if(onMoving){
    					xTouchValue = width-A.x;
    	    			demx=demx+1;
    	    			//Toast.makeText(TransitionAnimationActivity.this, demx+"  "+soanh+"", Toast.LENGTH_SHORT).show();

    	    			if (demx==soanh+1)
    	    			{

    	    	        	Intent gui1=new Intent(VidduActivity.this, VidduActivity.class);

    	    	          	 startActivity(gui1);
    	    	          	 finish();
    	    	          	 

    	    			}
    					onMoving=false;
    				}
    				flipping = true;				
    				flip();
    				break;
    			case MotionEvent.ACTION_MOVE:
    				onMoving=true;
    				
    				float xMouse = event.getX();
    				float yMouse = event.getY();
    				
    				xTouchValue -= (xMouse - oldTouchX) / 1;
    				
    				yTouchValue -= yMouse - oldTouchY;

    				if (xMouse < oldTouchX) 
    				{
    					if (!next) 
    					{
    						flip = false;
    					}
    					next = true;

    				} else {
    					if (next) 
    					{
    						flip = false;
    					}
    					next = false;
    				}

    				oldTouchX = event.getX();
    				oldTouchY = event.getY();

    				this.invalidate();
    				break;
    			}


    		}

    		return true;
    	}

    	public void flip() 
    	{
    		// neu chua load thi load va lat sang trang
    		if (flipping) 
    		{			
    			// if (now - mLastMove > mMoveDelay)
    			if (xTouchValue > width || xTouchValue < DEFAULT_FLIP_VALUE) 
    			{
    				flipping = false;
    				if (!flipping) 
    				{
    					if (next) 
    					{
    						swap2Page();
    					} 
    					flip = false;
    					xTouchValue = DEFAULT_FLIP_VALUE;
    					yTouchValue = DEFAULT_FLIP_VALUE;
    					
    				}
    				return;
    			}
    			if (next) 
    			{
    				// new cd sang trai-> trang moi
    				xTouchValue += FLIP_SPEED;				
    			} else {
    				// neu cd sang phai-> trang cu
    				xTouchValue -= FLIP_SPEED;
    				
    			}
    			this.invalidate();
    		
    			// call hander
    			flippingHandler.sleep(mMoveDelay);
    		}
    	}

    	@Override
    	protected void onDraw(Canvas canvas) 
    	{
    		// super.onDraw(canvas);
    		width = getWidth();
    		height = getHeight();
    		
    		if(flipping){
    			pointGenerate(xTouchValue, width, height);
    		}else 
    		{
    			//pointGenerateII(xTouchValue, yTouchValue, width, height);
    			pointGenerate(xTouchValue, width, height);
    		}
    		
    		// First Page render
    		Paint paint = new Paint();
    		//canvas.drawColor(Color.GRAY);
    		canvas.drawColor(Color.rgb(220, 220, 220));	//moi them
    		//canvas.drawBitmap(visiblePage, 0, 0, paint);
    		

 			 
    		int fromOX=(width-visiblePage.getWidth())/2;	//moi them
    		int fromOY=(height-visiblePage.getHeight())/2;	//moi them		
    		if (fromOX>0&&fromOY>0)		//moi them
    			canvas.drawBitmap(visiblePage, Math.abs(fromOX),Math.abs(fromOY), paint);
    		else
    		if (fromOX>0)
    			canvas.drawBitmap(visiblePage, Math.abs(fromOX),0, paint);
    		else
    		if (fromOY>0)
    			canvas.drawBitmap(visiblePage, 0,Math.abs(fromOY), paint);	
    		else
    			canvas.drawBitmap(visiblePage, 0, 0, paint);
    		
    		// Second Page Render
    		Path pathX = pathOfTheMask();
    		canvas.clipPath(pathX);
    		
    		//canvas.drawBitmap(invisiblePage, 0, 0, paint);
    		int fromOX_=(width-invisiblePage.getWidth())/2;		//moi them
    		int fromOY_=(height-invisiblePage.getHeight())/2;	//moi them
    		if (fromOX_>0&&fromOY_>0)		//moi them
    			canvas.drawBitmap(invisiblePage, Math.abs(fromOX_),Math.abs(fromOY_), paint);
    		else
    		if (fromOX>0)
    			canvas.drawBitmap(invisiblePage, Math.abs(fromOX_),0, paint);
    		else
    		if (fromOY>0)
    			canvas.drawBitmap(invisiblePage,0, Math.abs(fromOY_), paint);
    		else
    			canvas.drawBitmap(invisiblePage, 0, 0, paint);
    		
    		canvas.restore();
    		// Flip Page render

    		Path pathX2 = pathOfFlippedPaper();
    		canvas.drawPath(pathX2, flipPagePaint);
    		
    		pathX = null;
    		pathX2 = null;
    		paint = null;

    	}
    	
    	// float degress =0;	
    	private Path pathOfTheMask() 
    	{
    		Path path = new Path();
    		path.moveTo(A.x, A.y);
    		path.lineTo(B.x, B.y);
    		path.lineTo(C.x, C.y);
    		path.lineTo(D.x, D.y);
    		path.lineTo(A.x, A.y);

    		return path;
    	}	
    	
    	private Path pathOfFlippedPaper() 
    	{
    		Path path = new Path();
    		path.moveTo(A.x, A.y);
    		path.lineTo(D.x, D.y);
    		path.lineTo(E.x, E.y);
    		path.lineTo(F.x, F.y);
    		path.lineTo(A.x, A.y);
    		return path;
    	}

    	private void pointGenerate(float distance, int width, int height) 
    	{
    		float xA = width - distance;
    		float yA = height;

    		// float xB= width;
    		// float yB= height;

    		// float xC = width;
    		// float yC = 0;

    		float xD = 0;
    		float yD = 0;
    		
    		if (xA > width / 2) 
    		{
    			xD = width;
    			yD = height - (width - xA) * height / xA;
    		} else {
    			xD = 2 * xA;
    			yD = 0;
    		}
    	
    		double a = (height - yD) / (xD + distance - width);
    		double alpha = Math.atan(a);
    		double _cos = Math.cos(2 * alpha), _sin = Math.sin(2 * alpha);
    		// E
    		float xE = (float) (xD + _cos * (width - xD));
    		float yE = (float) -(_sin * (width - xD));
    		// F
    		float xF = (float) (width - distance + _cos * distance);
    		float yF = (float) (height - _sin * distance);
    		
    		if (xA > width / 2) 
    		{
    			xE = xD;
    			yE = yD;
    		}
    			
    		A.x = xA;
    		A.y = yA;
    		D.x = xD;
    		D.y = yD;
    		E.x = xE;
    		E.y = yE;
    		F.x = xF;
    		F.y = yF;
    		
    	}

    	float oldxF=0, oldyF=0;
    	private void pointGenerateII(float xTouch, float yTouch, int width,int height) 
    	{			
    		float yA = height;
    		float xD = width;

    		float xF = width - xTouch+0.1f;
    		float yF = height - yTouch+0.1f;
    		
    		if(A.x==0){
    			xF= Math.min(xF, oldxF);
    			yF= Math.max(yF, oldyF);
    		}
    		
    		float deltaX = width-xF;
    		float deltaY = height-yF;
    		
    		float BH = (float) (Math.sqrt(deltaX * deltaX + deltaY * deltaY) / 2);
    		double tangAlpha = deltaY / deltaX;
    		double alpha = Math.atan(tangAlpha);
    		double _cos = Math.cos(alpha), _sin = Math.sin(alpha);
    		
    		float xA = (float) (width - (BH / _cos));
    		float yD = (float) (height - (BH / _sin));
    		

    		xA = Math.max(0, xA);
    		if(xA==0){
    			//yF= Math.max(yF, height-(float) Math.sqrt(width*width-xF*xF));
    			oldxF = xF;
    			oldyF = yF;
    		}
    		
    		float xE = xD;
    		float yE = yD;
    		// if (xA > width / 2) 

    		if (yD < 0) {
    			xD = width + (float) (tangAlpha * yD);
    			yE = 0;
    			xE = width + (float) (Math.tan(2 * alpha) * yD);

    		}

    		A.x = xA;
    		A.y = yA;
    		D.x = xD;
    		D.y = Math.max(0, yD);
    		E.x = xE;
    		E.y = yE;
    		F.x = xF;
    		F.y = yF;
    		
    	}

    	private void swap2Page() 
    	{
    		/*Bitmap temp = visiblePage;
    		visiblePage = invisiblePage;
    		invisiblePage = temp;*/
    		
    		if (isnext==true)
    		{
    			if (curindex==totalphoto) 	//moi them
    			{
    				visiblePage=pages.get(curindex);
    				invisiblePage=pages.get(0);
    			}
    			else	//moi them
    			{
    				visiblePage=pages.get(curindex);
    				invisiblePage=pages.get(curindex+1);
    			}			
    		}
    		else
    		{
    			if (curindex==0) 	//moi them
    			{
    				visiblePage=pages.get(totalphoto);
    				invisiblePage=pages.get(curindex);
    			}
    			else	//moi them
    			{
    				visiblePage=pages.get(curindex-1);
    				invisiblePage=pages.get(curindex);
    			}			
    		}
    		
    		// stringSplitterInOrder();
    		//temp = null;

    	}


    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_caro, menu);
		
		menu.add("Game");
menu.getItem(0).setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
Intent goi2=new Intent(VidduActivity.this, Game_Caro.class);
				
				startActivity(goi2);
				finish();
				return false;
			}
		});
return true;
	}
}