# Gerenciador ADACA

Sistema de Gerenciamento para acompanhamento de crianças, atividades, relatórios, gráficos e configurações, desenvolvido com Spring Boot.

## Sumário

- [Visão Geral](#visão-geral)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Documentação das Classes](#documentação-das-classes)
- [Endpoints da API](#endpoints-da-api)
- [Como Executar](#como-executar)
- [Testes](#testes)
- [Licença](#licença)

---

## Visão Geral

O Gerenciador ADACA faz parte do Ambiente Digital de Aprendizagem para Crianças Autistas (ADACA), que desenvolve ferramentas computacionais para auxiliar na aprendizagem e inclusão digital de crianças autistas. O sistema é utilizado no Laboratório ADACA (LADACA), que conta com três ambientes: lúdico, computacional e de gerenciamento/relatórios. O servidor centraliza os dados das sessões de jogos de cada criança, permitindo o acompanhamento do desenvolvimento individual.

O objetivo principal do sistema é promover a análise da evolução dos usuários do laboratório, por meio da criação de tabelas e gráficos baseados nos dados capturados durante o uso contínuo dos jogos e atividades. Esses dados são fundamentais para que avaliadores possam observar o desempenho das crianças ao longo do tempo, contribuindo também para o aprimoramento dos jogos e do planejamento das atividades do projeto.

O sistema é desenvolvido utilizando análise e projeto orientados a objetos, implementado em Java, com uso de padrões de projeto, Spring Framework e AngularJS. O banco de dados utilizado é o MySQL Server, e todo o desenvolvimento prioriza o uso de software livre. O Spring oferece uma arquitetura baseada em interfaces e POJOs, facilitando a segurança, controle de transações e testes unitários, além de modularidade para futuras expansões.

O Gerenciador ADACA permite a geração de relatórios científico-estatísticos a partir dos dados das sessões de jogos, possibilitando análises detalhadas sobre o desenvolvimento e aprendizagem das crianças com autismo. Essas análises orientam adequações nos jogos, atividades e no próprio sistema, sempre com foco na aprendizagem da criança.

Além disso, o projeto ADACA busca estratégias para facilitar a interação, comunicação e aprendizado das crianças, incluindo o desenvolvimento de aplicativos Android para dispositivos móveis. O ambiente computacional visa promover a inserção do autista na sociedade, incentivando o uso de computadores, tablets e celulares para trabalhar conteúdos de língua portuguesa e matemática, desenvolvendo o raciocínio lógico por meio de jogos e atividades lúdicas.

### Metodologia

O projeto utiliza métodos quantitativos discretos e contínuos para avaliação do desempenho das crianças. Medidas discretas incluem, por exemplo, a quantidade de cliques do mouse, enquanto medidas contínuas avaliam o tempo gasto em tarefas. A partir desses dados, são gerados gráficos que facilitam a avaliação individual das crianças.

O sistema gera relatórios mensais de avaliação para cada aluno, gráficos de desempenho a qualquer momento e testes periódicos para medir o alcance das metas de aprendizagem. Os resultados reais podem ser comparados com os esperados, tanto a curto quanto a longo prazo.

### Resultados e Discussão

A análise dos dados segue referências da literatura sobre uso de jogos para crianças com dificuldades de atenção e aprendizagem. São considerados métodos quantitativos e qualitativos, como número de acessos, tempo gasto, tipo de jogo utilizado e desempenho obtido. Estatísticas como tempo médio de atividade e desempenho (razão entre cliques corretos e totais) são calculadas, e ferramentas como a linguagem R podem ser utilizadas para análise e visualização gráfica dos dados.

Além disso, técnicas de aprendizado de máquina podem ser aplicadas para identificar a taxa de aprendizado da criança, dificuldades e sucessos em diferentes jogos, permitindo adaptar as atividades ao perfil individual de cada usuário.

---

## Estrutura do Projeto

```bash
src/
  main/
    java/
      br/com/adaca/
        config/         # Configurações do Spring (segurança, web, etc)
        controller/     # Controladores REST e MVC do sistema
        dto/            # Objetos de Transferência de Dados (DTOs)
        exception/      # Exceções customizadas
        handler/        # Manipuladores globais de exceções e segurança
        mapper/         # Mapeadores entre entidades e DTOs (MapStruct)
        model/          # Entidades do domínio (JPA)
        repository/     # Interfaces de acesso a dados (Spring Data)
        service/        # Serviços de negócio
        util/           # Utilitários e helpers
        view/           # Views abstratas e suporte a visualização
    resources/
      static/           # Arquivos estáticos (CSS, JS, imagens)
      templates/        # Templates HTML (Thymeleaf)
      application.properties # Configurações da aplicação
  test/
    java/
      br/com/adaca/     # Testes automatizados do sistema
```

---

## Documentação das Classes

### Modelos

#### Autista

Classe entidade que representa uma criança cadastrada no sistema.

- **Atributos principais:**
  - `Integer id`: Identificador único.
  - `String nome`: Nome da criança.
  - `String sexo`: Sexo.
  - `Date dtnasc`: Data de nascimento.
  - `String classificacao`: Classificação TEA.
  - `String escola`: Escola frequentada.
  - `String mediador`: Nome do mediador.
  - `Boolean medicamentos`: Indica se faz uso de medicamentos.
  - `String brinquedo`, `alimento`, `bebida`, `atividade`, `medo`: Preferências e características.
  - `Boolean ler`, `escrever`: Habilidades.
  - `String comunicacao`, `terapia`, `observacao`: Informações adicionais.
  - `Byte[] foto`: Foto da criança.
  - `List<Medicamento> medicamentoList`: Lista de medicamentos associados.

#### Atividade

Entidade que representa uma atividade realizada pelas crianças.

- **Atributos principais:**
  - `Integer id`
  - `String nome`
  - `String classificacao`
  - `Integer nivel`
  - `List<Labirinto> labirintoList`
  - `List<Resultado> resultadoList`

#### Labirinto

Entidade que representa um labirinto resolvido pela criança.

- **Atributos principais:**
  - `Integer id`
  - `Integer quanterro`
  - `String paredes`
  - `Integer quantdicas`
  - `String posicaoerro`
  - `String teclaerrada`
  - `Integer quantbotoes`
  - `Date horainicio`
  - `Date horafim`

#### Medicamento

Entidade para registro de medicamentos.

- **Atributos principais:**
  - `Integer id`
  - `String nome`
  - `String tipo`
  - `String composicao`
  - `String laboratorio`
  - `String posologia`
  - `String observacao`
  - `String stats`

#### Relatorio

Entidade para relatórios gerados no sistema.

- **Atributos principais:**
  - `Integer id`
  - `Autista idautista`
  - `Date datagerado`
  - `String tiporelatorio`
  - `byte[] relatorio`

#### Resultado

Entidade para resultados de atividades.

- **Atributos principais:**
  - `Integer id`
  - `Integer dicas`
  - `Integer cliquecerto`
  - `Integer cliqueerrado`
  - `String mouseclique`
  - `String mousedrag`
  - `String mousepos`
  - `String poserrado`
  - `Date horainicio`
  - `Date horafim`

#### Responsavel

Entidade para responsáveis das crianças.

- **Atributos principais:**
  - `Integer id`
  - `String cpf`
  - `String nome`
  - `String sexo`
  - `String email`
  - `String telefone`
  - `String celular`
  - `String endereco`
  - `String cidade`

#### Sessao

Entidade para sessões de uso do sistema.

- **Atributos principais:**
  - `Integer id`
  - `Date datalogin`
  - `Date datalogout`
  - `Autista idautista`
  - `Tutor idtutor`

#### Grafico

Entidade para gráficos gerados.

- **Atributos principais:**
  - `Integer id`
  - `String grafico`
  - `String tiporelatorio`
  - `Date datagerado`
  - `Administrador idadministrador`

---

### DTOs

- **AutistaDTO:** DTO para transferência de dados de crianças.
- **AtividadeDTO:** DTO para atividades.
- **LabirintoDTO:** DTO para labirintos.
- **MedicamentoDTO:** DTO para medicamentos.
- **RelatorioEstatisticaDTO:** DTO para estatísticas de relatórios.
- **ResponsavelDTO:** DTO para responsáveis.
- **ResultadoDTO:** DTO para resultados.
- **SessaoDTO:** DTO para sessões.
- **TutorDTO:** DTO para tutores.

---

### Services

- **AutistaService:** Serviço para operações de negócio relacionadas a crianças.
- **LabirintoService:** Serviço para operações de labirintos.
- **RelatorioService:** Serviço para relatórios.
- **ResultadoService:** Serviço para resultados.
- **GraficoService:** Serviço para gráficos.
- **AdministradorService:** Serviço para administradores.
- **TutorService:** Serviço para tutores.
- **RelatorioEstatisticaService:** Serviço para estatísticas de relatórios.

---

### Mappers

- **LabirintoMapper:** MapStruct mapper para conversão entre entidades e DTOs de Labirinto.
- **GraficoMapper:** MapStruct mapper para conversão entre entidades e DTOs de Gráfico.

---

### Exceções

- **NotFoundException:** Exceção lançada quando um recurso não é encontrado.
- **ConflictException:** Exceção lançada em caso de conflito de dados.
- **EmptyException:** Exceção lançada quando um recurso está vazio.

---

### Configurações

- **SecurityConfig:** Configuração de segurança do Spring Security.

---

### Utilitários

- **ErrorDetails:** Classe utilitária para detalhamento de erros em respostas HTTP.

---

### View

- **View:** Classe abstrata para views, com métodos utilitários para listagem, adição, edição e remoção de entidades.

---

## Endpoints da API

Abaixo estão listados os endpoints REST disponíveis no Gerenciador ADACA, conforme implementados nos controllers do projeto.

---

### AutistaController

- **GET `/Gerenciador/Autistas`**  
  Lista todos os autistas cadastrados.

- **GET `/Gerenciador/Autistas/{autistaId}`**  
  Retorna os dados de um autista específico pelo ID.

- **POST `/Gerenciador/Autistas`**  
  Cadastra um novo autista.

- **PUT `/Gerenciador/Autistas`**  
  Atualiza os dados de um autista existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Autistas/{autistaId}`**  
  Remove um autista pelo ID.

- **DELETE `/Gerenciador/Autistas`**  
  Remove um autista enviando o objeto completo no corpo da requisição.

---

### AtividadeController

- **GET `/Gerenciador/Atividades`**  
  Lista todas as atividades.

- **GET `/Gerenciador/Atividades/{atividadeId}`**  
  Retorna os dados de uma atividade específica.

- **POST `/Gerenciador/Atividades`**  
  Cadastra uma nova atividade.

- **PUT `/Gerenciador/Atividades`**  
  Atualiza uma atividade existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Atividades/{atividadeId}`**  
  Remove uma atividade pelo ID.

- **DELETE `/Gerenciador/Atividades`**  
  Remove uma atividade enviando o objeto completo no corpo da requisição.

---

### LabirintoController

- **GET `/Gerenciador/Labirintos`**  
  Lista todos os labirintos.

- **GET `/Gerenciador/Labirintos/{labirintoId}`**  
  Retorna os dados de um labirinto específico.

- **POST `/Gerenciador/Labirintos`**  
  Cadastra um novo labirinto.

- **PUT `/Gerenciador/Labirintos`**  
  Atualiza um labirinto existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Labirintos/{labirintoId}`**  
  Remove um labirinto pelo ID.

- **DELETE `/Gerenciador/Labirintos`**  
  Remove um labirinto enviando o objeto completo no corpo da requisição.

---

### MedicamentoController

- **GET `/Gerenciador/Medicamentos`**  
  Lista todos os medicamentos.

- **GET `/Gerenciador/Medicamentos/{medicamentoId}`**  
  Retorna os dados de um medicamento específico.

- **POST `/Gerenciador/Medicamentos`**  
  Cadastra um novo medicamento.

- **PUT `/Gerenciador/Medicamentos`**  
  Atualiza um medicamento existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Medicamentos/{medicamentoId}`**  
  Remove um medicamento pelo ID.

- **DELETE `/Gerenciador/Medicamentos`**  
  Remove um medicamento enviando o objeto completo no corpo da requisição.

---

### RelatorioController

- **GET `/Gerenciador/Relatorios`**  
  Lista todos os relatórios.

- **GET `/Gerenciador/Relatorios/{relatorioId}`**  
  Retorna os dados de um relatório específico.

- **POST `/Gerenciador/Relatorios`**  
  Gera um novo relatório.

- **PUT `/Gerenciador/Relatorios`**  
  Atualiza um relatório existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Relatorios/{relatorioId}`**  
  Remove um relatório pelo ID.

- **DELETE `/Gerenciador/Relatorios`**  
  Remove um relatório enviando o objeto completo no corpo da requisição.

---

### ResultadoController

- **GET `/Gerenciador/Resultados`**  
  Lista todos os resultados.

- **GET `/Gerenciador/Resultados/{resultadoId}`**  
  Retorna os dados de um resultado específico.

- **POST `/Gerenciador/Resultados`**  
  Cadastra um novo resultado.

- **PUT `/Gerenciador/Resultados`**  
  Atualiza um resultado existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Resultados/{resultadoId}`**  
  Remove um resultado pelo ID.

- **DELETE `/Gerenciador/Resultados`**  
  Remove um resultado enviando o objeto completo no corpo da requisição.

---

### ResponsavelController

- **GET `/Gerenciador/Responsaveis`**  
  Lista todos os responsáveis.

- **GET `/Gerenciador/Responsaveis/{responsavelId}`**  
  Retorna os dados de um responsável específico.

- **POST `/Gerenciador/Responsaveis`**  
  Cadastra um novo responsável.

- **PUT `/Gerenciador/Responsaveis`**  
  Atualiza um responsável existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Responsaveis/{responsavelId}`**  
  Remove um responsável pelo ID.

- **DELETE `/Gerenciador/Responsaveis`**  
  Remove um responsável enviando o objeto completo no corpo da requisição.

---

### SessaoController

- **GET `/Gerenciador/Sessoes`**  
  Lista todas as sessões.

- **GET `/Gerenciador/Sessoes/{sessaoId}`**  
  Retorna os dados de uma sessão específica.

- **POST `/Gerenciador/Sessoes`**  
  Cadastra uma nova sessão.

- **PUT `/Gerenciador/Sessoes`**  
  Atualiza uma sessão existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Sessoes/{sessaoId}`**  
  Remove uma sessão pelo ID.

- **DELETE `/Gerenciador/Sessoes`**  
  Remove uma sessão enviando o objeto completo no corpo da requisição.

---

### GraficoController

- **GET `/Gerenciador/Graficos`**  
  Lista todos os gráficos.

- **GET `/Gerenciador/Graficos/{graficoId}`**  
  Retorna os dados de um gráfico específico.

- **POST `/Gerenciador/Graficos`**  
  Gera um novo gráfico.

- **PUT `/Gerenciador/Graficos`**  
  Atualiza um gráfico existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Graficos/{graficoId}`**  
  Remove um gráfico pelo ID.

- **DELETE `/Gerenciador/Graficos`**  
  Remove um gráfico enviando o objeto completo no corpo da requisição.

---

### AdministradorController

- **GET `/Gerenciador/Administradores`**  
  Lista todos os administradores.

- **GET `/Gerenciador/Administradores/{administradorId}`**  
  Retorna os dados de um administrador específico.

- **POST `/Gerenciador/Administradores`**  
  Cadastra um novo administrador.

- **PUT `/Gerenciador/Administradores`**  
  Atualiza um administrador existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Administradores/{administradorId}`**  
  Remove um administrador pelo ID.

- **DELETE `/Gerenciador/Administradores`**  
  Remove um administrador enviando o objeto completo no corpo da requisição.

---

### TutorController

- **GET `/Gerenciador/Tutores`**  
  Lista todos os tutores.

- **GET `/Gerenciador/Tutores/{tutorId}`**  
  Retorna os dados de um tutor específico.

- **POST `/Gerenciador/Tutores`**  
  Cadastra um novo tutor.

- **PUT `/Gerenciador/Tutores`**  
  Atualiza um tutor existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Tutores/{tutorId}`**  
  Remove um tutor pelo ID.

- **DELETE `/Gerenciador/Tutores`**  
  Remove um tutor enviando o objeto completo no corpo da requisição.

---

### ConfiguracaoController

- **GET `/Gerenciador/Configuracoes`**  
  Lista todas as configurações.

- **GET `/Gerenciador/Configuracoes/{configuracaoId}`**  
  Retorna os dados de uma configuração específica.

- **POST `/Gerenciador/Configuracoes`**  
  Cadastra uma nova configuração.

- **PUT `/Gerenciador/Configuracoes`**  
  Atualiza uma configuração existente (o ID é passado no corpo da requisição).

- **DELETE `/Gerenciador/Configuracoes/{configuracaoId}`**  
  Remove uma configuração pelo ID.

- **DELETE `/Gerenciador/Configuracoes`**  
  Remove uma configuração enviando o objeto completo no corpo da requisição.

---

> **Observação:**  
> Os endpoints podem exigir autenticação e/ou autorização. Consulte a configuração de segurança do projeto para detalhes.

---

## Como Executar

1. Certifique-se de ter o Java 8+ e o Gradle instalados.
2. Configure o banco de dados conforme o arquivo `application.properties`.
3. Execute:

```sh
./gradlew bootRun
```

Acesse [http://localhost:8080/Gerenciador/menu](http://localhost:8080/Gerenciador/menu)

---

## Testes

Os testes automatizados estão em `src/test/java/br/com/adaca/GerenciadorApplicationTests.java`.

Execute:

```sh
./gradlew test
```

## Adição/Extensão de Recursos

O Gerenciador ADACA foi projetado com uma arquitetura modular e orientada a serviços, facilitando a adição ou extensão de recursos de forma organizada e segura. Para adicionar um novo recurso ou funcionalidade (por exemplo, um novo tipo de relatório, exportação de dados, filtros, etc.), siga as etapas abaixo, respeitando as camadas e responsabilidades do projeto:

1. **Modelagem do Domínio**
   - Crie ou altere entidades no diretório `model/` para representar o novo recurso, se necessário.
   - Atualize ou crie DTOs em `dto/` para transportar os dados entre as camadas.

2. **Persistência**
   - Implemente ou ajuste repositórios em `repository/` para acesso ao banco de dados.

3. **Regra de Negócio**
   - Implemente a lógica do novo recurso em um service dentro de `service/`.
   - Adicione validações, regras de negócio e integrações necessárias.

4. **Exposição via Controller**
   - Crie ou altere endpoints REST no controller correspondente em `controller/`.
   - Utilize DTOs para entrada e saída de dados, mantendo a separação entre domínio e apresentação.

5. **Mapeamento**
   - Utilize ou crie mappers em `mapper/` para conversão entre entidades e DTOs, facilitando a manutenção e testes.

6. **Interface do Usuário**
   - Altere ou crie templates HTML em `resources/templates/Gerenciador/` para refletir o novo recurso na interface.
   - Adicione ou ajuste scripts em `resources/static/js/` para dar suporte à nova funcionalidade.

7. **Configuração e Segurança**
   - Caso necessário, ajuste permissões e regras de acesso em `config/SecurityConfig.java` para proteger o novo recurso.

8. **Testes**
   - Implemente testes automatizados para o novo recurso em `test/java/br/com/adaca/`, garantindo a qualidade e integridade do sistema.

**Exemplo:**  
Para adicionar uma nova funcionalidade de exportação de relatório em PDF:

- Implemente o método de geração de PDF no service.
- Crie um endpoint no controller para exportação.
- Adicione um botão na interface de relatórios para acionar a exportação.
- Ajuste permissões se necessário.
- Implemente testes para a nova funcionalidade.

> **Dica:** Sempre siga o fluxo: Model → Repository → Service → Controller → View, utilizando DTOs, mappers e respeitando as regras de negócio e segurança do projeto.

---

## Licença

Projeto de uso interno ADACA. Para uso externo, consulte o autor.

---
