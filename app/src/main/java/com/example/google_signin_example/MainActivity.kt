package com.example.google_signin_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        warmupFlutterEngine();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var btn: Button = findViewById(R.id.button);

        var self = this;
        btn.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View) {
                println("click");
                startActivity(
                    FlutterActivity
                        .withCachedEngine(AddFlutterApplication.FLUTTER_ENGINE_NAME)
                        .build(self)
                );
                println("click after");
            }
        })
    }

    private fun warmupFlutterEngine() {
        val flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put(AddFlutterApplication.FLUTTER_ENGINE_NAME, flutterEngine)
    }
}