package com.zst.xposed.mediatekphonemod;

import android.content.res.XModuleResources;
import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam;

public class Xposed implements IXposedHookInitPackageResources, IXposedHookZygoteInit, IXposedHookLoadPackage {
	
	static XModuleResources modRes;
	
	@Override
	public void handleInitPackageResources(InitPackageResourcesParam resparam) throws Throwable {
		Theming.handleInitPackageResources(resparam, modRes);
	}
	
	@Override
	public void initZygote(StartupParam startupParam) throws Throwable {
		modRes = XModuleResources.createInstance(startupParam.modulePath, null);
	}
	
	@Override
	public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
		if (lpparam.packageName.equals(FixDevSettings.PACKAGE_NAME)) {
			FixDevSettings.init(lpparam.classLoader);
        }
		FixCameraUpsideDown.load(lpparam);
		
		/* if (lpparam.equals("com.android.systemui")){
			final Class<?> hookClass = findClass("com.android.systemui.statusbar.SignalClusterViewGemini",
					lpparam.classLoader);		
			XposedBridge.hookAllConstructors(hookClass, new XC_MethodHook() {
				protected void afterHookedMethod(MethodHookParam param) throws Throwable {
					View thiz = (View) param.thisObject;
					ImageView tv = (ImageView) thiz.findViewById(thiz.getResources().getIdentifier("mobile_net_type_gemini", "id", "com.android.systemui"));
					tv.setMaxHeight(0);
					tv.setMaxWidth(0);
				}
			});
		} */
		
		
	}
	
}
