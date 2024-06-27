# Aplicação Java de Rede

Este projeto é uma aplicação Java que demonstra comunicação de rede entre um cliente e um servidor usando sockets TCP e UDP. Ele inclui interfaces gráficas simples usando Swing para interação do usuário.

## Funcionalidades

- **Servidor TCP:** Aceita conexões de clientes e ecoa mensagens recebidas.
- **Cliente TCP:** Conecta-se ao servidor TCP e envia mensagens, exibindo a resposta do servidor.
- **Servidor UDP:** Recebe datagramas UDP, processa e envia uma resposta.
- **Cliente UDP:** Envia datagramas UDP para o servidor e exibe a resposta recebida.
- **NetworkApp:** Interface gráfica centralizada para gerenciar clientes e servidores TCP/UDP.

## Pré-requisitos

- JDK (Java Development Kit) instalado
- Ambiente de desenvolvimento Java configurado corretamente

## Configuração do Projeto

1. **Clone o repositório:**

    ```sh
    git clone https://github.com/waands/network-app.git
    cd network-app/src
    ```

2. **Compilação Manual:**

    ```sh
    javac com/mycompany/networkapp/*.java com/mycompany/networkapp/ui/*.java
    ```

## Executando a Aplicação

### Executando os Servidores

1. **Servidor TCP:**

    ```sh
    java com.mycompany.networkapp.ui.ServerUI
    ```

2. **Servidor UDP:**

    ```sh
    java com.mycompany.networkapp.ui.UDPServerUI
    ```

### Executando o NetworkApp

1. **NetworkApp (Interface de Gerenciamento):**

    ```sh
    java com.mycompany.networkapp.ui.NetworkApp
    ```

### Executando os Clientes

Os NetworkApp.java junta os dois clientes em um só programa, mas caso seja da vontade do usuário ele pode iniciar os clientes separados:

1. **Cliente TCP:**

    ```sh
    java com.mycompany.networkapp.ui.ClientUI
    ```

2. **Cliente UDP:**

    ```sh
    java com.mycompany.networkapp.ui.UDPClientUI
    ```

## Interface do Usuário

- **ClientUI:** Interface gráfica para enviar mensagens TCP para o servidor e exibir respostas.
- **ServerUI:** Interface gráfica para iniciar o servidor TCP na porta especificada e exibir mensagens recebidas.
- **UDPClientUI:** Interface gráfica para enviar mensagens UDP para o servidor e exibir respostas.
- **UDPServerUI:** Interface gráfica para iniciar o servidor UDP na porta especificada e exibir mensagens recebidas.
- **NetworkApp:** Interface gráfica centralizada para gerenciar clientes e servidores TCP/UDP.

## Exemplo de Uso

1. Inicie o servidor TCP ou UDP.
2. Inicie o NetworkApp para gerenciar e monitorar todas as conexões.
3. Interaja com a interface do cliente para enviar mensagens e observe as respostas na interface do servidor.

## Contribuições

Contribuições são bem-vindas! Para grandes mudanças, abra um problema primeiro para discutir o que você gostaria de mudar.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
