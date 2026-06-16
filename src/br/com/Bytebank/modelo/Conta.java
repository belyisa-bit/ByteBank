package br.com.Bytebank.modelo;

import java.util.ArrayList;
import java.util.List;

public class Conta {
    // Atributos privados (Encapsulamento)
    private int agencia;
    private int numero;
    private Cliente titular; // Composição: Conta "tem um" Cliente
    private double saldo;
    private List<Transacao> historico; // Composição: Conta "tem uma" lista de Transações

    // Construtor para inicializar a conta com dados obrigatórios
    public Conta(int agencia, int numero, Cliente titular) {
        this.agencia = agencia;
        this.numero = numero;
        this.titular = titular;
        this.saldo = 0.0; // Toda conta nova começa com saldo zerado
        this.historico = new ArrayList<>(); // Inicializa a lista de histórico vazia
    }

    // Regra de Negócio: Depósito
    public void depositar(double valor) {
        if (valor > 0) {
            this.saldo += valor;
            this.historico.add(new Transacao("Depósito", valor));
            System.out.println("Depósito de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
        } else {
            System.out.println("Erro: O valor do depósito deve ser positivo.");
        }
    }

    // Regra de Negócio: Saque
    public boolean sacar(double valor) {
        if (valor > 0 && this.saldo >= valor) {
            this.saldo -= valor;
            this.historico.add(new Transacao("Saque", valor));
            System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
            return true;
        } else {
            System.out.println("Erro: Saldo insuficiente ou valor de saque inválido.");
            return false;
        }
    }

    // Regra de Negócio: Transferência
    // Reutiliza os métodos sacar e depositar para evitar duplicação de lógica
    public boolean transferir(double valor, Conta destino) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            
            // Registra detalhes mais específicos no histórico de ambas as contas
            this.historico.add(new Transacao("Transferência enviada para " + destino.getTitular().getNome(), valor));
            destino.historico.add(new Transacao("Transferência recebida de " + this.titular.getNome(), valor));
            
            System.out.println("Transferência para " + destino.getTitular().getNome() + " realizada!");
            return true;
        }
        return false;
    }

    // Exibição do Extrato / Histórico de Transações
    public void exibirExtrato() {
        System.out.println("\n==========================================");
        System.out.println("=== EXTRATO BYTEBANK ===");
        System.out.println("Agência: " + this.agencia + " | Conta: " + this.numero);
        System.out.println("Titular: " + this.titular.getNome());
        System.out.println("Saldo: R$ " + String.format("%.2f", this.saldo));
        System.out.println("\n--- HISTÓRICO DE TRANSAÇÕES ---");
        
        if (this.historico.isEmpty()) {
            System.out.println("Nenhuma transação realizada.");
        } else {
            for (Transacao transacao : this.historico) {
                System.out.println(transacao);
            }
        }
        
        System.out.println("==========================================");
    }

    // Getters (Encapsulamento - acesso controlado aos atributos)
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transacao> getHistorico() {
        return historico;
    }
}