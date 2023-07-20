package com.algaworks.banco.modelo.atm;

import com.algaworks.banco.Conta;
import com.algaworks.banco.modelo.pagamento.DocumentoEstornavel;
import com.algaworks.banco.modelo.pagamento.DocumentoPagavel;

public class CaixaEletronica {
    public void imprimirSaldo(Conta conta){
        System.out.println("Conte:"+ conta.getAgencia() + "/" + conta.getNumero());
        System.out.println("Titular:"+ conta.getTitular().getNome());
        System.out.println("Saldo:"+ conta.getSaldo());
        System.out.println("Saldo disponível:"+ conta.getSaldoDisponivel());
    }
    public void pagar(DocumentoPagavel documento, Conta conta ){
        if (documento.estaPago()){
            throw new IllegalStateException("Documento já está pago");
        }
        conta.sacar(documento.getValorTotal());
        documento.quitarPagamento();

    }
    public void estornarPagamento(DocumentoEstornavel documento, Conta conta){
        if(!documento.estaPago()){
            throw new IllegalStateException("Documento não está pago");
        }
        conta.depositar(documento.getValorTotal());
        documento.estornarPagamento();
    }

}
