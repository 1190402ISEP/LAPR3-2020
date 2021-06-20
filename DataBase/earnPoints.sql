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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/earnPoints.sql
 * Ficheiro:  earnPoints.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: earnPoints.sql
 */

create or replace procedure earnPoints(p_orderID int)
    is
    v_productsPrice    float;
    v_pontosAcumulados float;
    v_pontosInt        int;
    v_email            users.email%type;
    v_actualCredits    int;

begin

    select PRODUCTSTOTALPRICE into v_productsPrice from INVOICE where ORDERID = p_orderID;

    v_pontosAcumulados := 0.05 * v_productsPrice * 100;

    select regexp_substr(v_pontosAcumulados, '[^,]+', 1, 1)
    into v_pontosInt
    from dual;

    select USEREMAIL into v_email from CLIENTORDER where ID = p_orderID;

    select CREDITS into v_actualCredits from CLIENT where USEREMAIL = v_email;
    update CLIENT set CREDITS = v_actualCredits + v_pontosInt where USEREMAIL = v_email;

end;
/
commit;

