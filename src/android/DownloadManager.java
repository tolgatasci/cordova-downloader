package downloadmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * This class echoes a string called from JavaScript.
 */
public class DownloadManager extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {
            String message = args.getString(0);
	    String file_name = 	args.getString(1);
            this.startDownload(message,file_name, callbackContext);
            return true;
        }
        return false;
    }

    private void startDownload(String message, String file_name, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            String filename = message.substring(message.lastIndexOf("/")+1, message.length());
            try {
                filename = URLDecoder.decode(filename,"UTF-8");
            } catch (UnsupportedEncodingException e) {

                callbackContext.error("Error in converting filename");
            }
            android.app.DownloadManager downloadManager = (android.app.DownloadManager) cordova.getActivity().getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);            
            Uri Download_Uri = Uri.parse(message);
            android.app.DownloadManager.Request request = new android.app.DownloadManager.Request(Download_Uri);
            
            request.setAllowedNetworkTypes(android.app.DownloadManager.Request.NETWORK_WIFI | android.app.DownloadManager.Request.NETWORK_MOBILE);
          
            request.setAllowedOverRoaming(false);

            request.setTitle(file_name);

            request.setDescription("Cici Müzik");

            request.setDestinationInExternalFilesDir(cordova.getActivity().getApplicationContext(), Environment.DIRECTORY_MUSIC, file_name);

            request.setNotificationVisibility(android.app.DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            long downloadReference = downloadManager.enqueue(request);
            callbackContext.success(Environment.DIRECTORY_MUSIC+'/'+file_name);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
