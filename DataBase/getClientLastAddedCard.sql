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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/getClientLastAddedCard.sql
 * Ficheiro:  getClientLastAddedCard.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: getClientLastAddedCard.sql
 */

create or replace function getClientLastAddedCard(p_email users.email%type) return sys_refcursor
    IS

    p_card sys_refcursor;
BEGIN

    open p_card for
        select *
        from CREDITCARD
        where EMAIL = p_email
        order by CREDITCARD.CREATIONDATE desc
            fetch first row only;
    return p_card;

END;
/

commit;