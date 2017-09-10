package com.mygdx.game;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = "AndroidLauncher";
	protected AdView adView;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RelativeLayout layout = new RelativeLayout(this);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode = true;
		View view = initializeForView(new MyGdxGame(), config);
		layout.addView(view);

		adView = new AdView(this);
		adView.setAdListener(new AdListener() {
			@Override
			public void onAdLoaded() {
				Log.i(TAG, "Ad loaded...");
			}
		});
		/*
		DisplayMetrics dm = getResources().getDisplayMetrics();

		double density = dm.density * 160;
		double x = Math.pow(dm.widthPixels / density, 2);
		double y = Math.pow(dm.heightPixels / density, 2);
		double screenInches = Math.sqrt(x + y);


		if (screenInches > 8) { // > 728 X 90
			adView.setAdSize(AdSize.LEADERBOARD);
		} else if (screenInches > 6) { // > 468 X 60
			adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
		} else { // > 320 X 50
			adView.setAdSize(AdSize.BANNER);
		}
		*/
		adView.setAdUnitId("ca-app-pub-8448913483080652/2975233819");

		AdRequest.Builder builder = new AdRequest.Builder();
		builder.addTestDevice("0C62BBE184A2FB60E6D2D9FB8AE29F62");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT
		);
		//adParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

		layout.addView(adView, adParams);
		adView.loadAd(builder.build());
		setContentView(layout);
	}
}
