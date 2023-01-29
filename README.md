# CRUD-CEP b_the>next#955

#SOBRE O PROJETO

O projeto foi desenvolvido utilizando o java 17. Antes de executar verificar a existencia do arquivo "crud.property" para que o programa possa ser executado.

o crud.property tem por responsabilidade definir as configuracoes de como o programa deve ser executado.
exemplo de utilizacao:
cidade.controller.tipo = DEFINITIVO

estado.controller.tipo = DEFINITIVO

pais.controller.tipo = DEFINITIVO

cidade.persistencia.tipo = XML

estado.persistencia.tipo = XML

pais.persistencia.tipo = XML

O projeto tem por finalidade cadastrar cidades, estados e paises de forma volatil ou definitva.
Para o cadastro de forma definitiva, as opcoes de salvamento sao xml (.xml) ou binario (.dat)


a unica database anexada ao arquivo foi em formato xml.
