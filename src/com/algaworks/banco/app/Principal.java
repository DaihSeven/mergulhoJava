package com.algaworks.banco.app;

import com.algaworks.banco.ContaEspecial;
import com.algaworks.banco.ContaInvestimento;
import com.algaworks.banco.Pessoa;
import com.algaworks.banco.TipoPessoa;
import com.algaworks.banco.modelo.atm.CaixaEletronica;
import com.algaworks.banco.modelo.excecao.SaldoInsuficienteException;
import com.algaworks.banco.modelo.pagamento.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Principal {
    public static void main(String[] args) {
        Pessoa titular1 = new Pessoa();
        titular1.setNome("Daiane");
        titular1.setDocumento ("0983672");
        titular1.setRendimentoAnual(new BigDecimal("15000"));
        titular1.setTipo(TipoPessoa.JURIDICA);
        //System.out.println(titular1.getTipo());
        titular1.setDataUltimaAtualizacao(LocalDateTime.parse("2023-07-20T13:56"));
        System.out.println(titular1.getDataUltimaAtualizacao());

        Pessoa titular2 = new Pessoa();
        titular2.setNome("Abadia");
        titular2.setDocumento ("5382645");

        ContaInvestimento minhaConta = new ContaInvestimento(titular1,123,986);
//        minhaConta.setTitular(titular1);
//        minhaConta.setAgencia (123);
//        minhaConta.setNumero(986);
//       // minhaConta.setSaldo(27_000);
        CaixaEletronica caixaEletronica = new CaixaEletronica();

        ContaEspecial suaConta = new ContaEspecial(titular2, 126, 906, new BigDecimal(1000));
//        suaConta.setTitular(titular2);
//        suaConta.setAgencia (126);
//        suaConta.setNumero(906);
//        //suaConta.setSaldo(7_000);
    try {
        minhaConta.depositar(new BigDecimal("30000"));
        minhaConta.sacar(new BigDecimal("1000"));
//        minhaConta.creditarRendimento(0.8);

        suaConta.depositar(new BigDecimal("15000"));
        suaConta.sacar(new BigDecimal("15500"));
        suaConta.debitarTarifaMensal();

        Boleto boletoEscola = new Boleto(titular2,new BigDecimal("1000"));
        Holerite salarioFuncionario = new Holerite(titular2, new BigDecimal("100"), 160);

        caixaEletronica.pagar(boletoEscola, minhaConta);
        caixaEletronica.pagar(salarioFuncionario, minhaConta);

        caixaEletronica.estornarPagamento(boletoEscola, minhaConta);

        boletoEscola.imprimirRecibo();
        salarioFuncionario.imprimirRecibo();
    } catch(SaldoInsuficienteException e){
        System.out.println("Errro ao executar operação na conta:" + e.getMessage());
    }
//        System.out.println("Boleto pago:"+ boletoEscola.estaPago());
//        System.out.println("Salario pago:"+ salarioFuncionario.estaPago());


        caixaEletronica.imprimirSaldo(minhaConta);
        System.out.println();
        caixaEletronica.imprimirSaldo(suaConta);


//        System.out.println("Titular:"+ minhaConta.getTitular().getNome());
//        System.out.println("Saldo:"+ minhaConta.getSaldo());

//        System.out.println("Titular:"+ suaConta.getTitular().getNome());
//        System.out.println("Saldo:"+ suaConta.getSaldo());
    }
}
