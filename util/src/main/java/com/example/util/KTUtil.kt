import android.content.res.Resources
import android.util.TypedValue
import com.example.util.DimensionUtils


fun Int.todp(): Float {
    return DimensionUtils.dp2Px(this.toFloat()).toFloat()
}

fun Int.toTextSizeValue(): Float {
    val resources = Resources.getSystem()
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), resources.displayMetrics)
}