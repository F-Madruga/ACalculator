package com.example.acalculator

class ListStorage private constructor() {

    private val storage = mutableListOf<Operation>()

    companion object {

        private var instance: ListStorage? = null

        fun getIntance(): ListStorage {
            synchronized(this) {
                if (instance == null) {
                    instance = ListStorage()
                }
                return instance as ListStorage
            }
        }
    }

    fun insert(operation: Operation) {
        storage.add(operation)
    }

    fun getAll(): List<Operation> {
        return storage.toList()
    }
}