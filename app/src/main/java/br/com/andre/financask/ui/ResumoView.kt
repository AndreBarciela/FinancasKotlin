package br.com.andre.financask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.andre.financask.R
import br.com.andre.financask.extension.formataParaBrasileiro
import br.com.andre.financask.model.Resumo
import br.com.andre.financask.model.Tipo
import br.com.andre.financask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(context: Context,
                 private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        with(view.resumo_card_receita) {
            setTextColor(corReceita)
            text = totalReceita.formataParaBrasileiro()
        }
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        with(view.resumo_card_despesa) {
            setTextColor(corDespesa)
            text = totalDespesa.formataParaBrasileiro()
        }
    }

    fun adicionaTotal() {
        val total = resumo.total()
        val cor = corPor(total)

        with(view.resumo_card_total) {
            setTextColor(cor)
            text = total.formataParaBrasileiro()
        }
    }

    private fun corPor(total: BigDecimal): Int {
        return if (total >= BigDecimal.ONE) {
            corReceita
        } else {
            corDespesa
        }
    }
}