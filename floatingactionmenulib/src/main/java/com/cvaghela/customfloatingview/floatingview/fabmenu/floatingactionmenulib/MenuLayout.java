package com.cvaghela.customfloatingview.floatingview.fabmenu.floatingactionmenulib;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.Px;
import androidx.cardview.widget.CardView;
import androidx.core.view.ViewCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuLayout extends RelativeLayout {

    private Context mContext;

    private int mFabRotationDirection;
    private static final int MainFabRotateAngle = 135;
    private int mFabMarginRight;
    private int mFabMarginButtom;

    private int mPopupImageSize;
    private int mPopupImageMargin;
    private int mFabSize;
    private boolean mShowText;
    private boolean mShowCustomSizePopup;
    private boolean mShowTextbg;
    private @Px int mTextSize;
    private int mTextColor;
    private int mTextBgColor;
    private int mOpenMenuIconColor;
    private int mMainFabBgColor;
    private boolean mShowImageBg;
    private int mImageBgColor;
    private int mTextImageMargin;
    private List<String> mListTextStr;
    private List<Integer> mListImageResoureId;

    private FloatingActionButton mMainButton;

    private View mAlphaView;
    private LinearLayout menuLayout;
    private boolean isStart = false;

    private static final int mElevation = 6;
    private static final int mTextBgRadius = 8;
    private static final int mTextPadding = 10;
    private int mainIconColor;
    private OnItemClickListener onItemClickListener;


    public MenuLayout(Context context) {
        this(context, null);
    }

    public MenuLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mFabRotationDirection = 1;
        mFabMarginRight = mFabMarginButtom = Utils.dip2px(mContext, 15);
        mShowText = true;
        mShowTextbg = true;
        mShowCustomSizePopup = false;
        mTextColor = Color.BLACK;
        mTextSize = Utils.px2sp(mContext, 10);
        mTextBgColor = Color.WHITE;
        mMainFabBgColor = Color.WHITE;
        mShowImageBg = true;
        mImageBgColor = Color.WHITE;
        mOpenMenuIconColor = Color.WHITE;
        mFabSize = Utils.dip2px(mContext, 56);
        mPopupImageSize = Utils.dip2px(mContext, 40);
        mPopupImageMargin = Utils.dip2px(mContext, 20);
        mTextImageMargin = Utils.dip2px(mContext, 12);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MenuLayout, defStyleAttr, 0);
        if (typedArray != null) {
            mFabRotationDirection = typedArray.getInt(R.styleable.MenuLayout_fab_rotation_direction, mFabRotationDirection);
            mFabMarginRight = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_fab_margin_right, mFabMarginRight);
            mFabMarginButtom = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_fab_margin_buttom, mFabMarginButtom);
            mShowText = typedArray.getBoolean(R.styleable.MenuLayout_show_text, mShowText);
            mShowCustomSizePopup = typedArray.getBoolean(R.styleable.MenuLayout_show_custom_size_popup, mShowCustomSizePopup);
            mShowTextbg = typedArray.getBoolean(R.styleable.MenuLayout_show_text_bg, mShowTextbg);
            mTextColor = typedArray.getColor(R.styleable.MenuLayout_textColor, mTextColor);
            mTextSize = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_textSize, mTextSize);
            mTextBgColor = typedArray.getColor(R.styleable.MenuLayout_text_bg_color, mTextBgColor);
            mMainFabBgColor = typedArray.getColor(R.styleable.MenuLayout_fab_img_tint_color, mMainFabBgColor);
            mOpenMenuIconColor = typedArray.getColor(R.styleable.MenuLayout_open_menu_icon_color, mOpenMenuIconColor);
            mShowImageBg = typedArray.getBoolean(R.styleable.MenuLayout_show_image_bg, mShowImageBg);
            mImageBgColor = typedArray.getColor(R.styleable.MenuLayout_image_bg_color, mImageBgColor);
            mFabSize = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_fab_Size, mFabSize);
            mPopupImageSize = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_popup_fab_size, mPopupImageSize);
            mPopupImageMargin = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_popup_fab_margin, mPopupImageMargin);
            mTextImageMargin = typedArray.getDimensionPixelSize(R.styleable.MenuLayout_text_image_maragin, mTextImageMargin);
            typedArray.recycle();
        }

        AddShadowView();

        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        lp.rightMargin = mFabMarginRight;
        lp.bottomMargin = mFabMarginButtom;

        mMainButton = new FloatingActionButton(mContext);
        mMainButton.setId(R.id.menuLayout_fab_view);
        mMainButton.setCompatElevation(Utils.dip2px(mContext, mElevation));
        mMainButton.setImageTintList(ColorStateList.valueOf(mMainFabBgColor));
        mMainButton.setCustomSize(mFabSize);
        mMainButton.setScaleType(ImageView.ScaleType.CENTER);
        mMainButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                popupMenu();
            }
        });

        addView(mMainButton, lp);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int HeightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (HeightMode == MeasureSpec.AT_MOST) {
            int HeightSize = MeasureSpec.getSize(heightMeasureSpec);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(HeightSize, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private void createMenuView() {


        if (mListImageResoureId != null && mListImageResoureId.size() > 0) {
            menuLayout = createLinearLayout(LinearLayout.VERTICAL);
            int size = mListImageResoureId.size();
            for (int i = 0; i < size; i++) {
                String textStr = "";
                if (mListTextStr != null && mListTextStr.size() > i) {
                    textStr = mListTextStr.get(i);
                }
                View view = createMenuItem(i, textStr, mListImageResoureId.get(i));
                if (mShowText) {
                    menuLayout.addView(view);
                } else {
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.topMargin = mPopupImageMargin / 2;
                    layoutParams.bottomMargin = mPopupImageMargin / 2;
                    if (view instanceof FloatingActionButton) {
                        layoutParams.rightMargin = mFabMarginRight + mFabSize / 2 - mPopupImageSize / 2;
                    } else if (view instanceof ImageView) {
                        int imageSize = Utils.getImageSize(mContext, mListImageResoureId.get(i));
                        layoutParams.rightMargin = mFabMarginRight + mFabSize / 2 - imageSize / 2;
                    }
                    menuLayout.addView(view, layoutParams);
                }
            }

            LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(ABOVE, R.id.menuLayout_fab_view);
            layoutParams.bottomMargin = mPopupImageMargin / 2;
            menuLayout.setVisibility(INVISIBLE);
            menuLayout.setScaleY(0);
            menuLayout.setAlpha(0);
            addView(menuLayout, layoutParams);

        }


    }

    private void AddShadowView() {

        mAlphaView = new View(mContext);
        mAlphaView.setBackgroundColor(Color.BLACK);
        //0完全透明
        mAlphaView.setId(R.id.menuLayout_alpha_view);
        mAlphaView.setAlpha(0);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        if (mContext instanceof Activity) {
            Activity activity = (Activity) mContext;
            ViewGroup viewGroup = activity.findViewById(android.R.id.content);
            if (viewGroup != null) {
                viewGroup.addView(mAlphaView);
            }
        } else {
            addView(mAlphaView, layoutParams);
        }

    }

    private View createMenuItem(int position, String textStr, int resourceId) {

        if (mShowCustomSizePopup) {
            LinearLayout linearLayout = createLinearLayout(LinearLayout.HORIZONTAL);

            linearLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);

            ImageView imageView = creatImageView(position, resourceId);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.topMargin = mPopupImageMargin / 6;
            layoutParams.bottomMargin = mPopupImageMargin / 6;
            layoutParams.rightMargin = mFabMarginRight;
            layoutParams.leftMargin = mTextImageMargin;
            linearLayout.addView(imageView, layoutParams);
            return linearLayout;
        } else {
            if (mShowText) {
                LinearLayout linearLayout = createLinearLayout(LinearLayout.HORIZONTAL);

                linearLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.END);
                TextView textView = createTextView(position, textStr);
                if (mShowTextbg) {
                    CardView cardView = createCardView();
                    cardView.addView(textView);

                    linearLayout.addView(cardView);
                } else {
                    linearLayout.addView(textView);
                }
                if (mShowImageBg) {
                    FloatingActionButton floatingActionButton = createFAB(position, mImageBgColor, resourceId);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.topMargin = mPopupImageMargin / 2;
                    layoutParams.bottomMargin = mPopupImageMargin / 2;
                    layoutParams.rightMargin = mFabMarginRight + mFabSize / 2 - mPopupImageSize / 2;
                    layoutParams.leftMargin = mTextImageMargin;
                    linearLayout.addView(floatingActionButton, layoutParams);
                } else {
                    ImageView imageView = creatImageView(position, resourceId);
                    int imageSize = Utils.getImageSize(mContext, resourceId);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.topMargin = mPopupImageMargin / 2;
                    layoutParams.bottomMargin = mPopupImageMargin / 2;
                    layoutParams.rightMargin = mFabMarginRight + mFabSize / 2 - imageSize / 2;
                    layoutParams.leftMargin = mTextImageMargin;
                    linearLayout.addView(imageView, layoutParams);
                }
                return linearLayout;
            } else {
                if (mShowImageBg) {
                    return createFAB(position, mImageBgColor, resourceId);

                } else {
                    return creatImageView(position, resourceId);
                }
            }
        }
    }

    private CardView createCardView() {
        CardView cardView = new CardView(mContext);
        cardView.setCardElevation(Utils.dip2px(mContext, mElevation));
        cardView.setCardBackgroundColor(mTextBgColor);
        cardView.setContentPadding(Utils.dip2px(mContext, mTextPadding), Utils.dip2px(mContext, mTextPadding), Utils.dip2px(mContext, mTextPadding), Utils.dip2px(mContext, mTextPadding));
        cardView.setRadius(Utils.dip2px(mContext, mTextBgRadius));
        cardView.setUseCompatPadding(true);
        cardView.setPreventCornerOverlap(true);
        return cardView;
    }

    private FloatingActionButton createFAB(final int position, int mbgColor, final int resId) {
        FloatingActionButton floatingActionButton = new FloatingActionButton(mContext);
        ViewCompat.setBackgroundTintList(floatingActionButton, ColorStateList.valueOf(mbgColor));
        floatingActionButton.setImageResource(resId);

        floatingActionButton.setCustomSize(mPopupImageSize);
        floatingActionButton.setScaleType(ImageView.ScaleType.CENTER);
        floatingActionButton.setCompatElevation(Utils.dip2px(mContext, mElevation));
        floatingActionButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onImageItemClickListener(position, resId);
                }
            }
        });
        return floatingActionButton;
    }

    private TextView createTextView(final int position, final String text) {
        TextView textView = new TextView(mContext);
        textView.setTextColor(mTextColor);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(Utils.px2sp(mContext, mTextSize));
        textView.setText(text);
        textView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onTextItemClickListener(position, text);
                }
            }
        });
        return textView;
    }

    private ImageView creatImageView(final int position, final int resourceId) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(resourceId);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        imageView.setBackgroundDrawable(null);
        imageView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onImageItemClickListener(position, resourceId);
                }
            }
        });
        return imageView;
    }

    private LinearLayout createLinearLayout(int orientation) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(orientation);
        return linearLayout;
    }

    public MenuLayout setListText(String... listText) {
        if (listText != null && listText.length > 0) {
            mListTextStr = new ArrayList<>(listText.length);

            this.mListTextStr.addAll(Arrays.asList(listText));
        }
        return this;
    }

    public MenuLayout setListImageResource(Integer... listImageResourceId) {
        if (listImageResourceId != null && listImageResourceId.length > 0) {
            this.mListImageResoureId = new ArrayList<>(listImageResourceId.length);
            this.mListImageResoureId.addAll(Arrays.asList(listImageResourceId));
        }

        return this;
    }

    public void createMenu() {
        createMenuView();
    }

    public MenuLayout setMainButtonColorAndIcon(@ColorRes int resColorId, @DrawableRes int resImageId) {
        if (mMainButton != null) {

            mainIconColor = resColorId;
            ViewCompat.setBackgroundTintList(mMainButton, ColorStateList.valueOf(getResources().getColor(mainIconColor)));
            mMainButton.setImageResource(resImageId);
        }
        return this;
    }

    public MenuLayout setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private void startAnim() {
        int ratation_2 = MainFabRotateAngle + 20;
        int ratation_1 = MainFabRotateAngle;
        if (mFabRotationDirection == 2) {
            ratation_1 = -ratation_1;
            ratation_2 = -ratation_2;
        }
        if (menuLayout != null) {
            if (menuLayout.getPivotY() != menuLayout.getHeight()) {
                menuLayout.setPivotY(menuLayout.getHeight());
            }
            if (menuLayout.getVisibility() != VISIBLE) {
                menuLayout.setVisibility(VISIBLE);
            }
        }
        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.setInterpolator(new LinearInterpolator());
        ObjectAnimator fab = ObjectAnimator.ofFloat(mMainButton, "rotation", 0, ratation_2, ratation_1);
        fab.setDuration(100);
        ObjectAnimator alphaView = ObjectAnimator.ofFloat(mAlphaView, "alpha", 0, 0.4f);
        alphaView.setDuration(100);
        ObjectAnimator alphaMenuView = ObjectAnimator.ofFloat(menuLayout, "alpha", 0, 1);
        alphaMenuView.setDuration(200);
        ObjectAnimator scaleYMenuView = ObjectAnimator.ofFloat(menuLayout, "scaleY", 0, 0.4f, 1);
        scaleYMenuView.setDuration(200);

        animatorSet.play(fab).with(alphaView);
        animatorSet.play(alphaView).before(alphaMenuView);
        animatorSet.play(alphaMenuView).with(scaleYMenuView);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewCompat.setBackgroundTintList(mMainButton, ColorStateList.valueOf(mOpenMenuIconColor));
                isStart = true;

            }
        });

        animatorSet.start();

    }

    private void popupMenu() {
        if (isStart) {
            endAnim();

        } else {
            startAnim();
        }
    }

    private void endAnim() {
        int ratation_2 = MainFabRotateAngle;
        int ratation_1 = 20;
        if (mFabRotationDirection == 2) {
            ratation_1 = -ratation_1;
            ratation_2 = -ratation_2;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator fab = ObjectAnimator.ofFloat(mMainButton, "rotation", ratation_2, ratation_1, 0);
        ObjectAnimator alphaView = ObjectAnimator.ofFloat(mAlphaView, "alpha", 0.4f, 0);
        ObjectAnimator alphaMenuView = ObjectAnimator.ofFloat(menuLayout, "alpha", 1, 0);

        animatorSet.playTogether(fab, alphaView, alphaMenuView);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isStart = false;
                ViewCompat.setBackgroundTintList(mMainButton, ColorStateList.valueOf(getResources().getColor(mainIconColor)));

                if (menuLayout != null) {
                    menuLayout.setVisibility(INVISIBLE);
                }
            }
        });

        animatorSet.start();
    }


    public interface OnItemClickListener {
        void onTextItemClickListener(int position, String str);

        void onImageItemClickListener(int position, @DrawableRes int resId);

    }


}

