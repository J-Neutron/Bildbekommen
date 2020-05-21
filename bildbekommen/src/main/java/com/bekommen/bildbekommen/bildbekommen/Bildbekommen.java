package com.bekommen.bildbekommen.bildbekommen;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bekommen.bildbekommen.MainActivity;
import java.util.ArrayList;

public class Bildbekommen {
    public static Activity activitys;
    public static ArrayList<String> main_list = new ArrayList<>();
    public int list_size;
    public int final_limit;
    public static boolean limitselected;
    public ListOfImages setList(@Nullable ArrayList<String> list) {
        main_list = list;
        list_size = list.size();
        return new ListOfImages(list);
    }

    public void execute(@Nullable Activity activity) {
        limitselected = false;
        activitys = activity;
        Intent intent = new Intent(activity, MainActivity.class);
        intent.putExtra("Selection", 1);
        activity.startActivityForResult(intent, 15);
    }

    public Limit setLimit(@NonNull int count) {
        list_size = 0;
        return new Limit(count);
    }
    public final class ListOfImages {
        private ListOfImages(@Nullable ArrayList<String> list) {
            main_list = list;
        }
        public LimitWithSelection setLimit(@NonNull int count) {
            final_limit = count;
            return new LimitWithSelection(count);
        }
        public void execute(@NonNull Activity activity) {
            limitselected = false;
            activitys = activity;
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("Selection", 3);
            activity.startActivityForResult(intent, 15);
        }
    }

    public final class Limit {
        private Limit(@Nullable int count) {
            final_limit = count;
            limitselected = true;
        }
        public Limit execute(@NonNull Activity activity) {
            limitselected = true;
            activitys = activity;
            Intent intent = new Intent(activity, MainActivity.class);
            intent.putExtra("Limit", final_limit);
            intent.putExtra("Selection", 2);
            activity.startActivityForResult(intent, 15);
            return this;
        }
    }

    public final class LimitWithSelection {
        private LimitWithSelection(@Nullable int count) {
            final_limit = count;
            limitselected = true;
        }

        public LimitWithSelection execute(@NonNull Activity activity) {
            if (list_size >= final_limit)
            {
                limitselected = false;
                new FinishActivity(activity,final_limit);
            }
            else {
                limitselected = true;
                activitys = activity;
                Intent intent = new Intent(activity, MainActivity.class);
                intent.putExtra("Limit", final_limit);
                intent.putExtra("Selection", 4);
                activity.startActivityForResult(intent, 15);
            }
            return this;
        }
    }

    public static final class FinishActivity {
        private FinishActivity(@Nullable Activity activity,int final_limit) {
            limitselected = false;
            Toast.makeText(activity, "Limit is "+final_limit, Toast.LENGTH_SHORT).show();
            return;
        }
    }
}
