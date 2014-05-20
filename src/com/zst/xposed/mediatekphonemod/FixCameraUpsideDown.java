/*
 * Copyright (C) 2014 zst123
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zst.xposed.mediatekphonemod;

import android.hardware.Camera;
import android.util.Log;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class FixCameraUpsideDown {
	/**
	 * This phone's camera libs are messed up.
	 * 
	 * Apparently the Chinese manufacturers decided to do
	 * a dirty work-around by hardcoding the rotation in
	 * the stock camera instead of fixing the libs
	 */
	public static void load(LoadPackageParam lpparam) {
		final String pkgName = lpparam.packageName;
		
		if (pkgName.equals("com.android.camera")) return;
		if (pkgName.equals("com.ucamera.ucam")) return;
		/** Filter out camera that do not face this bug */
		
        try {
        	XposedBridge.hookAllConstructors(Camera.class, new XC_MethodHook() {
				@Override
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					Log.d("zst123", "(FixCameraUpsideDown) Constructor Before");
					((Camera) param.thisObject).setDisplayOrientation(0);
					Log.d("zst123", "(FixCameraUpsideDown) Constructor After");
					// Set orientation in constructor for apps
					// that don't touch the orientation
				}
			});
        	
			XposedBridge.hookAllMethods(Camera.class, "setDisplayOrientation", new XC_MethodHook() {
				@Override
				protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
					final Integer original_rotation = (Integer) param.args[0];
					Log.d("zst123", "(FixCameraUpsideDown) Original Rotation: " + original_rotation);
					
					// filter apps that misbehave
					if (pkgName.equals("com.google.android.gallery3d")) {
						param.args[0] = 270;
						/* Google's Camera seems to filter for Samsung's
						 * custom camera orientation & detects this phone
						 * by mistake.
						 * Thus, we have to specially hard code it in. */
					} else {
						param.args[0] = reverseRotation(original_rotation);
					}
					Log.d("zst123", "(FixCameraUpsideDown) Fixed Rotation: " + (Integer) param.args[0]);
				}
			});
        } catch (Throwable t) {
        	Log.d("zst123", "(FixCameraUpsideDown) ERROR: " + t.toString());
        	t.printStackTrace();
            XposedBridge.log(t);
        }
    }
	
	private static int reverseRotation(int original) {
		switch (original) {
		case 0:
			return 180;
		case 90:
			return 270;
		case 180:
			return 0;
		case 270:
			return 90;
		}
		return original;
	}
}
