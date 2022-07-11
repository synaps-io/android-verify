package io.synaps;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class SynapsWebInterface {
    Context mContext;
    SynapsVerify synaps;

    /** Instantiate the interface and set the context */
    SynapsWebInterface(Context c, SynapsVerify synaps) {
        mContext = c;
        this.synaps = synaps;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void onReady() {
        if(synaps.onReadyListener != null) {
            this.synaps.onReadyListener.onReady();
        }
    }
    /** Show a toast from the web page */
    @JavascriptInterface
    public void onFinish() {
        if(synaps.onFinishListener != null) {
            this.synaps.onFinishListener.onFinish();
        }
    }
}
