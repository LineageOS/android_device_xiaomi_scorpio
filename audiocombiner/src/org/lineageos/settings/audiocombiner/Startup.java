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
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.UserHandle;
import android.provider.Settings;

public class Startup extends BroadcastReceiver {

    private AudioCombiner mAudioCombiner;

    @Override
    public void onReceive(Context context, Intent intent) {
        mAudioCombiner = new AudioCombiner();
        IntentFilter headsetFilter = new IntentFilter(AudioManager.ACTION_HEADSET_PLUG);
        IntentFilter bluetoothFilter = new IntentFilter(BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED);
        IntentFilter bluetoothOffFilter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        context.getApplicationContext().registerReceiver(mAudioCombiner, headsetFilter);
        context.getApplicationContext().registerReceiver(mAudioCombiner, bluetoothFilter);
        context.getApplicationContext().registerReceiver(mAudioCombiner, bluetoothOffFilter);

        Settings.System.putIntForUser(context.getContentResolver(),
                 Settings.System.MASTER_MONO, 1, UserHandle.USER_CURRENT);
    }
}
