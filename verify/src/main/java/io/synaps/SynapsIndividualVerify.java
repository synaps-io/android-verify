package io.synaps;

import android.content.Context;
import android.util.AttributeSet;

public class SynapsIndividualVerify extends SynapsVerify {
    static String baseUrl = "https://verify.synaps.io";

    public SynapsIndividualVerify(Context context) {
        super(context, baseUrl);
    }

    public SynapsIndividualVerify(Context context, AttributeSet attrs) {
        super(context, attrs, baseUrl);
    }

    public SynapsIndividualVerify(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, baseUrl);
    }
}
