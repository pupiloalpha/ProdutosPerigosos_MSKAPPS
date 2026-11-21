# Produtos Perigosos

Aplicativo Android para consulta de informações sobre produtos perigosos. Permite pesquisar produtos pelo número ONU ou pela descrição e consultar dados de risco e orientações associadas.

> **Aviso:** este aplicativo é uma ferramenta de consulta. Em situações de emergência, siga os protocolos oficiais e contate os serviços responsáveis.

## Funcionalidades

- Pesquisa de produtos por número ONU ou descrição.
- Consulta dos dados do produto, incluindo classe e número de risco.
- Exibição de rótulos de risco e informações de segurança relacionadas ao guia aplicável.
- Consulta de classes e números de risco e de infrações.
- Acesso a telefones úteis, com opção de iniciar uma chamada pelo discador do dispositivo.
- Seção com informações sobre o aplicativo.

## Tecnologias

- Kotlin e Android.
- Banco de dados local com Room.
- Dados de produtos carregados de um arquivo JSON incluído nos recursos do aplicativo.
- Gson para converter os dados JSON.
- Coroutines para operações assíncronas, incluindo a carga inicial do banco.

Na primeira inicialização, o aplicativo verifica o banco local e, se necessário, importa os dados do arquivo JSON. As consultas posteriores são feitas no banco Room.

## Estrutura do código

Os arquivos principais ficam em `src/main/java/com/msk/produtosperigosos/`:

- `TelaInicialApp.kt`: atividade principal e navegação entre as telas.
- `db/`: entidade dos produtos, DAO, banco Room e carregamento inicial dos dados.
- `listas/`: telas de pesquisa e listagem.
- `info/`: detalhes do produto e informações sobre guias, riscos e infrações.
- `telas/`: telas de início, rótulos, telefones úteis e informações do aplicativo.

## Abrir e executar

Abra o projeto no Android Studio e execute a configuração Android disponível no repositório. As versões do Android SDK e as instruções exatas de compilação não estão documentadas neste README.

## Dados e segurança

As informações exibidas são destinadas à consulta. Confirme dados operacionais em fontes e documentos oficiais antes de tomar decisões relacionadas ao manuseio, transporte ou resposta a emergências com produtos perigosos.
