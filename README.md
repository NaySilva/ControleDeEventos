# ControleDeEventos

Controla a organização e execução de eventos acadêmicos e suas atividades desde sua inscrição evitando interferências nos horários de cada usuário cadastrado. Também tem a responsabilidade de calcular o valor da conta da inscrição em tal evento, contando com cada atividade nela adquirida.

<h4>Principais Classes</h4>

<b>Atividade</b></br>
É uma classe abstrata que tem o sentido de base para outras classes. Lá estão os principais atributos e métodos para gerenciar uma 
atividade qualquer, como as variáveis de nome, preço, lista de inscrições, etc...

<b>Evento</b></br>
Considerando evento um tipo de atividade, a classe evento herda da classe atividade. Também adicionadas outras características que define 
um evento como uma lista de atividades que realizará no evento, período de inscrição, instituição, entre demais..

<b>Agenda</b></br>
Muito usada por varias classes, é uma classe com quatro variáveis onde duas datas e duas horas, referindo ao inicio e o final. Tem funções de 
comparações de diversas maneiras.

<b>Inscrição</b></br>
Pode-se dizer que está por cima de todos. Há composição das principais classes, ligando o usuário com o evento. Há uma lista de atividades
desejadas com regras para ser adicionada. Também uma variável pagamento da classe Pagamento para controlar a custo da inscrição.

<b>Pagamento</b></br>
Onde os cálculos são realizados. Uma classe separada com métodos para realizar o calculo do valor da conta da inscrição. Tem variavel pago
para identificar se esta pago determinado por varias regras.

<b>CupomPromocional</b></br>
Também uma classe Abstrata para estender diversos cupons com um tipo e validade.

<b>Usuario</b></br>
Dados pessoais do usuario do sistema, login e senha para identificar. Tem uma lista de Perfis que o ususario pode tornar e que pode 
variar.

<b>Perfil</b></br>
Defini o que tipo de participação o ususario esta em relação com o evento inscrito.

<b>Palestra, Minicurso e outros tipos de atividades</b></br>
São classes de tipos de atividades herdadas pela classe Atividade. Há diferenças entre eles, mas não está definido ainda.

<b>Semana, Simposio e outros tipos de atividades</b></br>
São classes do tipo de Eventos herdadas pela classe Evento. Planejado da mesma forma que as atividades acima.

<h4>Diagrama de classes</h4>
![](https://github.com/NaySilva/ControleDeEventos/blob/master/diagrama-de-classes.PNG?raw:true "diagrama de classes")

<h4>Como Testar?
Basta clonar ou baixar o projeto inteiro e importar no eclipse.</h4>
