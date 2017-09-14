package hbtbiz.hdoc.com.picandfond;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * Created by xyb on 2016/4/26.
 */
public class MyEditText extends EditText {
    private final String TAG = "MyEditText";
    private Drawable dRight;
    private Drawable lLeft;
    private Rect rBounds;

    public MyEditText(Context paramContext) {
        super(paramContext);
        initEditText();
    }

    public MyEditText(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        initEditText();
    }

    public MyEditText(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        initEditText();
    }

    // 初始化edittext 控件
    private void initEditText() {
        setEditTextDrawable();
        addTextChangedListener(new TextWatcher() { // 对文本内容改变进行监听
            @Override
            public void afterTextChanged(Editable paramEditable) {
            }

            @Override
            public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
            }

            @Override
            public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {
                MyEditText.this.setEditTextDrawable();
            }


        });
        
        setOnFocusChangeListener(new
        		OnFocusChangeListener() {
        	@Override
        	public void onFocusChange(View v, boolean hasFocus) {
        		if (hasFocus) {
        			// 此处为得到焦点时的处理内容
        			MyEditText.this.setEditTextDrawable();
        		} else {
        			// 此处为失去焦点时的处理内容
        			MyEditText.this.hideEditTextDrawable();
        		}
        	}
        });
        setOnFocusChangeListener(new
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    MyEditText.this.setEditTextDrawable();
                } else {
                    // 此处为失去焦点时的处理内容
                    MyEditText.this.hideEditTextDrawable();
                }
            }
        });
    }

    // 控制图片的显示
    public void setEditTextDrawable() {
        if (getText().toString().length() == 0) {
            setCompoundDrawables(this.lLeft, null, null, null);
        } else {
            setCompoundDrawables(this.lLeft, null, this.dRight, null);
        }

    }
    
    
    //隐藏右边的图标
    public void hideEditTextDrawable(){
    	setCompoundDrawables(this.lLeft, null, null, null);
    }
    

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.dRight = null;
        this.rBounds = null;

    }

    /**
     * 添加触摸事件 点击之后 出现 清空editText的效果
     */
    @Override
    public boolean onTouchEvent(MotionEvent paramMotionEvent) {
        if ((this.dRight != null) && (paramMotionEvent.getAction() == 1)) {
            this.rBounds = this.dRight.getBounds();
            int i = (int) paramMotionEvent.getRawX();// 距离屏幕的距离
            // int i = (int) paramMotionEvent.getX();//距离边框的距离
            if (i > getRight() - 3 * this.rBounds.width()) {
                setText("");
                paramMotionEvent.setAction(MotionEvent.ACTION_CANCEL);
            }
        }
        return super.onTouchEvent(paramMotionEvent);
    }

    /**
     * 显示右侧X图片的
     *
     * 左上右下
     */
    @Override
    public void setCompoundDrawables(Drawable paramDrawable1, Drawable paramDrawable2, Drawable paramDrawable3, Drawable paramDrawable4) {
        if (paramDrawable3 != null)
            this.dRight = paramDrawable3;
            this.lLeft = paramDrawable1;
        super.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
    }
}
