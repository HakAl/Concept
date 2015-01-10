package concept.com.labtech.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;

public class VerticalViewAnimations
{
    private static final int STANDARD_ANIMATION_DURATION = 333;
    private static final int ROTATE_ANIMATION_DURATION = 800;


    public static void rotate(View view)
    {
        final Animation rotateAnimation = new android.view.animation.RotateAnimation(0.0f, -179f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(ROTATE_ANIMATION_DURATION);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setRepeatMode(Animation.RESTART);
        view.startAnimation(rotateAnimation);
    }

    /**
     * Slide out vertically & alpha
     *
     * @param theView the View to shift_out_left out
     */
    public static void slideOut(final View theView)
    {
        TranslateAnimation anim = new TranslateAnimation(0, 0, 0, theView.getHeight());
        anim.setDuration(STANDARD_ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
                theView.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        theView.setAnimation(anim);
        theView.startAnimation(anim);
    }

    /**
     * Slide in vertically
     *
     * @param theView the View to slide in
     */
    public static void slideIn(final View theView)
    {
        TranslateAnimation anim = new TranslateAnimation(0, 0, theView.getHeight(), 0);
        anim.setDuration(STANDARD_ANIMATION_DURATION);
        anim.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                theView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation)
            {
            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {
            }
        });
        theView.setAnimation(anim);
        theView.startAnimation(anim);
    }
}