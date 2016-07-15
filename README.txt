Controle de Eventos

Controla a organiza��o de eventos acad�micos e suas atividades, desde sua inscri��o evitando interfer�ncias nos hor�rios de cada usu�rio cadastrado. Tamb�m calcula o valor da conta do evento, contando com cada atividade nela adquirida.

Classes Principais
Atividade
� uma classe abstrata que tem o sentido de base para outras classes. L� est�o os principais atributos e m�todos para gerenciar uma atividade qualquer, como as vari�veis de nome, pre�o, lista de inscri��es, etc...
Evento
Considerando evento um tipo de atividade, por v�rias fun��es e vari�veis iguais, a classe evento herda da classe atividade. Tamb�m adicionadas outras caracter�sticas que define um evento como uma lista de atividades que realizar� no evento, per�odo de inscri��o, institui��o, entre demais.
Agenda
Muito usada por varias classes, � uma classe com duas vari�veis do tipo DataEHora onde cada vari�vel tem uma data e hora. Tem fun��es de compara��es de diversas maneiras.
Inscri��o
Pode-se dizer que est� por cima de todos. H� composi��o das principais classes, ligando o usu�rio com o evento. H� uma lista de atividades desejadas com regras para ser adicionada. Tamb�m uma vari�vel pago para controlar a inscri��o.
CalcularConta
Onde os c�lculos s�o realizados. Uma classe separada com m�todos para realizar o calculo do valor da conta da inscri��o.
CupomPromocional
Tamb�m uma classe Abstrata para estender diversos cupons com um tipo e validade.
Usuario
N�o implementado definitivamente, com atributos login, nome, senha e perfil.
Palestra, Minicurso...
S�o classes de tipos de atividades herdadas pela classe Atividade. H� diferen�as entre eles, mas n�o est� definido ainda.
Semana, Simposio...
S�o classes do tipo de Eventos herdadas pela classe Evento. Planejado da mesma forma que as atividades acima.
