
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.standard.base.IApplication
import com.standard.cloud.bean.UserBean
import java.lang.reflect.Type

/**
 * Created by zsl on 2020/3/8.
 */
class SharedPreUtil {

    companion object {
        private const val fileName: String = "shared_data"

        fun setValue(key: String, value: Any) {
            val type: String = value.javaClass.simpleName
            val shared: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(
                    fileName,
                    Context.MODE_PRIVATE
                )
            val editor: SharedPreferences.Editor = shared.edit()

            when (type) {
                "String" -> {
                    editor.putString(key, value as String)
                }
                "Integer" -> {
                    editor.putInt(key, value as Int)
                }
                "Boolean" -> {
                    editor.putBoolean(key, value as Boolean)
                }
                "MessageExtra", "UserBean" -> {
                    val toJson = Gson().toJson(value)
                    editor.putString(key, Gson().toJson(value))
                }
            }
            editor.apply()
        }

        fun getUser(key: String): UserBean {
            val shared: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE)
            val result: String = shared.getString(key, "")!!
            val type: Type = object : TypeToken<UserBean>() {}.type
            return Gson().fromJson(result, type)
        }


        fun getValue(key: String, defaultObj: Any): Any? {
            val gson = Gson()
            val type: String = defaultObj.javaClass.simpleName
            val shared: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE)
            when (type) {
                "String" -> {
                    return shared.getString(key, defaultObj as String)
                }
                "Integer" -> {
                    return shared.getInt(key, defaultObj as Int)
                }
                "Boolean" -> {
                    return shared.getBoolean(key, defaultObj as Boolean)
                }

                "MessageExtra" -> {
//
//                    val entityType: Type = object : TypeToken<MessageExtra>() {}.type
//                    val result: String? = shared.getString(key, "")
//                    if (TextUtils.isEmpty(result)) {
//                        return MessageExtra()
//                    }
//                    return gson.fromJson(result, entityType)
                }
            }
            return null
        }

        /**
         * 清除为key的值
         */
        fun clearValue(key: String) {
            val shared: SharedPreferences =
                IApplication.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = shared.edit()
            editor.remove(key).apply()
        }
    }
}