package fun.com.myviewcommonlib.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import fun.com.mytoolslib.LU;
import fun.com.myviewcommonlib.R;


/**
 * Created by TANG on 2016/3/29.
 */
public class CommonDialog extends Dialog {

    public static final String TAG = "CommonDialog:";

    protected Button mConfirmBtn;
    protected Button mCancelBtn;
    private EditText mEditTv;
    private OnDialogCickListener mDialogLisener;


    public CommonDialog(Context context, String title, String message,
                        String editHint, String confirm, String cancel, String scrollerMesssage, Boolean ifTouchDismiss,
                        OnDialogCickListener mLisener) {
        super(context);
        LU.he(TAG,"fsfds");
        View v = initCommon(context, title, message, editHint, confirm, cancel, ifTouchDismiss, mLisener);
        ScrollView scrollView = v.findViewById(R.id.dialog_scroller);

        if (scrollView != null && !TextUtils.isEmpty(scrollerMesssage)) {
            scrollView.setVisibility(View.VISIBLE);
            ((TextView) v.findViewById(R.id.dialog_scrollermessage)).setText(scrollerMesssage);
        }
    }


    public CommonDialog(Context context, String title, String message,
                        String editHint, String confirm, String cancel, Boolean ifTouchDismiss,
                        OnDialogCickListener mLisener) {

        super(context);
        initCommon(context, title, message, editHint, confirm, cancel, ifTouchDismiss, mLisener);

    }

    private View initCommon(Context context, String title, String message,
                            String editHint, String confirm, String cancel, Boolean ifTouchDismiss,
                            OnDialogCickListener mLisener) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        View view = LayoutInflater.from(context).
                inflate(R.layout.common_notice_dialog, null, false);
        setContentView(view);

        setCanceledOnTouchOutside(ifTouchDismiss);

        TextView mTitleTv = (TextView) findViewById(R.id.dialog_title);
        if (TextUtils.isEmpty(title)) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setText(title);
        }


        TextView mMessageTv = (TextView) findViewById(R.id.dialog_message);
        if (message == null) {
            mMessageTv.setVisibility(View.GONE);
        } else {
            mMessageTv.setText(message);
        }

        mEditTv = (EditText) findViewById(R.id.dialog_edittext);
        if (TextUtils.isEmpty(editHint)) {
            mEditTv.setVisibility(View.GONE);
        } else {
            mEditTv.setHint(editHint);
        }


        mConfirmBtn = (Button) findViewById(R.id.dialog_confirm);

        if (TextUtils.isEmpty(confirm)) {
            mConfirmBtn.setVisibility(View.GONE);
        } else {
            mConfirmBtn.setText(confirm);
        }

        mCancelBtn = (Button) findViewById(R.id.dialog_cancel);
        if (TextUtils.isEmpty(cancel)) {
            mCancelBtn.setVisibility(View.GONE);

        } else {
            mCancelBtn.setText(cancel);
        }


        mDialogLisener = mLisener;


        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogLisener != null) {
                    mDialogLisener.onConfirm(mEditTv.getText().toString());
                }
                dismiss();
            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDialogLisener != null) {
                    mDialogLisener.onCancle();
                }
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {
            LU.he(TAG, e.toString());
            e.printStackTrace();
        }
    }
}
