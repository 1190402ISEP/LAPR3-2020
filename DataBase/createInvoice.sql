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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/createInvoice.sql
 * Ficheiro:  createInvoice.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: createInvoice.sql
 */

create or replace procedure createInvoice(p_orderID orders.id%type, p_deliveryFee invoice.deliveryFee%type)
    is

    creditosInvalidos exception;
    v_clientCredits  client.credits%type;
    v_credits_to_use clientOrder.usedcredits%type;

begin
    select USEDCREDITS into v_credits_to_use from CLIENTORDER where ID = p_orderID;

    if v_credits_to_use < 0 then
        raise creditosInvalidos;
    end if;

    select c.CREDITS
    into v_clientCredits
    from CLIENTORDER o
             inner join CLIENT c on c.USEREMAIL = o.USEREMAIL
    where o.ID = p_orderID;

    if v_credits_to_use > v_clientCredits then
        raise creditosInvalidos;
    end if;


    insert into INVOICE (ORDERID, TOTALPRICE, DELIVERYFEE, PRODUCTSTOTALPRICE, TOTALWITHDISCOUNT)
    values (p_orderID, p_deliveryFee, p_deliveryFee, 0, p_deliveryFee);


exception
    when no_data_found then
        raise_application_error(-2020, 'An unexpected error has occurred!');
    when
        creditosInvalidos then
        raise_application_error(-2015, 'Invalid Credits!');

end;
/
commit;