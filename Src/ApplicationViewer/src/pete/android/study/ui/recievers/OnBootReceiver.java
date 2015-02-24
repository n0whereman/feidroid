package pete.android.study.ui.recievers;


import pete.android.study.ui.activities.ServiceUpdate;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*start service on phone boot*/
public class OnBootReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		ServiceUpdate.POST_WHAT = "all_apps";
		
		Intent startServiceIntent = new Intent(context, ServiceUpdate.class);
        context.startService(startServiceIntent);
	}

}
