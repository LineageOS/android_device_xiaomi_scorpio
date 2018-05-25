/*
 * Copyright (C) 2018 The LineageOS Project
 *
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

package org.lineageos.settings.audiocombiner;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.UserHandle;
import android.provider.Settings;

public class AudioCombiner extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        if (AudioManager.ACTION_HEADSET_PLUG.equals(action) &&
                   intent.getIntExtra("state", -1) == 1) {
            Settings.System.putIntForUser(context.getContentResolver(),
                    Settings.System.MASTER_MONO, 0, UserHandle.USER_CURRENT);
        } else if (AudioManager.ACTION_HEADSET_PLUG.equals(action) &&
                   intent.getIntExtra("state", -1) == 0) {
            Settings.System.putIntForUser(context.getContentResolver(),
                    Settings.System.MASTER_MONO, 1, UserHandle.USER_CURRENT);
        } else if (BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED.equals(action) &&
                   intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, -1) == BluetoothA2dp.STATE_CONNECTED) {
            Settings.System.putIntForUser(context.getContentResolver(),
                    Settings.System.MASTER_MONO, 0, UserHandle.USER_CURRENT);
        } else if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action) &&
                   intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1) == BluetoothAdapter.STATE_OFF) {
            Settings.System.putIntForUser(context.getContentResolver(),
                    Settings.System.MASTER_MONO, 1, UserHandle.USER_CURRENT);
        }
    }
}
