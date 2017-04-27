package com.brioal.msgview;

import android.content.Context;
import android.util.TypedValue;

/**
 * Github:https://github.com/Brioal
 * Emalil : brioal@foxmail.com
 * Created by brioal on 17-3-16.
 */

public class SizeUtil {
    //DP 转Px
    public static int DpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    //SP 转Px
    public static int SpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dp, context.getResources().getDisplayMetrics());
    }

}
