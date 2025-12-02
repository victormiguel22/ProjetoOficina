package main;

import BO.ClienteBO;
import BO.VeiculoBO;
import BO.OrdemServicoBO;
import DTO.ClienteDTO;
import DTO.VeiculoDTO;
import DTO.OrdemServicoDTO;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static ClienteBO clienteBO = new ClienteBO();
    private static VeiculoBO veiculoBO = new VeiculoBO();
    private static OrdemServicoBO ordemServicoBO = new OrdemServicoBO();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (!login()) {
            System.out.println("Login falhou. Encerrando o programa.");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== Sistema de Gestão para Oficina Mecânica ===");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Veículos");
            System.out.println("3. Gerenciar Ordens de Serviço");
            System.out.println("4. Dashboard Analítico");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    gerenciarClientes();
                    break;
                case 2:
                    gerenciarVeiculos();
                    break;
                case 3:
                    gerenciarOrdens();
                    break;
                case 4:
                    exibirDashboard();
                    break;
                case 5:
                    running = false;
                    System.out.println("Encerrando o sistema...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static boolean login() {
        System.out.print("Usuário: ");
        String user = scanner.nextLine();
        System.out.print("Senha: ");
        String pass = scanner.nextLine();
        return user.equals("admin") && pass.equals("123");
    }

    private static void gerenciarClientes() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Gerenciamento de Clientes ===");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Listar Clientes");
            System.out.println("3. Buscar Cliente por ID");
            System.out.println("4. Buscar Cliente por Nome");
            System.out.println("5. Buscar Cliente por CPF");
            System.out.println("6. Atualizar Cliente");
            System.out.println("7. Excluir Cliente");
            System.out.println("8. Voltar");
            System.out.print("Escolha uma opção: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1:
                        ClienteDTO cliente = new ClienteDTO();
                        System.out.print("Nome: ");
                        cliente.setNome(scanner.nextLine());
                        System.out.print("CPF: ");
                        cliente.setCpf(scanner.nextLine());
                        System.out.print("Telefone: ");
                        cliente.setTelefone(scanner.nextLine());
                        System.out.print("Email: ");
                        cliente.setEmail(scanner.nextLine());
                        System.out.print("Endereço: ");
                        cliente.setEndereco(scanner.nextLine());
                        clienteBO.cadastrar(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                        break;
                    case 2:
                        List<ClienteDTO> clientes = clienteBO.listar();
                        for (ClienteDTO c : clientes) {
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", CPF: " + c.getCpf());
                        }
                        break;
                    case 3:
                        System.out.print("ID: ");
                        int id = scanner.nextInt();
                        ClienteDTO cId = clienteBO.buscarPorId(id);
                        if (cId != null) {
                            System.out.println("Nome: " + cId.getNome() + ", CPF: " + cId.getCpf());
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                        break;
                    case 4:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine();
                        List<ClienteDTO> cNomes = clienteBO.buscarPorNome(nome);
                        for (ClienteDTO c : cNomes) {
                            System.out.println("ID: " + c.getId() + ", Nome: " + c.getNome() + ", CPF: " + c.getCpf());
                        }
                        break;
                    case 5:
                        System.out.print("CPF: ");
                        String cpf = scanner.nextLine();
                        ClienteDTO cCpf = clienteBO.buscarPorCpf(cpf);
                        if (cCpf != null) {
                            System.out.println("Nome: " + cCpf.getNome() + ", CPF: " + cCpf.getCpf());
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                        break;
                    case 6:
                        System.out.print("ID do Cliente: ");
                        int upId = scanner.nextInt();
                        scanner.nextLine();
                        ClienteDTO upCliente = clienteBO.buscarPorId(upId);
                        if (upCliente != null) {
                            System.out.print("Novo Nome (atual: " + upCliente.getNome() + "): ");
                            upCliente.setNome(scanner.nextLine());
                            System.out.print("Novo CPF (atual: " + upCliente.getCpf() + "): ");
                            upCliente.setCpf(scanner.nextLine());
                            // ... outros campos
                            clienteBO.atualizar(upCliente);
                            System.out.println("Cliente atualizado!");
                        } else {
                            System.out.println("Cliente não encontrado.");
                        }
                        break;
                    case 7:
                        System.out.print("ID: ");
                        int delId = scanner.nextInt();
                        clienteBO.excluir(delId);
                        System.out.println("Cliente excluído!");
                        break;
                    case 8:
                        back = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void gerenciarVeiculos() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Gerenciamento de Veículos ===");
            System.out.println("1. Cadastrar Veículo");
            System.out.println("2. Listar Veículos");
            System.out.println("3. Listar Veículos por Cliente");
            System.out.println("4. Buscar Veículo por ID");
            System.out.println("5. Buscar Veículo por Placa");
            System.out.println("6. Atualizar Veículo");
            System.out.println("7. Excluir Veículo");
            System.out.println("8. Voltar");
            System.out.print("Escolha uma opção: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1:
                        VeiculoDTO veiculo = new VeiculoDTO();
                        System.out.print("Placa: ");
                        veiculo.setPlaca(scanner.nextLine());
                        System.out.print("Marca: ");
                        veiculo.setMarca(scanner.nextLine());
                        System.out.print("Modelo: ");
                        veiculo.setModelo(scanner.nextLine());
                        System.out.print("Ano: ");
                        veiculo.setAno(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Cor: ");
                        veiculo.setCor(scanner.nextLine());
                        System.out.print("ID do Cliente: ");
                        veiculo.setClienteId(scanner.nextInt());
                        veiculoBO.cadastrar(veiculo);
                        System.out.println("Veículo cadastrado!");
                        break;
                    case 2:
                        List<VeiculoDTO> veiculos = veiculoBO.listar();
                        for (VeiculoDTO v : veiculos) {
                            System.out.println("ID: " + v.getId() + ", Placa: " + v.getPlaca() + ", Cliente ID: " + v.getClienteId());
                        }
                        break;
                    case 3:
                        System.out.print("ID do Cliente: ");
                        int cliId = scanner.nextInt();
                        List<VeiculoDTO> veicCli = veiculoBO.listarPorCliente(cliId);
                        for (VeiculoDTO v : veicCli) {
                            System.out.println("ID: " + v.getId() + ", Placa: " + v.getPlaca());
                        }
                        break;
                    case 4:
                        System.out.print("ID: ");
                        int vId = scanner.nextInt();
                        VeiculoDTO vById = veiculoBO.buscarPorId(vId);
                        if (vById != null) {
                            System.out.println("Placa: " + vById.getPlaca() + ", Marca: " + vById.getMarca());
                        } else {
                            System.out.println("Veículo não encontrado.");
                        }
                        break;
                    case 5:
                        System.out.print("Placa: ");
                        String placa = scanner.nextLine();
                        VeiculoDTO vPlaca = veiculoBO.buscarPorPlaca(placa);
                        if (vPlaca != null) {
                            System.out.println("ID: " + vPlaca.getId() + ", Marca: " + vPlaca.getMarca());
                        } else {
                            System.out.println("Veículo não encontrado.");
                        }
                        break;
                    case 6:
                        System.out.print("ID do Veículo: ");
                        int upVId = scanner.nextInt();
                        scanner.nextLine();
                        VeiculoDTO upVeiculo = veiculoBO.buscarPorId(upVId);
                        if (upVeiculo != null) {
                            System.out.print("Nova Placa (atual: " + upVeiculo.getPlaca() + "): ");
                            upVeiculo.setPlaca(scanner.nextLine());
                            // ... outros campos
                            veiculoBO.atualizar(upVeiculo);
                            System.out.println("Veículo atualizado!");
                        } else {
                            System.out.println("Veículo não encontrado.");
                        }
                        break;
                    case 7:
                        System.out.print("ID: ");
                        int delVId = scanner.nextInt();
                        veiculoBO.excluir(delVId);
                        System.out.println("Veículo excluído!");
                        break;
                    case 8:
                        back = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void gerenciarOrdens() {
        boolean back = false;
        while (!back) {
            System.out.println("\n=== Gerenciamento de Ordens de Serviço ===");
            System.out.println("1. Cadastrar Ordem");
            System.out.println("2. Listar Ordens");
            System.out.println("3. Listar Ordens por Status");
            System.out.println("4. Listar Ordens por Data");
            System.out.println("5. Listar Ordens por Cliente");
            System.out.println("6. Buscar Ordem por ID");
            System.out.println("7. Atualizar Ordem");
            System.out.println("8. Excluir Ordem");
            System.out.println("9. Voltar");
            System.out.print("Escolha uma opção: ");
            int op = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (op) {
                    case 1:
                        OrdemServicoDTO ordem = new OrdemServicoDTO();
                        ordem.setDataEntrada(new Date());
                        System.out.print("Descrição: ");
                        ordem.setDescricao(scanner.nextLine());
                        System.out.print("Serviços: ");
                        ordem.setServicos(scanner.nextLine());
                        System.out.print("Valor Peças: ");
                        ordem.setValorPecas(scanner.nextDouble());
                        System.out.print("Valor Mão de Obra: ");
                        ordem.setValorMaoObra(scanner.nextDouble());
                        System.out.print("ID do Veículo: ");
                        ordem.setVeiculoId(scanner.nextInt());
                        ordemServicoBO.cadastrar(ordem);
                        System.out.println("Ordem cadastrada! Valor Total: " + ordem.getValorTotal());
                        break;
                    case 2:
                        List<OrdemServicoDTO> ordens = ordemServicoBO.listar();
                        for (OrdemServicoDTO o : ordens) {
                            System.out.println("ID: " + o.getId() + ", Status: " + o.getStatus() + ", Valor Total: " + o.getValorTotal());
                        }
                        break;
                    case 3:
                        System.out.print("Status (Pendente/Em Andamento/Concluida/Entregue): ");
                        String status = scanner.nextLine();
                        List<OrdemServicoDTO> ordStatus = ordemServicoBO.listarPorStatus(status);
                        for (OrdemServicoDTO o : ordStatus) {
                            System.out.println("ID: " + o.getId() + ", Valor Total: " + o.getValorTotal());
                        }
                        break;
                    case 4:
                        // Simplificado: use datas fixas ou parse
                        System.out.println("Implemente entrada de datas se necessário.");
                        break;
                    case 5:
                        System.out.print("ID do Cliente: ");
                        int cliOrdId = scanner.nextInt();
                        List<OrdemServicoDTO> ordCli = ordemServicoBO.listarPorCliente(cliOrdId);
                        for (OrdemServicoDTO o : ordCli) {
                            System.out.println("ID: " + o.getId() + ", Status: " + o.getStatus());
                        }
                        break;
                    case 6:
                        System.out.print("ID: ");
                        int oId = scanner.nextInt();
                        OrdemServicoDTO oById = ordemServicoBO.buscarPorId(oId);
                        if (oById != null) {
                            System.out.println("Status: " + oById.getStatus() + ", Valor Total: " + oById.getValorTotal());
                        } else {
                            System.out.println("Ordem não encontrada.");
                        }
                        break;
                    case 7:
                        System.out.print("ID da Ordem: ");
                        int upOId = scanner.nextInt();
                        scanner.nextLine();
                        OrdemServicoDTO upOrdem = ordemServicoBO.buscarPorId(upOId);
                        if (upOrdem != null) {
                            System.out.print("Novo Status (atual: " + upOrdem.getStatus() + "): ");
                            upOrdem.setStatus(scanner.nextLine());
                            // ... outros campos
                            ordemServicoBO.atualizar(upOrdem);
                            System.out.println("Ordem atualizada! Novo Valor Total: " + upOrdem.getValorTotal());
                        } else {
                            System.out.println("Ordem não encontrada.");
                        }
                        break;
                    case 8:
                        System.out.print("ID: ");
                        int delOId = scanner.nextInt();
                        ordemServicoBO.excluir(delOId);
                        System.out.println("Ordem excluída!");
                        break;
                    case 9:
                        back = true;
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void exibirDashboard() {
        try {
            System.out.println("\n=== Dashboard Analítico ===");
            System.out.println("Total de Clientes: " + clienteBO.listar().size());
            System.out.println("Veículos em Manutenção: " + ordemServicoBO.listarPorStatus("Em Andamento").size());
            double fatTotal = 0;
            List<OrdemServicoDTO> ordens = ordemServicoBO.listar();
            double maxOrdem = 0;
            for (OrdemServicoDTO o : ordens) {
                if (o.getStatus().equals("Entregue")) {
                    fatTotal += o.getValorTotal();
                }
                if (o.getValorTotal() > maxOrdem) {
                    maxOrdem = o.getValorTotal();
                }
            }
            System.out.println("Faturamento Total: R$ " + fatTotal);
            System.out.println("Ordem Mais Cara: R$ " + maxOrdem);

            // Distribuição por Status
            System.out.println("Distribuição de Ordens por Status:");
            System.out.println("Pendente: " + ordemServicoBO.listarPorStatus("Pendente").size());
            System.out.println("Em Andamento: " + ordemServicoBO.listarPorStatus("Em Andamento").size());
            System.out.println("Concluída: " + ordemServicoBO.listarPorStatus("Concluida").size());
            System.out.println("Entregue: " + ordemServicoBO.listarPorStatus("Entregue").size());

            // Ordens por Mês e Faturamento (simplificado, sem gráficos)
            System.out.println("Ordens e Faturamento por Mês: Implemente lógica de agrupamento por mês se necessário.");
        } catch (SQLException e) {
            System.out.println("Erro ao carregar dashboard: " + e.getMessage());
        }
    }
}