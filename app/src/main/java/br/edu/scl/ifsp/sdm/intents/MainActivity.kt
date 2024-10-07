package br.edu.scl.ifsp.sdm.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import br.edu.scl.ifsp.sdm.intents.databinding.ActivityMainBinding
import br.edu.scl.ifsp.sdm.intents.Constantes.PARAMETRO_EXTRA

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    companion object Constantes{
        const val parametro_exta = "PARAMETRO_EXTRA"
        const val PARAMETRO_REQUEST_CODE = 0
    }

    private lateinit var parl: ActivityResultLauncher<Intent> // parametro activity result launcher (parl)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(amb.root)
//        @@ -22,22 +29,51 @@
        class MainActivity : AppCompatActivity() {
        }

        amb.entrarParametroBt.setOnClickListener {
            // INTENT EXPLÍCITA
            Intent(this, ParameterActivity::class.java).also{
                it.putExtra(PARAMETRO_EXTRA, amb.parametroTv.text.toString()) // chave e valor -> envia o valor do text view pro campo de input na outra tela
                parl.launch(it)
            }
        }

        parl = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            object: ActivityResultCallback<ActivityResult> {
                override fun onActivityResult(result: ActivityResult) {
                    if(result.resultCode == RESULT_OK){
                        result.data?.getStringExtra(PARAMETRO_EXTRA)?.let{
                            amb.parametroTv.text = it
                        }
                    }
                }
            })
        //TEM UM DESSE PRA CADA TELA
//        parl = registerForActivityResult(
//            ActivityResultContracts.StartActivityForResult()) // contrato
//        { result -> // lambda
//            if (result.resultCode == RESULT_OK) {
//                result.data?.getStringExtra(PARAMETRO_EXTRA)?.let {
//                    amb.parametroTv.text = it
//                }
//            }
//        }
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        menuInflater.inflate(R.menu.menu_main,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.viewMi -> {true}
            R.id.callMi -> {true}
            R.id.dialMi -> {true}
            R.id.pickMi -> {true}
            R.id.chooserMi -> {true}
            else -> {false}
        }
    }
}