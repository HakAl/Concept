package concept.com.labtech.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import concept.com.labtech.R;
import concept.com.labtech.ui.callbacks.DrawerClickListener;
import concept.com.labtech.util.FragmentHelper;

import static concept.com.labtech.util.FragmentHelper.MAIN_FRAGMENT;

public final class WebViewActivity extends ABaseActivity {
    public static final String WEBVIEW_URL = "url";
    public static final String WEBVIEW_TITLE = "title";
    public static final int WEB_VIEW_REQUEST_CODE = 4321;

    private WebView webView;
    private LoadingRelativeLayout loadingContainer;
//    private ActionBarController actionBarController;


    public static void launch(Context context, String url)
    {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEBVIEW_URL, url);
        context.startActivity(intent);
    }

    public static void launch(Context context, String title, String url)
    {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(WEBVIEW_TITLE, title);
        intent.putExtra(WEBVIEW_URL, url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(this);

//        this.actionBarController = new ActionBarController(this);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

//        actionBarController.onPostCreate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//        actionBarController.onConfigurationChanged(newConfig);
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
//        actionBarController.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

//        actionBarController = null;
    }

    private void setView() {
        //todo
        setContentView(R.layout.activity_web);
        this.loadingContainer = (LoadingRelativeLayout) findViewById(R.id.web_activity_container);
        this.loadingContainer.setLoadingView("Loading . . . ");
        this.webView = (WebView) LayoutInflater.from(this).inflate(R.layout.webview, loadingContainer, false);
        webView.getSettings().setJavaScriptEnabled(true);
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