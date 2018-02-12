package com.ueuo.gabrieltavares.agendadecontatos.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImagemUtil {

    public static void getImagemByCaminho(String caminhoImagem, ImageView imageView){
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoImagem);
        Bitmap bitmapreduzido = Bitmap.createScaledBitmap(bitmap, 320, 426, true);
        imageView.setImageBitmap(bitmapreduzido);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setTag(caminhoImagem);
    }

}
