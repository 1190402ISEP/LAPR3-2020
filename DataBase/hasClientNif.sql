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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/hasClientNif.sql
 * Ficheiro:  hasClientNif.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: hasClientNif.sql
 */

create or replace procedure hasClientNif(p_email users.email%type, p_return out int)
    IS
    v_nif users.nif%type;

BEGIN

    select c.nif
    into v_nif
    from users c
    where c.EMAIL = p_email;

    if v_nif is null then
        p_return := 0;
    end if;

    p_return := 1;

exception
    when no_data_found then
        p_return := 0;

END;
/

commit;