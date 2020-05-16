package com.raydevelopers.newvalley.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.raydevelopers.newvalley.models.category.CategoryX
import com.raydevelopers.newvalley.models.channel.ChannelX
import com.raydevelopers.newvalley.models.channel.LatestMedia
import com.raydevelopers.newvalley.models.channel.Sery
import com.raydevelopers.newvalley.models.newepisode.Media
import java.util.*

object RoomTypeConverters {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun channelXStringToList(data: String?): List<ChannelX> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<ChannelX>>() {

        }.type

        return gson.fromJson<List<ChannelX>>(data, listType)
    }
    @TypeConverter
    @JvmStatic
    fun channelXListToString(channelList: List<ChannelX>): String {
        return gson.toJson(channelList)
    }

    @TypeConverter
    @JvmStatic
    fun latestMediaStringToList(data: String?): List<LatestMedia> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<LatestMedia>>() {

        }.type

        return gson.fromJson<List<LatestMedia>>(data, listType)
    }
    @TypeConverter
    @JvmStatic
    fun latestMediaListToString(mediaList: List<LatestMedia>): String {
        return gson.toJson(mediaList)
    }

    @TypeConverter
    @JvmStatic
    fun seryStringToList(data: String?): List<Sery> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Sery>>() {

        }.type

        return gson.fromJson<List<Sery>>(data, listType)
    }
    @TypeConverter
    @JvmStatic
    fun seryListToString(channelList: List<Sery>): String {
        return gson.toJson(channelList)
    }

    @TypeConverter
    @JvmStatic
    fun mediaStringToList(data: String?): List<Media> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Media>>() {

        }.type

        return gson.fromJson<List<Media>>(data, listType)
    }
    @TypeConverter
    @JvmStatic
    fun mediaListToString(mediaList: List<Media>): String {
        return gson.toJson(mediaList)
    }

    @TypeConverter
    @JvmStatic
    fun stringToList(data: String?): List<CategoryX> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<CategoryX>>() {

        }.type

        return gson.fromJson<List<CategoryX>>(data, listType)
    }
    @TypeConverter
    @JvmStatic
    fun listToString(categoryList: List<CategoryX>): String {
        return gson.toJson(categoryList)
    }

}