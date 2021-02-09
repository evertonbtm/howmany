package br.com.btguth.howmany.utils;

import android.content.Context;

public class ScreenUtils {

    Context context;

    public ScreenUtils(Context ctx){
        this.context =ctx;
    }

    public Integer getBetterColumnCount() {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            return 4;
        }
        if (density >= 3.0) {
            return 4;
        }
        if (density >= 2.0) {
            return 3;
        }
        if (density >= 1.5) {
            return 3;
        }
        if (density >= 1.0) {
            return 3;
        }
        return 2;
    }

    public String getDensityName() {
        float density = context.getResources().getDisplayMetrics().density;
        if (density >= 4.0) {
            return "xxxhdpi";
        }
        if (density >= 3.0) {
            return "xxhdpi";
        }
        if (density >= 2.0) {
            return "xhdpi";
        }
        if (density >= 1.5) {
            return "hdpi";
        }
        if (density >= 1.0) {
            return "mdpi";
        }
        return "ldpi";
    }
}
