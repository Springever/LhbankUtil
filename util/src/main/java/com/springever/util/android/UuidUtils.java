package com.springever.util.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * @author  maowenping 2018-06-11 获取uuid
 * 使用：1、UuidUtils.buidleID(context).check();2、String uuid= UuidUtils.getUUID();
 */
public class UuidUtils{
        private static final String TAG = UuidUtils.class.getName();
        private static UuidUtils device;
        private Context context;
        private final static String DEFAULT_NAME = "lhbank_system_device_id";
        private final static String DEFAULT_FILE_NAME = "lhbank_system_device_id";
        private final static String DEFAULT_DEVICE_ID = "lhbank_dervice_id";
        private final static String FILE_ANDROID = Environment.getExternalStoragePublicDirectory("Android") + File.separator + DEFAULT_FILE_NAME;
        private final static String FILE_DCIM = Environment.getExternalStoragePublicDirectory("DCIM") + File.separator + DEFAULT_FILE_NAME;
        private static SharedPreferences preferences = null;

        public UuidUtils(Context context) {
            this.context = context;
        }

        private String uuid;

        public static UuidUtils buidleID(Context context) {
            if (device == null) {
                synchronized (UuidUtils.class) {
                    if (device == null) {
                        device = new UuidUtils(context);
                    }
                }
            }
            return device;
        }

        public static String getUUID() {
            if (preferences == null) {
                return "dervice_id";
            }
            return preferences.getString(DEFAULT_DEVICE_ID, DEFAULT_DEVICE_ID);
        }

        //生成一个128位的唯一标识符
        private String createUUID() {
            return java.util.UUID.randomUUID().toString();
        }


        public void check() {
            preferences = context.getSharedPreferences(DEFAULT_NAME, 0);
            uuid = preferences.getString(DEFAULT_DEVICE_ID, null);
            if (uuid == null) {
                if (checkAndroidFile() == null && checkDCIMFile() == null) {
                    uuid = createUUID();
                    saveAndroidFile(uuid);
                    saveDCIMFile(uuid);
                }

                if (checkAndroidFile() == null) {
                    uuid = checkDCIMFile();
                    saveAndroidFile(uuid);
                }

                if (checkDCIMFile() == null) {
                    uuid = checkAndroidFile();
                    saveDCIMFile(uuid);
                }

                uuid = checkAndroidFile();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(DEFAULT_DEVICE_ID, uuid);
                editor.commit();
            } else {

                if (checkAndroidFile() == null) {
                    saveAndroidFile(uuid);
                }

                if (checkDCIMFile() == null) {
                    saveDCIMFile(uuid);
                }
            }
        }

        private String checkAndroidFile() {
            BufferedReader reader = null;
            try {
                File file = new File(FILE_ANDROID);
                reader = new BufferedReader(new FileReader(file));
                return reader.readLine();
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void saveAndroidFile(String id) {
            try {
                File file = new File(FILE_ANDROID);
                FileWriter writer = new FileWriter(file);
                writer.write(id);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String checkDCIMFile() {
            BufferedReader reader = null;
            try {
                File file = new File(FILE_DCIM);
                reader = new BufferedReader(new FileReader(file));
                return reader.readLine();
            } catch (Exception e) {
                return null;
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        private void saveDCIMFile(String id) {
            try {
                File file = new File(FILE_DCIM);
                FileWriter writer = new FileWriter(file);
                writer.write(id);
                writer.flush();
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
}
