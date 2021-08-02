package com.rustfisher.tutorial2020.workmanaer;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.rustfisher.tutorial2020.R;

public class WorkManagerAct extends AppCompatActivity {

    public static final String TAG = "WorkManagerAct";

    final OneTimeWorkRequest workA =
            new OneTimeWorkRequest.Builder(UploadWorker.class)
                    .addTag("workA")
                    .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_work_manager);
        findViewById(R.id.btn_workA).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WorkManager.getInstance(WorkManagerAct.this)
                        .enqueue(workA);
            }
        });
        findViewById(R.id.btn_workB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final OneTimeWorkRequest workB =
                        new OneTimeWorkRequest.Builder(UploadWorker.class)
                                .addTag("workB")
                                .build();

                WorkManager.getInstance(WorkManagerAct.this)
                        .enqueue(workB);
            }
        });
    }

    public static class UploadWorker extends Worker {
        public UploadWorker(
                @NonNull Context context,
                @NonNull WorkerParameters params) {
            super(context, params);
        }

        @Override
        public Result doWork() {

            // Do the work here--in this case, upload the images.
            Log.d(TAG, "UploadWorker " + getTags() + "-----------");

            // Indicate whether the work finished successfully with the Result
            return Result.success();
        }
    }
}
