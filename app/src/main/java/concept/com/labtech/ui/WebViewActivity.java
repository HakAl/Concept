package concept.com.labtech.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import concept.com.labtech.R;

public final class WebViewActivity extends ABaseActivity {
    public static final String WEBVIEW_URL = "url";
    public static final String WEBVIEW_TITLE = "title";
    public static final int WEB_VIEW_REQUEST_CODE = 4321;

    private WebView webView;
    private LoadingRelativeLayout loadingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);
        this.transferBillingData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.setView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.loadingContainer.removeAllViews();
        this.webView = null;
        this.loadingContainer = null;
    }

    /**
     * This is who the data in setResult() will be sent to.
     * You can use this information to validate that the recipient is allowed to receive the data.
     */
    private void transferBillingData() {
        if (this.getCallingActivity() != null) {
            this.setResult(WEB_VIEW_REQUEST_CODE);
        }
    }

    private void setView() {
        String title = getIntent().getStringExtra(WEBVIEW_TITLE);
        //todo
        setContentView(R.layout.activity_webview);
        this.loadingContainer = (LoadingRelativeLayout) findViewById(R.id.web_activity_container);
        this.loadingContainer.setLoadingView("Loading . . . " + getIntent().getStringExtra(WEBVIEW_TITLE));
        this.webView = (WebView) LayoutInflater.from(this).inflate(R.layout.webview, loadingContainer, false);
        webView.getSettings().setJavaScriptEnabled(false);
        webView.setWebViewClient(new TempClient());
        webView.loadUrl(getIntent().getStringExtra(WEBVIEW_URL));
    }

    void loadWebClient() {
        this.loadingContainer.removeAllViews();
        this.loadingContainer.addView(webView);
    }

    public class TempClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            loadWebClient();
        }
    }
}