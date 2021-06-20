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
 * Caminho: E:/GITHUB 1190402/Licenciatura/2ºANO/1ºSEMESTRE/LAPR3/DataBase/addCreditCard.sql
 * Ficheiro:  addCreditCard.sql
 * Última Edição: 18/05/21, 19:15
 * Nome da Classe: addCreditCard.sql
 */

create or replace procedure addCreditCard(p_number  CREDITCARD.cardNumber%type, p_cvv creditcard.cvv%type, p_date creditcard.expirateDate%type, p_email creditcard.email%type)
    IS

    dataInvalida exception;
BEGIN

    if p_date <= sysdate then
        raise dataInvalida;
    end if;

    insert into CREDITCARD (CARDNUMBER, EMAIL, CVV, EXPIRATEDATE, creationDate) values (p_number, p_email, p_cvv, p_date, sysdate);

exception

    when dataInvalida then
        raise_application_error(-20010,'Data de expiração inválida!');

    
END;
/
commit;