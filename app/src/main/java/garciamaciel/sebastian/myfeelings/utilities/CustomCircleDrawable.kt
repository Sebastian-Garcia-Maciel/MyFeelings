package garciamaciel.sebastian.myfeelings.utilities

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import garciamaciel.sebastian.myfeelings.R

class CustomCircleDrawable : Drawable {
    var coordenadas: RectF? = null
    var anguloBarrido: Float = 0.0F
    var anguloInicio: Float = 0.0F
    var grosorMetrica: Int = 0
    var grosorFondo: Int = 0
    var context: Context? = null
    var emociones = ArrayList<Emociones>()


    constructor(context: Context, emociones: ArrayList<Emociones>) {
        this.context = context
        grosorMetrica = context.resources.getDimensionPixelSize(R.dimen.grapWith)
        grosorFondo = context.resources.getDimensionPixelSize(R.dimen.grapBackground)
        this.emociones = emociones
    }

    override fun draw(canva: Canvas) {
        val fondo: Paint = Paint()
        fondo.style = Paint.Style.STROKE
        fondo.strokeWidth = (this.grosorFondo).toFloat()
        fondo.isAntiAlias = true
        fondo.strokeCap = Paint.Cap.ROUND
        fondo.color = context?.resources?.getColor(R.color.gray) ?: R.color.gray
        val ancho: Float = (canva.width - 25).toFloat()
        val alto: Float = (canva.height - 25).toFloat()

        coordenadas = RectF(25.0F, 25.0F, ancho, alto)
        canva.drawArc(coordenadas!!, 0.0F, 360.0F, false, fondo)

        if (emociones.size != 0){
            for (e in emociones){
                val degree: Float = (e.porcentaje*360)/100
                this.anguloBarrido = degree

                var seccion: Paint = Paint()
                seccion.style = Paint.Style.STROKE
                seccion.isAntiAlias = true
                seccion.strokeWidth = (this.grosorMetrica).toFloat()
                seccion.strokeCap = Paint.Cap.SQUARE
                seccion.color = ContextCompat.getColor(this.context!!, e.color)


                canva.drawArc(coordenadas!!, this.anguloInicio, anguloBarrido, false, seccion)

                this.anguloInicio += this.anguloBarrido
            }
        }

    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return  PixelFormat.OPAQUE
    }


}
