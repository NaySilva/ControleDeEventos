Controle de Eventos

Controla a organização de eventos acadêmicos e suas atividades, desde sua inscrição evitando interferências nos horários de cada usuário cadastrado. Também calcula o valor da conta do evento, contando com cada atividade nela adquirida.

Classes Principais
Atividade
É uma classe abstrata que tem o sentido de base para outras classes. Lá estão os principais atributos e métodos para gerenciar uma atividade qualquer, como as variáveis de nome, preço, lista de inscrições, etc...
Evento
Considerando evento um tipo de atividade, por várias funções e variáveis iguais, a classe evento herda da classe atividade. Também adicionadas outras características que define um evento como uma lista de atividades que realizará no evento, período de inscrição, instituição, entre demais.
Agenda
Muito usada por varias classes, é uma classe com duas variáveis do tipo DataEHora onde cada variável tem uma data e hora. Tem funções de comparações de diversas maneiras.
Inscrição
Pode-se dizer que está por cima de todos. Há composição das principais classes, ligando o usuário com o evento. Há uma lista de atividades desejadas com regras para ser adicionada. Também uma variável pago para controlar a inscrição.
CalcularConta
Onde os cálculos são realizados. Uma classe separada com métodos para realizar o calculo do valor da conta da inscrição.
CupomPromocional
Também uma classe Abstrata para estender diversos cupons com um tipo e validade.
Usuario
Não implementado definitivamente, com atributos login, nome, senha e perfil.
Palestra, Minicurso...
São classes de tipos de atividades herdadas pela classe Atividade. Há diferenças entre eles, mas não está definido ainda.
Semana, Simposio...
São classes do tipo de Eventos herdadas pela classe Evento. Planejado da mesma forma que as atividades acima.
