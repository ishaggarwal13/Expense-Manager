package com.example.expensemanageri

import androidx.room.*


@Dao
interface TransactionDao {
    @Insert
    fun insertAll(vararg transaction: Transaction)

    @Delete
    fun delete(transaction: Transaction)

    @Update
    fun update(vararg transaction: Transaction)

    @Query("SELECT * from transactions")
    fun getAll(): List<Transaction>
}