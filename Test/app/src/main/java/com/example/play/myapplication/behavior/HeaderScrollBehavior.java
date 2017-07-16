package com.example.play.myapplication.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ScrollerCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import com.example.play.myapplication.R;

public class HeaderScrollBehavior extends HeaderBehavior<HeaderLayout> {
    private static final String TAG = HeaderScrollBehavior.class.getSimpleName();

    private int mTouchSlop;
    private int mMaxFlingVelocity;
    private int mMinFlingVelocity;

    private boolean mIsScrolling;

    private View mFixedLayout;

    private int mMinOffset;
    private int mMaxOffset;

    private View mTargetView;

    private int mSkippedOffset;

    private ViewFlinger mViewFlinger;

    public HeaderScrollBehavior() {

    }

    public HeaderScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);

        final ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        mTouchSlop = viewConfiguration.getScaledTouchSlop();
        mMaxFlingVelocity = viewConfiguration.getScaledMaximumFlingVelocity();
        mMinFlingVelocity = viewConfiguration.getScaledMinimumFlingVelocity();
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, HeaderLayout child, int layoutDirection) {
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, HeaderLayout child, int parentWidthMeasureSpec,
                                  int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec,
                heightUsed);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, HeaderLayout child, View dependency) {
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, HeaderLayout child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, HeaderLayout child, View directTargetChild,
                                       View target, int nestedScrollAxes) {
        if (mViewFlinger != null) {
            mViewFlinger.cancel();
        }

        if ((nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0) {
            mTargetView = target;

            mIsScrolling = false;
            mSkippedOffset = 0;

            mFixedLayout = child.findViewById(R.id.fixed_view);

            mMinOffset = -(child.getHeight() - mFixedLayout.getHeight());
            mMaxOffset = 0;

            return true;
        }
        return false;
    }

    @Override
    boolean canDragView(HeaderLayout view) {
        return true;
    }

    @Override
    int getMaxDragOffset(HeaderLayout view) {
        return -view.getScrollRange();
    }

    @Override
    int getScrollRangeForDragFling(HeaderLayout view) {
        return view.getScrollRange();
    }

    @Override
    View getHandleView(HeaderLayout view) {
        return view;
//        return view.findViewById(R.id.fixed_view);
//        return super.getHandleView(view);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, HeaderLayout child, View target, int dx, int dy, int[] consumed) {

        Log.d("BEHAVIOR", "onNestedPreScroll ## dx = " + dx + " , dy = " + dy + " , consumed[0] = " + consumed[0]);

        //TODO 아래로 스크롤 시 판단조건 추가
        int scrollOffset = 0;
        if (target instanceof ScrollingView) {
            scrollOffset = ((ScrollingView) target).computeVerticalScrollOffset();
        }

        if (dy < 0) {
            // We're scrolling down
            if (scrollOffset > 0) return;
        } else {
            // We're scrolling up
        }

        if (!mIsScrolling) {
            mSkippedOffset += dy;

            if (Math.abs(mSkippedOffset) >= mTouchSlop) {
                mIsScrolling = true;
                target.getParent().requestDisallowInterceptTouchEvent(true);
            }
        }

        if (mIsScrolling && dy != 0) {
            int min = -child.getScrollRange();
            int max = 0;

            int currentOffset = getTopAndBottomOffset();
            int newOffset = Math.min(Math.max(min, currentOffset - dy), max);

            consumed[1] = newOffset - currentOffset;

            setTopAndBottomOffset(newOffset);
        }
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, HeaderLayout child, View target,
                                    float velocityX, float velocityY) {

        Log.d("BEHAVIOR", "onNestedPreFling ## velocityX = " + velocityX + " , velocityY = " + velocityY);

        if (mViewFlinger != null) {
            mViewFlinger.cancel();
        } else {
            mViewFlinger = new ViewFlinger(coordinatorLayout);
        }

        final int targetOffsetRange;
        final int targetOffset;
        if (target instanceof ScrollingView) {
            targetOffsetRange = ((ScrollingView) target).computeVerticalScrollRange() + target.getPaddingTop() + target.getPaddingBottom();
            targetOffset = ((ScrollingView) target).computeVerticalScrollOffset();
        } else {
            targetOffsetRange = Math.max(0, target.getHeight() - coordinatorLayout.getHeight());
            targetOffset = target.getScrollY();
        }

        mViewFlinger.fling((int) velocityY, targetOffset, targetOffsetRange);

        return true;
    }

    private class ViewFlinger implements Runnable {
        private final ScrollerCompat mScroller;
        private final CoordinatorLayout mCoordinatorLayout;
        private int mLastFlingY;

        public ViewFlinger(CoordinatorLayout coordinatorLayout) {
            mScroller = ScrollerCompat.create(coordinatorLayout.getContext());
            mCoordinatorLayout = coordinatorLayout;
        }

        public void fling(int velocity, int targetOffset, int targetOffsetRange) {
            if (Math.abs(velocity) < mMinFlingVelocity) {
                return;
            }

            velocity = Math.max(-mMaxFlingVelocity, Math.min(velocity, mMaxFlingVelocity));

            mScroller.fling(0, 0, 0, velocity, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
//			mCoordinatorLayout.postOnAnimation(this);
            ViewCompat.postOnAnimation(mCoordinatorLayout, this);

            mLastFlingY = 0;
        }

        public void cancel() {
            mScroller.abortAnimation();
        }

        @Override
        public void run() {
            if (mScroller.computeScrollOffset()) {
                int dy = mScroller.getCurrY() - mLastFlingY;

                boolean moveDown = false;

                final int selfOffset;
                if (dy < 0 && ((ScrollingView) mTargetView).computeVerticalScrollOffset() > 0) {
                    moveDown = true;
                    selfOffset = 0;
                } else {
                    moveDown = false;
                    selfOffset = getTopAndBottomOffset();
                }

                //TODO 아래로 fling 시 판단조건 추가
//				final int selfOffset = getTopAndBottomOffset();
                final int newSelfOffset = Math.max(mMinOffset, Math.min(mMaxOffset, selfOffset - dy));
                final int skipped = newSelfOffset - selfOffset + dy;


                final boolean selfFinished;
                if (moveDown) {
                    // We're scrolling down
                    selfFinished = true;
                } else {
                    // We're scrolling up
                    selfFinished = !setTopAndBottomOffset(newSelfOffset);

                    Log.d("BEHAVIOR", "ViewFlinger ## scrollup : selfFinished = " + selfFinished);
                }

//				final boolean selfFinished = !setTopAndBottomOffset(newSelfOffset);

                final int targetOffset;
                final boolean targetFinished;
                if (mTargetView instanceof ScrollingView) {
                    targetOffset = ((ScrollingView) mTargetView).computeVerticalScrollOffset();
                    mTargetView.scrollBy(0, skipped);
                    targetFinished = (targetOffset == ((ScrollingView) mTargetView).computeVerticalScrollOffset());
                } else {
                    targetOffset = mTargetView.getScrollY();
                    mTargetView.scrollBy(0, skipped);
                    targetFinished = (targetOffset == mTargetView.getScrollY());
                }


                final boolean scrollerFinished = mScroller.isFinished();

                if (scrollerFinished || (selfFinished && targetFinished)) {
                    return;
                }

                ViewCompat.postOnAnimation(mCoordinatorLayout, this);
//				mCoordinatorLayout.postOnAnimation(this);

                mLastFlingY = mScroller.getCurrY();
            }
        }
    }
}
