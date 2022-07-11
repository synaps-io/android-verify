package io.synaps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.util.AttributeSet;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import java.lang.String;
import java.net.URI;
import java.net.URISyntaxException;


class SynapsVerify extends WebView {
    public OnReadyListener onReadyListener;
    public OnFinishListener onFinishListener;
    protected String baseUrl;

    public SynapsVerify(Context context, String endpoint) {
        super(context);
        this.baseUrl = endpoint;
        try {
            this.init(context, null, 0);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public SynapsVerify(Context context, AttributeSet attrs, String endpoint) {
        super(context, attrs);
        this.baseUrl = endpoint;
        try {
            this.init(context, attrs, 0);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public SynapsVerify(Context context, AttributeSet attrs, int defStyleAttr, String endpoint) {
        super(context, attrs, defStyleAttr);
        this.baseUrl = endpoint;
        try {
            this.init(context, attrs, defStyleAttr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    private String colorToString(int color) {
        return String.format("%02x%02x%02x", Color.red(color), Color.green(color), Color.blue(color));
    }
    private void init(Context context, AttributeSet attrs, int defStyleAttr) throws URISyntaxException {
        this.getSettings().setJavaScriptEnabled(true);
        this.addJavascriptInterface(new SynapsWebInterface(context, this), "SynapsAndroid");
        if (attrs != null) {
            TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.synaps,
                    defStyleAttr, 0);
            String tierID = attributes.getString( R.styleable.synaps_tier);
            if (tierID != null) {
                this.baseUrl = this.appendUri(this.baseUrl, "tier=" + tierID);
            }
            int primaryColor = attributes.getColor( R.styleable.synaps_primary_color, 0);
            if (primaryColor != 0) {
                this.baseUrl = this.appendUri(this.baseUrl, "primary_color=" + colorToString(primaryColor));
            }
            int secondaryColor = attributes.getColor(R.styleable.synaps_secondary_color, 0);
            if (secondaryColor != 0) {
                this.baseUrl = this.appendUri(this.baseUrl, "secondary=" + colorToString(secondaryColor));
            }
        }
    }

    public void setOnReadyListener(OnReadyListener onReadyListener) {
        this.onReadyListener = onReadyListener;
    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }

    public String appendUri(String url, String appendQuery) throws URISyntaxException {
        URI oldUri = new URI(url);

        String newQuery = oldUri.getQuery();
        if (newQuery == null) {
            newQuery = appendQuery;
        } else {
            newQuery += "&" + appendQuery;
        }

        return new URI(oldUri.getScheme(), oldUri.getAuthority(),
                oldUri.getPath(), newQuery, oldUri.getFragment()).toString();
    }

    public void launch(String sessionID) throws  CameraAccessException {
        try {
            String url = this.appendUri(this.baseUrl, "session_id="+sessionID);
            if (ContextCompat.checkSelfPermission(
                    this.getContext(), Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED) {
                this.setWebChromeClient(new MyWebViewClient());
                this.loadUrl(url);
            } else {
                throw new CameraAccessException(CameraAccessException.CAMERA_DISABLED);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
