package br.edu.scl.ifsp.sdm.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.scl.ifsp.sdm.intents.Constantes.PARAMETRO_EXTRA

import br.edu.scl.ifsp.sdm.intents.databinding.ActivityParameterBinding

class ParameterActivity : AppCompatActivity() {
    private val apb: ActivityParameterBinding by lazy{
        ActivityParameterBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(apb.root)

        intent.getStringExtra(PARAMETRO_EXTRA)?.let{
            apb.parametroEt.setText(it)
        }

        apb.mainTb.apply {
            title = getString(R.string.app_name)
            subtitle = this@ParameterActivity.javaClass.simpleName
            setSupportActionBar(this)
        }
        apb.enviarParametroBt.setOnClickListener {
            val parametro = apb.parametroEt.text.toString()
            val resultadoIntent = Intent()
            resultadoIntent.putExtra(PARAMETRO_EXTRA, parametro)
            setResult(RESULT_OK, resultadoIntent)
            finish()
        }

    }
}