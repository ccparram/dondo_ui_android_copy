package com.dondo.demo

import androidx.multidex.MultiDexApplication
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import timber.log.Timber
import timber.log.Timber.DebugTree

class DemoApplication : MultiDexApplication() {

	override fun onCreate() {
		super.onCreate()

		initLogger()
	}

	private fun initLogger() {
		val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
			.showThreadInfo(false)
			.tag("DEMO_LOGGER")
			.methodCount(1)
			.methodOffset(4)
			.build()

		Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

		Timber.plant(object : DebugTree() {
			override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
				Logger.log(priority, tag, message, t)
			}
		})
	}
}
