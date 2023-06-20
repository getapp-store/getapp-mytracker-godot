package ru.rustore.mytracker

import com.my.tracker.ads.AdEvent
import com.my.tracker.ads.AdEventBuilder
import com.my.tracker.ads.AdFormat
import com.my.tracker.ads.AdNetwork
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
    fun setLang(lang: String) {
        Tracker.getTrackerParams().setLang(lang)
    }

    @Suppress("UNCHECKED_CAST")
    @UsedByGodot
    fun trackInviteEvent(p: org.godotengine.godot.Dictionary) {
        val params = p.toMap().filterValues {
            it is String
        } as? Map<String, String>

        Tracker.trackInviteEvent(params)
    }

    @Suppress("UNCHECKED_CAST")
    @UsedByGodot
    fun trackLevelEvent(level: Int, p: org.godotengine.godot.Dictionary) {
        val params = p.toMap().filterValues {
             it is String
         } as? Map<String, String>

        Tracker.trackLevelEvent(level, params)
    }

    @Suppress("UNCHECKED_CAST")
    @UsedByGodot
    fun trackEvent(name: String, p: org.godotengine.godot.Dictionary) {
        val params = p.toMap().filterValues {
            it is String
        } as? Map<String, String>

        Tracker.trackEvent(name, params)
    }

    @UsedByGodot
    fun trackAdEvent(name: String, network: Int, params: org.godotengine.godot.Dictionary) {
        var event: AdEventBuilder? = null

        if (name == EVENT_IMPRESSION) {
            event = AdEventBuilder.newImpressionBuilder(network)
        }

        if (name == EVENT_CLICK) {
            event = AdEventBuilder.newClickBuilder(network)
        }

        if (name == EVENT_REVENUE) {
            event = AdEventBuilder.newRevenueBuilder(
                network,
                params.get(AD_REVENUE) as? Double ?: 0.0,
                params.get(AD_CURRENCY) as? String ?: "")
        }

        if (params.containsKey(AD_SOURCE)) {
            event?.withSource(params[AD_SOURCE] as? String)
        }

        if (params.containsKey(AD_ID)) {
            event?.withAdId(params[AD_ID] as? String)
        }

        if (params.containsKey(AD_PLACEMENT_ID)) {
            event?.withAdId(params[AD_PLACEMENT_ID] as? String)
        }

        if (params.containsKey(AD_FORMAT)) {
            event?.withAdFormat(params[AD_FORMAT] as? String)
        }

        Tracker.trackAdEvent(event?.build())

        AdNetwork.ADMOB
    }

    companion object {
        const val EVENT_IMPRESSION = "impression"
        const val EVENT_CLICK = "click"
        const val EVENT_REVENUE = "revenue"

        const val AD_REVENUE = "revenue"
        const val AD_CURRENCY = "currency"
        const val AD_SOURCE = "source"
        const val AD_ID = "ad_id"
        const val AD_PLACEMENT_ID = "placement_id"
        const val AD_FORMAT = "format"
    }
}