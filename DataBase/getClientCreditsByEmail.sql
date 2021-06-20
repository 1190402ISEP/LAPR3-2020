/*
 * António Daniel Barbosa Fernandes, [17.05.21 19:26]
 * Copyright (c) 2021.
 *
 * Programador: António Daniel Barbosa Fernandes
 *
 * UserName: anton
 * ----------------
 * INFORMAÇÕES:
 * Nome do Projeto: GITHUB 1190402
 * Módulo: GITHUB 1190402
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientCreditsByEmail.sql
 * Ficheiro:  getClientCreditsByEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientCreditsByEmail.sql
 */

create or replace procedure getClientCreditsByEmail(p_email users.email%type, p_return out CLIENT.credits%type)
    is
    v_return client.credits%type;
begin

    select credits into v_return from CLIENT where USEREMAIL = p_email;
    p_return := v_return;


end;
/

commit;