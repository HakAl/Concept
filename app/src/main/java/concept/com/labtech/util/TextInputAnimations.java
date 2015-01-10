package concept.com.labtech.util;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;

public class TextInputAnimations
{
    private static final int ANIMATION_DURATION = 200;

    /**
     * Slide out to left and alpha along the way to smooth transition.
     *
     * @param label the View to shift_out_left out
     * @param input the View to grow
     * @param size  the size to expand the input to
     */
    public static void slideOut(View label, View input, int size)
    {
        shrinkAnimation(label, 0);
        label.animate().
                translationX(-label.getWidth()).
                setDuration(ANIMATION_DURATION).
                setInterpolator(new DecelerateInterpolator()).
//                setListener(new LayerEnablingAnimatorListener(label)).
                start();
        growAnimation(input, size);
    }

    /**
     * Slide in from left and alpha along the way to smooth transition.
     *
     * @param label the View to shift_out_left out
     * @param input the View to grow
     */
    public static void slideIn(View label, View input)
    {
        label.animate().
                translationX(0).
                alpha(1).
                setDuration(ANIMATION_DURATION).
                setInterpolator(new AccelerateInterpolator()).
//                setListener(new LayerEnablingAnimatorListener(label)).
                start();
        shrinkAnimation(input, 0);
    }

    /**
     * Animation to scale the EditTexts when TextViews shift_out_left out.
     */
    private static void growAnimation(View v, int width)
    {
        ResizeWidthAnimation scaleX = new ResizeWidthAnimation(v, width);
        scaleX.setDuration(ANIMATION_DURATION);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        v.setAnimation(scaleX);
    }

    /**
     * Animation to scale the EditTexts when TextViews shift_out_left out.
     */
    private static void shrinkAnimation(View v, int width)
    {
        ResizeWidthAnimation scaleX = new ResizeWidthAnimation(v, width);
        scaleX.setDuration(ANIMATION_DURATION);
        scaleX.setInterpolator(new DecelerateInterpolator());
        v.setAnimation(scaleX);
    }

    private static class ResizeWidthAnimation extends Animation
    {
        private int mWidth;
        private int mStartWidth;
        private View mView;

        public ResizeWidthAnimation(View view, int width)
        {
            mView = view;
            mWidth = width;
            mStartWidth = view.getWidth();
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t)
        {
            mView.getLayoutParams().width = (mStartWidth + (int) ((mWidth - mStartWidth) * interpolatedTime));
            mView.requestLayout();
        }

        @Override
        public boolean willChangeBounds()
        {
            return true;
        }
    }
}