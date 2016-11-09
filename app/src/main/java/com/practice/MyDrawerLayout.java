package com.practice;

import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by user1 on 2016/11/9.
 */

public class MyDrawerLayout extends FrameLayout {
    public static final String tag = MyDrawerLayout.class.getSimpleName();
    private static final int MIN_FLING_VELOCITY = 400;
    private View contentContainer;
    private View menuContainer;
    private View darkenOverlay;
    private ViewDragHelper viewDragHelper;


    public MyDrawerLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        viewDragHelper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == menuContainer;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {
                int realLeft = left > -child.getWidth() ? left : -child.getWidth();
                realLeft = realLeft > 0 ? 0 : realLeft;
                return realLeft;
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                viewDragHelper.captureChildView(contentContainer, pointerId);
            }


            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                int releasedChildWidth = releasedChild.getWidth();
                float showRate = (float) (releasedChildWidth + releasedChild.getLeft()) / releasedChildWidth;
                if (xvel > 0 || showRate > 0.5f) {
                    viewDragHelper.settleCapturedViewAt(0, releasedChild.getTop());
                } else {
                    viewDragHelper.settleCapturedViewAt(-releasedChildWidth, releasedChild.getTop());
                }
                invalidate();
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                int releasedChildWidth = changedView.getWidth();
                float showRate = (float) (releasedChildWidth + changedView.getLeft()) / releasedChildWidth;
                darkenOverlay.setAlpha(showRate/2);
            }

            @Override
            public int getViewHorizontalDragRange(View child) {
                return child== menuContainer ?1:0;
            }
        });
        viewDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_LEFT);
        viewDragHelper.setMinVelocity(MIN_FLING_VELOCITY*getResources().getDisplayMetrics().density);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        if(viewDragHelper.continueSettling(true)){
            invalidate();
        }
    }

    public void openDrawer(){
        viewDragHelper.smoothSlideViewTo(menuContainer,0, menuContainer.getTop());
        postInvalidate();
    }

    public void closeDrawer(){
        viewDragHelper.smoothSlideViewTo(menuContainer,-menuContainer.getWidth(), menuContainer.getTop());
        postInvalidate();
    }



    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentContainer =findViewById(R.id.contentContainer);
        menuContainer =findViewById(R.id.menuContainer);
        darkenOverlay=findViewById(R.id.darkenOverlay);
    }
}
