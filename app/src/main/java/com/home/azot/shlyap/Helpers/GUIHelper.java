package com.home.azot.shlyap.Helpers;

import android.graphics.Outline;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.animation.PathInterpolator;

import com.home.azot.shlyap.R;

/**
 * Created by Женя on 19.02.2015.
 */
public class GUIHelper {

    public static void configureWindowEnterExitExplodeTransition(Window w) {

        Fade ex = new Fade();
        ex.setInterpolator(new PathInterpolator(0.4f, 0, 1, 1));
        ex.setDuration(500);
        w.setExitTransition(ex);
        w.setEnterTransition(ex);
    }
    public static void configureFab (View fabButton) {

        int fabSize = fabButton.getContext().getResources()
                .getDimensionPixelSize(R.dimen.fab_size);

        Outline fabOutLine = new Outline();
        fabOutLine.setOval(0, 0, fabSize, fabSize);
    }
}
