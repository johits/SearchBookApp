package com.jhs.searchbookapp.data.core.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jhs.searchbookapp.data.core.local.room.entity.BookmarkEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {
    @Insert
    fun insertBookmarkId(bookmarkEntity: BookmarkEntity)

    @Update
    fun updateBookmarkId(bookmarkEntity: BookmarkEntity)

    @Query("SELECT * FROM bookmark")
    fun getBookmarks(): Flow<List<BookmarkEntity>>

    // 삭제
    @Query("DELETE FROM bookmark where isbn = :bookmarkId")
    fun deleteBookmarkId(bookmarkId: String)

    @Query("DELETE FROM bookmark")
    fun deleteAll()
}