package intership.dev.contact.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import intership.dev.contact.R;

/**
 * Class custom action bar
 * Created by Hop on 20/07/2015.
 */
public class ActionBarCustom extends RelativeLayout implements View.OnClickListener {
    /**
     * The logging tag is used by this class
     */
    public static final String TAG = ActionBarCustom.class.getSimpleName();

    /**
     * Interface definition for a callback to be invoked when a view of Actionbar is clicked.
     */
    public interface OnHeaderBarListener {
        /**
         * Called when left view item has been clicked
         *
         * @param v The view was clicked
         */
        void onLeftItemClick(View v);

        /**
         * Called when left view item has been clicked
         *
         * @param v The view was clicked
         */
        void onRightItemClick(View v);
    }

    /**
     * Listener is used to dispatch click events
     */
    private OnHeaderBarListener mOnHeaderBarListener;

    private Context mContext;
    private View mRootView;
    private ImageView mImgViewLeft;
    private ImageView mImgViewRight;
    private TextView mTxtViewTitle;

    /**
     * Simple constructor to use when creating a View from code
     *
     * @param context The context which the view is running in
     */
    public ActionBarCustom(Context context) {
        super(context);
        init(context);
    }

    /**
     * This constructor is used to inflate a view from XML and supply attributes
     * that were specified in the XML file
     *
     * @param context The context which the view is running in
     * @param attrs   The attributes of the XML tag that is inflating the view
     */
    public ActionBarCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        setAttributeSet(context, attrs);
    }

    /**
     * Perform inflation from XML, supplying attributes that were specified in the
     * XML file and apply a class-specific base style from a theme attribute
     *
     * @param context      The context which the view is running in
     * @param attrs        The attributes of the XML tag that is inflating the view
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource. Can be 0 to not look for
     *                     defaults
     */
    public ActionBarCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        setAttributeSet(context, attrs);
    }

    /**
     * Call when a view is clicked
     *
     * @param v The view is clicked
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.imgLeft) {
            if (mOnHeaderBarListener != null) {
                mOnHeaderBarListener.onLeftItemClick(v);
            } else {
                ((Activity) getContext()).onBackPressed();
            }
        }
        if (id == R.id.imgRight) {
            if (mOnHeaderBarListener != null) {
                mOnHeaderBarListener.onRightItemClick(v);
            }
        }
    }

    /**
     * Inflate the view from the XML file
     *
     * @param context The context that the view is running in
     */
    private void init(Context context) {
        mContext = context;
        mRootView = LayoutInflater.from(context).inflate(R.layout.custom_actionbar, this, false);
        addView(mRootView);

        mTxtViewTitle = (TextView) mRootView.findViewById(R.id.tvTitle);
        mImgViewLeft = (ImageView) mRootView.findViewById(R.id.imgLeft);
        mImgViewRight = (ImageView) mRootView.findViewById(R.id.imgRight);

        mImgViewLeft.setOnClickListener(this);
        mImgViewRight.setOnClickListener(this);
        setDefaultUI();

    }

    /**
     * Set default UI for ActionBar
     */
    private void setDefaultUI() {
        mImgViewLeft.setVisibility(GONE);
    }

    /**
     * Set values for attributes of XML tag, styled attribute information is contained in attrs.xml
     *
     * @param context The context that the view is running in
     * @param attrs   The attributes of the XML tag that is inflating the view
     */
    private void setAttributeSet(Context context, AttributeSet attrs) {
        TypedArray typedArr = context.obtainStyledAttributes(attrs, R.styleable.ActionBarCustom);

        //get
        String title = typedArr.getString(R.styleable.ActionBarCustom_actionbar_title);
        int left_icon = typedArr.getResourceId(R.styleable.ActionBarCustom_left_icon, 0);
        int right_icon = typedArr.getResourceId(R.styleable.ActionBarCustom_right_icon, 0);

        //set
        if (title != null) {
            mTxtViewTitle.setText(title);
        }

        if (left_icon == 0) {
            mImgViewLeft.setVisibility(GONE);
        } else {
            mImgViewLeft.setImageResource(left_icon);
            mImgViewLeft.setVisibility(VISIBLE);
        }

        if (right_icon == 0) {
            mImgViewRight.setVisibility(GONE);
        } else {
            mImgViewRight.setImageResource(right_icon);
            mImgViewRight.setVisibility(VISIBLE);
        }

        typedArr.recycle();
    }

    /**
     * Register a callback to be invoked when this view is clicked. If this view
     * is not clickable, ti becomes cickable
     *
     * @param listener The callback that will run
     */
    public void setOnHeaderBarListener(OnHeaderBarListener listener) {
        if (!isClickable()) {
            setClickable(true);
        }
        mOnHeaderBarListener = listener;
    }

    /**
     * Set title for the custom action bar
     *
     * @param title The title wll be displayed
     */
    public void setTitle(String title) {
        mTxtViewTitle.setText(title);
    }

}
