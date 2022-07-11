package io.synaps;

import android.os.Build;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;

class MyWebViewClient extends WebChromeClient {
    @Override
    public void onPermissionRequest(final PermissionRequest request) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            request.grant(request.getResources());
        }
    }
}