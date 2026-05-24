package spi;

import javax.swing.JOptionPane;

class Conta {
    String numero;
    String nome;
    String pin;
    double saldo;

    Conta(String numero, String nome, String pin, double saldo) {
        this.numero = numero;
        this.nome = nome;
        this.pin = pin;
        this.saldo = saldo;
    }
}

class Referencia {
    String entidade;
    String referencia;
    double valor;

    Referencia(String entidade, String referencia, double valor) {
        this.entidade = entidade;
        this.referencia = referencia;
        this.valor = valor;
    }
}

public class SPI {

    static Conta[] contas = {
            new Conta("001", "Cristovao", "1234", 150000000),
            new Conta("002", "Arystofanes", "4321", 150000000),
            new Conta("003", "Albino", "3214", 150000000)
    };

    static Referencia[] referencias = {
            new Referencia("11111", "123456789", 200),
            new Referencia("22222", "987654321", 150)
    };

    public static Conta encontrarConta(String numero) {
        for (Conta c : contas) {
            if (c.numero.equals(numero)) {
                return c;
            }
        }
        return null;
    }

    public static void main(String[] args) {

        String numConta = JOptionPane.showInputDialog("Digite o número da conta:");
        Conta conta = encontrarConta(numConta);

        if (conta == null) {
            JOptionPane.showMessageDialog(null, "Conta não encontrada!");
            return;
        }

        int tentativas = 3;
        boolean autenticado = false;

        while (tentativas > 0) {
            String pin = JOptionPane.showInputDialog("Digite o PIN:");

            if (conta.pin.equals(pin)) {
                autenticado = true;
                break;
            } else {
                tentativas--;
                JOptionPane.showMessageDialog(null,
                        "PIN incorreto! Tentativas restantes: " + tentativas);
            }
        }

        if (!autenticado) {
            JOptionPane.showMessageDialog(null,
                    "Cartão bloqueado!");
            return;
        }

        int opcao;

        do {
            String menu = "Bem-vindo " + conta.nome +
                    "\nSaldo: " + conta.saldo +
                    "\n\n1 - Levantamento" +
                    "\n2 - Pagamento por Referência" +
                    "\n3 - Transferência" +
                    "\n4 - Serviços Académicos" +
                    "\n0 - Sair";

            opcao = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcao) {

                case 1:
                    double valorLev = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor a levantar:"));

                    if (valorLev > 0 && valorLev <= conta.saldo) {
                        conta.saldo -= valorLev;
                        JOptionPane.showMessageDialog(null,
                                "Levantamento realizado!\nNovo saldo: " + conta.saldo);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Saldo insuficiente ou valor inválido!");
                    }
                    break;

                case 2:
                    String ent = JOptionPane.showInputDialog("Digite a entidade:");
                    String ref = JOptionPane.showInputDialog("Digite a referência:");

                    boolean pago = false;

                    for (Referencia r : referencias) {
                        if (r.entidade.equals(ent) && r.referencia.equals(ref)) {

                            if (r.valor <= conta.saldo) {
                                conta.saldo -= r.valor;
                                JOptionPane.showMessageDialog(null,
                                        "Pagamento realizado!\nValor: " + r.valor +
                                                "\nSaldo: " + conta.saldo);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Saldo insuficiente!");
                            }
                            pago = true;
                            break;
                        }
                    }

                    if (!pago) {
                        JOptionPane.showMessageDialog(null,
                                "Referência inválida!");
                    }
                    break;

                case 3:
                    String destinoNum = JOptionPane.showInputDialog("Conta destino:");
                    Conta destino = encontrarConta(destinoNum);

                    if (destino == null) {
                        JOptionPane.showMessageDialog(null,
                                "Conta destino não encontrada!");
                        break;
                    }

                    double valorTransf = Double.parseDouble(
                            JOptionPane.showInputDialog("Valor a transferir:"));

                    if (valorTransf > 0 && valorTransf <= conta.saldo) {
                        conta.saldo -= valorTransf;
                        destino.saldo += valorTransf;

                        JOptionPane.showMessageDialog(null,
                                "Transferência realizada!\nNovo saldo: " + conta.saldo);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Saldo insuficiente ou valor inválido!");
                    }
                    break;

                case 4:
                    int opcaoServ;

                    String menuServ = "Serviços Académicos\n\n" +
                            "1 - Pagamento de Propina (46.551 Kz)\n" +
                            "2 - Declaração de Frequência\n" +
                            "0 - Voltar";

                    opcaoServ = Integer.parseInt(JOptionPane.showInputDialog(menuServ));

                    switch (opcaoServ) {

                        case 1:
                            double propina = 46551;

                            if (conta.saldo >= propina) {
                                conta.saldo -= propina;
                                JOptionPane.showMessageDialog(null,
                                        "Propina paga!\nSaldo: " + conta.saldo);
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Saldo insuficiente!");
                            }
                            break;

                        case 2:
                            int tipo;

                            String menuDecl = "Declaração de Frequência\n\n" +
                                    "1 - Normal (8.771 Kz)\n" +
                                    "2 - Urgente (16.720 Kz)\n" +
                                    "3 - Muito Urgente (20.834 Kz)\n" +
                                    "0 - Voltar";

                            tipo = Integer.parseInt(JOptionPane.showInputDialog(menuDecl));

                            double valorDecl = 0;

                            switch (tipo) {
                                case 1:
                                    valorDecl = 8771;
                                    break;
                                case 2:
                                    valorDecl = 16720;
                                    break;
                                case 3:
                                    valorDecl = 20834;
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                                    break;
                            }

                            if (valorDecl > 0) {
                                if (conta.saldo >= valorDecl) {
                                    conta.saldo -= valorDecl;
                                    JOptionPane.showMessageDialog(null,
                                            "Pedido realizado!\nSaldo: " + conta.saldo);
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Saldo insuficiente!");
                                }
                            }
                            break;

                        case 0:
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                    }
                    break;
            }

        } while (opcao != 0);

        JOptionPane.showMessageDialog(null, "Sessão terminada!");
    }
}
