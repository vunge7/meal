# SysHospitalar - Modulo de Pagamento e Faturação!

Estou a trabalhar no modulo de "Pagamento e Faturação" (DocumentController) e para prencher os dados pelo front, eu preciso de alguns dados...

1 - para o front poder criar um documeto, do tipo FP, FR, NC, ... eu preciso que o front tenha acesso a uma lista de tipos de documentos que podem ser criados, o que já tem lá disponivel no controler, mas agora preciso poder ver os consumiveis do cliente (os serviços e produtos que ele usou no hospital). então quando o paciente é enviando de ala a ala, preciso ter isso monitorado e ter o preço dos serviços que ele consumiu, bem como os produtos que ele usou.

por exemplo, precisamos criar um seeder que vai ter o preço base para a consulta e preço dos exames requistados, vê o controller dele "LabRequestController", mas isso tem que ser editavel, tanto os preços como os serviços e produtos que o cliente consumiu para poder fazer a cobrança! o objectivo é poder ter o fluxo do primeiro case [# Cenário 1: Consulta Simples (Particular/Dinheiro)] funcional... falta apenas poder fazer as cobranças!


ATT: isso é um sistema de gestão hospital privado... então esse aspecto é muito importante... o front já está a consumir os documentos, vai faltar apenas poder criar, então tem um monte de lugares que ele deve consumir criar um novo documento... valide isso.

ATT: use sempre praticas que respeitem o DRY e o SOLID

