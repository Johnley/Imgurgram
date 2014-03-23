package com.johnley.imgurgram;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Base64;


public class MainActivity extends Activity {

    private ImageView imgMain ;
    private static final int SELECT_PHOTO = 100;
	private static final int REQUEST_TAKE_PHOTO = 1;
    private Bitmap src;
    String mCurrentPhotoPath;
    Bitmap mCurrentModdedPhoto = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgMain = (ImageView) findViewById(R.id.effect_main);
        src = BitmapFactory.decodeResource(getResources(), R.drawable.image);
        folderCheck();
    }
    public void buttonClicked(View v){

        Toast.makeText(this,Messages.getString("MainActivity.processing"),Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
        ImageFilters imgFilter = new ImageFilters();        	
		if(v.getId() == R.id.effect_black)
            saveBitmap(imgFilter.applyBlackFilter(src),Messages.getString("MainActivity.effect_black")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_boost_1)
            saveBitmap(imgFilter.applyBoostEffect(src, 1, 40),Messages.getString("MainActivity.effectBoost1")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_boost_2)
            saveBitmap(imgFilter.applyBoostEffect(src, 2, 30),Messages.getString("MainActivity.effectBoost2")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_boost_3)
            saveBitmap(imgFilter.applyBoostEffect(src, 3, 67),Messages.getString("MainActivity.effectBoost3")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_brightness)
            saveBitmap(imgFilter.applyBrightnessEffect(src, 80),Messages.getString("MainActivity.effectBrightness")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_color_red)
            saveBitmap(imgFilter.applyColorFilterEffect(src, 255, 0, 0),Messages.getString("MainActivity.effectColorRed")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_color_green)
            saveBitmap(imgFilter.applyColorFilterEffect(src, 0, 255, 0),Messages.getString("MainActivity.effectColorGreen")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_color_blue)
            saveBitmap(imgFilter.applyColorFilterEffect(src, 0, 0, 255),Messages.getString("MainActivity.effectColorBlue")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_color_depth_64)
            saveBitmap(imgFilter.applyDecreaseColorDepthEffect(src, 64),Messages.getString("MainActivity.effectColorDepth64")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_color_depth_32)
            saveBitmap(imgFilter.applyDecreaseColorDepthEffect(src, 32),Messages.getString("MainActivity.effectColorDepth32")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_contrast)
            saveBitmap(imgFilter.applyContrastEffect(src, 70),Messages.getString("MainActivity.effectContrast")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_emboss)
            saveBitmap(imgFilter.applyEmbossEffect(src),Messages.getString("MainActivity.effectEmboss")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_engrave)
            saveBitmap(imgFilter.applyEngraveEffect(src),Messages.getString("MainActivity.effectEngrave")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_flea)
            saveBitmap(imgFilter.applyFleaEffect(src),Messages.getString("MainActivity.effectFlea")); //$NON-NLS-1$
        else  if(v.getId() == R.id.effect_gaussian_blue)
            saveBitmap(imgFilter.applyGaussianBlurEffect(src),Messages.getString("MainActivity.effectGaussianBlue")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_gamma)
            saveBitmap(imgFilter.applyGammaEffect(src, 1.8, 1.8, 1.8),Messages.getString("MainActivity.effectGamma")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_grayscale)
            saveBitmap(imgFilter.applyGreyscaleEffect(src),Messages.getString("MainActivity.effectGrayscale")); //$NON-NLS-1$
        else  if(v.getId() == R.id.effect_hue)
            saveBitmap(imgFilter.applyHueFilter(src, 2),Messages.getString("MainActivity.effectHue")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_invert)
            saveBitmap(imgFilter.applyInvertEffect(src),Messages.getString("MainActivity.effectInvert")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_mean_remove)
            saveBitmap(imgFilter.applyMeanRemovalEffect(src),Messages.getString("MainActivity.effectMeanRemove")); //$NON-NLS-1$
//        else if(v.getId() == R.id.effect_reflaction)
//            saveBitmap(imgFilter.applyReflection(src),"effect_reflaction");
        else if(v.getId() == R.id.effect_round_corner)
            saveBitmap(imgFilter.applyRoundCornerEffect(src, 45),Messages.getString("MainActivity.effectRoundCorner")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_saturation)
            saveBitmap(imgFilter.applySaturationFilter(src, 1),Messages.getString("MainActivity.effectSaturation")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sepia)
            saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 1.5, 0.6, 0.12),Messages.getString("MainActivity.effectSepia")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sepia_green)
            saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 0.88, 2.45, 1.43),Messages.getString("MainActivity.effectSepiaGreen")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sepia_blue)
            saveBitmap(imgFilter.applySepiaToningEffect(src, 10, 1.2, 0.87, 2.1),Messages.getString("MainActivity.effectSepiaBlue")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_smooth)
            saveBitmap(imgFilter.applySmoothEffect(src, 100),Messages.getString("MainActivity.effectSmooth")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sheding_cyan)
            saveBitmap(imgFilter.applyShadingFilter(src, Color.CYAN),Messages.getString("MainActivity.effectShedingCyan")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sheding_yellow)
            saveBitmap(imgFilter.applyShadingFilter(src, Color.YELLOW),Messages.getString("MainActivity.effectShedingYellow")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_sheding_green)
            saveBitmap(imgFilter.applyShadingFilter(src, Color.GREEN),Messages.getString("MainActivity.effectShedingGreen")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_tint)
            saveBitmap(imgFilter.applyTintEffect(src, 100),Messages.getString("MainActivity.effectTint")); //$NON-NLS-1$
        else if(v.getId() == R.id.effect_watermark)
            saveBitmap(imgFilter.applyWaterMarkEffect(src, Messages.getString("MainActivity.siteString"), 200, 200, Color.GREEN, 80, 24, false),Messages.getString("MainActivity.effectWatermark")); //$NON-NLS-1$ //$NON-NLS-2$

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_camera:
            	Toast.makeText(this,Messages.getString("MainActivity.processing"),Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
                dispatchTakePictureIntent();
                return true;
            case R.id.action_gallery:
            	Toast.makeText(this,Messages.getString("MainActivity.processing"),Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
            	Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*"); //$NON-NLS-1$
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
                return true;
            case R.id.action_save:
			try {
				saveImage(mCurrentModdedPhoto);
				Toast.makeText(this,Messages.getString("MainActivity.saved"),Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void saveBitmap(Bitmap bmp,String fileName){
        try {
            imgMain.setImageBitmap(bmp);
            File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + Messages.getString("MainActivity.cache") + fileName + ".png"); //$NON-NLS-1$ //$NON-NLS-2$
            FileOutputStream fos = new FileOutputStream(f);
            bmp.compress(Bitmap.CompressFormat.PNG,90,fos);
            mCurrentModdedPhoto = bmp;
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode) {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK){
            		Bitmap bmp = null;
                    Uri selectedImage = data.getData();
                    bmp = decodeUri(selectedImage);
                    if(bmp !=null){
                        src = bmp;
                        imgMain.setImageBitmap(src);

                    }
                    else{
                    	Toast.makeText(this, "Could not load image", Toast.LENGTH_SHORT).show(); //$NON-NLS-1$
                    }
                }
            case REQUEST_TAKE_PHOTO:
            	if(resultCode == RESULT_OK) {
            		Bitmap bmp = null;
                    File photo = new File(Environment.getExternalStorageDirectory() + Messages.getString("MainActivity.cache"), "temp.jpg"); //$NON-NLS-1$ //$NON-NLS-2$
                    bmp = decodeUri(Uri.fromFile(photo));
                	if(bmp != null){
                		src = bmp;
                		imgMain.setImageBitmap(src);
                	}
                
            }
        }
    }
    private Bitmap decodeUri(Uri selectedImage)  {

        try {

        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o);

        // The new size we want to scale to
        final int REQUIRED_SIZE = 400;

        // Find the correct scale value. It should be the power of 2.
        int width_tmp = o.outWidth, height_tmp = o.outHeight;
        int scale = 1;
        while (true) {
            if (width_tmp / 2 < REQUIRED_SIZE
                    || height_tmp / 2 < REQUIRED_SIZE) {
                break;
            }
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        // Decode with inSampleSize
        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(selectedImage), null, o2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private void dispatchTakePictureIntent() {
	    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
	    	File f = new File(android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/Imgurgram/cache/temp.jpg"); //$NON-NLS-1$
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
	            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
	        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }
    public boolean saveImage(Bitmap bmp) throws IOException{
    	String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + 
                Messages.getString("MainActivity.imgramDir"); //$NON-NLS-1$
    	String timeStamp = new SimpleDateFormat(Messages.getString("MainActivity.fileFormat"), Locale.US).format(new Date()); //$NON-NLS-1$
    	File file = new File(file_path, Messages.getString("MainActivity.filePreformat") + timeStamp + ".png"); //$NON-NLS-1$ //$NON-NLS-2$
    	FileOutputStream fOut = new FileOutputStream(file);
    	bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
    	fOut.flush();
    	fOut.close();
    	return true;
    }
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    public void folderCheck(){
    	String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + 
    			"MainActivity.imgramDir"; //$NON-NLS-1$
    	File dir = new File(file_path);
    	if(!dir.exists())
    		dir.mkdirs();
    	file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + 
                "MainActivity.cache"; //$NON-NLS-1$
    	dir = new File(file_path);
    	if(!dir.exists())
    		dir.mkdirs();
    }
    private boolean imgurUpload (Bitmap bmp){
    	
    	
    	
    	
    	return false;
    }
}
