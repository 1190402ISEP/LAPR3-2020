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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientNifByEmail.sql
 * Ficheiro:  getClientNifByEmail.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientNifByEmail.sql
 */

create or replace procedure getClientNifByEmail(p_email users.EMAIL%type, p_nif out users.nif%type)
    is

    v_return users.nif%type;


begin

    select nif into v_return from users where EMAIL = p_email;
    p_nif := v_return;


end;
/
commit;