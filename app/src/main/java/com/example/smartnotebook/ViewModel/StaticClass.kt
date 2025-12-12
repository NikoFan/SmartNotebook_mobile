package com.example.smartnotebook.ViewModel

object StaticClass {
    private var _activeAccountId: Long? = null

    val activeAccountId: Long? get() = _activeAccountId

    fun SetActiveId(latestIdNumber: Long?){
        _activeAccountId = latestIdNumber
    }
}