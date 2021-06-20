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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/discountCredits.sql
 * Ficheiro:  discountCredits.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: discountCredits.sql
 */

create or replace procedure discountCredits(p_orderID int, p_creditsToUse int)
    is
    v_priceToDiscount float;
    v_actualPrice     float;
    v_final           float;
begin
    v_priceToDiscount := p_creditsToUse / 100;

    select TOTALWITHDISCOUNT into v_actualPrice from INVOICE where ORDERID = p_orderID;

    v_final := v_actualPrice - v_priceToDiscount;
    if v_final < 0 then
        v_final := 0;
    end if;

    update INVOICE set TOTALWITHDISCOUNT = v_final where ORDERID = p_orderID;

end;
/
commit;