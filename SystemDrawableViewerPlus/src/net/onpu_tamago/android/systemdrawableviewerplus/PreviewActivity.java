package net.onpu_tamago.android.systemdrawableviewerplus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.androidquery.AQuery;

public class PreviewActivity extends Activity implements
		OnCheckedChangeListener {

	private static final String TAG = "[Preview]SystemDrawableViewerPlus";
	private int mIconId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);

		Intent intent = getIntent();
		if (intent != null) {
			mIconId = intent.getIntExtra(MainActivity.EXTRA_ICONID, 0);
			AQuery $ = new AQuery(this);
			$.id(R.id.out_iconname).text(
					intent.getStringExtra(MainActivity.EXTRA_ICONNAME));
			RadioGroup edit_draw_state = ((RadioGroup) $.id(
					R.id.edit_draw_state).getView());
			edit_draw_state.setOnCheckedChangeListener(this);
			edit_draw_state.check(R.id.edit_draw_icon);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preview, menu);
		return true;

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int backgroundId = android.R.drawable.btn_default;
		int foregroundId = 0;
		switch (checkedId) {
		case R.id.edit_draw_icon:
			Log.d(TAG, "DrawIcon");
			foregroundId = mIconId;
			break;
		case R.id.edit_draw_background:
			Log.d(TAG, "DrawBackground");
			backgroundId = mIconId;
			break;
		default:
			break;
		}
		AQuery $ = new AQuery(this);
		$.id(R.id.out_preview1).background(backgroundId).image(foregroundId);
		$.id(R.id.out_preview2).background(backgroundId).image(foregroundId);
		$.id(R.id.out_preview3).background(backgroundId).image(foregroundId);
	}
}
