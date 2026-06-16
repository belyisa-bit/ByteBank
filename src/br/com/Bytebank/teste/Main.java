package br.com.Bytebank.teste;

// Importamos as classes do pacote modelo para poder usá-las aqui
import br.com.Bytebank.modelo.Cliente;
import br.com.Bytebank.modelo.Conta;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("--- Criando Clientes ---");
        // 1. Instanciando os clientes (Titulares)
        Cliente cliente1 = new Cliente("Isabelle", "123.456.789-00", "Desenvolvedora Java");
        Cliente cliente2 = new Cliente("Davi", "987.654.321-11", "Administrador");

        System.out.println("\n--- Abrindo Contas ---");
        // 2. Criando as contas e associando aos clientes correspondentes
        Conta contaIsabelle = new Conta(1001, 55432, cliente1);
        Conta contaDavi = new Conta(1001, 77651, cliente2);

        System.out.println("\n--- Simulando Operações na Conta de Isabelle ---");
        // 3. Testando Depósito e Saque
        contaIsabelle.depositar(1000.00);
        contaIsabelle.sacar(250.00);
        contaIsabelle.sacar(900.00); // Teste de validação: Deve dar erro de saldo insuficiente

        System.out.println("\n--- Simulando Transferência ---");
        // 4. Testando Transferência entre as contas (Isabelle enviando para Davi)
        contaIsabelle.transferir(300.00, contaDavi);

        System.out.println("\n--- Exibindo Extratos Finais ---");
        // 5. Verificando o histórico e saldos finais de ambas as contas
        contaIsabelle.exibirExtrato();
        contaDavi.exibirExtrato();
    }
}