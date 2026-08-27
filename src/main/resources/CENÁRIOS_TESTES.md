# 📋 CENÁRIOS DE TESTE - SISTEMA DE ESTOQUE ESCOLAR

## Visão Geral
Documento contendo **25 cenários realistas de teste** para validação do Sistema de Gestão de Estoque Escolar. Cada cenário representa situações reais enfrentadas pela administração escolar.

---

## 🎯 CENÁRIOS DE SOLICITAÇÃO E SAÍDA DE MATERIAIS

### Cenário 1: Professor requisita material para aula prática
- **Contexto:** O professor Márcio solicita 50 folhas de papel sulfite para uma atividade de desenho com a turma 2ºA
- **Passos:** 
  1. Professor Márcio faz login no sistema
  2. Acessa a aba "Solicitações"
  3. Seleciona motivo "Atividade Prática"
  4. Escolhe o material "Papel Sulfite A4" e quantidade "50"
  5. Confirma a solicitação
- **Resultado Esperado:** Solicitação criada, estoque reduzido em 50 unidades, registrada em log de saída
- **Dados de Entrada:**
  - `idProfessor: 1`
  - `idMotivo: 1`
  - `descricao: "Atividade de desenho turma 2ºA"`
  - `dataSolicitacao: 2026-07-16T10:30:00`

---

### Cenário 2: Professor solicitação urgente com falta de material
- **Contexto:** Professora Ana solicita 100 unidades de pilhas AA, mas há apenas 20 em estoque
- **Passos:**
  1. Professora Ana inicia solicitação
  2. Sistema identifica quantidade insuficiente
  3. Aviso exibido na tela
- **Resultado Esperado:** Sistema alerta sobre quantidade insuficiente e nega a requisição ou cria aviso para o almoxarife
- **Verificação:** Validar se `material.quantidade < request.quantidade`

---

### Cenário 3: Almoxarife aprova solicitação pendente
- **Contexto:** Almoxarife Marisa revisa solicitações pendentes de material de limpeza
- **Passos:**
  1. Marisa acessa painel de "Solicitações Pendentes"
  2. Seleciona solicitação do professor Carlos
  3. Clica em "Aprovar"
- **Resultado Esperado:** Solicitação marcada como `isAceito = true`, material disponibilizado
- **Endpoint:** `PUT /solicitacoes/{id}/aceitar`

---

### Cenário 4: Múltiplos professores solicitam mesmo material no mesmo dia
- **Contexto:** Professores Márcio, Ana e Carlos solicitam papel A4 no mesmo período
- **Passos:**
  1. Márcio solicita 500 folhas (15h)
  2. Ana solicita 300 folhas (15:30h)
  3. Carlos solicita 200 folhas (16h)
  4. Almoxarife processa na ordem
- **Resultado Esperado:** Primeira solicitação processada, segunda parcialmente (se limite), terceira negada se sem estoque. Sistema mantém fila e registra prioridade

---

### Cenário 5: Solicitação para substituição de material danificado
- **Contexto:** Professor solicita 10 pincéis porque 8 dos anteriores quebraram
- **Passos:**
  1. Seleciona motivo "Substituição - Material Danificado"
  2. Descreve o problema
  3. Submete solicitação
- **Resultado Esperado:** Solicitação criada com tag de devolução/substituição
- **Dados de Entrada:**
  - `descricao: "Substituição - 8 pincéis quebraram na aula anterior"`

---

## 📦 CENÁRIOS DE ENTRADA E RECEBIMENTO DE MATERIAIS

### Cenário 6: Fornecedor entrega material com sucesso
- **Contexto:** Fornecedor "Papelaria Central" entrega 1000 folhas de papel sulfite
- **Passos:**
  1. Almoxarife Marisa escaneia código de barras da entrada
  2. Confirma fornecedor "Papelaria Central"
  3. Valida quantidade (1000 unidades)
  4. Registra como entrada
- **Resultado Esperado:** Estoque aumentado em 1000 unidades, log gerado, data/hora registrada
- **Dados de Entrada:**
  - `fornecedorId: 1`
  - `materialId: 1`
  - `codigo: "670981205"`
  - `quantidade: 1000`
  - `dataEntrada: 2026-07-16T14:00:00`
  - `isDevolucao: false`

---

### Cenário 7: Entrada com discrepância de quantidade
- **Contexto:** Pedido era por 500 pincéis, mas chegaram apenas 450
- **Passos:**
  1. Almoxarife escaneia código e tenta registrar 500
  2. Física recebida é 450
  3. Sistema permite registrar diferença
- **Resultado Esperado:** Estoque aumentado por 450, gera notificação de discrepância, aviso ao fornecedor
- **Ação:** Criar log de discrepância para revisão

---

### Cenário 8: Devolução de material defeituoso
- **Contexto:** Lote de tesouras chegou com defeito, precisa ser devolvido
- **Passos:**
  1. Almoxarife marca como "Devolução"
  2. Indica motivo "Produto Defeituoso"
  3. Contata fornecedor
- **Resultado Esperado:** Entrada registrada com flag `isDevolucao = true`, nota criada, estoque não aumenta
- **Dados de Entrada:**
  - `isDevolucao: true` (marca operação como devolução)

---

### Cenário 9: Recebimento de material novo que ainda não está no catálogo
- **Contexto:** Chega um produto novo (marcadores de ponta dupla) que não existe no sistema
- **Passos:**
  1. Almoxarife tenta registrar entrada
  2. Material não encontrado no catálogo
  3. Sistema guia para cadastro rápido
- **Resultado Esperado:** Material criado com dados básicos, entrada registrada
- **Erro Esperado:** `EntidadeNaoExisteException: "Material não encontrado"`

---

## ⚠️ CENÁRIOS DE LIMITE E ALERTA DE ESTOQUE

### Cenário 10: Material atinge limite mínimo
- **Contexto:** Papel sulfite tem limite mínimo de 200 unidades. Atual é 250.
- **Passos:**
  1. Professor solicita 60 folhas
  2. Sistema calcula: 250 - 60 = 190
  3. 190 < 200 (limite mínimo)
- **Resultado Esperado:** Alerta gerado, email enviado ao almoxarife, sistema recomenda reposição
- **Validação:** `if (novaQuantidade < limiteMínimo) → alertar`

---

### Cenário 11: Material excede limite máximo de armazenagem
- **Contexto:** Limite máximo de papel é 2000 unidades. Fornecedor entrega 2500.
- **Passos:**
  1. Entrada de 2500 unidades será registrada
  2. Sistema detecta excesso: 2500 > 2000
- **Resultado Esperado:** Alerta sobre excesso de estoque, sugestão de armazenagem alternativa
- **Validação:** `if (novaQuantidade > limiteMáximo) → alertar`

---

### Cenário 12: Material sem limite definido
- **Contexto:** Novo material "caneta vermelha" não tem limite ainda
- **Passos:**
  1. Entrada de 500 unidades
  2. Sistema permite porque não há limite
  3. Almoxarife define o limite depois
- **Resultado Esperado:** Entrada processada, almoxarife deve ser lembrado de configurar limites
- **Status:** ⚠️ AVISO para configurar limites

---

## 👤 CENÁRIOS DE GESTÃO DE USUÁRIOS E AUTENTICAÇÃO

### Cenário 13: Almoxarife realiza login e acessa Dashboard
- **Contexto:** Almoxarife Marisa inicia turno
- **Passos:**
  1. Login com email e senha
  2. JWT validado
  3. Acessa dashboard
- **Resultado Esperado:** Token gerado, acesso concedido, histórico de últimas movimentações exibido
- **Dados de Entrada:**
  - `email: "marisa@escola.edu.br"`
  - `senha: "xingu1234"`

---

### Cenário 14: Professor sem acesso tenta acessar painel administrativo
- **Contexto:** Professor tenta acessar relatório de fornecedores
- **Passos:**
  1. Professor faz login
  2. Tenta acessar `/admin/fornecedores`
- **Resultado Esperado:** Acesso negado, mensagem de permissão insuficiente
- **HTTP Status:** 403 Forbidden

---

### Cenário 15: Almoxarife cadastra novo professor
- **Contexto:** Professor novo chega à escola
- **Passos:**
  1. Almoxarife acessa "Cadastro de Professores"
  2. Preenche: nome, email, telefone
  3. Confirma cadastro
- **Resultado Esperado:** Professor criado, pode fazer login e solicitar materiais
- **Dados de Entrada:**
  - `nome: "João Silva"`
  - `email: "joao.silva@escola.edu.br"`
  - `telefone: "11987654321"`

---

## 📊 CENÁRIOS DE RELATÓRIOS E CONSULTAS

### Cenário 16: Almoxarife consulta histórico de movimentações
- **Contexto:** Precisa auditar entradas/saídas do mês
- **Passos:**
  1. Acessa "Movimentações"
  2. Filtra período: 01/07/2026 a 15/07/2026
  3. Seleciona material "Papel Sulfite"
- **Resultado Esperado:** Lista com todas as saídas de papel (por professor/data) e entradas (por fornecedor)
- **Endpoint:** `GET /pedido-saida?materialId=1&dataInicio=2026-07-01&dataFim=2026-07-15`

---

### Cenário 17: Relatório de estoque por categoria
- **Contexto:** Diretor quer saber quanto há em "Materiais de Limpeza"
- **Passos:**
  1. Abre "Relatórios"
  2. Seleciona "Por Categoria"
  3. Escolhe "Limpeza"
- **Resultado Esperado:** Exibe quantidade, limite, status (normal/crítico) de cada material
- **Endpoint:** `GET /materiais?categoriaId=X`

---

### Cenário 18: Histórico de solicitações de um professor
- **Contexto:** Verificar padrão de requisições do professor Márcio
- **Passos:**
  1. Busca professor "Márcio"
  2. Visualiza todas suas solicitações (2026)
- **Resultado Esperado:** Lista com datas, materiais, quantidades, motivos
- **Endpoint:** `GET /solicitacoes?professorId=1`

---

## 🏢 CENÁRIOS DE FORNECEDORES E CATEGORIAS

### Cenário 19: Cadastro de novo fornecedor
- **Contexto:** Fornecedor novo "Distribuidora XYZ" deve ser adicionado
- **Passos:**
  1. Almoxarife acessa "Fornecedores"
  2. Clica em "Novo Fornecedor"
  3. Preenche: nome, email, telefone, tipo (Distribuidor)
- **Resultado Esperado:** Fornecedor criado, pode receber entradas de material
- **Dados de Entrada:**
  - `nome: "Distribuidora XYZ"`
  - `email: "contato@distribuidoraxyz.com.br"`
  - `telefone: "1133334444"`
  - `tipoFornecedorId: 1`

---

### Cenário 20: Exclusão de fornecedor com histórico
- **Contexto:** Fornecedor "ABC Ltda" saiu do ramo
- **Passos:**
  1. Busca fornecedor
  2. Tenta excluir
  3. Sistema encontra 50 PedidosEntrada vinculados
- **Resultado Esperado:** Sistema alerta sobre cascata de exclusão, pede confirmação
- **Validação:** Verificar referências antes de deletar (CASCADE)

---

### Cenário 21: Cadastro de categoria e materiais associados
- **Contexto:** Criar categoria "Tecnologia" com itens como "Cabo USB", "HD Externo"
- **Passos:**
  1. Cria categoria "Tecnologia"
  2. Adiciona materiais à categoria
  3. Define unidade de medida (Unidade)
  4. Configura limites
- **Resultado Esperado:** Categoria e materiais criados com relacionamentos
- **Dados de Entrada:**
  - `nomeCategoria: "Tecnologia"`
  - Materiais:
    - `nomeMaterial: "Cabo USB"`, `unidadeMedida: "Unidade"`, `quantidade: 50`
    - `nomeMaterial: "HD Externo"`, `unidadeMedida: "Unidade"`, `quantidade: 5`

---

## 🤖 CEN��RIOS DE INTEGRAÇÃO E INTELIGÊNCIA ARTIFICIAL

### Cenário 22: Previsão de IA para reposição automática
- **Contexto:** IA analisa histórico e recomenda compra de papel
- **Passos:**
  1. Sistema calcula consumo médio (50 folhas/dia)
  2. Estoque atual: 200
  3. IA prevê falta em 4 dias
  4. Gera sugestão de compra
- **Resultado Esperado:** Recomendação exibida, almoxarife pode gerar pedido de entrada automático
- **Cálculo:** `diasRestantes = quantidadeAtual / consumoDiário`

---

### Cenário 23: Escala de atendimento influencia prioridade
- **Contexto:** Pedido de manhã vs pedido de noite tem diferentes prioridades
- **Passos:**
  1. Professor solicita material (escala: "Manhã")
  2. Sistema associa escala ao pedido
  3. Prioridade definida por escala
- **Resultado Esperado:** Pedidos agrupados e processados por escala
- **Dados de Entrada:**
  - `escalaId: 1` (Manhã)
  - `escalaId: 2` (Noite)

---

## ❌ CENÁRIOS DE VALIDAÇÃO E ERROS

### Cenário 24: Validação de código de barras na entrada
- **Contexto:** Almoxarife tenta registrar entrada com código inválido
- **Passos:**
  1. Escaneia código: "999999999"
  2. Sistema verifica se código existe
  3. Verifica se pertence ao material informado
- **Resultado Esperado:** Erro "Código de barras não encontrado" ou "Código não pertence a este material"
- **Erro Esperado:** `EntidadeInvalidException: "Codigo de barras nao pertence ao material informado"`

---

### Cenário 25: Solicitação com professor inexistente
- **Contexto:** Tentativa de criar solicitação com professor que foi removido
- **Passos:**
  1. API recebe: `idProfessor: 999`
  2. Sistema valida
  3. Professor não existe
- **Resultado Esperado:** Erro 404 "Professor Não Encontrado"
- **Erro Esperado:** `EntidadeNaoExisteException: "Professor Não Encontrado"`
- **HTTP Status:** 404 Not Found

---

## 📊 MAPA DE FUNCIONALIDADES TESTADAS

| Funcionalidade | Cenários |
|---|---|
| **Solicitações** | 1, 2, 3, 4, 5, 18 |
| **Entradas/Recebimentos** | 6, 7, 8, 9, 22 |
| **Limites & Alertas** | 10, 11, 12 |
| **Autenticação & Segurança** | 13, 14, 15 |
| **Relatórios** | 16, 17, 18 |
| **Fornecedores & Categorias** | 19, 20, 21 |
| **IA & Inteligência** | 22, 23 |
| **Validações** | 24, 25 |

---

## 🔧 PROCEDIMENTO DE EXECUÇÃO

1. **Preparação:** Configure dados de teste (professores, fornecedores, materiais)
2. **Execução:** Siga os passos de cada cenário
3. **Validação:** Verifique o resultado esperado
4. **Documentação:** Registre desvios encontrados
5. **Repetição:** Execute para cada versão/ambiente

---

## 📝 Notas Importantes

- Todos os cenários assumem dados válidos previamente cadastrados
- Datas são relativas à data atual do sistema (2026-07-16)
- Endereços de email e telefones são exemplos ilustrativos
- Limites de estoque devem estar configurados antes dos testes
- Logs de movimentação devem ser verificados após cada operação

---

**Última Atualização:** 2026-07-16
**Versão:** 1.0

