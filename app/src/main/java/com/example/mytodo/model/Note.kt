package com.example.mytodo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "NOTE_TABLE")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteHead:String,
    val noteBody:String,
):Parcelable
