/*
 * Copyright (C) 2013 zst123
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

import android.content.res.XModuleResources;
import android.widget.TextView;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;
import de.robv.android.xposed.callbacks.XC_InitPackageResources.InitPackageResourcesParam;

public class Theming {
	public static void handleInitPackageResources(InitPackageResourcesParam resparam, XModuleResources modRes) {
		if (resparam.packageName.equals("com.android.phone")) {
			// Theme the In call layout to be more holo
			resparam.res.hookLayout("com.android.phone", "layout", "call_banner", new XC_LayoutInflated() {
				@Override
				public void handleLayoutInflated(LayoutInflatedParam liparam) throws Throwable {
					TextView tv = (TextView)liparam.view.findViewById(liparam.
							res.getIdentifier("simIndicator", "id", "com.android.phone"));
					tv.setBackgroundResource(0);
					tv.setTextSize(0f);
				}
			});
			resparam.res.setReplacement("com.android.phone", "drawable", "call_add_call_icon", modRes.fwd(R.drawable.call_add_call_icon));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_add_call_icon_dim", modRes.fwd(R.drawable.call_add_call_icon_dim));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_add_call_icon_press", modRes.fwd(R.drawable.call_add_call_icon_press));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_dial_btn_focus", modRes.fwd(R.drawable.call_dial_btn_normal_focused));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_dial_btn_normal", modRes.fwd(R.drawable.call_dial_btn_normal));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_dial_btn_press", modRes.fwd(R.drawable.call_dial_btn_normal_pressed));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_end_call_icon_dim", modRes.fwd(R.drawable.call_end_call_icon_dim));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_end_call_icon", modRes.fwd(R.drawable.call_end_call_icon));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_end_call_icon_press", modRes.fwd(R.drawable.call_end_call_icon_press));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_red_btn_disable_focused", modRes.fwd(R.drawable.call_red_btn_disable_focused));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_red_btn_normal", modRes.fwd(R.drawable.call_red_btn_normal));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_red_btn_pressed", modRes.fwd(R.drawable.call_red_btn_pressed));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_red_btn_selected", modRes.fwd(R.drawable.call_red_btn_selected));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable_focused", modRes.fwd(R.drawable.call_default_btn_disable_focused));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable", modRes.fwd(R.drawable.call_default_btn_disable));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable_focused_switch_off", modRes.fwd(R.drawable.call_default_btn_disable_focused_switch_off));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable_focused_switch_on", modRes.fwd(R.drawable.call_default_btn_disable_focused_switch_on));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable_switch_off", modRes.fwd(R.drawable.call_default_btn_disable_switch_off));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_disable_switch_on", modRes.fwd(R.drawable.call_default_btn_disable_switch_on));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_normal", modRes.fwd(R.drawable.call_default_btn_normal));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_normal_switch_off", modRes.fwd(R.drawable.call_default_btn_normal_switch_off));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_normal_switch_on", modRes.fwd(R.drawable.call_default_btn_normal_switch_on));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_pressed", modRes.fwd(R.drawable.call_default_btn_pressed));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_pressed_switch_off", modRes.fwd(R.drawable.call_default_btn_pressed_switch_off));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_pressed_switch_on", modRes.fwd(R.drawable.call_default_btn_pressed_switch_on));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_selected", modRes.fwd(R.drawable.call_default_btn_selected));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_selected_switch_off", modRes.fwd(R.drawable.call_default_btn_selected_switch_off));
			resparam.res.setReplacement("com.android.phone", "drawable", "call_default_btn_selected_switch_on", modRes.fwd(R.drawable.call_default_btn_selected_switch_on));
			//resparam.res.setReplacement("com.android.phone", "drawable", "incall_status_color3", modRes.fwd(R.drawable.incall_status_color3));
			//resparam.res.setReplacement("com.android.phone", "drawable", "incall_status_color7", modRes.fwd(R.drawable.incall_status_color7));
			resparam.res.setReplacement("com.android.phone", "drawable", "picture_unknown", modRes.fwd(R.drawable.picture_unknown_old));
		} else if (resparam.packageName.equals("com.android.systemui")) {
			/* Remove the blue bg from status bar */
			resparam.res.setReplacement("com.mediatek", "drawable", "sim_background_blue", modRes.fwd(R.drawable.sim_background_purple));
		}
	}
}
