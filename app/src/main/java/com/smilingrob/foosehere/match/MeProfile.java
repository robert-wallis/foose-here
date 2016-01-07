package com.smilingrob.foosehere.match;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;

import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.materialdrawer.holder.ImageHolder;
import com.mikepenz.materialdrawer.holder.StringHolder;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

public class MeProfile implements IProfile{

    @Override
    public Object withName(String name) {
        return null;
    }

    @Override
    public StringHolder getName() {
        return new StringHolder("Me Profile");
    }

    @Override
    public Object withEmail(String email) {
        return null;
    }

    @Override
    public StringHolder getEmail() {
        return null;
    }

    @Override
    public Object withIcon(Drawable icon) {
        return null;
    }

    @Override
    public Object withIcon(Bitmap bitmap) {
        return null;
    }

    @Override
    public Object withIcon(@DrawableRes int iconRes) {
        return null;
    }

    @Override
    public Object withIcon(String url) {
        return null;
    }

    @Override
    public Object withIcon(Uri uri) {
        return null;
    }

    @Override
    public Object withIcon(IIcon icon) {
        return null;
    }

    @Override
    public ImageHolder getIcon() {
        return null;
    }

    @Override
    public Object withSelectable(boolean selectable) {
        return null;
    }

    @Override
    public boolean isSelectable() {
        return false;
    }

    @Override
    public Object withIdentifier(int identifier) {
        return null;
    }

    @Override
    public int getIdentifier() {
        return 0;
    }
}
