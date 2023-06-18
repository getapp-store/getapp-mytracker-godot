package ru.rustore.mytracker

import com.my.tracker.MyTracker as Tracker
import org.godotengine.godot.Godot
import org.godotengine.godot.plugin.GodotPlugin
import org.godotengine.godot.plugin.UsedByGodot

class MyTracker(godot: Godot?) : GodotPlugin(godot) {
    override fun getPluginName(): String {
        return "MyTracker"
    }

    @UsedByGodot
    fun init(key: String) {
        if (godot == null) {
            return
        }

        Tracker.initTracker(key, godot.requireActivity().application)
        Tracker.trackLaunchManually(godot.requireActivity())
    }

    @UsedByGodot
    fun setTrackingLaunchEnabled(trackingLaunchEnabled: Boolean) {
        Tracker.getTrackerConfig().setTrackingLaunchEnabled(trackingLaunchEnabled)
    }

    @UsedByGodot
    fun setLaunchTimeout(seconds: Int) {
        Tracker.getTrackerConfig().setLaunchTimeout(seconds)
    }

    @UsedByGodot
    fun setBufferingPeriod(seconds: Int) {
        Tracker.getTrackerConfig().setBufferingPeriod(seconds)
    }

    @UsedByGodot
    fun setForcingPeriod(seconds: Int) {
        Tracker.getTrackerConfig().setForcingPeriod(seconds)
    }

    @UsedByGodot
    fun setAutotrackingPurchaseEnabled(autotrackingPurchaseEnabled: Boolean) {
        Tracker.getTrackerConfig().setAutotrackingPurchaseEnabled(autotrackingPurchaseEnabled)
    }

    @UsedByGodot
    fun setTrackingPreinstallEnabled(trackingPreinstallEnabled: Boolean) {
        Tracker.getTrackerConfig().setTrackingPreinstallEnabled(trackingPreinstallEnabled)
    }

    @UsedByGodot
    fun setApkPreinstallParams(apkPreinstallParams: String) {
        Tracker.getTrackerConfig().setApkPreinstallParams(apkPreinstallParams)
    }

    @UsedByGodot
    fun setDebugMode(debugMode: Boolean) {
        Tracker.setDebugMode(debugMode)
    }

    @UsedByGodot
    fun setCustomParam(key: String, value: String) {
        Tracker.getTrackerParams().setCustomParam(key, value)
    }

    @UsedByGodot
    fun setEmail(email: String) {
        Tracker.getTrackerParams().setEmail(email)
    }

    @UsedByGodot
    fun setCustomUserId(id: String) {
        Tracker.getTrackerParams().setCustomUserId(id)
    }

    @UsedByGodot
    fun setAge(age: Int) {
        Tracker.getTrackerParams().setAge(age)
    }

    @UsedByGodot
    fun setPhone(phone: String) {
        Tracker.getTrackerParams().setPhone(phone)
    }

    @UsedByGodot
    fun steLange(lang: String) {
        Tracker.getTrackerParams().setLang(lang)
    }

    @UsedByGodot
    fun trackInviteEvent(params: Map<String, String>?) {
        if (params != null) {
            Tracker.trackInviteEvent(params)
        } else {
            Tracker.trackInviteEvent()
        }
    }

    @UsedByGodot
    fun trackLevelEvent(level: Int, params: Map<String, String>?) {
        if (params != null) {
            Tracker.trackLevelEvent(level, params)
        } else {
            Tracker.trackLevelEvent()
        }
    }

    @UsedByGodot
    fun trackEvent(name: String, params: Map<String, String>?) {
        if (params != null) {
            Tracker.trackEvent(name, params)
        } else {
            Tracker.trackEvent(name)
        }
    }
}