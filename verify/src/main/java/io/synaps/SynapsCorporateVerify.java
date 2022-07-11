package io.synaps;

import android.content.Context;
import android.util.AttributeSet;

public class SynapsCorporateVerify extends SynapsVerify {
    static String baseUrl = "https://verify-v3.synaps.io";

    public SynapsCorporateVerify(Context context) {
        super(context, baseUrl);
    }

    public SynapsCorporateVerify(Context context, AttributeSet attrs) {
        super(context, attrs, baseUrl);
    }

    public SynapsCorporateVerify(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr, baseUrl);
    }
}
