package urm.core

interface URMRegisterSetListener {
    fun onRegisterSet(index: Int, value: Int, register: URMRegister)
}
