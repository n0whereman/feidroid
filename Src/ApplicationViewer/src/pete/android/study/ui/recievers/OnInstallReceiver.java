package pete.android.study.ui.recievers;


import pete.android.study.ui.activities.ServiceUpdate;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*start service if it detects new application or update*/
public class OnInstallReceiver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent){
		ServiceUpdate.POST_WHAT = "changes";
		ServiceUpdate.changedPackage = intent.getDataString();

		
	    Intent startServiceIntent = new Intent(context, ServiceUpdate.class);
	    context.startService(startServiceIntent);	    
	}
}
