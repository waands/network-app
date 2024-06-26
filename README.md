# Aplicação Java de Rede

Este projeto é uma aplicação Java que demonstra comunicação de rede entre um cliente e um servidor usando sockets TCP e UDP. Ele inclui interfaces gráficas simples usando Swing para interação do usuário.

## Funcionalidades

- **Servidor TCP:** Aceita conexões de clientes e ecoa mensagens recebidas.
- **Cliente TCP:** Conecta-se ao servidor TCP e envia mensagens, exibindo a resposta do servidor.
- **Servidor UDP:** Recebe datagramas UDP, processa e envia uma resposta.
- **Cliente UDP:** Envia datagramas UDP para o servidor e exibe a resposta recebida.

## Pré-requisitos

- JDK (Java Development Kit) instalado
- Ambiente de desenvolvimento Java configurado corretamente

## Configuração do Projeto

1. **Clone o repositório:**

git clone https://github.com/waands/network-app.git

cd network-app/src


2. **Compilação Manual:**

javac com/mycompany/networkapp/*.java com/mycompany/networkapp/ui/*.java


## Executando a Aplicação

### Executando o Servidor

1. **Servidor TCP:**

java com.mycompany.networkapp.ui.ServerUI

2. **Servidor UDP:**

java com.mycompany.networkapp.ui.UDPServerUI


### Executando o Cliente

1. **Cliente TCP:**

java com.mycompany.networkapp.ui.ClientUI

2. **Cliente UDP:**

java com.mycompany.networkapp.ui.UDPClientUI


## Interface do Usuário

- **ClienteUI:** Interface gráfica para enviar mensagens TCP para o servidor e exibir respostas.
- **ServerUI:** Interface gráfica para iniciar o servidor TCP na porta especificada e exibir mensagens recebidas.

## Exemplo de Uso

1. Inicie o servidor TCP ou UDP.
2. Inicie o cliente correspondente (TCP ou UDP).
3. Interaja com a interface do cliente para enviar mensagens e observe as respostas na interface do servidor.

## Contribuições

Contribuições são bem-vindas! Para grandes mudanças, abra um problema primeiro para discutir o que você gostaria de mudar.

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
