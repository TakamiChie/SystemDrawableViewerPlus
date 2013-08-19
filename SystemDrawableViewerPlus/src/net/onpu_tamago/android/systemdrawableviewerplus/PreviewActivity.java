package net.onpu_tamago.android.systemdrawableviewerplus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.WindowManager.LayoutParams;

public class PreviewActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);

		Intent intent = getIntent();
		PreviewFragment fragment = new PreviewFragment();
		if (intent != null) {
			setTitle(intent.getStringExtra(MainFragment.EXTRA_ICONNAME));
			fragment.setArguments(intent.getExtras());
		}

		FragmentTransaction transaction = getSupportFragmentManager()
				.beginTransaction();
		transaction.add(R.id.container, fragment);
		transaction.commit();

		LayoutParams params = getWindow().getAttributes();
		params.width = LayoutParams.MATCH_PARENT;
		getWindow().setAttributes(params);
	}
}
