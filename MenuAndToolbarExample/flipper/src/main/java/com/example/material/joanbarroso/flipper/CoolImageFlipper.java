package com.example.material.joanbarroso.flipper;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;


public class CoolImageFlipper {


    private final Context context;

    public CoolImageFlipper(Context context) {
        this.context = context;
    }


    public void flipImage(final Drawable newImage, final ImageView containerToFlip) {
        ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(
                context, R.animator.flip);
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ObjectAnimator anim2 = (ObjectAnimator) AnimatorInflater.loadAnimator(
                        context, R.animator.flip_end);
                containerToFlip.setImageDrawable(newImage);
                anim2.setTarget(containerToFlip);
                anim2.setDuration(100);
                anim2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.setTarget(containerToFlip);
        anim.setDuration(100);
        anim.start();

    }
}
