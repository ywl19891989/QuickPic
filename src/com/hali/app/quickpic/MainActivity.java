package com.hali.app.quickpic;

import android.os.Bundle;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.view.SubMenu;

public class MainActivity extends SherlockActivity implements
		OnMenuItemClickListener {

	MenuItem _radioBtn[] = new MenuItem[3];
	MenuItem _checkBtn;
	int _lastSelect = 0;
	boolean _isBrowFolder = false;

	@Override
	public boolean onMenuItemClick(MenuItem item) {

		if (item == _checkBtn) {
			_isBrowFolder = !_isBrowFolder;
			_checkBtn.setChecked(_isBrowFolder);
		} else {
			for (int i = 0; i < 3; i++) {
				if (_radioBtn[i] == item) {
					_lastSelect = i;
				}
				_radioBtn[i].setChecked(false);
			}
			_radioBtn[_lastSelect].setChecked(true);
		}

		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getSupportMenuInflater().inflate(R.menu.gallery, menu);
		
		MenuItem galleryItem = menu.getItem(0);
		galleryItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu galleryMenu = galleryItem.getSubMenu();
		getSupportMenuInflater().inflate(R.menu.main, galleryMenu);
		
		for (int i = 0; i < 3; i++) {
			_radioBtn[i] = galleryMenu.getItem(i);
			_radioBtn[i].setOnMenuItemClickListener(this);
			_radioBtn[i].setChecked(_lastSelect == i);
		}

		_checkBtn = galleryMenu.getItem(3);
		_checkBtn.setOnMenuItemClickListener(this);
		_checkBtn.setChecked(false);

		return true;
	}
}
