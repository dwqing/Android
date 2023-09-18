package com.example.newclass;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private int diectionChange;
    public FooterBehavior(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout
                                       ,View child,View directTargetChild
                                       ,View target,int nestedScrollAxes
    ){
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout,View
                                  child,View target,int dx,int dy,int[] consumed){
        if (dy>0 && diectionChange<0||dy<0&&diectionChange>0) {
            child.animate().cancel();
            diectionChange = 0;
        }
        diectionChange += dy;
        if (diectionChange>child.getHeight()&&child.getVisibility() ==
        View.VISIBLE){
            hide(child);
        }else if (diectionChange<0&&child.getVisibility() == View.GONE){
            show(child);
        }

    }

    private void show(final View child) {
        ViewPropertyAnimator animator =
                child.animate().translationY(0)
                        .setInterpolator(new FastOutSlowInInterpolator())
                        .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                child.setVisibility(View.VISIBLE);
            }
        });
        animator.start();
    }

    private void hide(final View child) {
        ViewPropertyAnimator animator = child.animate().translationX(child
                .getHeight()).setInterpolator(new FastOutSlowInInterpolator())
                .setDuration(200);
        animator.setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                child.setVisibility(View.GONE);
            }
        });
        animator.start();
    }
}
