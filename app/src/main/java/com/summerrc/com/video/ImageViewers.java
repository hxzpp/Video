package com.summerrc.com.video;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * @author SummerRC
 * @version 1.0
 */

public class ImageViewers extends Activity {
    private ImageView imageView;
    private String imagePath;
    private static final String PATH = "PATH";

    public static void startSelf(Context context, String path) {
        Intent intent = new Intent(context, ImageViewers.class);
        intent.putExtra(PATH, path);
        context.startActivity(intent);
    }
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.item);
        findViews();
    }

    public void findViews() {
        imageView = (ImageView) findViewById(R.id.image);
        Intent intent = this.getIntent();
        imagePath = intent.getStringExtra(PATH);
        String TAG = "ImageView";
        Log.i(TAG, "image path:" + imagePath + "======");
        Drawable drawable = Drawable.createFromPath(imagePath);
        imageView.setImageDrawable(drawable);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add("process");
        return result;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("process")) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix m = new Matrix();
            m.setRotate(45);
            bitmap = Bitmap.createBitmap(bitmap, (width - 100) / 2, (height - 100) / 2, 100, 100, m, true);
            imageView.setScaleType(ImageView.ScaleType.CENTER);
            imageView.setImageBitmap(bitmap);
        }
        return super.onOptionsItemSelected(item);
    }
}